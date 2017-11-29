package com.cetc28.seu.sparql;

import com.cetc28.seu.rdf.Term;

/**
 * 
 * @author Think
 *
 */
public class FilterClause<T> {
	private Term term;
	private String symbol;
	private T value;
	
	public FilterClause(T value) {
		super();
		this.value = value;
	}

	public Term getTerm() {
		return term;
	}

	public String getSymbol() {
		return symbol;
	}

	public T getValue() {
		return value;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	
}
