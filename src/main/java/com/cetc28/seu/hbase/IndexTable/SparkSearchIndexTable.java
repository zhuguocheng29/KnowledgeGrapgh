package com.cetc28.seu.hbase.IndexTable;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.loading.theme.model.EntityInfo;


import scala.Tuple2;
//implements KryoRegistrator
public class SparkSearchIndexTable implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3091241702305814899L;
	private static HbaseTool hbaseTool = HbaseTool.getInstance();
	private static String THEME_NAME;
	private static Logger log=null;
	private static String indexTableName;
	
	private static SparkConf sparkConf;
	private static JavaSparkContext jsc;
	private static Configuration conf;
	private static Configuration originalConf;
	//private static long start;
	public HbaseTool getHbaseTool() {
		return hbaseTool;
	}

	
	public SparkSearchIndexTable(String theme_name,String indexTableName) {		
		SparkSearchIndexTable.setLog(LoggerFactory.getLogger(this.getClass()));
//	    System.setProperty("spark.serializer","org.apache.spark.serializer.KryoSerializer");
	    
	    SparkSearchIndexTable.THEME_NAME = theme_name;
	    SparkSearchIndexTable.indexTableName = indexTableName;
		SparkSearchIndexTable.sparkConf = new SparkConf();
		SparkSearchIndexTable.sparkConf.setMaster("local[4]");
		SparkSearchIndexTable.sparkConf.setAppName("QueryEntitiesByThemes");
		SparkSearchIndexTable.jsc = new JavaSparkContext(sparkConf);
		//扫索引表
		SparkSearchIndexTable.conf = HBaseConfiguration.create();
		SparkSearchIndexTable.conf.set("hbase.zookeeper.quorum","ubuntu1,ubuntu2,ubuntu3");
		SparkSearchIndexTable.conf.set(TableInputFormat.INPUT_TABLE, SparkSearchIndexTable.indexTableName);
		//扫原表
		SparkSearchIndexTable.originalConf = HBaseConfiguration.create();
		SparkSearchIndexTable.originalConf.set("hbase.zookeeper.quorum","ubuntu1,ubuntu2,ubuntu3");
		SparkSearchIndexTable.originalConf.set(TableInputFormat.INPUT_TABLE, SparkSearchIndexTable.THEME_NAME);

	}
	//扫描原表
	public void scanOriginalConvert(Scan scan) throws IOException
	{
		
		ClientProtos.Scan proto = ProtobufUtil.toScan(scan);
		String scanToString = Base64.encodeBytes(proto.toByteArray());
		SparkSearchIndexTable.originalConf.set(TableInputFormat.SCAN, scanToString);
	}
	
	public void scanConvert(Scan scan) throws IOException
	{
		
		ClientProtos.Scan proto = ProtobufUtil.toScan(scan);
		String scanToString = Base64.encodeBytes(proto.toByteArray());
		SparkSearchIndexTable.conf.set(TableInputFormat.SCAN, scanToString);
	}
	

	//从索引表中获取列值
	public List<EntityInfo> searchIndexTable(String namedEntityName,final List<String> subjectNames) throws IOException
	{
		Filter filter = new RowFilter(CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes(namedEntityName)));
		Scan scan = new Scan();
		scan.setFilter(filter);
		scanConvert(scan);
		
        JavaPairRDD<ImmutableBytesWritable, Result> rdd = SparkSearchIndexTable.jsc.newAPIHadoopRDD(
        		SparkSearchIndexTable.conf, TableInputFormat.class, ImmutableBytesWritable.class, Result.class);
        
        JavaRDD<List<byte[]>> value = rdd.map(
        		new Function<Tuple2<ImmutableBytesWritable,Result>,List<byte[]>>()
        		{

					private static final long serialVersionUID = -4458155939724466280L;

					public List<byte[]> call(Tuple2<ImmutableBytesWritable, Result> t) throws Exception {
						List<byte[]> list = new ArrayList<byte[]>();
						for(Cell cell : t._2.rawCells())
						{
							for(String subjectName : subjectNames)
							{
								if(Bytes.toString(CellUtil.cloneValue(cell)).contains(subjectName))
								{
									list.add(CellUtil.cloneValue(cell));
								}
							}
							
						}

						return list;
					}
        			
        		});
        JavaRDD<List<byte[]>> temp = value.cache();	
        return getSearchResult(temp.first());
	}
	
	//获取的列值在原表中搜索
		public List<EntityInfo> getSearchResult(List<byte[]> list) throws IOException
		{
			
			List<EntityInfo> listEntity = new ArrayList<EntityInfo>();
			for(byte[] rowKey: list)
			{
				Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(rowKey));
				Scan scan = new Scan();
				scan.setFilter(filter);
				scanOriginalConvert(scan);
		        JavaPairRDD<ImmutableBytesWritable, Result> rdd = SparkSearchIndexTable.jsc.newAPIHadoopRDD(
		        		SparkSearchIndexTable.originalConf, TableInputFormat.class, ImmutableBytesWritable.class, Result.class);
		        
		        JavaRDD<EntityInfo> entityInfo = rdd.map(
		        		new Function<Tuple2<ImmutableBytesWritable,Result>,EntityInfo>()
		        		{
							private static final long serialVersionUID = -596471046709035780L;

							public EntityInfo call(Tuple2<ImmutableBytesWritable, Result> t) throws Exception {
								EntityInfo entityInfo = new EntityInfo();
								Map<String,String> map = new HashMap<String,String>();
								String[] getThemeFromRow = Bytes.toString(t._2.getRow()).split(":"); 
								String theme = getThemeFromRow[0];
								entityInfo.setSubjectName(theme);
								for(Cell cell : t._2.rawCells())
								{
									map.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
								}
								entityInfo.setDetails(map);
								return entityInfo;
							}
		        			
		        		});

		        entityInfo.cache();

		        listEntity.add(entityInfo.first());
			}
			return listEntity;
		}
	
