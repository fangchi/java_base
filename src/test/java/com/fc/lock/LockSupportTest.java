package com.fc.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

	 public static void main(String[] args)throws Exception {
	        Thread A = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                int sum = 0;
	                for(int i=0;i<10;i++){
	                    sum+=i;
	                }
	                LockSupport.park();
	                System.out.println(sum);
	            }
	        });
	        A.start();
	        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
	        Thread.sleep(3000);
	        LockSupport.unpark(A);//即使先执行unpark 后执行park也不会阻塞
	    }
}
