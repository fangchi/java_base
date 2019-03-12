package com.fc.heap;

import org.junit.Test;

public class HTest {

	@Test
	public void testHeap(){
		 Heap h = new Heap(10);
		    h.insert(10);
		    h.insert(30);
		    h.insert(20);
		    h.insert(18);
		    h.insert(12);
		    h.displayHeap();
		    h.remove();
		    h.displayHeap();
	}
}
