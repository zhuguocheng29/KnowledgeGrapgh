package com.cetc28.seu.spark.query.result;

import java.util.HashMap;

import com.cetc28.seu.rdf.Term;

/**
 *  　该类保存查询返回值的信息
 * @author yaosheng
 */
public class ReturnValue {
	private HashMap<String,String> returnProperty=new HashMap<>();
	private Term returnObject;
	public ReturnValue(HashMap<String,String> returnProperty,Term returnObject){
		
	}
	public HashMap<String, String> getReturnProperty() {
		return returnProperty;
	}
	public void setReturnProperty(HashMap<String, String> returnProperty) {
		this.returnProperty = returnProperty;
	}
	public Term getReturnObject() {
		return returnObject;
	}
	public void setReturnObject(Term returnObject) {
		this.returnObject = returnObject;
	}
}
