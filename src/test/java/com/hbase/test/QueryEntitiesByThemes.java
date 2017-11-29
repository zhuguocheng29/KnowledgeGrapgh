package com.hbase.test;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import com.cetc28.seu.hbase.IndexTable.SparkSearchIndexTable;

import junit.framework.TestCase;

public class QueryEntitiesByThemes extends TestCase implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2388752444648443692L;

	public void testQuery() throws IOException
	{
		final String indexTableName = "index";
		final String tableName = "experiment";
			
		String namedEntityName = "Ferrari";
		List<String> subjectNames = new ArrayList<String>();
		subjectNames.add("Good car");
		subjectNames.add("Bad car");
		subjectNames.add("好车");
		SparkSearchIndexTable sparkSearchIndexTable = new SparkSearchIndexTable(tableName,indexTableName);
		sparkSearchIndexTable.searchIndexTable(namedEntityName, subjectNames);
	}

}
