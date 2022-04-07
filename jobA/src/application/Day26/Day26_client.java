package application.Day26;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Day26_client {
	
	public static void main(String[] args) {
		
		
		//1. 소켓 만들기
		Socket  socket = new Socket();
		
		
		while(true) {
			try {	//2. 서버 소켓으로 연결하기 [서버 소켓의 ip 와  port 번호 넣어주기]
				socket.connect(new InetSocketAddress("127.168.102.50", 5000));
				System.out.println("서버와 연결 성공!!");
				
				Scanner scanner = new Scanner(System.in);
				//3.서버에 데이터 송신하기 [보내기] : 스트림을 이용한 외부 네트워크 통신
					
					//3-1 데이터 입력받기
					System.out.println("서버에게 보낼 메시지 "); 
					String msg=scanner.nextLine();
					//3-2 소켓의 출력 스트림 가져오기
					OutputStream outputStream =socket.getOutputStream();
					
					//3-3 내보내기
					outputStream.write(msg.getBytes());
					
				//4.서버에게 데이터 수신[받기] 하기
					//4-1 소켓의 입력 스트림
					InputStream inputStream = socket.getInputStream();
					//4-2 바이트 배열 확인
					byte[] bytes = new byte[1000];
					//4-3 입력 스트림으로부터 바이트 가져오기
					inputStream.read();
					System.out.println("서버가 보낸 메시지 :"+new String(bytes));
			} catch (Exception e) {
				
			}
			
		}
	}
	
}
