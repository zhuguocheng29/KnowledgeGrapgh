package com.cetc28.seu.loading.theme.parser;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.cetc28.seu.loading.theme.model.EquivalentObject;
import com.cetc28.seu.loading.theme.model.Property;
import com.cetc28.seu.loading.theme.output.ParserWriter;


/**
 * Description : parse the nested obj
 * @author yaosheng
 */
public class ObjectParser extends Parser{
	//private int id;
	private String themeName;
	private ParserWriter writer;
	private static int id;
	public ObjectParser(ParserWriter writer) {
		super();
		//this.id=0;
		this.writer = writer;
	}
	
	private String constructRowId(long id, String themeName){
		String rowid=themeName+":"+String.valueOf(id);
		
		return rowid;
	}
	
	private String constructRowId(long id, String themeName, int cur) {
		String rowid=themeName+":"+String.valueOf(id)+":"+String.valueOf(cur);
		
		return rowid;
	}
	
	@SuppressWarnings("unused")
	private String construceRowId(long id, String themeName, int min,int max){//随机生成4位值使其均匀分布在 不同region
		Random random = new Random();
	    int s = random.nextInt(max)%(max-min+1) + min;
		String rowid=String.valueOf(s)+":"+themeName+":"+String.valueOf(id);
		return rowid;
	}
	
	/**
	 * @param obj
	 * @param type
	 * @param parent
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public int dfs(Object obj,Class<?> type,long parent) throws IllegalArgumentException, IllegalAccessException{
		
		Field[] attrs=type.getDeclaredFields();
		HashMap<String,String> arrayChild=new HashMap<>();
		HashMap<String,String> childObj=new HashMap<>();
		HashMap<String,String> basePro=new HashMap<>();
		
		//先构造出所有基础属性,已便生成id(通过hashcode)
		for(Field attr: attrs){
			attr.setAccessible(true);
			Class<?> attrtype = attr.getType();
			Object attrValue=attr.get(obj);
			String attrname=attr.getName();
			//System.out.println("attrvalue: " + attrValue);
			if(isArray(attrtype)&& isBaseArray(attrtype)){
				String value="";
				for(int i=0;i<Array.getLength(attrValue);i++){
					value=value+Array.get(attrValue, i)+" ";
				}
				basePro.put(attrname, value);
			}else if(isList(attrtype)){
				ParameterizedType pt = (ParameterizedType) attr.getGenericType() ;
				Class<?> clz = (Class<?>) pt.getActualTypeArguments()[0];
				List<?> listValue=(List<?>) attrValue;
				if(!isObject(clz)){
					String value="";
					for(Object ev : listValue){
						value=value+String.valueOf(ev)+" ";
					}
					basePro.put(attrname, value);
				}
			}else if(!isObject(attrtype)&& (!isArray(attrtype))){
				basePro.put(attrname, String.valueOf(attrValue));
			}
		}
		
		int cur=hashcode(basePro,type.getSimpleName());
		int curid=id;
		for(Field attr : attrs){//遍历所有属性
			attr.setAccessible(true);
			Class<?> attrtype = attr.getType();
			String attrname=attr.getName();
			Object attrValue=attr.get(obj);
			if(attrValue==null) continue;
			if(isArray(attrtype)){//如果是数组
				if(!isBaseArray(attrtype)){//如果是对象数组
					Object[] arrayValue=(Object[]) attrValue;
					String value="";
					for(Object everyObj : arrayValue){
						id++;//TODO
						int childId=dfs(everyObj,everyObj.getClass(),cur);
						
						String idTheme=constructRowId(childId,themeName,id);
						value=value+idTheme+" ";
					}
					arrayChild.put(attrname, value);
				}else{//如果是基础类型的数组
					/*String value="";
					for(int i=0;i<Array.getLength(attrValue);i++){
						value=value+Array.get(attrValue, i)+" ";
					}
					basePro.put(attrname, value);*/
				}
			}else if(isList(attrtype)){//如果是List
				ParameterizedType pt = (ParameterizedType) attr.getGenericType() ;
				Class<?> clz = (Class<?>) pt.getActualTypeArguments()[0];
				List<?> listValue=(List<?>) attrValue;
				if(isObject(clz)){//list中保存的是对象
					String value="";
					for(Object listObj : listValue){
						id++;
						int childId=dfs(listObj,clz,cur);
						
						String idTheme=constructRowId(childId,themeName,id);
						value=value+idTheme+" ";
					}
					arrayChild.put(attrname, value);
				}
				else{//保存的是基础类型
					/*String value="";
					for(Object ev : listValue){
						value=value+String.valueOf(ev)+" ";
					}
					basePro.put(attrname, value);*/
				}
			}else if(isObject(attrtype)){// 该属性是对象
				Object child=attr.get(obj);
				id++;
				int childid=dfs(child,attrtype,cur);
				
				childObj.put(attrname, constructRowId(childid,themeName,id));
			}
			else {
			/*	basePro.put(attrname, String.valueOf(attrValue));*/
			}
		}
		Property prop=new Property(basePro, constructRowId(parent,themeName,cur), childObj, arrayChild,constructRowId(cur,themeName,curid),type.getSimpleName());
		
		
		writer.writer(prop);
		System.out.println("prop111: " + prop);
		
		
		return cur;
	}
	
	public void extractTreeObj(List<Object> objs,Class<?> type,String themeName) {
		this.themeName=themeName;
		for(Object obj : objs){
			try {
				dfs(obj,type,-1);
				id++;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		writer.flush();
	}
	public void writerToHbase(Property prop,String themeName){
		System.out.println("oid : " +prop.getOid()+" parentid : " +prop.getParent()+" type : "+prop.getType());

	}
	public void updateToHbase(Property prop){
		// 1. 查出 Hbase 中相同的prop 
		// 2. 修改 该 prop中的  sameAs 列
		// 3. 将该prop写入 Hbase
		System.out.println("need updated object : ");
		
		System.out.println("\n\n");
	}
	
	private int hashcode(HashMap<String,String> basePro, String type){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basePro== null) ? 0 : basePro.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
}
