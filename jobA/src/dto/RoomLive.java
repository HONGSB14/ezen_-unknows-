package dto;

public class RoomLive {
	
	private int livenum ;
	private int  ronum	;
	private String mid	;   
	
	public RoomLive() {
		// TODO Auto-generated constructor stub
	}

	public RoomLive(int livenum, int ronum, String mid) {
		super();
		this.livenum = livenum;
		this.ronum = ronum;
		this.mid = mid;
	}

	public int getLivenum() {
		return livenum;
	}

	public void setLivenum(int livenum) {
		this.livenum = livenum;
	}

	public int getRonum() {
		return ronum;
	}

	public void setRonum(int ronum) {
		this.ronum = ronum;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
	
	
	
	
}
