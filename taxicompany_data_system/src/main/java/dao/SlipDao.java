package dao;

import java.util.ArrayList;

import dto.Slip;

public class SlipDao extends Dao{

	public SlipDao() {
		super();
	}
	
	public static SlipDao slipDao = new SlipDao();
	
	public static SlipDao getSlipDao() {
		return slipDao;
	}
	
	//전표 등록 메소드
	public boolean addSlip(Slip slip) {
		
		String sql ="INSERT INTO taxisaledata.slip(cnum,carnum,sflux,sfee,scardfee,sdaysale,snote) values(?,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, slip.getCnum());
			ps.setString(2, slip.getCarnum());
			ps.setInt(3, slip.getSflux());
			ps.setInt(4, slip.getSfee());
			ps.setInt(5, slip.getScardfee());
			ps.setInt(6, slip.getSdaysale());
			ps.setString(7, slip.getSnote());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("addSlip err!!   "+e);
		}
		return false;
	}
	
	//전표 출력 메소드
	public ArrayList<Slip> sliplist(int cnum){
		 ArrayList<Slip> sliplist = new ArrayList<Slip>();
		String sql="SELECT * from taxisaledata.slip where cnum=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			while(rs.next()) {
				Slip slip = new Slip(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9));
				sliplist.add(slip);
			}
			return sliplist;
		} catch (Exception e) {
			System.out.println("전표 리스트 호출 실패 !!   " +e);
		}
		return null;
	}
	
	//전표 검색등록 메소드
	public boolean searchSlip(Slip slip) {
				
		String sql ="INSERT INTO taxisaledata.slip(cnum,carnum,sflux,sfee,scardfee,sdaysale,snote,sdate) "
					+ "values("+slip.getCnum()+",'"+slip.getCarnum()+"',"+slip.getSflux()+","+slip.getSfee()+","+slip.getScardfee()+","+slip.getSdaysale()+",'"+slip.getSnote()+"','"+slip.getSdate()+"')";
			try {
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
					
				return true;
			} catch (Exception e) {
				System.out.println("addSlip err!!   "+e);
			}
				return false;
		}

}
