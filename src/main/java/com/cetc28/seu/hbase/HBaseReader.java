
package  com.cetc28.seu.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.Map.Entry;




import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;

import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;

import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetc28.seu.hbase.IndexTable.SearchIndexTable;
import com.cetc28.seu.loading.theme.model.ClassRelation;
import com.cetc28.seu.query.struct.Joiner;
import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.query.struct.QueryPlan;
import com.cetc28.seu.loading.theme.model.EntityInfo;

/**
 * a class implements CRUD op for hbase.
 * @author ttf
 * TODO 修改变量名
 */
public class HBaseReader {

	private HbaseTool hbaseTool = HbaseTool.getInstance();
	private static String THEME_NAME;
	private static Logger log;
	private static String indexTableName;
	//private static String indexTableName;
	/**
	 * Description: query conditions that joiner info included
	 * @param condition  the condition for base property.
	 * @param join  joiner info between queries
	 * @return the results 
	 * @throws IOException 
	 */
	
	public HBaseReader(String theme,String indexTableName){
		HBaseReader.log=LoggerFactory.getLogger(this.getClass());
		HBaseReader.THEME_NAME = theme;
		HBaseReader.indexTableName = indexTableName;
	}
	
	
	public HBaseReader() {
		super();
		HBaseReader.log=LoggerFactory.getLogger(this.getClass());
		HBaseReader.THEME_NAME = Configution.instanceTableName;
		HBaseReader.indexTableName = Configution.indexTableName;
	}



	public HbaseTool getHbaseTool() {
		return hbaseTool;
	}
	
