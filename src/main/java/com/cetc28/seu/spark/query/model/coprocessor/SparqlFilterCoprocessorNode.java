package com.cetc28.seu.spark.query.model.coprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Row;
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

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.spark.query.model.LeafNode;
import com.cetc28.seu.spark.query.model.memory.SparqlFilterInMemoryNode;
import com.cetc28.seu.spark.query.result.LocalResultSet;
import com.cetc28.seu.spark.query.result.ResultSet;
import com.cetc28.seu.sparql.FilterClause;

import scala.Tuple2;
/**
 * 查询类似如下情况 
 * ?s attributes:age ?o FILTER (?o > 20)*
 * @author Think
 *
 */
public class SparqlFilterCoprocessorNode extends LeafNode {

	private static final long serialVersionUID = 582891289243864013L;
	public static List<FilterClause<?>> filterClause;
	public static HashSet<String> hashSet;
	//public static JavaPairRDD<ImmutableBytesWritable, Result> rdd;
	public String name = Configution.instanceTableName;

	public SparqlFilterCoprocessorNode(int id, int lchild, int rchild, int parent, Term term, QueryCondition queryCondition,List<FilterClause<?>> filterClause) 
	{
		super(id, lchild, rchild, parent, term);
		SparqlFilterCoprocessorNode.filterClause = filterClause;
		this.setAttributes(queryCondition);
	}
	
