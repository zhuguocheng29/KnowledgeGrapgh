package com.cetc28.seu.loading.theme.output;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetc28.seu.hbase.concurrence.WriterService;
import com.cetc28.seu.loading.theme.parser.ObjectParser;
import com.cetc28.seu.loading.theme.server.ExtractUtils;


public class ThemeWriter implements WriterService{
	private HashMap<String,String> mapPartition;
	private static Logger log=null;
	public ThemeWriter(HashMap<String,String> mapPartition){
		this.mapPartition=mapPartition;
		ThemeWriter.log=LoggerFactory.getLogger(this.getClass());
	}
	@Override
	public void serve() {
		int pageSize=1000;
		HbaseWritter writter;
		try {
			writter = new HbaseWritter();
			ObjectParser oParser=new ObjectParser(writter);
			for(Map.Entry<String,String> entry : mapPartition.entrySet()){
				int totalCount;
				try {
					totalCount = ExtractUtils.getNums(entry.getKey(), Class.forName(entry.getValue()));
					int numPage= ExtractUtils.setPageNums(pageSize, totalCount);

					for (int ipage = 1; ipage < numPage; ipage++) {
						
							List<Object> datas = ExtractUtils.extract(entry.getKey(), Class.forName(entry.getValue()), ipage, pageSize);
							if (datas != null && datas.size() != 0) {
								oParser.extractTreeObj(datas, Class.forName(entry.getValue()), entry.getKey());
							}
							else{
								break;
							}				
					}
				}
				 catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					 log.warn(e.getMessage());
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage());
		} 
		
	}
	
}
