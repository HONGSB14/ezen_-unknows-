package controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.login.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class Main implements Initializable {
							//Initializable  :�ش� fxml�� �������� �ʱⰪ �޼ҵ� ���� ( view�� �������� �ʱⰪ ���� �޼ҵ� ����)

    public static Main ���ΰ�ü;
    
    public Main() {
    	���ΰ�ü=this;
    }
	
	@FXML
    private BorderPane borderpane; //fx :id 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	// �ش� fxml�� �������� �ʱⰪ �޼ҵ� ����
		loadpage("/view/login");	//loadpage �޼ҵ� ȣ�� �� [���� ���]
		
	}
	
	public void loadpage(String page) {// loadpage (���ϰ��)
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page+".fxml"));
			borderpane.setCenter(parent);	//main.fxml �� �����ϴ� boderpane ��ü �� center�� �ش� ���Ϸ� ����
		} catch (Exception e) {	//���࿡ ������ �������� ������ ���� �߻�
			System.out.println("������ ���� ���� ���� " +e); 
		}
		
	}
}
