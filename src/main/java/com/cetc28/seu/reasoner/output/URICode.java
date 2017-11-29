package com.cetc28.seu.reasoner.output;

import java.util.HashMap;
import java.util.List;

import com.cetc28.seu.reasoner.AdjacentTable;
import com.cetc28.seu.reasoner.ArrayNode;

public class URICode {
	private HashMap<String,String> codeTable;
	private AdjacentTable adjacent;
	public URICode(AdjacentTable ad){
		codeTable=new HashMap<>();
		adjacent=ad;
	}
	
	public void getResults(){
		List<ArrayNode> listArrayNode=adjacent.getListArrayNode();
		for(ArrayNode node : listArrayNode){
			codeTable.put(node.getData(), node.getPrefix());
		}
	}
	
	public HashMap<String, String> getCodeTable() {
		return codeTable;
	}
	public void setCodeTable(HashMap<String, String> codeTable) {
		this.codeTable = codeTable;
	}
	public AdjacentTable getAdjacent() {
		return adjacent;
	}
	public void setAdjacent(AdjacentTable adjacent) {
		this.adjacent = adjacent;
	}
	
	
}
