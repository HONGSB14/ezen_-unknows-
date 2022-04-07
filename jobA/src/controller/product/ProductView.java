package controller.product;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import controller.login.loginpane;
import dao.MemberDao;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProductView implements Initializable{

		Alert alert = new Alert(AlertType.CONFIRMATION);
	    @FXML
	    private Button btnupdate;

	    @FXML
	    private TextField txtpname;

	    @FXML
	    private TextArea txtpcontent;

	    @FXML
	    private ImageView img;

	    @FXML
	    private Button btndelete;

	    @FXML
	    private Button btnback;

	    @FXML
	    private Label txtprice;

	    @FXML
	    private Label txtpdate;

	    @FXML
	    private Label txtmid;

	    @FXML
	    private Label txtpacctivation;
	    
	    @FXML
	    private Button btnacctivation;
	   
	    //�ڷΰ��� �޼ҵ�
	    @FXML
	    void back(ActionEvent event) {
	    		Home.home.loadpage("/view/product/product.fxml");
	    }

	    //���� �޼ҵ�
	    @FXML
	    void delete(ActionEvent event) {
	    		alert.setHeaderText("��ǰ�� ���� �����Ͻðڽ��ϱ�?");
	    		Optional<ButtonType> optional = alert.showAndWait();
	    		if(optional.get()== ButtonType.OK) {
	    			boolean result=ProductDao.productDao.delete(ProductControl.select.getpnum());
	    			if(result) {
	    				Home.home.loadpage("/view/product/product.fxml");
	    			}
	    		}
	    }

	    //���� �޼ҵ�
	    @FXML
	    void update(ActionEvent event) {
	    		Home.home.loadpage("/view/product/productupdate.fxml");
	    }

	    //�ǸŻ��� ��ư
	    @FXML
	    void acctivation(ActionEvent event) {
	    	if(btnacctivation.getText().equals("�ŷ� ��")) {
	    		//��Ʈ�� �� ����
	    		txtpacctivation.setText("����: �ŷ� ��");
	    		btnacctivation.setText("�ǸſϷ�");
	    		//DB�� �� ����
	    		ProductDao.productDao.activation(ProductControl.select.getpnum());
	    		//���õ� ��ǰ�� ����
	    		ProductControl.select.setPacctivation(2);
	    		return;
	    	}
	    	if(btnacctivation.getText().equals("�ǸſϷ�")) {
	    		txtpacctivation.setText("����: �ǸſϷ�");
	    		btnacctivation.setText("�Ǹ� ��");
	    		ProductDao.productDao.activation(ProductControl.select.getpnum());
	    		ProductControl.select.setPacctivation(3);
	    		return;
	    	}
	    	if(btnacctivation.getText().equals("�Ǹ� ��")) {
	    		txtpacctivation.setText("����: �Ǹ� ��");
	    		btnacctivation.setText("�ŷ� ��");
	    		ProductDao.productDao.activation(ProductControl.select.getpnum());
	    		ProductControl.select.setPacctivation(1);
	    		return;
	    	}
	    }
	
	
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	//1.��Ͽ��� ���õ� ��ǰ ��ü�� ȣ��
	    	Product product = ProductControl.select;
			//2. �� ��Ʈ�ѿ� �� �ֱ�
			img.setImage(new Image(product.getPimg()));
			txtpname.setText(product.getPname());
			txtpcontent.setText(product.getPcontent());
				//õ���� ��ǥ
			DecimalFormat decimalFormat = new DecimalFormat("����: #,##0��");
			txtprice.setText(decimalFormat.format(product.getPprice()) );
			if(product.getPacctivation()==1) {
				txtpacctivation.setText("�Ǹ� ��"); 
				btnacctivation.setText("�ŷ� ��");
			}
			if(product.getPacctivation()==2) {
				txtpacctivation.setText("�ŷ� ��");
				btnacctivation.setText("�ǸſϷ�");
			}
			if(product.getPacctivation()==3) {
				txtpacctivation.setText("�ǸſϷ�");
				btnacctivation.setText("�Ǹ� ��");
			}
			txtpdate.setText("��ǰ�����:" +product.getPdate());
			//* ȸ����ȣ�� �̿��� ȸ�� idã�� [DAO ���� �޼ҵ� �̿�]
			txtmid.setText("��ǰ ���ȸ��: "+MemberDao.memberDao.getmid(product.getMnum()));
			
			txtpname.setEditable(false);
			txtpcontent.setEditable(false);
			
			//��ǰ ��� ȸ�� ��ȣ�� �α��� �� ȸ�� ��ȣ�� �������� ������
			if(product.getMnum() != Login.member.getMnum()) {
			btndelete.setVisible(false);
			btnupdate.setVisible(false);
		}
		
	}
}
