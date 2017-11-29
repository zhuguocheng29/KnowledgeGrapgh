package com.cetc28.seu.sparql;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.StringLiteral;
import com.cetc28.seu.rdf.Term;



public class SelectClause implements Clause{
	
	public Map<String,String> prefix;
	public List<Term> variableList;
	public HashMap<String,String> returnProperty=new HashMap<>();//保存返回值是属性的值
	public Term returnObject;
	public List<RDF> rdfList;
	private WhereClause wClause;
	//添加 增加判断rdf中的操作属性，是union还是optional
	private Map<String,List<RDF>> rdfOp;
	//order by后的参数
	private Map<String,Term> orderTerm;
	//限制的返回数
	private int limit;
	//偏移量
	private int offset;
	//单个filter条件设置
	private List<FilterClause<?>> filter;
	
	public SelectClause() {
		super();
	}
	public  SelectClause(List<RDF> rdfs){
		constructReturnProperty(rdfs);
		wClause=new WhereClause(rdfs);
	}

	public SelectClause(List<RDF> rdfs, List<Term> variableList) {
		this.variableList=variableList;
		constructReturnProperty(rdfs);
		wClause=new WhereClause(rdfs);
	}
	
	public void compile(){
		//对是否包含UNION的操作进行判断，若有，添加UNION相关的三元组构造返回属性，没有，直接返回rdfList
		List<RDF> judgeList = null;
		if(rdfOp.containsKey("UNION"))
		{
			judgeList = rdfOp.get("UNION");
			for(RDF rdf : rdfList)
			{
				judgeList.add(rdf);
			}
		}
		else
		{
			judgeList = rdfList;
		}
		constructReturnProperty(judgeList);//构造返回的属性
		construct(rdfList);//构造join
	}
	
	public void constructReturnProperty(List<RDF> rdfs){
		List<RDF> temps=new ArrayList<>();
		//select 不为*的情况
		if(variableList.size() != 0)
		{
			for(Term var : variableList){
				if(var instanceof StringLiteral){
					for(RDF rdf: rdfs){
						if(rdf.getObject().getValue().equals(var.getValue()) && rdf.getSubject().isVariable()){
							temps.add(rdf);
						}
					}
				}
			}
			for(RDF temp : temps){//移除描述 查询属性的 rdf
				returnProperty.put(temp.getPredict().getValue(),temp.getObject().getValue());
				rdfs.remove(temp);
				variableList.remove(temp.getObject());
			}
			if(variableList.size()>=1){//还存在返回对象,只返回一个对象
				returnObject=variableList.get(0);
				
			}
		}
		//select * 的情况
		else
		{
			
			//returnObject = null;
		}

		//System.out.println("returnProperty" + returnProperty);
		//System.out.println("returnObject" + returnObject);
	}
	
	public void construct(List<RDF> rdfs){
		wClause=new WhereClause(rdfs);
	}

	public Map<String, String> getPrefix() {
		return prefix;
	}

	public List<Term> getVariableList() {
		return variableList;
	}

	public List<RDF> getRdfList() {
		return rdfList;
	}

	public void setPrefix(Map<String, String> prefix) {
		this.prefix = prefix;
	}

	public void setVariableList(List<Term> variableList) {
		this.variableList = variableList;
	}

	public void setRdfList(List<RDF> rdfList) {
		this.rdfList = rdfList;
	}
		
	public WhereClause getwClause() {
		return wClause;
	}
	public void setwClause(WhereClause wClause) {
		this.wClause = wClause;
	}
	
	public Map<String, List<RDF>> getRdfOp() {
		return rdfOp;
	}
	public void setRdfOp(Map<String, List<RDF>> rdfOp) {
		this.rdfOp = rdfOp;
	}
	public Map<String, Term> getOrderTerm() {
		return orderTerm;
	}
	public void setOrderTerm(Map<String, Term> orderTerm) {
		this.orderTerm = orderTerm;
	}
	public int getLimit() {
		return limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public List<FilterClause<?>> getFilter() {
		return filter;
	}
	public void setFilter(List<FilterClause<?>> filter) {
		this.filter = filter;
	}

	
	

	
	
}
