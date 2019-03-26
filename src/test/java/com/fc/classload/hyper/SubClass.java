package com.fc.classload.hyper;

public class SubClass extends SuperClasss{

	static {
		System.out.println("SubClass static ...");
	}
	
	
	
	public SubClass() {
		System.out.println("SubClass init ...");
	}
}
