package application.Day26;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Day26_client {
	
	public static void main(String[] args) {
		
		
		//1. ���� �����
		Socket  socket = new Socket();
		
		
		while(true) {
			try {	//2. ���� �������� �����ϱ� [���� ������ ip ��  port ��ȣ �־��ֱ�]
				socket.connect(new InetSocketAddress("127.168.102.50", 5000));
				System.out.println("������ ���� ����!!");
				
				Scanner scanner = new Scanner(System.in);
				//3.������ ������ �۽��ϱ� [������] : ��Ʈ���� �̿��� �ܺ� ��Ʈ��ũ ���
					
					//3-1 ������ �Է¹ޱ�
					System.out.println("�������� ���� �޽��� "); 
					String msg=scanner.nextLine();
					//3-2 ������ ��� ��Ʈ�� ��������
					OutputStream outputStream =socket.getOutputStream();
					
					//3-3 ��������
					outputStream.write(msg.getBytes());
					
				//4.�������� ������ ����[�ޱ�] �ϱ�
					//4-1 ������ �Է� ��Ʈ��
					InputStream inputStream = socket.getInputStream();
					//4-2 ����Ʈ �迭 Ȯ��
					byte[] bytes = new byte[1000];
					//4-3 �Է� ��Ʈ�����κ��� ����Ʈ ��������
					inputStream.read();
					System.out.println("������ ���� �޽��� :"+new String(bytes));
			} catch (Exception e) {
				
			}
			
		}
	}
	
}
