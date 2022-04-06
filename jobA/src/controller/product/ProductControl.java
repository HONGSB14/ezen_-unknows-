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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProductControl implements Initializable{
	
	 public static Product select; // 선택된 버튼 저장필드
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//1. 모든 제품 가져오기
		ArrayList<Product> productlist = ProductDao.productDao.list();
		
	
		
		//* 제품의 개수가 고정적이지 않을 때  즉,다수일때  
		
		//2.grid class [ 행 /  열 ] 
		GridPane gridPane = new GridPane();
		
			//그리드 간 여백
				gridPane.setPadding(new Insets(10));
			//그리드 버튼 여백
				gridPane.setHgap(10);
				gridPane.setVgap(10);
				
				
		//3.반복문
		int i =0;
		for(int row=0; row<productlist.size()/3; row++) { //행 //나누기 3 하는 이유 는 한줄에 3개씩
			
			for(int col =0; col<3; col++) {	//열
				
				//1.이미지 
				ImageView imgView = new ImageView(new Image(productlist.get(i).getPimg()));
				
				imgView.setFitHeight(250);
				imgView.setFitWidth(200);
				
				//2.버튼 생성
				Button button = new Button(null, imgView);
			
					//버튼 배경 제거	[transparent 투명색]
					button.setStyle("-fx-background-color:transparent");
					//버튼 id값 넣기 [제품 식별 = index]
					button.setId(i+"");
					//버튼 클릭 이벤트
					button.setOnAction(e->{
						//1.클릭한 버튼 id 가져오기
						int id=Integer.parseInt(e.toString().split(",")[0].split("=")[2]);
						//2.클릭한 제품 번호 저장
						 select =productlist.get(id);
						//3. 화면전환
						 Home.home.loadpage("/view/product/productview.fxml");
						});
					gridPane.add(button , col, row);	//그리드내 해당 열번호, 행번호 에 버튼 추가
				i++;
			}
		}
		
		//* 3배수의 나머지 값
		int row= productlist.size()/3; 
		int remain = productlist.size() % 3;
		if(remain != 0) {
			
			for(int col =0; col<remain; col++) {	//열
				
				//1.이미지 
				ImageView imgView = new ImageView(new Image(productlist.get(i).getPimg()));
				imgView.setFitHeight(250);
				imgView.setFitWidth(200);
				
				//2.버튼 생성
				Button button = new Button(null, imgView);
					//버튼 배경 제거	[transparent 투명색]
					button.setStyle("-fx-backgroud-coler:transparent");
					//버튼 id값 넣기 [제품 식별 = index]
					button.setId(i+"");
					//버튼 클릭 이벤트
					button.setOnAction(e->{
						//1.클릭한 버튼 id 가져오기
						int id=Integer.parseInt(e.toString().split(",")[0].split("=")[2]);
						//2.클릭한 제품 번호 저장
						 select =productlist.get(id);
						//3.화면전환
						 Home.home.loadpage("/view/product/productview.fxml");
						});
					gridPane.add(button , col, row);	//마지막 행에 나머지 값 만큼 버튼을 추가하여 그리드에 추가
				i++;
			}
		}
		
		//4.
		vbox.getChildren().add(gridPane);
		
	}
	
	@FXML
    private Button btnadd;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vbox;
    
  
    @FXML
    void accadd(ActionEvent event) {
    	Home.home.loadpage("/view/product/productadd.fxml");
    }
	

}
