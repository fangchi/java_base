package com.fc.classload;

import java.net.URL;

import com.fc.classload.hyper.InitTest;

public class ClassLoadTest2 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (URL url : urls) {
			System.out.println(url.toExternalForm());
		}

		ClassLoader c = ClassLoadTest2.class.getClassLoader();
		System.out.println(c);
		ClassLoader c1 = c.getParent(); // 获取c这个类加载器的父类加载器
		System.out.println(c1);
		ClassLoader c2 = c1.getParent();// 获取c1这个类加载器的父类加载器
		System.out.println(c2);
		
		Class<InitTest> class1= (Class<InitTest>) c.loadClass("com.fc.classload.hyper.InitTest");
		InitTest object = class1.newInstance();
		System.out.println(object);
	}
}
