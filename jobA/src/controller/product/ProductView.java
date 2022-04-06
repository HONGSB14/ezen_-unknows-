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
	    		alert.setHeaderText("제품을 정말 삭제하시겠습니까?");
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
		//1.목록에서 선택된 제품 객체를 호출
		Product product = ProductControl.select;
		//2. 각 컨트롤에 값 넣기
		img.setImage(new Image(product.getPimg()));
		txtpname.setText(product.getPname());
		txtpcontent.setText(product.getPcontent());
			//천단위 쉼표
		DecimalFormat decimalFormat = new DecimalFormat("가격: #,##0원");
		txtprice.setText(decimalFormat.format(product.getPprice()) );
		if(product.getPacctivation()==1) {txtpacctivation.setText("판매중");}
		if(product.getPacctivation()==2) {txtpacctivation.setText("거래중");}
		if(product.getPacctivation()==3) {txtpacctivation.setText("판매완료");}
		txtpdate.setText("제품등록일:" +product.getPdate());
		//* 회원번호를 이용한 회원 id찾기 [DAO 에서 메소드 이용]
		txtmid.setText("제품 등록회원: "+MemberDao.memberDao.getmid(product.getMnum()));
		
		txtpname.setEditable(false);
		txtpcontent.setEditable(false);
		
		//제품 등록 회원 번호와 로그인 된 회원 번호가 동일하지 않으면
		if(product.getMnum() != Login.member.getMnum()) {
			btndelete.setVisible(false);
			btnupdate.setVisible(false);
		}
		
	}
}
