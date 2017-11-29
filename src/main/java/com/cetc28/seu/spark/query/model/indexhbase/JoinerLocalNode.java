  package com.cetc28.seu.spark.query.model.indexhbase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.spark.query.model.InnerNode;
import com.cetc28.seu.spark.query.result.LocalResultSet;
import com.cetc28.seu.spark.query.result.ResultSet;


enum  returnValueLocal{left,right};// 表示返回值
public class JoinerLocalNode extends InnerNode {

	private static final long serialVersionUID = -5515459951672424727L;
	private final static String family = "objects";
	private String joinColumn;
	private returnValueLocal returnV;
	
	public  JoinerLocalNode() {
		
	}
	
	public JoinerLocalNode(int id, int parent, Term term, String joinColumn) {
		super(id, -1, -1, parent, term);
		this.joinColumn=joinColumn;
	}

	public JoinerLocalNode(int id, int parent, Term childTerm, RDF childRDF, String joinColumn) {
		super(id, -1, -1, parent, childTerm);
		constructReturn(childRDF);
		this.joinColumn=joinColumn;
	}

	public void constructReturn(RDF rdf){
		if(this.getTerm().getValue().equals(rdf.getSubject().getValue())){
			returnV=returnValueLocal.left;
		}else{
			returnV=returnValueLocal.right;
		}
	}
	
	@Override
	public ResultSet query() {
		// TODO local query join using hashMap
		// 1. read from the left (subject) , save to a hashmap
		LocalResultSet locRes=new LocalResultSet();
		List<Result> res=new ArrayList<>();
		LocalResultSet subject=(LocalResultSet) this.getLelfInputResult();
		List<Result> subjectResults=subject.getResults();
		HashMap<String,Result> maps=new HashMap<>();
		for(Result result : subjectResults){
			byte[] key=result.getValue(Bytes.toBytes(family), Bytes.toBytes(joinColumn));
			maps.put(Bytes.toString(key),result);
		}
		List<Result> objectResult=((LocalResultSet) this.getRightInputResult()).getResults();
		boolean flag=false;
		for(Result result : objectResult){
			String key = Bytes.toString(result.getRow());
			if(maps.containsKey(key)){
				if(flag||returnV.equals(returnValueLocal.left)){
					flag=true;
					res.add(maps.get(key));
				}else{
					res.add(result);
				}
			}
		}
		locRes.setResults(res);
		return locRes;
	}
	
	
	public String getJoinColumn() {
		return joinColumn;
	}


	public void setJoinColumn(String joinColumn) {
		this.joinColumn = joinColumn;
	}

	public returnValueLocal getReturnV() {
		return returnV;
	}

	public void setReturnV(returnValueLocal returnV) {
		this.returnV = returnV;
	}

	
}
