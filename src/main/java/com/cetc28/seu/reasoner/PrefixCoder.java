package com.cetc28.seu.reasoner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;


/**
 * Description: 父子类关系前缀编码
 * @author Think
 *
 */


public class PrefixCoder {
	
	private Set<String> listArray;
	private List<HashMap<String,String>> lTable; //key为parent,value为child
	private AdjacentTable adjTable;
	
	public PrefixCoder(String path) {
		super();
		listArray = new LinkedHashSet<String>();
		lTable =  new ArrayList<HashMap<String,String>>();
		parseByJena(path);
		List<String> list = transSetToList(listArray);
		adjTable = new AdjacentTable();
		adjTable.constructAdjacentTable(list, lTable);
	}
	
	
/*	public static void splitString(List<HashMap<String,String>> lTable, Set<String> listArray, String test)
	{
		String[] relation = test.split(">");
		HashMap<String,String> hashMap = new HashMap<String,String>();
		hashMap.put(relation[1]+">", relation[0]+">");
		lTable.add(hashMap); 
		listArray.add(relation[1]+">");
		listArray.add(relation[0]+">");

		
	}*/

	public AdjacentTable getAdjTable() {
		return adjTable;
	}


	public void setAdjTable(AdjacentTable adjTable) {
		this.adjTable = adjTable;
	}


	//set转list
	public static List<String> transSetToList(Set<String> listArray)
	{
		List<String> list = new ArrayList<String>();
		for(String array : listArray)
		{
			list.add(array);
		}
		return list;
	}

	//"D:/glossary.owl"
	public void parseByJena(String path)
	{
	    OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);  
	    try {
			ontModel.read(new FileInputStream(path),"","TTL");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	    for(Iterator<?> i = ontModel.listClasses(); i.hasNext();)
	    {
	    	OntClass c = (OntClass) i.next();	//返回类型强制转换
	    	String child = "<" + c.getURI() + ">";
	    	//System.out.println("child: " + child);
	    	listArray.add(child);
	    	if(c.listSuperClasses() != null && c.listSuperClasses().hasNext())
	    	{
	    		for(Iterator<?> it = c.listSuperClasses(); it.hasNext();)
	    		{
	    			OntClass sp = (OntClass) it.next();
	    			String parent = "<" + sp.getURI() + ">";
	    	    	//System.out.println("parent: " + parent);
  	    			listArray.add(parent);
  	    			HashMap<String,String> hashMap = new HashMap<String,String>();
  	    			System.out.println(child + " subclass: " + parent);
	    			hashMap.put(parent, child);
	    			lTable.add(hashMap);
	    		}
	    	}
	    	
	    	/*if(c.listSuperClasses() != null)	//如果不是匿名类
	    	{
	    		//迭代显示当前类的直接父类
	    		for(Iterator<?> it = c.listSuperClasses(); it.hasNext();)
	    		{
	    			OntClass sp = (OntClass) it.next();
	    			String parent = "<" + sp.getURI() + ">";
  	    			listArray.add(parent);
	    			//迭代得到属性
		    		StmtIterator iterator = c.listProperties();
		    		while(iterator.hasNext())
		    		{
		    			Statement statement = iterator.next();
		    			String child = "<" + statement.getSubject().toString() + ">";
		    			listArray.add(child);
		    			HashMap<String,String> hashMap = new HashMap<String,String>();
		    			hashMap.put(parent, child);
		    			lTable.add(hashMap);
		    			//System.out.println("predicate: "+statement.getPredicate().toString().substring(statement.getPredicate().toString().indexOf("#")+1));
		    			break;
		    		}
	    		}*/
	    		/*//迭代显示当前类的直接子类
	    		for(Iterator<?> it = c.listSubClasses(); it.hasNext();)
	    		{
	    			OntClass sb = (OntClass) it.next();
	    			//System.out.println("subclass: "+sb.getModel().getGraph().getPrefixMapping().shortForm(sb.getURI().substring(1)));
	    			System.out.println("subclass: " + sb.getURI());

	    		}*/
	    	/*	//迭代得到属性
	    		StmtIterator iterator = c.listProperties();
	    		while(iterator.hasNext())
	    		{
	    			Statement statement = iterator.next();
	    			System.out.println("subject: " + statement.getSubject().toString());
	    			//System.out.println("predicate: "+statement.getPredicate().toString().substring(statement.getPredicate().toString().indexOf("#")+1));
	    			//System.out.println("objects: " +statement.getObject().toString());
	    			break;
	    		}
	    	}*/
	    }
	}

}
