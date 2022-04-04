package controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.login.Login;
import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Findpwd implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblconfirm.setText("");
		
	}
	
	
    @FXML
    private TextField txtid;

    @FXML
    private Button btnfindpwd;

    @FXML
    private Button btnback;

    @FXML
    private Label lblconfirm;

    @FXML
    private TextField txtiemail;

    @FXML
    void back(ActionEvent event) {
    	Login.본인객체.loadpage("/view/loginpane.fxml");
    }

    @FXML
    void findpwd(ActionEvent event) {
    		String id =txtid.getText();
    		String email =txtiemail.getText();
    		
    	
    	String findpwd=MemberDao.memberDao.findpwd(id, email);
    	if(findpwd==null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("알림!");							
			alert.setHeaderText("동일한 비밀번호가없습니다.");
			alert.setContentText("띠링");
			alert.showAndWait();
			
			
    	}else {
    		Member.sendmail(email, findpwd);
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("비밀번호 전송완료");							
			alert.setHeaderText("이메일로 전송이 되었습니다.");
			alert.showAndWait();
			Login.본인객체.loadpage("/view/loginpane.fxml");
			
    	}
    }

}
