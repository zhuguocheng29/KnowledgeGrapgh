package com.cetc28.seu.fromAntlr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cetc28.seu.fromAntlr.SparqlParser.ConditionalAndExpressionContext;
import com.cetc28.seu.fromAntlr.SparqlParser.ValueLogicalContext;
import com.cetc28.seu.rdf.Constant;
import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.StringLiteral;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.rdf.Variable;
import com.cetc28.seu.sparql.FilterClause;
import com.cetc28.seu.sparql.SelectClause;

/**
 * 继承自SparqlBaseListener的结构
 * 
 * @author Think
 *
 */
public class CustomListener extends SparqlBaseListener {
	public SparqlParser parser;
	public SelectClause selectClause;
	private Map<String, String> prefix;
	private List<Term> variableList;
	private List<RDF> rdfList;
	private List<RDF> rdfUnionList;
	private List<RDF> rdfOptionalList;
	private List<FilterClause<?>> filterClauseList;
	private static String rdfExpression;

	public CustomListener(SparqlParser parser) {
		this.rdfList = new ArrayList<RDF>();
		this.rdfUnionList = new ArrayList<RDF>();
		this.rdfOptionalList = new ArrayList<RDF>();
		this.filterClauseList = new ArrayList<FilterClause<?>>();
		this.parser = parser;
		this.selectClause = new SelectClause();
		this.prefix = new HashMap<String, String>();
		this.variableList = new ArrayList<Term>();

		//判断为union，optional还是普通的三元组
		rdfExpression = "";
		//避免外部调用为null
		this.selectClause.setFilter(filterClauseList);
		this.selectClause.setLimit(0);
		this.selectClause.setOffset(0);
		this.selectClause.setOrderTerm(new HashMap<String,Term>());
		this.selectClause.setPrefix(prefix);
		this.selectClause.setRdfList(rdfList);
		this.selectClause.setRdfOp(new HashMap<String,List<RDF>>());
		this.selectClause.setVariableList(variableList);
	}

	public SelectClause getSelectClause() {
		return selectClause;
	}

	public void setSelectClause(SelectClause selectClause) {
		this.selectClause = selectClause;
	}

	/**
	 * 将获得普通的三元组，放入construct
	 */
	public void exitWhereClause(SparqlParser.WhereClauseContext ctx) {
		// yao
		//selectClause.construct(rdfList);
	}

	/**
	 * 获得前缀，如：PREFIX attributes: <http://www.w3.org/2001/vcard-rdf/3.0#>
	 */
	public void enterPrefixDecl(SparqlParser.PrefixDeclContext ctx) {
		String prefix_v = ctx.PNAME_NS().getText();
		String prefix_url = ctx.IRI_REF().getText();
		prefix.put(prefix_v, prefix_url);
		selectClause.setPrefix(prefix);
	}

	/**
	 * 将select获得的变量与selectQuery中获得的变量相匹配，如select ?givenName where ?givenName...
	 */
	public void exitSelectQuery(SparqlParser.SelectQueryContext ctx) {

		int size = ctx.variable().size();
		if(size == 0)
		{
			String allVariableList = ctx.getChild(1).getText();
			System.out.println("allVariableList:" + allVariableList);
		}
		else
		{
			for (int i = 0; i < size; i++) {
				String text = ctx.variable(i).getText();
				// 存在union情况进行判断
				if ((selectClause.getRdfOp() != null) && (selectClause.getRdfOp().containsKey("UNION"))) {
					List<RDF> list = selectClause.getRdfOp().get("UNION");
					for (RDF rdf : list) {
						String judgeSubject = rdf.getSubject().getValue();
						String judgePredict = rdf.getPredict().getValue();
						String judgeObject = rdf.getObject().getValue();
						if (text.equals(judgeSubject)) {
							if (!variableList.contains(rdf.getSubject())) {
								variableList.add(rdf.getSubject());
							}
						} else if (text.equals(judgePredict)) {
							if (!variableList.contains(rdf.getPredict())) {
								variableList.add(rdf.getPredict());
							}
						} else if (text.equals(judgeObject)) {
							if (!variableList.contains(rdf.getObject())) {
								variableList.add(rdf.getObject());
							}
						} else {
	
						}
					}
				}
	
				// 存在optional情况进行判断
				if ((selectClause.getRdfOp() != null) && (selectClause.getRdfOp().containsKey("OPTIONAL"))) {
					List<RDF> list = selectClause.getRdfOp().get("OPTIONAL");
					for (RDF rdf : list) {
						String judgeSubject = rdf.getSubject().getValue();
						String judgePredict = rdf.getPredict().getValue();
						String judgeObject = rdf.getObject().getValue();
						if (text.equals(judgeSubject)) {
							if (!variableList.contains(rdf.getSubject())) {
								variableList.add(rdf.getSubject());
							}
						} else if (text.equals(judgePredict)) {
							if (!variableList.contains(rdf.getPredict())) {
								variableList.add(rdf.getPredict());
							}
						} else if (text.equals(judgeObject)) {
							if (!variableList.contains(rdf.getObject())) {
								variableList.add(rdf.getObject());
							}
						} else {
	
						}
					}
				}
	
				// 不存在union,optional情况
				for (int j = 0; j < rdfList.size(); j++) {
					String judgeSubject = selectClause.getRdfList().get(j).getSubject().getValue();
					String judgePredict = selectClause.getRdfList().get(j).getPredict().getValue();
					String judgeObject = selectClause.getRdfList().get(j).getObject().getValue();
					// System.out.println("test: " + judgeSubject +"
					// "+judgePredict+" "+judgeObject);
					if (text.equals(judgeSubject)) {
						if (!variableList.contains(selectClause.getRdfList().get(j).getSubject())) {
							variableList.add(rdfList.get(j).getSubject());
						}
					} else if (text.equals(judgePredict)) {
						if (!variableList.contains(selectClause.getRdfList().get(j).getPredict())) {
							variableList.add(rdfList.get(j).getPredict());
						}
					} else if (text.equals(judgeObject)) {
						if (!variableList.contains(selectClause.getRdfList().get(j).getObject())) {
							variableList.add(rdfList.get(j).getObject());
						}
					} else {
	
					}
				}
	
			}
			selectClause.setVariableList(variableList);
			
		}
	}

