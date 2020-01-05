import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10068);//创建绑定到特定端口的服务器Socket。
            Socket socket = null;//需要接收的客户端Socket
            System.out.println("服务器启动");
            //定义一个死循环，不停的接收客户端连接
            Lock lock1=new ReentrantLock();
            Lock lock2=new ReentrantLock();
            socket = serverSocket.accept();//侦听并接受到此套接字的连接
            InetAddress inetAddress=socket.getInetAddress();//获取客户端的连接
            ServerThread thread=new ServerThread(socket,inetAddress,lock1,lock2);//自己创建的线程类
            thread.start();//启动线程
            int count=0;
            while(true){
                synchronized (lock1){
                    lock1.wait();
                    String s=thread.getInfoBuffer();
                    thread.setInfoBuffer(s+":"+count);
                        count++;
                        System.out.println("服务端发送"+thread.getInfoBuffer());
                        synchronized (lock2){
                    lock2.notify();
                }}
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
