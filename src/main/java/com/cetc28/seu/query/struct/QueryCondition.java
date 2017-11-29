package com.cetc28.seu.query.struct;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.cetc28.seu.rdf.RDF;

public class QueryCondition implements QueryElement,Serializable{

	private static final long serialVersionUID = 1L;
	private static final String family="attributes";
	private HashMap<String,String> conditions;//查询使用的条件 列名 和值
	private static List<String> familyList;
	//查询?s attributes:age ?o的情况,根据列名
	private RDF rdf;
	@Deprecated
	private List<String> answer;//要查询的列名
	
	public QueryCondition(RDF rdf)
	{
		super();
		this.rdf = rdf;
	}
	
	public QueryCondition(HashMap<String, String> conditions) {
		super();
		this.conditions = conditions;
	}

	public String getFamily() {
		return family;
	}
	
	public void setFamilyList(List<String> familyList){
		this.familyList = familyList;
	}

	public List<String> getFamilyList(){
		return familyList;
	}

	public HashMap<String, String> getConditions() {
		return conditions;
	}

	public void setConditions(HashMap<String, String> conditions) {
		this.conditions = conditions;
	}

	public RDF getRdf() {
		return rdf;
	}

	public void setRdf(RDF rdf) {
		this.rdf = rdf;
	}

	public List<String> getAnswer() {
		return answer;
	}

	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}
	
}
