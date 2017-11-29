package com.cetc28.seu.hbase.IndexTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.loading.theme.model.EntityInfo;

public class SearchIndexTable {
	private HbaseTool hbaseTool = HbaseTool.getInstance();
	private static String THEME_NAME;
	private static Logger log;
	private static String indexTableName;
	
	public HbaseTool getHbaseTool() {
		return hbaseTool;
	}

	public SearchIndexTable(String theme_name,String indexTableName) {
		SearchIndexTable.setLog(LoggerFactory.getLogger(this.getClass()));
		SearchIndexTable.THEME_NAME = theme_name;
		SearchIndexTable.indexTableName = indexTableName;
		
	}
	
	
	public List<EntityInfo> searchIndexTable(String namedEntityName,List<String> subjectNames) throws IOException
	{
		HTableInterface index = null;
		try {
			index = hbaseTool.getIndexTable(indexTableName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Filter filter = new RowFilter(CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes(namedEntityName)));
		Scan scan = new Scan();
		scan.setBatch(10);
		
		for(int k = 1; k <= 50; k++)
		{
			scan.addColumn(Bytes.toBytes("Row"), Bytes.toBytes(k+""));
		}
		scan.setFilter(filter);
		ResultScanner resultscanner = null;
		try {
			 resultscanner = index.getScanner(scan);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<byte[]> list = new ArrayList<byte[]>();
		
		for(Result result:resultscanner)
		{
			//System.out.println("result: " + Bytes.toString(result.getRow()));
			for(Cell cell : result.rawCells())
			{
				for(String subjectName : subjectNames)
				{
					if(Bytes.toString(CellUtil.cloneValue(cell)).contains(subjectName))
					{
						list.add(CellUtil.cloneValue(cell));
					}
				}
				
			}
		}	
		try {
			index.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		List<byte[]> list = new ArrayList<byte[]>();

		Get get = new Get(Bytes.toBytes(namedEntityName));
		
		for(int k = 1; k <= 100; k++)
		{
			get.addColumn(Bytes.toBytes("Row"), Bytes.toBytes(k+""));
		}
		Result result = index.get(get);
		for(Cell cell : result.rawCells())
		{
			for(String subjectName : subjectNames)
			{
				if(Bytes.toString(CellUtil.cloneValue(cell)).contains(subjectName))
				{
					list.add(CellUtil.cloneValue(cell));
				}
			}
			
		}
		return getSearchResult(list);
	}
	
	
	public List<EntityInfo> getSearchResult(List<byte[]> list) throws IOException
		{
			List<EntityInfo> listEntity = new ArrayList<EntityInfo>();
			HTableInterface original = hbaseTool.getTable(THEME_NAME);
			for(byte[] rowKey: list)
			{
				Get get = new Get(rowKey);
				Result result = original.get(get);
				
				EntityInfo entityInfo = new EntityInfo();
				Map<String,String> map = new HashMap<String,String>();
				String[] getThemeFromRow = Bytes.toString(result.getRow()).split(":"); 
				String theme = getThemeFromRow[0];
				entityInfo.setSubjectName(theme);
				for(Cell cell : result.rawCells())
				{
					map.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
				}
				entityInfo.setDetails(map);
				listEntity.add(entityInfo);
				
			}
			return listEntity;
		}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		SearchIndexTable.log = log;
	}
		
	
	

}
