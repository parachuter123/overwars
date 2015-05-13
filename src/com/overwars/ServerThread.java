package com.overwars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread  extends Thread{
	Socket clientRequest;// 用户连接的通信套接字  
    BufferedReader input;// 输入流  
    PrintWriter output;// 输出流  
	public ServerThread(Socket st) {
		this.clientRequest = st;
		InputStreamReader reader;  
        OutputStreamWriter writer;  
        try{
        	// 初始化输入、输出流  
            reader = new InputStreamReader(clientRequest.getInputStream());  
            writer = new OutputStreamWriter(clientRequest.getOutputStream());  
            input = new BufferedReader(reader);  
            output = new PrintWriter(writer, true);  
        } catch (IOException e){  
            System.out.println(e.getMessage());  
        } 
        output.println("Welcome to the server!");
	}
	public void run(){
		boolean done = false;
		String str = null;
		while(!done){
			try {
				str = input.readLine();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			clientRequest.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
