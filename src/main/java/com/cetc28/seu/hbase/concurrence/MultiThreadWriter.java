package com.cetc28.seu.hbase.concurrence;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MultiThreadWriter {

	private ThreadPoolExecutor threadPoolExecutor;
	private List<WriterTask> taskList;

	public MultiThreadWriter() {
		super();
	}

	public MultiThreadWriter(ThreadPoolExecutor threadPoolExecutor, List<WriterTask> taskList) {
		super();
		this.threadPoolExecutor = threadPoolExecutor;
		this.taskList = taskList;
	}

	public MultiThreadWriter(List<WriterTask> taskList) {
		super();
		int threads = Runtime.getRuntime().availableProcessors() * 4;
		threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threads);
		this.taskList = taskList;
	}

	public void executeTask() throws InterruptedException {

		List<Future<Boolean>> t = threadPoolExecutor.invokeAll(taskList);

		do {
			
			System.out.println("total numbers of finishing task:  " + threadPoolExecutor.getCompletedTaskCount());

			for (int i = 0; i < t.size(); ++i) {
				Future<Boolean> f = t.get(i);
				if(f.isDone())
					System.out.println("task " + i + "has finished");
			}
			
		

		} while (threadPoolExecutor.getCompletedTaskCount() < taskList.size());

	}

}
