package dto;

public class Company {

	private int cnum;		//회사고유번호
	private int crn;		//사업자 등록 번호 
	private String cname;	//회사 명
	private String cprice;	// 가격
	private String cbank;   // 은행
	private String caccout; //계좌
	public Company() {
		
	}
	public Company(int cnum, int crn, String cname, String cprice, String cbank, String caccout) {
		super();
		this.cnum = cnum;
		this.crn = crn;
		this.cname = cname;
		this.cprice = cprice;
		this.cbank = cbank;
		this.caccout = caccout;
	}
	@Override
	public String toString() {
		return "Company [cnum=" + cnum + ", crn=" + crn + ", cname=" + cname + ", cprice=" + cprice + ", cbank=" + cbank
				+ ", caccout=" + caccout + "]";
	}
	
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public int getCrn() {
		return crn;
	}
	public void setCrn(int crn) {
		this.crn = crn;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCprice() {
		return cprice;
	}
	public void setCprice(String cprice) {
		this.cprice = cprice;
	}
	public String getCbank() {
		return cbank;
	}
	public void setCbank(String cbank) {
		this.cbank = cbank;
	}
	public String getCaccout() {
		return caccout;
	}
	public void setCaccout(String caccout) {
		this.caccout = caccout;
	}

	
}
