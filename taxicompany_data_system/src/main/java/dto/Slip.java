package dto;

public class Slip {

	private int cnum; 			//회사고유번호
	private int snum;			//전표번호
	private String carnum;  	//차 번호
	private int sflux;          //유량
	private int sfee;     		//실입수입
	private int scardfee;    	//카드수입
	private int sdaysale;   	//(일)총 매출
	private String snote;		//비고
	private String sdate;       //기입날짜
	
	public Slip() {
		// TODO Auto-generated constructor stub
	}
	

	public Slip(int cnum, int snum, String carnum, int sflux, int sfee, int scardfee, int sdaysale, String snote,
			String sdate) {
		super();
		this.cnum = cnum;
		this.snum = snum;
		this.carnum = carnum;
		this.sflux = sflux;
		this.sfee = sfee;
		this.scardfee = scardfee;
		this.sdaysale = sdaysale;
		this.snote = snote;
		this.sdate = sdate;
	}

	




	@Override
	public String toString() {
		return "Slip [cnum=" + cnum + ", snum=" + snum + ", carnum=" + carnum + ", sflux=" + sflux + ", fee=" + sfee
				+ ", cardfee=" + scardfee + ", sdaysale=" + sdaysale + ", snote=" + snote + ", sdate=" + sdate + "]";
	}


	public int getCnum() {
		return cnum;
	}


	public void setCnum(int cnum) {
		this.cnum = cnum;
	}


	public int getSnum() {
		return snum;
	}


	public void setSnum(int snum) {
		this.snum = snum;
	}


	public String getCarnum() {
		return carnum;
	}


	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}


	public int getSflux() {
		return sflux;
	}


	public void setSflux(int sflux) {
		this.sflux = sflux;
	}


	public int getSfee() {
		return sfee;
	}


	public void setSfee(int sfee) {
		this.sfee = sfee;
	}


	public int getScardfee() {
		return scardfee;
	}


	public void setScardfee(int scardfee) {
		this.scardfee = scardfee;
	}


	public int getSdaysale() {
		return sdaysale;
	}


	public void setSdaysale(int sdaysale) {
		this.sdaysale = sdaysale;
	}


	public String getSnote() {
		return snote;
	}


	public void setSnote(String snote) {
		this.snote = snote;
	}


	public String getSdate() {
		return sdate;
	}


	public void setSdate(String sdate) {
		this.sdate = sdate;
	}



	
}
