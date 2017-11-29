package com.cetc.seu.spark.query;

import java.io.IOException;
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
import com.cetc28.seu.spark.query.QueryEngineOnSpark;
import com.cetc28.seu.spark.query.parser.CoprocessorQueryTreeGenerator;
import com.cetc28.seu.spark.query.result.ResultSet;

public class KG_Demo {

	public static void main(String[] args) {
			//测试用，添加数据和表
//			putDemoData();
			String tableName = Configution.instanceTableName;
	    	HTableInterface hTable = null;
	    	try {
				 hTable = HbaseTool.getInstance().getTable(tableName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	while(1 != 0){
	    		System.out.println("请输入Sparql语句：");
	    		Scanner sc = new Scanner(System.in);
		    	String content = sc.nextLine();

		    	//PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#> SELECT ?s WHERE {?s attributes:职业 篮协主席 .}
		    	//PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#> SELECT ?o WHERE {?s attributes:职业 篮球运动员 . ?s objects:队友 ?o . ?o attributes:职业 篮协主席 .}
		    	//PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#> SELECT ?s WHERE {?s attributes:职业 ?o .}

//		    	String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//		        		+ "SELECT ?s WHERE {?s attributes:职业 篮协主席 .}";
//		    	String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//		        		+ "SELECT ?o WHERE {?s attributes:职业 篮球运动员 . ?s objects:队友 ?o . ?o attributes:职业 篮协主席 .}";
//		    	String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//		        		+ "SELECT ?s WHERE {?s attributes:职业 ?o .}";

					ANTLRInputStream input = new ANTLRInputStream(content);
			        SparqlLexer lexer = new SparqlLexer(input);
			        CommonTokenStream tokens = new CommonTokenStream(lexer);
			        SparqlParser parser = new SparqlParser(tokens);
			    	try{
			            parser.removeErrorListeners();
			            parser.addErrorListener(new ParserException());
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
			        System.out.println();
	    	}
	    	
		}
		
		public static void putDemoData(){
			String tableName = Configution.instanceTableName;
	    	try {
				if(!HbaseTool.getAdmin().tableExists(tableName)){
					String[] families = {"objects","attributes","array_objects","index"};
					HbaseTool.createTable(tableName,families);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    	HTableInterface hTable = null;
	    	try {
				 hTable = HbaseTool.getInstance().getTable(tableName);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    	//添加小部分数据进行试验

			HbaseTool.getInstance().putDemo();			
			System.out.println("start build index");
			HbaseTool.getInstance().buildIndex();
			System.out.println("finished");
		}

}
