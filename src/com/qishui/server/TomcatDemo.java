package com.qishui.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//模拟Tomcat服务器   不能访问，未知原因，待解开。
public class TomcatDemo extends Thread {

	Socket socket;

	public TomcatDemo(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			// 获取socket的输出流对象
			OutputStream outputStream = socket.getOutputStream();
			// 把数据写到浏览器上
			outputStream.write(
					"<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Test</title></head><body>Hello World !</body></html>"
							.getBytes());
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// 建立tcp的服务端
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(7078);
		// 不断的接受客户端的连接
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "访问了...");
			new TomcatDemo(socket).start();
			
		}
		
		
	}

}
