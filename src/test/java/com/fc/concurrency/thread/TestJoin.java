package com.fc.concurrency.thread;

import java.io.IOException;

public class TestJoin {
    
    public static void main(String[] args) throws IOException  {
        System.out.println("进入线程"+Thread.currentThread().getName());
        TestJoin test = new TestJoin();
        MyThread thread1 = test.new MyThread(5);
        MyThread thread2 = test.new MyThread(8);
        thread1.start();
        thread2.start();
        try {
            System.out.println("线程"+Thread.currentThread().getName()+"等待");
            thread1.join();
            thread2.join();
            System.out.println("线程"+Thread.currentThread().getName()+"继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } 
     
    class MyThread extends Thread{
    	
    	private int sleep = 0;
    	
    	public MyThread(int sleep) {
			this.sleep = sleep;
		}
    	
        @Override
        public void run() {
            System.out.println("进入线程"+Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(this.sleep * 1000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            System.out.println("线程"+Thread.currentThread().getName()+"执行完毕");
        }
    }
}