	//判断是否有表
	public boolean tablesExists(String tableName) throws IOException
	{
		if(HbaseTool.getAdmin().tableExists(tableName))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//根据主题名和实体名称查询，返回EntityInfo
	public List<EntityInfo> getRelatedEntity(String namedEntityName, List<String> subjectNames) throws IOException
	{
		SearchIndexTable searchIndexTable = new SearchIndexTable(THEME_NAME,indexTableName);
		return searchIndexTable.searchIndexTable(namedEntityName,subjectNames);
	}
	

	
	public List<Map<String, String>> getSingleIntanceQuery(QueryCondition condition, Joiner join) throws IOException {

		HTableInterface htable = hbaseTool.getTable(THEME_NAME);
		List<Filter> ls = new ArrayList<>();
		Map<String, String> attributeMap = condition.getConditions();
		if (!attributeMap.isEmpty())
			for (Map.Entry<String, String> entry : attributeMap.entrySet()) {

				SingleColumnValueFilter columnValueFilter = new SingleColumnValueFilter("course".getBytes(),
						entry.getKey().getBytes(), CompareOp.EQUAL, entry.getValue().getBytes());
				ls.add(columnValueFilter);
			}
		/*
		 * if (!condition.getObjectConditions().isEmpty()) for
		 * (Map.Entry<String, String> entry :
		 * condition.getObjectConditions().entrySet()) {
		 * 
		 * SingleColumnValueFilter columnValueFilter = new
		 * SingleColumnValueFilter("objects".getBytes(),
		 * entry.getKey().getBytes(), CompareOp.EQUAL,
		 * entry.getValue().getBytes()); ls.add(columnValueFilter); }
		 */

		FilterList fl = new FilterList(ls);
		Scan scan = new Scan();
		scan.setFilter(fl);
		ResultScanner rs = null;
		try {
			rs = htable.getScanner(scan);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				htable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		List<Map<String, String>> answerList = new ArrayList<Map<String, String>>();
		List<String> answers = condition.getAnswer();
		String predict = null;
		if (join != null)
			predict = join.getPredict();

		for (Result r : rs) {

			Map<String, String> map = new HashMap<>();
			map.put("rowkey", Bytes.toString(r.getRow()));
			byte[] temp = null;
			if (predict != null) {
				if ((temp = r.getValue("objects".getBytes(), predict.getBytes())) != null) {
					map.put("predict", Bytes.toString(temp));
				} else {
					continue;
				}
			}
			for (String s : answers) {
				byte[] value = r.getValue("atrributes".getBytes(), s.getBytes());
				if (value != null) {
					// System.out.println(Bytes.toString(value));
					map.put(s, Bytes.toString(value));

				}

			}
			answerList.add(map);
		}

		return answerList;

	}

	/**
	 * Description: query by a query plan. The plan describle the task for query.
	 * @param queryPlan
	 * @return the results.
	 * @throws IOException 
	 */
	public List<Map<String, String>> getQueryByPlan(QueryPlan queryPlan) {

		QueryCondition subQueryCondition = queryPlan.getSubFilterQuery();
		Joiner joiner = queryPlan.getJoinerQuery();
		List<Map<String, String>> subAnswers = new ArrayList<>();

		try {
			subAnswers = getSingleIntanceQuery(subQueryCondition, joiner);
			if (joiner == null)
				return subAnswers;

			QueryCondition objectCondition = queryPlan.getObjectFilterQuer();

			List<Map<String, String>> answers = getSingleIntanceQuery(objectCondition, null);

			List<Map<String, String>> results = new ArrayList<>();
			String predict = joiner.getPredict();
			for (Map<String, String> m : subAnswers) {

				for (Map<String, String> map : answers) {

					if (!m.get(predict).equals(map.get("rowkey")))
						continue;

					Map<String, String> r = new HashMap<>();
					r.putAll(m);
					for (Map.Entry<String, String> entry : map.entrySet()) {
						r.put(entry.getKey(), entry.getValue());
					}

					results.add(r);
				}
			}
			return results;
		} catch (IOException e) {
			log.warn(e.getMessage());
		}
		return null;
	}
	

	
	
	//transmit classRelation class
	public List<ClassRelation> transmitClassRelation() throws IOException
	{
		String attributes = "attributes";
		String objects = "objects";
		String array_objects = "array_objects";
		HTableInterface htable = hbaseTool.getTable(THEME_NAME);

    	List<ClassRelation> RelationList = new ArrayList<>();
    	Scan scan1 = new Scan();
    	ResultScanner rs = htable.getScanner(scan1);
    	
    	for (Result result: rs){
    		ClassRelation relation = new ClassRelation();
    		

    		//获取className 类型名
    		String className = Bytes.toString(result.getValue(Bytes.toBytes("attributes"), Bytes.toBytes("className")));
    		relation.setClassName(className);


    		
    		//get object
    		NavigableMap<byte[], byte[]> objectMap =  result.getFamilyMap(Bytes.toBytes(objects));

    		Set<Entry<byte[], byte[]>> objectSet = objectMap.entrySet();
    		HashMap<String,String> hashMap1 = new HashMap<String,String>();
    		for(Entry<byte[],byte[]> extractObject :objectSet)
    		{
    			String objectKey = Bytes.toString(extractObject.getKey());
    			String objectValue = Bytes.toString(extractObject.getValue());
        		hashMap1.put(objectKey, objectValue);
    		}  		
    		relation.setObject(hashMap1);
    		

    		//get ArrayObject 
    		NavigableMap<byte[], byte[]> ArrayObjectMap =  result.getFamilyMap(Bytes.toBytes(array_objects));

    		Set<Entry<byte[], byte[]>> ArrayObjectSet = ArrayObjectMap.entrySet();
    		HashMap<String,String> hashMap2 = new HashMap<String,String>();
    		for(Entry<byte[],byte[]> extractObject :ArrayObjectSet)
    		{
    			String ArrayObjectKey = Bytes.toString(extractObject.getKey());
    			String ArrayObjectValue = Bytes.toString(extractObject.getValue());
        		hashMap2.put(ArrayObjectKey, ArrayObjectValue);
    		} 
    		relation.setArrayObject(hashMap2);;
    		

    		
    		//get parent 
    		String parent = Bytes.toString(result.getValue(Bytes.toBytes(attributes), Bytes.toBytes("parent")));
    		relation.setParent(parent);
    		
    		//get baseProperty 
    		NavigableMap<byte[], byte[]> basePropertyMap =  result.getFamilyMap(Bytes.toBytes(attributes));

    		Set<Entry<byte[], byte[]>> basePropertySet = basePropertyMap.entrySet();
    		HashMap<String,String> hashMap3 = new HashMap<String,String>();
    		for(Entry<byte[],byte[]> extractObject : basePropertySet)
    		{
    			
    			String basePropertyKey = Bytes.toString(extractObject.getKey());
    			String basePropertyValue = Bytes.toString(extractObject.getValue());
        		
    			if(!basePropertyKey.equals("parent")&& !basePropertyKey.equals("className")){
    				hashMap3.put(basePropertyKey, basePropertyValue);
    			}
    		} 
    		relation.setBaseProperty(hashMap3);

   
    		//get rowkey 
    		int rowKey = Bytes.toInt(result.getRow());
    		relation.setId(rowKey);
    		
    		//get themeName
    		String themeName = Bytes.toString(result.getValue(Bytes.toBytes(attributes), Bytes.toBytes("themeName")));

    		relation.setThemeName(themeName);
    		
    		RelationList.add(relation);
    		
    	}
    	return RelationList;
	}
	

}
