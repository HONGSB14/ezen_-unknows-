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
    		System.out.println("아이디 찾기로 이동");
    }

    @FXML
    void accfindpassword(ActionEvent event) {
    		System.out.println("패스워드 찾기로 이동");
    }

    @FXML
    void accsignup(ActionEvent event) {
    		System.out.println("회원가입 페이지로 이동");
    		//login 컨트롤에 존재하는 borderpane 객체 내 센터 변경
    			//문제?!: borderpane  클래스가 다르다.
    			//새롭게 객체 생성을 하면  메모리 할당이 새로 되기 때문에 기존의 것을 가져와야함.
    			//기존 login 클래스에서사용중인 borderpane 을 가져와야함.
    			Login.본인객체.loadpage("/view/sighuppane.fxml");
    }

    @FXML
    void login(ActionEvent event) {
    	System.out.println("로그인 처리");
    	
    	String id = txtid.getText();
    	String pwd = txtpwd.getText();
    	
    	if(id.equals("adimn") && pwd.equals("1234")) {
    		lblconfirm.setText("hi admin! ");
    	}else {
    		lblconfirm.setText("환영합니다!");
    	}
    	System.out.println("로그인 처리 !!");
    }
	
	

}
