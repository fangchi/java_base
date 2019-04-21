 package com.fc.concurrency.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

import com.fc.concurrency.annotations.ThreadSafe;
import com.fc.concurrency.annotations.ThreadUnSafe;

/**
 * @author chi.fang
 * @date 2019/04/05
 */

public class AtomicTest {

    @ThreadUnSafe
    static int count = 0;
    
    @ThreadSafe
    static AtomicInteger count2 =new AtomicInteger(0);
    
    @ThreadSafe
    //本质上是底层作了数组，添加通过hash进行分离 然后value求值得时候 最后求职 提高了并发 
    static LongAdder count3 =new LongAdder();
    
    @ThreadSafe
    static AtomicReference<Integer> count4 = new AtomicReference<Integer>(0);
    
    static ReentrantLock lock = new ReentrantLock();
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final Semaphore semaphore = new Semaphore(50);//控制同时有多少并发
        final CountDownLatch countDownLatch = new CountDownLatch(5000);//解决操作完成后再往下走
        for (int i = 0; i < 5000; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                     e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(count);
        System.out.println(count2.get());
        System.out.println(count3.longValue());
        System.out.println(count4.get());
        executorService.shutdown();
    }
    
    private static  void add() {
        lock.lock();
        count++;
        lock.unlock();
        count2.incrementAndGet();
        count3.add(1);
        count4.compareAndSet(0, 8);
    }
}
