package com.cetc28.seu.query.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cetc28.seu.query.struct.Joiner;
import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.query.struct.QueryPlan;
import com.cetc28.seu.rdf.Constant;
import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;


/**
 * @author yaosheng
 * 
 */
public class ConstructQueryPlan {
	
	/**
	 * @param rdfs
	 * @return
	 * 
	 */
	public  static QueryPlan plan(List<RDF> rdfs){
		com.cetc28.seu.query.struct.QueryPlan queryPlan=null;
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
}
