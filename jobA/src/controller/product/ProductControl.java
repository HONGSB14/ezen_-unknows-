		package controller.product;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.home.Home;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProductControl implements Initializable{
	
	 public static Product select; // ���õ� ��ư �����ʵ�
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		show(null);	
		
	}
	
		@FXML
	    private Button btnadd;

	    @FXML
	    private TextField txtsearch;

	    @FXML
	    private Button btnsearch;

	    @FXML
	    private ScrollPane scrollpane;
	  
	    @FXML
	    private VBox vbox;

		//��ǰ�߰�
		@FXML
		public void accadd(ActionEvent event) {
		   Home.home.loadpage("/view/product/productadd.fxml");
		}
		   
		//��ǰ�˻�
		@FXML
		public void search(ActionEvent event) {
		   String search =txtsearch.getText(); //�˻�â�� �Էµ� ������ ��������
		   show(search);	//�Է��� �˻�� show �޼ҵ忡 �־��ֱ�
		 }
   
   
		public void show(String search) {
		
		 if(vbox.getChildren().isEmpty()==false) {	// is.empty() : �ش� ��ü �� ����ִ��� Ȯ�� [ vbox �� ��������� ]
			 //vbox �� ��ü�� ������� ������
			 vbox.getChildren().remove(0);	//��ü�� ������� ������ ������ü�� ����
    	 }
    	
		 
    	//1. ��� ��ǰ ��������
		ArrayList<Product> productlist = ProductDao.productDao.list(Home.category ,search);
		
		
	
		
		//* ��ǰ�� ������ ���������� ���� ��  ��,�ټ��϶�  
		
		//2.grid class [ �� /  �� ] 
		GridPane gridPane = new GridPane();
		
			//�׸��� �� ����
				gridPane.setPadding(new Insets(10));
			//�׸��� ��ư ����
				gridPane.setHgap(10);
				gridPane.setVgap(10);
				
				
		//3.�ݺ���
		int i =0;
		for(int row=0; row<productlist.size()/3; row++) { //�� //������ 3 �ϴ� ���� �� ���ٿ� 3����
			
			for(int col =0; col<3; col++) {	//��
				
				//1.�̹��� 
				ImageView imgView = new ImageView(new Image(productlist.get(i).getPimg()));
				
				imgView.setFitHeight(250);
				imgView.setFitWidth(200);
				
				//2.��ư ����
				Button button = new Button(null, imgView);
			
					//��ư ��� ����	[transparent �����]
					button.setStyle("-fx-background-color:transparent");
					//��ư id�� �ֱ� [��ǰ �ĺ� = index]
					button.setId(i+"");
					//��ư Ŭ�� �̺�Ʈ
					button.setOnAction(e->{
						//1.Ŭ���� ��ư id ��������
						int id=Integer.parseInt(e.toString().split(",")[0].split("=")[2]);
						//2.Ŭ���� ��ǰ ��ȣ ����
						 select =productlist.get(id);
						//3. ȭ����ȯ
						 Home.home.loadpage("/view/product/productview.fxml");
						});
					gridPane.add(button , col, row);	//�׸��峻 �ش� ����ȣ, ���ȣ �� ��ư �߰�
				i++;
			}
		}
		
		//* 3����� ������ ��
		int row= productlist.size()/3; 
		int remain = productlist.size() % 3;
		if(remain != 0) {
			
			for(int col =0; col<remain; col++) {	//��
				
				//1.�̹��� 
				ImageView imgView = new ImageView(new Image(productlist.get(i).getPimg()));
				imgView.setFitHeight(250);
				imgView.setFitWidth(200);
				
				//2.��ư ����
				Button button = new Button(null, imgView);
					//��ư ��� ����	[transparent �����]
					button.setStyle("-fx-backgroud-coler:transparent");
					//��ư id�� �ֱ� [��ǰ �ĺ� = index]
					button.setId(i+"");
					//��ư Ŭ�� �̺�Ʈ
					button.setOnAction(e->{
						//1.Ŭ���� ��ư id ��������
						int id=Integer.parseInt(e.toString().split(",")[0].split("=")[2]);
						//2.Ŭ���� ��ǰ ��ȣ ����
						 select =productlist.get(id);
						//3.ȭ����ȯ
						 Home.home.loadpage("/view/product/productview.fxml");
						});
					gridPane.add(button , col, row+1);	//������ �࿡ ������ �� ��ŭ ��ư�� �߰��Ͽ� �׸��忡 �߰�
				i++;
			}
		}
		//4. vbox �� �׸��� �߰��ϱ�
		vbox.getChildren().add(gridPane);
    }

}
