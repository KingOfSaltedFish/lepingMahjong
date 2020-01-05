package com.mahjong.trans;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerWriter implements Runnable{
    Socket serverSocket;
    StringBuffer stringBuffer;
    @Override
    public synchronized void run() {
        try {
            Thread.sleep(1000);
            while(true){
                System.out.println("before send to client");
                this.wait();
                OutputStream out=serverSocket.getOutputStream();
                PrintWriter ps=new PrintWriter(out);
                ps.println(stringBuffer.toString());
                System.out.println("send"+stringBuffer.toString()+" to client");
                System.out.println("after send to client");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }
    public Socket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ServerWriter() {
        serverSocket=null;
    }
    public ServerWriter(Socket socket){
        this.serverSocket=socket;
    }

}
