package com.overwars;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SocketDemo {
	Proxy proxy;
	URL url;
	URLConnection conn;
	Scanner scan;
	PrintStream ps;
	String proxyAddress = "127.0.0.1";
	int proxyPort;
	String urlString = "http://www.baidu.com";
	public void init(){
		try {
			url = new URL(urlString);
			proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(proxyAddress, 80));
			conn = url.openConnection(proxy);
			conn.setConnectTimeout(5000);
			scan = new Scanner(conn.getInputStream());
			ps = new PrintStream("index.html");
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				System.out.println(line);
				ps.println(line);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ps!=null){
				ps.close();
			}
		}
	}
	public static void main(String[] args) {
		try {
			InetAddress add = InetAddress.getLocalHost();
			System.out.println(add.getHostName() + "," + add.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
