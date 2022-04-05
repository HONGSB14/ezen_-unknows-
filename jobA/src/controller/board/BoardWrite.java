package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BoardDao;
import dto.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class BoardWrite implements Initializable {

    @FXML
    private Button btnwrite;

    @FXML
    private TextField txttitle;

    @FXML
    private TextArea txtcontent;

    @FXML
    private Button btnback;

    @FXML
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/board/board.fxml");
    	//여기도 마찬가지 입니다
    	// 목록보기 버튼을 클릭했을때 메소드 입니다.
    	// 버튼 클릭시 Home 클래스내 borderpane center 값을 변경 해야합니다.
    	// 그래서 기존 home 메모리를 사용하기 위해 this 객체를 만들고 호출합니다.
    	// 같은 이유 입니다..
    		
    }

    @FXML
    void write(ActionEvent event) {
    	
    	//1.컨트롤에 입력된 데이터 가져오기
	    	String title = txttitle.getText();	//해당 fxid객체에서 입력된 내용 가져오기
	    	String content = txtcontent.getText();
	    	//현재 로그인 한 아이디가 곧 작성자!!
	    	String write = Login.member.getMid();
    	//2.객체화
	    	Board board = new Board(0, title, content, write, null, 0);
    			
    	//3.DB저장[파일처리]
	    	boolean result =BoardDao.boardDao.write(board);
	    	
	    	if(result) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("게시물 작성 완료 ");
	    		alert.showAndWait();
	    		
	    		Home.home.loadpage("/view/board/board.fxml");
	    	}else {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("게시물 작성 실패 ");
	    		alert.showAndWait();
	    	}
    	
    }

	
	
	  @Override
	  public void initialize(URL arg0, ResourceBundle arg1) {
	    	

	  }


}
