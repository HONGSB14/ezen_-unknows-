package application.Day22;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DB테스트컨트롤러 implements Initializable{
									//Initializable :fxml 새로열렸을때 초기값 설정 메소드
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Day22_2 DB연동 = new Day22_2();
		
	}

	  @FXML
	    private TextField txtwriter;

	    @FXML
	    private TextField txtcontent;

	    @FXML
	    private Button btnget;

	    @FXML
	    private Button btnwrite;

	    @FXML
	    private TextArea txtcontentlist;

	    @FXML
	    void get(ActionEvent event) {
	    	System.out.println("DB내 데이터를 호출합니다.");
	    	
	    	
	    	//1.DB연동 객체 만들기
	    	Day22_2	DB연동 = new Day22_2();
	    	
	    	//2.DB객체 내 메소드 호출
	    	ArrayList<데이터> 데이터목록 = DB연동.get();
	    	txtcontentlist.setText(""); //해당 컨트롤에 공백으로 설정
	    	//3.리스트를 컨트롤에 넣어주기
	    	
	    	for(데이터 temp : 데이터목록) {
	    		
	    		//fxod.appendText : 해당컨트롤에 내용 추가
	    		txtcontentlist.appendText(temp.get번호()+" . "+
	    									temp.get작성자()+":"+
	    										temp.get내용()+"\n"
	    								  );
	    	}
	    }

	    @FXML
	    void write(ActionEvent event) {
	    	System.out.println("DB내 데이터를 저장합니다.");
	    	
	    	//fxid명.getText() : 해당 컨트롤에 입력된 값을 가져오기	
	    	String 작성자 = txtwriter.getText();
	    	String 내용 = txtcontent.getText();
	    		
	    		//1.DB연동 객체 만들기
	    		Day22_2 DB연동 = new Day22_2();
	    		boolean result= DB연동.write(작성자, 내용);
	    		
	    		if(result) {
	    			System.out.println("[DB저장 성공]");
	    			
	    			//성공시 컨트롤에 입력된 데이터를 지워주기
	    				//fxid명.setText() :해당 컨트롤에 값 넣기
	    			txtwriter.setText(""); //성공시 입력창공백으로 만들어주기
	    			txtcontent.setText("");
	    			
	    		}else {
	    			System.out.println("[DB저장 실패]");
	    		}
	    }
}
