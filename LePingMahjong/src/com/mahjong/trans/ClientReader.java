package com.mahjong.trans;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ClientReader implements Runnable{
    Socket socket;
    @Override
    public synchronized void run() {
        try {
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                Thread.sleep(1000);
                    System.out.println("received data\n");
                    this.notify();
                    System.out.println("after notify");
                    System.out.println((in.readLine()));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ClientReader(Socket socket) {
        this.socket = socket;
    }
}
