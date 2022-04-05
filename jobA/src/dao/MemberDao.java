package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import dto.Member;

public class MemberDao {	//DB ���ٰ�ü
	
	private Connection con; 		//DB������ ���Ǵ� Ŭ����: DB����Ŭ����
	private PreparedStatement ps;	//����� DB �� SQL �����Ҷ� ���Ǵ� �������̽�
	private ResultSet rs;			//������� �����ϴ� �������̽�
	
	//DB���� ��ü
	public static MemberDao memberDao = new MemberDao();	// DB���� �Ҷ����� ��ü ���� �� ���ʿ��� �ڵ� �� �� �ֱ� ������
	
	//constructor
	
	public MemberDao () {
		//DB���� 
		try {
			//1.DB����̹� ��������
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2.DB�ּ� ����
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
			
		} catch (Exception e) {
			System.out.println("[DB���� ���� ]"+e);
		}				
	}
	

	//method 
	
	//�Ƶ� �ߺ� üũ �޼ҵ�(�μ�: ���̵� �μ��� �޾� DB�� �����ϴ��� üũ)
	public boolean idcheck(String id) {
		
		try {
			
			//1.SQL �ۼ�
			//�˻� : select * from ���̺�� where ����(�ʵ��=��) 	*�� ����ʵ�  
			String sql ="select *from member where mid=?";
			
			//2.SQL ����
			ps =con.prepareStatement(sql);
			ps.setString(1, id);
			
			//3.SQL ����
			rs=ps.executeQuery(); // select ���� -> �˻��� ������� ���� -> resultset 0
				//resultset ó�� �⺻ ���� null�̿���.  : null----next()---->��� ���ڵ�
			
			//4.SQL ���
			if(rs.next()) {//���� ���� ������� �����Ѵٸ� => �ش� ���̵� ���� => �ߺ�o
				return true;	//�ش���̵�� �ߺ��� ���� (�˻��� ã�°��̱� ������ ã������Ʈ��! �� true �� �ߺ�)
			}
				
		} catch (Exception e) {
			System.out.println("���̵� �ߺ�üũ sql ����"+e);
		}
		return false;//�ߺ� x
	}
	
	//1. ȸ������ �޼ҵ�	(�μ�:DB�� ���� ���̵�,��й�ȣ,�̸���,�ּ�)
	public boolean signup(Member member) {
		try {
			//1. SQL �ۼ�		[ȸ����ȣ(�ڵ���ȣ =auto) , ����Ʈ (���Խ�0)  ������ ��� �ʵ� ����]
			String sql = "insert into member(mid , mpwd, memail , madd, mpoint ,mcince )values(?,?,?,?,?,?)";
			//2. SQL ����
			ps= con.prepareStatement(sql);			// prepareStatement �������̽� �� ����� DB�� SQL �ֱ�
			ps.setString(1, member.getMid());		//1�� ? �� ���̵� �־��ֱ�
			ps.setString(2, member.getMpwd());		//2�� ? �� ��й�ȣ �־��ֱ�
			ps.setString(3, member.getMemail());	//3�� ? �� �̸��� �־��ֱ�
			ps.setString(4, member.getMadd());		//4�� ? �� �ּ� �־��ֱ�
			ps.setInt	(5, member.getMpoint());	//5�� ? �� ����Ʈ �־��ֱ�
			ps.setString(6, member.getMcince());	//6�� ? �� ������ �־��ֱ�
			
			//3. SQL ����		//insert ���� -> ������ ������� ���⶧����  -> resultset �� �ʿ䰡 ����
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("[SQL���� ����]"+e);
		}
		return false;
	}
	
