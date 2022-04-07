package controller.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dto.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Server implements Initializable {
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		txtserver.setDisable(true);  //해당 fxid사용금지!
	}
	
	
    @FXML
    private Button btnserver;

    @FXML
    private TextArea txtserver;

    public static Vector<Client> clientlist = new Vector<>();	//Vector 를 쓰는 이유 :동기화 지원 (
    	
    
    
    //서버 실행 메소드
    @FXML
    void server(ActionEvent event) {
    	if(btnserver.getText().equals("서버실행")) {
    		serverStart();
    		txtserver.appendText("서버실행합니다.\n");
    		btnserver.setText("서버중지");
    	}else {	//버튼의 텍스트가 " 서버중지 "이면 
    		txtserver.appendText("서버중지합니다.\n");
    		serverStop();
    		btnserver.setText("서버실행");
    	}
    }
	
    
    //* 서버에 연결된 클라이언트를 저장하는 리스트
    //* 멀티스레드를 관리해주는 스레드 
    public static ExecutorService threadPool;
    	//ExecutorService :스레드 풀 구현 인터페이스 [ 구현클래스(implement) vs 직접구현(익명객체) ] 
    //1.서버 소켓 선언
    ServerSocket serverSocket;
   
    
    //2.서버실행 메소드
    public void serverStart() {
    	
    		try {
    			
    			//1.서버소켓 메모리 할당
    			serverSocket =new ServerSocket();
    			
    			//2.서버소켓 바인딩[ IP 와 PORT 설정]
    			serverSocket.bind(new InetSocketAddress ("127.0.0.1" , 1234));

			} catch (Exception e) {
			
				//3.클라이언트의 요청 대기 [멀티 스레드]
				Runnable runnable = new Runnable() {	
					@Override
					public void run() {
						while(true) {
							try {
								Socket socket = serverSocket.accept(); //요청 수락후에 수락된 소켓을 저장
								clientlist.add(new Client(socket));	   //연결된 클라이언트(연결된소켓) 생성 후에 리스트 저장
							} catch (Exception e2) {
								
							}
						}
					}
				};//멀티스레드 구현 끝
				threadPool= Executors.newCachedThreadPool();	//스레드 풀에 메모리 초기화[할당]
				threadPool.submit(runnable);					//위에서 구현된 멀티스레드를 스레드 풀에 넣어주기
			}
    
    		
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
		
		}
    }
}
