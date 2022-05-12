package dto;

public class Company {

	private int cnum;		//회사고유번호
	private int crn;		//사업자 등록 번호 
	private String cname;	//회사 명
	
	public Company() {
		
	}

	public Company(int cnum, int crn, String cname) {
		super();
		this.cnum = cnum;
		this.crn = crn;
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "Company [cnum=" + cnum + ", crn=" + crn + ", cname=" + cname + "]";
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

}
