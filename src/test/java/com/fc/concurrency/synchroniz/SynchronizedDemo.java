package com.fc.concurrency.synchroniz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedDemo {

    private Object lock = new Object();
    
    
	public void method() throws InterruptedException {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(50);
                System.out.println("Thread: "+ Thread.currentThread().getName() +"method "+i);
            }
           
        }
    }
	
	
	public static void main(String[] args) throws InterruptedException {
	    SynchronizedDemo demo = new SynchronizedDemo();
	    ExecutorService executorService = Executors.newFixedThreadPool(3);
	    for (int i = 0; i < 10; i++) {
	        executorService.execute(()->{
	            try {
	                demo.method();
	            } catch (InterruptedException e) {
	                 e.printStackTrace();
	            }
	        });
        }
	    //executorService.shutdown();
    }
}
