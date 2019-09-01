package com.xl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {

	private ServerSocket server;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Server2 server = new Server2();
		server.start();


	}
	/**
	 * 启动方法
	 */
	public void start(){
		try {
			server = new ServerSocket(8888);
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 接收客户端
	 */
	private void receive(){
		try {
			Socket client =server.accept();
			byte[] data=new byte[20480];
			int len =client.getInputStream().read(data);
			//接收客户端的请求信息
			String requestInfo=new String(data,0,len).trim();
			System.out.println(requestInfo);

		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

	/**
	 * 听着服务器
	 */
	public void stop(){

	}


}
/**
 POST /log HTTP/1.1
 Host: localhost:8888
 Connection: keep-alive
 Content-Length: 17
 Cache-Control: max-age=0
 Upgrade-Insecure-Requests: 1
 Origin: null
 Content-Type: application/x-www-form-urlencoded
 User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36
 Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*  ;q=0.8
 Accept-Encoding: gzip, deflate, br
 Accept-Language: zh-CN,zh;q=0.9
 uname=123&pwd=123
 **/
