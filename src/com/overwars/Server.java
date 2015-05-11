package com.overwars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket sk=null;
    private Socket st=null;
    private static final int PORT=8887;
    private BufferedReader br=null;
    private PrintWriter pw=null;
    private BufferedReader FromCenlit=null;
    public Server(){
        init();
    }
    public void init(){
        try {
            sk=new ServerSocket(PORT);
            System.out.println("�������Ѿ�������������");
            st=sk.accept();   
                         //�������ȡ�ӿͻ��˷��������ݣ�
            br=new BufferedReader(new InputStreamReader(st.getInputStream()));
            pw=new PrintWriter(st.getOutputStream(),true);
            FromCenlit=new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String fromc=null;
                if((fromc=br.readLine())!=null){
                    System.out.println("�ͻ���"+fromc);
                }
                String FrCenlit=null;
                if((FrCenlit=FromCenlit.readLine())!=null){
                    pw.println(FrCenlit);
                }
                if(FrCenlit.equals("bey")){
                    break;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                FromCenlit.close();
                pw.close();
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             
             
        }
         
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
            new Server();
    }
 
}