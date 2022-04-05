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
		//�� ��Ʈ�ѿ� �����ϱ�
		lblwrite.setText("�ۼ���: "+board.getBwrite());
		lbldate.setText("�ۼ���: "+board.getBdate());
		lblview.setText("��ȸ��: "+board.getBview());
		txttitle.setText(board.getBtitle());
		txtcontent.setText(board.getBcontent());
		//���� �Խù� �ۼ��ڿ� ����α��� �� id�� �������� �ʴٸ� 
		if(!board.getBwrite().equals(Login.member.getMid())) {
		btndelete.setVisible(false);  	// ��ư �����
		btnupdate.setVisible(false);	// false =��ư����� / true=��ư���̱�
		}
		//����� ������ �������ϰ� ���ó�� (��Ʈ�� ����)
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
	  
	    //��� ���̺� �޼ҵ�	[������ ���̺� ȣ���ϱ� ���� �޼ҵ�ȭ]
	    public void replytableshow() {
	    	//1.���� �Խù�
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
	    	
	    	
	    	//4.���̺� �信 ����Ʈ �־��ֱ�
	    	replytable.setItems(replylist);
	    }
	    
	    //�ڷΰ��� �޼ҵ�
	    @FXML
	    void back(ActionEvent event) {
	    	Home.home.loadpage("/view/board/board.fxml");
	    }

	    //�Խù����� �޼ҵ�
	    @FXML
	    void delete(ActionEvent event) {
	    	
	    	//1.��� �޽��� �˸�
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setHeaderText("�ش� �Խù� �����ұ��ȣȣȣ.?");
	    	
	    	//2.Ȯ�ι�ư ��������
	    	Optional<ButtonType> optional= alert.showAndWait();	//showAndWait() �޼ҵ��� ��ȯŸ�� => ������ ��ư
	    		//optional Ŭ���� : null �� ��ü�� �����ϴ� Ŭ����
	    	
	    	//3.����ó��
	    	if(optional.get()==ButtonType.OK) {
	    		BoardDao.boardDao.delete(controller.board.Board.board.getBnum());
	    	
	    	//4.������ ��ȯ 
	    	Home.home.loadpage("/view/board/board.fxml");	
	    	}
	    	
	    	
	    }
	    Alert alert = new Alert(AlertType.INFORMATION);
	  
	    //�����ۼ� �޼ҵ�
	    @FXML
	    void rewrite(ActionEvent event) {
	    	String rcontent =txtrecontent.getText();
	    	
	    	String rwrite = Login.member.getMid();
	    	
	    	int bnum = controller.board.Board.board.getBnum();
	    	
	    	Reply reply = new Reply(0, rcontent, rwrite, null, bnum);
	    	boolean result=BoardDao.boardDao.rwrite(reply);
	    	if(result) {
	    		alert.setHeaderText("�ۼ��Ϸ�!");
	    		alert.showAndWait();
	    		txtrecontent.setText("");
	    		//��� �ۼ��� ���ΰ�ħ
	    		replytableshow();
	    	}
	    }
	   
	    
	    boolean anyupdate = true;	//���� ��ư ����ġ����
	    //���� �޼ҵ�
	    @FXML
	    void update(ActionEvent event) {
	    	//��������
	    	if(anyupdate) { 
	    		
		    	alert.setHeaderText("�Խñ� ���� �� �����Ϸ� ��ư �����ּ���");
		    	alert.showAndWait();
		    	
		    	txttitle.setEditable(true);
		    	txtcontent.setEditable(true);
		    	
		    	btnupdate.setText("�����Ϸ�");
		    	
		    	anyupdate=false;
		    //�����Ϸ� 
	    	}else {	
	    		BoardDao.boardDao.update(controller.board.Board.board.getBnum(),
	    				txttitle.getText(), 
	    				txtcontent.getText());
	    		
	    	
		    	alert.setHeaderText("�Խñ� ���� �Ϸ�Ǿ����ϴ�");
		    	alert.showAndWait();
		    	
		    	txttitle.setEditable(false);
		    	txtcontent.setEditable(false);
		    	
		    	btnupdate.setText("�����Ϸ�");
		    	
		    	anyupdate=true;
	    	}
	    	
	    	
	    }
	
}
