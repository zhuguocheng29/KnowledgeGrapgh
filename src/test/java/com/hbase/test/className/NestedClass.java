package com.hbase.test.className;

public class NestedClass {
	private String childName;
	private int childAge;
	private String Country;
	
	public NestedClass(String childName, int childAge, String country) {
		super();
		this.childName = childName;
		this.childAge = childAge;
		Country = country;
	}
	public NestedClass() {
		super();
	}
	public String getChildName() {
		return childName;
	}
	public int getChildAge() {
		return childAge;
	}
	public String getCountry() {
		return Country;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public void setChildAge(int childAge) {
		this.childAge = childAge;
	}
	public void setCountry(String country) {
		Country = country;
	}
	
	
	
}
