package com.fc.classload.hyper;

public class InitTest {

	public static void main(String[] args) throws ClassNotFoundException {
		//对于静态字段，只有直接定义这个字段的类才会被初始化
		System.out.println(SubClass.value);
	}
}
