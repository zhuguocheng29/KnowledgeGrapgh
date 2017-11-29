package com.antlr.test;


import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.cetc28.seu.fromAntlr.CustomListener;
import com.cetc28.seu.fromAntlr.SparqlLexer;
import com.cetc28.seu.fromAntlr.SparqlParser;
import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.sparql.FilterClause;

public class testAntlrByMain {
	 public static void main( String[] args )
	    {
	        
			String content = "PREFIX attributes: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX objects: <http://www.w3.org/2001/vname-rdf/2.0#> "
	        		+ "SELECT ?givenName ?y WHERE { ?y objects:Family ?givenName . ?givenName attributes:Given \"Smith\" .} ORDER BY ?givenName ";

	    	//union加普通
//			String content = "PREFIX attributes: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX objects: <http://www.w3.org/2001/vname-rdf/2.0#> "
//	        		+ "SELECT ?givenName ?y WHERE { {?y objects:Family \"Smith\"^^xsd:string .} UNION {?givenName attributes:Given \"Smith\" .} ?givenName attributes:car \"Smith\" .} ORDER BY ?givenName LIMIT 5 OFFSET 25 ";
	        
//			String content = "PREFIX attributes: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX objects: <http://www.w3.org/2001/vname-rdf/2.0#> "
//	        		+ "SELECT ?givenName ?y WHERE { ?y objects:Family \"Smith\"^^xsd:string . FILTER(?y<100)} ORDER BY ?givenName LIMIT 5 OFFSET 25 ";
	//  
//			String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//	        		+ "SELECT ?s ?o WHERE { ?s attributes:className zhu0 . ?s objects:parent ?o . ?o attributes:age 1000 .?s attributes:age ?v .}";
//			String content = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
//	        		+ "SELECT ?s WHERE { ?s attributes:className zhu0 .}";

			ANTLRInputStream input = new ANTLRInputStream(content);
	        SparqlLexer lexer = new SparqlLexer(input);
	        CommonTokenStream tokens = new CommonTokenStream(lexer);
	        SparqlParser parser = new SparqlParser(tokens);
	        ParseTree tree = parser.query(); // begin parsing at init rule
	      
	        ParseTreeWalker walker = new ParseTreeWalker();
	        //SparqlBaseListener sparqlBaseListener = new SparqlBaseListener();
	        CustomListener sparqlBaseListener = new CustomListener(parser);
	        walker.walk(sparqlBaseListener, tree);
	        //compile操作
	        sparqlBaseListener.getSelectClause().compile();

	        // variableList
	        System.out.println("variableList: " + sparqlBaseListener.getSelectClause().variableList);
	        
	        //对于order by的操作
	        if(sparqlBaseListener.getSelectClause().getOrderTerm() != null)
	        {
	            Map<String,Term> map = sparqlBaseListener.getSelectClause().getOrderTerm();
	            for(Entry<String,Term> entry : map.entrySet())
	            {
	            	System.out.println("type: " + entry.getKey()+" value: " + entry.getValue());
	            }
	        }

	       

	        //对于union,optional的操作
	        boolean exist;
	        if(sparqlBaseListener.getSelectClause().getRdfOp() != null)
	        {
	        	exist = true;
	        }
	        else
	        {
	        	exist = false; 
	        }
	        if(exist)
	        {
	        	if(sparqlBaseListener.getSelectClause().getRdfOp().containsKey("UNION"))
	        	{
	        		List<RDF> values = sparqlBaseListener.getSelectClause().getRdfOp().get("UNION");
	        		System.out.println("1");
	        		for(int i = 0; i < values.size(); i++)
	                {
	                	System.out.println(values.get(i).getSubject()+" "+values.get(i).getPredict()+" "+values.get(i).getObject());
	                }
	        	}
	        	if(sparqlBaseListener.getSelectClause().getRdfOp().containsKey("OPTIONAL"))
	        	{
	        		List<RDF> values = sparqlBaseListener.getSelectClause().getRdfOp().get("OPTIONAL");
	        		System.out.println("2");
	        		for(int i = 0; i < values.size(); i++)
	                {
	                	System.out.println(values.get(i).getSubject()+" "+values.get(i).getPredict()+" "+values.get(i).getObject());
	                }
	        	}
	        }

	        //普通where操作
	        if(sparqlBaseListener.getSelectClause().getRdfList() != null)
	        {
	        	int size = sparqlBaseListener.getSelectClause().getRdfList().size();
	        	if(size != 0)
	        	{
	        		System.out.println("3");
	    	    	for(int i = 0; i < size; i++)
	    	        {
	    	        	System.out.println(sparqlBaseListener.getSelectClause().getRdfList().get(i).getSubject()+" "
	    	      			+sparqlBaseListener.getSelectClause().getRdfList().get(i).getPredict()+" "+
	    	      			sparqlBaseListener.getSelectClause().getRdfList().get(i).getObject());
	    	        }
	        	}
	        }
	    	
	    	//获取filter
	    	if(sparqlBaseListener.getSelectClause().getFilter() != null)
	    	{
	        	for(FilterClause filterClause : sparqlBaseListener.getSelectClause().getFilter())
	        	{
	        		Term term = filterClause.getTerm();
	        		String symbol = filterClause.getSymbol();
	        		int value = (int)filterClause.getValue();
	        		System.out.println("Term: " + term + " symbol: "+ symbol+ " value: "+value);
	        	}
	    	}

	    	//获取limit
	    	if(sparqlBaseListener.getSelectClause().getLimit() != 0)
	    	{
	        	System.out.println("limit: " + sparqlBaseListener.getSelectClause().getLimit());
	    	}
	    	
	    	//获取offset
	    	if(sparqlBaseListener.getSelectClause().getOffset() != 0)
	    	{
	        	System.out.println("offset: " + sparqlBaseListener.getSelectClause().getOffset());
	    	}
	    }
/*	public static void main(String[] args) throws IOException {
		String path = "D:\\SparqlTest.txt";
		String content = null;
		File file = new File(path);
		if(file.isFile() && file.exists())
		{
			InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			BufferedReader buffererReader = new BufferedReader(read);
			StringBuffer sb = new StringBuffer();
			String line = null;
			while((line = buffererReader.readLine()) != null )
			{
				sb.append(line);
			}
			content = sb.toString();
		}

		String content = "PREFIX attributes: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX objects: <http://www.w3.org/2001/vname-rdf/2.0#> "
        		+ "SELECT ?givenName ?y WHERE { ?y objects:Family ?givenName . ?givenName attributes:Given \"Smith\" .} ORDER BY ?givenName";
		 //?givenName objects:Given \"Smith\" .
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
        int size = sparqlBaseListener.getSelectClause().getRdfList().size();
        for(int i = 0; i < size; i++)
        {
        	System.out.println(sparqlBaseListener.getSelectClause().getRdfList().get(i).getSubject()+" "
        			+sparqlBaseListener.getSelectClause().getRdfList().get(i).getPredict()+" "+
        			sparqlBaseListener.getSelectClause().getRdfList().get(i).getObject());
        }
	}*/

}
