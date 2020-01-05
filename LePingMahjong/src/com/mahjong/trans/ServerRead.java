package com.mahjong.trans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

public  class ServerRead implements Runnable{
    Socket serverSocket;
    String readBuffer;
    @Override
    public synchronized void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            Thread.sleep(1000);
            System.out.println(in.readLine());
            this.notify();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ServerRead(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public String getReadBuffer() {

        return readBuffer;
    }

    public void setReadBuffer(String readBuffer) {
        this.readBuffer = readBuffer;
    }
}
