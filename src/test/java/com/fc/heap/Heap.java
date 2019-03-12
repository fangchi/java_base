package com.fc.heap;

/**
 * https://www.cnblogs.com/g177w/p/8469399.html
 * @author chi.fang
 *
 */
public class Heap {
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;

	public Heap(int mx) {
		maxSize = mx;
		heapArray = new Node[maxSize];
		currentSize = 0;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean insert(int key) {
		if (currentSize == maxSize)
			return false;
		Node thenode = new Node(key);
		heapArray[currentSize] = thenode;
		trickleUp(currentSize++);
		return true;
	}

	public void trickleUp(int index) {
		int parent = (index - 1) / 2;
		Node bottom = heapArray[index];
		while (index > 0 && heapArray[parent].getkey() < bottom.getkey()) {
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent - 1) / 2;
		}
		heapArray[index] = bottom;
	}

	public Node remove() {
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	}

	public void trickleDown(int index) {
		int largeChild;
		Node top = heapArray[index];
		while (index < currentSize / 2) {
			int leftChild = 2 * index + 1;
			int rightChild = 2 * index + 2;
			if (rightChild < currentSize && heapArray[leftChild].getkey() < heapArray[rightChild].getkey())
				largeChild = rightChild;
			else
				largeChild = leftChild;
			if (top.getkey() >= heapArray[largeChild].getkey())
				break;
			heapArray[index] = heapArray[largeChild];
			index = largeChild;
		}
		heapArray[index] = top;
	}

	public boolean change(int index, int newvalue) {
		if (index < 0 || index >= currentSize)
			return false;
		int oldvalue = heapArray[index].getkey();
		heapArray[index].setkey(newvalue);
		if (oldvalue < newvalue)
			trickleUp(index);
		else
			trickleDown(index);
		return true;
	}

	public void displayHeap() {
		System.out.print("heapArray:");
		for (int i = 0; i < currentSize; i++) {
			if (heapArray[i] != null)
				System.out.print(heapArray[i].getkey() + "  ");
			else
				System.out.print("--");
		}
		System.out.println("");
		int nBlanks = 32;
		int itemsPerrow = 1;
		int column = 0;
		int j = 0;
		String dots = "........................";
		System.out.println(dots + dots);
		while (currentSize > 0) {
			if (column == 0)
				for (int i = 0; i < nBlanks; i++) {
					System.out.print(" ");
				}
			System.out.print(heapArray[j].getkey());
			if (++j == currentSize)
				break;
			if (++column == itemsPerrow) {
				nBlanks /= 2;
				itemsPerrow *= 2;
				column = 0;
				System.out.println();
			} else
				for (int i = 0; i < nBlanks * 2 - 2; i++)
					System.out.print(' ');
		}
		System.out.println("\n" + dots + dots);
	}
}
