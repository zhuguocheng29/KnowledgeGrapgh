package com.cetc.seu.spark.query;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;

public class testDim {

	public static void main(String[] args) {
		String tableName = Configution.instanceTableName;
		HashSet<String> hashSet = new HashSet<String>();
		
    	HTableInterface hTable = null;
    	try {
			 hTable = HbaseTool.getInstance().getTable(tableName);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes("attributes"));
		scan.addFamily(Bytes.toBytes("objects"));
		scan.addFamily(Bytes.toBytes("array_objects"));
		SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("attributes"), Bytes.toBytes("cl"), CompareFilter.CompareOp.EQUAL, new SubstringComparator("jas"));
		filter.setFilterIfMissing(true);
		scan.setFilter(filter);
		
		ResultScanner rs = null;
		try {
			rs = hTable.getScanner(scan);
			for(Result res : rs){
				System.out.println(Bytes.toString(res.getRow()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

}
