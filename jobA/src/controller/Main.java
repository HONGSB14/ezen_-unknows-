package controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.login.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class Main implements Initializable {
							//Initializable  :해당 fxml이 열렸을떄 초기값 메소드 제공 ( view가 열렸을때 초기값 설정 메소드 제공)

    public static Main 본인객체;
    
    public Main() {
    	본인객체=this;
    }
	
	@FXML
    private BorderPane borderpane; //fx :id 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	// 해당 fxml이 열렸을떄 초기값 메소드 제공
		loadpage("/view/login");	//loadpage 메소드 호출 시 [파일 경로]
		
	}
	
	public void loadpage(String page) {// loadpage (파일경로)
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page+".fxml"));
			borderpane.setCenter(parent);	//main.fxml 에 존재하는 boderpane 객체 내 center을 해당 파일로 변경
		} catch (Exception e) {	//만약에 파일이 존재하지 않으면 예외 발생
			System.out.println("페이지 연결 실패 이유 " +e); 
		}
		
	}
}
