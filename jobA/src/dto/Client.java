package dto;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import controller.server.Server;

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
						for(Client client : Server.clientlist) {
							client.send(msg);	//���� �޽����� ������ ���ӵ� [clientlist ] ��� Ŭ���̾�Ʈ
						}
					}
				} catch (Exception e) {
					
				}
			}
		};//��Ƽ������ ���� ��
		
		//�ش� �����带 ��Ƽ�����带 ������ Ǯ�� �־���
		Server.threadPool.submit(runnable);
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
						
					}
					
				}
			};
			Server.threadPool.submit(runnable);
	}
}
