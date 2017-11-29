
package com.hbase.test;

import java.io.IOException;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;

import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.hbase.concurrence.WriterService;


public class ImplementWriterService implements WriterService{
	HTableInterface hTable;
	@Override
	public void serve() {
        System.out.println( "Hello World!" );
    	final String tableName = "test11";
    	HbaseTool hBaseTool = HbaseTool.getInstance();
    	
    	try {
    		HbaseTool.createTable(tableName, null);
			hTable = hBaseTool.getTable(tableName);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	long start = System.currentTimeMillis();
    	System.out.println("startTime: " + start);
    	putTable();
    	long end = System.currentTimeMillis();
    	System.out.println("endTime: " + end);
    	System.out.println("pay time: " + (end-start));
		
	}

	
	
	public void putTable()
	{
		System.out.println("successful read");

		for(int i=1; i<=10000;i++)
		{
			Put put = new Put(Bytes.toBytes("row-"+i));
			if(i%10 == 0)
			{
				int j = i+1;				
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("name"),Bytes.toBytes("zhu" + i));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("age"),Bytes.toBytes(i));
				put.add(Bytes.toBytes("objects"), Bytes.toBytes("child"),Bytes.toBytes("row-"+j));
				put.add(Bytes.toBytes("objects"), Bytes.toBytes("car"),Bytes.toBytes("Benz"));
			}
			else
			{
				//int j = i+1;
				//Put put = new Put(Bytes.toBytes("row-"+i));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("name"),Bytes.toBytes("zhu" + i));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("age"),Bytes.toBytes(i));
				//put.add(Bytes.toBytes("objects"), Bytes.toBytes("child"),Bytes.toBytes("row-"+j));
				put.add(Bytes.toBytes("objects"), Bytes.toBytes("car"),Bytes.toBytes("Ferrari"));
			}
			
			try {
				hTable.put(put);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			hTable.flushCommits();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("successful insert");
		//better use HTablePool,Batch operation
	}
}
