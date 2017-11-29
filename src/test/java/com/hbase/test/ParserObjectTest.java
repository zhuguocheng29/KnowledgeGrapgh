package com.hbase.test;

import java.io.IOException;


import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.HbaseTool;


import junit.framework.TestCase;

public class ParserObjectTest extends TestCase{
	
	public void testParse() throws IOException
	{

/*		//实例化HbaseWritter
		//System.out.println("11111");
		ParserWriter  hbaseWritter = new HbaseWritter();
		ObjectParser objectParser = new ObjectParser(hbaseWritter);
		//模拟数据库中类与类的关系
		//相同的child
		NestedClass nestedClass = new NestedClass();
		nestedClass.setChildName("child");
		nestedClass.setChildAge(1);
		nestedClass.setCountry("China");
		//parent1
		ParentClass parentClass = new ParentClass();
		parentClass.setParentName("parent1");
		parentClass.setParentAge(30);
		parentClass.setChild(nestedClass);
		//parent2
		ParentClass parentClass2 = new ParentClass();
		parentClass2.setParentName("parent2");	
		parentClass2.setParentAge(26);
		parentClass2.setChild(nestedClass);
		
		List<Object> object = new ArrayList<Object>();
		object.add(parentClass);
		object.add(parentClass2);
		//System.out.println("object: " + ((ParentClass)object.get(0)).getParentAge());
		objectParser.extractTreeObj(object, parentClass.getClass(), "instancesV2");
		//System.out.println("type: " + parentClass.getClass().toString());
*/		 
		//测试版本压力1000个
		String[] families={"attributes","objects","array_objects"};
		String THEME_NAME = "test444";
		HbaseTool hbaseTool = HbaseTool.getInstance();
		if(HbaseTool.getAdmin().tableExists(THEME_NAME))
		{
			HbaseTool.getAdmin().disableTable(THEME_NAME);
			HbaseTool.getAdmin().deleteTable(THEME_NAME);
		}
		HbaseTool.createTable(THEME_NAME, families);
		Put put = new Put(Bytes.toBytes("row-1"));
		for(int i=0; i<=1000; i++)
		{
			put.add(Bytes.toBytes("attributes"),Bytes.toBytes("car"),Bytes.toBytes(i+""));
			//put.add(Bytes.toBytes("attributes"),Bytes.toBytes("age"),Bytes.toBytes(i+""));
			hbaseTool.getTable(THEME_NAME).put(put);
			//System.out.println("i: " + i);
			
		}
		hbaseTool.getTable(THEME_NAME).flushCommits();
		hbaseTool.getTable(THEME_NAME).close();

		
	}

}
