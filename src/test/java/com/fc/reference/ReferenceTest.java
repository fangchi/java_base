package com.fc.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {

	public static void main(String[] args) {
		Object obj = new Object();
		SoftReference<Object> sf = new SoftReference<Object>(obj);
		obj = null;
		for (int i = 0; i < 10; i++) {
			System.gc();
			System.out.println(sf.get());
		}
		
		Object obj2 = new Object();
		WeakReference<Object> wf = new WeakReference<Object>(obj2);
		obj2 = null;
		for (int i = 0; i < 10; i++) {
			System.gc();
			System.out.println(wf.get());
			System.out.println(wf.isEnqueued());
		}
	}
}
