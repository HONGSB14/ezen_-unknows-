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
	    void back(ActionEvent event) {
	    		Home.home.loadpage("/view/product/product.fxml");
	    }

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

	    @FXML
	    void update(ActionEvent event) {
	    		Home.home.loadpage("/view/product/productupdate.fxml");
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
		if(product.getPacctivation()==1) {txtpacctivation.setText("�Ǹ���");}
		if(product.getPacctivation()==2) {txtpacctivation.setText("�ŷ���");}
		if(product.getPacctivation()==3) {txtpacctivation.setText("�ǸſϷ�");}
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
