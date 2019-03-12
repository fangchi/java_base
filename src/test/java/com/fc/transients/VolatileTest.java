package com.fc.transients;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * {@link https://www.cnblogs.com/chengxiao/p/6528109.html}
 * volatile具备两种特性，第一就是保证共享变量对所有线程的可见性。将一个共享变量声明为volatile后，会有以下效应：
　　　　1.当写一个volatile变量时，JMM会把该线程对应的本地内存中的变量强制刷新到主内存中去；
　　　　2.这个写会操作会导致其他线程中的缓存无效。
	  3.禁止指令重排序优化
	  	重排序是指编译器和处理器为了优化程序性能而对指令序列进行排序的一种手段。但是重排序也需要遵守一定规则
	  	> 重排序操作不会对存在数据依赖关系的操作进行重排序
	  	比如：a=1;b=a; 这个指令序列，由于第二个操作依赖于第一个操作，所以在编译时和处理器运行时这两个操作不会被重排序
	  	> 重排序是为了优化性能，但是不管怎么重排序，单线程下程序的执行结果不能被改变
	  	比如：a=1;b=2;c=a+b这三个操作，第一步（a=1)和第二步(b=2)由于不存在数据依赖关系，所以可能会发生重排序，
	  		但是c=a+b这个操作是不会被重排序的，因为需要保证最终的结果一定是c=a+b=3
 * @author chi.fang
 *
 */
public class VolatileTest {

	public static volatile int num = 0;
	//使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch = new CountDownLatch(30);
    
  //使用原子操作类
    public static AtomicInteger num2 = new AtomicInteger(0);
    
	@Test
	public void testVolatileTest() throws IOException, ClassNotFoundException, InterruptedException {
		 //开启30个线程进行累加操作
        for(int i=0;i<30;i++){
            new Thread(){
                public void run(){
                    for(int j=0;j<10000;j++){
                    	/**
                    	 * 1.读取
                    	 * 2.加一
                    	 * 3.赋值
                    	 * 　所以，在多线程环境下，有可能线程A将num读取到本地内存中，此时其他线程可能已经将num增大了很多，
                    	 * 线程A依然对过期的num进行自加，重新写到主存中，最终导致了num的结果不合预期，而是小于30000
                    	 */
                        num++;//因为num++不是个原子性的操作，而是个复合操作
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        //等待计算线程执行完
        countDownLatch.await();
        //
        assertNotEquals(300000, num);
	}
	
	
	@Test
	public void testVolatileTest2() throws IOException, ClassNotFoundException, InterruptedException {
		 //开启30个线程进行累加操作
        for(int i=0;i<30;i++){
            new Thread(){
                public void run(){
                    for(int j=0;j<10000;j++){
                    	//原子性的num++,通过循环CAS方式
                    	 num2.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        //等待计算线程执行完
        countDownLatch.await();
       assertEquals(300000, num2.get());
	}
}
