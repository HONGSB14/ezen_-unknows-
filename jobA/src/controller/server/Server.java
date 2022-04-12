package controller.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server  {	//fxml ������� �ʴ� Ŭ���� [ ���� ��Ʈ�� ���]

    public Vector<Client> clientlist = new Vector<>(); //Vector �� ���� ���� :����ȭ �����ϱ� ����.  //����ȭ:���� �����尡 �ϳ��� �޼ҵ� ������ ��� ������ �����

    //* ������ ����� Ŭ���̾�Ʈ�� �����ϴ� ����Ʈ
    //* ��Ƽ�����带 �������ִ� ������ 
    public ExecutorService threadPool;
    	//ExecutorService :������ Ǯ ���� �������̽� [ ����Ŭ����(implement) vs ��������(�͸�ü) ] 
    //1.���� ���� ����
    ServerSocket serverSocket;
    
    //2.�������� �޼ҵ�	[�μ��� IP�� PORT �� �޾Ƽ� ���� IP�� PORT �� �������� ���ε�(����)]
    public void serverStart(String ip, int port) {
    	
    		try {
    			
    			//1.�������� �޸� �Ҵ�
    			serverSocket =new ServerSocket();
    			
    			//2.�������� ���ε�[ IP �� PORT ����]
    			serverSocket.bind(new InetSocketAddress (ip , port));

			} catch (Exception e) {
				System.out.println("�������� ����!"+e);
			}
			//3.Ŭ���̾�Ʈ�� ��û ��� [��Ƽ ������]
			Runnable runnable = new Runnable() {	
				@Override
				public void run() {
					while(true) {
						try {
							Socket socket = serverSocket.accept(); //��û �����Ŀ� ������ ������ ����
							clientlist.add(new Client(socket));	   //����� Ŭ���̾�Ʈ(����ȼ���) ���� �Ŀ� ����Ʈ ����
				
						} catch (Exception e) {
							
							
							System.out.println("���� Ŭ���̾�Ʈ ���� ���� ! controller.server.Sever : "+e);
							
						}	
					}
				}
			};//��Ƽ������ ���� ��
			threadPool= Executors.newCachedThreadPool();	//������ Ǯ�� �޸� �ʱ�ȭ[�Ҵ�]
			threadPool.submit(runnable);					//������ ������ ��Ƽ�����带 ������ Ǯ�� �־��ֱ�
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
			
			System.out.println("���� ���� ����!  controller.server.Sever: "+e);
		}
    }

    
    
    // Ŭ�����ȿ� Ŭ���� ���� ��ø Ŭ����  [client]  
    public class Client {
    	//������ ������ Ŭ���̾�Ʈ�� Ŭ����
    	
    	//1.�ʵ�
    	public Socket socket;
    	
    	//2.������
    	public Client(Socket socket ) {
    		this.socket= socket;
    		recive();  //������ ����� Ŭ���̾�Ʈ ��ü�� �����ɶ� �ޱ� ������ 
    		
    	}
    	
    	//3.�����κ��� �޴� �޽���
    	public void recive() { //[��������: ������ Ŭ���̾�Ʈ�� ����Ǿ�����]	
    		//��Ƽ������ �ϴ� ���� �޽����� �ޱ⵵ �ϰ� �ֱ⵵ �ؾߵǱ� ����.
    		//��Ƽ������ [Thread Ŭ���� or Runnable �������̽�]
    		//Runnable �Ӹ��ƴ϶�  �������̽���ü�� �߻�޼ҵ尡 �����ϱ� ������ ������ �ʼ� !! 
    		Runnable runnable = new Runnable() {
    			
    			@Override
    			public void run() {	//�߻� �޼ҵ� ����
    				//��������� �޽��� �޴� ����
    				try {
    					while(true) {
    						InputStream inputStream =socket.getInputStream();   //�Է½�Ʈ��
    						byte [] bytes = new byte[1000];						//����Ʈ �迭 ����
    						inputStream.read(bytes);							//�Է½�Ʈ���� ����Ʈ�� �о����
    						String msg = new String(bytes);						//����Ʈ��->���ڿ� ��ȯ
    						
    						//������ ���� �޽����� ���� ������ ���ӵ� ��� Ŭ���̾�Ʈ���� ���� �޽��� ������
    						for(Client client : clientlist) {
    							client.send(msg);	//���� �޽����� ������ ���ӵ� [clientlist ] ��� Ŭ���̾�Ʈ
    						}
    					}
    				} catch (Exception e) {
    				
    					System.out.println("�����κ��� �޴� �޽��� ����!! controller.server.Sever : "+e);
    				}
    			}
    		};//��Ƽ������ ���� ��
    		threadPool.submit(runnable);	//�ش� �����带 ��Ƽ�����带 ������ Ǯ�� �־���
    	}
    	
    	//4.�����κ��� ������ �޽���	[��������: ������ �޽����� �޾��� ��]
    	public void send(String msg) {	//��Ƽ������
    			Runnable runnable = new Runnable() {
    				
    				@Override
    				public void run() {
    					try {
    						OutputStream outputStream = socket.getOutputStream();
    						outputStream.write(msg.getBytes());
    					} catch (Exception e) {
    						
    						System.out.println("�����κ��� ������ �޽��� ���� !! controller.server.Sever : "+e);
    					}
    					
    				}
    			};
    			threadPool.submit(runnable);
    	}
    }
}
