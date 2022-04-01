package application.Day22;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;






public class Day22_2 {
	//1.field
	private Connection connection;
	
	//2.constructor
	
	public Day22_2() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection =DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
			//jdbc:mysql://IP�ּ�(����[����pc] �̸� localhost):port��ȣ/DB�̸�?DB�ð�����,������,"DB��й�ȣ
			//JDBC : JAVA DATABASE CONNECTION
			
			System.out.println("[db���� ����]");
		} catch (Exception e) {
			System.out.println("[db���� ����]");
		}
		
	}
	
	//3.method
	
	//������ ����
	public boolean write(String �ۼ���, String ����) {
		try {
			//1.SQL �ۼ� [DB�� ����]
			//DB�� ���̺� ������ ���� : insert into ���̺��(�ʵ��,�ʵ��)  values (�ʵ�� 1�� �� , �ʵ�� 2�� ��)
			String sql = "insert into test(writer,content) values(?,?)";
			
			//2.SQL ���� [����� DB �� SQL ����]
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, �ۼ���); // SQL �� �ۼ��� ù��° ? �� ���� �ֱ�	[1 : ù��°]
			ps.setString(2, ����);  // SQL �� �ۼ��� �ι�° ? �� ���� �ֱ�	[2 : �ι�°]
			
			//3.SQL ����
			ps.executeUpdate();
			
			//������ true
			return true;
		
		} catch (Exception e) {
			System.out.println("sql�������"+e);
			//�������� 
		}
		
		
		
		
		
		//���н� false
		
		return false;
	}
	
	//������ ȣ��	[��ȯŸ���� arraylist ����� ���� : ������ [���ڵ�]�� �������� ���ؼ�
	public ArrayList<������> get(){
		
		//* DB���� ������ �����͸� ������  ����Ʈ ����
		ArrayList<������> �����͸�� = new ArrayList<>();
		try {
			//1.SQL �ۼ� [������ ȣ�� ]
			//select *(����ʵ�) form ���̺��; ��� ������ �������� // ���ڵ尡 ������ ����
			String sql = "select * from test";
		
			//2.SQL ���� [DB�� ����� ��ü�� �����������̽� ����
			PreparedStatement ps = connection.prepareStatement(sql);
		
			//3.SQL ����
			ResultSet rs=ps.executeQuery(); //�����[���پ�=���ڵ�] ��������
			
			//����� �ϳ��� �ƴϰ� �������̹Ƿ� �ݺ��� ����ؼ� 
			//���پ� ��üȭ -> ������[���ڵ�]����Ʈ ����
			
			while(rs.next()) { //���� �� [���ڵ� ] �� ������				
				������ temp = new ������(rs.getInt(1),rs.getString(2),rs.getString(3));  // �ش���[���ڵ�]�� ù��° �ʵ� [����] �� ��������
				�����͸��.add(temp);
			}
			//�ݺ��� ����Ǹ� ����Ʈ ��ȯ
		
		//* ������ ������ ��� ��ȯ
		return �����͸��;
			
		} catch (Exception e) {
			System.out.println("SQL������� "+e);
		}
		
		//���� �� 
		return null;
	}
}
