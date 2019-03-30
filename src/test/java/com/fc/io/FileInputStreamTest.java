package com.fc.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileInputStreamTest {

	public static void method2() {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream("src/test/resources/HelloWorld.java"));
			byte[] buf = new byte[1024];
			int bytesRead = in.read(buf);
			while (bytesRead != -1) {
				for (int i = 0; i < bytesRead; i++)
					System.out.print((char) buf[i]);
				bytesRead = in.read(buf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void method1() {
		RandomAccessFile aFile = null;
		try {
			aFile = new RandomAccessFile("src/test/resources/HelloWorld.java", "rw");
			FileChannel fileChannel = aFile.getChannel();
			//分配空间
			ByteBuffer buf = ByteBuffer.allocate(8);
			//写入数据到Buffer
			int bytesRead = fileChannel.read(buf);
			System.out.println(bytesRead);
			while (bytesRead != -1) {
				System.out.println("load....");
				buf.flip();
				while (buf.hasRemaining()) {
					//从Buffer中读取数据
					System.out.print((char) buf.get());
				}
				buf.compact();
				bytesRead = fileChannel.read(buf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (aFile != null) {
					aFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		method1();
	}
}
