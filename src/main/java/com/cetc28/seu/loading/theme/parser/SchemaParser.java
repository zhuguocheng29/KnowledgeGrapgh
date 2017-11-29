package com.cetc28.seu.loading.theme.parser;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Set;

import com.cetc28.seu.loading.theme.model.ClassRelation;
import com.cetc28.seu.loading.theme.output.ParserWriter;






/**
 * Description : the schema of theme's parse
 * @author yaosheng
 *
 */
public class SchemaParser extends Parser{
	private String themeName="";
	private int id=0;
	private ParserWriter writer;
	
	public SchemaParser(ParserWriter writer){
		this.writer=writer;
	}
	/**
	 * Description : use dfs search all  classes
	 * @param type
	 * @param curClassName
	 * @param parent
	 */
	public void dfs(Class<?> type,String curClassName,String parent){
		int cur=id;
		HashMap<String,String> objects=new HashMap<>();
		HashMap<String, String> baseProperty=new HashMap<>();
		HashMap<String,String> ArrayObject=new HashMap<>();
		Field[] attrs=type.getDeclaredFields();
		String className=type.getSimpleName();
	
		for(Field attr : attrs){ //遍历所有属性
			Class<?> childType=attr.getType();
			String propName=attr.getName();
			if(isObject(childType)&& !isArray(childType)&& !isList(childType)){//该属性是对象
				objects.put(className, childType.getSimpleName());
				id++;
				dfs(childType,className,curClassName);
			}
			else if(isArray(childType)){//该属性是数组
				Class<?> parttype=childType.getComponentType();
				if(!isObject(parttype)){
					baseProperty.put(attr.getName(), parttype.getSimpleName());
				}else{
					ArrayObject.put(className, parttype.getSimpleName());
					id++;
					dfs(childType,className,curClassName);
				}
			}
			else if(isList(childType)){//该属性是List
				ParameterizedType pt = (ParameterizedType) attr.getGenericType() ;
				Class<?> clz = (Class<?>) pt.getActualTypeArguments()[0];
				if(isObject(clz)){
					ArrayObject.put(className, clz.getSimpleName());
					id++;
					dfs(childType,className,curClassName);
				}else{
					baseProperty.put(className, clz.getSimpleName());
				}
			}
			else{//该属性是基础类型
			
				String typeName=childType.getSimpleName();
				baseProperty.put(propName, typeName);
			}
		}
		
		ClassRelation cr=new ClassRelation(className, objects, ArrayObject, parent, baseProperty, cur ,themeName);
		writer.writer(cr);;
	}
	
	public void writerToHbase(ClassRelation cr,String themeName){
		Set<String> keys=cr.getBaseProperty().keySet();
		for(String key : keys){
			System.out.println("key: "+key);
			System.out.println("comments : "+cr.getBaseProperty().get(key));
		}
	}
	
	
	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	
}
