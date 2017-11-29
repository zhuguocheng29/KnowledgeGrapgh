package com.cetc28.seu.reasoner.output;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.HbaseTool;

public class URICodeWriter {
	private String URICodeTableName;
	private String[] families;
	private HbaseTool hbaseTool = HbaseTool.getInstance();
	private HBaseAdmin hBaseAdmin ;
	private HTableInterface htable;
	public URICodeWriter(String tableName){
		this.URICodeTableName=tableName;
		try {
			htable=hbaseTool.getTable(URICodeTableName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hBaseAdmin = HbaseTool.getAdmin();
	}
	
	public String getURICodeTableName() {
		return URICodeTableName;
	}

	public void setURICodeTableName(String uRICodeTableName) {
		URICodeTableName = uRICodeTableName;
	}
	
	public void writer(URICode uricode){
		HashMap<String,String> codes=uricode.getCodeTable();
		try {
			if (!hBaseAdmin.tableExists(URICodeTableName)) {
				System.out.println("the table is not exist ! ");
				HbaseTool.createTable(URICodeTableName, families);
			}	
			for(Entry<String, String> code  : codes.entrySet()){
				Put put = new Put(Bytes.toBytes(code.getKey()));
				put.add("value".getBytes(), "uri".getBytes(), Bytes.toBytes(code.getValue()));
				htable.put(put);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				htable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
