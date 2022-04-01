package controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.login.Login;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Findid implements Initializable {

	
	    @FXML
	    private Button btnfindid;

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
	    void findid(ActionEvent event) {
	    		String email = txtiemail.getText();
	    		
	    		String findid=MemberDao.memberDao.findid(email);
	    		if(findid==null) {
	    			Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("�˸�!");							//�޽��� ������
	    			alert.setHeaderText("������ ���̵� �����ϴ�.");
	    			alert.setContentText("�층");
	    			alert.showAndWait();		
	    		}else {
	    			Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("�˸�!");							//�޽��� ������
	    			alert.setHeaderText("����� ���̵��?");
	    			alert.setContentText(findid);
	    			alert.showAndWait();
	    			Login.���ΰ�ü.loadpage("/view/loginpane.fxml");
	    		}
	    }

	
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	
	
	    }

}
