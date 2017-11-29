package com.cetc28.seu.hbase.concurrence;

import java.io.IOException;

import java.util.List;


import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;


import com.cetc28.seu.hbase.HbaseTool;


public abstract class TaskGenerate {
	private List<String> themeNames;
	private HConnection connection;
	//private HbaseTool hbaseTool = HbaseTool.getInstance();
	//private final static String TABLE_NAME = "instacesV2";
	public TaskGenerate(List<String> themeNames) {
		super();
		try {
			setConnection(HConnectionManager.createConnection(HbaseTool.getBaseConfiguration()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setThemeNames(themeNames);
	}
	
	
	public TaskGenerate() {
		
	}
	
	
	public abstract List<WriterTask> generate() throws IOException;


	public List<String> getThemeNames() {
		return themeNames;
	}


	public void setThemeNames(List<String> themeNames) {
		this.themeNames = themeNames;
	}


	public HConnection getConnection() {
		return connection;
	}


	public void setConnection(HConnection connection) {
		this.connection = connection;
	}


	
	
	
	
	

}
