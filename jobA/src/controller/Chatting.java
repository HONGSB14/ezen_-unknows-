package controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.login.Login;
import controller.server.Server;
import dao.RoomDao;
import dto.Room;
import dto.RoomLive;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Chatting implements Initializable{

	public Server server;	//서버 소캣 셍성
		
    public static Room selectroom; //테이블 뷰에서 선택된 방 객체

    Socket socket;			//1.클라이언트 소켓 선언
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//채팅 fxml 열렸을때 초기값 메소드
			//채팅방 입장 시 fxid사용금지 시키기
		txtmsg.setText("채팅방 입장 후 사용가능");
		txtmsg.setDisable(true);		//채팅 입력창 사용금지 
		txtcontent.setDisable(true);	//채팅창 목록 사용금지
		btnsend.setDisable(true);		//전송버튼 사용금지
		btnconnect.setDisable(true);  	//입장 버튼 사용금지
		txtmidlist.setDisable(true);	//방접속회원목록 사용금지
		show();
		
	}
	
    @FXML
    private Button btnconnect;

    @FXML
    private TextArea txtcontent;

    @FXML
    private TextField txtmsg;

    @FXML
    private Button btnsend;

    @FXML
    private TableView<Room> roomtable;

    @FXML
    private TextField txtroomname;

    @FXML
    private Button btnadd;

    @FXML
    private TextArea txtmidlist;

    @FXML
    private Label lblselect;

    @FXML
    private Label lblmlist;
	    
    
	    

	    
	    
	    //2.클라이언트 실행 메소드
	    public void clientstart (String ip , int port) {
	    	//멀티스레드
	    	Thread thread = new Thread() {	
	    		@Override
	    		public void run () {
	    			try {
	    				socket  = new Socket(ip , port);//서버의 ip와 port 번호 넣어주기 [서버연결]
	    				send(Login.member.getMid()+"님이 입장하였습니다.\n");
	    				receive();								//접속과 동시에 받기 메소드는 무한루프
					} catch (Exception e) {
						
						System.out.println("클라이언트 실행 오류!! controller.Chatting : "+e);
					}	
	    		};
	    	};
	    	thread.start();	//멀티스레드 실행
	    }
	    
	    //3.클라이언트 종료 메소드
	    public void clientstop () {
	    	try {
				socket.close();
			} catch (Exception e) {
				
				System.out.println("클라이언트 종료 오류 !! controller.Chatting : "+e);
			}
	    }
	   
	    //4.서버에게 보내기 메소드
	    public void send(String msg) {
	    	Thread thread = new Thread() {
	    		@Override
	    public void run () {
	    			try {
						OutputStream outputStream = socket.getOutputStream(); 	//1.출력 스트림
						outputStream.write(msg.getBytes());						//2.내보내기
						outputStream.flush(); 									//3.스트림 초기화 [ 스트림 내 바이트 지우기]
					} catch (Exception e) {
						clientstop();
						System.out.println("메시지보내기 오류 !! controller.Chatting : "+e);
					}
	    		}
	    	};
	    	midshow();
	    thread.start();
	    }
	    
	    //5.서버에게 메시지 받기 메소드
	    public void receive() {
	    	try {
	    		while(true) {
	    			InputStream inputStream = socket.getInputStream();  //입력 스트림
	    			byte [] bytes= new byte[1000];						//바이트배열 선언
	    			inputStream.read(bytes);							//읽어오기
	    			String msg = new String(bytes);						//바이트열->문자열
	    			txtcontent.appendText(msg); 						//받은 문자열을 메시지창에 띄우기
	    		}
			} catch (Exception e) {
				clientstop();
				System.out.println("메시지 받기 오류 !! controller.Chatting : "+e);
			}
	    }
	   
	    public void midshow() {	//msg 메소드: 입력창에서 엔터 쳤을 때 //send메소드 : 전송 버튼 눌렀을 때
	    	//1.테이블 뷰에서 선택된 방 번호의 접속된 회원명단 가져오기
	    	ArrayList<RoomLive> roomlivelist 
				= RoomDao.roomDao.getroomlivelist( selectroom.getRonum() );
			txtmidlist.setText("");
			int i = 1; 
			for( RoomLive temp : roomlivelist ) {
				txtmidlist.appendText( i +"번 "+ temp.getMid() +"\n");
				i++;
			}
	    }
	    
	    @FXML
	    void connect(ActionEvent event) {
    		
	    	if(btnconnect.getText().equals("채팅방 입장")) {// 만약에 버튼의 텍스트가 "채팅방 입장" 이면 
	    		// 테이블뷰에서 선택된 방의 ip 와 port 를 클라이언트시작 메소드에 넣어주기
    			clientstart(selectroom.getRoip(), selectroom.getRonum());
    				//현재 방 접속 명단 추가
    				RoomLive roomLive =new RoomLive(0, selectroom.getRonum(), Login.member.getMid());
    				//DB처리
    				RoomDao.roomDao.addroomlive(roomLive);	
    			
    			
    			
    			txtcontent.appendText("---[채팅방 입장]---\n");
    			btnconnect.setText("채팅방 나가기");
    			
    			txtmsg.setText(" ");			//채팅입력창 빈칸으로 설정
    			txtmsg.setDisable(false);		//채팅 입력창 사용
    			txtcontent.setDisable(false);	//채팅창 목록 사용
    			btnsend.setDisable(false);		//전송버튼 사용
    			txtmsg.requestFocus();			//채팅입력창으로 포커스 [커서 ] 이동
    			
    			txtroomname.setDisable(true);   //채팅방 이름 입력창 사용금지
    			btnadd.setDisable(true);        //채팅 개설 사용 금지
    	        roomtable.setDisable(true);     //채팅방 목록 사용금지
    		}else {
    			clientstop();
    			txtcontent.appendText("---[채팅방 퇴장]---\n");
    			btnconnect.setText("채팅방 입장");
    			
    			txtmsg.setText("채팅방 입장 후 사용가능");
    			txtmsg.setDisable(true);		//채팅 입력창 사용금지 
    			txtcontent.setDisable(true);	//채팅창 목록 사용금지
    			btnsend.setDisable(true);		//전송버튼 사용금지
    			btnconnect.setDisable(true);	//채팅내용 사용금지
    			
    			txtroomname.setDisable(false);  //채팅방이름 사용
    			btnadd.setDisable(false);       //방개설 버튼 사용
    			roomtable.setDisable(false);    //채팅방 목록 사용
    			//1.방 접속 명단에서 제외[삭제] 하기
    			RoomDao.roomDao.roomlivedelete(Login.member.getMid());
    			
    			
    			//2.만약 방 접속 명단이 0명이면 방 삭제
    				//2-2 만약에 방이 삭제되면서 소켓 종료
    			boolean result =RoomDao.roomDao.roomdelte(selectroom.getRonum());
    			if(result) {
    				server.serverStop();
    			}
    			
    			//* 테이블뷰에서 선태고딘 방객체 초기화
    			selectroom= null;
    			//* 선택된 방 레이블 초기화
    			lblselect.setText("현재 선택된 방: ");
    			
    			//방 나갈때 서버 소캣 내 접속된 소켓 레스트에서 소켓 제거
    			
    		}
    	}
    
	    @FXML
	    void send(ActionEvent event) {	//전송 버튼을 눌렀을때
	    	
	    	String msg = txtmsg.getText()+"\n";	//보낼 메시지
	    	send(msg);							//메시지 보내기
	    	txtmsg.setText("");					//보내기 후 메시지 입력 창 지우기
	    	txtmsg.requestFocus();				//보내기 후 메시지 입력 창 포커스(커서)이동 
	    	
	    	midshow();
	    	
	    }
	    
	    //입력창에서 입력 후 엔터를 눌렀을 때
	    @FXML
	    void msg(ActionEvent event) {
	    	String msg=Login.member.getMid()+":"+txtmsg.getText()+"\n";
	    	send(msg);
	    	txtmsg.setText("");
	    	txtmsg.requestFocus();
	    	
	    	ArrayList<RoomLive> roomlivelist = RoomDao.roomDao.getroomlivelist(selectroom.getRonum());
	    	txtmidlist.setText("");
	    	int i=0;
	    	for(RoomLive temp : roomlivelist) {
	    		txtmidlist.appendText(i+"번"+temp.getMid()+"\n");
	    		i++;
	    	}
 	    }

	    //방 개설 버튼 메소드
	    @FXML
	    void add(ActionEvent event) {
	    	
	    	//1.컨트롤에 입력된 방 이름 가져오기
	    	String roomname = txtroomname.getText();
	    	if(roomname.length()<4) {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setHeaderText("방 제목을 4글자 이상으로 해주세요.");
	    	return;
	    	}
	    	//2.방 객체
	    	Room room= new Room(0, roomname, "127.0.0.1", 0);
	    	//3.DB처리
	    	RoomDao.roomDao.roomadd(room);
	    	//4.해당 방 서버 실행
	    	server= new Server();
	    	//서버 실행 
	    	server.serverStart(room.getRoip(), RoomDao.roomDao.getroomnum());
	    	txtroomname.setText("");					//개설 후 방 이름 입력창 공백 처리
	    	
	    	show(); //테이블 새로고침
	    	
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setHeaderText("방 개설 완료!\n"+"방번호: "+RoomDao.roomDao.getroomnum());
	    	alert.showAndWait();
	    }
	    
	    //모든 방 목록 가져오기 메소드
	    public void show() {
	    	
	    	//1.DB에서 모든 방 목록 가져오기
	    	ObservableList<Room> roomlist= RoomDao.roomDao.roomlist();
	    	
	    	//2.테이블 뷰의 첫번쨰 필드를 가져와서 방 번호 필드로 설정
	    	TableColumn tc = roomtable.getColumns().get(0);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("ronum"));
	    	
	    	//3. 테이블 뷰의 세번 째 필드를 가져와서 방 번호 필드로 설정
	    	tc= roomtable.getColumns().get(1);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("roname"));
	    	
	    	//4.테이블 뷰 의 네번 째 필드를 가져와서 방 번호 필드로 설정
	    	tc= roomtable.getColumns().get(2);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("mcount"));
	    	
	    	//5.테이블 뷰의 리시트를 넣어주기
	    	roomtable.setItems(roomlist);
	    	
	    	//6. 해당 테이블 뷰를 클릭 했을때 
	    	roomtable.setOnMouseClicked( e-> {
	    			//7.클릭된 객체 가져오기
	    			selectroom =roomtable.getSelectionModel().getSelectedItem();
	    			//8.레이블 표시
	    			lblselect.setText("현재 선택된 방: "+selectroom.getRoname());
	    			//9.접속 버튼 사용 활성화
	    			btnconnect.setDisable(false);
	    	});
	    }
}
