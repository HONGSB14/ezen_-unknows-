package controller.login;


import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Signuppane implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblconfirm.setText("");//로그인 화면시 오류 표시문구를 공백으로 하여 문구를 없내는 부분
		
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
    	Login.본인객체.loadpage("/view/loginpane.fxml");
    	
    }

    @FXML
    void signup(ActionEvent event) {

    	String id =txtid.getText();
    	String password = txtpwd.getText();
    	String passwordconfirm = txtpwdconfirm.getText();
    	String email =txtiemail.getText();
    	String adress = txtadd.getText();
  
    	//현재 날짜 가져오기
    	SimpleDateFormat formet = new SimpleDateFormat("yyyy-MM-dd");
    	String cine = formet.format(new Date());
    	
    	//유효성 검사 [제한]
    		//1. id	중복	체크
    		 boolean result2 = MemberDao.memberDao.idcheck(id);
    		 
    		 if(result2) {
    			 lblconfirm.setText("사용중인 아이디입니다.");
    			 return; //가입못하게 return 으로 탈출
    		 }
    		
    		//2. id 형식 체크	[길이가 4~10 사이 ]
    		if(id.length()<4 || id.length()> 10 ) {
    			lblconfirm.setText("아이디를 4~10 자리로 맞춰주세요.");
    			return;
    		}
    		
    		//3. 비밀번호 길이 체크 [ 문자열.length() : 문자열 길이 ]
    		if(password.length()<4 || password.length()> 10 || passwordconfirm.length()<4 && passwordconfirm.length()>10 ) {
    			lblconfirm.setText("비밀번호를 4~10 자리로 맞춰주세요.");
    			return;
    		}
    		
    		//4. 비밀번호 일치 체크
    		if(! password.equals(passwordconfirm)) {
    			lblconfirm.setText("패스워드가 일치 하지 않습니다.");
    			return;
    		}
    		
    		//5. email 체크	[문자열.indextOf("문자") : 해당문자열 내 문자가 존재하면 해당 문자의 인덱스 호출 / 만일 없으면 -1
    		if(email.indexOf("@")==-1) {	//만약 입력한 이메일에 @가 없으면 
    			lblconfirm.setText("이메일 형식에 맞게 기입하여주세요. @");
    			return;
    		}
    		
    		//6. 주소 체크	[문자열.contains("문자") : 해당 문자열 내 문자가 존재하면 true / 없으면 false]
    		if(!(adress.contains("시") &&adress.contains("구")&&adress.contains("동"))) {
    			lblconfirm.setText("주소 형식 [시,구,동 포함] 으로 입력해주세요.");
    			return;
    		}
    		
    	// 모든 유효성 검사 통과 시 DB에 저장
    		//1. 객체화	[회원번호 없음 /id 값]
    		Member member = new Member(0, id, password, email, adress, 0, cine);
    		
    		//2. DB 객체내 메소드 호출
    		boolean result=MemberDao.memberDao.signup(member);
    		if(result){
    			
    			//1.메시지 창 출력 [Alert : 메시지 (알람) 클래스
    			Alert alert = new Alert(AlertType.INFORMATION);	//메시지 객체 생성
    			alert.setTitle("알림!");							//메시지 제목설정
    			alert.setHeaderText("진짜 싸울려고??");
    			alert.setContentText("결투신청완료");
    			alert.showAndWait();							//메시지 실행
 
    			//2.화면 전환 [로그인 페이지 전환]
    			Login.본인객체.loadpage("/view/loginpane.fxml");
    		}else {
    			System.out.println("회원가입 실패");
    		}
    		//3.확인
    }
}
