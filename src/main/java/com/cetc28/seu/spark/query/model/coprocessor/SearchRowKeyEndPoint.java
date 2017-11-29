package com.cetc28.seu.spark.query.model.coprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.Coprocessor;
import org.apache.hadoop.hbase.CoprocessorEnvironment;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.coprocessor.CoprocessorException;
import org.apache.hadoop.hbase.coprocessor.CoprocessorService;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.protobuf.ResponseConverter;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchRequest;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchResponse;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchService;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.google.protobuf.Service;



/**
 * 使用协处理器通过scan索引表的方法找到对应的行键
 * @author Think
 *
 */
public class SearchRowKeyEndPoint extends SearchService implements Coprocessor, CoprocessorService {

	private RegionCoprocessorEnvironment env;
	private static final Log LOG = LogFactory.getLog(SearchRowKeyEndPoint.class);
	
	@Override
	public Service getService() {
		return this;
	}
	

	@Override
	public void start(CoprocessorEnvironment env) throws IOException {
		if (env instanceof RegionCoprocessorEnvironment) {
			this.env = (RegionCoprocessorEnvironment) env;
		} else {
			throw new CoprocessorException("Must be loaded on a table region!");
		}
		
	}

	@Override
	public void stop(CoprocessorEnvironment env) throws IOException {
	}

	@Override
	public void getRowKey(RpcController controller, SearchRequest request, RpcCallback<SearchResponse> done) {
		//获取该region的起始行键,不知道先确认
		String startKeyPrefix = Bytes.toString(env.getRegion().getStartKey());
		if(startKeyPrefix.equals("") || startKeyPrefix.isEmpty() || startKeyPrefix.equals("9999a"))
		{
			return;
		}
		else
		{
			String family = request.getFamily();
			String column = request.getColumn();
			String value = request.getValue();
		
			//判断是不是模糊搜索，即value中有"()"
			if(!value.equals("") && value.contains("()")){
				Scan scan = new Scan();
				String startKey = null;
				String stopKey = null;
				
				value = value.substring(0, value.length()-2);
				stopKey = startKeyPrefix.substring(0, startKeyPrefix.length()-1)+"1:";
				Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(value));
				scan.setStopRow(Bytes.toBytes(stopKey));
				scan.addFamily(Bytes.toBytes("index"));
				
				SearchResponse response = null;
				SearchResponse.Builder builder = SearchResponse.newBuilder();
				InternalScanner scanner = null; 
				List<String> arrayList = new ArrayList<String>();
				try {
					 scanner = env.getRegion().getScanner(scan);
					 List<Cell> result = new ArrayList<Cell>();
					 boolean hasMore = false;
					 do{
						 String returnRowKey = "";
						 hasMore = scanner.next(result);
						 if(result.size() == 0)
						 {
							 break;			
						 }
						 else
						 {
							 String temp = Bytes.toString(CellUtil.cloneRow(result.get(0)));
//							 LOG.info("dim temp Key: ============" + temp);
							 String[] partRowKey = temp.split("_");
							 returnRowKey = partRowKey[1];
//							 LOG.info("dim returnRowKey: =============" + returnRowKey);
							 arrayList.add(returnRowKey);
							 result.clear();
						 }
					 }while(hasMore);
					 builder.addAllRowKey(arrayList);
					 response = builder.build();		
				} catch (IOException e) {
					ResponseConverter.setControllerException(controller, e);
				}
				finally {
		            if (scanner != null) {
		                try {
		                    scanner.close();
		                } catch (IOException ignored) {}
		            }
		        }
		        done.run(response);
			}else{
				//精确搜索，对scan进行区间扫描
				Scan scan = new Scan();
				String startKey = null;
				String stopKey = null;
				if(family.equals("") && column.equals("")){
					startKey = startKeyPrefix+":"+value+":";
					stopKey = startKeyPrefix+":"+value+";";
					scan.setStartRow(Bytes.toBytes(startKey));
					scan.setStopRow(Bytes.toBytes(stopKey));
				}else if (!family.isEmpty() && !column.isEmpty() && value.equals("")){
					scan.addFamily(Bytes.toBytes("index"));
					//"attributes:bdnm"
					String temp = family+":"+column;
					Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(temp));
					scan.setFilter(filter);
				}else{
					startKey = startKeyPrefix+":"+value+":"+family+":"+column;
					stopKey = startKeyPrefix+":"+value+":"+family+":"+column+"`"; //unicode编码比_大
					scan.setStartRow(Bytes.toBytes(startKey));
					scan.setStopRow(Bytes.toBytes(stopKey));
				}
			
//				LOG.info("startKey: " + startKey);
//				LOG.info("stopKey: " + stopKey);
				SearchResponse response = null;
				SearchResponse.Builder builder = SearchResponse.newBuilder();
				InternalScanner scanner = null; 
				List<String> arrayList = new ArrayList<String>();
	
				try {
					 scanner = env.getRegion().getScanner(scan);
					 List<Cell> result = new ArrayList<Cell>();
					 
//					 //关键字查询（包括模糊查询）
//					 if(family.equals("") && column.equals("")){
//						 boolean hasMore1 = false;
//						 do{
//							 String returnRowKey = "";
//							 hasMore1 = scanner.next(result);
//							 
//							 if(result.size() == 0)
//							 {
//								 result.clear();
//								 Scan dimScan = new Scan();
//	//							 startKey = startKeyPrefix.substring(0, startKeyPrefix.length()-1)+1+":";
//	//							 dimScan.setStartRow(Bytes.toBytes(startKey));
//								 dimScan.addFamily(Bytes.toBytes("attributes"));
//								 dimScan.addFamily(Bytes.toBytes("objects"));
//								 dimScan.addFamily(Bytes.toBytes("array_objects"));
//								 Filter filter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(value));
//								 dimScan.setFilter(filter);
//								 scanner = env.getRegion().getScanner(dimScan);
//								 boolean hasMore = false;
//								 int count = 0;
//								 do{
//									 //模糊查询最多10个返回
//									 if(count > 10){
//										 break;
//									 }
//									 hasMore = scanner.next(result);
////									 LOG.info("hasMore: " + startKeyPrefix + hasMore + result.size());
//									 if(result.size() == 0)
//									 {
//										 break;
//									 }
//									 else
//									 {
//										 String RowKey = Bytes.toString(CellUtil.cloneRow(result.get(0)));
////										 LOG.info("RowKey===: " + RowKey);
//										 arrayList.add(RowKey);
//										 result.clear();
//										 count++;
//									 }
//								 }while(hasMore);						 
//							 }
//							 else
//							 {
//								 String temp = Bytes.toString(CellUtil.cloneRow(result.get(0)));
////								 LOG.info("Key: " + temp);
//								 String[] partRowKey = temp.split("_");
//								 returnRowKey = partRowKey[1];
////								 LOG.info("RowKey: " + returnRowKey);
//								 arrayList.add(returnRowKey);
//								 result.clear();
//							 }
//						 }while(hasMore1);
//					 // 包括?s attribute:className ?o查询	 
//					 }else{
						 //不是模糊查询
						 boolean hasMore = false;
						 do{
							 String returnRowKey = "";
							 hasMore = scanner.next(result);
							 if(result.size() == 0)
							 {
								 break;			
							 }
							 else
							 {
								 String temp = Bytes.toString(CellUtil.cloneRow(result.get(0)));
//								 LOG.info("temp Key: " + temp);
								 String[] partRowKey = temp.split("_");
								 returnRowKey = partRowKey[1];
//								 LOG.info("returnRowKey: " + returnRowKey);
								 arrayList.add(returnRowKey);
								 result.clear();
							 }
						 }while(hasMore);
//					 }
					 
					 builder.addAllRowKey(arrayList);
					 response = builder.build();		
					 
				} catch (IOException e) {
					ResponseConverter.setControllerException(controller, e);
				}
				finally {
		            if (scanner != null) {
		                try {
		                    scanner.close();
		                } catch (IOException ignored) {}
		            }
		        }
		        done.run(response);
			}
		}
	}

}


