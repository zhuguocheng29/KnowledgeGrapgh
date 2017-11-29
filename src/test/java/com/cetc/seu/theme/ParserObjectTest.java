package com.cetc.seu.theme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import com.cetc28.seu.loading.theme.output.ParserWriter;
import com.cetc28.seu.loading.theme.parser.ObjectParser;


import junit.framework.TestCase;

public class ParserObjectTest extends TestCase{
	@Test
	public void testParse() throws IOException
	{

		ParserWriter  testWriter1 = new testWriter();
		ObjectParser objectParser = new ObjectParser(testWriter1);
		
		NestedClass nestedClass = new NestedClass();
		nestedClass.setChildName("child");
		nestedClass.setChildAge(1);
		nestedClass.setCountry("China");
		//parent1
		ParentClass parentClass = new ParentClass();
		parentClass.setParentName("parent1");
		parentClass.setParentAge(30);
		parentClass.setChild(nestedClass);
		//parent2
		ParentClass parentClass2 = new ParentClass();
		parentClass2.setParentName("parent2");
		parentClass2.setParentAge(26);
		parentClass2.setChild(nestedClass);
		
		List<Object> object = new ArrayList<Object>();
		object.add(parentClass);
		object.add(parentClass2);
		
		objectParser.extractTreeObj(object, parentClass.getClass(), "instancesV2");
		//System.out.println("type: " + parentClass.getClass().toString());
		
		
	}

}
