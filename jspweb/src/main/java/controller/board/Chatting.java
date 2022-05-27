package controller.board;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
//@serverendpint ("서버소켓 클래스로 들어오는 경로 정의")
@ServerEndpoint("/chatting")
public class Chatting {
	
	//0.접속자 명단
	public static List<Session> clients = new Vector<Session>(); //동기화 [한명씩 처리]
	
	//1. 소켓 오픈
	@OnOpen			// *session 은 websoket 으로 받아야함
	public void onOpen( Session session) {
		clients.add(session);
	}
	//2. 소켓 닫기
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
	//3. 소켓 메시지 보내기
	@OnMessage
	public void onMessage(String msg, Session session) throws IOException{
		
		System.out.println(session +"이 보냈습니다.");
		
		for(Session s : clients) {	//리스트에존재하는 세션들에게 메시지 보내기
			s.getBasicRemote().sendText(msg);
		}
	}
}