/*if(family.equals("") && column.equals("")){
	 boolean judge = false;
	 judge = scanner.next(result);
	 LOG.info("judge: " + startKeyPrefix + judge);
	 if(judge == true){
		 boolean hasMore = judge;
		 while(hasMore == true){
			 String returnRowKey = "";
			 String temp = Bytes.toString(CellUtil.cloneRow(result.get(0)));
			 LOG.info("Key: " + temp);
			 String[] partRowKey = temp.split("_");
			 returnRowKey = partRowKey[1];
			 LOG.info("Key word: " + returnRowKey);
			 arrayList.add(returnRowKey);
			 result.clear();
			 hasMore = scanner.next(result);
			 if(result.size() == 0){
				 break;
			 }
		 }
			 
	 }else if(judge == false){
		 result.clear();
		 Scan dimScan = new Scan();
		 dimScan.addFamily(Bytes.toBytes("attributes"));
		 dimScan.addFamily(Bytes.toBytes("objects"));
		 dimScan.addFamily(Bytes.toBytes("array_objects"));
		 Filter filter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(value));
		 dimScan.setFilter(filter);
		 scanner = env.getRegion().getScanner(dimScan);
		 boolean hasMore = false;
		 int count = 0;
		 do{
			 //模糊查询最多6个返回
			 if(count > 5){
				 break;
			 }
			 hasMore = scanner.next(result);
			 LOG.info("hasMore: " + startKeyPrefix + hasMore + result.size());
			 if(result.size() == 0)
			 {
				 break;
			 }
			 else
			 {
				 String RowKey = Bytes.toString(CellUtil.cloneRow(result.get(0)));
				 LOG.info("RowKey: " + RowKey);
				 arrayList.add(RowKey);
				 result.clear();
				 count++;
			 }
		 }while(hasMore);
	 }
	 
}*/




/* Scan dimScan = new Scan();
//应该用SingleColumnfilter,在attribute和object里搜索即可
Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(value));
dimScan.setFilter(filter);
//添加cache
//dimScan.setCaching(1000000);
InternalScanner scanner1 = env.getRegion().getScanner(dimScan);
int counter = 0;
while(scanner1.next(result)){
	 if(counter >= 10){
		 break;
	 }
	 counter++;
	 String returnRowKey = "";
	 String temp = Bytes.toString(CellUtil.cloneRow(result.get(0)));
	 LOG.info("Key: " + temp);
	 String[] partRowKey = temp.split("_");
	 returnRowKey = partRowKey[1];
	 LOG.info("RowKey: " + returnRowKey);
	 arrayList.add(returnRowKey);
}*/