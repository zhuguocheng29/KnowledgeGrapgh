package com.cetc28.seu.reasoner;

/**
 * 表中的顶点
 * @author Think
 *
 */
public class ArrayNode {
	//顶点的信息
	private String data;
	private ListNode firstNode;
	private String prefix;
	//增加父亲节点
	private ArrayNode parentNode;
	
	public String getData() {
		return data;
	}
	public ListNode getFirstNode() {
		return firstNode;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setFirstNode(ListNode firstNode) {
		this.firstNode = firstNode;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public ArrayNode getParentNode() {
		return parentNode;
	}
	public void setParentNode(ArrayNode parentNode) {
		this.parentNode = parentNode;
	}
	
	
}
