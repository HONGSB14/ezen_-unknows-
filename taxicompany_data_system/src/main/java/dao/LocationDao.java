package dao;

import dto.Location;

public class LocationDao extends Dao {
	
	public LocationDao() {
		super();
	}

	public static LocationDao locationDao = new LocationDao();
	
	
	public static LocationDao getLocationDao() {
		return locationDao;
	}
	//위치 정보 저장 메소드
	public boolean locationset (Location location) {
		
		String sql ="INSERT INTO taxisaledata.location(cnum,coordinate,carnum) values (?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, location.getCnum());
			ps.setString(2, location.getCoordinate());
			ps.setString(3, location.getCarnum());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("DB위치 저장 실패 !!  "+e);
		}
		return false;
	}
}
