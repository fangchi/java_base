package com.fc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSeq2Condition {

	static class PrintThread implements Runnable {
		private Lock lock;
		private Condition precondition;
		private Condition postcondition;
		private String  chars;
		private int  index;
		
		public PrintThread(Lock lock,String chars,Condition precondition,Condition postcondition,int index) {
			this.lock = lock;
			this.chars = chars;
			this.precondition = precondition;
			this.postcondition = postcondition;
			this.index = index;
		}

		@Override
		public void run() {
			try {
				lock.lock();
				if(!((index == 0) && "A".equals(chars))){
					precondition.await();
				}
				System.out.println(chars);
				Thread.sleep(100);
				if(!((index == 9) && "C".equals(chars))){
					postcondition.signal();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			
		}
	}
	

	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		Condition conditionA = lock.newCondition();
		Condition conditionB = lock.newCondition();
		Condition conditionC = lock.newCondition();
		
		for (int i = 0; i < 10 ; i++) {
			PrintThread  aPrintThread = new PrintThread(lock,"A",conditionC,conditionA,i);
			PrintThread  bPrintThread = new PrintThread(lock,"B",conditionA,conditionB,i);
			PrintThread  cPrintThread = new PrintThread(lock,"C",conditionB,conditionC,i);
			new Thread(cPrintThread).start();
			Thread.sleep(100);
			new Thread(bPrintThread).start();
			Thread.sleep(100);
			new Thread(aPrintThread).start();
		}
	}
}
