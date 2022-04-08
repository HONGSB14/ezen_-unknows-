package controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.login.Login;
import controller.server.Server;
import dao.RoomDao;
import dto.Room;
import dto.RoomLive;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Chatting implements Initializable{

	public Server server;	//���� ��Ĺ �ļ�
		
    public static Room selectroom; //���̺� �信�� ���õ� �� ��ü

    Socket socket;			//1.Ŭ���̾�Ʈ ���� ����
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//ä�� fxml �������� �ʱⰪ �޼ҵ�
			//ä�ù� ���� �� fxid������ ��Ű��
		txtmsg.setText("ä�ù� ���� �� ��밡��");
		txtmsg.setDisable(true);		//ä�� �Է�â ������ 
		txtcontent.setDisable(true);	//ä��â ��� ������
		btnsend.setDisable(true);		//���۹�ư ������
		btnconnect.setDisable(true);  	//���� ��ư ������
		txtmidlist.setDisable(true);	//������ȸ����� ������
		show();
		
	}
	
    @FXML
    private Button btnconnect;

    @FXML
    private TextArea txtcontent;

    @FXML
    private TextField txtmsg;

    @FXML
    private Button btnsend;

    @FXML
    private TableView<Room> roomtable;

    @FXML
    private TextField txtroomname;

    @FXML
    private Button btnadd;

    @FXML
    private TextArea txtmidlist;

    @FXML
    private Label lblselect;

    @FXML
    private Label lblmlist;
	    
    
	    

	    
	    
	    //2.Ŭ���̾�Ʈ ���� �޼ҵ�
	    public void clientstart (String ip , int port) {
	    	//��Ƽ������
	    	Thread thread = new Thread() {	
	    		@Override
	    		public void run () {
	    			try {
	    				socket  = new Socket(ip , port);//������ ip�� port ��ȣ �־��ֱ� [��������]
	    				send(Login.member.getMid()+"���� �����Ͽ����ϴ�.\n");
	    				receive();								//���Ӱ� ���ÿ� �ޱ� �޼ҵ�� ���ѷ���
					} catch (Exception e) {
						
						System.out.println("Ŭ���̾�Ʈ ���� ����!! controller.Chatting : "+e);
					}	
	    		};
	    	};
	    	thread.start();	//��Ƽ������ ����
	    }
	    
	    //3.Ŭ���̾�Ʈ ���� �޼ҵ�
	    public void clientstop () {
	    	try {
				socket.close();
			} catch (Exception e) {
				
				System.out.println("Ŭ���̾�Ʈ ���� ���� !! controller.Chatting : "+e);
			}
	    }
	   
	    //4.�������� ������ �޼ҵ�
	    public void send(String msg) {
	    	Thread thread = new Thread() {
	    		@Override
	    public void run () {
	    			try {
						OutputStream outputStream = socket.getOutputStream(); 	//1.��� ��Ʈ��
						outputStream.write(msg.getBytes());						//2.��������
						outputStream.flush(); 									//3.��Ʈ�� �ʱ�ȭ [ ��Ʈ�� �� ����Ʈ �����]
					} catch (Exception e) {
						clientstop();
						System.out.println("�޽��������� ���� !! controller.Chatting : "+e);
					}
	    		}
	    	};
	    	midshow();
	    thread.start();
	    }
	    
	    //5.�������� �޽��� �ޱ� �޼ҵ�
	    public void receive() {
	    	try {
	    		while(true) {
	    			InputStream inputStream = socket.getInputStream();  //�Է� ��Ʈ��
	    			byte [] bytes= new byte[1000];						//����Ʈ�迭 ����
	    			inputStream.read(bytes);							//�о����
	    			String msg = new String(bytes);						//����Ʈ��->���ڿ�
	    			txtcontent.appendText(msg); 						//���� ���ڿ��� �޽���â�� ����
	    		}
			} catch (Exception e) {
				clientstop();
				System.out.println("�޽��� �ޱ� ���� !! controller.Chatting : "+e);
			}
	    }
	   
	    public void midshow() {	//msg �޼ҵ�: �Է�â���� ���� ���� �� //send�޼ҵ� : ���� ��ư ������ ��
	    	//1.���̺� �信�� ���õ� �� ��ȣ�� ���ӵ� ȸ����� ��������
	    	ArrayList<RoomLive> roomlivelist 
				= RoomDao.roomDao.getroomlivelist( selectroom.getRonum() );
			txtmidlist.setText("");
			int i = 1; 
			for( RoomLive temp : roomlivelist ) {
				txtmidlist.appendText( i +"�� "+ temp.getMid() +"\n");
				i++;
			}
	    }
	    
	    @FXML
	    void connect(ActionEvent event) {
    		
	    	if(btnconnect.getText().equals("ä�ù� ����")) {// ���࿡ ��ư�� �ؽ�Ʈ�� "ä�ù� ����" �̸� 
	    		// ���̺�信�� ���õ� ���� ip �� port �� Ŭ���̾�Ʈ���� �޼ҵ忡 �־��ֱ�
    			clientstart(selectroom.getRoip(), selectroom.getRonum());
    				//���� �� ���� ��� �߰�
    				RoomLive roomLive =new RoomLive(0, selectroom.getRonum(), Login.member.getMid());
    				//DBó��
    				RoomDao.roomDao.addroomlive(roomLive);	
    			
    			
    			
    			txtcontent.appendText("---[ä�ù� ����]---\n");
    			btnconnect.setText("ä�ù� ������");
    			
    			txtmsg.setText(" ");			//ä���Է�â ��ĭ���� ����
    			txtmsg.setDisable(false);		//ä�� �Է�â ���
    			txtcontent.setDisable(false);	//ä��â ��� ���
    			btnsend.setDisable(false);		//���۹�ư ���
    			txtmsg.requestFocus();			//ä���Է�â���� ��Ŀ�� [Ŀ�� ] �̵�
    			
    			txtroomname.setDisable(true);   //ä�ù� �̸� �Է�â ������
    			btnadd.setDisable(true);        //ä�� ���� ��� ����
    	        roomtable.setDisable(true);     //ä�ù� ��� ������
    		}else {
    			clientstop();
    			txtcontent.appendText("---[ä�ù� ����]---\n");
    			btnconnect.setText("ä�ù� ����");
    			
    			txtmsg.setText("ä�ù� ���� �� ��밡��");
    			txtmsg.setDisable(true);		//ä�� �Է�â ������ 
    			txtcontent.setDisable(true);	//ä��â ��� ������
    			btnsend.setDisable(true);		//���۹�ư ������
    			btnconnect.setDisable(true);	//ä�ó��� ������
    			
    			txtroomname.setDisable(false);  //ä�ù��̸� ���
    			btnadd.setDisable(false);       //�氳�� ��ư ���
    			roomtable.setDisable(false);    //ä�ù� ��� ���
    			//1.�� ���� ��ܿ��� ����[����] �ϱ�
    			RoomDao.roomDao.roomlivedelete(Login.member.getMid());
    			
    			
    			//2.���� �� ���� ����� 0���̸� �� ����
    				//2-2 ���࿡ ���� �����Ǹ鼭 ���� ����
    			boolean result =RoomDao.roomDao.roomdelte(selectroom.getRonum());
    			if(result) {
    				server.serverStop();
    			}
    			
    			//* ���̺�信�� ���°�� �水ü �ʱ�ȭ
    			selectroom= null;
    			//* ���õ� �� ���̺� �ʱ�ȭ
    			lblselect.setText("���� ���õ� ��: ");
    			
    			//�� ������ ���� ��Ĺ �� ���ӵ� ���� ����Ʈ���� ���� ����
    			
    		}
    	}
    
	    @FXML
	    void send(ActionEvent event) {	//���� ��ư�� ��������
	    	
	    	String msg = txtmsg.getText()+"\n";	//���� �޽���
	    	send(msg);							//�޽��� ������
	    	txtmsg.setText("");					//������ �� �޽��� �Է� â �����
	    	txtmsg.requestFocus();				//������ �� �޽��� �Է� â ��Ŀ��(Ŀ��)�̵� 
	    	
	    	midshow();
	    	
	    }
	    
	    //�Է�â���� �Է� �� ���͸� ������ ��
	    @FXML
	    void msg(ActionEvent event) {
	    	String msg=Login.member.getMid()+":"+txtmsg.getText()+"\n";
	    	send(msg);
	    	txtmsg.setText("");
	    	txtmsg.requestFocus();
	    	
	    	ArrayList<RoomLive> roomlivelist = RoomDao.roomDao.getroomlivelist(selectroom.getRonum());
	    	txtmidlist.setText("");
	    	int i=0;
	    	for(RoomLive temp : roomlivelist) {
	    		txtmidlist.appendText(i+"��"+temp.getMid()+"\n");
	    		i++;
	    	}
 	    }

	    //�� ���� ��ư �޼ҵ�
	    @FXML
	    void add(ActionEvent event) {
	    	
	    	//1.��Ʈ�ѿ� �Էµ� �� �̸� ��������
	    	String roomname = txtroomname.getText();
	    	if(roomname.length()<4) {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setHeaderText("�� ������ 4���� �̻����� ���ּ���.");
	    	return;
	    	}
	    	//2.�� ��ü
	    	Room room= new Room(0, roomname, "127.0.0.1", 0);
	    	//3.DBó��
	    	RoomDao.roomDao.roomadd(room);
	    	//4.�ش� �� ���� ����
	    	server= new Server();
	    	//���� ���� 
	    	server.serverStart(room.getRoip(), RoomDao.roomDao.getroomnum());
	    	txtroomname.setText("");					//���� �� �� �̸� �Է�â ���� ó��
	    	
	    	show(); //���̺� ���ΰ�ħ
	    	
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setHeaderText("�� ���� �Ϸ�!\n"+"���ȣ: "+RoomDao.roomDao.getroomnum());
	    	alert.showAndWait();
	    }
	    
	    //��� �� ��� �������� �޼ҵ�
	    public void show() {
	    	
	    	//1.DB���� ��� �� ��� ��������
	    	ObservableList<Room> roomlist= RoomDao.roomDao.roomlist();
	    	
	    	//2.���̺� ���� ù���� �ʵ带 �����ͼ� �� ��ȣ �ʵ�� ����
	    	TableColumn tc = roomtable.getColumns().get(0);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("ronum"));
	    	
	    	//3. ���̺� ���� ���� ° �ʵ带 �����ͼ� �� ��ȣ �ʵ�� ����
	    	tc= roomtable.getColumns().get(1);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("roname"));
	    	
	    	//4.���̺� �� �� �׹� ° �ʵ带 �����ͼ� �� ��ȣ �ʵ�� ����
	    	tc= roomtable.getColumns().get(2);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("mcount"));
	    	
	    	//5.���̺� ���� ����Ʈ�� �־��ֱ�
	    	roomtable.setItems(roomlist);
	    	
	    	//6. �ش� ���̺� �並 Ŭ�� ������ 
	    	roomtable.setOnMouseClicked( e-> {
	    			//7.Ŭ���� ��ü ��������
	    			selectroom =roomtable.getSelectionModel().getSelectedItem();
	    			//8.���̺� ǥ��
	    			lblselect.setText("���� ���õ� ��: "+selectroom.getRoname());
	    			//9.���� ��ư ��� Ȱ��ȭ
	    			btnconnect.setDisable(false);
	    	});
	    }
}
