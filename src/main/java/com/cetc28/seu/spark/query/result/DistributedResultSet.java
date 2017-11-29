package com.cetc28.seu.spark.query.result;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;



import scala.Tuple2;


public class DistributedResultSet extends ResultSet{

	private static final long serialVersionUID = 6476846857571193551L;
	
	private JavaPairRDD<String, Result> resultRDD;
	
	public JavaPairRDD<String, Result> getResultRDD() {
		return resultRDD;
	}
	public void setResultRDD(JavaPairRDD<String, Result> resultRDD) {
		this.resultRDD = resultRDD;
	}
	
	public void show(int num, HashMap<String, String> cols){
		
		JavaRDD< Map<String,String>> results=resultRDD.map(new Function<Tuple2<String,Result>, Map<String,String>>() {
			
			private static final long serialVersionUID = -7232265844466542327L;
			
			@Override
			public Map<String,String> call(Tuple2<String, Result> v1) throws Exception {
				Map<String,String> colValue=new HashMap<>();
				
				for(String col : cols.keySet()){
					colValue.put(col, Bytes.toString(v1._2.getValue(Bytes.toBytes("attributes"),Bytes.toBytes(col))));
				}
				return colValue;
			}
		});
		List<Map<String, String>> resultStr=results.take(num);
		for(Map<String, String> str : resultStr){
			for( Entry<String, String> ele : str.entrySet()){
				//System.out.println(ele.getKey());
				System.out.println(cols.get(ele.getKey())+" : "+ele.getValue());
			}
			
		}
	}
	
	@Override
	public void show(HashMap<String, String> cols) {
		// TODO 利用show(int)实现分页版本的显示
		
	}
	@Override
	public void show(int num) {
		// TODO Auto-generated method stub
		
	}
	
}
