 package com.fc.concurrency.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.fc.concurrency.annotations.ThreadSafe;
import com.fc.concurrency.annotations.ThreadUnSafe;

/**
 * @author chi.fang
 * @date 2019/04/05
 */

public class ReentrantReadWriteLockTest {

    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    static Lock readlock = lock.readLock();
    
    static Lock writeLock =lock.writeLock();
    
    
    static Map<String, String> data = new HashMap<>();
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final Semaphore semaphore = new Semaphore(50);//控制同时有多少并发
        final CountDownLatch countDownLatch = new CountDownLatch(5000);//解决操作完成后再往下走
        for (int i = 0; i < 5000; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
//                    data.put(""+i, i+"");
//                    data.put(""+i, i+"");
                    semaphore.release();
                } catch (InterruptedException e) {
                     e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
      
        executorService.shutdown();
    }
    
    private static  String get(String key) {
        try {
            readlock.lock();
            return data.get(key);
        } finally{
            readlock.unlock();
        }
    }
    
    private static  String write(String key,String val) {
        try {
            writeLock.lock();
            return data.get(key);
        } finally{
            writeLock.unlock();
        }
    }
}
