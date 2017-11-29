package com.cetc28.seu.hbase.IndexTable;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
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
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetc28.seu.hbase.HbaseTool;

public class BuildIndexTable {
	private HbaseTool hbaseTool = HbaseTool.getInstance();
	private static String THEME_NAME;
	private static Logger log;
	private static String indexTableName;
	private HTableInterface putIndex = null;
	private Map<String, Integer> countColumn=null;
/*	public HbaseTool getHbaseTool() {
		return hbaseTool;
	}*/

	public BuildIndexTable(String theme_name, String indexTableName) {
		BuildIndexTable.setLog(LoggerFactory.getLogger(this.getClass()));
		BuildIndexTable.THEME_NAME = theme_name;
		BuildIndexTable.indexTableName = indexTableName;
	}
	
	//扫描原表一行，添加一行数据到index
	public void buildTable(List<String> subjectNames) throws IOException
	{
		if(!HbaseTool.getAdmin().tableExists(indexTableName))
		{
			hbaseTool.createIndexTable(indexTableName);
		}
		this.putIndex = hbaseTool.getIndexTable(indexTableName);
		for(String theme : subjectNames)
		{
			Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new SubstringComparator(theme));
			Scan scan = new Scan();
			scan.setFilter(filter);
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
				//System.out.println("countColumn: " + countColumn );
			}

		}
		putIndex.close();
		hbaseTool.getTable(BuildIndexTable.THEME_NAME).close();
		
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
	
	
	
/*
 * test
 */
		public void buildTable1(List<String> subjectNames) throws IOException
		{
			if(!HbaseTool.getAdmin().tableExists(indexTableName))
			{
				hbaseTool.createIndexTable(indexTableName);
			}
			this.putIndex = hbaseTool.getIndexTable(indexTableName);
			for(String theme : subjectNames)
			{
				Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new SubstringComparator(theme));
				Scan scan = new Scan();
				scan.setFilter(filter);
				HTableInterface hTable = hbaseTool.getTable(BuildIndexTable.THEME_NAME);
				ResultScanner resultScanner = hTable.getScanner(scan);
				
				this.countColumn = new HashMap<String,Integer>();
				
				for(Result result : resultScanner)
				{
					Map<String,String> map = new HashMap<String,String>();
					//扫描一行数据
					for(Cell cell : result.rawCells())
					{
						map.put(Bytes.toString(CellUtil.cloneValue(cell)),Bytes.toString(result.getRow()));
					}
					//插入扫描行的数据
					long start = System.currentTimeMillis();
					putData1(map);
					long end = System.currentTimeMillis();
					System.out.println("time:"+(end-start));
					//System.out.println("countColumn: " + countColumn );
					putIndex.flushCommits();
				}
				
			}
			putIndex.close();
			hbaseTool.getTable(BuildIndexTable.THEME_NAME).close();
			
		}
	
	public void putData1(Map<String,String> map) throws IOException
	{
		System.out.println("countColumn: " +this.countColumn);
		for(Entry<String,String> entry : map.entrySet())
		{	
			Get get = new Get(Bytes.toBytes(entry.getKey()));
			Result result = putIndex.get(get);
			
			//System.out.println("result: " + result.size());
			if(result == null || result.size() == 0)
			{
				int i = 1;
				countColumn.put(entry.getKey(),i);
				//若无返回值，则直接插入
				Put put = new Put(Bytes.toBytes(entry.getKey()));
				put.add(Bytes.toBytes("Row"), Bytes.toBytes(1+""), Bytes.toBytes(entry.getValue()));
				putIndex.put(put);
			}
			else
			{
				if(countColumn.containsKey(entry.getKey()))
				{
					int i = (int)countColumn.get(entry.getKey());
					i++;
					countColumn.put(entry.getKey(), i);
					//有返回值，说明有值重复，获得当前服务器的行数，然后+1
					Put put = new Put(Bytes.toBytes(entry.getKey()));
					//int column = result.listCells().size()+1;
					put.add(Bytes.toBytes("Row"), Bytes.toBytes(i+""), Bytes.toBytes(entry.getValue()));	
					putIndex.put(put);
				}
				
			}
			
		}
		
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		BuildIndexTable.log = log;
	}
	
	
	
}
