package kcci.iotjyh_v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ClientJyhExample {

	public static void main(String[] args) throws IOException {
		ClientJyhThread clientJyhThread = new ClientJyhThread();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		clientJyhThread.start();
		
		try {
			while(true) {
				
				String str = br.readLine();
				if(str.equals("quit")) {
					clientJyhThread.stopClient();
					break;					
				}
				else {
					clientJyhThread.sendData(str);
				}
				
			}
			br.close();
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("서버가 중지되었습니다");
			//e.printStackTrace();
		}
		
		

	}

}
