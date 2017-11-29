package com.cetc28.seu.spark.query.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;



public class LocalResultSet extends ResultSet{

	private static final long serialVersionUID = 7256652389358789457L;
	private List<Result> results;
	
	//private JavaPairRDD<String, Result> resultRDD = null;
	
	public LocalResultSet(){
		this.results=new ArrayList<>();
	}
	
	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public void show(int num, HashMap<String, String> cols) {
		// TODO Auto-generated method stub
		List<HashMap<String,String>> resultStr=new ArrayList<>();
		for(Result res : results){
			HashMap<String,String> resultCol=new HashMap<>();
			for(String col : cols.keySet()){
				resultCol.put(col, Bytes.toString(res.getValue(Bytes.toBytes("attributes"), Bytes.toBytes(col))));
			}
			resultStr.add(resultCol);
		}
		for(Map<String, String> str : resultStr){
			for( Entry<String, String> ele : str.entrySet()){
				//System.out.println(ele.getKey());
				System.out.println(cols.get(ele.getKey())+" : "+ele.getValue());
			}
			
		}
	}

	@Override
	public void show(HashMap<String, String> cols) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show(int num) {
		// TODO Auto-generated method stub
		for (int i = 0; i < num; i++) {
			if (results.size() == 0) {
				System.out.println("Null");
				break;
			} else if (i < results.size()) {
				Result result = results.get(i);
				String key = Bytes.toString(result.getRow());
				String s = "";
				for (Cell cell : result.rawCells())
//					System.out.println(key + ": " + Bytes.toString(CellUtil.cloneFamily(cell)) + " "
//							+ Bytes.toString(CellUtil.cloneQualifier(cell)) + " "
//							+ Bytes.toString(CellUtil.cloneValue(cell)));
					System.out.println(key.substring(5) +  " "
							+ Bytes.toString(CellUtil.cloneQualifier(cell)) + " "
							+ (s = Bytes.toString(CellUtil.cloneValue(cell)).contains(":")? Bytes.toString(CellUtil.cloneValue(cell)).substring(5) : Bytes.toString(CellUtil.cloneValue(cell))));
				
				System.out.println();
			}
		}
	}


	

}
