package com.cetc28.seu.loading.theme.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import com.cetc28.seu.hbase.Configution;
import com.cetc28.seu.hbase.HbaseTool;
import com.cetc28.seu.hbase.concurrence.MultiThreadWriter;
import com.cetc28.seu.hbase.concurrence.TaskGenerate;
import com.cetc28.seu.hbase.concurrence.WriterTask;
import com.cetc28.seu.loading.theme.output.HbaseWritter;
import com.cetc28.seu.loading.theme.output.ThemeWriter;
import com.cetc28.seu.loading.theme.parser.ObjectParser;
import com.cetc28.seu.loading.theme.parser.SchemaParser;
import com.cetc28.seu.loading.theme.parser.XMLParser;

public class ExtractService extends TaskGenerate {
	private String path = Configution.path;
	private static Logger log = null;
	private int patitionNum = 5;
	HBaseAdmin hBaseAdmin = HbaseTool.getAdmin();
	private static String TABLE_NAME ;
	private static  String SCHEMA_NAME;
	/**
	 * 
	 */
	public ExtractService(String TABLE_NAME) {
		ExtractService.log = LoggerFactory.getLogger(this.getClass());
		ExtractService.TABLE_NAME=TABLE_NAME;
	}
	public ExtractService(String TABLE_NAME,String SCHEMA_NAME) {
		ExtractService.log = LoggerFactory.getLogger(this.getClass());
		ExtractService.TABLE_NAME=TABLE_NAME;
		ExtractService.SCHEMA_NAME=SCHEMA_NAME;
	}
	
	/**
	 * 使用多线程抽取主题数据
	 */
	public void extractByMultilThread() {
		try {
			if (!hBaseAdmin.tableExists(TABLE_NAME)) {
				String [] families={"attributes","objects","array_objects"};
				HbaseTool.createTable(TABLE_NAME, families);
				List<WriterTask> tasks=this.generate();
				MultiThreadWriter mtw = new MultiThreadWriter(tasks);
				mtw.executeTask();
			}
		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}

	/**
	 * 抽取所有主题数据
	 */
	public void extractAllTheme()  {

		HashMap<String, String> themeNameAndClass = new HashMap<>();
		XMLParser xmlParser = new XMLParser();
		xmlParser.patchParse(path, themeNameAndClass);
		HbaseWritter writter=null;
		try {
			writter = new HbaseWritter(TABLE_NAME);
		} catch (IOException e1) {
			log.error(e1.getMessage());
		}
		ObjectParser oParser = new ObjectParser(writter);
		int pageSize = 1000;
		
		for (Map.Entry<String, String> entry : themeNameAndClass.entrySet()) {
			int totalCount;
			try {
				totalCount = ExtractUtils.getNums(entry.getKey(), Class.forName(entry.getValue()));int numPage= ExtractUtils.setPageNums(pageSize, totalCount);

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
				 log.warn(e.getMessage());
			}
		}
	}
	public void extractAllSchema() {
		HashMap<String, String> maps = new HashMap<String, String>();
		XMLParser xp = new XMLParser();
		xp.patchParse(path, maps);
		HbaseWritter hw;
		try {
			hw = new HbaseWritter(SCHEMA_NAME);
			SchemaParser sp = new SchemaParser(hw);
			for (Entry<String, String> map : maps.entrySet()) {
				try {
					sp.setThemeName(map.getKey());
					String className = map.getValue();
					sp.dfs(Class.forName(className), className, " ");
				} catch (ClassNotFoundException e) {
					log.warn(e.getMessage());
				}
			}
		} catch (IOException e1) {
			log.warn(e1.getMessage());
		}

	}
	@Override
	public List<WriterTask> generate() throws IOException {

		List<WriterTask> tasks=new ArrayList<>();
		HashMap<String, String> themeNameAndClass = new HashMap<>();
		XMLParser xmlParser = new XMLParser();
		xmlParser.patchParse(path, themeNameAndClass);
		int nums = themeNameAndClass.size();
		int partitionSize=0;
		if(nums>patitionNum) partitionSize = nums / patitionNum;
		else partitionSize=nums;
		Set<Entry<String, String>> entries = themeNameAndClass.entrySet();
		Iterator<Entry<String, String>> itr = entries.iterator();
		for (int i = 0; i < patitionNum; i++) {
			HashMap<String, String> mapPartition = new HashMap<>();
			int curNum = 0;
			for (; curNum < partitionSize; curNum++) {
				Entry<String, String> curEntry = itr.next();
				mapPartition.put(curEntry.getKey().trim(), curEntry.getValue().trim());
			}
			ThemeWriter tw=new ThemeWriter(mapPartition);
			WriterTask wt=new WriterTask(tw);
			tasks.add(wt);
		}
		return tasks;
	}
}
