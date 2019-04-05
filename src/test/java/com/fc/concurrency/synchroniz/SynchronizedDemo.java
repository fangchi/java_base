package com.fc.concurrency.synchroniz;

public class SynchronizedDemo {

	public void method() {
        synchronized (this) {
            System.out.println("Method 1 start");
        }
    }
}
