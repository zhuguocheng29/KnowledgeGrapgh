package com.cetc28.seu.spark.query.model.coprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.spark.query.model.InnerNode;
import com.cetc28.seu.spark.query.result.LocalResultSet;
import com.cetc28.seu.spark.query.result.ResultSet;

enum  returnValueLocal{left,right};// 表示返回值
public class JoinerCoprocessorNode extends InnerNode{

	private static final long serialVersionUID = -5515459951672424727L;
	private final static String family = "objects";
	private String joinColumn;
	private returnValueLocal returnV;
	private static int count = 0;
	
	public  JoinerCoprocessorNode() {
		
	}
	
	public JoinerCoprocessorNode(int id, int parent, Term term, String joinColumn) {
		super(id, -1, -1, parent, term);
		this.joinColumn=joinColumn;
	}

	public JoinerCoprocessorNode(int id, int parent, Term childTerm, RDF childRDF, String joinColumn) {
		super(id, -1, -1, parent, childTerm);
		constructReturn(childRDF);
		this.joinColumn=joinColumn;
	}

	public void constructReturn(RDF rdf){
		//System.out.println("subject: " + rdf.getSubject().getValue());
		//System.out.println("Term: " + this.getTerm().getValue());
		if(this.getTerm().getValue().equals(rdf.getSubject().getValue())){
			returnV=returnValueLocal.left;
		}else{
			returnV=returnValueLocal.right;
		}
	}
	
	@Override
	public ResultSet query() {
		// TODO local query join using hashMap
		// 1. read from the left (subject) , save to a hashmap
		LocalResultSet locRes=new LocalResultSet();
		List<Result> res=new ArrayList<>();
		LocalResultSet subject=(LocalResultSet) this.getLelfInputResult();
		List<Result> subjectResults=subject.getResults();
		HashMap<String,Result> maps=new HashMap<>();
		//System.out.println(count++);
		//System.out.println(this.joinColumn);
		//System.out.println(returnV.toString());

		
		for(Result result : subjectResults){
			//System.out.println("sub: " + Bytes.toString(result.getValue(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"))));
			byte[] key=result.getValue(Bytes.toBytes(family), Bytes.toBytes(joinColumn));
			maps.put(Bytes.toString(key),result);
		}
		List<Result> objectResult=((LocalResultSet) this.getRightInputResult()).getResults();
		if(objectResult.size() == 0){
			if(returnV.equals(returnValueLocal.left)){
				for(Result r : maps.values()){
					res.add(r);
				}
			}else{
				try {
					HTableInterface hTable = HbaseTool.getInstance().getTable(Configution.instanceTableName);
					for(String s: maps.keySet()){
						if(s == null || s.equalsIgnoreCase("null") || s.equals("Null") || s.equals("") )
							continue;
						Get get = new Get(Bytes.toBytes(s));
						Result r = hTable.get(get);
						res.add(r);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			locRes.setResults(res);
			return locRes;
		}
		
		boolean flag=false;
		for(Result result : objectResult){
			//System.out.println("obj: " + Bytes.toString(result.getValue(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"))));
			String key = Bytes.toString(result.getRow());
			if(maps.containsKey(key)){
				if(flag||returnV.equals(returnValueLocal.left)){
					flag=true;
					res.add(maps.get(key));
				}else{
					res.add(result);
				}
			}
		}
		locRes.setResults(res);
		return locRes;
	}
	
	
	public String getJoinColumn() {
		return joinColumn;
	}


	public void setJoinColumn(String joinColumn) {
		this.joinColumn = joinColumn;
	}

	public returnValueLocal getReturnV() {
		return returnV;
	}

	public void setReturnV(returnValueLocal returnV) {
		this.returnV = returnV;
	}

}
