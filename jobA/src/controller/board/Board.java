package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import dao.BoardDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Board implements Initializable{

	   public static dto.Board board; //테이블에서 클릭한 객체를 저장하는 객체
	
		@FXML
	    private TableView<dto.Board> boardtable;	//테이블에 넣을 자료형 선택

	    @FXML
	    private Button btnwrite;

	    @FXML
	    void accwrite(ActionEvent event) {
	    
	    	// 쓰기 버튼 클릭했을때 메소드 입니다!!!!!
	    	// 버튼 클릭시 Home 클래스내 borderpane center 값을 변경 해야합니다.
	    	// 그래서 기존 home 메모리를 사용하기 위해 this 객체를 만들고 호출합니다.
	    	Home.home.loadpage("/view/board/boardwrite.fxml");
	    	
	    }
	
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
					// Arraylist 가 아닌 ObservableList 사용하는 이유 [tableview ObservableList를 사용]
		
		//1. DB에서 모든 게시물 가져오기
			ObservableList<dto.Board> boardlist = BoardDao.boardDao.list();
		
		//2. tableview 에 추가
			TableColumn tc = boardtable.getColumns().get(0);//TableColumn  :테이블 열	// boardtable.getColumns().get(0) :첫번째 열 호출
			tc.setCellValueFactory(new PropertyValueFactory<>("bnum"));
			
			tc=boardtable.getColumns().get(1);
			tc.setCellValueFactory(new PropertyValueFactory<>("btitle"));
			
			tc=boardtable.getColumns().get(2);
			tc.setCellValueFactory(new PropertyValueFactory<>("bwrite"));
			
			tc=boardtable.getColumns().get(3);
			tc.setCellValueFactory(new PropertyValueFactory<>("bdate"));
			
			tc=boardtable.getColumns().get(4);
			tc.setCellValueFactory(new PropertyValueFactory<>("bview"));
		
		//3. tableview 에 list 연결
			boardtable.setItems(boardlist);
			//테이블명(fxid).setItems(observableList);  테이블에 표시할 리스트 (일반적 Arreylist 사용 불가)
		
		//* tableview 에서 해당 셀 클릭했을때 이벤트

			// [1회용]	인수 -> 람다식 (익명함수: 이름이 없는 함수 [인수와 실행코드만 존재] ) 
			
			//1.테이블에서 클릭한 객체를 임시객체에 저장
			//boardtable.setOnMouseClicked( ) : 테이블을 클릭 했을때
			boardtable.setOnMouseClicked( e -> {
				
			board=boardtable.getSelectionModel().getSelectedItem();
			//2.조회수 증가
				
			//3.페이지전환
			System.out.println(board.getBtitle());
			Home.home.loadpage("/view/board/boardview.fxml");
			
			}
			);	//클릭된 board객체 호출
		
			
			
			
			
	}


}
