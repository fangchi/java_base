package com.fc.thread;

public class TestWait {
	 public static Object object = new Object();
	    public static void main(String[] args) {
	        Thread1 thread1 = new Thread1();
	        Thread2 thread2 = new Thread2();
	         
	        thread1.start();
	         
	        try {
	            Thread.sleep(200);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	         
	        thread2.start();
	    }
	     
	    static class Thread1 extends Thread{
	        @Override
	        public void run() {
	            synchronized (object) { //获取对象锁 1
	                try {
	                    object.wait(); //释放对象锁 2
	                } catch (InterruptedException e) {//待对象锁4 释放后 执行
	                }
	                System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
	            }
	        }
	    }
	     
	    static class Thread2 extends Thread{
	        @Override
	        public void run() {
	            synchronized (object) {//待 2 后进入
	                object.notify(); //唤醒对象 3
	                System.out.println("线程"+Thread.currentThread().getName()+"调用了object.notify()");
	            } //释放对象锁  4
	            try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            System.out.println("线程"+Thread.currentThread().getName()+"释放了锁");
	        }
	    }
}
