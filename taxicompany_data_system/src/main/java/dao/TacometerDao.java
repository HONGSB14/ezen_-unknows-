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
	
	
	public ArrayList<Tacometer> getTacoList(int cnum){
		ArrayList<Tacometer> tacolist = new ArrayList<Tacometer>();
		
		String sql= "SELECT * from taxisaledata.tacometer where cnum=?";
		
		try {
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			while(rs.next()) {
				Tacometer tacometer =  new  Tacometer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getString(7));
				tacolist.add(tacometer);
			}
			return tacolist;
		} catch (Exception e) {
			System.out.println("tacolist 호출 실패~ !   "+ e);
		}
		return null;
	}
	
	
}

