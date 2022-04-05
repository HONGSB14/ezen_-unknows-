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
		
		//�α��� ������ ȸ����ü [static �������: �ٸ� Ŭ�������� ȣ���ϱ� ����
		public static Member member;		
		//�ش� Ŭ������ �޸𸮸� ��ȯ���ִ� �޼ҵ�
		public static Login ���ΰ�ü;
		//������ ����
		public Login() {
			���ΰ�ü=this; // super : ����Ŭ���� (���) this :��Ŭ����
			//this: ����Ŭ���� ��ü �޸� ȣ��
		}
		public static Login get���Ͱ�ü() {
			return ���ΰ�ü;
		}
		
		
    	@FXML
    	private MediaView mediaview;
			
	    @FXML
	    private BorderPane borderpane;
	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			System.out.println("����");
		//1.������ �����ϱ�
			//1. ������ ���� ��üȭ
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
