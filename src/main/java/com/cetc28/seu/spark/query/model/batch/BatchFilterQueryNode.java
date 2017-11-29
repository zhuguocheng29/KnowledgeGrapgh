package com.cetc28.seu.spark.query.model.batch;



import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.spark.query.model.LeafNode;
import com.cetc28.seu.spark.query.result.DistributedResultSet;



public class BatchFilterQueryNode extends LeafNode{

	private static final long serialVersionUID = -2762681125802257005L;
	private String startKey;
	private String endKey;
	public BatchFilterQueryNode(String startKey,String endKey){
		this.startKey=startKey;
		this.endKey=endKey;
		
	}
	public DistributedResultSet query() {
		DistributedResultSet rs=new DistributedResultSet();		
		//设置scan
		scan.setStartRow(Bytes.toBytes(startKey));
		scan.setStopRow(Bytes.toBytes(endKey));
		scan.setFilter(getFilter(this.getAttributes()));
		scan.setCaching(1000);//cache
		//查询并保存到rs中
		SparkBaseQuery(scan, rs);
		return rs;
	}
	public String getStartKey() {
		return startKey;
	}
	public void setStartKey(String startKey) {
		this.startKey = startKey;
	}
	public String getEndKey() {
		return endKey;
	}
	public void setEndKey(String endKey) {
		this.endKey = endKey;
	}
}
