package dto;

public class Tacometer {

	private int cnum;				//회사 고유 번호
	private String carNum;			//차량 번호 
	private int totalMileage;		//총 주행거리
	private int mileageFee;			//주행요금
	private String dname;			//운전자 이름
	private int dnum;				//운전자 고유 번호 (id)	
	private String startLocation;	//운행 시작위치
	private String tdate;
	
	public Tacometer() {
		// TODO Auto-generated constructor stub
	}

	public Tacometer(int cnum, String carNum, int totalMileage, int mileageFee, String dname, int dnum,
			String startLocation, String tdate) {
		super();
		this.cnum = cnum;
		this.carNum = carNum;
		this.totalMileage = totalMileage;
		this.mileageFee = mileageFee;
		this.dname = dname;
		this.dnum = dnum;
		this.startLocation = startLocation;
		this.tdate = tdate;
	}

	@Override
	public String toString() {
		return "Tacometer [cnum=" + cnum + ", carNum=" + carNum + ", totalMileage=" + totalMileage + ", mileageFee="
				+ mileageFee + ", dname=" + dname + ", dnum=" + dnum + ", startLocation=" + startLocation + ", tdate="
				+ tdate + "]";
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public int getTotalMileage() {
		return totalMileage;
	}

	public void setTotalMileage(int totalMileage) {
		this.totalMileage = totalMileage;
	}

	public int getMileageFee() {
		return mileageFee;
	}

	public void setMileageFee(int mileageFee) {
		this.mileageFee = mileageFee;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public int getDnum() {
		return dnum;
	}

	public void setDnum(int dnum) {
		this.dnum = dnum;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	
	
}
