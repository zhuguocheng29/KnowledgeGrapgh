package com.cetc28.seu.spark.query.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaSparkContext;

import com.cetc28.seu.query.struct.Joiner;
import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.query.struct.QueryPlan;
import com.cetc28.seu.rdf.Constant;
import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.rdf.Variable;
import com.cetc28.seu.spark.query.model.simple.SimpleFilterNode;

public class Parser {
	public  static QueryPlan plan(List<RDF> rdfs,JavaSparkContext sc,Configuration hbaseconf){
		QueryPlan queryPlan=null;
		Set<Term> subjectSet=new HashSet<Term>();
		RDF joiner = null;
	
		for(RDF rdf : rdfs){
			subjectSet.add(rdf.getSubject());
		}
		HashMap<Term,List<RDF>>  sameSubject =new HashMap<Term,List<RDF>>();
		
		for(Term term : subjectSet){
			List<RDF> sames=new ArrayList<>();
			for(RDF rdf : rdfs){
				//System.out.println("term.equals(rdf.getSubject()) : " +term.equals(rdf.getSubject()));
				//System.out.println("rdf.getObject().isVariable(): "+rdf.getObject().isVariable());
				if(term.equals(rdf.getSubject()) && !rdf.getObject().isVariable()){
					sames.add(rdf);
				}
			}
			sameSubject.put(term, sames);
		}
		for(RDF rdf : rdfs){
				
			if(rdf.getObject().isVariable() && subjectSet.contains(rdf.getObject())){
				joiner=rdf;
			}
		}
		
		if(sameSubject.size()>2){
			System.out.println("the current query is not supported");
			return null;
		}
		QueryCondition subject=null;
		QueryCondition object=null;
	
		for(Map.Entry<Term, List<RDF>> entry : sameSubject.entrySet()){
			if(joiner==null ) System.out.println("error !");
			//String family="attributes";
			HashMap<String,String> conditions = new HashMap<>();
			List<String> answer=new ArrayList<>();
			List<RDF> filter=entry.getValue();
			for(RDF rdf : filter){
				Constant predict=(Constant) rdf.getPredict();
				Constant obj=(Constant) rdf.getObject();
				if(!obj.isVariable())conditions.put(predict.getValue(),obj.getValue());
				else answer.add(predict.getValue());
			}
			if(entry.getKey().equals(joiner.getSubject())){
				subject=new QueryCondition(conditions);
				
			}else{
				object=new QueryCondition(conditions);
			}
		}
		Constant joinerPre=(Constant)joiner.getPredict();
		Joiner joinerQuery=new Joiner("objects", joinerPre.getValue());
		queryPlan=new QueryPlan(subject, object, joinerQuery);		
		return queryPlan;
	}
	public static SimpleFilterNode FilterPlan(List<RDF> rdfs,Configuration conf ,JavaSparkContext sc){
		Term head=rdfs.get(0).getSubject();
		Variable headVar=(Variable) head;
		HashMap<String, String> conditions=new HashMap<>();
		List<String> answer=new ArrayList<>();
		for(RDF rdf : rdfs){
			Term subject=rdf.getSubject();
			if(!subject.isVariable()) {
				System.out.println("subject is not var");
				System.exit(0);
			}
			Variable subjectVar=(Variable) subject;			
			if(subjectVar.getValue()!=headVar.getValue()){
				System.out.println("this query is not star style!");
				System.exit(0);
			}
			if(!rdf.getPredict().isVariable())conditions.put(rdf.getPredict().getValue(), rdf.getObject().getValue());	
			else {
				answer.add(rdf.getPredict().getValue());
			}
		}
		QueryCondition attributes=new QueryCondition(conditions);
		SimpleFilterNode node=new SimpleFilterNode(attributes, conf, sc);
		return node;
	}
}
