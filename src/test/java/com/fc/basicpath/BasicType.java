package com.fc.basicpath;

/**
 * 测试类
 */
public class BasicType {

	public static void main(String[] args) {
		  
		Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
         
        System.out.println(i1==i2);
        System.out.println(i3==i4);
        System.out.println(i3.intValue()==200);
        System.out.println(i3==200);
        System.out.println(i3==Integer.valueOf(200));
	}
}
