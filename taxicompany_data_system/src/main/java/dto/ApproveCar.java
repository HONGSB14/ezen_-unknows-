package dto;

public class ApproveCar {
	
	private String apCarId;			//차량 승인 아이디
	private String apCarNum;		//차량 승인 번호
	private String apState;			//차량 승인  상태
	
	
	 public ApproveCar() {
		// TODO Auto-generated constructor stub
	}


	public ApproveCar(String apCarId, String apCarNum, String apState) {
		super();
		this.apCarId = apCarId;
		this.apCarNum = apCarNum;
		this.apState = apState;
	}


	@Override
	public String toString() {
		return "ApproveCar [apCarId=" + apCarId + ", apCarNum=" + apCarNum + ", apState=" + apState + "]";
	}


	public String getApCarId() {
		return apCarId;
	}


	public void setApCarId(String apCarId) {
		this.apCarId = apCarId;
	}


	public String getApCarNum() {
		return apCarNum;
	}


	public void setApCarNum(String apCarNum) {
		this.apCarNum = apCarNum;
	}


	public String getApState() {
		return apState;
	}


	public void setApState(String apState) {
		this.apState = apState;
	}
	 
	
	

}