	@Override
	public LocalResultSet query() {
		long start = System.currentTimeMillis();
		final String family = this.getAttributes().getFamily();
		List<String> answers = this.getAttributes().getAnswer();
		RDF attributeRDF = this.getAttributes().getRdf();
		List<List<String>> index = new ArrayList<List<String>>();
		
		//从文件中读取column名字，用hashSet进行存储
		if(hashSet == null){
			hashSet = HbaseTool.getInstance().inputFile();
		}
		
		if(hashSet.contains(attributeRDF.getPredict().getValue().split(":")[1])){
			index.add(HbaseTool.getInstance().startSearchIndex(family,attributeRDF.getPredict().getValue().split(":")[1],""));
		}else{
			index.add(HbaseTool.getInstance().startSearch(family,attributeRDF.getPredict().getValue().split(":")[1],""));
		}
		
		//与条件或者只有一个条件
		List<String> list = new ArrayList<String>();
		int i = 0;
		for(List<String> out : index){
			if(out.size() == 0){
				continue;
			}
			if(i == 0){
				list = out;
			}else{
				//只包含list和out共有的数
				list.retainAll(out);			
			}
			i++;
		}
//		List<String> temp = new ArrayList<String>();
//		for(List<String> out : index)
//		{
//			if(index.size() == 0){
//			}
//			else if(index.size() == 1)
//			{
//				for(String in : out)
//				{
//					list.add(in);
//				}
//			}else{
//				for(String in : out){
//					if(!temp.contains(in)){
//						temp.add(in);
//					}
//					else{
//						if(!list.contains(in))
//							list.add(in);
//					}
//				}
//			}
//			
//		}
		
		//批量处理
		LocalResultSet resultSet = new LocalResultSet();
		List<Result> listResult = new ArrayList<Result>();
		List<Row> batch = new ArrayList<Row>();
		HTableInterface hTable = null;
		
		try {
			hTable = HbaseTool.getInstance().getTable(name);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (String rowKey : list) {
			Get get = new Get(Bytes.toBytes(rowKey));
			batch.add(get);
		}
		Object[] results = new Object[batch.size()];
		try {
			hTable.batch(batch, results);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Object object: results)
		{
			Result result = (Result)object;
			//filter条件过滤
			if(SparqlFilterCoprocessorNode.filterClause.size() == 0){
				listResult.add(result);
			}else if(queryFilter(SparqlFilterCoprocessorNode.filterClause,this.getAttributes(),result))
				listResult.add(result);
		}
		resultSet.setResults(listResult);
		long end = System.currentTimeMillis();
//		System.out.println("inner pay time: " + (end - start));
		return resultSet;
		
	}
/*	public LocalResultSet query()
	{
		DistributedResultSet resultSet = new DistributedResultSet();
//		scan.setMaxVersions();
//		scan.setCaching(1000);
//		setScanToConf(scan);
		
//		JavaPairRDD<ImmutableBytesWritable, Result> rdd = sc.newAPIHadoopRDD(hbaseConf,
//				TableInputFormat.class, ImmutableBytesWritable.class, Result.class);
		
		JavaPairRDD<ImmutableBytesWritable, Result> result = rdd.filter(new Function<Tuple2<ImmutableBytesWritable,Result>,Boolean>(){

			*//**
			 * 
			 *//*
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
	}*/
	
	public Boolean queryFilter(List<FilterClause<?>> filterClause, QueryCondition queryCondition, Result result)
	{
		//scan的过滤条件，判断是主语过滤还是宾语过滤
		String columnFamily = queryCondition.getFamily();
		String[] column = queryCondition.getRdf().getPredict().getValue().split(":");
//		Map<FilterClause<?>,String> map = new HashMap<FilterClause<?>,String>();
		String compare = "^-?\\d+$";
		Boolean judgeCondition = false;
		int cnt = 1;
		for(FilterClause<?> localFilterClause : filterClause)
		{
			Map<FilterClause<?>,String> map = new HashMap<FilterClause<?>,String>();
			if(localFilterClause.getTerm().equals(queryCondition.getRdf().getSubject()))
			{
				map.put(localFilterClause, "subject");
			}
			if(localFilterClause.getTerm().equals(queryCondition.getRdf().getObject()))
			{
				map.put(localFilterClause, "object");
			}
			
			//多个条件循环判断
			for(Entry<FilterClause<?>, String> entry : map.entrySet())
			{
				//对filter条件的主语进行搜索，主语目前只能判断=
				if(entry.getValue().equals("subject"))
				{
					switch(entry.getKey().getSymbol())
					{
						case "=":
							if(entry.getKey().getValue().equals(Bytes.toString((result.getRow()))))
							{
								if(cnt == 1)
									judgeCondition = true;
								else
									judgeCondition = judgeCondition && true;
							}else{
								judgeCondition = judgeCondition && false;
							}
							break;
						
					}
				}
				//对filter条件的谓语进行搜索
				if(entry.getValue().equals("object"))
				{
					switch(entry.getKey().getSymbol())
					{
						case ">":
							if(entry.getKey().getValue() instanceof Integer){
								if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) > (int)entry.getKey().getValue()){
									if(cnt == 1)
										judgeCondition = true;
									else
										judgeCondition = judgeCondition && true;
								}else{
									judgeCondition = judgeCondition && false;
								}
							}
							break;
						case "<":
							if(entry.getKey().getValue() instanceof Integer){
								if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) < (int)entry.getKey().getValue()){
//									judgeCondition = judgeCondition && true;
									if(cnt == 1)
										judgeCondition = true;
									else
										judgeCondition = judgeCondition && true;
								}else{
									judgeCondition = judgeCondition && false;
								}
							}
							break;
						case "=":
							if(entry.getKey().getValue() instanceof Integer){
								if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) == (int)entry.getKey().getValue()){
									if(cnt == 1)
										judgeCondition = true;
									else
										judgeCondition = judgeCondition && true;
								}else{
									judgeCondition = judgeCondition && false;
								}
							}else if(entry.getKey().getValue() instanceof String){
								if(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]))).equals(entry.getKey().getValue())){
									if(cnt == 1)
										judgeCondition = true;
									else
										judgeCondition = judgeCondition && true;
								}else{
									judgeCondition = judgeCondition && false;
								}
							}
							break;
						case ">=":
							if(entry.getKey().getValue() instanceof Integer){
								if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) >= (int)entry.getKey().getValue()){
									if(cnt == 1)
										judgeCondition = true;
									else
										judgeCondition = judgeCondition && true;
								}else{
									judgeCondition = judgeCondition && false;
								}
							}
							break;
						case "<=":
							if(entry.getKey().getValue() instanceof Integer){
								if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) <= (int)entry.getKey().getValue()){
									if(cnt == 1)
										judgeCondition = true;
									else
										judgeCondition = judgeCondition && true;
								}else{
									judgeCondition = judgeCondition && false;
								}
							}
							break;
						case "!=":
							if(entry.getKey().getValue() instanceof Integer){
								if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) != (int)entry.getKey().getValue()){
									if(cnt == 1)
										judgeCondition = true;
									else
										judgeCondition = judgeCondition && true;
								}else{
									judgeCondition = judgeCondition && false;
								}
							}
							break;
						default :
							judgeCondition = false;
					}
				}
			}
			cnt++;
		}
		return judgeCondition; 
	}	
