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
		
	//일 매출 구하기 메소드
	public ArrayList<Slip> daysaleadd(int cnum) {
		 ArrayList<Slip> saleDayList = new ArrayList<Slip>();
		String sql="SELECT DISTINCT cnum,sum(sflux),sum(sfee),sum(scardfee),sum(sdaysale),date_format(sdate,'%Y-%m-%d') "
					+ "FROM taxisaledata.slip "
					+ "GROUP BY date_format(sdate, '%Y-%m-%d') "
					+ "HAVING cnum =? "
					+ "ORDER BY date_format(sdate,'%Y-%m-%d') ASC";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			while(rs.next()) {
				Slip slip = new Slip(rs.getInt(1),0, null, rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), null, rs.getString(6));
				saleDayList.add(slip);
			}
			return saleDayList;
		} catch (Exception e) {
			System.out.println("일 매출 리스트 호출 실패 !!   "+e);
		}
		return null;
	}
	
	//월 매출 구하기 메소드
	public ArrayList<Slip> monthsaleadd(int cnum){
		ArrayList<Slip> monthSaleList = new ArrayList<Slip>();
		String sql="SELECT DISTINCT cnum,sum(sflux),sum(sfee),sum(scardfee),sum(sdaysale),date_format(sdate,'%Y-%m') "
				+ "FROM taxisaledata.slip "
				+ "GROUP BY date_format(sdate, '%Y-%m') "
				+ "HAVING cnum =? "
				+ "ORDER BY date_format(sdate,'%Y-%m') ASC";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			while(rs.next()) {
				Slip slip = new Slip(rs.getInt(1),0, null, rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), null, rs.getString(6));
				monthSaleList.add(slip);
			}
			return monthSaleList;
		} catch (Exception e) {
			System.out.println("월 매출 리스트 호출 실패 !!   "+e);
		}
		return null;
	}
	
	//월 매출 구하기 메소드
		public ArrayList<Slip> yearsaleadd(int cnum){
			ArrayList<Slip> yearSaleList = new ArrayList<Slip>();
			String sql="SELECT DISTINCT cnum,sum(sflux),sum(sfee),sum(scardfee),sum(sdaysale),date_format(sdate,'%Y') "
					+ "FROM taxisaledata.slip "
					+ "GROUP BY date_format(sdate, '%Y') "
					+ "HAVING cnum =? "
					+ "ORDER BY date_format(sdate,'%Y') ASC";
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, cnum);
				rs=ps.executeQuery();
				while(rs.next()) {
					Slip slip = new Slip(rs.getInt(1),0, null, rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), null, rs.getString(6));
					yearSaleList.add(slip);
				}
				return yearSaleList;
			} catch (Exception e) {
				System.out.println("월 매출 리스트 호출 실패 !!   "+e);
			}
			return null;
		}
}
