package com.fc.concurrency.threadlocal;

import org.junit.Test;

public class ThreadLocalTest {
	
    private ThreadLocal<Object> threadLocal = new ThreadLocal<Object>(){
        /**
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected Object initialValue()
        {
            System.out.println("threadLocal 调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
            return 1;
        }
    };
    
    private InheritableThreadLocal<Object> inheritableThreadLocal = new InheritableThreadLocal<Object>(){
    	/**
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected Object initialValue()
        {
            System.out.println("inheritableThreadLocal调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
            return 1;
        }
    };
    
	
	@Test
	public void testTheadLocalTest(){
		System.out.println(threadLocal.get());
		threadLocal.set("pj");
		System.out.println(threadLocal.get());
	}
	
	
	@Test
	public void testTheadLocalTest2() throws InterruptedException{
		System.out.println(inheritableThreadLocal.get());
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				//这时输出的是1 而非重新初始化
				System.out.println(inheritableThreadLocal.get());
				inheritableThreadLocal.set("pj2");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//这时输出的是pj2 而非pj3
				System.out.println(inheritableThreadLocal.get());
			}
		};
		Thread thread =  new Thread(runnable);
		thread.start();
		Thread.sleep(1000);
		inheritableThreadLocal.set("pj3");
		while (true) {
			Thread.sleep(1000);
		}
	}
}
