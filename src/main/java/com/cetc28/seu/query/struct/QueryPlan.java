package com.cetc28.seu.query.struct;


public class QueryPlan {
	private QueryCondition subFilterQuery;
	private QueryCondition objectFilterQuer;
	private Joiner joinerQuery;
	
	public QueryPlan(QueryCondition subFilterQuery, QueryCondition objectFilterQuer, Joiner joinerQuery) {
		super();
		this.subFilterQuery = subFilterQuery;
		this.objectFilterQuer = objectFilterQuer;
		this.joinerQuery = joinerQuery;
	}

	public QueryCondition getSubFilterQuery() {
		return subFilterQuery;
	}

	public void setSubFilterQuery(QueryCondition subFilterQuery) {
		this.subFilterQuery = subFilterQuery;
	}

	public QueryCondition getObjectFilterQuer() {
		return objectFilterQuer;
	}

	public void setObjectFilterQuer(QueryCondition objectFilterQuer) {
		this.objectFilterQuer = objectFilterQuer;
	}

	public Joiner getJoinerQuery() {
		return joinerQuery;
	}

	public void setJoinerQuery(Joiner joinerQuery) {
		this.joinerQuery = joinerQuery;
	}
	
	
}
