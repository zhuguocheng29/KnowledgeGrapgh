package com.cetc28.seu.spark.query.model.indexhbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Row;

import org.apache.hadoop.hbase.util.Bytes;


import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.spark.query.result.LocalResultSet;
import com.cetc28.seu.sparql.FilterClause;



/**
 * 主键搜索节点
 * 
 * @author Think
 *
 */
public class SimpleIndexQueryNode extends LeafLocalNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1288477445544636704L;
	public static List<FilterClause> filterClause;
	private String originalTableName = "HbaseTest1000000";
	private String indexTableName = "indexHbaseTest1000000";
	private HbaseTool hbaseTool = HbaseTool.getInstance();

	public SimpleIndexQueryNode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SimpleIndexQueryNode(int id, int lchild, int rchild, int parent, Term term, QueryCondition attributes) {
		super(id, lchild, rchild, parent, term);
		this.setAttributes(attributes);
//		this.originalTableName = originalTableName;
//		this.indexTableName = "index_"+originalTableName;
	}
	 
	// 加入filter函数
	public SimpleIndexQueryNode(int id, int lchild, int rchild, int parent, Term term, QueryCondition attributes,
			List<FilterClause> filterClause) {
		super(id, lchild, rchild, parent, term);
		this.setAttributes(attributes);
		SimpleIndexQueryNode.filterClause = filterClause;
//		this.originalTableName = originalTableName;
//		this.indexTableName = "index_"+originalTableName;
	}
	

	public LocalResultSet query() {
		// // TODO 查询列的设置
		// Map<String, String> attributeMap =
		// this.getAttributes().getConditions();
		// List<Result> listResults = new ArrayList<Result>();
		// LocalResultSet localResult = new LocalResultSet();
		//
		// for(Entry<String, String> entry : attributeMap.entrySet())
		// {
		// try {
		// LocalResultSet resultSet = searchIndexTable(entry.getValue());
		// listResults.addAll(resultSet.getResults());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// localResult.setResults(listResults);
		// return localResult;
		
		
		// TODO 查询列的设置
		Map<String, String> attributeMap = this.getAttributes().getConditions();
		CreateIndexXml cix = new CreateIndexXml();
		String element = null;
		String value = null;
		
		for(Entry<String, String> entry : attributeMap.entrySet())
		{
			if(attributeMap.size() == 1)
			{
				if(cix.parser(entry.getKey()))
				{
					LocalResultSet resultSet = new LocalResultSet();
					try {
						resultSet = searchIndexTable(entry.getKey()+":"+entry.getValue());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return resultSet;
				}
			}
			else
			{
				element = element + entry.getKey()+":";
				value = value + entry.getKey() +":"+entry.getValue()+":";	
			}
		}
		if(cix.parser(element))
		{
			LocalResultSet resultSet = new LocalResultSet();
			try {
				resultSet = searchIndexTable(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultSet;
		}
		return null;
//		for(Entry<String, String> entry : attributeMap.entrySet())
//		{
//			try {
//				LocalResultSet resultSet = searchIndexTable(entry.getValue());
//				list.add(resultSet);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if(list.size() == 0)
//		{
//			return returnLocalResult;
//		}
//		else if(list.size() == 1)
//		{
//			return list.get(0);
//		}
//		else
//		{
//			Set<Result> set = new HashSet<Result>();
//			for(int i = 0; i < list.size(); i++)
//			{
//				for(Result result: list.get(i).getResults())
//				{
//					set.add(result);
//				}
//			}
//			
//			List<Result> resultList = new ArrayList<Result>();
//			for(Iterator<Result> j = set.iterator(); j.hasNext();)
//			{
//				resultList.add(j.next());
//			}
//			returnLocalResult.setResults(resultList);
//			return returnLocalResult;
//		}
	}
	
	
	/**
	 * 从自建索引表中获取列值
	 * @param namedEntityName
	 * @return LocalResultSet
	 * @throws IOException
	 */
	public LocalResultSet searchIndexTable(String namedEntityName) throws IOException {
		if(!HbaseTool.getAdmin().tableExists(indexTableName))
		{
			hbaseTool.createIndexTable(indexTableName);
			BuildIndexTable buildIndexTable = new BuildIndexTable(this.originalTableName,this.indexTableName);
			buildIndexTable.buildTable();
		}
		HTableInterface index = hbaseTool.getIndexTable(indexTableName);
		List<byte[]> list = new ArrayList<byte[]>();
		Get get = new Get(Bytes.toBytes(namedEntityName));
		Result result = index.get(get);
		for (Cell cell : result.rawCells()) {
			list.add(CellUtil.cloneValue(cell));
		}
		try {
			index.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalResultSet resultSet = getSearchResult(list);
		return resultSet;
	}

	/**
	 * 从自建的索引表中获取列值然后在原表中搜索
	 * @param list
	 * @return LocalResultSet
	 * @throws IOException
	 */
	public LocalResultSet getSearchResult(List<byte[]> list) throws IOException {
		HTableInterface original = hbaseTool.getTable(originalTableName);
		LocalResultSet resultSet = new LocalResultSet();
		List<Result> listResult = new ArrayList<Result>();
		//批量处理
		List<Row> batch = new ArrayList<Row>();

		for (byte[] rowKey : list) {
			Get get = new Get(rowKey);
			batch.add(get);
		}
		Object[] results = new Object[batch.size()];
		try {
			original.batch(batch, results);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Object object: results)
		{
			Result result = (Result)object;
			listResult.add(result);
		}
		resultSet.setResults(listResult);
		return resultSet;
	}


}
