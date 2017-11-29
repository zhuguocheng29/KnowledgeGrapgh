package com.cetc28.seu.spark.query.parser;

import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaSparkContext;

import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.spark.query.model.QueryNode;
import com.cetc28.seu.spark.query.model.QueryTree;
import com.cetc28.seu.spark.query.model.localhbase.JoinerLocalNode;
import com.cetc28.seu.spark.query.model.localhbase.SimpleIndexQueryNode;
import com.cetc28.seu.spark.query.model.localhbase.SparqlLocalFilterNode;
import com.cetc28.seu.sparql.FilterClause;
import com.cetc28.seu.sparql.SelectClause;

public class IndexQueryTreeGenerator extends TreeGenerator{

	public IndexQueryTreeGenerator(){
		
	}
	
    public IndexQueryTreeGenerator(Configuration hbaseconf,JavaSparkContext sc){
		QueryNode.hbaseConf=hbaseconf;
		QueryNode.sc=sc;
	}
    
	public QueryTree getTree() {
		return tree;
	}

	public void setTree(QueryTree tree) {
		this.tree = tree;
	}
	@Override
	public void generate(SelectClause sClause) {
		// TODO Auto-generated method stub
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
					SimpleIndexQueryNode leafNode=new SimpleIndexQueryNode(nodeid++, -1, -1, -1, sub, qc, sClause.getFilter());
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
						JoinerLocalNode rootNode = new JoinerLocalNode();
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
						SparqlLocalFilterNode leafNode=new SparqlLocalFilterNode(nodeid++, -1, -1, -1, root,queryCondition, sClause.getFilter());
						nodeList.add(leafNode);
						tree.setRoot(leafNode);
					}

				}

				tree.setTrees(nodeList);
				tree.setReturnObject(returnObject);
				tree.setReturnProperty(returnProperty);
				return;
	}
	
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
				child = new SparqlLocalFilterNode(nodeid++, -1, -1, parent.getId(), childTerm,queryCondition, filterClause);
				nodeList.add(child);
			}
			else
			{
				String[] predicate = childRDF.getPredict().getValue().split(":");
				child = new JoinerLocalNode(nodeid++, parent.getId(), childTerm,childRDF,
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
			child = new SimpleIndexQueryNode(nodeid++, -1, -1, parent.getId(), childTerm, qc, filterClause);
			nodeList.add(child);
			reduce(counts, childTerm);
			return child.getId();
		} else {
			return -1;// 已经是叶子结点
		}

	}
}
