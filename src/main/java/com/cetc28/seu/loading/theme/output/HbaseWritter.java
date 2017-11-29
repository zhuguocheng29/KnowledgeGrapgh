package com.cetc28.seu.loading.theme.output;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.loading.theme.model.ClassRelation;
import com.cetc28.seu.loading.theme.model.Property;

public class HbaseWritter implements ParserWriter {
	private String[] families={"attributes","objects","array_objects"};
	private HbaseTool hbaseTool = HbaseTool.getInstance();
	//private static final String SCHEMA_NAME = "schema";
	private HTableInterface htable = null;
	private static  String THEME_NAME = "instancesV2";
	//private static  String SCHEMA_NAME = "schema";
	public HbaseWritter () throws IOException{
		this.htable=hbaseTool.getTable(THEME_NAME);
	}
	public HbaseWritter (String tableName) throws IOException{
		this.htable=hbaseTool.getTable(tableName);
	}
	/**
	 * Descripition : writer instances to hbase
	 * @param property
	 */

	
	public void writer(Property property) {

		HBaseAdmin hBaseAdmin = HbaseTool.getAdmin();
		
		try {
			if (!hBaseAdmin.tableExists(THEME_NAME)) {
				System.out.println("the table is not exist ! ");
				HbaseTool.createTable(THEME_NAME, families);
			}	

			Put put = new Put(Bytes.toBytes(property.getOid()));
			put.add("attributes".getBytes(), "parent".getBytes(), Bytes.toBytes(property.getParent()));
			for(String family : families){
				for (Map.Entry<String, String> entry : property.getProperty().get(family).entrySet()) {
					put.add(family.getBytes(), entry.getKey().getBytes(), entry.getValue().getBytes());
				}
			}

			htable.put(put);
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
	
	/*//带有版本号的添加数据
	public void writer(Property property) {

		HBaseAdmin hBaseAdmin = HbaseTool.getAdmin();
		
		try {
			if (!hBaseAdmin.tableExists(THEME_NAME)) {
				System.out.println("the table is not exist ! ");
				HbaseTool.createTable(THEME_NAME, families);
			}	
			//添加时间戳
			long time = System.nanoTime();
			Put put = new Put(Bytes.toBytes(property.getOid()));
			put.add(Bytes.toBytes("attributes"), Bytes.toBytes("parent"),Bytes.toBytes(property.getParent()));
			for(String family : families){
				for (Map.Entry<String, String> entry : property.getProperty().get(family).entrySet()) {
					if(family.equals("attributes"))
					{
						put.add(Bytes.toBytes(family), Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
					}
					else
					{
						put.add(Bytes.toBytes(family), Bytes.toBytes(entry.getKey()), time,Bytes.toBytes(entry.getValue()));
					}
				}
			}
			
			htable.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				htable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}*/
	
	/**
	 * write the schema info to hbase.
	 * cr: a object to describe the schema.
	 */
	public void writer(ClassRelation cr) {

		try {

			Put put = new Put(Bytes.toBytes(cr.getId()));
		
			put.add("attributes".getBytes(), "parent".getBytes(), Bytes.toBytes(cr.getParent()));
			for (Map.Entry<String, String> entry : cr.getBaseProperty().entrySet()) {
				put.add("attributes".getBytes(), entry.getKey().getBytes(), entry.getValue().getBytes());
			}

			for (Map.Entry<String, String> entry : cr.getObject().entrySet()) {
				put.add("objects".getBytes(), entry.getKey().getBytes(), Bytes.toBytes(entry.getValue()));
			}

			for (Map.Entry<String, String> entry : cr.getArrayObject().entrySet()) {
				put.add("array_objects".getBytes(), entry.getKey().getBytes(), Bytes.toBytes(entry.getValue()));
			}		
			
			htable.put(put);
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
	
	/*//带有时间戳版本的写入schema
	public void writer(ClassRelation cr) {

		try {
			
			Put put = new Put(Bytes.toBytes(cr.getId()));
			long time = System.nanoTime();
			put.add(Bytes.toBytes("attributes"), Bytes.toBytes("parent"),Bytes.toBytes(cr.getParent()));

			for (Map.Entry<String, String> entry : cr.getBaseProperty().entrySet()) {
				put.add(Bytes.toBytes("attributes"),Bytes.toBytes(entry.getKey()),Bytes.toBytes(entry.getValue()));
			}

			for (Map.Entry<String, String> entry : cr.getObject().entrySet()) {
				put.add(Bytes.toBytes("objects"),Bytes.toBytes(entry.getKey()),time,Bytes.toBytes(entry.getValue()));
			}

			for (Map.Entry<String, String> entry : cr.getArrayObject().entrySet()) {
				put.add(Bytes.toBytes("array_objects"),Bytes.toBytes(entry.getKey()),time,Bytes.toBytes(entry.getValue()));
			}
			htable.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				htable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}*/
	

	public void flush()  {
		try {
			htable.flushCommits();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(Property prop) {
		Put put = new Put(Bytes.toBytes(prop.getOid()));
		put.add(Bytes.toBytes("attributes"), Bytes.toBytes("parent"), Bytes.toBytes(prop.getParent()));
		
		//一般只有objects和array_objects不同
		String[] partFamilies = {"objects","array_objects"};
		for(String family : partFamilies){
			for (Map.Entry<String, String> entry : prop.getProperty().get(family).entrySet()) {
				put.add(Bytes.toBytes(family), Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
			}
		}
/*		//添加时间戳
		put.add(Bytes.toBytes("attributes"), Bytes.toBytes("parent"), customTimeStamp(),Bytes.toBytes(prop.getParent()));
		for(String family : partFamilies){
			for (Map.Entry<String, String> entry : prop.getProperty().get(family).entrySet()) {
				put.add(Bytes.toBytes(family), Bytes.toBytes(entry.getKey()), customTimeStamp(),Bytes.toBytes(entry.getValue()));
			}
		}*/
		try {
			htable.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			htable.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
