package com.overwars;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket sk = null;
	private static final int PORT = 8887;

	public Server() throws IOException {
		sk = new ServerSocket(PORT);
		System.out.println("服务器已经启动····");
	}

	public void service() {
		while (true) {
			Socket st = null;
			try {
				st = sk.accept();
				Thread thread = new Thread(new Handler(st));
				thread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * st=sk.accept(); //从这里获取从客户端发来的数据！ br=new BufferedReader(new
		 * InputStreamReader(st.getInputStream())); pw=new
		 * PrintWriter(st.getOutputStream(),true); Map<String,Object> map = new
		 * HashMap<String,Object>();
		 */
		/*
		 * while(true){ String fromc = null; if((fromc=br.readLine())!=null){
		 * System.out.println(fromc);
		 * 
		 * String[] str = fromc.split(" "); if(str[0].equals("GET")){
		 * map.put(str[0], str[1]); break; } } }
		 */

		/*
		 * HttpCilentExample httpClient = new HttpCilentExample();
		 * if(map.get("GET")!=null){ StringBuffer html =
		 * httpClient.sentGet(map.get("GET").toString());
		 * System.out.println(html.toString()); pw.println(html.toString()); }
		 */

	}

	public static void main(String[] args) throws IOException {
		new Server().service();
	}

}