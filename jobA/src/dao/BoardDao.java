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
	
	public static BoardDao boardDao = new BoardDao();	//DB연동객체
	
	private Connection conn; 		// 1.DB 연동 클래스
	private PreparedStatement ps; 	// 2.연결된 DB내 SQL 조작
	private ResultSet rs; 			// 3.SQL 결과 레코드

	
	
	public BoardDao() {	//생성자와 DB를 연동하는 이유 :  객체 생성시 바로 연동하기 위해
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC 드라이브 
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			
		}
	}

	

	//1.글쓰기 메소드
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
			System.out.println("글쓰기 sql 오류 "+e);
		}
		
		return false;
	}
	
	//2.모든 글 호출 메소드
	public ObservableList<Board> list() {
			try {
				//1. 리스트 선언
				ObservableList<Board> boardlist =FXCollections.observableArrayList();
				//2.SQL작성
					//정렬 : order by 필드명 asc 		[해당 클래스 오름차순]
					//		order by 필드명 desc		[해당 클래스 내림차순]
				String sql = "select * from board order by bnum desc";
				//3.SQL조작
				ps=conn.prepareStatement(sql);
				//4.SQL실행
				rs=ps.executeQuery();
				//5.SQL결과
				while(rs.next()) {	//레코드가 없을때 까지 반복문
					
					//1.객체화
					Board board = new Board(rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3), 
							rs.getString(4), 
							rs.getString(5), 
							rs.getInt(6));
					//2.해당 객체를 리스트에 담기
						boardlist.add(board);
				}
				return boardlist;
			
				} catch (Exception e) {
				
				}
			return null;
	}
	
	//3.글 삭제 메소드
	public boolean delete(int bnum) {
		
		try {
			String sql = "delete from board where bnum =? ";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("게시글삭제 오류 !"+ e);
		}
		return false;
	}

	//4.글 수정 메소드
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
		System.out.println("수정실패!!"+e);
		}
		return false;
	}
	
	//5.댓글 작성 메소드
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
	
	//6.해당 게시물의 댓글 호출 메소드
	
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
