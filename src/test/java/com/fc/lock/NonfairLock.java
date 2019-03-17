package com.fc.lock;

import java.util.concurrent.locks.ReentrantLock;


public class NonfairLock {

	/**
     *     false 表示 ReentrantLock 的非公平锁
     */
    private  ReentrantLock lock = new ReentrantLock(false);

    public  void testFail(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() +"获得了锁");
        }finally {
        	System.out.println(Thread.currentThread().getName() +"释放了锁");
            lock.unlock();
        }
    }
    public static void main(String[] args) {
    	NonfairLock nonfairLock = new NonfairLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName()+"启动");
            nonfairLock.testFail();
        };
        Thread[] threadArray = new Thread[10];
        for (int i=0; i<10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i=0; i<10; i++) {
            threadArray[i].start();
        }
    }
}
