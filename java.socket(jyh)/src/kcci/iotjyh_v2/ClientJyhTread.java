package kcci.iotjyh_v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientJyhTread extends Thread{
	Socket socket = null;	
	private String instruction = null;
	
	
	void sendData(String message){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OutputStream os = null;
		try {
			os = socket.getOutputStream();
			message = br.readLine();
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
		System.out.println("[������ ������ ����]");
		
	}

	@Override
	public void run() {
		try {
			socket = new Socket();
			System.out.println("[���� ��û]");
			socket.connect(new InetSocketAddress("localhost", 5000));
			System.out.println("[���� ����]");
			
			String message = null;
			byte[] bytes=null;		
			sendData(message);
			
			while(true) {
				InputStream is = socket.getInputStream();
				bytes = new byte[100];
				int readByteCount = is.read(bytes);
				message = new String(bytes, 0, readByteCount, "UTF-8");
				System.out.println("[������ �ޱ� ����]: " + message);
				is.close();
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("������ �����Ǿ����ϴ�");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				instruction = br.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (!socket.isClosed()) {
			try {
				
				if(instruction == "quit") {
					socket.close();
				}
				
			} catch (IOException e1) {
			}

		}
		System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�");
	}
	

}
