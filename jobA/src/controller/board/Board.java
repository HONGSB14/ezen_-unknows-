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

	   public static dto.Board board; //���̺��� Ŭ���� ��ü�� �����ϴ� ��ü
	
		@FXML
	    private TableView<dto.Board> boardtable;	//���̺� ���� �ڷ��� ����

	    @FXML
	    private Button btnwrite;

	    @FXML
	    void accwrite(ActionEvent event) {
	    
	    	// ���� ��ư Ŭ�������� �޼ҵ� �Դϴ�!!!!!
	    	// ��ư Ŭ���� Home Ŭ������ borderpane center ���� ���� �ؾ��մϴ�.
	    	// �׷��� ���� home �޸𸮸� ����ϱ� ���� this ��ü�� ����� ȣ���մϴ�.
	    	Home.home.loadpage("/view/board/boardwrite.fxml");
	    	
	    }
	
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
					// Arraylist �� �ƴ� ObservableList ����ϴ� ���� [tableview ObservableList�� ���]
		
		//1. DB���� ��� �Խù� ��������
			ObservableList<dto.Board> boardlist = BoardDao.boardDao.list();
		
		//2. tableview �� �߰�
			TableColumn tc = boardtable.getColumns().get(0);//TableColumn  :���̺� ��	// boardtable.getColumns().get(0) :ù��° �� ȣ��
			tc.setCellValueFactory(new PropertyValueFactory<>("bnum"));
			
			tc=boardtable.getColumns().get(1);
			tc.setCellValueFactory(new PropertyValueFactory<>("btitle"));
			
			tc=boardtable.getColumns().get(2);
			tc.setCellValueFactory(new PropertyValueFactory<>("bwrite"));
			
			tc=boardtable.getColumns().get(3);
			tc.setCellValueFactory(new PropertyValueFactory<>("bdate"));
			
			tc=boardtable.getColumns().get(4);
			tc.setCellValueFactory(new PropertyValueFactory<>("bview"));
		
		//3. tableview �� list ����
			boardtable.setItems(boardlist);
			//���̺��(fxid).setItems(observableList);  ���̺� ǥ���� ����Ʈ (�Ϲ��� Arreylist ��� �Ұ�)
		
		//* tableview ���� �ش� �� Ŭ�������� �̺�Ʈ

			// [1ȸ��]	�μ� -> ���ٽ� (�͸��Լ�: �̸��� ���� �Լ� [�μ��� �����ڵ常 ����] ) 
			
			//1.���̺��� Ŭ���� ��ü�� �ӽð�ü�� ����
			//boardtable.setOnMouseClicked( ) : ���̺��� Ŭ�� ������
			boardtable.setOnMouseClicked( e -> {
				
			board=boardtable.getSelectionModel().getSelectedItem();
			//2.��ȸ�� ����
				
			//3.��������ȯ
			System.out.println(board.getBtitle());
			Home.home.loadpage("/view/board/boardview.fxml");
			
			}
			);	//Ŭ���� board��ü ȣ��
		
			
			
			
			
	}


}
