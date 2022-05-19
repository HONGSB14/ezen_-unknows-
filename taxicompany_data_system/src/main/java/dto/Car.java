package dto;

public class Car {
	
	private int cnum;
	private String carNum;
	private String carType;
	private String carName;
	private String fuelType;
	
	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(int cnum, String carNum, String carType, String carName, String fuelType) {
		super();
		this.cnum = cnum;
		this.carNum = carNum;
		this.carType = carType;
		this.carName = carName;
		this.fuelType = fuelType;
	}

	@Override
	public String toString() {
		return "Car [cnum=" + cnum + ", carNum=" + carNum + ", carType=" + carType + ", carName=" + carName
				+ ", fuelType=" + fuelType + "]";
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

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	

}
