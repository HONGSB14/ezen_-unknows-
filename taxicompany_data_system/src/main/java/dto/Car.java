package dto;

public class Car {
		
	private int cnum;			//회사번호
	private String carNum;		//차량번호
	private String carId;		//차량 등록번호
	private String carType;		//차량 종류
	private String carName;		//차량 이름
	private String fuelType;	//차량 연료타입
	
	public Car() {
		// TODO Auto-generated constructor stub
	}
	
	public Car(int cnum, String carNum, String carId, String carType, String carName, String fuelType) {
		super();
		this.cnum = cnum;
		this.carNum = carNum;
		this.carId = carId;
		this.carType = carType;
		this.carName = carName;
		this.fuelType = fuelType;
	}

	@Override
	public String toString() {
		return "Car [cnum=" + cnum + ", carNum=" + carNum + ", carId=" + carId + ", carType=" + carType + ", carName="
				+ carName + ", fuelType=" + fuelType + "]";
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

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
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
