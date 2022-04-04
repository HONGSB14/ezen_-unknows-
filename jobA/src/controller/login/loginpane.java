package controller.login;


import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import dao.MemberDao;
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
		lblconfirm.setText("");//로그인 화면시 오류 표시문구를 공백으로 하여 문구를 없내는 부분
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
    	Login.본인객체.loadpage("/view/findid.fxml");
    }

    @FXML
    void accfindpassword(ActionEvent event) {
    		Login.본인객체.loadpage("/view/findpwd.fxml");
    }

    @FXML
    void accsignup(ActionEvent event) {
    		System.out.println("회원가입 페이지로 이동");
    		//login 컨트롤에 존재하는 borderpane 객체 내 센터 변경
    			//문제?!: borderpane  클래스가 다르다.
    			//새롭게 객체 생성을 하면  메모리 할당이 새로 되기 때문에 기존의 것을 가져와야함.
    			//기존 login 클래스에서사용중인 borderpane 을 가져와야함.
    			Login.본인객체.loadpage("/view/signuppane.fxml");
    }

    @FXML
    void login(ActionEvent event) {
    	
    	//1.컨트롤에 입력된 값 가져오기
    		String id = txtid.getText();
    		String pwd = txtpwd.getText();
    	
		//2.DB객체 내 메소드 호출
    		boolean result =MemberDao.memberDao.login(id, pwd);
    	
    	//3.결과 확인
    		if(result) {
    				//로그인 성공시 성공한 회원정보 저장 [ 로그아웃 시 초기화 ]
    				Login.member=MemberDao.memberDao.getMember(id);
    				//페이지 전환
    				Main.본인객체.loadpage("/view/home/home"); // loadpage 메소드에 fxml 이라고 + 로 연결이 되어 있기 때문에 fxml 제외해서 작성하셔야합니다.. [ 강사 코드랑 다름 ] 
    				//*테스트
    				lblconfirm.setText("로그인성공!");
   
    		}else {
    			lblconfirm.setText("동일회원정보를 차지 못하였습니다.");
    		}
    }
}
