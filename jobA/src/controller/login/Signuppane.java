package controller.login;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Signuppane implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
    private TextField txtid;

    @FXML
    private PasswordField txtpwdconfirm;

    @FXML
    private Button btnback;

    @FXML
    private Button btnsignup;

    @FXML
    private Label lblconfirm;

    @FXML
    private PasswordField txtpwd;

    @FXML
    private TextField txtiemail;

    @FXML
    private TextField txtadd;

    @FXML
    void back(ActionEvent event) {
    	Login.∫ª¿Œ∞¥√º.loadpage("/view/loginpane.fxml");
    }

    @FXML
    void signup(ActionEvent event) {

    }


}
