package com.fc.concurrency.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chi.fang
 * @date 2019/04/06
 */
public class ThreadPoolExecutorTest2 {

    public static class PrintThread extends Thread{
        
        private String charactor;
        
        public PrintThread(String charactor){
            this.charactor = charactor;
        }
        
        public void run(){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                 e.printStackTrace();
            }
            System.out.println(charactor);
        }
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        PrintThread printAThread = new ThreadPoolExecutorTest2.PrintThread("A");
        PrintThread printBThread =new ThreadPoolExecutorTest2.PrintThread("B");
        PrintThread printCThread = new ThreadPoolExecutorTest2.PrintThread("C");
        for (int i = 0; i < 10; i++) {
            executor.submit(printAThread);
            executor.submit(printBThread);
            executor.submit(printCThread);
            
        }
        executor.shutdown();
    }
}


