package com.overwars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class Handler implements Runnable {
	private Socket socket;

	public Handler(Socket socket) {
		this.socket = socket;
	}

	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}

	private BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}

	public String echo(String msg) {
		return " echo: " + msg;
	}

	public void run() {
		try {
			BufferedReader br = getReader(socket);
			PrintWriter pw = getWriter(socket);
			Map<String, Object> map = new HashMap<String, Object>();
			while (true) {
				String fromc = null;
				if ((fromc = br.readLine()) != null) {
					String[] str = fromc.split(" ");
					if (str[0].equals("GET")) {
						map.put(str[0], str[1]);
						break;
					}
				}
			}
			HttpCilentExample httpClient = new HttpCilentExample();
			if (map.get("GET") != null) {
				StringBuffer html = httpClient.sentGet(map.get("GET").toString());
				pw.println(html.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}