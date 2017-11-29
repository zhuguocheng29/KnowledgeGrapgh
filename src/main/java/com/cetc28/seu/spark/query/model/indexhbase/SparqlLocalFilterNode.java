package com.cetc28.seu.spark.query.model.indexhbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;

import org.apache.hadoop.hbase.util.Bytes;


import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.rdf.Term;

import com.cetc28.seu.spark.query.result.ResultSet;
import com.cetc28.seu.sparql.FilterClause;



/**
 * 
 * @author Think
 *
 */
public class SparqlLocalFilterNode extends LeafLocalNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7105984284635656270L;
	/**
	 * 查询类似如下情况 ?s attributes:age ?o (FILTER ?o > 20)*
	 */
	public static QueryCondition queryCondition;
	public static List<FilterClause<?>> filterClause;

	public SparqlLocalFilterNode(int id, int lchild, int rchild, int parent, Term term, QueryCondition queryCondition,
			List<FilterClause<?>> filterClause) {
		super(id, lchild, rchild, parent, term);
		SparqlLocalFilterNode.queryCondition = queryCondition;
		SparqlLocalFilterNode.filterClause = filterClause;
		this.setAttributes(queryCondition);
	}

/*	public LocalResultSet query() {
		LocalResultSet resultSet = new LocalResultSet();
		scan.setMaxVersions();
		scan.setCaching(1000);
		setScanToConf(scan);

		JavaPairRDD<ImmutableBytesWritable, Result> rdd = sc.newAPIHadoopRDD(hbaseConf, TableInputFormat.class,
				ImmutableBytesWritable.class, Result.class);

		JavaPairRDD<ImmutableBytesWritable, Result> result = rdd
				.filter(new Function<Tuple2<ImmutableBytesWritable, Result>, Boolean>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Boolean call(Tuple2<ImmutableBytesWritable, Result> v1) throws Exception {
						// TODO Auto-generated method stub
						String columnFamily = queryCondition.getFamily();
						String[] column = queryCondition.getRdf().getPredict().getValue().split(":");

						if (filterClause.size() != 0) {
							return queryFilter(filterClause, queryCondition, v1._2);
						} else if (v1._2.containsColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]))) // 只有列存在且没有filter操作，才扫描相应的结果
						{
							return true;
						} else {
							return false;
						}
					}

				});

		JavaPairRDD<String, Result> rddResult = result
				.mapToPair(new PairFunction<Tuple2<ImmutableBytesWritable, Result>, String, Result>() {

					private static final long serialVersionUID = -842442285360302812L;

					@Override
					public Tuple2<String, Result> call(Tuple2<ImmutableBytesWritable, Result> t) throws Exception {
						byte[] key = t._2.getRow();
						Tuple2<String, Result> result = new Tuple2<String, Result>(Bytes.toString(key), t._2);
						return result;
					}

				});
		resultSet.setResultRDD(rddResult);

		return resultSet;
	}
*/
	/*public Boolean queryFilter(List<FilterClause> filterClause, QueryCondition queryCondition, Result result) {
		// scan的过滤条件，判断是主语过滤还是宾语过滤
		String columnFamily = queryCondition.getFamily();
		String[] column = queryCondition.getRdf().getPredict().getValue().split(":");
		Map<FilterClause, String> map = new HashMap<FilterClause, String>();
		Boolean judgeCondition = false;
		for (FilterClause localFilterClause : filterClause) {
			if (localFilterClause.getTerm().equals(queryCondition.getRdf().getSubject())) {
				map.put(localFilterClause, "subject");
			}
			if (localFilterClause.getTerm().equals(queryCondition.getRdf().getObject())) {
				map.put(localFilterClause, "object");
			}
		}
		for (Entry<FilterClause, String> entry : map.entrySet()) {
			if (entry.getValue().equals("subject")) {
				switch (entry.getKey().getSymbol()) {
				case ">":
					if (Bytes.toInt(result.getRow()) > entry.getKey().getValue()) {
						judgeCondition = true;
					}
					break;
				case "<":
					if (Bytes.toInt(result.getRow()) < entry.getKey().getValue()) {
						judgeCondition = true;
					}
					break;
				case "=":
					if (Bytes.toInt(result.getRow()) == entry.getKey().getValue()) {
						judgeCondition = true;
					}
					break;
				case ">=":
					if (Bytes.toInt(result.getRow()) >= entry.getKey().getValue()) {
						judgeCondition = true;
					}
					break;
				case "<=":
					if (Bytes.toInt(result.getRow()) <= entry.getKey().getValue()) {
						judgeCondition = true;
					}
					break;
				case "!=":
					if (Bytes.toInt(result.getRow()) != entry.getKey().getValue()) {
						judgeCondition = true;
					}
					break;
				}
			}
			if (entry.getValue().equals("object")) {
				switch (entry.getKey().getSymbol()) {
				case ">":
					for (Cell cell : result.rawCells()) {
						if (Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily)
								&& Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1])
								&& (Bytes.toInt(CellUtil.cloneValue(cell)) > entry.getKey().getValue())) {
							judgeCondition = true;
							break;
						}
					}
					break;
				case "<":
					for (Cell cell : result.rawCells()) {
						if (Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily)
								&& Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1])
								&& (Bytes.toInt(CellUtil.cloneValue(cell)) < entry.getKey().getValue())) {
							judgeCondition = true;
							break;
						}
					}
					break;
				case "=":
					for (Cell cell : result.rawCells()) {
						if (Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily)
								&& Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1])
								&& (Bytes.toInt(CellUtil.cloneValue(cell)) == entry.getKey().getValue())) {
							judgeCondition = true;
							break;
						}
					}
					break;
				case ">=":
					for (Cell cell : result.rawCells()) {
						if (Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily)
								&& Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1])
								&& (Bytes.toInt(CellUtil.cloneValue(cell)) >= entry.getKey().getValue())) {
							judgeCondition = true;
							break;
						}
					}
					break;
				case "<=":
					for (Cell cell : result.rawCells()) {
						if (Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily)
								&& Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1])
								&& (Bytes.toInt(CellUtil.cloneValue(cell)) <= entry.getKey().getValue())) {
							judgeCondition = true;
							break;
						}
					}
					break;
				case "!=":
					for (Cell cell : result.rawCells()) {
						if (Bytes.toString(CellUtil.cloneFamily(cell)).equals(columnFamily)
								&& Bytes.toString(CellUtil.cloneQualifier(cell)).equals(column[1])
								&& (Bytes.toInt(CellUtil.cloneValue(cell)) != entry.getKey().getValue())) {
							judgeCondition = true;
							break;
						}
					}
					break;
				}
			}
		}
		return judgeCondition;
	}
	 

	public List<Filter> queryFilter(List<FilterClause> filterClause, QueryCondition queryCondition) {
		// scan的过滤条件，判断是主语过滤还是宾语过滤
		String columnFamily = queryCondition.getFamily();
		String[] column = queryCondition.getRdf().getPredict().getValue().split(":");
		Map<FilterClause, String> map = new HashMap<FilterClause, String>();
		List<Filter> listFilter = new ArrayList<>();
		for (FilterClause localFilterClause : filterClause) {
			if (localFilterClause.getTerm().equals(queryCondition.getRdf().getSubject())) {
				map.put(localFilterClause, "subject");
			}
			if (localFilterClause.getTerm().equals(queryCondition.getRdf().getObject())) {
				map.put(localFilterClause, "object");
			}
		}
		for (Entry<FilterClause, String> entry : map.entrySet()) {
			if (entry.getValue().equals("subject")) {
				switch (entry.getKey().getSymbol()) {
				case ">":
					Filter filterG = new RowFilter(CompareOp.GREATER,
							new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
					listFilter.add(filterG);
					break;
				case "<":
					Filter filterL = new RowFilter(CompareOp.LESS,
							new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
					listFilter.add(filterL);
					break;
				case "=":
					Filter filterE = new RowFilter(CompareOp.EQUAL,
							new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
					listFilter.add(filterE);
					break;
				case ">=":
					Filter filterGOE = new RowFilter(CompareOp.GREATER_OR_EQUAL,
							new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
					listFilter.add(filterGOE);
					break;
				case "<=":
					Filter filterLOE = new RowFilter(CompareOp.LESS_OR_EQUAL,
							new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
					listFilter.add(filterLOE);
					break;
				case "!=":
					Filter filterNE = new RowFilter(CompareOp.NOT_EQUAL,
							new BinaryComparator(Bytes.toBytes(entry.getKey().getValue())));
					listFilter.add(filterNE);
					break;
				}
			}
			if (entry.getValue().equals("object")) {
				switch (entry.getKey().getSymbol()) {
				case ">":
					SingleColumnValueFilter singleColumnValueFilterG = new SingleColumnValueFilter(
							Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]), CompareOp.GREATER,
							Bytes.toBytes(entry.getKey().getValue()));
					singleColumnValueFilterG.setFilterIfMissing(true);
					listFilter.add(singleColumnValueFilterG);
					break;
				case "<":
					SingleColumnValueFilter singleColumnValueFilterL = new SingleColumnValueFilter(
							Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]), CompareOp.LESS,
							Bytes.toBytes(entry.getKey().getValue()));
					singleColumnValueFilterL.setFilterIfMissing(true);
					listFilter.add(singleColumnValueFilterL);
					break;
				case "=":
					SingleColumnValueFilter singleColumnValueFilterE = new SingleColumnValueFilter(
							Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]), CompareOp.EQUAL,
							Bytes.toBytes(entry.getKey().getValue()));
					singleColumnValueFilterE.setFilterIfMissing(true);
					listFilter.add(singleColumnValueFilterE);
					break;
				case ">=":
					SingleColumnValueFilter singleColumnValueFilterGOE = new SingleColumnValueFilter(
							Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]), CompareOp.GREATER_OR_EQUAL,
							Bytes.toBytes(entry.getKey().getValue()));
					singleColumnValueFilterGOE.setFilterIfMissing(true);
					listFilter.add(singleColumnValueFilterGOE);
					break;
				case "<=":
					SingleColumnValueFilter singleColumnValueFilterLOE = new SingleColumnValueFilter(
							Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]), CompareOp.LESS_OR_EQUAL,
							Bytes.toBytes(entry.getKey().getValue()));
					singleColumnValueFilterLOE.setFilterIfMissing(true);
					listFilter.add(singleColumnValueFilterLOE);
					break;
				case "!=":
					SingleColumnValueFilter singleColumnValueFilterNE = new SingleColumnValueFilter(
							Bytes.toBytes(columnFamily), Bytes.toBytes(column[1]), CompareOp.NOT_EQUAL,
							Bytes.toBytes(entry.getKey().getValue()));
					singleColumnValueFilterNE.setFilterIfMissing(true);
					listFilter.add(singleColumnValueFilterNE);
					break;
				}
			}
		}
		return listFilter;
	}*/

	@Override
	public ResultSet query() {
		// TODO Auto-generated method stub
		return null;
	}


}
