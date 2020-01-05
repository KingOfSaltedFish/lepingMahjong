package com.mahjong.trans;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriter implements Runnable{
    Socket socket;

    public ClientWriter(Socket socket) {
        this.socket = socket;
    }

    @Override

    public synchronized void run() {
        try {
            PrintWriter out=new PrintWriter(socket.getOutputStream());
            while(true){
                this.wait();
                String line="in.nextLine()";
                System.out.println("send date "+ line);
                out.println(line);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
