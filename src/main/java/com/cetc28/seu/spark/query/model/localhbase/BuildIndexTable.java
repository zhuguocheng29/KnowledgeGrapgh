package com.cetc28.seu.spark.query.model.localhbase;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetc28.seu.hbase.HbaseTool;

public class BuildIndexTable {
	private HbaseTool hbaseTool;
	private static String THEME_NAME;
	private static Logger log;
	private static String indexTableName;
	private HTableInterface putIndex = null;

	public BuildIndexTable(String theme_name, String indexTableName) {
		BuildIndexTable.setLog(LoggerFactory.getLogger(this.getClass()));
		hbaseTool = HbaseTool.getInstance();
		BuildIndexTable.THEME_NAME = theme_name;
		BuildIndexTable.indexTableName = indexTableName;
	}
	
	//扫描原表一行，添加一行数据到index
	public void buildTable() throws IOException
	{
//		if(!HbaseTool.getAdmin().tableExists(indexTableName))
//		{
//			hbaseTool.createIndexTable(indexTableName);
//		}
		this.putIndex = hbaseTool.getIndexTable(indexTableName);
		Scan scan = new Scan();
		HTableInterface hTable = hbaseTool.getTable(BuildIndexTable.THEME_NAME);
		ResultScanner resultScanner = hTable.getScanner(scan);			
		for(Result result : resultScanner)
		{
			Map<byte[],byte[]> map = new HashMap<byte[],byte[]>();
			//扫描一行数据
			for(Cell cell : result.rawCells())
			{
				map.put(CellUtil.cloneValue(cell), result.getRow());
			}
			//插入扫描行的数据
			putData(map);
		}
		resultScanner.close();
		putIndex.close();
		hbaseTool.getTable(BuildIndexTable.THEME_NAME).close();
		System.out.println("build index table successful");
		
	}
	
	public void putData(Map<byte[],byte[]> map) throws IOException
	{
		for(Entry<byte[],byte[]> entry : map.entrySet())
		{	
			Get get = new Get(entry.getKey());
			Result result = putIndex.get(get);
			if(result == null || result.size() == 0)
			{
				//若无返回值，则直接插入
				Put put = new Put(entry.getKey());
				put.add(Bytes.toBytes("Row"), Bytes.toBytes(1+""), entry.getValue());
				putIndex.put(put);
			}
			else
			{	
				//有返回值，说明有值重复，获得当前服务器的行数，然后+1
				Put put = new Put(entry.getKey());
				int column = result.listCells().size()+1;
				put.add(Bytes.toBytes("Row"), Bytes.toBytes(column+""), entry.getValue());	
				putIndex.put(put);
			}
			
		}
		//由于设置缓冲池，但是是从服务器获取列数，所以必须每put一次提交一次
		putIndex.flushCommits();
		
	}
	

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		BuildIndexTable.log = log;
	}
	
	
	
}
