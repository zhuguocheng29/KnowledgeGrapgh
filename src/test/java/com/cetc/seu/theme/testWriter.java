package com.cetc.seu.theme;

import com.cetc28.seu.loading.theme.model.ClassRelation;
import com.cetc28.seu.loading.theme.model.Property;
import com.cetc28.seu.loading.theme.output.ParserWriter;

public class testWriter implements ParserWriter{

	@Override
	public void writer(Property prop) {
		System.out.println("writering");
		System.out.println("prop : " + prop.getProperty().toString());
	}
	@Override
	public void update(Property prop) {
		System.out.println("updating");
		System.out.println("prop : " + prop.getProperty().toString());
	}
	@Override
	public void writer(ClassRelation cr) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

}
