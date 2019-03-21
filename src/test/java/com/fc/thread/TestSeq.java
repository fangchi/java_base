package com.fc.thread;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class TestSeq {

	public static void main(String[] args) throws IOException, InterruptedException {
		for (int i = 0; i < 10; i++) {
			CountDownLatch countDownLatch = new CountDownLatch(3);
			ThreadA threadA = new ThreadA(countDownLatch,"A",null);
			ThreadA threadB = new ThreadA(countDownLatch,"B",threadA);
			ThreadA threadC = new ThreadA(countDownLatch,"C",threadB);
			threadC.start();
			threadB.start();
			threadA.start();
			countDownLatch.await();
		}
	}

	static class ThreadA extends Thread {
		CountDownLatch countDownLatch = null;
		String str = null;
		Thread pre = null;
		
		public ThreadA(CountDownLatch countDownLatch,String s,Thread pre){
			this.countDownLatch = countDownLatch;
			this.str =s;
			this.pre = pre;
		}
		
		public void run() {
			if(pre!=null){
				synchronized (this.pre) {
					try {
						this.pre.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println(this.str);
			countDownLatch.countDown();
		}
	}
}
