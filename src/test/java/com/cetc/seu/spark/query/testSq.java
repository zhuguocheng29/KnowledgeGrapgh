package com.cetc.seu.spark.query;

public class testSq {

	public static void main(String[] args) {
		String value = "123()";
		if(value.contains("()")){
			value = value.substring(0, value.length()-2);
		}
	}

}
