package controller.home;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.Main;
import controller.login.Login;
import dao.MemberDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Home implements Initializable {

	public static Home home;
	public static String category;
	
	public Home() {
		home = this;
	}
	
	
	@FXML
	private Label lblloginid;
	@FXML
	private Label lblpoint;
	@FXML
	private Label lbllogout;
	@FXML
	private Label lbldelete;
	@FXML
	private Label lblchange;
	@FXML
	private BorderPane borderpane;
	@FXML
	private Label lblinfo;
	@FXML
	private Label lblupdate;
	@FXML
	private Label lblboard;
	@FXML
	private Label lblproduct1;
	@FXML
	private Label lblproduct2;
	@FXML
	private Label lblproduct3;
	@FXML
	private Label lblproduct4;
	
	//회원정보 레이블 클릭이벤트
	@FXML
	public void accinfo(MouseEvent e) {
		loadpage("/view/home/info.fxml");
	}
	
	//로그아웃 이벤트
	@FXML	
	public void logout (MouseEvent e) {
		//1.login 정보 지우기
		Login.member= null;
		//2.페이지전환
		Main.본인객체.loadpage("/view/login");
	}
	
	//회원삭제 이벤트
	@FXML 
	public void delete(MouseEvent e ) {
		//1.메시지
		Alert alert = new Alert(AlertType.CONFIRMATION);			//확인,취소 버튼 타입
			alert.setHeaderText("정말 탈퇴하시겠습니까?");
			//2.버튼 확인 [Optional :클래스]
			Optional<ButtonType> optional = alert.showAndWait();	//실행
			if(optional.get() == ButtonType.OK) {					//확인 버튼 눌렀을 
				boolean result=MemberDao.memberDao.delete(Login.member.getMnum());	//현재 로그인 중인 회원의 번호 호출 
					if(result) {//탈퇴성공
							//로그아웃[Login 클래스 내 Member 객체 null 로 수정]
							Login.member =null;
							//페이지 전환
							Main.본인객체.loadpage("/view/login");
					}
			}
			//아니면
	}	
	
	//회원수정 이벤트
	@FXML
	public void update (MouseEvent e) {
		loadpage("/view/home/update.fxml");
	}
	
	//HOME 오버라이딩
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadpage("/view/chatting.fxml");
		lblloginid.setText(Login.member.getMid()+"님");
		lblpoint.setText("포인트"+Login.member.getMpoint()+"점");
		
		
	}
	
	//화면전환 이벤트
	public void loadpage(String page) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page));
			borderpane.setCenter(parent);
		} catch (Exception e) {
			System.out.println( e );
		}
	}

	//자유게시 이벤트
	public void board(MouseEvent e) {
		loadpage("/view/board/board.fxml"); 
	}
	
	
		
	//상품등록1 이벤트	
	@FXML
	void accproduct1(MouseEvent event) {
		loadpage("/view/product/product.fxml");
		category="태권도";
	   }
	//상품등록2 이벤트
	@FXML
	void accproduct2(MouseEvent event) {
	    loadpage("/view/product/product.fxml"); 
    	category="유도";
	}
	//상품등록3 이벤트
	@FXML
	void accproduct3(MouseEvent event) {
		loadpage("/view/product/product.fxml"); 
		category="검도";
	}
	//상품등록4 이벤트
	@FXML
	void accproduct4(MouseEvent event) {
	    loadpage("/view/product/product.fxml");	
		category="쿵푸";
	}


}
