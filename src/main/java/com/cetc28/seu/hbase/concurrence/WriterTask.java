package com.cetc28.seu.hbase.concurrence;

import java.util.concurrent.Callable;

public class WriterTask implements Callable<Boolean> {

	private WriterService ws;
	
	@Override
	public Boolean call() throws Exception {
		
		//
		ws.serve();
		return true ;
	}


	public WriterTask() {
		super();
	}


	public WriterTask(WriterService ws) {
		super();
		this.ws = ws;
	}


	public WriterService  getWs() {
		return ws;
	}


	public void setEs(WriterService ws) {
		this.ws = ws;
	}
	
	

}
