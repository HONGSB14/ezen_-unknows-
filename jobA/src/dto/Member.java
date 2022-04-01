package dto;

public class Member {	//데이터 모델

	
	private int mnum ;		//회원번호
	private String mid ;	//아이디
	private String mpwd ;	//패스워드
	private String memail ;	//이메일
	private String madd ;	//주소
	private int mpoint;		//포인트
	private String mcince;	//가입일

	
	public Member() {}


	public Member(int mnum, String mid, String mpwd, String memail, String madd, int mpoint, String mcince) {
		super();
		this.mnum = mnum;
		this.mid = mid;
		this.mpwd = mpwd;
		this.memail = memail;
		this.madd = madd;
		this.mpoint = mpoint;
		this.mcince = mcince;
	}


	public int getMnum() {
		return mnum;
	}


	public void setMnum(int mnum) {
		this.mnum = mnum;
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	public String getMpwd() {
		return mpwd;
	}


	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}


	public String getMemail() {
		return memail;
	}


	public void setMemail(String memail) {
		this.memail = memail;
	}


	public String getMadd() {
		return madd;
	}


	public void setMadd(String madd) {
		this.madd = madd;
	}


	public int getMpoint() {
		return mpoint;
	}


	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}


	public String getMcince() {
		return mcince;
	}


	public void setMcince(String mcince) {
		this.mcince = mcince;
	}
}
