package application.Day22;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Day22_1 extends Application{

	@Override
	public void start(Stage 스테이지) throws Exception {
			//스테이지 이름 정하기 [아무거나]
		//1.씬 빌더에서 작성한 파일 [ fxml ] 을 객체로 가져오기
		Parent parent = FXMLLoader.load(getClass().getResource("dbtest.fxml"));
		//2. 씬 객체를 만들어서 가져온 fxml 객체를 넣어준다
		Scene scene = new Scene(parent);		
		스테이지.setScene(scene);
		스테이지.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