/*	//从索引表中获取列值
	public List<EntityInfo> searchIndexTable(String namedEntityName,final List<String> subjectNames) throws IOException
	{
		start = System.currentTimeMillis();
		Filter filter = new RowFilter(CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes(namedEntityName)));
		Scan scan = new Scan();
		//scan.setStartRow(Bytes.toBytes(namedEntityName));
		scan.setFilter(filter);
		scanConvert(scan);
		
        JavaPairRDD<ImmutableBytesWritable, Result> rdd = SparkSearchIndexTable.jsc.newAPIHadoopRDD(
        		SparkSearchIndexTable.conf, TableInputFormat.class, ImmutableBytesWritable.class, Result.class);
        long stop = System.currentTimeMillis();
        System.out.println("stop1: " + (stop-start));
        JavaRDD<List<byte[]>> value = rdd.map(
        		new Function<Tuple2<ImmutableBytesWritable,Result>,List<byte[]>>()
        		{

					private static final long serialVersionUID = -4458155939724466280L;

					public List<byte[]> call(Tuple2<ImmutableBytesWritable, Result> t) throws Exception {
						List<byte[]> list = new ArrayList<byte[]>();
						for(Cell cell : t._2.rawCells())
						{
							list.add(CellUtil.cloneValue(cell));						
						}

						return list;
					}
        			
        		});
        JavaRDD<List<byte[]>> temp = value.cache();	
        long stop2 = System.currentTimeMillis();
        System.out.println("stop2: " + (stop2-start));
        //List<byte[]> list = temp.first();
        long stop3 = System.currentTimeMillis();
        System.out.println("stop3: " + (stop3-start));
        JavaRDD<List<EntityInfo>> listEntity = temp.map(new Function<List<byte[]>,List<EntityInfo>>()
        		{

					*//**
					 * 
					 *//*
					private static final long serialVersionUID = -1398206067326385886L;

					@Override
					public List<EntityInfo> call(List<byte[]> list) throws Exception {
						List<EntityInfo> listEntity = new ArrayList<EntityInfo>();
						for(byte[] rowKey: list)
						{
							Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(rowKey));
							Scan scan = new Scan();
							scan.setFilter(filter);
							scanOriginalConvert(scan);
					        JavaPairRDD<ImmutableBytesWritable, Result> rdd = SparkSearchIndexTable.jsc.newAPIHadoopRDD(
					        		SparkSearchIndexTable.originalConf, TableInputFormat.class, ImmutableBytesWritable.class, Result.class);

					        long original_stop4 = System.currentTimeMillis();
					        System.out.println("original_stop4: " + (original_stop4-start));
					        
					        JavaRDD<EntityInfo> entityInfo = rdd.map(
					        		new Function<Tuple2<ImmutableBytesWritable,Result>,EntityInfo>()
					        		{
										*//**
										 * 
										 *//*
										private static final long serialVersionUID = -596471046709035780L;

										public EntityInfo call(Tuple2<ImmutableBytesWritable, Result> t) throws Exception {
											EntityInfo entityInfo = new EntityInfo();
											Map<String,String> map = new HashMap<String,String>();
											String[] getThemeFromRow = Bytes.toString(t._2.getRow()).split(":"); 
											String theme = getThemeFromRow[0];
											entityInfo.setSubjectName(theme);
											for(Cell cell : t._2.rawCells())
											{
												map.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
											}
											entityInfo.setDetails(map);
											return entityInfo;
										}
					        			
					        		});
					        listEntity.add(entityInfo.first());
					        long original_stop5 = System.currentTimeMillis();
					        System.out.println("original_stop5: " + (original_stop5-start));
						}
						return listEntity;
					}
        	
        		});
        return listEntity.first();
	}
*/	
	

	public static Logger getLog() {
		return log;
	}


	public static void setLog(Logger log) {
		SparkSearchIndexTable.log = log;
	}
		
	
	
}
