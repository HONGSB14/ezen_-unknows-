package application.Day21;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Day21_1 extends Application {
     	
	@Override
	public void start(Stage stage) throws Exception {
		//javafx 를 실행하는 메소드 [ 추상 => 구현]
			//start(stage 스테이지이름)
		
		//1.컨테이너 만들기 [여려개 컨트롤 세로로 저장]
		VBox box = new VBox();
		
		//2. 컨트롤 만들기
		Button button = new Button();
		button.setText("닫기");
		button.setOnAction(e ->Platform.exit());
					// 값 ->람다식 ( 이름이 없는 메소드)
		
		//3.컨테이너에 컨트롤 넣기
		box.getChildren().add(button);
		
		//4.씬 만들기
		Scene scene = new Scene(box , 500 , 500);
		
		//5.스테이지에 씬 넣기
		stage.setScene(scene);
		stage.show();//stage open
		// 순서 : 스테이지-> 씬 -> 컨테이너 -> 여려개의 컨트롤( 버튼 , 입력상자 , 표 등)
	}
	public static void main(String[] args) { //메인 스레드 가지고 있는 메소드
		launch(args); //start 메소드 호출
	}
}
