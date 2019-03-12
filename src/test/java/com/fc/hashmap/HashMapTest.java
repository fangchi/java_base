package com.fc.hashmap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class HashMapTest {

	@Test
	public void testHsahMap() {
		HashMap<String, String> data = new HashMap<>(3);
		String val = data.put("fc", "pj");
		assertNull(val);
		String val2 = data.put("fc", "bcc");
		assertEquals("pj", val2);

		for (int i = 0; i < 30; i++) {
			data.put("fc" + i, "bcc");
		}
		data.remove("fc2");

	}

	static class MyThread extends Thread {
		public Map map;
		public String name;

		public MyThread(Map map, String name) {
			this.map = map;
			this.name = name;
		}

		public void run() {
			for (int j = 0; j < 100; j++) {
				double i = Math.random() * 100000;
				map.put("键" + i, "值" + i);
				map.remove("键" + i);
				System.out.println(name + "当前时间：" + i + "   size = " + map.size());
			}

		}
	}

	@Test
	public void testHsahMap2() {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 100; i++) {
			MyThread myThread = new MyThread(map, "线程名字：" + i);
			myThread.start();
		}

	}

	@Test
	public void testConcurrentHashMap() {
		//http://www.importnew.com/28263.html
		ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap<>();
		for (int i = 0; i < 1000000; i++) {
			hashMap.put(String.valueOf(i), String.valueOf(i));
		}
		
	}

}
