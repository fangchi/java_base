package com.fc.bytetest;

public class ByteTest {

	/**
	 * 只要是字符,不管是数字还是英文还是汉字,都占两个字节,都是一个char
	 * bit: 1 bit位 = 1 二进制数据
		byte: 1 byte = 8 bit位 （-128 ~ 127）
		字母: 1 字母 = 1 byte = 8 bit(位)
		short: 16位
		char: Unicode字符，16bit位
		int: 32bit位，比如int 类型占用4个字节，32位
		long: 64bit位
		float: 32bit位
		double: 64bit位
	 * @see 控制台打印结果,如下
	 * @see 4
	 * @see 4
	 * @see ----------------------------
	 * @see str=ABab
	 * @see 65
	 * @see 66
	 * @see 97
	 * @see 98
	 * @see ----------------------------
	 * @see name=玄玉
	 * @see -48
	 * @see -2
	 * @see -45
	 * @see -15
	 * @see ----------------------------
	 * @see 2
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//byte bb11 = 128;  //cannot convert from int to byte
		//byte bb22 = -129; //cannot convert from int to byte
		byte bb11 = 127;
		byte bb22 = -128;
		byte bb33 = 56;
		
		//byte bb44 = '玄'; //错误,因为: 1汉字==2字节
		//byte bb55 = 'bb'; //错误,因为: 'bb'==2字节
		byte bb44 = 'b';
		byte bb55 = 'B';
		byte bb66 = 'Z';
		
		//int i11 = "玄玉";  //1 int = 32 bit, but int is numeral
		//short ss = '玄玉'; //1 short = 2 byte = 16 bit = 1 汉字
		short ss11 = '玄';
		
		//char cc11 = '玄玉'; //这样是不可以的
		//char cc22 = 'cc';  //这样是不可以的
		char cc11 = '玄';
		char cc22 = 'c';
		
		String str = "ABab";
		byte[] data = str.getBytes();
		System.out.println(str.length());
		System.out.println(data.length);
		System.out.println("----------------------------");
		
		System.out.println("str=" + new String(data));
		for(int i=0; i<data.length; i++){
			System.out.println(data[i]);
		}
		System.out.println("----------------------------");
		
		byte[] name = "玄玉".getBytes();
		System.out.println("name=" + new String(name));
		for(int i=0; i<name.length; i++){
			System.out.println(name[i]);
		}
		System.out.println("----------------------------");
		
		System.out.println("av".getBytes().length);
		long s = 1212192192102L;
		System.out.println(s);
	}
}
