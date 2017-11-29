package com.cetc.seu.spark.query;

import java.io.IOException;

import org.apache.hadoop.hbase.client.HTableInterface;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;

public class testPut {

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
    	
    	//添加小部分数据进行试验
		try {
			HbaseTool.getInstance().putTable();
			//测试一个join
			HbaseTool.getInstance().addOneRecord();
			//测试两个Join
			HbaseTool.getInstance().addTwoJoin();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("start build index");
		HbaseTool.getInstance().buildIndex();
		System.out.println("finished");
		//测试模糊搜索
		HbaseTool.getInstance().addDimSearch();
	}

}