	/**
	 * 获取limit值和offset值
	 */
	public void enterLimitOffsetClauses(SparqlParser.LimitOffsetClausesContext ctx) {
		int limit = Integer.parseInt(ctx.limitClause().getChild(1).getText());
		int offset = Integer.parseInt(ctx.offsetClause().getChild(1).getText());
		selectClause.setLimit(limit);
		selectClause.setOffset(offset);
	}

	/**
	 * 获取order是升序还是降序，以及其变量
	 */
	public void enterOrderClause(SparqlParser.OrderClauseContext ctx) {
		int size = ctx.orderCondition().size();
		for (int i = 0; i < size; i++) {
			Term term = null;
			String orderCondition = ctx.orderCondition(i).getChild(0).getText();
			if (orderCondition.equals("DESC") || orderCondition.equals("ASC")) {
				String condition = "";
				if (orderCondition.equals("DESC")) {
					condition = "DESC";
				}
				if (orderCondition.equals("ASC")) {
					condition = "ASC";
				}
				String orderTerm = ctx.orderCondition(i).brackettedExpression().getText();
				if (orderTerm.contains("?")) {
					term = new Variable(orderTerm);
				} else {
					term = new Constant(orderTerm);
				}
				Map<String, Term> map = new HashMap<String, Term>();
				map.put(condition, term);
				selectClause.setOrderTerm(map);
			} else {
				String orderTerm = ctx.orderCondition(i).getText();
				if (orderTerm.contains("?")) {
					term = new Variable(orderTerm);
				} else {
					term = new Constant(orderTerm);
				}
				Map<String, Term> map = new HashMap<String, Term>();
				map.put("ASC", term);
				selectClause.setOrderTerm(map);
			}
		}
	}

	/**
	 * 读取where条件下的三元组
	 */
	public void enterTriplesBlock(SparqlParser.TriplesBlockContext ctx) {
		if (rdfExpression.equals("")) {
			Term termWhereSubject = null;
			Term termWherePredict = null;
			Term termWhereObject = null;

			String whereSubject = ctx.triplesSameSubjectPath().varOrTerm().getText();
			if (whereSubject.contains("?")) {
				termWhereSubject = new Variable(whereSubject);
			} else {
				termWhereSubject = new Constant(whereSubject);
			}

			String wherePredict = ctx.triplesSameSubjectPath().propertyListNotEmptyPath().verbPath(0).getText();
			String whereObject = ctx.triplesSameSubjectPath().propertyListNotEmptyPath().objectList(0).getText();
			if(whereObject.substring(0, 1).equals(":") || whereObject.substring(whereObject.length()-1,whereObject.length()).equals(":"))
			{
				System.out.println("Please input correct format");
				return;
			}

			if (wherePredict.contains("?")) {
				termWherePredict = new Variable(wherePredict);
				if (whereObject.contains("?")) {
					termWhereObject = new Variable(whereObject);
				} else {
					termWhereObject = new Constant(whereObject);
				}
				RDF rdf = new RDF(termWhereSubject, termWherePredict, termWhereObject);
				rdfList.add(rdf);
			} else if (wherePredict.contains("attributes")) {
				termWherePredict = new Constant(wherePredict);
				if (whereObject.contains("?")) {
					termWhereObject = new Variable(whereObject);
				} else {
					termWhereObject = new StringLiteral(whereObject);
				}
				RDF rdf = new RDF(termWhereSubject, termWherePredict, termWhereObject);
				rdfList.add(rdf);
			} else if (wherePredict.contains("objects")) {
				termWherePredict = new Constant(wherePredict);
				if (whereObject.contains("?")) {
					termWhereObject = new Variable(whereObject);
				} else {
					termWhereObject = new Constant(whereObject);
				}
				RDF rdf = new RDF(termWhereSubject, termWherePredict, termWhereObject);
				rdfList.add(rdf);
			} else {
				System.out.println("Please input correct format");
			}

			/*
			 * for(int p = 0; p < rdfList.size(); p++) {
			 * System.out.println("rdflist subject:" +
			 * rdfList.get(p).getSubject());
			 * System.out.println("rdflist subject:" +
			 * rdfList.get(p).getPredict());
			 * System.out.println("rdflist subject:" +
			 * rdfList.get(p).getObject());
			 * 
			 * } System.out.println();
			 */
			// //yao
			// selectClause.construct(rdfList);
		}

	}

