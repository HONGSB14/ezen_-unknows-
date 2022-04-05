package controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import dto.Member;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Login  implements Initializable{
		
		//로그인 성공한 회원객체 [static 사용이유: 다른 클래스에서 호출하기 위해
		public static Member member;		
		//해당 클래스의 메모리를 반환해주는 메소드
		public static Login 본인객체;
		//생성자 생성
		public Login() {
			본인객체=this; // super : 슈퍼클래스 (상속) this :현클래스
			//this: 현재클래스 자체 메모리 호출
		}
		public static Login get본익객체() {
			return 본인객체;
		}
		
		
    	@FXML
    	private MediaView mediaview;
			
	    @FXML
	    private BorderPane borderpane;
	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			System.out.println("실행");
		//1.동영상 삽입하기
			//1. 동영상 파일 객체화
//		Media media = new Media(getClass().getResource("/img/login.mp4").toString());
//		
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		
//		mediaview.setMediaPlayer(mediaPlayer);
//		
//		mediaPlayer.play();
//			
			loadpage("/view/loginpane.fxml");
		}
	
		public void loadpage(String page) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource(page));
				borderpane.setCenter(parent);
			}catch (Exception e) {
				System.out.println( e );
				
			}
		}
//
	
	

}
