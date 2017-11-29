package com.cetc28.seu.rdf;

public class StringLiteral implements Term{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5413174514278649125L;
	private String value;
	
	public StringLiteral(String value) {
		this.value=value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public boolean isVariable() {
		if(value.startsWith("?")){
			return true;
		}
		return false;
	}
	
}
