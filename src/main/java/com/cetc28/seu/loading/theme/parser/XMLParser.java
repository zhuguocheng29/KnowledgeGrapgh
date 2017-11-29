package com.cetc28.seu.loading.theme.parser;

import java.io.File;

import java.util.HashMap;

import java.util.Set;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Description : parser the xml relation to theme, get the map of themeName and themeClass
 * @author yaosheng
 *
 */
public class XMLParser {
	SAXReader reader=new SAXReader();
	HashMap<String,String> tagAndValue=new HashMap<>();	
	/**
	 * Description : parser all files in path
	 * @param path
	 * @param maps
	 */
	public void patchParse(String path,HashMap<String,String> maps){
		File root = new File(path);
		File[] files=root.listFiles();
		for(File file : files){
			XmlAloneFileParse(file,maps);
		}
	}
	
	
	/**
	 * Description : parser alone xml file
	 * @param file
	 * @param maps
	 */
	public void XmlAloneFileParse(File file,HashMap<String,String> maps){
		Document document;
		try {
			document = reader.read(file);
			Element nodes=document.getRootElement();
			Element subNameElement = nodes.element("subjectName");
			Element subClassElemenr=nodes.element("bizObject");
			if(subNameElement!=null && subClassElemenr!=null) maps.put(subNameElement.getText(), subClassElemenr.getText());
			else System.err.println("This file "+file.getName()+"'s format is error ! ");
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param file
	 * @param tags : get the value of every tag in tags
	 */
	public void XmlAloneFileParse(File file,String ... tags){
		Document document;
		try {
			document = reader.read(file);
			Element nodes=document.getRootElement();
			for(String tag : tags){
				Element node=nodes.element(tag);
				tagAndValue.put(tag, node.getText());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws DocumentException, ClassNotFoundException {
		HashMap<String,String> NameMapClass=new HashMap<>();
		XMLParser xmlp=new XMLParser();
		String filename="resource";
	//	File file=new File(filename);
		xmlp.patchParse(filename,NameMapClass);
		Set<String> names=NameMapClass.keySet();
		for(String name : names){
			System.out.println("sub name : "+name );
			System.out.println("sub class String : "+NameMapClass.get(name));
			System.out.println("sub class type : "+Class.forName(NameMapClass.get(name)));
		}
	}

}
