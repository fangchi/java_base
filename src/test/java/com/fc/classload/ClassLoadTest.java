package com.fc.classload;

public class ClassLoadTest {

	private static ClassLoadTest singleton = new ClassLoadTest();
    public static int value1;
    public static int value2 = 0;

    private ClassLoadTest(){
        value1++;
        value2++;
    }

    public static ClassLoadTest getInstance(){
        return singleton;
    }
    
    public static void main(String[] args) {
    	ClassLoadTest singleton = ClassLoadTest.getInstance();
        System.out.println("Singleton1 value1:" + singleton.value1);
        System.out.println("Singleton1 value2:" + singleton.value2);
	}
}

