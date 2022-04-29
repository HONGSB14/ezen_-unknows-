package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.cj.xdevapi.Result;

import dto.Board;
import dto.Member;

public class Dao {
	
		private Connection conn; 		//DB연동시 사용되는 클래스: DB연동클래스
		private PreparedStatement ps;	//연결된 DB 내 SQL 조작할때 사용되는 인터페이스
		private ResultSet rs;			//결과물을 조작하는 인터페이스
	
	public Dao() {
		//jdbc
			//1. 프로젝트 내 mysql 빌드패스 추가
			//2.프로젝트  내 web - inf - lib -> mysqljdbc 패스 추가

		//1.드라이브 가져오기
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsptest?serverTimezone=UTC","root","1234");
			
		} catch (Exception e) {
			System.out.println("DB연동 실패 경로:dao.MemberDao  "+e);
		}

	}
	//회원가입 메소드
	public boolean signup(Member member) {
		//1.SQL 작성
		String sql = "INSERT INTO jsptest.member(mid,mpwd,mname) VALUES(?,?,?)";
				try {
					//2.SQL 조작
					ps=conn.prepareStatement(sql);
					ps.setString(1, member.getId());
					ps.setString(2, member.getPassword());
					ps.setString(3, member.getName());
					//3.SQL 실행
					ps.executeUpdate();
					//4.SQL 결과
					return true;
				} catch (Exception e) {
					System.out.println("signup 오류!! 경로:dao.MemberDao : "+e);
				}
		return false;
	}
	
	//로그인 메소드
	public boolean login(String id ,String pwd) {
		
		String sql= "SELECT * FROM jsptest.member WHERE mid=? and mpwd=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd); 
			rs=ps.executeQuery();
		
			if(rs.next()) {
				
			}
			return true; // 왜 펄스값이 뜨는거지?
		} catch (Exception e) {
			System.out.println("login 오류!! 경로:dao.MemberDao : "+e);
		}
		return false;
		
	}
	//삭제 메소드
	public boolean delete(int mnum) {
		
		String sql= "DELETE FROM jsptest.member WHERE mnum=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	//보드작성
	public boolean write(Board board) {
		
		String sql = "INSERT INTO board(btitle,bcontent,bwriter,bdate) values(?,?,?,?)";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			ps.setString(4, board.getDate());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("write 연동 실패 !");
		}
		
		return false;
	}
	//보드 호출
	public ArrayList<Board> list () {
		
		ArrayList<Board> boardlist = new ArrayList<Board>();
		String sql="SELECT *FROM board ORDER BY bno DESC";
		
		try {
			ps= conn.prepareStatement(sql);
			rs=ps.executeQuery(sql);
			while(rs.next()) {
				Board board = new Board(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				boardlist.add(board);
			}
			return boardlist;
		} catch (Exception e) {
			// TODO: handle exception
		}
	return null;
	}
	
	//개별 글 조회
	
	public Board get(int bno) {
		try {
			String sql = "SELECT * FROM board where bno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bno);
			rs=ps.executeQuery();
			if(rs.next()) {
				Board board = new Board(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				
				return board;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	//게시물 삭제
	public boolean ddelete (int bnum) {
		
		String sql = "DELETE FROM board where bno=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	
	
}
