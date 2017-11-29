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
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.protobuf.ResponseConverter;
import org.apache.hadoop.hbase.regionserver.InternalScanner;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchDimService;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchRequest;
import com.cetc28.seu.spark.query.model.coprocessor.SearchRowKey.SearchResponse;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.google.protobuf.Service;

public class SearchRowKeyDimEndPoint extends SearchDimService implements Coprocessor, CoprocessorService{

	private RegionCoprocessorEnvironment env;
	private static final Log LOG = LogFactory.getLog(SearchRowKeyDimEndPoint.class);
	@Override
	public Service getService() {
		return null;
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
	public void getRowKeyDim(RpcController controller, SearchRequest request, RpcCallback<SearchResponse> done) {
		//获取该region的起始行键,不知道先确认
				String startKeyPrefix = Bytes.toString(env.getRegion().getStartKey());
				if(startKeyPrefix.equals("") || startKeyPrefix.isEmpty() || startKeyPrefix.equals("9999a"))
				{
					return;
				}
				else
				{
					LOG.info("dim search=============");
				}
//					String family = request.getFamily();
//					String column = request.getColumn();
//					String value = request.getValue();
//					
//					//flag,确定是哪种查询，默认为0
//					int t = 0;
//					//对scan进行区间扫描
//					String startKeyPrefixChanged = startKeyPrefix.substring(0, 3)+"1";
//					
//					Scan scan = new Scan();
//					String startKey = null;
//					if(family.equals("") && column.equals("")){
//						startKey = startKeyPrefixChanged+":";
//						scan.setStartRow(Bytes.toBytes(startKey));
//						scan.addFamily(Bytes.toBytes("attributes"));
//						scan.addFamily(Bytes.toBytes("objects"));
//						scan.addFamily(Bytes.toBytes("array_objects"));
//						Filter filter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(value));
//						scan.setFilter(filter);
//						t = 1;
//					}else if (!family.isEmpty() && !column.isEmpty() && value.equals("")){
//						//"attributes:bdnm"
//						startKey = startKeyPrefixChanged+":";
//						scan.setStartRow(Bytes.toBytes(startKey));
//						Filter filter1 = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(value));
//						Filter filter2 = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(value));
//						scan.setFilter(filter1);
//						scan.setFilter(filter2);
//						t = 1;
//					}else{
//						startKey = startKeyPrefixChanged+":";
//						scan.setStartRow(Bytes.toBytes(startKey));
//						scan.addFamily(Bytes.toBytes("attributes"));
//						scan.addFamily(Bytes.toBytes("objects"));
//						scan.addFamily(Bytes.toBytes("array_objects"));
//						SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(column), CompareFilter.CompareOp.EQUAL, new SubstringComparator(value));
//						filter.setFilterIfMissing(true);
//						scan.setFilter(filter);
//					}
//
//					//LOG.info("SearchRowKeyWithoutIndex startKey: " + startKey);
//					SearchResponse response = null;
//					SearchResponse.Builder builder = SearchResponse.newBuilder();
//					InternalScanner scanner = null; 
//					List<String> arrayList = new ArrayList<String>();		
//					try {
//						 scanner = env.getRegion().getScanner(scan);
//						 List<Cell> result = new ArrayList<Cell>();
//						 boolean hasMore = false;
//						 do{
//							 String returnRowKey = "";
//							 hasMore = scanner.next(result);
//							 if(result.size() == 0)
//							 {
//								 break;			
//							 }
//							 else
//							 {
//								 String temp = Bytes.toString(CellUtil.cloneRow(result.get(0)));
//								 String[] partRowKey = temp.split("_");
//								 returnRowKey = partRowKey[1];
//								 LOG.info("returnRowKey dim =========: " + returnRowKey);
//								 arrayList.add(returnRowKey);
//								 result.clear();
//							 }
//						 }while(hasMore);
//					 // 包括?s attribute:className ?o查询	 
//						 builder.addAllRowKey(arrayList);
//						 response = builder.build();		
//						 
//					} catch (IOException e) {
//						ResponseConverter.setControllerException(controller, e);
//					}
//					finally {
//			            if (scanner != null) {
//			                try {
//			                    scanner.close();
//			                } catch (IOException ignored) {}
//			            }
//			        }
//			        done.run(response);
//				}		
	}

}
