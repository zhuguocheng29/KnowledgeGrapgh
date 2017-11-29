package com.cetc28.seu.spark.query.model.coprocessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.loading.theme.model.Property;

/**
 * scan全表输出到Property
 * @author Think
 *
 */
public class PropertyGet {

	public static void main(String[] args) {
		PropertyGet pg = new PropertyGet();
		pg.getProperty();
	}
	
	/**
	 * 返回Property列表
	 * @return
	 */
	public List<Property> getProperty(){
		
		String tableName = Configution.instanceTableName;
		Scan scan = new Scan();
		scan.addFamily(Bytes.toBytes("attributes"));
		scan.addFamily(Bytes.toBytes("objects"));
		scan.addFamily(Bytes.toBytes("array_objects"));
		
		HTableInterface hTable = null;
    	try {
			 hTable = HbaseTool.getInstance().getTable(tableName);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	List<Property> list = new ArrayList<Property>();
    	ResultScanner rs = null;
		try {
			rs = hTable.getScanner(scan);
			//parent不知道是什么待定，oid是region+主键还是只是主键？待定
			for(Result res : rs){
				HashMap<String,String> basePro = new HashMap<>();
				HashMap<String,String> childObj = new HashMap<>();
				HashMap<String,String> array_Obj = new HashMap<>();
				String rowKey = Bytes.toString(res.getRow()).substring(5);
				if(rowKey.equals("")){
//					System.out.println(Bytes.toString(res.getRow()));
					continue;
				}
//				System.out.println(rowKey+"=======");
				String[] temp = Bytes.toString(res.getRow()).split(":");
				String type = temp[1];
//				System.out.println(type+"=======");
				String parent = "";
				for(Cell cell : res.rawCells()){
					if(Bytes.toString(CellUtil.cloneFamily(cell)).equals("attributes")){
						basePro.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
					}else if(Bytes.toString(CellUtil.cloneFamily(cell)).equals("objects")){
						childObj.put(Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
					}
				}
				Property property = new Property(basePro,parent,childObj,array_Obj,rowKey,type);
				list.add(property);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		return list;
	}

}
