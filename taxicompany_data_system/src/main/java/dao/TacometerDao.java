package dao;

import java.util.ArrayList;

import dto.Tacometer;

public class TacometerDao extends Dao {

	public TacometerDao() {
		super();
	}
	

	static public TacometerDao tacometerDao = new TacometerDao();
	
	static public TacometerDao gettacometerDao() {
			
		return tacometerDao;
	}
	
	//타코미터 리스트 호출
	public ArrayList<Tacometer> getTacoList(int cnum){
		ArrayList<Tacometer> tacolist = new ArrayList<Tacometer>();
		
		String sql= "SELECT * from taxisaledata.tacometer where cnum=?";
		
		try {
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			while(rs.next()) {
				Tacometer tacometer =  new  Tacometer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getString(7),rs.getString(8));
				tacolist.add(tacometer);
			}
			return tacolist;
		} catch (Exception e) {
			System.out.println("tacolist 호출 실패~ !   "+ e);
		}
		return null;
	}
	
	//하루 차량 총 매출 타코미터 리스트 
	public ArrayList<Tacometer> getDayTacoList(int cnum){
		
		ArrayList<Tacometer> dayTacoList = new ArrayList<Tacometer>();
		
		String sql ="SELECT carnum,sum(totalmileage), sum(mileagefee),date_format(tdate,'%Y-%m-%d')  from taxisaledata.tacometer where cnum=? group by date_format(tdate,'%Y-%m-%d') , carnum";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			while(rs.next()) {
				Tacometer tacometer = new Tacometer(0, rs.getString(1), rs.getInt(2), rs.getInt(3), null, 0, null, rs.getString(4));
			 dayTacoList.add(tacometer);
			}
			return dayTacoList;
		} catch (Exception e) {
			System.out.println("daytacolist 호출 실패 ~ !!  "+e);
		}
		
		return null;
		
	}
	
	//위치 정보 가져오기
	public ArrayList<Tacometer> getlocation(int cnum){
		ArrayList<Tacometer> locationList =new ArrayList<Tacometer>();
		String sql="select startlocation,tdate from taxisaledata.tacometer where cnum=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			while(rs.next()) {
				Tacometer tacometer = new Tacometer(0, null, 0, 0, null, 0, rs.getString(1), rs.getString(2));
				locationList.add(tacometer);
			}
			return locationList;
		} catch (Exception e) {
			System.out.println("위치정보 불러오기 실패 ~ !" +e);
		}
		return null;
	}
	
}

