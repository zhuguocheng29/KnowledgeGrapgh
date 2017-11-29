package com.cetc28.seu.reasoner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


/**
 * TODO 邻接表的建立
 * @author Think
 *
 */
public class AdjacentTable {
	
	private List<ArrayNode> listArrayNode;
	private HashMap<String,String> hashMap; //用于遍历子类，返回 值和前缀编码，key为值，value为编码
	private HashMap<String,String> hashParent; //用于遍历父类，返回 值和前缀编码，key为值，value为编码
	private Map<String,ArrayList<String>> returnVar; //返回值, key为字符串，value为prefix列表
	public AdjacentTable()
	{
		listArrayNode = new ArrayList<ArrayNode>();
		hashMap = new HashMap<String,String>(); 
		hashParent = new HashMap<String,String>(); 
		returnVar = new ConcurrentHashMap<String,ArrayList<String>>();
	}
	
	public Map<String, ArrayList<String>> getReturnVar() {
		return returnVar;
	}



	public void setReturnVar(Map<String, ArrayList<String>> returnVar) {
		this.returnVar = returnVar;
	}


	public List<ArrayNode> getListArrayNode() {
		return listArrayNode;
	}


	public void setListArrayNode(List<ArrayNode> listArrayNode) {
		this.listArrayNode = listArrayNode;
	}


	public HashMap<String, String> getHashMap() {
		return hashMap;
	}


	public void setHashMap(HashMap<String, String> hashMap) {
		this.hashMap = hashMap;
	}


	public HashMap<String, String> getHashParent() {
		return hashParent;
	}


	public void setHashParent(HashMap<String, String> hashParent) {
		this.hashParent = hashParent;
	}


	/**
	 * TODO 构建邻接表
	 * @param vertexs
	 * @param edges
	 */
	public void constructAdjacentTable(List<String> vertexs, List<HashMap<String,String>> edges)
	{
		int vertexsNum = vertexs.size();
		int edgesNum = edges.size();
		
		for(int i = 0; i < vertexsNum; i++)
		{
			ArrayNode aNode = new ArrayNode();
			aNode.setData(vertexs.get(i));
			aNode.setFirstNode(null);
			aNode.setParentNode(null);
			listArrayNode.add(aNode);
		}
		
		for(int j = 0; j < edgesNum; j++)
		{
			for(Entry<String, String> entry : edges.get(j).entrySet())
			{
				int positionBegin = getPosition(entry.getKey());
				int positionEnd = getPosition(entry.getValue());
				
				ListNode lNode = new ListNode();
				lNode.setId(positionEnd);
				lNode.setValue(entry.getValue());
				
				String prefix = CreateMD5.getMd5(lNode.getValue());
				if(listArrayNode.get(positionBegin).getFirstNode() == null)
				{
					if(listArrayNode.get(positionBegin).getPrefix() == null)
					{
						String listPrefix = CreateMD5.getMd5(listArrayNode.get(positionBegin).getData());
						listArrayNode.get(positionBegin).setPrefix(listPrefix);
						//初始时添加returnVar
						if(returnVar != null)
						{
							if(returnVar.size() == 0)
							{
								ArrayList<String> firstValue = new ArrayList<String>();
								String key = listArrayNode.get(positionBegin).getData();
								String value = listArrayNode.get(positionBegin).getPrefix();
								firstValue.add(value);
								returnVar.put(key, firstValue);
							}
							else
							{
								putReturnVar(listArrayNode.get(positionBegin).getData(),listArrayNode.get(positionBegin).getPrefix());
							}
						}
					}
					lNode.setPrefix(listArrayNode.get(positionBegin).getPrefix()+prefix);
					//在链表中检索，然后添加到renturnVar
					if(returnVar.size() != 0)
					{
						putReturnVar(lNode.getValue(),lNode.getPrefix());
					}
					addTablePrefix(entry.getValue(),listArrayNode.get(positionBegin).getPrefix()+prefix,positionBegin);
					listArrayNode.get(positionBegin).setFirstNode(lNode);
				}
				else
				{
					//setLink(listArrayNode.get(positionBegin), lNode, prefix, entry.getValue());
					setLink(listArrayNode.get(positionBegin), lNode, prefix, positionBegin);
				}
			}
			
			
		}
	}
	
	public void putReturnVar(String nodeValue, String nodePrefix)
	{
		boolean ljudge = false;
		for(Entry<String, ArrayList<String>> compEntry : returnVar.entrySet())
		{
			//若字符相等，则相同字符串下添加前缀
			if(compEntry.getKey().equals(nodeValue))
			{
				ArrayList<String> tempValue = compEntry.getValue();
				String value = nodePrefix;
				tempValue.add(value);
				returnVar.put(compEntry.getKey(), tempValue);
				ljudge = true;
				break;
			}
		}
		if(!ljudge)
		{
			ArrayList<String> firstValue = new ArrayList<String>();
			String key = nodeValue;
			String value = nodePrefix;
			firstValue.add(value);
			returnVar.put(key, firstValue);				
		}
		
	}
	
	
	
	
	/**
	 * TODO 遍历父类
	 * @param value
	 */
	public void traverseParent(String value)
	{
		int size = listArrayNode.size();
		for(int i = 0; i < size; i++)
		{
			if(listArrayNode.get(i).getData().equals(value))
			{
				if(listArrayNode.get(i).getParentNode() == null)
				{
					hashParent.put(listArrayNode.get(i).getData(),listArrayNode.get(i).getPrefix());
				}
				else
				{
					ArrayNode aNode = listArrayNode.get(i).getParentNode();
					while(aNode != null)
					{
						hashParent.put(aNode.getData(), aNode.getPrefix());
						aNode = aNode.getParentNode();
					}
				}
			}
		}
	}
		
	
	
