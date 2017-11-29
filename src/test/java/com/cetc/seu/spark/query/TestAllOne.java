package com.cetc.seu.spark.query;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.hadoop.hbase.client.HTableInterface;

import com.cetc28.seu.fromAntlr.CustomListener;
import com.cetc28.seu.fromAntlr.ParserException;
import com.cetc28.seu.fromAntlr.SparqlLexer;
import com.cetc28.seu.fromAntlr.SparqlParser;
import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.loading.theme.model.EntityInfo;
import com.cetc28.seu.spark.query.QueryEngineOnSpark;
import com.cetc28.seu.spark.query.model.coprocessor.SearchKeyWordByNameEntity;
import com.cetc28.seu.spark.query.parser.CoprocessorQueryTreeGenerator;
import com.cetc28.seu.spark.query.result.ResultSet;

public class TestAllOne {

	public static void main(String[] args) {
		String tableName = Configution.instanceTableName;
    	HTableInterface hTable = null;
    	try {
			 hTable = HbaseTool.getInstance().getTable(tableName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Input: ");
    	while(sc.hasNextLine()){
    		String str = sc.nextLine();
    		if(str.contains("PREFIX")){
    			String content = str;
    			ANTLRInputStream input = new ANTLRInputStream(content);
    	        SparqlLexer lexer = new SparqlLexer(input);
    	        CommonTokenStream tokens = new CommonTokenStream(lexer);
    	        SparqlParser parser = new SparqlParser(tokens);
    	    	try{
    	            parser.removeErrorListeners();
    	            parser.addErrorListener(new ParserException());
    	            //parser.setErrorHandler(new BailErrorStrategy());
    	        }catch(Exception e){
    	        	System.err.println("请输入正确的SPARQL格式");
    	        }
    	        ParseTree tree = parser.query(); // begin parsing at query rule
    	        
    	        ParseTreeWalker walker = new ParseTreeWalker();
    	        CustomListener sparqlBaseListener = new CustomListener(parser);
    	        walker.walk(sparqlBaseListener, tree);

    	        /**
    	         * coprocessor query
    	         */
    	        CoprocessorQueryTreeGenerator cqGeneration = new CoprocessorQueryTreeGenerator();
    	        sparqlBaseListener.getSelectClause().compile();
    	        cqGeneration.generate(sparqlBaseListener.getSelectClause());
    	        QueryEngineOnSpark qEngine=new QueryEngineOnSpark();
    	        ResultSet rs = qEngine.coprocessorRun(cqGeneration.getTree());
    	        rs.show(100);

    		}else{
    			try {
    				if(!HbaseTool.getAdmin().tableExists(tableName)){
    					String[] families = {"objects","attributes","array_objects","index"};
    					HbaseTool.createTable(tableName,families);
    				}

    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			SearchKeyWordByNameEntity si = new SearchKeyWordByNameEntity();
    			String nameEntity = str;
    			List<String> subjectNames = new ArrayList<String>();
    			Scanner sc1 = new Scanner(System.in);
    			while(sc1.hasNextLine()){
    				subjectNames.add(sc1.nextLine());	
    			}
    			Long start = System.currentTimeMillis();
    			List<EntityInfo> list = si.search(nameEntity, subjectNames);
    			Long end = System.currentTimeMillis();
    			System.out.println("pay time: " + (end - start));
    			for(EntityInfo en:list){
    				System.out.println(en.getNm());
    				System.out.println(en.getDetails());
    			}
    		}
    		System.out.println();
    		System.out.println("input:");
    	}
	}

}
