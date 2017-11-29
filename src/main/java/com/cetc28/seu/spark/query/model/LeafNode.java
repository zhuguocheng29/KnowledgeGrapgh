package com.cetc28.seu.spark.query.model;

import org.apache.hadoop.hbase.filter.FilterList;

import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.spark.query.result.DistributedResultSet;

/**
 * 
 * @author yaosheng
 *  this class represent only query entity by filter 
 */
public abstract class LeafNode extends QueryNode{

	private static final long serialVersionUID = -4227280518313382680L;
	private QueryCondition attributes;
	
	public LeafNode(){
		
	}
	public LeafNode(int id, int lchild, int rchild, int parent, Term term) {
		super(id, lchild, rchild, parent, term);
	}

	public QueryCondition getAttributes() {
		return attributes;
	}

	public void setAttributes(QueryCondition attributes) {
		this.attributes = attributes;
	}
	
	public DistributedResultSet FilterBaseQuery(FilterList flist){
		
		return null;
	}
	
}
