package com.cetc28.seu.spark.query.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cetc28.seu.rdf.Term;

/**
 * 
 * @author yaosheng
 * TODO 当前版本仅支持返回一个实体
 */
public class QueryTree {
	private QueryNode root;
	private List<QueryNode> trees;
	private HashMap<String,String> prefix;//保存查询的前缀
	
	private HashMap<String,String> returnProperty=new HashMap<>();
	private Term returnObject;
	public QueryTree(QueryNode root){
		this.root=root;
		trees=new ArrayList<>();
	}
	public QueryTree() {
	}
	public QueryNode getRoot() {
		return root;
	}
	public void setRoot(QueryNode root) {
		this.root = root;
	}
	public List<QueryNode> getTrees() {
		return trees;
	}
	public void setTrees(List<QueryNode> trees) {
		this.trees = trees;
	}
	public HashMap<String, String> getPrefix() {
		return prefix;
	}
	public void setPrefix(HashMap<String, String> prefix) {
		this.prefix = prefix;
	}
	public HashMap<String, String> getReturnProperty() {
		return returnProperty;
	}
	public void setReturnProperty(HashMap<String, String> returnProperty) {
		this.returnProperty = returnProperty;
	}
	public Term getReturnObject() {
		return returnObject;
	}
	public void setReturnObject(Term returnObject) {
		this.returnObject = returnObject;
	}
	
}
