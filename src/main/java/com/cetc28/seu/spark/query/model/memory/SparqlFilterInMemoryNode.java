
package com.cetc28.seu.spark.query.model.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;

import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.spark.query.model.LeafNode;
import com.cetc28.seu.spark.query.result.DistributedResultSet;
import com.cetc28.seu.sparql.FilterClause;

import scala.Tuple2;

/**
 * 
 * @author Think
 *
 */
public class SparqlFilterInMemoryNode extends LeafNode {

	/**
	 * 查询类似如下情况 
	 * ?s attributes:age ?o (FILTER ?o > 20)*
	 */
	private static final long serialVersionUID = 582891289243864013L;
	public static QueryCondition queryCondition;
	public static List<FilterClause<?>> filterClause;
	public static JavaPairRDD<ImmutableBytesWritable, Result> rdd;
	
	public SparqlFilterInMemoryNode(int id, int lchild, int rchild, int parent, Term term, QueryCondition queryCondition,List<FilterClause<?>> filterClause) 
	{
		super(id, lchild, rchild, parent, term);
		SparqlFilterInMemoryNode.queryCondition = queryCondition;
		SparqlFilterInMemoryNode.filterClause = filterClause;
		this.setAttributes(queryCondition);
	}
	
	public DistributedResultSet query()
	{
		DistributedResultSet resultSet = new DistributedResultSet();
//		scan.setMaxVersions();
//		scan.setCaching(1000);
//		setScanToConf(scan);
		
//		JavaPairRDD<ImmutableBytesWritable, Result> rdd = sc.newAPIHadoopRDD(hbaseConf,
//				TableInputFormat.class, ImmutableBytesWritable.class, Result.class);
		
		JavaPairRDD<ImmutableBytesWritable, Result> result = rdd.filter(new Function<Tuple2<ImmutableBytesWritable,Result>,Boolean>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean call(Tuple2<ImmutableBytesWritable, Result> v1) throws Exception {
				// TODO Auto-generated method stub
				String columnFamily = queryCondition.getFamily();
				String[] column = queryCondition.getRdf().getPredict().getValue().split(":");
				
				if(filterClause.size() != 0)
				{
					return queryFilter(filterClause,queryCondition,v1._2);
				}
				else if(v1._2.containsColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]))) //只有列存在且没有filter操作，才扫描相应的结果
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
		});
		
		JavaPairRDD<String,Result> rddResult=result.mapToPair(new PairFunction<Tuple2<ImmutableBytesWritable,Result>,String, Result>() {

			private static final long serialVersionUID = -842442285360302812L;

			@Override
			public Tuple2<String, Result> call(Tuple2<ImmutableBytesWritable, Result> t)
					throws Exception {
				byte[] key=t._2.getRow();
				Tuple2<String, Result> result=new Tuple2<String, Result>(Bytes.toString(key), t._2);
				return result;
			}
			
		});
		resultSet.setResultRDD(rddResult);

		
		return resultSet;
	}
	
	public Boolean queryFilter(List<FilterClause<?>> filterClause, QueryCondition queryCondition, Result result)
	{
		//scan的过滤条件，判断是主语过滤还是宾语过滤
		String columnFamily = queryCondition.getFamily();
		String[] column = queryCondition.getRdf().getPredict().getValue().split(":");
		Map<FilterClause<?>,String> map = new HashMap<FilterClause<?>,String>();
		Boolean judgeCondition = false;
		for(FilterClause<?> localFilterClause : filterClause)
		{
			if(localFilterClause.getTerm().equals(queryCondition.getRdf().getSubject()))
			{
				map.put(localFilterClause, "subject");
			}
			if(localFilterClause.getTerm().equals(queryCondition.getRdf().getObject()))
			{
				map.put(localFilterClause, "object");
			}
		}
		for(Entry<FilterClause<?>, String> entry : map.entrySet())
		{
			if(entry.getValue().equals("subject"))
			{
				switch(entry.getKey().getSymbol())
				{
					case "=":
						if(entry.getKey().getValue().equals(Bytes.toString((result.getRow()))))
						{
							judgeCondition = true;
						}
						break;				
				}
			}
			if(entry.getValue().equals("object"))
			{
				int t = 0;
				if(entry.getKey().getValue() instanceof Integer){
					t = (int)entry.getKey().getValue();
				}
				switch(entry.getKey().getSymbol())
				{
					case ">":
						for(Cell cell : result.rawCells())
						{
							if(Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily) && Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1]) && (Bytes.toInt(CellUtil.cloneValue(cell)) > t))
							{
								judgeCondition = true;
								break;
							}
						}
						break;
					case "<":
						for(Cell cell : result.rawCells())
						{
							if(Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily) && Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1]) && (Bytes.toInt(CellUtil.cloneValue(cell)) < t))
							{
								judgeCondition = true;
								break;
							}
						}
						break;
					case "=":
						for(Cell cell : result.rawCells())
						{
							if(Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily) && Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1]) && (Bytes.toInt(CellUtil.cloneValue(cell)) == t))
							{
								judgeCondition = true;
								break;
							}
						}
						break;
					case ">=":
						for(Cell cell : result.rawCells())
						{
							if(Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily) && Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1]) && (Bytes.toInt(CellUtil.cloneValue(cell)) >= t))
							{
								judgeCondition = true;
								break;
							}
						}
						break;
					case "<=":
						for(Cell cell : result.rawCells())
						{
							if(Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily) && Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1]) && (Bytes.toInt(CellUtil.cloneValue(cell)) <= t))
							{
								judgeCondition = true;
								break;
							}
						}
						break;
					case "!=":
						for(Cell cell : result.rawCells())
						{
							if(Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily) && Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1]) && (Bytes.toInt(CellUtil.cloneValue(cell)) != t))
							{
								judgeCondition = true;
								break;
							}
						}
						break;
				}
			}
		}
		return judgeCondition;
	}
}