	/**
	 * 读完所有基本三元组后set三元组
	 */
	public void exitGroupGraphPatternSub(SparqlParser.GroupGraphPatternSubContext ctx) {
		selectClause.setRdfList(rdfList);
	}

	/**
	 * OPTIONAL三元组的操作
	 */
	public void enterOptionalGraphPattern(SparqlParser.OptionalGraphPatternContext ctx) {
		rdfExpression = "OPTIONAL";
		Term termWhereSubject = null;
		Term termWherePredict = null;
		Term termWhereObject = null;
		// 默认为第一个
		String whereSubject = ctx.groupGraphPattern().groupGraphPatternSub().triplesBlock(0).triplesSameSubjectPath()
				.varOrTerm().getText();
		if (whereSubject.contains("?")) {
			termWhereSubject = new Variable(whereSubject);
		} else {
			termWhereSubject = new Constant(whereSubject);
		}
		// 都默认为第一个
		String wherePredict = ctx.groupGraphPattern().groupGraphPatternSub().triplesBlock(0).triplesSameSubjectPath()
				.propertyListNotEmptyPath().verbPath(0).getText();
		String whereObject = ctx.groupGraphPattern().groupGraphPatternSub().triplesBlock(0).triplesSameSubjectPath()
				.propertyListNotEmptyPath().objectList(0).getText();

		if (wherePredict.contains("?")) {
			termWherePredict = new Variable(wherePredict);
			if (whereObject.contains("?")) {
				termWhereObject = new Variable(whereObject);
			} else {
				termWhereObject = new Constant(whereObject);
			}
			RDF rdf = new RDF(termWhereSubject, termWherePredict, termWhereObject);
			rdfOptionalList.add(rdf);
		} else if (wherePredict.contains("attributes")) {
			termWherePredict = new Constant(wherePredict);
			if (whereObject.contains("?")) {
				termWhereObject = new Variable(whereObject);
			} else {
				termWhereObject = new StringLiteral(whereObject);
			}
			RDF rdf = new RDF(termWhereSubject, termWherePredict, termWhereObject);
			rdfOptionalList.add(rdf);
		} else if (wherePredict.contains("objects")) {
			termWherePredict = new Constant(wherePredict);
			if (whereObject.contains("?")) {
				termWhereObject = new Variable(whereObject);
			} else {
				termWhereObject = new Constant(whereObject);
			}
			RDF rdf = new RDF(termWhereSubject, termWherePredict, termWhereObject);
			rdfOptionalList.add(rdf);
		} else {
			System.out.println("Please input correct format");
		}
	}

	/**
	 * 退出OPTIONAL时，设置
	 */
	public void exitOptionalGraphPattern(SparqlParser.OptionalGraphPatternContext ctx) {
		Map<String, List<RDF>> rdfOp = new HashMap<String, List<RDF>>();
		rdfOp.put("OPTIONAL", rdfOptionalList);
		selectClause.setRdfOp(rdfOp);
		rdfExpression = "";
	}

