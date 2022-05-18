package dao;

import java.util.ArrayList;

import dto.Driver;

public class DriverDao extends Dao {

	public DriverDao() {
		super();
		
	}
	
	static public DriverDao driverDao = new DriverDao();
	
	static public DriverDao getDriverDao() {
	
		return driverDao;
	}
	
	//기사등록 메소드
	public boolean driversignup(Driver driver) {
		
		String sql="INSERT INTO taxisaledata.driver(cnum,dnum,dname,dnote) VALUES (?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, driver.getCnum());
			ps.setInt(2, driver.getDnum());
			ps.setString(3, driver.getDname());
			ps.setString(4, driver.getDnote());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			System.out.println("기사등록 실패!! " +e);
		}
		return false;
	}
	//모든 기사 리스트 뽑아오기 리스트
	public ArrayList<Driver> driverlist(int cnum){
		ArrayList<Driver> driverlist = new ArrayList<Driver>();
		String sql = "SELECT * from taxisaledata.driver where cnum=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			while(rs.next()) {
				Driver driver = new Driver(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5));
				driverlist.add(driver);
			}
			return driverlist;
		}catch (Exception e) {
			System.out.println("기사리스트 불러오기 실패!! " +e);
		}
		
		return null;
	}
}
