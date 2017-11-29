package com.cetc28.seu.runable;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.cetc28.seu.fromAntlr.CustomListener;
import com.cetc28.seu.fromAntlr.SparqlLexer;
import com.cetc28.seu.fromAntlr.SparqlParser;
import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.loading.theme.model.EntityInfo;
import com.cetc28.seu.loading.theme.server.ExtractService;

import com.cetc28.seu.spark.query.QueryEngineOnSpark;
import com.cetc28.seu.spark.query.model.coprocessor.SearchKeyWordByNameEntity;
import com.cetc28.seu.spark.query.parser.CoprocessorQueryTreeGenerator;
import com.cetc28.seu.spark.query.parser.SimpleTreeGenerator;

/**
 * 该类集成了该系统所有功能的入口
 * @author yaosheng
 */
public class SystemEntrance {
	private static ExtractService es=new ExtractService(Configution.instanceTableName, Configution.schemaTableName);
	private static String exampleSql = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " + " PREFIX vname: <http://www.w3.org/2001/vname-rdf/2.0#>"
    		+ "SELECT ?s WHERE {?s attributes:className zhu0 .}";
	/**
	 * @param 1. 输入为主题的XML文档 2. Hbase表
	 * @return 将数据解析入Hbase表中
	 * 
	 */
	public static void extractThemeInstances(){
		es.extractAllTheme();
	}
	/**
	 * @param 1. 输入为主题的XML文档 2. Hbase表
	 * @return 将数据解析入Hbase表中
	 */
	public static void extractThemeInstancesByMutilThread(){
		es.extractByMultilThread();
	}
	/**
	 * @param 1. 输入为主题的XML文档 2. Hbase表
	 * @return 将数据解析入Hbase表中
	 */
	public static void extractThemeSchema(){
		es.extractAllSchema();
	}

	/**
	 * 提供基于协处理器的查询版本
	 * @param sql 查询语句
	 */
	public static void queryOnCoProcess(String sql){
		ANTLRInputStream input = new ANTLRInputStream(sql);
        SparqlLexer lexer = new SparqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SparqlParser parser = new SparqlParser(tokens);
        ParseTree tree = parser.query(); // begin parsing at init rule
        ParseTreeWalker walker = new ParseTreeWalker();
        CustomListener sparqlBaseListener = new CustomListener(parser);
        walker.walk(sparqlBaseListener, tree);
        
        CoprocessorQueryTreeGenerator cqGeneration = new CoprocessorQueryTreeGenerator();
        sparqlBaseListener.getSelectClause().compile();
        cqGeneration.generate(sparqlBaseListener.getSelectClause());
        QueryEngineOnSpark qEngine=new QueryEngineOnSpark();
        qEngine.coprocessorRun(cqGeneration.getTree());
	}
	
	/**
	 * 提供基于spark的查询版本
	 */
	public static void queryOnSpark(String sql){
		//解析sql
		ANTLRInputStream input = new ANTLRInputStream(sql);
        SparqlLexer lexer = new SparqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SparqlParser parser = new SparqlParser(tokens);
        ParseTree tree = parser.query(); // begin parsing at init rule
        ParseTreeWalker walker = new ParseTreeWalker();
        CustomListener sparqlBaseListener = new CustomListener(parser);
        walker.walk(sparqlBaseListener, tree);
        SimpleTreeGenerator stGeneration=new SimpleTreeGenerator();
        sparqlBaseListener.getSelectClause().compile();
        //生成查询树
        stGeneration.generate(sparqlBaseListener.getSelectClause());
        QueryEngineOnSpark qEngine=new QueryEngineOnSpark(Configution.instanceTableName);
        qEngine.run(stGeneration.getTree());
	}
	
	/**
	 * 建立索引,在导入所有数据后建立索引，索引与数据在同一张表中
	 * @param 
	 */
	public static void buildIndexInTable(){
		try {
			HbaseTool.getInstance().getTable(Configution.instanceTableName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HbaseTool.getInstance().buildIndex();
	}
	
	/**
	 * 关键字搜索，样例：关键字，类别，若类别没有指定则查全部类别。 nameEntity = "zhu" List<String> subjectNames
	 */
	public static void queryEntitiesByThemes(String nameEntity, List<String> subjectNames){
		SearchKeyWordByNameEntity si = new SearchKeyWordByNameEntity();
		List<EntityInfo> list = si.search(nameEntity, subjectNames);
		for(EntityInfo en:list){
			System.out.println(en.getNm());
		}
	}
	
	/**
	 * 需要使用上下谓关系的查询
	 * @param
	 */
	public static void rewriterQuery(){
		//
	}
	
}
