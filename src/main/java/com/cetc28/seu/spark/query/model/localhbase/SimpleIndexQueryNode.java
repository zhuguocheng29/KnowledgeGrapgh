package com.cetc28.seu.spark.query.model.localhbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
	public static List<FilterClause<?>> filterClause;
	private String originalTableName = "HbaseTest40000";
	private String indexTableName = "indexHbaseTest40000";
	private HbaseTool hbaseTool = HbaseTool.getInstance();

	public SimpleIndexQueryNode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SimpleIndexQueryNode(int id, int lchild, int rchild, int parent, Term term, QueryCondition attributes) {
		super(id, lchild, rchild, parent, term);
		this.setAttributes(attributes);
	}
	 
	// 加入filter函数
	public SimpleIndexQueryNode(int id, int lchild, int rchild, int parent, Term term, QueryCondition attributes,
			List<FilterClause<?>> filterClause) {
		super(id, lchild, rchild, parent, term);
		this.setAttributes(attributes);
		SimpleIndexQueryNode.filterClause = filterClause;
	}
	/**
	 * 从自建索引表中获取列值
	 * @param namedEntityName
	 * @return LocalResultSet
	 * @throws IOException
	 */
	public LocalResultSet searchIndexTable(String namedEntityName) throws IOException {
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

	public LocalResultSet query() {
		// TODO 查询列的设置
		Map<String, String> attributeMap = this.getAttributes().getConditions();
		List<Result> listResults = new ArrayList<Result>();
		LocalResultSet localResult = new LocalResultSet();
		
		for(Entry<String, String> entry : attributeMap.entrySet())
		{
			try {
				LocalResultSet resultSet = searchIndexTable(entry.getValue());
				listResults.addAll(resultSet.getResults());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		localResult.setResults(listResults);
		return localResult;
	}

	

/*	@Override
	public LocalResultSet query() {
		// TODO Auto-generated method stub

		String family = this.getAttributes().getFamily();
		//List<String> answers = this.getAttributes().getAnswer();
		LocalResultSet rs = new LocalResultSet();
		Map<String, String> attributeMap = this.getAttributes().getConditions();

		List<Filter> fList = new ArrayList<>();
		if (!attributeMap.isEmpty()) {
			for (Map.Entry<String, String> entry : attributeMap.entrySet()) {

				SingleColumnValueFilter columnValueFilter = new SingleColumnValueFilter(family.getBytes(),
						entry.getKey().getBytes(), CompareOp.EQUAL, entry.getValue().getBytes());
				columnValueFilter.setFilterIfMissing(true);
				fList.add(columnValueFilter);
			}
		}

		FilterList filterList = new FilterList(fList);
		scan.setFilter(filterList);
		scan.setCaching(1000);// cache
		setScanToConf(scan);
		JavaPairRDD<ImmutableBytesWritable, Result> rdd = sc.newAPIHadoopRDD(hbaseConf, TableInputFormat.class,
				ImmutableBytesWritable.class, Result.class);

		JavaPairRDD<String, Result> rddResult = rdd
				.mapToPair(new PairFunction<Tuple2<ImmutableBytesWritable, Result>, String, Result>() {

					private static final long serialVersionUID = -842442285360302812L;

					@Override
					public Tuple2<String, Result> call(Tuple2<ImmutableBytesWritable, Result> t) throws Exception {
						byte[] key = t._2.getRow();
						Tuple2<String, Result> result = new Tuple2<String, Result>(Bytes.toString(key), t._2);
						return result;
					}

				});
		rs.setResultRDD(rddResult);

		return rs;
	}*/

}