//		for(Entry<FilterClause<?>, String> entry : map.entrySet())
//		{
//			//对filter条件的主语进行搜索，主语目前只能判断=
//			if(entry.getValue().equals("subject"))
//			{
//				switch(entry.getKey().getSymbol())
//				{
//					case "=":
//						if(entry.getKey().getValue().equals(Bytes.toString((result.getRow()))))
//						{
//							judgeCondition = true;
//						}
//						break;
//					
//				}
//			}
//			//对filter条件的谓语进行搜索
//			if(entry.getValue().equals("object"))
//			{
//				switch(entry.getKey().getSymbol())
//				{
//					case ">":
////						System.out.println(entry.getKey().getValue()+"======");
//						if(entry.getKey().getValue() instanceof Integer){
////							System.out.println(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))+"========"+column[1]);
////							System.out.println((result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]))));
//							if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) > (int)entry.getKey().getValue()){
//								judgeCondition = true;
////								System.out.println("111111111111111");
//								break;
//							}
//						}
//						break;
//					case "<":
//						if(entry.getKey().getValue() instanceof Integer){
//							if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) < (int)entry.getKey().getValue()){
//								judgeCondition = true;
//								break;
//							}
//						}
//						break;
//					case "=":
//						if(entry.getKey().getValue() instanceof Integer){
//							if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) == (int)entry.getKey().getValue()){
//								judgeCondition = true;
//								break;
//							}
//						}else if(entry.getKey().getValue() instanceof String){
//							if(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]))).equals(entry.getKey().getValue())){
//								judgeCondition = true;
//								break;
//							}
//						}
//						break;
//					case ">=":
//						if(entry.getKey().getValue() instanceof Integer){
//							if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) >= (int)entry.getKey().getValue()){
//								judgeCondition = true;
//								break;
//							}
//						}
//						break;
//					case "<=":
//						if(entry.getKey().getValue() instanceof Integer){
//							if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) <= (int)entry.getKey().getValue()){
//								judgeCondition = true;
//								break;
//							}
//						}
//						break;
//					case "!=":
//						if(entry.getKey().getValue() instanceof Integer){
//							if(Integer.parseInt(Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1])))) != (int)entry.getKey().getValue()){
//								judgeCondition = true;
//								break;
//							}
//						}
//						break;
//				}
//			}
//		}
//		return judgeCondition;
//	}
	
	
	
	
/*	@Override
	public ResultSet query() {

		ResultSet rs=new ResultSet();
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
	}*/

	/**
	 * 直接使用hbase的过滤器进行过滤
	 */
	/*public List<Filter> queryFilter(List<FilterClause> filterClause, QueryCondition queryCondition)
	{
		//scan的过滤条件，判断是主语过滤还是宾语过滤
		String columnFamily = queryCondition.getFamily();
		String[] column = queryCondition.getRdf().getPredict().getValue().split(":");
		Map<FilterClause,String> map = new HashMap<FilterClause,String>();
		List<Filter> listFilter = new ArrayList<>();
		for(FilterClause localFilterClause : filterClause)
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
		for(Entry<FilterClause, String> entry : map.entrySet())
		{
			if(entry.getValue().equals("subject"))
			{
				switch(entry.getKey().getSymbol())
				{
					case ">":
						Filter filterG = new RowFilter(CompareOp.GREATER,new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
						listFilter.add(filterG);
						break;
					case "<":
						Filter filterL = new RowFilter(CompareOp.LESS,new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
						listFilter.add(filterL);
						break;
					case "=":
						Filter filterE = new RowFilter(CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
						listFilter.add(filterE);
						break;
					case ">=":
						Filter filterGOE = new RowFilter(CompareOp.GREATER_OR_EQUAL,new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
						listFilter.add(filterGOE);
						break;
					case "<=":
						Filter filterLOE = new RowFilter(CompareOp.LESS_OR_EQUAL,new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
						listFilter.add(filterLOE);
						break;
					case "!=":
						Filter filterNE = new RowFilter(CompareOp.NOT_EQUAL,new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
						listFilter.add(filterNE);
						break;
				}
			}
			if(entry.getValue().equals("object"))
			{
				switch(entry.getKey().getSymbol())
				{
					case ">":
						SingleColumnValueFilter singleColumnValueFilterG = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.GREATER, Bytes.toBytes(entry.getKey().getValue()));	
						singleColumnValueFilterG.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterG);
						break;
					case "<":
						SingleColumnValueFilter singleColumnValueFilterL = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.LESS, Bytes.toBytes(entry.getKey().getValue()));	
						singleColumnValueFilterL.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterL);
						break;
					case "=":
						SingleColumnValueFilter singleColumnValueFilterE = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.EQUAL, Bytes.toBytes(entry.getKey().getValue()));	
						singleColumnValueFilterE.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterE);
						break;
					case ">=":
						SingleColumnValueFilter singleColumnValueFilterGOE = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(entry.getKey().getValue()));	
						singleColumnValueFilterGOE.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterGOE);
						break;
					case "<=":
						SingleColumnValueFilter singleColumnValueFilterLOE = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.LESS_OR_EQUAL, Bytes.toBytes(entry.getKey().getValue()));	
						singleColumnValueFilterLOE.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterLOE);
						break;
					case "!=":
						SingleColumnValueFilter singleColumnValueFilterNE = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
								Bytes.toBytes(column[1]), CompareOp.NOT_EQUAL, Bytes.toBytes(entry.getKey().getValue()));	
						singleColumnValueFilterNE.setFilterIfMissing(true);
						listFilter.add(singleColumnValueFilterNE);
						break;
				}
			}
		}
		return listFilter;
	}*/

	
}
