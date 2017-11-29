package com.cetc28.seu.query.parser;

import java.util.ArrayList;
import java.util.List;

import com.cetc28.seu.rdf.Constant;
import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.rdf.Variable;



/**
 * @author yaosheng
 */
public class SentenceParser {
	private static String rdf="<.*,.*,.*>";
	private static String rdfPattern="("+rdf+","+")"+"*"+rdf;
	
	/**
	 * @param sentence
	 * @return
	 */
	public static List<RDF> parser(String sentence){
		List<RDF> rdfs=new ArrayList<>();
		System.out.println(rdfPattern);
		String[] spos=sentence.trim().split(" ");
		for(String spo : spos){
			if(spo.matches(".*")){
				spo=spo.trim().substring(1,spo.length()-1);
				String[] elements=spo.trim().split(",");
				
			    Term subject;
				Term object;
				if(elements[0].startsWith("?")){
					subject=new Variable(elements[0]);
				}else{
					subject=new Constant(elements[0]);
				}
				Constant predicet =new Constant(elements[1]);
				
				if(elements[2].startsWith("?")){
					object=new Variable(elements[2]);
				}else{
					object=new Constant(elements[2]);
				}
				RDF rdf=new RDF(subject,predicet,object);
				rdfs.add(rdf);
			}else{
				System.out.println("input query format is error �?");
				break;
			}
		}
		return rdfs;
	}
}
