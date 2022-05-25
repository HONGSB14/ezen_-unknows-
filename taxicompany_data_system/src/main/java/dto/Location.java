package dto;

public class Location {

	private int cnum;				//회사고유번호
	private String coordinate;		//좌표
	private String carnum;			//차량 번호
	
	public Location() {
		
	}

	
	
	public Location(int cnum, String coordinate, String carnum) {
		super();
		this.cnum = cnum;
		this.coordinate = coordinate;
		this.carnum = carnum;
	}



	@Override
	public String toString() {
		return "Location [cnum=" + cnum + ", coordinate=" + coordinate + ", carnum=" + carnum + "]";
	}



	public int getCnum() {
		return cnum;
	}



	public void setCnum(int cnum) {
		this.cnum = cnum;
	}



	public String getCoordinate() {
		return coordinate;
	}



	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}



	public String getCarnum() {
		return carnum;
	}



	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	
	
	
}
