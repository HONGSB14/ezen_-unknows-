package dao;

import java.util.ArrayList;

import dto.Board;

public class BoardDao extends Dao {


	
	public BoardDao() {
		super();
	}
	
	public static BoardDao boardDao = new BoardDao();
	
	public static BoardDao getBoardDao() {
		
		return boardDao;
	}
	
	
 
	//1.게시물 쓰기 메소드
	public boolean write(Board board) {
		
		String sql ="insert into board(btitle, bcontent, mno, bfile)values(?,?,?,?)";
	try {
		ps=conn.prepareStatement(sql);
		ps.setString(1, board.getBtitle());
		ps.setString(2, board.getBcontent());
		ps.setInt(3, board.getMno() );
		ps.setString(4, board.getBfile());
		ps.executeUpdate();
		return true;
	} catch (Exception e) {
		System.out.println("게시물 글쓰기 오류 "+e);
	} 
		
		return false;
	}; 
	
	//2.모든 게시물 출력 메소드
	public ArrayList<Board> getboardlist() {
		
		ArrayList<Board> boardlist = new ArrayList<Board>();
		String sql ="select * from board order by bno desc";
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				Board board = new Board(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7), null);
				boardlist.add(board);
			}
			return boardlist;
		} catch (Exception e) {
			System.out.println("게시물 출력 오류 :"+e);
		}
		
		return null;
	};
	
	//3.개별 게시물 출력 메소드
	public Board getboard() {
		
		return null;
	};
	
	//4.게시를 수정 메소드
	public boolean update(Board board) {
		
		return false;
	};
	
	//5.게시물 삭제 메소드
	public boolean delete(int bno) {
		return false;
	};
	
	//6.게시물 조회 증가 메소드
	public boolean increview(int bno) {
		return false;
	};
	
	//7.댓글 작성 메소드
	public boolean replywrite() {
		return false;
	}
	
	//8.댓글 출력 메소드
	public boolean replylist() {
		return false;
	}
	
	//9.댓글 수정 메소드
	public boolean replyupdate() {
		return false;
	};
	
	//10.댓글 삭제 메소드
	public boolean replydelete() {
		return false;
	}
}
