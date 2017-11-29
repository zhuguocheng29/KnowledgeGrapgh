package com.cetc28.seu.sparql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;

public class WhereClause {
	private HashMap<Term, List<RDF>> rdfMap;
	private List<RDF> constantSub=new ArrayList<>();//主语为常量，即主键为常量 
	private List<RDF> joins;

	public WhereClause(List<RDF> rdfs) {
		this.rdfMap = new HashMap<>();
		this.joins = new ArrayList<>();
		construct(rdfs);
	}

	public HashMap<Term, List<RDF>> getRdfs() {
		return rdfMap;
	}

	public void setRdfs(HashMap<Term, List<RDF>> rdfs) {
		this.rdfMap = rdfs;
	}

	public List<RDF> getJoins() {
		return joins;
	}

	public void setJoins(List<RDF> joins) {
		this.joins = joins;
	}

	private void construct(List<RDF> rdfs) {
		for (int i = 0; i < rdfs.size(); i++) {
			Term sub = rdfs.get(i).getSubject();
			Term obj = rdfs.get(i).getObject();
			if(!sub.isVariable()){
				constantSub.add(rdfs.get(i));
			}else{
				if (this.rdfMap.containsKey(sub)&& !obj.isVariable()) {
					rdfMap.get(sub).add(rdfs.get(i));
					
				} else if(!obj.isVariable()){
					List<RDF> RDFArrays = new ArrayList<>();
					RDFArrays.add(rdfs.get(i));
					rdfMap.put(rdfs.get(i).getSubject(), RDFArrays);
					
				}else {
					/*for (int j = 0; j < rdfs.size(); j++) {
						if(i==j) continue;
						Term subOfj = rdfs.get(j).getSubject();
						Term objOfj = rdfs.get(j).getObject();
						// 如果 i的 宾语是变量 且等于 j的主语，则 i表示 join的rdf
						if (sub.isVariable()&&obj.isVariable()&&(obj.getValue().equals(subOfj.getValue())
								||sub.getValue().equals(subOfj.getValue())
								||sub.getValue().equals(objOfj.getValue())
								||obj.getValue().equals(objOfj.getValue()))) {
							joins.add(rdfs.get(j));
							joins.add(rdfs.get(i));
						} else {
							List<RDF> RDFArrays = new ArrayList<>();
							RDFArrays.add(rdfs.get(i));
							rdfMap.put(rdfs.get(i).getSubject(), RDFArrays);
						}
					}*/
					
					joins.add(rdfs.get(i));
					
				}
			}
			

		}
//		System.out.println(joins.toString());
//		System.out.println(joins.get(0).getSubject()+" "+joins.get(0).getPredict()+" "+joins.get(0).getObject());
	}

	public List<RDF> getConstantSub() {
		return constantSub;
	}

	public void setConstantSub(List<RDF> constantSub) {
		this.constantSub = constantSub;
	}

}
