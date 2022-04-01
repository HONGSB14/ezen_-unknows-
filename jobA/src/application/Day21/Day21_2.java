package application.Day21;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Day21_2 extends Application {

	@Override
	public void start(Stage 스테이지) throws Exception { 
		// TODO Auto-generated method stub
		
		//1. fxml 파일 불러오기 : 컨테이너 
		Parent parent = FXMLLoader.load(getClass().getResource("test.fxml"));
		//		parent : fxml파일(씬빌더파일) 을 객체화 
		//			FXMLLoader.load(getClass().getResource("경로/fxml파일명"));
		//2. 씬 객체 생성 : 씬에 컨테이너 넣기
		Scene scene = new Scene(parent);
			
		//3. 스테이지에 씬 넣기
		스테이지.setScene(scene);
		
		//4. 스테이지 시작
		스테이지.show();
	}
	public static void main(String[] args) {
		launch(args); 
		
	}
}
