package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Board;
import dto.Reply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao {
	
	public static BoardDao boardDao = new BoardDao();	//DB������ü
	
	private Connection conn; 		// 1.DB ���� Ŭ����
	private PreparedStatement ps; 	// 2.����� DB�� SQL ����
	private ResultSet rs; 			// 3.SQL ��� ���ڵ�

	
	
	public BoardDao() {	//�����ڿ� DB�� �����ϴ� ���� :  ��ü ������ �ٷ� �����ϱ� ����
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC ����̺� 
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			
		}
	}

	

	//1.�۾��� �޼ҵ�
	public boolean write(Board board) {
		
		try {
			String sql = "insert into board(btitle, bcontent , bwrite )values(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, board.getBtitle());
			ps.setString(2, board.getBcontent());
			ps.setString(3, board.getBwrite());
			ps.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("�۾��� sql ���� "+e);
		}
		
		return false;
	}
	
	//2.��� �� ȣ�� �޼ҵ�
	public ObservableList<Board> list() {
			try {
				//1. ����Ʈ ����
				ObservableList<Board> boardlist =FXCollections.observableArrayList();
				//2.SQL�ۼ�
					//���� : order by �ʵ�� asc 		[�ش� Ŭ���� ��������]
					//		order by �ʵ�� desc		[�ش� Ŭ���� ��������]
				String sql = "select * from board order by bnum desc";
				//3.SQL����
				ps=conn.prepareStatement(sql);
				//4.SQL����
				rs=ps.executeQuery();
				//5.SQL���
				while(rs.next()) {	//���ڵ尡 ������ ���� �ݺ���
					
					//1.��üȭ
					Board board = new Board(rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3), 
							rs.getString(4), 
							rs.getString(5), 
							rs.getInt(6));
					//2.�ش� ��ü�� ����Ʈ�� ���
						boardlist.add(board);
				}
				return boardlist;
			
				} catch (Exception e) {
				
				}
			return null;
	}
	
	//3.�� ���� �޼ҵ�
	public boolean delete(int bnum) {
		
		try {
			String sql = "delete from board where bnum =? ";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("�Խñۻ��� ���� !"+ e);
		}
		return false;
	}

	//4.�� ���� �޼ҵ�
	public boolean update (int bnum , String title , String content) {
		
		try {
			String sql = "update board set btitle=? , bcontent =? where bnum=?";
			ps= conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, bnum);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
		System.out.println("��������!!"+e);
		}
		return false;
	}
	
	//5.��� �ۼ� �޼ҵ�
	public boolean rwrite(Reply reply) {
		
		try {
			String sql = "insert into reply(rcontent,rwrite,bnum) values(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, reply.getRcontent());
			ps.setString(2, reply.getRwrite());
			ps.setInt(3, reply.getBnum());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	
	//6.�ش� �Խù��� ��� ȣ�� �޼ҵ�
	
	public ObservableList<Reply> replyList(int bnum){
		ObservableList<Reply> replylist = FXCollections.observableArrayList();
		
		try {
				String sql= "select * from reply where bnum =? order by bnum desc";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, bnum);
				rs=ps.executeQuery();
				while(rs.next()) {
					Reply reply = new Reply(rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3),
							rs.getString(4), 
							rs.getInt(5));
					replylist.add(reply);
				}
				return replylist;
		} catch (Exception e) {
		
		}
		return null;
	}
}
