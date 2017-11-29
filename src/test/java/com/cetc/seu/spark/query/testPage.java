package com.cetc.seu.spark.query;

import java.io.IOException;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;

public class testPage {

	public static void main(String[] args) {
		String tableName = Configution.instanceTableName;
    	try {
			if(!HbaseTool.getAdmin().tableExists(tableName)){
				String[] families = {"objects","attributes","array_objects","index"};
				HbaseTool.createTable(tableName,families);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	HTableInterface hTable = null;
    	try {
			 hTable = HbaseTool.getInstance().getTable(tableName);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Scan scan = new Scan();
    	PageFilter pf = new PageFilter(5);
    	scan.setFilter(pf);
    	try {
			ResultScanner rs = hTable.getScanner(scan);
			for(Result r : rs){
				System.out.println(Bytes.toString(r.getRow()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
