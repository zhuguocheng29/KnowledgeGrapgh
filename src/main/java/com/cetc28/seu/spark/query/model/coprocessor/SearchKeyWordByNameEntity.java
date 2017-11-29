package com.cetc28.seu.spark.query.model.coprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.loading.theme.model.EntityInfo;

/**
 * 搜索关键字+主题,时间慢，怎么解决15s此方法已包括模糊搜索
 * @author Think
 *
 */
public class SearchKeyWordByNameEntity {
	public String tableName = Configution.instanceTableName;
	public static void main(String[] args){
		SearchKeyWordByNameEntity ske = new SearchKeyWordByNameEntity();
		List<String> subjectNames = new ArrayList<>();
		subjectNames.add("test");

		System.out.println(ske.search("1", subjectNames));
		
	}
	/**
	 * 搜索关键字+主题
	 * @param namedEntityName
	 * @param subjectNames
	 * @return
	 */
	public List<EntityInfo> search(String namedEntityName, List<String> subjectNames){
		Long start = System.currentTimeMillis();
		List<EntityInfo> list = new ArrayList<EntityInfo>();
		List<Row> batch = new ArrayList<Row>();
		try {
			HbaseTool.getInstance().getTable(tableName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<String> resultList = new ArrayList<String>();
		resultList = HbaseTool.getInstance().startSearchIndex("", "", namedEntityName);
		if(resultList.size() == 0){
			resultList = HbaseTool.getInstance().startSearch("", "", namedEntityName);
		}
		//从协处理器中查出值
		Map<String,String> hashMap = new HashMap<String,String>();
		
		if(subjectNames == null || subjectNames.size()== 0 ||subjectNames.isEmpty()){
			for(String result : resultList){
				//result格式0000::topic:id
				Get get = new Get(Bytes.toBytes(result));
				batch.add(get);
			}
		}else{
			for(String subjectName : subjectNames){
				hashMap.put(subjectName, subjectName);
			}
			
			for(String result : resultList){
				//result格式0000::topic:id
				String[] topic = result.split(":");
				if(hashMap.containsKey(topic[1])){
					Get get = new Get(Bytes.toBytes(result));
					batch.add(get);
				}
			}	
		}
		
		//批处理获得值
		Object[] results = new Object[batch.size()];
		try {
			HbaseTool.getInstance().getTable(tableName).batch(batch, results);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		for(Object object : results){
			Result result = (Result)object;
			String rowKey = Bytes.toString(result.getRow());
			String[] topic = rowKey.split(":");
			Map<String,String> map = new HashMap<String,String>();
			EntityInfo entityInfo = new EntityInfo();
			entityInfo.setSubjectName(topic[1]);
			entityInfo.setNm(rowKey);
			for(Cell cell : result.rawCells())
			{
				map.put(Bytes.toString(CellUtil.cloneFamily(cell))+":"+Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
			}
			entityInfo.setDetails(map);
			list.add(entityInfo);
		}
		
		Long stop = System.currentTimeMillis();
		System.out.println(stop-start);
		return list;
	}
}
