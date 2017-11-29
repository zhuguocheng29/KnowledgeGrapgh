package com.cetc28.seu.spark.query.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Test;

import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.rdf.Constant;
import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.StringLiteral;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.rdf.Variable;
import com.cetc28.seu.spark.query.model.QueryNode;
import com.cetc28.seu.spark.query.model.simple.JoinerNode;
import com.cetc28.seu.spark.query.model.simple.SimpleFilterNode;
import com.cetc28.seu.spark.query.model.simple.SparqlFilterNode;
import com.cetc28.seu.sparql.FilterClause;
import com.cetc28.seu.sparql.SelectClause;

/**
 * TODO 实现简单的查询树生成，不考虑查询树结构的优化问题
 * 
 * @author yaosheng
 */
public class SimpleTreeGenerator extends TreeGenerator {

	public SimpleTreeGenerator(){
		
	}
	
    public SimpleTreeGenerator(Configuration hbaseconf,JavaSparkContext sc){
		QueryNode.hbaseConf=hbaseconf;
		QueryNode.sc=sc;
	}

	//zhuguocheng add
	@Override
	public void generate(SelectClause sClause) {
		// 通过查询语句的中的信息生成查询树
		joins = sClause.getwClause().getJoins();
		conditions = sClause.getwClause().getRdfs();
		this.returnProperty=sClause.returnProperty;
		this.returnObject=sClause.returnObject;
		if (joins == null) {
			System.out.println("inint error ! ");
			return;

		}else if (joins.size() == 0) {// 该查询不存在join,直接处理 filter查询
			// 如果不存在join，则说明应该只存在一个 filter结点
			Term  sub= conditions.keySet().iterator().next();
			if(sClause.returnObject!=null) sub=sClause.returnObject;
			else returnObject=sub;
			HashMap<String, String> qCondition = constructQueryCondition(conditions.get(sub));
			QueryCondition qc = new QueryCondition(qCondition);
			SimpleFilterNode leafNode=new SimpleFilterNode(nodeid++, -1, -1, -1, sub, qc, sClause.getFilter());
			nodeList.add(leafNode);
			tree.setRoot(leafNode); 
		}else{
			// 根据joins 生成树的结构，根据conditions 补充结点的conditions
			HashMap<Term, Integer> counts = countTerms();
			Term root = Max(counts);
			//构建返回值，如果返回值中有实体，以该实体为根,表示为 all;否则为返回实体的属性
			if(this.returnObject!=null){
				root=this.returnObject;
			}else{
				if(sClause.returnProperty==null|| sClause.returnProperty.size()==0){
					this.returnObject=root;
				}
			}
			// 找到Term 对应的一个三元组
			RDF rootRDF = findRDF(root);
			
			//zhuguocheng add,因为rootRDF.getPredict().getValue()返回值为objects:xx,需要拆分，获得xx
			if(rootRDF.getPredict().getValue().contains("objects"))
			{
				joins.remove(rootRDF);// 移除正在处理的三元组
				JoinerNode rootNode = new JoinerNode();
				rootNode.setId(nodeid++);
				String[] Predict = rootRDF.getPredict().getValue().split(":");
				//System.out.println(Predict[1]);
				rootNode.setJoinColumn(Predict[1]);
				rootNode.setTerm(root);
				nodeList.add(rootNode);
				tree.setRoot(rootNode);
				// reduce(counts,root);根部是返回值，不再需要join，不减去次数
				rootNode.setLchild(generateChild(rootNode, rootRDF.getSubject(), counts, sClause.getFilter()));
				rootNode.setRchild(generateChild(rootNode, rootRDF.getObject(), counts, sClause.getFilter()));
				rootNode.constructReturn(rootRDF);
			}
			else
			{
				//说明出现类似?s attributes:age ?v 可能
				QueryCondition queryCondition = new QueryCondition(rootRDF);
				SparqlFilterNode leafNode=new SparqlFilterNode(nodeid++, -1, -1, -1, root,queryCondition, sClause.getFilter());
				nodeList.add(leafNode);
				tree.setRoot(leafNode);
			}

		}

		tree.setTrees(nodeList);
		tree.setReturnObject(returnObject);
		tree.setReturnProperty(returnProperty);
		return;
	}
	/*@Override
	public void generate(SelectClause sClause) {
		// 通过查询语句的中的信息生成查询树
		joins = sClause.getwClause().getJoins();
		conditions = sClause.getwClause().getRdfs();
		this.returnProperty=sClause.returnProperty;
		this.returnObject=sClause.returnObject;
		if (joins == null) {
			System.out.println("inint error ! ");
			return;

		}else if (joins.size() == 0) {// 该查询不存在join,直接处理 filter查询
			// 如果不存在join，则说明应该只存在一个 filter结点
			Term  sub= conditions.keySet().iterator().next();
			if(sClause.returnObject!=null) sub=sClause.returnObject;
			else returnObject=sub;
			HashMap<String, String> qCondition = constructQueryCondition(conditions.get(sub));
			QueryCondition qc = new QueryCondition(qCondition);
			SimpleFilterNode leafNode=new SimpleFilterNode(nodeid++, -1, -1, -1, sub, qc);
			nodeList.add(leafNode);
			tree.setRoot(leafNode); 
		}else{
			// 根据joins 生成树的结构，根据conditions 补充结点的conditions
			HashMap<Term, Integer> counts = countTerms();
			Term root = Max(counts);
			//构建返回值，如果返回值中有实体，以该实体为根,表示为 all;否则为返回实体的属性
			if(this.returnObject!=null){
				root=this.returnObject;
			}else{
				if(sClause.returnProperty==null|| sClause.returnProperty.size()==0){
					this.returnObject=root;
				}
			}
			// 找到Term 对应的一个三元组
			RDF rootRDF = findRDF(root);
			joins.remove(rootRDF);// 移除正在处理的三元组
			JoinerNode rootNode = new JoinerNode();
			rootNode.setId(nodeid++);
			System.out.println(rootRDF.getPredict().getValue());
			rootNode.setJoinColumn(rootRDF.getPredict().getValue());
			rootNode.setTerm(root);
			nodeList.add(rootNode);
			tree.setRoot(rootNode);
			// reduce(counts,root);根部是返回值，不再需要join，不减去次数
			rootNode.setLchild(generateChild(rootNode, rootRDF.getSubject(), counts));
			rootNode.setRchild(generateChild(rootNode, rootRDF.getObject(), counts));
			rootNode.constructReturn(rootRDF);
		}

		tree.setTrees(nodeList);
		tree.setReturnObject(returnObject);
		tree.setReturnProperty(returnProperty);
		return;
	}*/
	
	
	/**
	 *  递归生成查询树
	 * @param root
	 * @return 当前的id
	 */
	private int generateChild(QueryNode parent, Term childTerm, HashMap<Term, Integer> counts, List<FilterClause<?>> filterClause) {

		// 找到父节点建立子结点
		QueryNode child = null;
		if (counts.get(childTerm) > 1) {
			RDF childRDF = findRDF(childTerm);
			joins.remove(childRDF);// 移除正在处理的三元组
			reduce(counts, childTerm);
			if(!childRDF.getPredict().getValue().contains("objects"))
			{
				//说明出现类似?s attributes:age ?v 可能
				QueryCondition queryCondition = new QueryCondition(childRDF);
				child = new SparqlFilterNode(nodeid++, -1, -1, parent.getId(), childTerm,queryCondition, filterClause);
				nodeList.add(child);
			}
			else
			{
				String[] predicate = childRDF.getPredict().getValue().split(":");
				child = new JoinerNode(nodeid++, parent.getId(), childTerm,childRDF,
						predicate[1]);
				nodeList.add(child);
			}
			child.setLchild(generateChild(child, childRDF.getSubject(), counts, filterClause));
			child.setRchild(generateChild(child, childRDF.getObject(), counts, filterClause));
			return child.getId();
		} else if (counts.get(childTerm) == 1) {
			QueryCondition qc = null;
			HashMap<String, String> attributes = new HashMap<>();
			if (conditions.containsKey(childTerm)) {
				attributes = constructQueryCondition(conditions.get(childTerm));
			}
			qc = new QueryCondition(attributes);
			child = new SimpleFilterNode(nodeid++, -1, -1, parent.getId(), childTerm, qc, filterClause);
			nodeList.add(child);
			reduce(counts, childTerm);
			return child.getId();
		} else {
			return -1;// 已经是叶子结点
		}

	}
	
