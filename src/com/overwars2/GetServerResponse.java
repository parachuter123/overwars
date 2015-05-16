package com.overwars2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class GetServerResponse {

	public GetServerResponse() {
	}
	public static InputStream getIPS(String urlfrom) throws IOException{
		
		URL url = new URL(urlfrom);
		URLConnection conn = url.openConnection();
		conn.setDoInput(true);
		// ��Post����������
		conn.setDoOutput(false);
//		 System.out.println("Type is: " + conn.getContentType());
//		 System.out.println("content length: " + conn.getContentLength());
//		 System.out.println("allowed user interaction: " + conn.getAllowUserInteraction());
//		 System.out.println("content encoding: " + conn.getContentEncoding());
//		 System.out.println("content type: " + conn.getContentType());

		// �����Ӧ
		InputStream is = null;
		if (conn.getContentLength() > 0) {
			try {
				is = conn.getInputStream();
				if(is==null){
					System.out.println("�ڶ��������������Ϊnull");
				}
			} catch (IOException ioe) {
				System.out.println("********* IO EXCEPTION **********: "+ ioe);
			}
		}else{
			try {
				is = conn.getInputStream();
			} catch (Exception e) {
				System.out.println("********* IO EXCEPTION **********: "+ e);
			}
		}
		return is;
	}

}
