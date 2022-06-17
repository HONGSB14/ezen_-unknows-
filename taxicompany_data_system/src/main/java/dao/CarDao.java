package dao;

import java.util.ArrayList;

import dto.Car;

public class CarDao extends Dao {

	public CarDao() {
		super();
	}
	
	static public CarDao carDao = new CarDao();
	
	static public CarDao getcarDao() {
		
		return carDao;
		
	}
	//차량 등록 메소드
	public boolean carresistration (Car car) {
		
		String sql="INSERT INTO taxisaledata.car (cnum,carnum,carid,cartype,carname,fueltype) VALUES (?,?,?,?,?,?)";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, car.getCnum());
			ps.setString(2, car.getCarNum());
			ps.setString(3, car.getCarId());
			ps.setString(4, car.getCarType());
			ps.setString(5, car.getCarName());
			ps.setString(6, car.getFuelType());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			System.out.println("차량 등록 실패!!   "+e);
		}
		return false;
	}
	
	
	//차량 리스트 호출 메소드
	public ArrayList<Car> carlist (int cnum) {
		ArrayList<Car> carlist = new ArrayList<>();
		
		String sql = "SELECT * FROM taxisaledata.car where cnum=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			while(rs.next()) {
				
				Car car = new Car(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6));
				carlist.add(car);
			}
			return carlist;
		} catch (Exception e) {
			System.out.println("차량 리스트 호출 실패!!   "+e);
		}
		
		return null;
	}
	

}
