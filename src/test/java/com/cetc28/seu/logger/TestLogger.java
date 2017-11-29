package com.cetc28.seu.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TestLogger {
	private static Logger log=LoggerFactory.getLogger(TestLogger.class);
	public static void main(String args[]){
		log.info("yes");
		int id=1;
		log.info("error: {}",id);
		log.warn("warn : {}",++id);
	}
}
