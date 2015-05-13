package com.overwars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT=8887;
    private BufferedReader br=null;
    private PrintWriter pw=null;
    private BufferedReader FromCenlit=null;
    
    public Server(){
    	ServerSocket rServer = null;// ServerSocket��ʵ��  
        Socket request = null; // �û�������׽��� 
        Thread receiveThread = null;  
        try {
        	rServer = new ServerSocket(PORT);
        	System.out.println("Welcome to the server!"); 
        	while(true){
        		request = rServer.accept();
        		receiveThread = new ServerThread(request); 
        		receiveThread.start();  
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public static void main(String[] args) {
            new Server();
    }
}