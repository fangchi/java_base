package com.fc.concurrency.futruetask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 可取消的Task
 * 
 * 规定时间内获取
 * @author chi.fang
 *
 */
public class FutrueTaskTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		FutureTask<Object> task = new FutureTask<Object>(new Callable<Object>() {
			
		    //可获取返回值
		    @Override
			public Object call() throws Exception {
				for (int i = 0; i < 1000; i++) {
					Thread.sleep(200);
					System.out.println("running i:"+i);
				}
				System.out.println("run finish");
				return new Object();
			}
		});
		Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(1500);
        task.cancel(true);
        try {
        	Object object = task.get(3, TimeUnit.SECONDS);
    		System.out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println(task.isCancelled());
		System.out.println(task.isDone());
	}
}
