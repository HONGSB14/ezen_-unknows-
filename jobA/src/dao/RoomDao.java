package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Product;
import dto.Room;
import dto.RoomLive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomDao {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public static RoomDao roomDao = new RoomDao();
	
	public RoomDao () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			System.out.println("[DB���� ���� ] dao.RoomDao : "+e);
		}
	}
	
    //1.�� ���� �޼ҵ�
	public boolean roomadd(Room room) {
		try {
			String sql= "insert into room(roname,roip) values(?,?)";	//���̺� ���ڵ带 �߰��ϴ� ���� : insert into ���̺�� (�ʵ��1,�ʵ��2) values (��1,��2);
			ps= conn.prepareStatement(sql);
			ps.setString(1, room.getRoname());
			ps.setString(2, room.getRoip());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("�� ���� ���� !: "+e);
		}
		
		return false;
	}

	//2.�� ��ȣ ȣ�� �޼ҵ�[���ȣ= ��Ʈ��ȣ ���]
	public int getroomnum() { //���� �ֱٿ� ��ϵ� �� ��ȣ ��������
		try {
			String sql = "select max(ronum) from room"; 	//Max(���̺��) �� ���̺��� �ִ밪
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("���ȣ ȣ�� ����: "+e);
		}
		return 0;
	}
	
	//3.��� �� ȣ�� �޼ҵ� [ 
	public ObservableList<Room> roomlist (){
		ObservableList<Room> roomList = FXCollections.observableArrayList();
		
		try {
			String sql = "select * from room order by ronum desc";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				Room room = new Room(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						0);
				roomList.add(room);
			}
			return roomList;
		} catch (Exception e) {
			System.out.println("��� �� ȣ�� ���� !! dao.RoomDao: "+e);
		}
		return null;
	}
	
	//4.ä�ù� ���� ��� �߰�
	public boolean addroomlive (RoomLive roomlive ) {
		
		String sql = "insert into roomlive(ronum,mid) values(?,?)";
		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1, roomlive.getRonum());
			ps.setString(2, roomlive.getMid());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("ä�ù� ���� ��� �߰� ����!! dao.RoomDao : "+e);
		}
		return false;
	}
	
	//5.ä�ù� ���ӵ� ��� ��� ȣ��
	public ArrayList<RoomLive> getroomlivelist(int ronum) {
			ArrayList<RoomLive> roomlivelist = new ArrayList<>();
			
			try {
				String  sql = "select * from roomlive where ronum=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, ronum);
				rs=ps.executeQuery();
				while(rs.next()) {
					RoomLive roomLive = new RoomLive(rs.getInt(1), rs.getInt(2), rs.getString(3));
					roomlivelist.add(roomLive);
							
				}
				return roomlivelist;
			} catch (Exception e) {
				System.out.println("ä�ù� ���ӵ� ��� ��� ȣ�� ����!!  dao.RoomDao : "+e);
			}
			return null;
	}
	
	//6.ä�ù� ���� ��� ����
		public boolean roomlivedelete(String mid) {
			String sql= "delete from roomlive where mid=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, mid);
				ps.executeUpdate();
				return true;
			} catch (Exception e) {
				System.out.println("ä�ù� ���� ��� ���� ����! dao.RoomDao : "+e);
			}
			return false;
		}
	
	//7. ä�ù� ���� [ ����: �ش� ä�ù濡 ���Ӹ���� 0 �̸�]
		public boolean roomdelte(int ronum) {
			
			String sql = "select * from roomlive where ronum=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, ronum);
				rs=ps.executeQuery();
				if(rs.next()) {	//�濡 ����� �����ϸ� 
					return false;
				}else {	//����� 0���̸�
					String sql2= "delete from room where ronum=?";
					ps=conn.prepareStatement(sql2);
					ps.setInt(1, ronum);
					ps.executeUpdate();
					return true;
				}
			} catch (Exception e) {
				System.out.println("ä�ù� ���� ����!! dao.RoomDao : "+e);
			}
			return false;
		}
}

	