	/*private int generateChild(QueryNode parent, Term childTerm, HashMap<Term, Integer> counts) {

		// 找到父节点建立子结点
		QueryNode child = null;
		if (counts.get(childTerm) > 1) {
			RDF childRDF = findRDF(childTerm);
			joins.remove(childRDF);// 移除正在处理的三元组
			reduce(counts, childTerm);
			child = new JoinerNode(nodeid++, parent.getId(), childTerm,childRDF,
					childRDF.getPredict().getValue());
			nodeList.add(child);
			child.setLchild(generateChild(child, childRDF.getSubject(), counts));
			child.setRchild(generateChild(child, childRDF.getObject(), counts));
			
			return child.getId();
		} else if (counts.get(childTerm) == 1) {
			QueryCondition qc = null;
			HashMap<String, String> attributes = new HashMap<>();
			if (conditions.containsKey(childTerm)) {
				attributes = constructQueryCondition(conditions.get(childTerm));
			}
			qc = new QueryCondition(attributes);
			child = new SimpleFilterNode(nodeid++, -1, -1, parent.getId(), childTerm, qc);
			nodeList.add(child);
			reduce(counts, childTerm);
			return child.getId();
		} else {
			return -1;// 已经是叶子结点
		}

	}*/



	@Test
	public void test() {
		List<RDF> rdfs = new ArrayList<>();
		/*
		 * RDF rdf1=new RDF(new Variable("?s1"), new Constant("p1"), new
		 * Variable("?o1")); RDF rdf2=new RDF(new Variable("?s1"), new
		 * Constant("p2"), new Variable("?o2")); rdfs.add(rdf1); rdfs.add(rdf2);
		 */
		RDF rdf1 = new RDF(new Variable("?s1"), new Constant("p1"), new Variable("?o1"));
		RDF rdf2 = new RDF(new Variable("?o1"), new Constant("p2"), new Variable("?o2"));
		RDF rdf3 = new RDF(new Variable("?o1"), new Constant("p3"), new StringLiteral("?v3"));
		rdfs.add(rdf3);
		rdfs.add(rdf1);
		rdfs.add(rdf2);
		List<Term> variableList=new ArrayList<>();
		variableList.add(new Variable("?o1"));
		variableList.add(new StringLiteral("?v3"));
		SelectClause sClause = new SelectClause(rdfs,variableList);
		
		
		SimpleTreeGenerator sTreeG = new SimpleTreeGenerator();
		sTreeG.generate(sClause);
		System.out.println("root : " + sTreeG.tree.getRoot().getTerm().getValue());
	}
}

