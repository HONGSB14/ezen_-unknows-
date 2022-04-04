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
		lblconfirm.setText("");//�α��� ȭ��� ���� ǥ�ù����� �������� �Ͽ� ������ ������ �κ�
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
    	Login.���ΰ�ü.loadpage("/view/findid.fxml");
    }

    @FXML
    void accfindpassword(ActionEvent event) {
    		Login.���ΰ�ü.loadpage("/view/findpwd.fxml");
    }

    @FXML
    void accsignup(ActionEvent event) {
    		System.out.println("ȸ������ �������� �̵�");
    		//login ��Ʈ�ѿ� �����ϴ� borderpane ��ü �� ���� ����
    			//����?!: borderpane  Ŭ������ �ٸ���.
    			//���Ӱ� ��ü ������ �ϸ�  �޸� �Ҵ��� ���� �Ǳ� ������ ������ ���� �����;���.
    			//���� login Ŭ��������������� borderpane �� �����;���.
    			Login.���ΰ�ü.loadpage("/view/signuppane.fxml");
    }

    @FXML
    void login(ActionEvent event) {
    	
    	//1.��Ʈ�ѿ� �Էµ� �� ��������
    		String id = txtid.getText();
    		String pwd = txtpwd.getText();
    	
		//2.DB��ü �� �޼ҵ� ȣ��
    		boolean result =MemberDao.memberDao.login(id, pwd);
    	
    	//3.��� Ȯ��
    		if(result) {
    				//�α��� ������ ������ ȸ������ ���� [ �α׾ƿ� �� �ʱ�ȭ ]
    				Login.member=MemberDao.memberDao.getMember(id);
    				//������ ��ȯ
    				Main.���ΰ�ü.loadpage("/view/home/home"); // loadpage �޼ҵ忡 fxml �̶�� + �� ������ �Ǿ� �ֱ� ������ fxml �����ؼ� �ۼ��ϼž��մϴ�.. [ ���� �ڵ�� �ٸ� ] 
    				//*�׽�Ʈ
    				lblconfirm.setText("�α��μ���!");
   
    		}else {
    			lblconfirm.setText("����ȸ�������� ���� ���Ͽ����ϴ�.");
    		}
    }
}
