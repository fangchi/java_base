package com.fc.concurrency.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chi.fang
 * @date 2019/04/06
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 核心线程数量
        int corePoolSize = 10;
        // 线程池最大线程数 
        int maximumPoolSize = 20;
        // 阻塞队列 储存等待执行的任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(30);
        //when the number of threads is greater than
        //the core, this is the maximum time that excess idle threads
        //will wait for new tasks before terminating.
        long keepAliveTime = 10L;
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
            TimeUnit.MINUTES, workQueue, new ThreadPoolExecutor.DiscardPolicy());
        Future<String> res = executorService.submit(()->{
            System.out.println("aaa");
            Thread.sleep(5000);
            return "OK";
        });
        int active = executorService.getActiveCount();
        System.out.println(active);
        System.out.println(res.get());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        Future<String> res2 = executorService.submit(()->{
            System.out.println("aaa");
            return "OK";
        });
        System.out.println(res2.get());
    }
}
