package dto;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import controller.server.Server;

public class Client {
	//서버에 들어오는 클라이언트의 클래스
	
	//1.필드
	public Socket socket;
	
	//2.생성자
	public Client(Socket socket ) {
		this.socket= socket;
		recive();  //서버와 연결된 클라이언트 객체가 생성될때 받기 떄문에 
		
	}
	
	//3.서버로부터 받는 메시지
	public void recive() { //[실행조건: 서버와 클라이언트가 연결되었을때]
		
		//멀티스레드 하는 이유 메시지를 받기도 하고 주기도 해야되기 때문.
		//멀티스레드 [Thread 클래스 or Runnable 인터페이스]
		//Runnable 뿐만아니라  인터페이스자체는 추상메소드가 존재하기 때문에 구현이 필수 !! 
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {	//추상 메소드 구현
				//계속적으로 메시지 받는 상태
				try {
					while(true) {
						InputStream inputStream =socket.getInputStream();   //입력스트림
						byte [] bytes = new byte[1000];						//바이트 배열 선언
						inputStream.read(bytes);							//입력스트림을 바이트로 읽어오기
						String msg = new String(bytes);						//바이트열->문자열 변환
						
						//서버가 받은 메시지를 현재 서버에 접속된 모든 클라이언트에게 받은 메시지 보내기
						for(Client client : Server.clientlist) {
							client.send(msg);	//받은 메시지를 서버에 접속된 [clientlist ] 모든 클라이언트
						}
					}
				} catch (Exception e) {
					
				}
			}
		};//멀티스레드 구현 끗
		
		//해당 스레드를 멀티스레드를 스레드 풀에 넣어줌
		Server.threadPool.submit(runnable);
	}
	
	//4.서버로부터 보내는 메시지	[실행조건: 서버가 메시지를 받았을 때]
	public void send(String msg) {	//멀티스레드
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(msg.getBytes());
					} catch (Exception e) {
						
					}
					
				}
			};
			Server.threadPool.submit(runnable);
	}
}
