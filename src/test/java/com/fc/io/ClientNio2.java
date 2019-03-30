package com.fc.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class ClientNio2 {

	public static void client(int port) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.connect(new InetSocketAddress("127.0.0.1", port));
			if (socketChannel.finishConnect()) {
				int i = 0;
				while (true) {
					TimeUnit.SECONDS.sleep(1);
					//write()方法无法保证能写多少字节到SocketChannel。所以，我们重复调用write()直到Buffer没有要写的字节为止
					System.out.println("client writing at "+port);
					String info = "I'm sendding" + i++ + "-th information from client port:"+port;
					buffer.clear();
					buffer.put(info.getBytes());
					buffer.flip();
					while (buffer.hasRemaining()) {
						socketChannel.write(buffer);
					}
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socketChannel != null) {
					socketChannel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Runnable runnable= new Runnable() {
			@Override
			public void run() {
				client(12001);
			}
		};
		Thread thread1 = new Thread(runnable);
		thread1.run();
	}
}
