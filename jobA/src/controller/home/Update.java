package controller.home;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import controller.login.Login;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Update implements Initializable {

	@FXML
	private TextField txtemail;
	
	@FXML
	private TextField txtadd;
	
	@FXML
	private Button btnupdate;
	
	
	@FXML
	public void update(ActionEvent e) {
		String email = txtemail.getText();
		String add =txtadd.getText();
		boolean result = MemberDao.memberDao.update(Login.member.getMnum(), email, add);
		if(result) {
			Alert alert = new Alert (AlertType.INFORMATION);
				alert.setHeaderText("회원정보 수정되었습니다. 다시 로그인을 해주세요. .");
				alert.showAndWait();
				
				Main.본인객체.loadpage("/view/login");	//페이지 전환
				Login.member= null;	//로그인 정보 null
		}else {
			
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtemail.setText(Login.member.getMemail());
		txtadd.setText(Login.member.getMadd());
	}
}
