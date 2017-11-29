package com.cetc28.seu.reasoner;

public class PrefixReasoner {
	private String coderTableName;
	
	public PrefixReasoner(String coderTableName){
		this.coderTableName=coderTableName;
	}
	/**
	 * 使用前缀进行父子类查询
	 */	
	public String getCoderTableName() {
		return coderTableName;
	}
	public void setCoderTableName(String coderTableName) {
		this.coderTableName = coderTableName;
	}
}
