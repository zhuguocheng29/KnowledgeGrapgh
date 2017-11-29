package com.antlr.test;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.cetc28.seu.fromAntlr.CustomListener;
import com.cetc28.seu.fromAntlr.SparqlLexer;
import com.cetc28.seu.fromAntlr.SparqlParser;

import junit.framework.TestCase;

public class TestAntlr extends TestCase{

	public void test()
	{
		String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
        		+ "SELECT ?givenName ?y WHERE { ?y vcard:Family \"Smith\" . ?y vcard:Given ?givenName .}";
        ANTLRInputStream input = new ANTLRInputStream(content);
        SparqlLexer lexer = new SparqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SparqlParser parser = new SparqlParser(tokens);
        ParseTree tree = parser.query(); // begin parsing at init rule
        
        ParseTreeWalker walker = new ParseTreeWalker();
        CustomListener sparqlBaseListener = new CustomListener(parser);
        walker.walk(sparqlBaseListener, tree);
        //System.out.println(sparqlBaseListener.getVariableList());
        //System.out.println(sparqlBaseListener.getPrefix());
        //System.out.println(sparqlBaseListener.getRdf().get(1).getSubject().getValue());
	}
}
