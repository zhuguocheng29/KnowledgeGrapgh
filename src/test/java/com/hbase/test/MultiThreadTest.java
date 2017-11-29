
package com.hbase.test;

import junit.framework.TestCase;

class MyRunner implements Runnable {

	@Override
	public void run() {

		System.out.println("process" + Thread.currentThread().getName());
		for (int i = 0; i < 100000; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}

	}

}

public class MultiThreadTest extends TestCase {

	public void testMuti() {
		MyRunner mr = new MyRunner();

		for (int i = 0; i < 3; i++) {

			new Thread(mr, "thread" + i).start();
		}

		for(int i = 0 ; i < 100; i++){
			
			System.out.println("process main .............");
		}
	}
}

