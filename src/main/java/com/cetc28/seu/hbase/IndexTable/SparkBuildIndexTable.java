package com.cetc28.seu.hbase.IndexTable;

import java.io.IOException;
import java.io.Serializable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapred.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapred.JobConf;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetc28.seu.hbase.HbaseTool;

import scala.Tuple2;


@SuppressWarnings("deprecation")
public class SparkBuildIndexTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1321360008889892402L;
	private static HbaseTool hbaseTool = HbaseTool.getInstance();
	private static String THEME_NAME;
	private static Logger log;
	private static String indexTableName;
	//private static HTableInterface putIndex;
	private static SparkConf sparkConf;
	private static JavaSparkContext jsc;
	private static Configuration conf;
	private static Configuration indexConf;

	
	public HbaseTool getHbaseTool() {
		return hbaseTool;
	}

	public SparkBuildIndexTable(String theme_name, String indexTableName)
	{
		SparkBuildIndexTable.setLog(LoggerFactory.getLogger(this.getClass()));
		SparkBuildIndexTable.THEME_NAME = theme_name;
		SparkBuildIndexTable.indexTableName = indexTableName;
		
		SparkBuildIndexTable.sparkConf = new SparkConf();
		SparkBuildIndexTable.sparkConf.setMaster("local[4]");
		SparkBuildIndexTable.sparkConf.setAppName("QueryEntitiesByThemes");
		SparkBuildIndexTable.jsc = new JavaSparkContext(sparkConf);
		//设置原表
		SparkBuildIndexTable.conf = HBaseConfiguration.create();
		SparkBuildIndexTable.conf.set("hbase.zookeeper.quorum","ubuntu1,ubuntu2,ubuntu3");
		SparkBuildIndexTable.conf.set(TableInputFormat.INPUT_TABLE, SparkBuildIndexTable.THEME_NAME);
		//设置索引表
		SparkBuildIndexTable.indexConf = HBaseConfiguration.create();
		SparkBuildIndexTable.indexConf.set("hbase.zookeeper.quorum","ubuntu1,ubuntu2,ubuntu3");
		SparkBuildIndexTable.indexConf.set(TableInputFormat.INPUT_TABLE, SparkBuildIndexTable.indexTableName);
	}
	
	public void scanConvert(Scan scan) throws IOException
	{
		
		ClientProtos.Scan proto = ProtobufUtil.toScan(scan);
		String scanToString = Base64.encodeBytes(proto.toByteArray());
		SparkBuildIndexTable.conf.set(TableInputFormat.SCAN, scanToString);
	}
	
	public void scanIndexConvert(Scan scan) throws IOException
	{	
		ClientProtos.Scan proto = ProtobufUtil.toScan(scan);
		String scanToString = Base64.encodeBytes(proto.toByteArray());
		SparkBuildIndexTable.indexConf.set(TableInputFormat.SCAN, scanToString);
	}
	
	//扫描原表一行，添加一行数据到index
	public void buildTable(List<String> subjectNames) throws IOException
	{
		if(!HbaseTool.getAdmin().tableExists(SparkBuildIndexTable.indexTableName))
		{
			hbaseTool.createIndexTable(SparkBuildIndexTable.indexTableName);
		}

		for(String theme : subjectNames)
		{
			Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new SubstringComparator(theme));
			Scan scan = new Scan();
			scan.setFilter(filter);
			scanConvert(scan);
			
			JavaPairRDD<ImmutableBytesWritable, Result> rdd = SparkBuildIndexTable.jsc.newAPIHadoopRDD(
	        		SparkBuildIndexTable.conf, TableInputFormat.class, ImmutableBytesWritable.class, Result.class);

			JavaRDD<Map<byte[],byte[]>> value = rdd.map(new Function<Tuple2<ImmutableBytesWritable,Result>,Map<byte[],byte[]>>()
				{
	
					/**
					 * 
					 */
					private static final long serialVersionUID = -6718839070431458484L;

					@Override
					public Map<byte[], byte[]> call(Tuple2<ImmutableBytesWritable, Result> T) throws Exception {
						Map<byte[],byte[]> map = new HashMap<byte[],byte[]>();
						for(Cell cell : T._2.rawCells())
						{
							map.put(CellUtil.cloneValue(cell), T._2.getRow());
						}
						return map;
					}
					
				});
			
			value.foreach(new VoidFunction<Map<byte[],byte[]>>(){

				/**
				 * 
				 */
				private static final long serialVersionUID = -933077921776861004L;

				@Override
				public void call(Map<byte[], byte[]> t) throws Exception {
					putData(t);
				}
				
			});

		}
		
		hbaseTool.getTable(SparkBuildIndexTable.THEME_NAME).close();
		
	}
	
	public void putData(Map<byte[],byte[]> map) throws IOException
	{
		//1.JobConf setup
/*		for(Entry<byte[],byte[]> entry : map.entrySet())
		{
			System.out.println("key: " + Bytes.toString(entry.getKey())+" value: " + Bytes.toString(entry.getValue()));
		}*/
		JobConf jobConf = new JobConf(SparkBuildIndexTable.indexConf, this.getClass());
		jobConf.setOutputFormat(TableOutputFormat.class);
		jobConf.set(TableOutputFormat.OUTPUT_TABLE, SparkBuildIndexTable.indexTableName);

		//2.map -> rdd -> hbase
		for(Entry<byte[],byte[]> temp : map.entrySet())
		{
			List<Tuple2<byte[], byte[]>> list = Arrays.asList(new Tuple2<byte[], byte[]>(temp.getKey(),temp.getValue()));
			JavaPairRDD<byte[], byte[]> rdd = SparkBuildIndexTable.jsc.parallelizePairs(list);
			JavaPairRDD<ImmutableBytesWritable, Put> rddData = rdd.mapToPair(new PairFunction<Tuple2<byte[],byte[]>,ImmutableBytesWritable, Put>(){

				private static final long serialVersionUID = -8090214471128514566L;

				@Override
				public Tuple2<ImmutableBytesWritable, Put> call(Tuple2<byte[], byte[]> t) throws Exception {
					System.out.println("t1: " + Bytes.toString(t._1) + " t2: " + Bytes.toString(t._2));				
					
					Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(t._1));
					Scan scan = new Scan();
					scan.setFilter(filter);
					scanIndexConvert(scan);
					//扫index表
					JavaPairRDD<ImmutableBytesWritable, Result> rdd1 = SparkBuildIndexTable.jsc.newAPIHadoopRDD(
			        		SparkBuildIndexTable.indexConf, TableInputFormat.class, ImmutableBytesWritable.class, Result.class);
					JavaRDD<Integer> value = rdd1.map(new Function<Tuple2<ImmutableBytesWritable,Result>,Integer>(){

						private static final long serialVersionUID = -556876677615659589L;

						@Override
						public Integer call(Tuple2<ImmutableBytesWritable, Result> v1) throws Exception {
							int size = v1._2.listCells().size();					
							return size;
						}
						
					});
					
					//int count = (int) value.cache().count();
					//System.out.println("value" + value.isEmpty());
					Put put = new Put(t._1);
					if(value.isEmpty())
					{
						//若无返回值，则直接插入					
						put.add(Bytes.toBytes("Row"), Bytes.toBytes(1+""), t._2);
					}
					else
					{
						//若有返回值
						int column = value.first() + 1;
						put.add(Bytes.toBytes("Row"), Bytes.toBytes(column+""), t._2);	
					}
					
					return new Tuple2<ImmutableBytesWritable, Put>(new ImmutableBytesWritable(), put) ;
				}
				
			});
			
			rddData.saveAsHadoopDataset(jobConf);
		}
		
		
		
		
	/*	for(Entry<byte[],byte[]> entry : map.entrySet())
		{	
			
			Get get = new Get(entry.getKey());
			Result result = putIndex.get(get);
			if(result == null || result.size() == 0)
			{
				//若无返回值，则直接插入
				Put put = new Put(entry.getKey());
				put.add(Bytes.toBytes("Row"), Bytes.toBytes(1+""), entry.getValue());
				putIndex.put(put);
			}
			else
			{	
				//有返回值，说明有值重复，获得当前服务器的行数，然后+1
				Put put = new Put(entry.getKey());
				int column = result.listCells().size()+1;
				put.add(Bytes.toBytes("Row"), Bytes.toBytes(column+""), entry.getValue());	
				putIndex.put(put);
			}
			
		}
		//由于设置缓冲池，但是是从服务器获取列数，所以必须每put一次提交一次
		putIndex.flushCommits();*/
		
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		SparkBuildIndexTable.log = log;
	}

}
