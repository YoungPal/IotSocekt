package kcci.iotjyh_v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class ClientJyhThread extends Thread{
	Socket socket = null;	
	
	
	void sendData(String message) throws SocketException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OutputStream os = null;
		
		try {
			os = socket.getOutputStream();
			byte[] bytes = message.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			
			//os.close();
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("[������ ������ ����]");		
	}
	
	void stopClient() {
		if (!socket.isClosed()) {
			try {
					socket.close();				
			} catch (IOException e1) {
				//e1.printStackTrace();
			}

		}
		System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�");
	}

	@Override
	public void run() {
		try {
				socket = new Socket();
				System.out.println("[���� ��û]");
				socket.connect(new InetSocketAddress("localhost", 5000));
				System.out.println("[���� ����]");
				while(true){
					String message = null;
					byte[] bytes=null;		
					InputStream is = socket.getInputStream();
					bytes = new byte[100];
					int readByteCount = is.read(bytes);
					message = new String(bytes, 0, readByteCount, "UTF-8");
					System.out.println("[������ �ޱ� ����]: " + message);
					
				}			
			
			
		} catch (Exception e) {
			//System.out.println("������ �����Ǿ����ϴ�");
			//e.printStackTrace();
			
		}

		if (!socket.isClosed()) {
			try {
					socket.close();				
			} catch (IOException e1) {
				//e1.printStackTrace();
			}

		}
		//System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�");
	}
	

}


