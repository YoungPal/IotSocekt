package kcci.iotjyh_v3;

import java.io.*;


public class ServerJyhExample {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		ServerJyhThread serverJyhThread = new ServerJyhThread();
		serverJyhThread.start();
		File file = new File("recvdata.txt");
		if(file.exists()==false) {file.createNewFile();}
		String queueData = null;
		
		while (true) {
			try {
				queueData = ServerJyhThread.queueInput.take();
				String[] afterstr = queueData.split("@");

				String str = "";
				int nBuffer;
				BufferedReader reader = new BufferedReader(new FileReader("recvdata.txt"));
				while((nBuffer = reader.read()) != -1) {
					str += (char)nBuffer;
				}
				BufferedWriter writer = new BufferedWriter(new FileWriter("recvdata.txt"));
				if(afterstr.length == 4) {
					writer.write(str+"ID : "+afterstr[0]+" 온도 : "+afterstr[1] + " 조도 : "+afterstr[2]+" 습도 : "+afterstr[3]+"\r\n");
				    
				    writer.close();
				}
				else {
					//writer.write(str+"error data"+"\r\n");
				    //writer.close();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    
			//System.out.println("TEST : " + queueData);
		}

	}

}
