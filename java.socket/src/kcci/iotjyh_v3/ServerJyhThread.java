package kcci.iotjyh_v3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerJyhThread extends Thread {
  static BlockingQueue<String> queueInput = new LinkedBlockingQueue<>();
  
  Socket socket = null;
  
  public void run() {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket();
      serverSocket.bind(new InetSocketAddress("localhost", 5000));
      label19: while (true) {
        System.out.println("[연결 기다림]");
        this.socket = serverSocket.accept();
        InetSocketAddress isa = (InetSocketAddress)this.socket.getRemoteSocketAddress();
        System.out.println("[연결 수락함]" + isa.getHostName());
        byte[] bytes = null;
        String message = null;
        InputStream is = this.socket.getInputStream();
        bytes = new byte[100];
        while (true) {
          int readByteCount = is.read(bytes);
          if (readByteCount <= 0)
            continue label19; 
          message = new String(bytes, 0, readByteCount, "UTF-8");
          System.out.println("[데이터 받기 성공]" + message);
          sendData(message);
        } 
        //break;
      } 
    } catch (Exception e) {
      e.printStackTrace();
      if (!serverSocket.isClosed())
        try {
          serverSocket.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }  
      return;
    } 
  }
  
  void sendData(String message) {
    try {
      queueInput.offer(message);
      OutputStream os = this.socket.getOutputStream();
      byte[] bytes = message.getBytes("UTF-8");
      os.write(bytes);
      os.flush();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
    System.out.println("[데이터 보내기 성공]");
  }
}
