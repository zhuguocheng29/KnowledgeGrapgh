package com.cetc.seu.spark.query;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.cetc28.seu.fromAntlr.CustomListener;
import com.cetc28.seu.fromAntlr.SparqlLexer;
import com.cetc28.seu.fromAntlr.SparqlParser;
import com.cetc28.seu.spark.query.QueryEngineOnSpark;
import com.cetc28.seu.spark.query.parser.SimpleTreeGenerator;


public class TestSparqlToHbase {
	public static void main(String[] args) {
//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//        		+ "SELECT ?v ?s WHERE { ?s attributes:className zhu0 . ?s objects:parent ?o . ?o attributes:age 1000 .?s attributes:age ?v .}";

//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//        		+ "SELECT ?s WHERE { ?s attributes:className ?o.}";
		
//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//		+ "SELECT ?o ?s WHERE { ?s attributes:className zhu0 . ?s objects:parent ?o . ?o attributes:age 1000 .}";

//		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//		+ "SELECT ?s WHERE { ?s attributes:age ?o FILTER(?o > 50)}";
		
		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
        		+ "SELECT ?s WHERE { ?s attributes:className zhu0 . ?s objects:parent ?o . ?o attributes:age 1000 .?s attributes:age ?v .}";


		ANTLRInputStream input = new ANTLRInputStream(content);
        SparqlLexer lexer = new SparqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SparqlParser parser = new SparqlParser(tokens);
        ParseTree tree = parser.query(); // begin parsing at init rule
        
        ParseTreeWalker walker = new ParseTreeWalker();
        CustomListener sparqlBaseListener = new CustomListener(parser);
        walker.walk(sparqlBaseListener, tree);

        System.out.println(sparqlBaseListener.getSelectClause().getPrefix());
        System.out.println(sparqlBaseListener.getSelectClause().getVariableList());
        System.out.println();

        SimpleTreeGenerator stGeneration=new SimpleTreeGenerator();
        sparqlBaseListener.getSelectClause().compile();
        stGeneration.generate(sparqlBaseListener.getSelectClause());
        QueryEngineOnSpark qEngine=new QueryEngineOnSpark("TestQuery");
        qEngine.run(stGeneration.getTree());
        
	}
}
