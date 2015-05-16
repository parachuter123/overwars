package com.overwars2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class GetRequest {
	public GetRequest(){
		
	}
	public static String getUrl(BufferedReader in ) throws IOException{
		String url = "http://wx.smny.cn";
		String inputLine;
		int cnt = 0;
		while ((inputLine = in.readLine()) != null) {
			try {
				StringTokenizer tok = new StringTokenizer(inputLine);
				tok.nextToken();
			} catch (Exception e) {
				break;
			}
			// ��ǰ�����һ���ҵ�url
			if (cnt == 0) {
				String[] tokens = inputLine.split(" ");
				url = tokens[1];
				// can redirect this to output log
				System.out.println("��һ�� ��������Ϊ : " + url);
			}
			cnt++;
		}
		return url;
	}
}
