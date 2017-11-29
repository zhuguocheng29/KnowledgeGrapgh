package com.cetc.seu.theme;

import com.cetc28.seu.loading.theme.model.ClassRelation;
import com.cetc28.seu.loading.theme.model.Property;
import com.cetc28.seu.loading.theme.output.ParserWriter;

public class TestHbaseWriter implements ParserWriter{

	@Override
	public void writer(Property prop) {
		// TODO Auto-generated method stub
		System.out.print(prop.getOid());
		System.out.println(prop.getProperty());
	}

	@Override
	public void update(Property prop) {
		// TODO Auto-generated method stub
		
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
