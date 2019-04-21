package com.fc.concurrency.futruetask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureTest {

	// (1)线程池单个线程，线程池队列元素个数为1
	private final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 10, 2L, TimeUnit.MINUTES,
			new ArrayBlockingQueue<Runnable>(5), new ThreadPoolExecutor.DiscardPolicy());

	public static void main(String[] args) throws Exception {

		// (2)添加任务one
		Future<String> futureOne = executorService.submit(()->{
			System.out.println("start runable one");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "OK";
		});

		// (3)添加任务two
		Future futureTwo = executorService.submit(()->{
			System.out.println("start runable two");
		});

		// (4)添加任务three
		Future<String> futureThree = executorService.submit(()->{
		    try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			System.out.println("start runable three");
			return "OK3";
		});

		System.out.println("task two " + futureTwo.get());// (6)等待任务two执行完毕
		try {
		    System.out.println("task three " + futureThree.get(2,TimeUnit.SECONDS));// (7)等待任务three执行完毕
        } catch (Exception e2) {
            e2.printStackTrace();
        }
		System.out.println("task one " + futureOne.get());// (5)等待任务one执行完毕
		executorService.shutdown();// (8)关闭线程池，阻塞直到所有任务执行完毕
	}
}
