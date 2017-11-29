package com.cetc28.seu.spark.query.model;


import com.cetc28.seu.rdf.Term;

import com.cetc28.seu.spark.query.result.ResultSet;


/**
 * 
 * @author yaosheng
 *  this class represent the query include join op
 */
public abstract class InnerNode extends QueryNode{
	public InnerNode(){
		
	}
	public InnerNode(int id, int lchild, int rchild, int parent, Term term) {
		super(id, lchild, rchild, parent, term);
	}

	private static final long serialVersionUID = 639199498621883137L;
	private ResultSet lelfInputResult;
	private ResultSet rightInputResult;
	public ResultSet getRightInputResult() {
		return rightInputResult;
	}

	public void setRightInputResult(ResultSet rightInputResult) {
		this.rightInputResult = rightInputResult;
	}

	public ResultSet getLelfInputResult() {
		return lelfInputResult;
	}

	public void setLelfInputResult(ResultSet inputResult) {
		this.lelfInputResult = inputResult;
	}
	
}
