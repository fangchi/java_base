package com.fc.classload.self;

public class TestMyClassLoader {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		MyClassLoader loader = new MyClassLoader("E://tcrm//java_base//src//test//resources");
        Class<?> c = loader.loadClass("HelloWorld");
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
        System.out.println(c.getClassLoader().getParent().getParent());
        
        System.out.println(c.newInstance());
        
        Runtime.getRuntime().gc();
	}
}
