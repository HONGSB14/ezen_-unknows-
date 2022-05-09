package dto;

import dao.MemberDao;

public class Member {

	private int mno;			//회원번호		(PK)
	private String mid;			//회원 아이디
	private String mpassword;	//회원 비밀번호
	private String mname;		//회원 이름
	private String mphone;		//회원 전화번호
	private String memail;		//회원 이메일
	private String maddress;	//회원 주소
	private int mpoint;			//회원 포인트
	private String mdate;		//회원 가입 날짜
	
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "Member [mno=" + mno + ", mid=" + mid + ", mpassword=" + mpassword + ", mname=" + mname + ", mphone="
				+ mphone + ", memail=" + memail + ", maddress=" + maddress + ", mpoint=" + mpoint + ", mdate=" + mdate
				+ "]";
	}



	public Member(int mno, String mid, String mpassword, String mname, String mphone, String memail, String maddress, int mpoint,
			String mdate) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpassword = mpassword;
		this.mname = mname;
		this.mphone = mphone;
		this.memail = memail;
		this.maddress = maddress;
		this.mpoint = mpoint;
		this.mdate = mdate;
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}
	
	
	public int getMno() {
		return mno;
	}


	public void setMno(int mno) {
		this.mno = mno;
	}


	public String getMpassword() {
		return mpassword;
	}


	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}


	public String getMname() {
		return mname;
	}


	public void setMname(String mname) {
		this.mname = mname;
	}


	public String getMphone() {
		return mphone;
	}


	public void setMphone(String mphone) {
		this.mphone = mphone;
	}


	public String getMemail() {
		return memail;
	}


	public void setMemail(String memail) {
		this.memail = memail;
	}


	public String getMaddress() {
		return maddress;
	}


	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}


	public int getMpoint() {
		return mpoint;
	}


	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}


	public String getMdate() {
		return mdate;
	}


	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	
	
	
	
	

}
