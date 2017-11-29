
package com.hbase.test;

import java.io.IOException;
import java.util.ArrayList;


import java.util.List;
import java.util.Scanner;





import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HBaseReader;
import com.cetc28.seu.hbase.HbaseTool;




//import com.cetc28.seu.hbase.ImplementWriterService;
import com.cetc28.seu.hbase.IndexTable.BuildIndexTable;
import com.cetc28.seu.hbase.IndexTable.SparkBuildIndexTable;
import com.cetc28.seu.loading.theme.model.EntityInfo;


import junit.framework.TestCase;

public class ConnectTest extends TestCase{

	public void findJoin(){
		HTableInterface hTable = null;
		try {
			hTable = HbaseTool.getInstance().getTable(Configution.instanceTableName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes("index"));
		Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("attributes:bdnm"));
		scan.setFilter(filter);
		scan.setStartRow(Bytes.toBytes("0000:"));
		scan.setStopRow(Bytes.toBytes("1000:"));
		ResultScanner rs = null;
		try {
			 rs = hTable.getScanner(scan);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Result result : rs){
			System.out.println(Bytes.toString(result.getRow()));
		}
	}
	public void testSpark() throws IOException
	{
/*		HbaseTool hbaseTool = HbaseTool.getInstance();
		for(int i = 1 ;i <= 20;i++)
		{
			if(hbaseTool.getAdmin().tableExists("index"+i))
			{
				hbaseTool.getAdmin().disableTable("index"+i);
				hbaseTool.getAdmin().deleteTable("index"+i);
			}
		}*/
		String tableName = "experiment";
		String indexTableName = "index7";
    	List<String> list = new ArrayList<String>();
		list.add("Bad car");
		list.add("Good car");
		list.add("好车");
		SparkBuildIndexTable sparkBuildIndexTable = new SparkBuildIndexTable(tableName,indexTableName);
		sparkBuildIndexTable.buildTable(list);
		
	}

	public void testIndex() throws IOException
	{
		String tableName = "experiment";
    	final String indexTableName = "index20";
    	//HbaseTool hbaseTool = HbaseTool.getInstance();
    	//HTableInterface hTable = hbaseTool.getTable(tableName);
    	List<String> list = new ArrayList<String>();
		list.add("Bad car");
		list.add("Good car");
		list.add("好车");
		long start = System.currentTimeMillis();
		BuildIndexTable buildIndexTable = new BuildIndexTable(tableName,indexTableName);
		buildIndexTable.buildTable(list);
		long end = System.currentTimeMillis();
		System.out.println("pay time: " + (end-start));
	}

	
/*	public void testMultithread() throws Exception

	{
		WriterService ws = new ImplementWriterService();
		WriterTask wt = new WriterTask(ws);
		List<WriterTask> list = new ArrayList<WriterTask>();
		list.add(wt);
		MultiThreadWriter mtw = new MultiThreadWriter(list);
		mtw.executeTask();

	}*/
   
	public void testQuery() throws Exception
	{
    	
/*    	QueryCondition queryCondition = new QueryCondition();
    	HashMap<String,String> hashMap = new HashMap<String,String>();
    	hashMap.put("Country", "China");
    	queryCondition.setCondition(hashMap);
    	ArrayList<String> arrayList = new ArrayList<String>();
    	arrayList.add("name");
    	arrayList.add("age");
    	arrayList.add("Country");
    	queryCondition.setColumnName(arrayList);
    	
    	Joiner joiner = new Joiner();
    	joiner.setPredicate("child");
    	
    	hBaseTool.getSingleInstance(queryCondition,joiner);*/
    	
  
    	/*QueryPlan queryPlan = new QueryPlan();
    	QueryCondition subjectCondition = new QueryCondition();
    	HashMap<String,String> subjectHashMap = new HashMap<String,String>();
    	subjectHashMap.put("Country", "China");
    	subjectCondition.setCondition(subjectHashMap);   
    	ArrayList<String> arrayList = new ArrayList<String>();
    	arrayList.add("name");
    	arrayList.add("age");
    	arrayList.add("Country");
    	subjectCondition.setColumnName(arrayList);
    	queryPlan.setSubjectCondition(subjectCondition);
    	
    	Joiner subject_joiner = new Joiner();
    	subject_joiner.setPredicate("child");
    	queryPlan.setJoinerQuery(subject_joiner);
    	
    	QueryCondition objectCondition = new QueryCondition();
    	HashMap<String,String> objectMap = new HashMap<String,String>();
    	objectMap.put("Country", "China");
    	objectCondition.setCondition(objectMap);
    	objectCondition.setColumnName(arrayList);
    	queryPlan.setObjectCondition(objectCondition);
    	
    	hBaseTool.getQueryByPlan(queryPlan);

    	hBaseTool.close();
    	System.out.println("-----------");*/
	}
	
    public void testConnect() throws Exception {
    	String tableName = "experiment";
    	final String indexTableName = "index5";
    	HbaseTool hbaseTool = HbaseTool.getInstance();
    	HTableInterface hTable = hbaseTool.getTable(tableName);
/*
    	HTableDescriptor hd = hTable.getTableDescriptor();
    	System.out.println(hd);*/
    	
    	HBaseReader hbaseReader = new HBaseReader(tableName,indexTableName);	    	

    	//
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("namedEntity: ");
    	String namedEntity = scanner.nextLine();
    	//String namedEntity = "Good car:970";
    	System.out.println("Please input themelist:");
    	String themeList = scanner.nextLine();
    	//System.out.println("themeList: " + themeList);
    	String[] reviseTheme = themeList.split(",");
    	
    	List<String> arrayList = new ArrayList<String>();

    	for(String theme:reviseTheme)
    	{
    		System.out.println("theme: " + theme);
    		arrayList.add(theme);
    	}
//    	arrayList.add("Good car");
    	long startTime = System.currentTimeMillis();
    	System.out.println("startTime: " + startTime);
    	List<EntityInfo> entityInfo = hbaseReader.getRelatedEntity(namedEntity,arrayList);
    	System.out.println("entityInfo:" + entityInfo);
    	if(entityInfo.size() == 0)
    	{
			System.out.println("No records");
    	}
    	else
    	{
    		System.out.println("subjectName: " + entityInfo.size());
	    	for(EntityInfo entity:entityInfo)
			{
				System.out.println("map: " + entity.getDetails());
			}
    	}

    	long endTime = System.currentTimeMillis();
    	System.out.println("endTime: " + endTime);
    	System.out.println("pay Time: " + (endTime-startTime));
    	scanner.close();
    	hbaseReader.getHbaseTool().close();
    	hTable.close();
    }
}

