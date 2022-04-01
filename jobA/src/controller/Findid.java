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
	    	Login.본인객체.loadpage("/view/loginpane.fxml");
	    }

	    @FXML
	    void findid(ActionEvent event) {
	    		String email = txtiemail.getText();
	    		
	    		String findid=MemberDao.memberDao.findid(email);
	    		if(findid==null) {
	    			Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("알림!");							//메시지 제목설정
	    			alert.setHeaderText("동일한 아이디 없습니다.");
	    			alert.setContentText("띠링");
	    			alert.showAndWait();		
	    		}else {
	    			Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("알림!");							//메시지 제목설정
	    			alert.setHeaderText("당신의 아이디는?");
	    			alert.setContentText(findid);
	    			alert.showAndWait();
	    			Login.본인객체.loadpage("/view/loginpane.fxml");
	    		}
	    }

	
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	
	
	    }

}
