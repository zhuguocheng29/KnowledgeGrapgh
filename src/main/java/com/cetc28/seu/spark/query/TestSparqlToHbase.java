package com.cetc28.seu.spark.query;



import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.hadoop.hbase.client.HTableInterface;

import com.cetc28.seu.fromAntlr.BailErrorStrategy;
import com.cetc28.seu.fromAntlr.CustomListener;
import com.cetc28.seu.fromAntlr.ParserException;
import com.cetc28.seu.fromAntlr.SparqlLexer;
import com.cetc28.seu.fromAntlr.SparqlParser;
import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.spark.query.parser.CoprocessorQueryTreeGenerator;
import com.cetc28.seu.spark.query.result.ResultSet;



public class TestSparqlToHbase {
	public static void main(String[] args) {
//       QueryEngineOnSpark qEngine=new QueryEngineOnSpark("HbaseTest40000");
        //外部条件输入
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入查询语句");
//        String content = sc.nextLine();
       
//        String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//        		+ "SELECT ?s WHERE { ?s attributes:className zhu0 . ?s objects:parent ?o . ?o attributes:car CTS . ?o objects:parent ?p . ?p attributes:age 24 .}";

		//String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
       // 		+ "SELECT ?v ?s WHERE { ?s attributes:className zhu0 . ?s objects:parent ?o . ?o attributes:age 24 .}";

//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//        		+ "SELECT ?s WHERE { ?s attributes:className zhu0.}";
		
//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//        		+ "SELECT ?s WHERE { ?s attributes:className ?o.}";
		
//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//		+ "SELECT ?o ?s WHERE { ?s attributes:className zhu0 . ?s objects:parent ?o . ?o attributes:age 1000 .}";

//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//		+ "SELECT ?s WHERE { ?s attributes:age ?o FILTER(?o > 50)}";
		
//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//        		+ "SELECT ?s WHERE { ?s attributes:className zhu0 . ?s objects:parent ?o . ?o attributes:age 1000 .?s attributes:age ?v .}";

		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
        		+ "SELECT ?s WHERE {?s attributes:className zhu0 .}";
		
//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//        		+ "SELECT ?s WHERE { ?s attributes:className ?o.}";
		
//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//		+ "SELECT ?o ?s WHERE { ?s attributes:className zhu0 . ?s objects:parent ?o . ?o attributes:age 1000 .}";

//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//		+ "SELECT ?s WHERE { ?s attributes:age ?o FILTER(?o > 50)}";
		
//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//        		+ "SELECT ?s WHERE { ?s attributes:className zhu0 . ?s objects:parent ?o . ?o attributes:age 1000 .?s attributes:age ?v .}";

		long temp = System.currentTimeMillis();
		System.out.println("启动时间:" + temp);
		String tableName = Configution.instanceTableName;
    	HTableInterface hTable = null;
    	try {
			 hTable = HbaseTool.getInstance().getTable(tableName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("启动结束时间:" + (System.currentTimeMillis()-temp));

        try{

    		ANTLRInputStream input = new ANTLRInputStream(content);
            SparqlLexer lexer = new SparqlLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SparqlParser parser = new SparqlParser(tokens);
            
            parser.removeErrorListeners();
            parser.addErrorListener(new ParserException());
            parser.setErrorHandler(new BailErrorStrategy());

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
        }
        catch(Exception e){
        	System.err.println("请输入正确的SPARQL格式");
        }
        

        /**
         * Simple query(original)
         */
//        SimpleTreeGenerator stGeneration=new SimpleTreeGenerator();
//        sparqlBaseListener.getSelectClause().compile();
//        stGeneration.generate(sparqlBaseListener.getSelectClause());
//        QueryEngineOnSpark qEngine=new QueryEngineOnSpark("HbaseTest1000000");
//        qEngine.run(stGeneration.getTree());
        long start =System.currentTimeMillis();
        /**
         * Memory query
         */
//        MemoryQueryTreeGenerator mqGeneration = new MemoryQueryTreeGenerator();
//        sparqlBaseListener.getSelectClause().compile();
//        mqGeneration.generate(sparqlBaseListener.getSelectClause());
//        //QueryEngineOnSpark qEngine=new QueryEngineOnSpark("HbaseRegion3");
//        QueryEngineOnSpark qEngine=new QueryEngineOnSpark("HbaseRegion2Join");
//        qEngine.run(mqGeneration.getTree());
//        long end =System.currentTimeMillis();
//        System.out.println("sum time : "+(end-start));
        /**
         * Index query
         */
//        IndexQueryTreeGenerator itGeneration = new IndexQueryTreeGenerator();
//        sparqlBaseListener.getSelectClause().compile();
//        itGeneration.generate(sparqlBaseListener.getSelectClause());
//        QueryEngineOnSpark qEngine=new QueryEngineOnSpark();
//        qEngine.run(itGeneration.getTree());
        /**
         * hbase filter query
         */
//        FilterQueryTreeGenerator fqGeneration = new FilterQueryTreeGenerator();
//        sparqlBaseListener.getSelectClause().compile();
//        fqGeneration.generate(sparqlBaseListener.getSelectClause());
//        QueryEngineOnSpark qEngine=new QueryEngineOnSpark();
//        qEngine.run(fqGeneration.getTree());
	}
}
