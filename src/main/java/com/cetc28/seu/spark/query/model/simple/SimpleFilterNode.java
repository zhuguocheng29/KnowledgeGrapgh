
package com.cetc28.seu.spark.query.model.simple;



import java.util.List;



import org.apache.hadoop.conf.Configuration;

import org.apache.spark.api.java.JavaSparkContext;



import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.spark.query.model.LeafNode;
import com.cetc28.seu.spark.query.model.QueryNode;
import com.cetc28.seu.spark.query.result.DistributedResultSet;
import com.cetc28.seu.sparql.FilterClause;



public class SimpleFilterNode extends LeafNode {

	private static final long serialVersionUID = 5600695106350071907L;
	public static List<FilterClause<?>> filterClause;

	public SimpleFilterNode(QueryCondition attributes){
		this.setAttributes(attributes);
	}
	public SimpleFilterNode(QueryCondition attributes,Configuration conf,JavaSparkContext sc) {
		this.setAttributes(attributes);		
		QueryNode.hbaseConf=conf;
		QueryNode.sc=sc;
	}
	
	public SimpleFilterNode(int id, int lchild, int rchild, int parent, Term term,QueryCondition attributes) {
		super(id, lchild, rchild, parent, term);
		this.setAttributes(attributes);	
	}
	//加入filter函数
	public SimpleFilterNode(int id, int lchild, int rchild, int parent, Term term,QueryCondition attributes, List<FilterClause<?>> filterClause) {
		super(id, lchild, rchild, parent, term);
		this.setAttributes(attributes);	
		SimpleFilterNode.filterClause = filterClause;
	}
	
	
	public DistributedResultSet query() {
		
		DistributedResultSet rs=new DistributedResultSet();
		scan.setFilter(getFilter(this.getAttributes()));
		scan.setCaching(1000);//cache
		SparkBaseQuery(scan, rs);
		return rs;
	}
	
	

	
	
}
