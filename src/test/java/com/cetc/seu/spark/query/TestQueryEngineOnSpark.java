package com.cetc.seu.spark.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Test;

import com.cetc28.seu.query.struct.QueryCondition;
import com.cetc28.seu.spark.query.QueryEngineOnSpark;
import com.cetc28.seu.spark.query.model.QueryNode;
import com.cetc28.seu.spark.query.model.simple.JoinerNode;
import com.cetc28.seu.spark.query.model.simple.SimpleFilterNode;

public class TestQueryEngineOnSpark {
	@Test
	public void testrun(){
		SparkConf conf = new SparkConf().setAppName("testQuery")
				.setSparkHome("/home/ubuntu/applications/spark-2.0.0-bin-hadoop2.7")
				;
		JavaSparkContext sc = new JavaSparkContext(conf);
		Configuration hbaseConf1 = HBaseConfiguration.create();
		hbaseConf1.set("hbase.zookeeper.property.clientPort", "2181"); //
		hbaseConf1.set("hbase.zookeeper.quorum", "223.3.73.215,223.3.77.59,223.3.83.31"); // 
		hbaseConf1.addResource("/home/ubuntu/spark_data/Spark-connect-Hbase/hbase-site.xml"); //
		hbaseConf1.set(TableInputFormat.INPUT_TABLE, "testQuery");
		
		HashMap<String, String> l2conditions=new HashMap<>();
		l2conditions.put("sex", "man");
		List<String> l2answer=new ArrayList<>();
		l2answer.add("rowkey");
		l2answer.add("name");
		QueryCondition l2attributes=new QueryCondition(l2conditions);
		SimpleFilterNode l2=new SimpleFilterNode(l2attributes, hbaseConf1, sc);
		
		HashMap<String, String> r2conditions=new HashMap<>();
		r2conditions.put("name", "shijun");
		List<String> r2answer=new ArrayList<>();
		r2answer.add("rowkey");
		QueryCondition r2attributes=new QueryCondition(r2conditions);
		SimpleFilterNode r2=new SimpleFilterNode(r2attributes, hbaseConf1, sc);
		
		l2.setId(0);
		QueryNode.trees.add(l2);
		l2.setParent(2);
		
		r2.setId(1);
		QueryNode.trees.add(r2);
		l2.setParent(2);
		
		JoinerNode root=new JoinerNode();
		QueryNode.trees.add(root);
		
		root.setId(2);
		root.setLchild(0);
		root.setRchild(1);
		root.setJoinColumn("classmates");
		QueryEngineOnSpark engine=new QueryEngineOnSpark(null);
		//engine.run(root);
	}
}
