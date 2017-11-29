package com.cetc28.seu.spark.query.model.indexhbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class CreateIndexXml {
	HashSet<String> hashSet;

	public CreateIndexXml(HashSet<String> set) {
		super();
		// TODO Auto-generated constructor stub
		hashSet = new HashSet<String>();
		hashSet.addAll(set);
	}
	
	public CreateIndexXml() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void create()
	{
		String dest = "resources\\index.xml";
		// 创建Document对象
        Document document = DocumentHelper.createDocument();
        // 创建根节点
        Element root = document.addElement("root");
        
        int i = 0;
        for(Iterator<?> iterator = hashSet.iterator(); iterator.hasNext();)
        {
        	
            // 创建Parameter子节点
            Element parameter = root.addElement("Parameter");
            // 设置id子节点
            Element id = parameter.addElement("id");
            id.addText(i+"");
            //设置element子节点
            Element element = parameter.addElement("element");
            element.addText((String) iterator.next());
            i++;
        }
        

        // 创建输出格式(OutputFormat对象)
        OutputFormat format = OutputFormat.createPrettyPrint();

        //设置输出文件的编码
        format.setEncoding("UTF-8");

        try {
            // 创建XMLWriter对象
            XMLWriter writer = new XMLWriter(new FileOutputStream(dest), format);

            //设置不自动进行转义
            writer.setEscapeText(false);

            // 生成XML文件
            writer.write(document);

            //关闭XMLWriter对象
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public boolean parser(String attribute)
	{
		//创建SAXReader对象  
        SAXReader reader = new SAXReader();  
        //读取文件 转换成Document  
        Document document = null;
		try {
			document = reader.read(new FileInputStream("resources\\index.xml"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (DocumentException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
        //获取根节点元素对象  
        Element root = document.getRootElement();  
        Iterator<Element> it = root.elementIterator();
        while(it.hasNext())
        {
        	Element e = it.next();
        	Iterator<Element> child = e.elementIterator();
        	while(child.hasNext())
        	{
        		Element e1 = child.next();
        		if(e1.getName().equals("element"))
        		{
        			if(e1.getData().equals(attribute))
        			{
        				return true;
        			}
        		}
        	}
        }
		return false;
	}
	public static void main(String[] args) throws FileNotFoundException, DocumentException
	{
//		HashSet<String> hashSet = new HashSet<String>();
//		hashSet.add("className");
//		hashSet.add("age");
//		hashSet.add("className:age");
		CreateIndexXml cix = new CreateIndexXml();
//		cix.create();
		System.out.println(cix.parser("className:age13"));
	}
}
