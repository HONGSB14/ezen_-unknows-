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
		lblconfirm.setText("");//�α��� ȭ��� ���� ǥ�ù����� �������� �Ͽ� ������ ������ �κ�
		
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
    	Login.���ΰ�ü.loadpage("/view/loginpane.fxml");
    	
    }

    @FXML
    void signup(ActionEvent event) {

    	String id =txtid.getText();
    	String password = txtpwd.getText();
    	String passwordconfirm = txtpwdconfirm.getText();
    	String email =txtiemail.getText();
    	String adress = txtadd.getText();
  
    	//���� ��¥ ��������
    	SimpleDateFormat formet = new SimpleDateFormat("yyyy-MM-dd");
    	String cine = formet.format(new Date());
    	
    	//��ȿ�� �˻� [����]
    		//1. id	�ߺ�	üũ
    		 boolean result2 = MemberDao.memberDao.idcheck(id);
    		 
    		 if(result2) {
    			 lblconfirm.setText("������� ���̵��Դϴ�.");
    			 return; //���Ը��ϰ� return ���� Ż��
    		 }
    		
    		//2. id ���� üũ	[���̰� 4~10 ���� ]
    		if(id.length()<4 || id.length()> 10 ) {
    			lblconfirm.setText("���̵� 4~10 �ڸ��� �����ּ���.");
    			return;
    		}
    		
    		//3. ��й�ȣ ���� üũ [ ���ڿ�.length() : ���ڿ� ���� ]
    		if(password.length()<4 || password.length()> 10 || passwordconfirm.length()<4 && passwordconfirm.length()>10 ) {
    			lblconfirm.setText("��й�ȣ�� 4~10 �ڸ��� �����ּ���.");
    			return;
    		}
    		
    		//4. ��й�ȣ ��ġ üũ
    		if(! password.equals(passwordconfirm)) {
    			lblconfirm.setText("�н����尡 ��ġ ���� �ʽ��ϴ�.");
    			return;
    		}
    		
    		//5. email üũ	[���ڿ�.indextOf("����") : �ش繮�ڿ� �� ���ڰ� �����ϸ� �ش� ������ �ε��� ȣ�� / ���� ������ -1
    		if(email.indexOf("@")==-1) {	//���� �Է��� �̸��Ͽ� @�� ������ 
    			lblconfirm.setText("�̸��� ���Ŀ� �°� �����Ͽ��ּ���. @");
    			return;
    		}
    		
    		//6. �ּ� üũ	[���ڿ�.contains("����") : �ش� ���ڿ� �� ���ڰ� �����ϸ� true / ������ false]
    		if(!(adress.contains("��") &&adress.contains("��")&&adress.contains("��"))) {
    			lblconfirm.setText("�ּ� ���� [��,��,�� ����] ���� �Է����ּ���.");
    			return;
    		}
    		
    	// ��� ��ȿ�� �˻� ��� �� DB�� ����
    		//1. ��üȭ	[ȸ����ȣ ���� /id ��]
    		Member member = new Member(0, id, password, email, adress, 0, cine);
    		
    		//2. DB ��ü�� �޼ҵ� ȣ��
    		boolean result=MemberDao.memberDao.signup(member);
    		if(result){
    			
    			//1.�޽��� â ��� [Alert : �޽��� (�˶�) Ŭ����
    			Alert alert = new Alert(AlertType.INFORMATION);	//�޽��� ��ü ����
    			alert.setTitle("�˸�!");							//�޽��� ������
    			alert.setHeaderText("��¥ �ο����??");
    			alert.setContentText("������û�Ϸ�");
    			alert.showAndWait();							//�޽��� ����
 
    			//2.ȭ�� ��ȯ [�α��� ������ ��ȯ]
    			Login.���ΰ�ü.loadpage("/view/loginpane.fxml");
    		}else {
    			System.out.println("ȸ������ ����");
    		}
    		//3.Ȯ��
    }
}
