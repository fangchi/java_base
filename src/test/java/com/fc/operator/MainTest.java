package com.fc.operator;

import org.junit.Test;

public class MainTest {

	@Test
	public void testTransientTest(){
		 //抑或 相同取0，相异取1
		 System.out.println("15^2=13 "+(15^2));
		 System.out.println("2<<3运算的结果是 16 :"+(2<<3));
		 System.out.println("7>>2运算的结果是  1:"+(7>>2));
		 System.out.println("7>>2运算的结果是  1:"+(7>>>2));
		 //且 只要有一个是0就算成0
		 System.out.println("1&2 运算的结果是  1:"+ (1&2));
		 System.out.println("8&15 运算的结果是  8:"+ (8&15));
		//或 只要有一个是1就算成1
		 System.out.println("1|6 运算的结果是  7:"+ (1|6));
	}
}
