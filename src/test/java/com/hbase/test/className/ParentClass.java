package com.hbase.test.className;

public class ParentClass {
	private String parentName;
	private int parentAge;
	private NestedClass child;
	public ParentClass(String parentName, int parentAge, NestedClass child) {
		super();
		this.parentName = parentName;
		this.parentAge = parentAge;
		this.child = child;
	}
	public ParentClass() {
		super();
	}
	public String getParentName() {
		return parentName;
	}
	public int getParentAge() {
		return parentAge;
	}
	public NestedClass getChild() {
		return child;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public void setParentAge(int parentAge) {
		this.parentAge = parentAge;
	}
	public void setChild(NestedClass child) {
		this.child = child;
	}
	
	
}
