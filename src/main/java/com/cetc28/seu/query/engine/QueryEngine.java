package com.cetc28.seu.query.engine;


import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cetc28.seu.hbase.HBaseReader;
import com.cetc28.seu.query.input.QueryConsoleInput;
import com.cetc28.seu.query.parser.ConstructQueryPlan;
import com.cetc28.seu.query.parser.SentenceParser;
import com.cetc28.seu.query.struct.QueryPlan;
import com.cetc28.seu.rdf.RDF;

public class QueryEngine {
	HBaseReader reader;
	public QueryEngine(String table_name) {
		reader=new HBaseReader(table_name, null);
	}

	/**
	 */
	public void run(){
		QueryConsoleInput qcI=new QueryConsoleInput();
		String sql=qcI.read();
		while(sql!=null && sql!=""){
			List<RDF> rdfs=SentenceParser.parser(sql);
			QueryPlan pl=ConstructQueryPlan.plan(rdfs);
			List<Map<String, String>> results=reader.getQueryByPlan(pl);
			this.show(results);
		}
		
	}
	
	/**
	 * @param results
	 */
	private void show( List<Map<String, String>> results){
		for(Map<String,String> result : results){
			for( Entry<String, String> col : result.entrySet()){
				 System.out.println(col.getKey()+" : "+col.getValue()+"\t");
			}
		}
	}
	public static void  main(String args[]){
		QueryEngine qe=new QueryEngine("Test");
		qe.run();
	}
}