	//2. �α��� �޼ҵ�(�μ�:�α��� �� �ʿ��� ���̵�,��й�ȣ)
	public boolean login(String id, String pwd) {
		
		try {
			//1.SQL �ۼ�
			//������ and : ����1 and ����2 	&&
			//������ or  : ����1 or ����2	||
			String sql = "select *from member where mid=? and mpwd=?";
			
			//2.SQL ����
			ps=con.prepareStatement(sql);
			ps.setString(1, id);	//ù��° ? id���� �ֱ�
			ps.setString(2, pwd);	//�ι�° ? pwd���� �ֱ�
			//3.SQL ����
			rs=ps.executeQuery();
			//4.SQL ���
			if(rs.next()) {			//select �� ������� ������
				return true;		//���̵�� ��й�ȣ�� �����ϸ� -> �α��� ����
			}
		} catch (Exception e) {
			System.out.println("login sql ����"+e);
		}
		return false;				//�α��� ����
	}
	
	//3. ���̵�ã�� �޼ҵ�(�μ�:���̵�ã�� �� �ʿ��� �̸���)
	public String findid(String email) {
		
		try {
			//1.SQL �ۼ�
			String sql = "select * from member where memail =?";
			//2.SQL ����
			ps= con.prepareStatement(sql);
			ps.setString(1, email);
			//3.SQL ����
			rs=ps.executeQuery();
			//4.SQL ���	
			if(rs.next()) {		//���� ����� ���� ���ڵ�
				return rs.getString(2);	//rs.getring (������ �ʵ������ȣ)
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	//4. ��й�ȣ ã�� �޼ҵ�(�μ�:��й�ȣ ã��� �ʿ��� ���̵�,�̸���)
	public String findpwd(String id , String email) {
		
		try {
			String sql = "select* from member where mid=? and memail=? ";
			ps= con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, email);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getString(3);
			}
		} catch (Exception e) {
		
		}
		return null;
	}

	//5.  ���̵� �μ��� ���̵�� ȸ������ ȣ��
	public Member getMember(String id) {
		try {
			//1.SQL �ۼ�
			String sql = "select * from member where mid=?";
		//2.SQL ����
			ps.getConnection().prepareStatement(sql);
			ps.setString(1, id);
		//3.SQL ����
			rs=ps.executeQuery();
		//4.SQL ���
			if(rs.next()) {
				//1. ��ü ����
				Member meber = new Member(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7));
				
				//rs.next () 				: ��� �� ���� ���ڵ� (��,����)
				//rs.getint (�ʵ������ȣ)		: �ش� �ʵ��� �ڷ����� ���������� ��������
				//rs.getString(�ʵ������ȣ)	: �ش��ʵ��� �ڷ����� ���ڿ��� ��������
				
				return meber;
			}
		} catch (Exception e) {
		}
		return null;	
	}
	
	//6. ȸ��Ż�� [ȸ����ȣ�� �μ��޾� �ش� ȸ����ȣ�� ���ڵ带 ���� ]
	public boolean delete(int mnum) {
		try {
			//1.SQL �ۼ�
			String sql = " delete from member where mnum = ? ";
			//2.SQL	����
			ps=con.prepareStatement(sql);
			ps.setInt(1, mnum);
			//3.SQL	����
			ps.executeUpdate();	//insert , update ,delete ����//
			//4.SQL	���
			return true;
		} catch (Exception e) {
			System.out.println("[sql����]"+e);
		}
		
		
		return false;
	}
	
	//7. ȸ������	
	public boolean update(int mnum , String email ,String add) {
		
		try {
			//���� : update ���̺�� set �ʵ�� = ������1 , �ʵ��2 = ������ 2 ......
			String sql = "update member set memail =? , madd=? where mnum =?";
											// �� �ʵ�� , ���� ! 
			ps=con.prepareStatement(sql);
			
			ps.setString(1, email);
			ps.setString(2, add);
			ps.setInt(3, mnum);
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("sql ����!!!   "+e);
		}
		
		return false;
	}
	
	//8.����Ʈ �޼ҵ� 
	public boolean getpoint(int mnum , String cince ,int point) {
		Date date = new Date();
		
		try {
			String sql = "update member set mpoint =?";
			
			ps=con.prepareStatement(sql);
			
		
			ps.setInt(1, mnum);
			ps.setInt(2, point);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
}
