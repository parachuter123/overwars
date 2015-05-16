package com.overwars2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ProxyThread extends Thread {
	private Socket socket = null;
	private static final int BUFFER_SIZE = 32768;

	public ProxyThread(Socket socket) {
		super("ProxyThread");
		this.socket = socket;
	}

	public void run() {
		// ��ÿͻ�������
		// �������������
		// ����˷�����Ӧ
		// ��Ӧ���ظ��ͻ���
		DataOutputStream out = null;
		BufferedReader in = null;
		try {
			out = new DataOutputStream(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String urlToCall = "http://wx.smny.cn";
			// ��һ������ÿͻ�������
			urlToCall = GetRequest.getUrl(in);

			try {
				// �ڶ����������������������, ����˷�����Ӧ
				InputStream is = GetServerResponse.getIPS(urlToCall);
				
				// ���Ĳ�����Ӧ���ظ��ͻ���
				byte by[] = new byte[BUFFER_SIZE];
				int index = is.read(by, 0, BUFFER_SIZE);
				while (index != -1) {
					out.write(by, 0, index);
					index = is.read(by, 0, BUFFER_SIZE);
				}
				out.flush();
			} catch (Exception e) {
				// can redirect this to error log
				//System.err.println("Encountered exception: " + e);
				// encountered error - just send nothing back, so
				// processing can continue
				out.write(-1);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}