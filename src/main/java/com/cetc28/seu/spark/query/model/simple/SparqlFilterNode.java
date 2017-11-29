package com.cetc28.seu.spark.query.model.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.NullComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaPairRDD;
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
public class SparqlFilterNode extends LeafNode {

	/**
	 * 查询类似如下情况 
	 * ?s attributes:age ?o (FILTER ?o > 20)*
	 */
	private static final long serialVersionUID = 582891289243864013L;
	public static QueryCondition queryCondition;
	public static List<FilterClause<?>> filterClause;
	
	public SparqlFilterNode(int id, int lchild, int rchild, int parent, Term term, QueryCondition queryCondition,List<FilterClause<?>> filterClause) 
	{
		super(id, lchild, rchild, parent, term);
		SparqlFilterNode.queryCondition = queryCondition;
		SparqlFilterNode.filterClause = filterClause;
	}
	
	
	@Override
	public DistributedResultSet query() {

		DistributedResultSet rs=new DistributedResultSet();
		Scan scan = new Scan();
		if(filterClause.size() != 0)
		{
			List<Filter> fList = queryFilter(filterClause,queryCondition);
			FilterList filterList = new FilterList(fList);
			scan.setFilter(filterList);
		}
		else
		{
			//只有列存在且没有filter操作，才扫描相应的结果
			String columnFamily = queryCondition.getFamily();
			String[] column = queryCondition.getRdf().getPredict().getValue().split(":");
			SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
					Bytes.toBytes(column[1]), CompareOp.NOT_EQUAL, new NullComparator());	
			singleColumnValueFilter.setFilterIfMissing(true);			
			scan.setFilter(singleColumnValueFilter);
		}
		
		setScanToConf(scan);
		JavaPairRDD<ImmutableBytesWritable, Result> rdd = sc.newAPIHadoopRDD(hbaseConf,
				TableInputFormat.class, ImmutableBytesWritable.class, Result.class);
		
		JavaPairRDD<String,Result> rddResult=rdd.mapToPair(new PairFunction<Tuple2<ImmutableBytesWritable,Result>,String, Result>() {

			private static final long serialVersionUID = -842442285360302812L;

			@Override
			public Tuple2<String, Result> call(Tuple2<ImmutableBytesWritable, Result> t)
					throws Exception {
				byte[] key=t._2.getRow();
				Tuple2<String, Result> result=new Tuple2<String, Result>(Bytes.toString(key), t._2);
				return result;
			}
			
		});
		rs.setResultRDD(rddResult);

		
		return rs;
	}

	public List<Filter> queryFilter(List<FilterClause<?>> filterClause, QueryCondition queryCondition)
	{
		//scan的过滤条件，判断是主语过滤还是宾语过滤
		String columnFamily = queryCondition.getFamily();
		String[] column = queryCondition.getRdf().getPredict().getValue().split(":");
		Map<FilterClause<?>,String> map = new HashMap<FilterClause<?>,String>();
		List<Filter> listFilter = new ArrayList<>();
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
						Filter filterE = new RowFilter(CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes((String)entry.getKey().getValue())));
						listFilter.add(filterE);
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
						SingleColumnValueFilter singleColumnValueFilterG = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.GREATER, Bytes.toBytes(t));	
						singleColumnValueFilterG.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterG);
						break;
					case "<":
						SingleColumnValueFilter singleColumnValueFilterL = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.LESS, Bytes.toBytes(t));	
						singleColumnValueFilterL.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterL);
						break;
					case "=":
						SingleColumnValueFilter singleColumnValueFilterE = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.EQUAL, Bytes.toBytes(t));	
						singleColumnValueFilterE.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterE);
						break;
					case ">=":
						SingleColumnValueFilter singleColumnValueFilterGOE = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(t));	
						singleColumnValueFilterGOE.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterGOE);
						break;
					case "<=":
						SingleColumnValueFilter singleColumnValueFilterLOE = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.LESS_OR_EQUAL, Bytes.toBytes(t));	
						singleColumnValueFilterLOE.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterLOE);
						break;
					case "!=":
						SingleColumnValueFilter singleColumnValueFilterNE = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.NOT_EQUAL, Bytes.toBytes(t));	
						singleColumnValueFilterNE.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterNE);
						break;
				}
			}
		}
		return listFilter;
	}
}
