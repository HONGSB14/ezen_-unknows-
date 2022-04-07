package controller.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dto.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Server implements Initializable {
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		txtserver.setDisable(true);  //�ش� fxid������!
	}
	
	
    @FXML
    private Button btnserver;

    @FXML
    private TextArea txtserver;

    public static Vector<Client> clientlist = new Vector<>();	//Vector �� ���� ���� :����ȭ ���� (
    	
    
    
    //���� ���� �޼ҵ�
    @FXML
    void server(ActionEvent event) {
    	if(btnserver.getText().equals("��������")) {
    		serverStart();
    		txtserver.appendText("���������մϴ�.\n");
    		btnserver.setText("��������");
    	}else {	//��ư�� �ؽ�Ʈ�� " �������� "�̸� 
    		txtserver.appendText("���������մϴ�.\n");
    		serverStop();
    		btnserver.setText("��������");
    	}
    }
	
    
    //* ������ ����� Ŭ���̾�Ʈ�� �����ϴ� ����Ʈ
    //* ��Ƽ�����带 �������ִ� ������ 
    public static ExecutorService threadPool;
    	//ExecutorService :������ Ǯ ���� �������̽� [ ����Ŭ����(implement) vs ��������(�͸�ü) ] 
    //1.���� ���� ����
    ServerSocket serverSocket;
   
    
    //2.�������� �޼ҵ�
    public void serverStart() {
    	
    		try {
    			
    			//1.�������� �޸� �Ҵ�
    			serverSocket =new ServerSocket();
    			
    			//2.�������� ���ε�[ IP �� PORT ����]
    			serverSocket.bind(new InetSocketAddress ("127.0.0.1" , 1234));

			} catch (Exception e) {
			
				//3.Ŭ���̾�Ʈ�� ��û ��� [��Ƽ ������]
				Runnable runnable = new Runnable() {	
					@Override
					public void run() {
						while(true) {
							try {
								Socket socket = serverSocket.accept(); //��û �����Ŀ� ������ ������ ����
								clientlist.add(new Client(socket));	   //����� Ŭ���̾�Ʈ(����ȼ���) ���� �Ŀ� ����Ʈ ����
							} catch (Exception e2) {
								
							}
						}
					}
				};//��Ƽ������ ���� ��
				threadPool= Executors.newCachedThreadPool();	//������ Ǯ�� �޸� �ʱ�ȭ[�Ҵ�]
				threadPool.submit(runnable);					//������ ������ ��Ƽ�����带 ������ Ǯ�� �־��ֱ�
			}
    
    		
    }
    
    //3.�������� �޼ҵ�
    public void serverStop() {
    	try {
    		//1.���ӵ� ��� Ŭ���̾�Ʈ���� ���� �ݱ�
        	for(Client client : clientlist) {
        		client.socket.close();
        		
        	}
        	//2.���� ���� �ݱ�
        	serverSocket.close();
        	//3.������ Ǯ �ݱ�
        	threadPool.shutdown();
		}catch (Exception e) {	
		
		}
    }
}
