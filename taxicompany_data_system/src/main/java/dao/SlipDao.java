package dao;

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
			System.out.println("addslip err!!"   +e);
		}
		return false;
	}
}
