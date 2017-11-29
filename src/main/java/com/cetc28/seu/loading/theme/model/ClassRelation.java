package com.cetc28.seu.loading.theme.model;

import java.util.HashMap;
/**
 * @author yaosheng
 */
public class ClassRelation {
	private String className; // 类型名
	private HashMap<String, String> object; // 保存属性名 和类型
	private HashMap<String, String> ArrayObject; // 保存属性名 和类型集合
	private String parent; // 父类型名
	private HashMap<String, String> baseProperty; //保存基础属性(属性名 ， 类型)
	private int id; // hbase 中的rowkey 对语义无帮助
	private String themeName;//

	public ClassRelation() {
	

	}

	public ClassRelation(String className, HashMap<String, String> object, HashMap<String, String> arrayObject,
			String parent, HashMap<String, String> baseProperty,int id,String themeName) {
		super();
		this.className = className;
		this.object = object;
		ArrayObject = arrayObject;
		this.parent = parent;
		this.baseProperty = baseProperty;
		this.id=id;
		this.themeName=themeName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public HashMap<String, String> getObject() {
		return object;
	}

	public void setObject(HashMap<String, String> object) {
		this.object = object;
	}

	public HashMap<String, String> getArrayObject() {
		return ArrayObject;
	}

	public void setArrayObject(HashMap<String, String> arrayObject) {
		ArrayObject = arrayObject;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public HashMap<String, String> getBaseProperty() {
		return baseProperty;
	}

	public void setBaseProperty(HashMap<String, String> baseProperty) {
		this.baseProperty = baseProperty;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

}
