package controller.board;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BoardDao;
import dto.Board;
import dto.Reply;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoardView implements Initializable {

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Board board = controller.board.Board.board;
		replytableshow();
		//각 컨트롤에 설정하기
		lblwrite.setText("작성자: "+board.getBwrite());
		lbldate.setText("작성일: "+board.getBdate());
		lblview.setText("조회수: "+board.getBview());
		txttitle.setText(board.getBtitle());
		txtcontent.setText(board.getBcontent());
		//만약 게시물 작성자와 현재로그인 된 id가 동일하지 않다면 
		if(!board.getBwrite().equals(Login.member.getMid())) {
		btndelete.setVisible(false);  	// 버튼 숨기기
		btnupdate.setVisible(false);	// false =버튼숨기기 / true=버튼보이기
		}
		//제목과 내용을 수정못하게 잠금처리 (컨트롤 제어)
		txttitle.setEditable(false);
		txtcontent.setEditable(false);
		
		
	}
		@FXML
	    private Button btnrewrite;

	    @FXML
	    private TextField txttitle;

	    @FXML
	    private TextArea txtcontent;

	    @FXML
	    private Button btnback;

	    @FXML
	    private Button btndelete;

	    @FXML
	    private Button btnupdate;

	    @FXML
	    private Label lblwrite;

	    @FXML
	    private Label lbldate;

	    @FXML
	    private Label lblview;

	    @FXML
	    private TextArea txtrecontent;

	    @FXML
	    private TableView<Reply> replytable;
	  
	    //댓글 테이블 메소드	[여러번 테이블 호출하기 위해 메소드화]
	    public void replytableshow() {
	    	//1.현재 게시물
	    	int bnum = controller.board.Board.board.getBnum();
	    	
	    	//2.
	    	ObservableList<Reply> replylist =  BoardDao.boardDao.replyList(bnum);
	    	
	    	//3.
	    	TableColumn tc = replytable.getColumns().get(0);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("rnum"));
	    	
	    	tc = replytable.getColumns().get(1);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("rwrite"));
	    	
	    	tc = replytable.getColumns().get(2);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("rdate"));
	    	
	    	tc = replytable.getColumns().get(3);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("rcontent"));
	    	
	    	
	    	//4.테이블 뷰에 리스트 넣어주기
	    	replytable.setItems(replylist);
	    }
	    
	    //뒤로가기 메소드
	    @FXML
	    void back(ActionEvent event) {
	    	Home.home.loadpage("/view/board/board.fxml");
	    }

	    //게시물삭제 메소드
	    @FXML
	    void delete(ActionEvent event) {
	    	
	    	//1.경고 메시지 알림
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setHeaderText("해당 게시물 삭제할까요호호호.?");
	    	
	    	//2.확인버튼 눌렀을때
	    	Optional<ButtonType> optional= alert.showAndWait();	//showAndWait() 메소드의 반환타입 => 선택한 버튼
	    		//optional 클래스 : null 를 객체로 저장하는 클래스
	    	
	    	//3.삭제처리
	    	if(optional.get()==ButtonType.OK) {
	    		BoardDao.boardDao.delete(controller.board.Board.board.getBnum());
	    	
	    	//4.페이지 전환 
	    	Home.home.loadpage("/view/board/board.fxml");	
	    	}
	    	
	    	
	    }
	    Alert alert = new Alert(AlertType.INFORMATION);
	  
	    //리플작성 메소드
	    @FXML
	    void rewrite(ActionEvent event) {
	    	String rcontent =txtrecontent.getText();
	    	
	    	String rwrite = Login.member.getMid();
	    	
	    	int bnum = controller.board.Board.board.getBnum();
	    	
	    	Reply reply = new Reply(0, rcontent, rwrite, null, bnum);
	    	boolean result=BoardDao.boardDao.rwrite(reply);
	    	if(result) {
	    		alert.setHeaderText("작성완료!");
	    		alert.showAndWait();
	    		txtrecontent.setText("");
	    		//댓글 작성후 새로고침
	    		replytableshow();
	    	}
	    }
	   
	    
	    boolean anyupdate = true;	//수정 버튼 스위치변수
	    //수정 메소드
	    @FXML
	    void update(ActionEvent event) {
	    	//수정시작
	    	if(anyupdate) { 
	    		
		    	alert.setHeaderText("게시글 수정 후 수정완료 버튼 눌러주세용");
		    	alert.showAndWait();
		    	
		    	txttitle.setEditable(true);
		    	txtcontent.setEditable(true);
		    	
		    	btnupdate.setText("수정완료");
		    	
		    	anyupdate=false;
		    //수정완료 
	    	}else {	
	    		BoardDao.boardDao.update(controller.board.Board.board.getBnum(),
	    				txttitle.getText(), 
	    				txtcontent.getText());
	    		
	    	
		    	alert.setHeaderText("게시글 수정 완료되었습니당");
		    	alert.showAndWait();
		    	
		    	txttitle.setEditable(false);
		    	txtcontent.setEditable(false);
		    	
		    	btnupdate.setText("수정완룡");
		    	
		    	anyupdate=true;
	    	}
	    	
	    	
	    }
	
}
