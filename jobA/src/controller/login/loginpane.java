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

public class loginpane  implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	
	}

    @FXML
    private TextField txtid;

    @FXML
    private PasswordField txtpwd;

    @FXML
    private Button btnsignup;

    @FXML
    private Button btnlogin;

    @FXML
    private Button btnfindid;

    @FXML
    private Button btnfindpwd;

    @FXML
    private Label lblconfirm;

    @FXML
    void accfindid(ActionEvent event) {
    		System.out.println("���̵� ã��� �̵�");
    }

    @FXML
    void accfindpassword(ActionEvent event) {
    		System.out.println("�н����� ã��� �̵�");
    }

    @FXML
    void accsignup(ActionEvent event) {
    		System.out.println("ȸ������ �������� �̵�");
    		//login ��Ʈ�ѿ� �����ϴ� borderpane ��ü �� ���� ����
    			//����?!: borderpane  Ŭ������ �ٸ���.
    			//���Ӱ� ��ü ������ �ϸ�  �޸� �Ҵ��� ���� �Ǳ� ������ ������ ���� �����;���.
    			//���� login Ŭ��������������� borderpane �� �����;���.
    			Login.���ΰ�ü.loadpage("/view/sighuppane.fxml");
    }

    @FXML
    void login(ActionEvent event) {
    	System.out.println("�α��� ó��");
    	
    	String id = txtid.getText();
    	String pwd = txtpwd.getText();
    	
    	if(id.equals("adimn") && pwd.equals("1234")) {
    		lblconfirm.setText("hi admin! ");
    	}else {
    		lblconfirm.setText("ȯ���մϴ�!");
    	}
    	System.out.println("�α��� ó�� !!");
    }
	
	

}
