package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//5. 컨테이너 불러오기
		Parent parent =FXMLLoader.load(getClass().getResource("/view/main.fxml"));
		
		//6. 씬 객체 생성
		Scene scene = new Scene(parent);
		
		//7.씬-> 스테이지
		stage.setScene(scene);
		//3. 스테이지로고 설정
		
		//1.이미지 불러오기
		Image iconImage = new Image("/img/stagelogo.jpg");
			//절대 경로 :모든 경로
			//상대 경로 :현 위치[프로젝트 기준 src] 경로
			//		생략[src폴더부터]  /img/파일명.확장자
		
		stage.getIcons().add(iconImage); 	//3.스테이지 아이콘 설정
		stage.setResizable(false); 			//4. 스테이지 크기 변경 불가
		stage.setTitle("싸우면 이기는데 아오!!"); //2.스테이지 창 이름
		stage.show(); 						//1.스테이지 열기
	}
	public static void main(String[] args) {
		launch(args);
	}
}
