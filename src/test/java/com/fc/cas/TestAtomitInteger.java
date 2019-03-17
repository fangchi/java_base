package com.fc.cas;

import java.util.concurrent.atomic.AtomicBoolean;


public class TestAtomitInteger {

	public static void main(String[] args) {
		AtomicBoolean flag = new AtomicBoolean(true);
		Runnable t1 = new Runnable() {
			@Override
			public void run() {
				if (flag.compareAndSet(true,false)){
		            System.out.println(Thread.currentThread().getName()+":"+flag.get());
		            try {
		                Thread.sleep(5000);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		            flag.compareAndSet(false,true);
		        }else{
		            System.out.println("重试机制thread:"+Thread.currentThread().getName()+";flag:"+flag.get());
		            try {
		                Thread.sleep(500);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		            run();
		        }
			}
		};
		Thread thread1 = new Thread(t1);
		thread1.start();
		Thread thread2 = new Thread(t1);
		thread2.start();
	}
}
