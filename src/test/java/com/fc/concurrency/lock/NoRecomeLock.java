package com.fc.concurrency.lock;

/**
 * 非可重入锁
 * @author chi.fang
 *
 */
public class NoRecomeLock {

	  private boolean isLocked = false;
	    public synchronized void lock() throws InterruptedException{
	        while(isLocked){    
	            wait();
	        }
	        isLocked = true;
	    }
	    public synchronized void unlock(){
	        isLocked = false;
	        notify();
	    }
}
