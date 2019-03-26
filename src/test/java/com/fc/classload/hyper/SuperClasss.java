package com.fc.classload.hyper;

public class SuperClasss {

	public static int value =1;
	
	static {
		System.out.println("SuperClasss static ...");
	}
	
	public SuperClasss() {
		System.out.println("SuperClasss init ...");
	}
}
