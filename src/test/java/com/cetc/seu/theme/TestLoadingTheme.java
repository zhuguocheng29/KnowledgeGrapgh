package com.cetc.seu.theme;

import java.util.ArrayList;
import java.util.List;

import com.cetc28.seu.loading.theme.parser.ObjectParser;

public class TestLoadingTheme {
	public static void main(String args[]){
		List<Object> objects=new ArrayList<>();
		Xxjhgg xxjhgg=new Xxjhgg();
		xxjhgg.setMesssageid("xxjhgg2");
		XXjhSender xxjhsender=new XXjhSender();
		xxjhsender.setSender("yaosheng");
		XXjhReciver xxjhreciver=new XXjhReciver();
		xxjhreciver.setReciver("shijun");
		xxjhgg.setXxjhreciver(xxjhreciver);
		xxjhgg.setXxjhsender(xxjhsender);
		objects.add(xxjhgg);
		//objects.add(xxjhsender);
		//objects.add(xxjhreciver);
		TestHbaseWriter thw=new TestHbaseWriter();
		ObjectParser op=new ObjectParser(thw);
		op.extractTreeObj(objects, Xxjhgg.class, "");
	}
}
