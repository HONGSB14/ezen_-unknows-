package controller.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server  {	//fxml 사용하지 않는 클래스 [ 서버 컨트롤 사용]

    public Vector<Client> clientlist = new Vector<>(); //Vector 를 쓰는 이유 :동기화 지원하기 때문.  //동기화:여러 스레드가 하나의 메소드 접근할 경우 대기상태 만들기

    //* 서버에 연결된 클라이언트를 저장하는 리스트
    //* 멀티스레드를 관리해주는 스레드 
    public ExecutorService threadPool;
    	//ExecutorService :스레드 풀 구현 인터페이스 [ 구현클래스(implement) vs 직접구현(익명객체) ] 
    //1.서버 소켓 선언
    ServerSocket serverSocket;
    
    //2.서버실행 메소드	[인수로 IP와 PORT 로 받아서 받은 IP와 PORT 로 서버소켓 바인딩(생성)]
    public void serverStart(String ip, int port) {
    	
    		try {
    			
    			//1.서버소켓 메모리 할당
    			serverSocket =new ServerSocket();
    			
    			//2.서버소켓 바인딩[ IP 와 PORT 설정]
    			serverSocket.bind(new InetSocketAddress (ip , port));

			} catch (Exception e) {
				System.out.println("서버생성 실패!"+e);
			}
			//3.클라이언트의 요청 대기 [멀티 스레드]
			Runnable runnable = new Runnable() {	
				@Override
				public void run() {
					while(true) {
						try {
							Socket socket = serverSocket.accept(); //요청 수락후에 수락된 소켓을 저장
							clientlist.add(new Client(socket));	   //연결된 클라이언트(연결된소켓) 생성 후에 리스트 저장
				
						} catch (Exception e) {
							
							
							System.out.println("서버 클라이언트 연결 실패 ! controller.server.Sever : "+e);
							
						}	
					}
				}
			};//멀티스레드 구현 끝
			threadPool= Executors.newCachedThreadPool();	//스레드 풀에 메모리 초기화[할당]
			threadPool.submit(runnable);					//위에서 구현된 멀티스레드를 스레드 풀에 넣어주기
    }
    
    //3.서버종료 메소드
    public void serverStop() {
    	try {
    		//1.접속된 모든 클라이언트들의 소켓 닫기
        	for(Client client : clientlist) {
        		client.socket.close();
        		
        	}
        	//2.서버 소켓 닫기
        	serverSocket.close();
        	//3.스레드 풀 닫기
        	threadPool.shutdown();
		}catch (Exception e) {	
			
			System.out.println("서버 종료 실패!  controller.server.Sever: "+e);
		}
    }

    
    
    // 클래스안에 클래스 선언 중첩 클래스  [client]  
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
    						for(Client client : clientlist) {
    							client.send(msg);	//받은 메시지를 서버에 접속된 [clientlist ] 모든 클라이언트
    						}
    					}
    				} catch (Exception e) {
    				
    					System.out.println("서버로부터 받는 메시지 오류!! controller.server.Sever : "+e);
    				}
    			}
    		};//멀티스레드 구현 끗
    		threadPool.submit(runnable);	//해당 스레드를 멀티스레드를 스레드 풀에 넣어줌
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
    						
    						System.out.println("서버로부터 보내는 메시지 오류 !! controller.server.Sever : "+e);
    					}
    					
    				}
    			};
    			threadPool.submit(runnable);
    	}
    }
}
