package com.cetc28.seu.spark.query.result;

import java.io.Serializable;
import java.util.HashMap;



public abstract class ResultSet implements Serializable{

	private static final long serialVersionUID = -9154325466263622982L;
	//protected List<String> askColumns;
	public abstract void show(int num,HashMap<String,String> cols);
	public abstract void show(HashMap<String,String> cols);
	public abstract void show(int num);

	//public List<Object> visit();

}
