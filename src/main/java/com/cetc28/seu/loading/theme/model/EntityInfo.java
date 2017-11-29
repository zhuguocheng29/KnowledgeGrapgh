
package com.cetc28.seu.loading.theme.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class EntityInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6718979697723593236L;

	private String subjectName; //主题名
	
	private String nm; //内码
	
	private Map<String,String> details = new HashMap<String,String>();	//其他详细信息

	public EntityInfo(String subjectName, Map<String, String> details) {
		super();
		this.subjectName = subjectName;
		this.details = details;
	}

	public EntityInfo() {
		super();
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}
	

}
