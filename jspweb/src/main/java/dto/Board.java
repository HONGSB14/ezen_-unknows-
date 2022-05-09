package dto;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dao.MemberDao;

public class Board {
	
	private int bno;			//게시판 번호 (PK)
	private String btitle;		//게시판 제목
	private String bcontent;	//게시판 내용
	private int mno;			//회원 번호 (FK)
	private int bview;			//게시판 조회수
	private String bdate;		//게시판 작성날짜
	private String bfile;		//게시판 첨부 파일
	private String mid;			//회원아이디   (화면 표시용)
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(int bno, String btitle, String bcontent, int mno, int bview, String bdate, String bfile , String mid) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.mno = mno;
		this.bview = bview;
		//작성일이 오늘이면 시간만 표시 // 아니면 날짜만 표시
		if( bdate != null ) {
		DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");	//날짜 형식 변환설정
		String today = dateformat.format(LocalDate.now());	//오늘 날짜를 문자열 반환
		String boardday = bdate.split(" ")[0];//날짜만
		String boardtime= bdate.split(" ")[1];//시간만
		
			if(today.equals(boardday)) { //만약 날짜가 같다면
				this.bdate=boardtime;	//시간만 출력
			}else{						//만약 날짜가 틀리다면
				this.bdate=boardday;	//전체 출력
			}
		}else{ 
			this.bdate=bdate; 
		} 
		
		this.bfile = bfile;
		// mno를 가지고 mid출력 (모든 곳에 아이디 출력)
		this.mid= MemberDao.getMemberDao().getid(mno);
	}

	
	//객체 정보(필드정보 ) 출력 메소드
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", mno=" + mno + ", bview="
				+ bview + ", bdate=" + bdate + ", bfile=" + bfile + ", mid=" + mid + "]";
	}
		
	
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getBview() {
		return bview;
	}

	public void setBview(int bview) {
		this.bview = bview;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getBfile() {
		return bfile;
	}

	public void setBfile(String bfile) {
		this.bfile = bfile;
	}
	
	public String getMid() {
		return mid;
	}

	public void setMid(String Mid) {
		this.mid = Mid;
	}

	
	
	
}
