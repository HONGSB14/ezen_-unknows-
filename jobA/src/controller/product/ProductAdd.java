package controller.product;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import controller.home.Home;
import controller.login.Login;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ProductAdd implements Initializable{
	
	Alert alert = new Alert(AlertType.INFORMATION);
	
	private String pimage =null;//메소드 밖에서 선언하는 이유: imgadd 메소드와  add메소드에서 사용하기 위해
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	 @FXML
	    private Button btnadd;

	    @FXML
	    private TextField txtpname;

	    @FXML
	    private TextArea txtpcontent;

	    @FXML
	    private TextField txtpprice;

	    @FXML
	    private Button btnimg;

	    @FXML
	    private RadioButton opt1;

	    @FXML
	    private ToggleGroup category;

	    @FXML
	    private RadioButton opt2;

	    @FXML
	    private RadioButton opt3;

	    @FXML
	    private RadioButton opt4;

	    @FXML
	    private ImageView imp;

	    @FXML
	    private Button btnback;

	    @FXML
	    private Label txtpath;

	    @FXML
	    private Label miribogi;
 
    //상품등록 메소드
    @FXML
    void add(ActionEvent event) {
    	//1.컨트롤에 입력된 데이터 가져오기
    	String pname = txtpname.getText();
    	String pcontent = txtpcontent.getText();
    	int pprice =Integer.parseInt(txtpprice.getText());
    	
    	String pcategory = null;
    	if(opt1.isSelected()) {//만약에 opt1 이라는 fxid 의 컨트롤(라디오버튼)이 선택되었으면 
    		pcategory="남성의류";
    	}
    	if(opt2.isSelected()) {
    		pcategory="여성의류";
    	}
    	if(opt3.isSelected()) {
    		pcategory ="태권용품";
    	}
    	if(opt4.isSelected()) {
    		pcategory ="어린이태권용품";
    	}
    	int mnum = Login.member.getMnum(); 	// (로그인정보 불러오기) loginpane 에  로그인 성공시 로그인 정보를 이미 담아놨기때문에 
    	
    	//2.객체화
    	Product product = new Product(0, pname, pimage, pcontent, pcategory, pprice, 1, null, mnum);
    	//3.DB처리
    	boolean result= ProductDao.productDao.add(product);
    	//4.결과처리
    		if(result) {
    			
    			alert.setHeaderText("제품 등록 완료");
    			alert.showAndWait();
    			Home.home.loadpage("/view/product/product.fxml");
    		}else {
    			alert.setHeaderText("제품 등록 실패");
				alert.showAndWait();
    		}
    }
   
    //뒤로가기 메소드
    @FXML
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/product/product.fxml");
    }
    
    //이미지 등록 메소드
    @FXML
    void imgadd(ActionEvent event) {
    	
    	//1. 파일 선택 클래스 
    		FileChooser fileChooser = new FileChooser();
    	
    	//2.파일선택 [필터] 형식 
    		fileChooser.getExtensionFilters().add(new ExtensionFilter("이미지파일:image file","*png","*jpg","*jepg","*gif"));
    	
    	//3.새로운 stage에서 파일 선택화면 실행 (윈도우 창)
    		File file=fileChooser.showOpenDialog(new Stage());
	    		//파일선택객체.showOpenDialog(스테이지 이름) ;
	    		//fileChooser 화면에서 선택된 파일을 file 클래스 객체화
    	
    	//4.선택 파일 경로 표시
    		txtpath.setText("파일경로:" +file.getPath());
    	
    	//5.파일경로 
    		pimage = file.toURI().toString();
    		
//    			System.out.println(file.getPath());				//경로 구분선: get path ->\
//    			System.out.println(file.toURI());				//경로 구분선:	toURI 	 ->/
//    			System.out.println(file.toURI().toString());	//문자열 변환
    	
    	
    	//6.미리보기 이미지컨트롤에 띄우기
    		Image image = new Image(pimage);	//해당 이미지의 경로값이 / 구분되어 있어야함.
    		miribogi.setText("");
    		imp.setImage(image);	
    		
    	//* 선택된 파일을 현재 프로젝트 폴더로 복사(이동) 해오기
    		try {
    			
    			//1.파일 입력 스트림
    			FileInputStream inputStream = new FileInputStream(file); //file: fileChooser 에서 선택된 파일 객체 inputstream 에 넣기
        		
    			//2.파일 출력 스트림
    			File copyfile = new File("C:\\Users\\504\\git\\ezen_-unknows-\\jobA\\src\\img\\"+file.getName()); //새로운 경로 설정
        		FileOutputStream outputStream = new FileOutputStream(copyfile);
				
        		//3.바이트 배열 서언
        		byte [] bytes = new byte [1024*1024*1024];
        		
        		//4.반복문을 이용한 inputstream의 파일 스트림 모두 읽어오기
        		int size;
        		while(( size =inputStream.read(bytes))>0) {	//읽어온 바이트가 0보다 작으면 반복문 종료[ 읽어올 바이트가 없기 때문]
        				outputStream.write(bytes, 0 ,size);
        		}
        		//5.용랑이 큰 경우에는 스트림 종료 필수~!
        		inputStream.close();
        		outputStream.close();
        		//* 파일명 DB저장
        		pimage= copyfile.toURI().toString();
    		} catch (Exception e) {
				System.out.println("파일에러:"+e);
			}
    		
    	
    		
    	
    }

}