	/**
	 * UNION的操作
	 */
	public void enterGroupOrUnionGraphPattern(SparqlParser.GroupOrUnionGraphPatternContext ctx) {
		rdfExpression = "UNION";
		int num = ctx.groupGraphPattern().size();
		int tripleBlock_num = 0;
		for (int i = 0; i < num; i++) {
			Term termWhereSubject = null;
			Term termWherePredict = null;
			Term termWhereObject = null;
			tripleBlock_num = ctx.groupGraphPattern(i).groupGraphPatternSub().triplesBlock().size();
			for (int j = 0; j < tripleBlock_num; j++) {
				String whereSubject = ctx.groupGraphPattern(i).groupGraphPatternSub().triplesBlock(j)
						.triplesSameSubjectPath().varOrTerm().getText();
				if (whereSubject.contains("?")) {
					termWhereSubject = new Variable(whereSubject);
				} else {
					termWhereSubject = new Constant(whereSubject);
				}
				int verbPath_num = ctx.groupGraphPattern(i).groupGraphPatternSub().triplesBlock(j)
						.triplesSameSubjectPath().propertyListNotEmptyPath().verbPath().size();
				for (int p = 0; p < verbPath_num; p++) {
					String wherePredict = ctx.groupGraphPattern(i).groupGraphPatternSub().triplesBlock(j)
							.triplesSameSubjectPath().propertyListNotEmptyPath().verbPath(p).getText();
					int objectList_num = ctx.groupGraphPattern(i).groupGraphPatternSub().triplesBlock(j)
							.triplesSameSubjectPath().propertyListNotEmptyPath().objectList().size();
					for (int q = 0; q < objectList_num; q++) {
						String whereObject = ctx.groupGraphPattern(i).groupGraphPatternSub().triplesBlock(j)
								.triplesSameSubjectPath().propertyListNotEmptyPath().objectList(q).getText();
						if (wherePredict.contains("?")) {
							termWherePredict = new Variable(wherePredict);
							if (whereObject.contains("?")) {
								termWhereObject = new Variable(whereObject);
							} else {
								termWhereObject = new Constant(whereObject);
							}
							RDF rdf = new RDF(termWhereSubject, termWherePredict, termWhereObject);
							rdfUnionList.add(rdf);
						} else if (wherePredict.contains("attributes")) {
							termWherePredict = new Constant(wherePredict);
							if (whereObject.contains("?")) {
								termWhereObject = new Variable(whereObject);
							} else {
								termWhereObject = new StringLiteral(whereObject);
							}
							RDF rdf = new RDF(termWhereSubject, termWherePredict, termWhereObject);
							rdfUnionList.add(rdf);
						} else if (wherePredict.contains("objects")) {
							termWherePredict = new Constant(wherePredict);
							if (whereObject.contains("?")) {
								termWhereObject = new Variable(whereObject);
							} else {
								termWhereObject = new Constant(whereObject);
							}
							RDF rdf = new RDF(termWhereSubject, termWherePredict, termWhereObject);
							rdfUnionList.add(rdf);
						} else {
							System.out.println("Please input correct format");
						}
					}

				}

			}

		}
	}

	/**
	 * 退出UNION的操作
	 */
	public void exitGroupOrUnionGraphPattern(SparqlParser.GroupOrUnionGraphPatternContext ctx) {
		Map<String, List<RDF>> rdfOp = new HashMap<String, List<RDF>>();
		rdfOp.put("UNION", rdfUnionList);
		// rdfOp.put("UNION", rdfList);
		selectClause.setRdfOp(rdfOp);
		rdfExpression = "";
	}

	/**
	 * 单个比较运算符的filter操作
	 */
	public void enterBrackettedExpression(SparqlParser.BrackettedExpressionContext ctx) {
		for (ConditionalAndExpressionContext condtionalAndExperssionContext : ctx.expression().conditionalOrExpression()
				.conditionalAndExpression()) {
			for (ValueLogicalContext valueLogicalContext : condtionalAndExperssionContext.valueLogical()) {
				Term term = null;
				String left = valueLogicalContext.relationalExpression().numericExpression(0).getText();
				if (left.contains("?")) {
					term = new Variable(left);
				} else {
					term = new StringLiteral(left);
				}
				String middle = valueLogicalContext.relationalExpression().getChild(1).getText();
				String right = valueLogicalContext.relationalExpression().numericExpression(1).getText();
				//判断是否为纯数字
				String regex ="^-?\\d+$";
				if(right.matches(regex)){
					int IntegerRight = Integer.parseInt(right);
					FilterClause<Integer> filterClause = new FilterClause<Integer>(IntegerRight);
					filterClause.setTerm(term);
					filterClause.setSymbol(middle);
					filterClauseList.add(filterClause);
					selectClause.setFilter(filterClauseList);
				}else{
					FilterClause<String> filterClause = new FilterClause<String>(right);
					filterClause.setTerm(term);
					filterClause.setSymbol(middle);
					filterClauseList.add(filterClause);
					selectClause.setFilter(filterClauseList);
				}
			}
		}
	}
}
