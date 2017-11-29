package com.cetc28.seu.loading.theme.model;

import java.util.HashMap;

/**
 * @author yaosheng
 */
public class Property {
	private String parent;
	private String oid;
	private String type;
	private String[] families={"attributes","objects","array_objects"};
	private HashMap<String, String> basePro;
	
	private HashMap<String,HashMap<String,String>> property;
	public HashMap<String, HashMap<String, String>> getProperty() {
		return property;
	}


	public void setProperty(HashMap<String, HashMap<String, String>> property) {
		this.property = property;
	}


	public Property(HashMap<String, String> basePro, String parent, HashMap<String, String> childObj,
			HashMap<String, String> arrayObj, String oid,String type) {
		super();
		this.parent = parent;
		this.oid = oid;
		this.type=type;
		this.basePro=basePro;
		property=new HashMap<>();
		property.put(families[0], basePro);
		property.put(families[1], childObj);
		property.put(families[2], arrayObj);
	}

	
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basePro== null) ? 0 : basePro.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (basePro == null) {
			if (other.basePro != null)
				return false;
		} else if (!basePro.equals(other.basePro))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