	/**
	 * 遍历得到相对应的子类
	 * @param value
	 */
	public void traverse(String value)
	{
		int size = listArrayNode.size();
		for(int i = 0; i < size; i++)
		{
			if(listArrayNode.get(i).getData().equals(value))
			{
				if(listArrayNode.get(i).getFirstNode() == null)
				{
					
					//System.out.println("sorry no prefix");
					hashMap.put(listArrayNode.get(i).getData(), listArrayNode.get(i).getPrefix());
				}
				else
				{
					List<ListNode> nodeList = new ArrayList<ListNode>();
					ListNode lNode = listArrayNode.get(i).getFirstNode();
					nodeList.add(lNode);
					hashMap.put(lNode.getValue(), lNode.getPrefix());
//					System.out.println(lNode.getValue());
//					System.out.println(lNode.getPrefix());
					while(lNode.getNextNode() != null)
					{
						lNode = lNode.getNextNode();
						nodeList.add(lNode);
						hashMap.put(lNode.getValue(), lNode.getPrefix());
//						System.out.println(lNode.getValue());
//						System.out.println(lNode.getPrefix());
					}
					for(ListNode node: nodeList)
					{
						traverse(node.getValue());
					}
				
				}
			}
		}
	}
	
	/**
	 * TODO 字符前缀编码，一层为5位
	 * @return 返回字符编码
	 */
	public String getPrefix()
	{
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int randomNumber = 0; randomNumber < 5; randomNumber++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    String row_key = sb.toString();
	    return row_key;
	}
	
	/**
	 * TODO 链表的建立
	 * @param aNode
	 * @param now
	 * @param prefix
	 * @param positionBegin
	 */
	public void setLink(ArrayNode aNode, ListNode now, String prefix, int positionBegin)
	{
		ListNode firstListNode = aNode.getFirstNode();
		String firstPrefix = aNode.getPrefix();
		while(firstListNode.getNextNode() != null)
		{
			firstListNode = firstListNode.getNextNode();
		}
		
		now.setPrefix(firstPrefix+prefix);
		
		//在链表中检索，然后添加到renturnVar
		if(returnVar.size() != 0)
		{
			putReturnVar(now.getValue(),now.getPrefix());		
		}
		
		addTablePrefix(now.getValue(),firstPrefix+prefix,positionBegin);
		firstListNode.setNextNode(now);
	}
	
	/**
	 * TODO 获得该值的顶点的位置
	 * @param value
	 * @return
	 */
	public int getPosition(String value)
	{
		for(int i = 0; i < listArrayNode.size(); i++)
		{
			if(listArrayNode.get(i).getData().equals(value))
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 给顶点添加前缀
	 * @param value
	 * @param prefix
	 * @param positionBegin
	 */
	public void addTablePrefix(String value, String prefix, int positionBegin)
	{
		for(int i = 0; i < listArrayNode.size(); i++)
		{
			if(listArrayNode.get(i).getData().equals(value))
			{
				listArrayNode.get(i).setPrefix(prefix);
				listArrayNode.get(i).setParentNode(listArrayNode.get(positionBegin));
			}
		}
	}
	
	/**
	 * TODO 打印所有子类
	 */
	public void printSubClass()
	{
		for(int i = 0; i < listArrayNode.size() ;i++)
		{
			System.out.println("node: " + listArrayNode.get(i).getData());
			System.out.println("node prefix: " + listArrayNode.get(i).getPrefix());
			ListNode lNode = listArrayNode.get(i).getFirstNode();
			while(lNode != null)
			{
				System.out.println("node value: " + lNode.getValue());
				System.out.println("node prefix: " + lNode.getPrefix());
				lNode = lNode.getNextNode();
			}
			System.out.println();
		}
		
		
	}
	
	/**
	 * TODO 打印所有父类
	 */
	public void printParent()
	{
		for(int i = 0; i < listArrayNode.size() ;i++)
		{
			System.out.println("node: " + listArrayNode.get(i).getData());
			System.out.println("node prefix: " + listArrayNode.get(i).getPrefix());
			ArrayNode aNode = listArrayNode.get(i).getParentNode();
			while(aNode != null)
			{
				System.out.println("parent: " + aNode.getData());
				aNode = aNode.getParentNode();
			}
			System.out.println();
		}
	}

}

