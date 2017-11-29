package com.cetc.seu.spark.query;

import java.io.IOException;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;

public class testByCl {

	public static void main(String[] args) throws IOException {
		Scan scan = new Scan();
		RowFilter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("topic:1692963:2"));
		scan.setFilter(filter);
		ResultScanner rs = HbaseTool.getInstance().getTable(Configution.instanceTableName).getScanner(scan);
		for(Result r : rs){
			System.out.println(Bytes.toString(r.getRow()));
			for(Cell cell : r.rawCells())
				System.out.println(Bytes.toString(CellUtil.cloneFamily(cell))+" "+Bytes.toString(CellUtil.cloneQualifier(cell))+" "+Bytes.toString(CellUtil.cloneValue(cell)));
		}
	}

}
