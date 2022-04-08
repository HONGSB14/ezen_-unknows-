package controller.home;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.Main;
import controller.login.Login;
import dao.MemberDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Home implements Initializable {

	public static Home home;
	public static String category;
	
	public Home() {
		home = this;
	}
	
	
	@FXML
	private Label lblloginid;
	@FXML
	private Label lblpoint;
	@FXML
	private Label lbllogout;
	@FXML
	private Label lbldelete;
	@FXML
	private Label lblchange;
	@FXML
	private BorderPane borderpane;
	@FXML
	private Label lblinfo;
	@FXML
	private Label lblupdate;
	@FXML
	private Label lblboard;
	@FXML
	private Label lblproduct1;
	@FXML
	private Label lblproduct2;
	@FXML
	private Label lblproduct3;
	@FXML
	private Label lblproduct4;
	
	//ȸ������ ���̺� Ŭ���̺�Ʈ
	@FXML
	public void accinfo(MouseEvent e) {
		loadpage("/view/home/info.fxml");
	}
	
	//�α׾ƿ� �̺�Ʈ
	@FXML	
	public void logout (MouseEvent e) {
		//1.login ���� �����
		Login.member= null;
		//2.��������ȯ
		Main.���ΰ�ü.loadpage("/view/login");
	}
	
	//ȸ������ �̺�Ʈ
	@FXML 
	public void delete(MouseEvent e ) {
		//1.�޽���
		Alert alert = new Alert(AlertType.CONFIRMATION);			//Ȯ��,��� ��ư Ÿ��
			alert.setHeaderText("���� Ż���Ͻðڽ��ϱ�?");
			//2.��ư Ȯ�� [Optional :Ŭ����]
			Optional<ButtonType> optional = alert.showAndWait();	//����
			if(optional.get() == ButtonType.OK) {					//Ȯ�� ��ư ������ 
				boolean result=MemberDao.memberDao.delete(Login.member.getMnum());	//���� �α��� ���� ȸ���� ��ȣ ȣ�� 
					if(result) {//Ż�𼺰�
							//�α׾ƿ�[Login Ŭ���� �� Member ��ü null �� ����]
							Login.member =null;
							//������ ��ȯ
							Main.���ΰ�ü.loadpage("/view/login");
					}
			}
			//�ƴϸ�
	}	
	
	//ȸ������ �̺�Ʈ
	@FXML
	public void update (MouseEvent e) {
		loadpage("/view/home/update.fxml");
	}
	
	//HOME �������̵�
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadpage("/view/chatting.fxml");
		lblloginid.setText(Login.member.getMid()+"��");
		lblpoint.setText("����Ʈ"+Login.member.getMpoint()+"��");
		
		
	}
	
	//ȭ����ȯ �̺�Ʈ
	public void loadpage(String page) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page));
			borderpane.setCenter(parent);
		} catch (Exception e) {
			System.out.println( e );
		}
	}

	//�����Խ� �̺�Ʈ
	public void board(MouseEvent e) {
		loadpage("/view/board/board.fxml"); 
	}
	
	
		
	//��ǰ���1 �̺�Ʈ	
	@FXML
	void accproduct1(MouseEvent event) {
		loadpage("/view/product/product.fxml");
		category="�±ǵ�";
	   }
	//��ǰ���2 �̺�Ʈ
	@FXML
	void accproduct2(MouseEvent event) {
	    loadpage("/view/product/product.fxml"); 
    	category="����";
	}
	//��ǰ���3 �̺�Ʈ
	@FXML
	void accproduct3(MouseEvent event) {
		loadpage("/view/product/product.fxml"); 
		category="�˵�";
	}
	//��ǰ���4 �̺�Ʈ
	@FXML
	void accproduct4(MouseEvent event) {
	    loadpage("/view/product/product.fxml");	
		category="��Ǫ";
	}


}
