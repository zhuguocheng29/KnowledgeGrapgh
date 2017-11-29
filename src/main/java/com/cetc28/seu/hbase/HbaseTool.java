
package com.cetc28.seu.hbase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.Coprocessor;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.coprocessor.Batch;
import org.apache.hadoop.hbase.ipc.BlockingRpcCallback;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKeyEndPoint;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKeyWithoutIndexEndPoint;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchDimService;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchNoIndexService;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchRequest;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchResponse;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchService;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKeyDimEndPoint;
import com.google.protobuf.ServiceException;

/**
 * Description: a class in order to obtain hbabse configuration, htable and hbaseAdmin.
 * @author ttf
 */
public class HbaseTool {

	private static HbaseTool hbaseTool;
	private static HBaseAdmin hBaseAdmin;
	private static Configuration baseConfiguration;
	private HTableInterface htable;
	//private HTable htable;
	private HTableInterface indexTable = null;
	private static HConnection connection;


	static {
		baseConfiguration = HBaseConfiguration.create();
		// in order to connect remote hbase, zookeeper quorum and client need to
		// be set
		//baseConfiguration.set("hbase.zookeeper.quorum", "ubuntu1");
		//baseConfiguration.set("hbase.zookeeper.quorum","ubuntu1,ubuntu2,ubuntu3");
		baseConfiguration.set("hbase.zookeeper.quorum",Configution.hostip);
		baseConfiguration.setInt("hbase.zookeeper.property.clientPort", Configution.port);
		// baseConfiguration.setInt("hbase.zookeeper.property.clientPort",
		// value);
		try {
			connection = HConnectionManager.createConnection(baseConfiguration);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			hBaseAdmin = new HBaseAdmin(baseConfiguration);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private HbaseTool() {
	}

	public static HBaseAdmin getAdmin() {

		return hBaseAdmin;
	}

	public static Configuration getBaseConfiguration() {
		return baseConfiguration;
	}

	public static void setBaseConfiguration(Configuration baseConfiguration) {
		HbaseTool.baseConfiguration = baseConfiguration;
	}

	public static HbaseTool getInstance() {

		if (hbaseTool == null)
			hbaseTool = new HbaseTool();

		return hbaseTool;
	}


	public HTableInterface getTable(String tableName) throws IOException {

		if (htable == null) {
/*			try {
				htable = new HTable(baseConfiguration, TableName.valueOf(tableName));
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			htable = connection.getTable(TableName.valueOf(tableName));
			htable.setWriteBufferSize(10*1024*1024);
			htable.setAutoFlushTo(false);
		}

		return htable;

	}


	/**
	 * 在建表时加载协处理器,增加了index这一个family 注意：加载协处理器时，最多只能加载2个endpoint
	 * @param tableName
	 * @param families
	 */
	public static void createTable(String tableName,String[] families)
	{
		HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
		HColumnDescriptor[] colDesArray=new HColumnDescriptor[families.length];
		for(int i=0;i<families.length;i++){
			colDesArray[i]=new HColumnDescriptor(families[i]);
			colDesArray[i].setBloomFilterType(BloomType.ROW);
			colDesArray[i].setMaxVersions(1);
			hTableDescriptor.addFamily(colDesArray[i]);
		}

		//添加协处理器路径,是namenode的路径,普通jar包就可以
		Path path = new Path("hdfs://223.3.84.42:9000/hbaseCoprocessor/kg.jar");
		try {
			//hTableDescriptor.addCoprocessor(CreateIndexByCop.class.getCanonicalName(), path, Coprocessor.PRIORITY_USER, null);
			hTableDescriptor.addCoprocessor(SearchRowKeyWithoutIndexEndPoint.class.getCanonicalName(), path, Coprocessor.PRIORITY_USER, null);		
			hTableDescriptor.addCoprocessor(SearchRowKeyEndPoint.class.getCanonicalName(), path, Coprocessor.PRIORITY_USER, null);		
//			hTableDescriptor.addCoprocessor(SearchRowKeyDimEndPoint.class.getCanonicalName(), path, Coprocessor.PRIORITY_USER, null);		
			hTableDescriptor.setRegionSplitPolicyClassName(ConstantSizeRegionSplitPolicy.class.getName());

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//设10个region块，因为索引表与数据放在一起
		byte[][] regions = new byte[][]{
			Bytes.toBytes("0000"),
			Bytes.toBytes("1000"),
			Bytes.toBytes("2000"),
			Bytes.toBytes("3000"),
			Bytes.toBytes("4000"),
			Bytes.toBytes("5000"),
			Bytes.toBytes("6000"),
			Bytes.toBytes("7000"),
			Bytes.toBytes("8000"),
			Bytes.toBytes("9000"),
			Bytes.toBytes("9999a"),
			Bytes.toBytes("aaaaa"),
		};
		
		try {
			hBaseAdmin.createTable(hTableDescriptor,regions);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("builtTable");

	}
	
	/**
	 * 根据自身所输入的region块个数进行自动划分，若不输入region块的个数则默认为10个,最好输入region块为10的倍数。若不输入为倍数，则会自动变为10的倍数
	 * @param tableName
	 * @param families
	 * @param regionNumber
	 */
	public static void createTable(String tableName,String[] families, int regionNumber)
	{
		HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
		HColumnDescriptor[] colDesArray=new HColumnDescriptor[families.length];
		for(int i=0;i<families.length;i++){
			colDesArray[i]=new HColumnDescriptor(families[i]);
			colDesArray[i].setBloomFilterType(BloomType.ROW);
			colDesArray[i].setMaxVersions(1);
			hTableDescriptor.addFamily(colDesArray[i]);
		}

		//添加协处理器路径,是namenode的路径,普通jar包就可以
		Path path = new Path("hdfs://223.3.84.42:9000/hbaseCoprocessor/kg.jar");
		try {
			hTableDescriptor.addCoprocessor(SearchRowKeyEndPoint.class.getCanonicalName(), path, Coprocessor.PRIORITY_USER, null);	
			hTableDescriptor.addCoprocessor(SearchRowKeyWithoutIndexEndPoint.class.getCanonicalName(), path, Coprocessor.PRIORITY_USER, null);		
//			hTableDescriptor.addCoprocessor(SearchRowKeyDimEndPoint.class.getCanonicalName(), path, Coprocessor.PRIORITY_USER, null);		
			//ConstantSizeRegionSplitPolicy.class.表示按照固定大小进行切分。设置表为禁止自动切分DisabledRegionSplitPolicy.class.
			hTableDescriptor.setRegionSplitPolicyClassName(ConstantSizeRegionSplitPolicy.class.getName());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			byte[][] regions = getRegions(regionNumber);
			hBaseAdmin.createTable(hTableDescriptor,regions);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("builtTable");

	}
	
	
	/**
	 * 根据输入的Region块数，设置每个Region块的起始行键
	 * @param num
	 * @return
	 * @throws Exception
	 */
	private static byte[][] getRegions(int num) throws Exception{
		if(num > 10000){
			throw new Exception("输入块数请<10000");
		}
		if(num % 10 != 0){
			int t = num / 10; 
			num = t * 10;
		}
		int sum = 10000/num;
		byte[][] region = new byte[num+2][];
		region[0] = Bytes.toBytes("0000");
		int regionNumber = 0;
		for(int i = 1; i < num; i++){
			String s = "";
			//补0
			regionNumber = regionNumber + sum;
			if(regionNumber < 1000){
				int num0 = 0;
				int temp = regionNumber;
				while(temp != 0){
					temp = temp / 10;
					num0++;
				}
				int com0 = 4 - num0;
				for(int j = 0; j < com0; j++){
					s = s+"0";
				}
				s = s + regionNumber;
			}else{
				//不补0
				s = String.valueOf(regionNumber);
			}
			//System.out.println("prefix: " + s);
			region[i] = Bytes.toBytes(s);
		}
		region[num] = Bytes.toBytes("9999a");
		region[num+1] = Bytes.toBytes("aaaaa");
		return region;
	}
	
	
/*	//带有版本号的建表
	public static void createTable(String themeName,String[] families) throws IOException {
		HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(themeName.getBytes()));
		HColumnDescriptor[] colDesArray=new HColumnDescriptor[families.length];
		for(int i=0;i<families.length;i++){
			colDesArray[i]=new HColumnDescriptor(families[i]);
			if(i != 0)
			{
				colDesArray[i].setMaxVersions(5000);
			}
			hTableDescriptor.addFamily(colDesArray[i]);
		}
		hBaseAdmin.createTable(hTableDescriptor);
	}*/
	
	public HTableInterface getIndexTable(String indexTableName) throws IOException{
		if(indexTable == null)
		{
			//connection = HConnectionManager.createConnection(baseConfiguration);
			indexTable = connection.getTable(TableName.valueOf(indexTableName));
			indexTable.setWriteBufferSize(10*1024*1024);
			indexTable.setAutoFlushTo(false);
		}
		return indexTable;

	}
	
	public void createIndexTable(String indexTableName)
	{
		HTableDescriptor hTableIndex = new HTableDescriptor(TableName.valueOf(indexTableName));
		HColumnDescriptor hColumnDescriptor_Row = new HColumnDescriptor("Row");

		hColumnDescriptor_Row.setMaxVersions(10);
		hTableIndex.addFamily(hColumnDescriptor_Row);
		try {
			hBaseAdmin.createTable(hTableIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 表建完后生成索引
	 */
/*	public void buildIndex()
	{
		Scan scan = new Scan();
		ResultScanner resultScanner = null;
		try {
			resultScanner = htable.getScanner(scan);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Result result : resultScanner)
		{
			String dataIndex = Bytes.toString(result.getRow());
			String dataIndexTemp = dataIndex.substring(0, 1);
			String regionNumber = getRegionNumber(dataIndexTemp);
			for(Cell cell : result.rawCells())
			{
				String family = Bytes.toString(CellUtil.cloneFamily(cell));
				String column = Bytes.toString(CellUtil.cloneQualifier(cell));
				String value = Bytes.toString(CellUtil.cloneValue(cell));
				//是否为纯数字
				String s1 = "^-?\\d+$";
				//是否为时间
				String s2 = "(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?(\\s)*(PM|AM)?)";
				//字段为bdnm,或包含id
				if((family.equals("attributes") && column.equals("bdnm")) || (family.equals("attributes") && column.contains("id"))){
					Put put = new Put(Bytes.toBytes(regionNumber+":"+value+":"+family+":"+column+"_"+dataIndex));
					put.add(Bytes.toBytes("index"), Bytes.toBytes("1"), Bytes.toBytes(dataIndex));
					try {
						htable.put(put);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//在28所改为value值
				}else if(value.matches(s1) || value.matches(s2) || value.equals("Null") || (family.equals("attributes") && column.equals("data"))){
					continue;
				}else{
					Put put = new Put(Bytes.toBytes(regionNumber+":"+value+":"+family+":"+column+"_"+dataIndex));
					put.add(Bytes.toBytes("index"), Bytes.toBytes("1"), Bytes.toBytes(dataIndex));
					try {
						htable.put(put);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
			try {
				htable.flushCommits();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			htable.flushCommits();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	
	/**
	 * 表建完后生成索引
	 */
	public void buildIndex()
	{
		HashSet<String> indexHashSet = new HashSet<String>();
		
		Scan scan = new Scan();
		ResultScanner resultScanner = null;
		try {
			resultScanner = htable.getScanner(scan);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Result result : resultScanner)
		{
			String dataIndex = Bytes.toString(result.getRow());
			String dataIndexTemp = dataIndex.substring(0, 1);
			String regionNumber = getRegionNumber(dataIndexTemp);
			ArrayList<Put> arrayList = new ArrayList<Put>();
			for(Cell cell : result.rawCells())
			{
				String family = Bytes.toString(CellUtil.cloneFamily(cell));
				String column = Bytes.toString(CellUtil.cloneQualifier(cell));
				String value = Bytes.toString(CellUtil.cloneValue(cell));
				//是否为纯数字
				String s1 = "^-?\\d+$";
				//是否为时间
				String s2 = "(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?(\\s)*(PM|AM)?)";
				//字段为bdnm,或包含id
				if((family.equals("attributes") && column.equals("bdnm")) || (family.equals("attributes") && column.contains("id"))){
					//对建立索引的列存放列名
					indexHashSet.add(column);
					
					Put put = new Put(Bytes.toBytes(regionNumber+":"+value+":"+family+":"+column+"_"+dataIndex));
					put.add(Bytes.toBytes("index"), Bytes.toBytes("1"), Bytes.toBytes(dataIndex));
					arrayList.add(put);
				}else if(value.matches(s1) || value.matches(s2) || value.equals("null") || value.equals("Null") || (family.equals("attributes") && column.equals("data"))){
					continue;
				}else{
					//对建立索引的列存放列名
					indexHashSet.add(column);
					
					Put put = new Put(Bytes.toBytes(regionNumber+":"+value+":"+family+":"+column+"_"+dataIndex));
					put.add(Bytes.toBytes("index"), Bytes.toBytes("1"), Bytes.toBytes(dataIndex));
					arrayList.add(put);
				}
				try {
					htable.put(arrayList);
					htable.flushCommits();

				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		//将建立索引的列存储起来，放入文件
		outputFile(indexHashSet);
	}
	
	
//	/**
//	 * 将建立文件的列存起来，放入文件
//	 * @param indexHashSet
//	 */
//	public void outputFile(HashSet<String> indexHashSet){
//		long start = System.currentTimeMillis();
//		try{
//			File file=new File("ColumnName.txt");
//	        if(file.exists())
//	            file.delete();
//	        if(!file.exists()){
//	        	file.createNewFile();
//	        }
//	        FileWriter fw = new FileWriter(file, true);  
//            BufferedWriter bw = new BufferedWriter(fw);
//            StringBuffer sb=new StringBuffer();
//	        for(String str : indexHashSet){
//	            sb.append(str +"\r\n");
//	        }        
//            bw.write(sb.toString());
//            bw.flush();
//	        bw.close();	
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("file output " + (end-start));
//	}
	

	/**
	 * 将建立文件的列存起来，放入文件，即使split后，由于表数据未变，所以不需要动态加入
	 * @param indexHashSet
	 */
	private void outputFile(HashSet<String> indexHashSet){
		
		try{
			File file=new File("ColumnName.txt");
	        if(file.exists()){
	            file.delete();
	        }
	        if(!file.exists()){
	        	file.createNewFile();
	        }
	        FileWriter fw = new FileWriter(file, true);  
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuffer sb=new StringBuffer();
            int i = 0;
            Put put = new Put(Bytes.toBytes("9999a"));
	        for(String str : indexHashSet){
	            sb.append(str +"\r\n");
	            //放入表中的region为9999a的部分
	            put.add(Bytes.toBytes("attributes"), Bytes.toBytes(String.valueOf(i)), Bytes.toBytes(str));
	            i++;
	        }        
	        if(indexHashSet.size() == 0){
	        	return;
	        }
            bw.write(sb.toString());
            bw.flush();
	        bw.close();	
	        htable.put(put);
	        htable.flushCommits();
	        
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 将column的名字从file中读取
//	 * @return hashSet
//	 */
//	public HashSet<String> inputFile(){
//        HashSet<String> indexHashSet = new HashSet<String>();
//		  try {  
//	            File file = new File("ColumnName.txt");  
//	            // 读取文件，并且以utf-8的形式写出去  
//	            BufferedReader bufread;  
//	            String read;  
//	            bufread = new BufferedReader(new FileReader(file));
//	            StringBuffer sb = new StringBuffer();
//	            while ((read = bufread.readLine()) != null) {  
//	                sb.append(read+" ");  
//	            }
//	            String[] str = sb.toString().split(" ");
//	            for(String s : str){
//	            	indexHashSet.add(s);
//	            }
//	            bufread.close();  
//
//	        } catch (FileNotFoundException ex) {  
//	            ex.printStackTrace();  
//	        } catch (IOException ex) {  
//	            ex.printStackTrace();  
//	        }  
//        return indexHashSet;
//	}
	
	/**
	 * 将column的名字从file中读取，若无file，从hbase表中读取数据然后放到file中
	 * @return hashSet
	 */
	public HashSet<String> inputFile(){
        HashSet<String> indexHashSet = new HashSet<String>();
		  try {  
	            File file = new File("ColumnName.txt");  
	            //当文件不存在时，从数据库读取之前建立的关键字表
	            if(!file.exists()){
	            	HashSet<String> hashSet = new HashSet<String>();
	            	Get get = new Get(Bytes.toBytes("9999a"));
	            	Result result = htable.get(get);
	            	for(Cell cell : result.rawCells()){
	            		hashSet.add(Bytes.toString(CellUtil.cloneValue(cell)));
	            	}
	            	outputFile(hashSet);
	            }
	            // 读取文件，并且以utf-8的形式写出去
	            BufferedReader bufread;  
	            String read;  
	            bufread = new BufferedReader(new FileReader(file));
	            StringBuffer sb = new StringBuffer();
	            while ((read = bufread.readLine()) != null) {  
	                sb.append(read+" ");  
	            }
	            String[] str = sb.toString().split(" ");
	            for(String s : str){
	            	indexHashSet.add(s);
	            }
	            bufread.close();  

	        } catch (FileNotFoundException ex) {  
	            ex.printStackTrace();  
	        } catch (IOException ex) {  
	            ex.printStackTrace();  
	        }  
        return indexHashSet;
	}
	
	
	/**
	 * 启动协处理器进行检索
	 * @param tableName
	 * @param family
	 * @param column
	 * @param value
	 * @return
	 */
	public List<String> startSearchIndex(String family, String column, String value)
	{
		List<String>  rowKey = new ArrayList<String>();
		final SearchRequest request = SearchRequest.newBuilder().setFamily(family).setColumn(column).setValue(value).build();
	    	
	    	try {
	    		Map<byte[], List<String>> result = htable.coprocessorService(SearchService.class, null, null, new Batch.Call<SearchService, List<String>>() {
	
					@Override
					public List<String> call(SearchService instance) throws IOException {
						// TODO Auto-generated method stub
						BlockingRpcCallback<SearchResponse> rpcCallback = new BlockingRpcCallback<SearchResponse>();
						instance.getRowKey(null, request, rpcCallback);
			            SearchResponse response = rpcCallback.get();
			            System.out.println("response.getRowKeyCount() has index: " + response.getRowKeyCount());
			            if(response.getRowKeyCount()==0)
			            {
			            	return new ArrayList<String>();
			            }
			            else
			            {
			            	return response.getRowKeyList();
			            }
			            
					}
				});
				System.out.println("str:" + result.size());

	    		for(List<String> resultValue : result.values())
	    		{
	    			for(String str : resultValue){
	    				rowKey.add(str);
	    			}

	    		}
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			System.out.println("num2:" + rowKey.size());
	    	return rowKey;
	}
	
	
	/**
	 * 启动协处理器进行检索,对于没有索引的部分进行查找
	 * @param tableName
	 * @param family
	 * @param column
	 * @param value
	 * @return
	 */
	public List<String> startSearch(String family, String column, String value)
	{
		List<String>  rowKey = new ArrayList<String>();
		System.out.println(family+":"+column+":"+value);
		final SearchRequest request = SearchRequest.newBuilder().setFamily(family).setColumn(column).setValue(value).build();
	    	
	    	try {
	    		Map<byte[], List<String>> result = htable.coprocessorService(SearchNoIndexService.class, null, null, new Batch.Call<SearchNoIndexService, List<String>>() {
	
					@Override
					public List<String> call(SearchNoIndexService instance) throws IOException {
						BlockingRpcCallback<SearchResponse> rpcCallback = new BlockingRpcCallback<SearchResponse>();
						instance.getRowKeyNoIndex(null, request, rpcCallback);
			            SearchResponse response = rpcCallback.get();
			            System.out.println("response.getRowKeyCount() no index : " + response.getRowKeyCount());
			            if(response.getRowKeyCount()==0)
			            {
			            	return new ArrayList<String>();
			            }
			            else
			            {
			            	return response.getRowKeyList();
			            }
			            
					}
				});
	    		for(List<String> resultValue : result.values())
	    		{
	    			for(String str : resultValue){
	    				rowKey.add(str);
	    			}

	    		}
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
	    	
	    	return rowKey;
	}
	
	
	/**
	 * 启动协处理器进行检索,dim 
	 * @param tableName
	 * @param family
	 * @param column
	 * @param value
	 * @return
	 */
	public List<String> startDimSearch(String family, String column, String value)
	{
		List<String>  rowKey = new ArrayList<String>();
		final SearchRequest request = SearchRequest.newBuilder().setFamily(family).setColumn(column).setValue(value).build();
	    	
	    	try {
	    		Map<byte[], List<String>> result = htable.coprocessorService(SearchDimService.class, null, null, new Batch.Call<SearchDimService, List<String>>() {
	
					@Override
					public List<String> call(SearchDimService instance) throws IOException {
						BlockingRpcCallback<SearchResponse> rpcCallback = new BlockingRpcCallback<SearchResponse>();
						instance.getRowKeyDim(null, request, rpcCallback);
			            SearchResponse response = rpcCallback.get();
			            System.out.println("response.getRowKeyCount() dim index : " + response.getRowKeyCount());
			            if(response.getRowKeyCount()==0)
			            {
			            	return new ArrayList<String>();
			            }
			            else
			            {
			            	return response.getRowKeyList();
			            }
			            
					}
				});
	    		for(List<String> resultValue : result.values())
	    		{
	    			for(String str : resultValue){
	    				rowKey.add(str);
	    			}

	    		}
			} catch (ServiceException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
	    	
	    	return rowKey;
	}
	
	
	
	
	public String getRegionNumber(String id)
	{
		String regionNumber = "";
		switch(id)
		{
			case "0": 
					regionNumber = "0000";
					break;
			case "1": 
					regionNumber = "1000";
					break;
			case "2": 
					regionNumber = "2000";
					break;
			case "3": 
					regionNumber = "3000";
					break;
			case "4": 
					regionNumber = "4000";
					break;
			case "5": 
					regionNumber = "5000";
					break;
			case "6": 
					regionNumber = "6000";
					break;
			case "7": 
					regionNumber = "7000";
					break;
			case "8": 
					regionNumber = "8000";
					break;
			case "9": 
					regionNumber = "9000";
					break;
		}
		return regionNumber;
	}
	
	/**
	 * 建增量索引
	 * @param put
	 */
	public void addIndexIncrement(Put dataPut){
		String dataIndex = Bytes.toString(dataPut.getRow());
		String dataIndexTemp = dataIndex.substring(0, 1);
		String regionNumber = getRegionNumber(dataIndexTemp);
		System.out.println(dataIndex);
		
		for(Entry<byte[], List<Cell>> entry : dataPut.getFamilyCellMap().entrySet()){
			ArrayList<Put> arrayList = new ArrayList<Put>();
			for(Cell cell : entry.getValue()){
				String family = Bytes.toString(CellUtil.cloneFamily(cell));
				String column = Bytes.toString(CellUtil.cloneQualifier(cell));
				String value = Bytes.toString(CellUtil.cloneValue(cell));
				System.out.println(family +"　"+column+" "+value);
				
				//是否为纯数字
				String s1 = "^-?\\d+$";
				//是否为时间
				String s2 = "(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?(\\s)*(PM|AM)?)";
				//字段为bdnm,或包含id
				if((family.equals("attributes") && column.equals("bdnm")) || (family.equals("attributes") && column.contains("id"))){
					Put put = new Put(Bytes.toBytes(regionNumber+":"+value+":"+family+":"+column+"_"+dataIndex));
					put.add(Bytes.toBytes("index"), Bytes.toBytes("1"), Bytes.toBytes(dataIndex));
					arrayList.add(put);
				}else if(value.matches(s1) || value.matches(s2) || value.equals("Null") || (family.equals("attributes") && column.equals("data"))){
					continue;
				}else{
					Put put = new Put(Bytes.toBytes(regionNumber+":"+value+":"+family+":"+column+"_"+dataIndex));
					put.add(Bytes.toBytes("index"), Bytes.toBytes("1"), Bytes.toBytes(dataIndex));
					arrayList.add(put);
				}
				try {
					htable.put(arrayList);
					htable.flushCommits();

				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}
	
	/**
	 * 随机建立4位数的前缀
	 * @return
	 */
	public String getRandomPrefix()
	{
		String base = "0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int randomNumber = 0; randomNumber < 4; randomNumber++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    String prefix = sb.toString();
	    return prefix;
	}
	
	public void putDemo(){
		String prefix = getRandomPrefix();
		String dataIndex = prefix+":姚明";
		Put put = new Put(Bytes.toBytes(dataIndex));
    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("职业"),Bytes.toBytes(String.valueOf("篮协主席")));
    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("妻子"),Bytes.toBytes(String.valueOf("叶莉")));
    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("女儿"),Bytes.toBytes(String.valueOf("姚沁蕾")));
    	put.add(Bytes.toBytes("objects"), Bytes.toBytes("队友"),Bytes.toBytes(String.valueOf("孙悦")));
    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("师傅"),Bytes.toBytes(String.valueOf("李秋平")));
    	try {
			htable.put(put);
			htable.flushCommits();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	String prefix1 = getRandomPrefix();
    	String data = prefix1+":易建联";
		Put put1 = new Put(Bytes.toBytes(data));
		put1.add(Bytes.toBytes("objects"),Bytes.toBytes("队友"),Bytes.toBytes(String.valueOf(dataIndex)));
		put1.add(Bytes.toBytes("attributes"),Bytes.toBytes("职业"),Bytes.toBytes(String.valueOf("篮球运动员")));
		try {
			htable.put(put1);
			htable.flushCommits();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 测试用
	 * @throws IOException
	 */
	public void putTable() throws IOException
	{
		
		System.out.println("successful read");
		for(int i=1; i<100; i++)
		{
			String prefix = getRandomPrefix();
			String indexPrefixTemp = prefix.substring(0, 1);
			String indexPrefix = getRegionNumber(indexPrefixTemp);
			String dataIndex = prefix+":test:"+prefix.hashCode()+":"+i;
			Put put = new Put(Bytes.toBytes(dataIndex));
		    int columnNumber = (int)(1+Math.random()*(15-1+1));
		    if(i%22 == 0)
		    {
		    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"),Bytes.toBytes(String.valueOf(i)));
		    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("className"),Bytes.toBytes("zhu0"));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("age"),Bytes.toBytes(i+""));
				put.add(Bytes.toBytes("objects"), Bytes.toBytes("parent"),Bytes.toBytes((prefix+":"+1000)+""));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("car"),Bytes.toBytes("Ferrari"));
		    }
		    else if(i%7== 0)
		    {
		    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"),Bytes.toBytes(String.valueOf(i)));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("className"),Bytes.toBytes("zhu" + i));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("age"),Bytes.toBytes(i+""));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("car"),Bytes.toBytes("Benz"));
				put.add(Bytes.toBytes("array_objects"), Bytes.toBytes("random"),Bytes.toBytes(prefix));
		    }
		    else if(i%37 == 0)
		    {
		    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"),Bytes.toBytes(String.valueOf(i)));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("className"),Bytes.toBytes("zhu" + i));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("age"),Bytes.toBytes(i+""));
				put.add(Bytes.toBytes("attributes"), Bytes.toBytes("car"),Bytes.toBytes("Benz"));
				put.add(Bytes.toBytes("array_objects"), Bytes.toBytes("random"),Bytes.toBytes(prefix));
		    }
		    else
		    {
		    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"),Bytes.toBytes((prefix+i)+""));
		    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("s-id"),Bytes.toBytes((prefix+i)+""));
		    	put.add(Bytes.toBytes("attributes"),Bytes.toBytes((i+1)+""),Bytes.toBytes((prefix+i)+""));
		    	put.add(Bytes.toBytes("attributes"),Bytes.toBytes((i+1)+""),Bytes.toBytes("Null"));
			    
		    }
		    htable.put(put);
			htable.flushCommits();

		}
		Put put = new Put(Bytes.toBytes("0111:test:-123:1"));
		put.add(Bytes.toBytes("attributes"), Bytes.toBytes("data"),Bytes.toBytes("zhu123"));
		put.add(Bytes.toBytes("attributes"), Bytes.toBytes("className"),Bytes.toBytes("zhu0"));
		htable.put(put);
		htable.flushCommits();
		
		System.out.println("successful insert");
		
	}
	
	
	
	
	/**
	 * 测试用，用于测试join,添加parent数据
	 */
	public void addOneRecord(){
		System.out.println("start");
		for(int i=1; i<10; i++)
		{
			String prefix = getRandomPrefix();
			String indexPrefixTemp = prefix.substring(0, 1);
			String indexPrefix = getRegionNumber(indexPrefixTemp);
			String dataIndex = prefix+":test:"+prefix.hashCode()+":"+i;
			
			Put put = new Put(Bytes.toBytes(dataIndex));	    	
	    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"),Bytes.toBytes("Rooney"));
	    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("className"),Bytes.toBytes("zhu0"));
			put.add(Bytes.toBytes("attributes"), Bytes.toBytes("age"),Bytes.toBytes(i+""));
			String t = prefix.substring(0, 3)+"0:topic:"+prefix.hashCode()+":"+i;
			put.add(Bytes.toBytes("objects"), Bytes.toBytes("parent"),Bytes.toBytes(t));
			put.add(Bytes.toBytes("attributes"), Bytes.toBytes("car"),Bytes.toBytes("Ferrari"));
			try {
			    htable.put(put);
				htable.flushCommits();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Put put1 = new Put(Bytes.toBytes(t));
	    	put1.add(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"),Bytes.toBytes("Man"));
			put1.add(Bytes.toBytes("attributes"), Bytes.toBytes("className"),Bytes.toBytes("zhu" + i));
			put1.add(Bytes.toBytes("attributes"), Bytes.toBytes("age"),Bytes.toBytes(i+""));
			put1.add(Bytes.toBytes("attributes"), Bytes.toBytes("car"),Bytes.toBytes("Benz"));
		   
		    try {
				htable.put(put1);
				htable.flushCommits();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		System.out.println("end");
		
	}
	
	
	/**
	 * 测试两个Join
	 */
	public void addTwoJoin(){
		System.out.println("start");
		for(int i=1; i<10; i++)
		{
			String prefix = getRandomPrefix();
			String indexPrefixTemp = prefix.substring(0, 1);
			String indexPrefix = getRegionNumber(indexPrefixTemp);
			String dataIndex = prefix+":judge:"+prefix.hashCode()+":"+i;
			
			Put put = new Put(Bytes.toBytes(dataIndex));	    	
	    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"),Bytes.toBytes("Jack"));
	    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("className"),Bytes.toBytes("zhu0"));
			put.add(Bytes.toBytes("attributes"), Bytes.toBytes("age"),Bytes.toBytes(i+""));
			String t = prefix.substring(0, 3)+"0:topic:"+prefix.hashCode()+":"+i;
			put.add(Bytes.toBytes("objects"), Bytes.toBytes("parent"),Bytes.toBytes(t));
			put.add(Bytes.toBytes("attributes"), Bytes.toBytes("car"),Bytes.toBytes("Ferrari"));
			try {
			    htable.put(put);
				htable.flushCommits();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Put put1 = new Put(Bytes.toBytes(t));
	    	put1.add(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"),Bytes.toBytes("Rose"));
			put1.add(Bytes.toBytes("attributes"), Bytes.toBytes("className"),Bytes.toBytes("zhu" + i));
			put1.add(Bytes.toBytes("attributes"), Bytes.toBytes("age"),Bytes.toBytes(i+""));
			put1.add(Bytes.toBytes("attributes"), Bytes.toBytes("car"),Bytes.toBytes("Benz"));
			String s = prefix.substring(0, 3)+"5:result:"+prefix.hashCode()+":"+i;
			put1.add(Bytes.toBytes("objects"), Bytes.toBytes("parent"),Bytes.toBytes(s));
		    try {
				htable.put(put1);
				htable.flushCommits();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			Put put2 = new Put(Bytes.toBytes(s));
	    	put2.add(Bytes.toBytes("attributes"), Bytes.toBytes("bdnm"),Bytes.toBytes("Baby"));
			put2.add(Bytes.toBytes("attributes"), Bytes.toBytes("className"),Bytes.toBytes("zhu" + i));
			put2.add(Bytes.toBytes("attributes"), Bytes.toBytes("age"),Bytes.toBytes(i+""));
			put2.add(Bytes.toBytes("attributes"), Bytes.toBytes("car"),Bytes.toBytes("Benz"));
		    try {
				htable.put(put2);
				htable.flushCommits();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    

		}

		System.out.println("end");
		
	}
	
	
	
	/**
	 * 测试模糊查询
	 */
	public void addDimSearch(){
		System.out.println("start");
		String prefix = getRandomPrefix();
		String indexPrefixTemp = prefix.substring(0, 1);
		String dataIndex = prefix+":bigdata:"+prefix.hashCode()+":"+0000;
		Put put = new Put(Bytes.toBytes(dataIndex));	    	
    	put.add(Bytes.toBytes("attributes"), Bytes.toBytes("cl"),Bytes.toBytes("Jas"));
		try {
		    htable.put(put);
			htable.flushCommits();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("end");
		
	}
	
	
	/**
	 * close the connection.
	 */
	public void close() {
		try {
			htable.close();
			connection.close();
			hBaseAdmin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
