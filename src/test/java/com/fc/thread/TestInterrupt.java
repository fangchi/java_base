package com.fc.thread;

import java.io.IOException;

public class TestInterrupt {

	public static void main(String[] args) throws IOException  {
		TestInterrupt test = new TestInterrupt();
//        MyThread thread = test.new MyThread();
//        thread.start();
//        try {
//            Thread.currentThread().sleep(2000);
//        } catch (InterruptedException e) {
//             
//        }
//        thread.interrupt();
//        
        
        MyThreadNo threa2d = test.new MyThreadNo();
        threa2d.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
             
        }
        threa2d.interrupt();
    } 
     
    class MyThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println("进入睡眠状态");
                Thread.currentThread().sleep(10000);
                System.out.println("睡眠完毕");
            } catch (InterruptedException e) {
                System.out.println("得到中断异常");
            }
            System.out.println("run方法执行完毕");
        }
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
