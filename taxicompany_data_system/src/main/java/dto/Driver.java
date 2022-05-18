package dto;

public class Driver {

	private int cnum;
	private int dnum;
	private String dname;
	private String dnote;
	private String ddate;
	
	
	public Driver() {
		// TODO Auto-generated constructor stub
	}

	
	public Driver(int cnum, int dnum, String dname, String dnote, String ddate) {
		super();
		this.cnum = cnum;
		this.dnum = dnum;
		this.dname = dname;
		this.dnote = dnote;
		this.ddate = ddate;
	}


	@Override
	public String toString() {
		return "Driver [cnum=" + cnum + ", dnum=" + dnum + ", dname=" + dname + ", dnote=" + dnote + ", ddate=" + ddate
				+ "]";
	}


	
	public int getCnum() {
		return cnum;
	}


	public void setCnum(int cnum) {
		this.cnum = cnum;
	}


	public int getDnum() {
		return dnum;
	}


	public void setDnum(int dnum) {
		this.dnum = dnum;
	}


	public String getDname() {
		return dname;
	}


	public void setDname(String dname) {
		this.dname = dname;
	}


	public String getDnote() {
		return dnote;
	}


	public void setDnote(String dnote) {
		this.dnote = dnote;
	}


	public String getDdate() {
		return ddate;
	}


	public void setDdate(String ddate) {
		this.ddate = ddate;
	}
	
	
}
