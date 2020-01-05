import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class ServerThread extends Thread {
    Socket socket = null;
    InetAddress inetAddress=null;//接收客户端的连接
    final Lock condition1;
    final Lock condition2;
    public boolean ready;
    String infoBuffer;
    public ServerThread(Socket socket,InetAddress inetAddress,Lock lock1,Lock lock2) {
        this.socket = socket;
        this.inetAddress=inetAddress;
        condition1=lock1;
        condition2=lock2;
    }

    @Override
    public  void run() {
        InputStream inputStream = null;//字节输入流
        InputStreamReader inputStreamReader = null;//将一个字节流中的字节解码成字符
        BufferedReader bufferedReader = null;//为输入流添加缓冲
        OutputStream outputStream = null;//字节输出流
        OutputStreamWriter writer = null;//将写入的字符编码成字节后写入一个字节流
        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;//临时
            outputStream = socket.getOutputStream();
            writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            //循环读取客户端信息
            while ((info = bufferedReader.readLine()) != null) {
                //获取客户端的ip地址及发送数据
                synchronized (this){
                    synchronized (condition1){
                    condition1.notify();}
                    System.out.println("服务器端接收：" + "{'from_client':'" + socket.getInetAddress().getHostAddress() + "','data':'" + info + "'}");
                    infoBuffer = info;
                    synchronized (condition2){
                    condition2.wait();
                    writer.write("{'to_client':'" + inetAddress.getHostAddress() + "','data':" + info + "}\n");
                    writer.flush();//清空缓冲区数据
                    }}
            }

            socket.shutdownInput();//关闭输入流

            //响应客户端请求
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (writer != null) {
                    writer.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getInfoBuffer() {
        return infoBuffer;
    }

    public void setInfoBuffer(String infoBuffer) {
        this.infoBuffer = infoBuffer;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}