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
	static BlockingQueue<String> queueInput = new LinkedBlockingQueue<String>();
	Socket socket = null;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", 5000));
			while (true) {
				System.out.println("[연결 기다림]");
				socket = serverSocket.accept();
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("[연결 수락함] " + isa.getHostName());

				byte[] bytes = null;
				String message = null;
				InputStream is = socket.getInputStream();
				bytes = new byte[100];
				int readByteCount = is.read(bytes);
				if(readByteCount <= 0)
					break;
				message = new String(bytes, 0, readByteCount, "UTF-8");
				System.out.println("[데이터 받기 성공]: " + message);
				sendData(message);
				is.close();
				socket.close();
			}
		} catch (Exception e) {
		}

		if (!serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} catch (IOException e1) {
			}
		}
	}

	void sendData(String message) {
		queueInput.offer(message);
		OutputStream os = null;
		try {
			os = socket.getOutputStream();
			
			byte[] bytes = message.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			os.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[데이터 보내기 성공]");
	}
}
