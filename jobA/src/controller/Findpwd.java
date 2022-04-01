package controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.login.Login;
import dao.MemberDao;
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
		// TODO Auto-generated method stub
		
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
    	Login.���ΰ�ü.loadpage("/view/loginpane.fxml");
    }

    @FXML
    void findpwd(ActionEvent event) {
    		String id =txtid.getText();
    		String email =txtiemail.getText();
    		
    	
    	String findpwd=MemberDao.memberDao.findpwd(id, email);
    	if(findpwd==null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�˸�!");							
			alert.setHeaderText("������ ��й�ȣ�������ϴ�.");
			alert.setContentText("�층");
			alert.showAndWait();
			
			
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("�˸�!");							
			alert.setHeaderText("����� ��й�ȣ��?");
			alert.setContentText(findpwd);
			alert.showAndWait();
			Login.���ΰ�ü.loadpage("/view/loginpane.fxml");
			
    	}
    }

}
