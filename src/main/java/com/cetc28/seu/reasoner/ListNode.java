package com.cetc28.seu.reasoner;

/**
 * 链表中的结点
 * @author Think
 *
 */
public class ListNode {
	//前缀编码
	private String prefix;
	private String value;
	private int id;
	private ListNode nextNode;
	
	public String getPrefix() {
		return prefix;
	}
	public int getId() {
		return id;
	}
	public ListNode getNextNode() {
		return nextNode;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNextNode(ListNode nextNode) {
		this.nextNode = nextNode;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
