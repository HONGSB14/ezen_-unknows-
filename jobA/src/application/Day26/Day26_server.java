package application.Day26;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Day26_server {
	
	/*
	  ��ſ� ���� �����
		1. ���� ���� �����
			����: Ŭ���̾�Ʈ���� ������ ���񽺸� �������ִ� ��ǻ��
			����: PC ���� ��� ������
		2. �������� ���ε�
			������ IP �ּҿ� PORT ��ȣ ����
				IP		: ��� �ν� ��ȣ [PC�� �ĺ��ϴ� ��ȣ]	(IP �� 6���� ������ PORT ��밡��)
				PORT	: PC�� ���μ���(���α׷�) �ĺ��ϴ� ��ȣ 	(PORT : 3307 ��ȣ�� MYSQL ���α׷����� �����ϴ� ��ȣ )
					
					
	*/
	
	public static void main(String[] args) {
		
		
		try {
			//1.���� ���� �����
			ServerSocket serverSocket = new ServerSocket();
			
			//2. �������� ���ε�							//IP�ּ�, PORT ����
			serverSocket.bind(new InetSocketAddress("127.168.102.50",5000));
			
			//3.Ŭ���̾�Ʈ�� ��û ���
			
			while(true) {
				System.out.println("������ ���� ������Դϴ�.");
				
				//4. ��û�� ������� ����
					Socket socket = serverSocket.accept(); // ��������.accept() : ��û����
				
				//5.������ ������ ���� Ȯ��
					InetSocketAddress soketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
					System.out.println("Ŭ���̾�Ʈ ���� �Ϸ�!! Ŭ���̾�Ʈ ���� :"+ soketAddress.getHostName());
				
				//6.Ŭ���̾�Ʈ���� ������ ���� [�ޱ�] �ϱ�
					InputStream inputStream =socket.getInputStream();
					byte[] bytes = new byte[1000];
					inputStream.read();
					System.out.println("Ŭ���̾�Ʈ�� ���� �޽���: "+new String(bytes) );
				
				//7.Ŭ���̾�Ʈ���� ������ �۽� [������] �ϱ�
				
					//7-1 ������ �Է¹ޱ�
					Scanner  scanner =new Scanner(System.in);
					System.out.println("Ŭ���̾�Ʈ���� ���� �޽���: ");
					String msg = scanner.next();
					//7-2 ������ ��� ��Ʈ��
					OutputStream outputStream = socket.getOutputStream();
					//7-3 ��������
					outputStream.write(msg.getBytes());
				
			}
		} catch (Exception e) {
			
		}
		
		
	}
}
