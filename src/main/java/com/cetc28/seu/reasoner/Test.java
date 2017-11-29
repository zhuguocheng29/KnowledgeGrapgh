package com.cetc28.seu.reasoner;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrefixCoder prefixCoder = new PrefixCoder("glossary.owl");
/*		List<ArrayNode> arrayNode = prefixCoder.getAdjTable().getListArrayNode();
		for(ArrayNode aNode : arrayNode)
		{
			System.out.println(aNode.getData() + " " + aNode.getPrefix());
		}*/
		Map<String, ArrayList<String>> hashMap = prefixCoder.getAdjTable().getReturnVar();
		for(Entry<String, ArrayList<String>> entry : hashMap.entrySet())
		{
			String key = entry.getKey();
			for(String value : entry.getValue())
			{
				System.out.println("key: " + key + " value: " + value);
				
			}
		}
	}

}
