package com.cetc.seu.spark.query;

import java.io.IOException;
import java.util.HashSet;
import java.util.NavigableMap;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;

public class getColumnNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tableName = Configution.instanceTableName;
		HashSet<String> hashSet = new HashSet<String>();
		
    	HTableInterface hTable = null;
    	try {
			 hTable = HbaseTool.getInstance().getTable(tableName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Scan scan = new Scan();
    	ResultScanner rs = null;
		try {
			rs = hTable.getScanner(scan);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for(Result result : rs){
    		NavigableMap<byte[], byte[]> nMap = result.getFamilyMap(Bytes.toBytes("attributes"));
    		for(byte[] b : nMap.keySet()){
    			String str = Bytes.toString(b);
    			//System.out.println("attributes:"+str);
    			hashSet.add("attributes:"+str);
    		}
    	}
    	for(String s : hashSet){
    		System.out.println(s);
    	}
	}

}
