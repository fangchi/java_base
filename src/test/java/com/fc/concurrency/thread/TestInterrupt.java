package com.fc.concurrency.thread;

import java.io.IOException;

public class TestInterrupt {

	public static void main(String[] args) throws IOException  {
		TestInterrupt test = new TestInterrupt();
        
        MyThreadNo threa2d = test.new MyThreadNo();
        threa2d.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
             
        }
        threa2d.interrupt();
    } 
     
    class MyThreadNo extends Thread{
        @Override
        public void run() {
        	 int i = 0;
             while(i<Integer.MAX_VALUE && !isInterrupted()){
                 System.out.println(i+" while循环");
                 i++;
             }
            System.out.println("run方法执行完毕");
        }
    }
}
