package com.cetc28.seu.query.struct;

public class Joiner implements QueryElement{

	private String familiy;
	private String predict;
	
	
	public Joiner(String familiy, String predict) {
		super();
		this.familiy = familiy;
		this.predict = predict;
	}

	public String getFamiliy() {
		return familiy;
	}

	public void setFamiliy(String familiy) {
		this.familiy = familiy;
	}

	public String getPredict() {
		return predict;
	}

	public void setPredict(String predict) {
		this.predict = predict;
	}
}
