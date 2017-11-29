package com.cetc28.seu.spark.query.parser;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.spark.query.model.QueryNode;
import com.cetc28.seu.spark.query.model.QueryTree;
import com.cetc28.seu.sparql.SelectClause;

public abstract class TreeGenerator {
	public abstract void generate(SelectClause sClause);
	protected List<QueryNode> nodeList = new ArrayList<>();
	protected QueryTree tree = new QueryTree();
	public List<QueryNode> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<QueryNode> nodeList) {
		this.nodeList = nodeList;
	}

	public QueryTree getTree() {
		return tree;
	}

	public void setTree(QueryTree tree) {
		this.tree = tree;
	}
	protected HashMap<String,String> returnProperty=new HashMap<>();
	protected Term returnObject;
	protected int nodeid = 0;
	protected HashMap<Term, List<RDF>> conditions;
	protected List<RDF> joins;
	
	/**
	 * @param counts
	 * @return 返回出现次数最多的Term ， 如果存在同样多的，则 返回第一个
	 */
	public Term Max(HashMap<Term, Integer> counts) {
		Term Max = null;
		int maxValue = Integer.MIN_VALUE;
		for (Entry<Term, Integer> ele : counts.entrySet()) {
			Term Key = ele.getKey();
			int value = ele.getValue();
			if (value > maxValue) {
				maxValue=value;
				Max = Key;
			}
		}
		return Max;
	}

	/**
	 *  为指定 Term 更新个数
	 * 
	 * @param counts
	 * @param term
	 */
	public void reduce(HashMap<Term, Integer> counts, Term term) {
		int value = counts.get(term);
		value--;
		counts.put(term, value);// 覆盖原值
	}
	/**
	 *  统计Term出现次数
	 * 
	 * @return
	 */
	public HashMap<Term, Integer> countTerms() {
		HashMap<Term, Integer> counts = new HashMap<>();
		// 统计 Term 的在三元组中出现的个数
		for (RDF rdf : joins) {
			if (counts.containsKey(rdf.getSubject())) {
				counts.put(rdf.getSubject(), counts.get(rdf.getSubject()) + 1);
			} else {
				counts.put(rdf.getSubject(), 1);
			}
			if (counts.containsKey(rdf.getObject())) {
				counts.put(rdf.getObject(), counts.get(rdf.getObject()) + 1);
			} else {
				counts.put(rdf.getObject(), 1);
			}
		}
		//System.out.println(counts.toString());
		return counts;
	}

	public RDF findRDF(Term max) {
		RDF maxRDF = null;
		for (RDF rdf : joins) {
			if (rdf.getSubject().equals(max) || rdf.getObject().equals(max)) {
				maxRDF = rdf;
				break;
			}
		}
		return maxRDF;
	}

	public HashMap<String, String> constructQueryCondition(List<RDF> rdfs) {
		HashMap<String, String> condition = new HashMap<>();
		for (RDF rdf : rdfs) {
			//zhuguocheng add
			String[] predict = rdf.getPredict().getValue().split(":");
			condition.put(predict[1], rdf.getObject().getValue());
//			condition.put(rdf.getPredict().getValue(), rdf.getObject().getValue());
		}
		return condition;
	}
	
	public List<String> ReturnQueryConditionAttributes(List<RDF> rdfs) {
		List<String> getList = new ArrayList<>();
		for (RDF rdf : rdfs) {
			//zhuguocheng add
			String[] predict = rdf.getPredict().getValue().split(":");
			getList.add(predict[0]);
		}
		return getList;
	}
}
