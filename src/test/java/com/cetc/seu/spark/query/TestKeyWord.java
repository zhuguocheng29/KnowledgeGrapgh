package com.cetc.seu.spark.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.HTableInterface;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.loading.theme.model.EntityInfo;
import com.cetc28.seu.spark.query.model.coprocessor.SearchKeyWordByNameEntity;

public class TestKeyWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tableName = Configution.instanceTableName;
    	HTableInterface hTable = null;
    	try {
			 hTable = HbaseTool.getInstance().getTable(tableName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			if(!HbaseTool.getAdmin().tableExists(tableName)){
				String[] families = {"objects","attributes","array_objects","index"};
				HbaseTool.createTable(tableName,families);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//添加小部分数据进行试验
//		try {
//			HbaseTool.getInstance().putTable();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		HbaseTool.getInstance().buildIndex();
		Long start = System.currentTimeMillis();
		SearchKeyWordByNameEntity si = new SearchKeyWordByNameEntity();
		String nameEntity = "Jack";
		List<String> subjectNames = new ArrayList<String>();
		subjectNames.add("judge");
		List<EntityInfo> list = si.search(nameEntity, subjectNames);
		Long end = System.currentTimeMillis();
		System.out.println("pay time: " + (end - start));
		for(EntityInfo en:list){
			System.out.println(en.getNm());
			System.out.println(en.getDetails());
		}
	}

}
