package dao;

import java.util.ArrayList;
import dto.Slip;

public class SaleDao extends Dao {

	public SaleDao() {
		super();
	}	
	
	public static SaleDao saleDao = new SaleDao();
	
	public static SaleDao getsaleDao() {
		
		return saleDao;
	
	}
	
		//일 매출 구하기 메소드
		public ArrayList<Slip> daysaleadd(int cnum) {
	
			ArrayList<Slip> saleDayList = new ArrayList<Slip>();
			String sql="SELECT cnum,sum(sflux),sum(sfee),sum(scardfee),sum(sdaysale),date_format(sdate,'%Y-%m-%d') "
					+ "FROM taxisaledata.slip "
					+ "WHERE cnum=? "
					+ "GROUP BY date_format(sdate,'%Y-%m-%d') "
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
		
			String sql="SELECT cnum,sum(sflux),sum(sfee),sum(scardfee),sum(sdaysale),date_format(sdate,'%Y-%m') "
					+ "FROM taxisaledata.slip "
					+ "WHERE cnum=? "
					+ "GROUP BY date_format(sdate,'%Y-%m') "
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
		
		//년 매출 구하기 메소드
		public ArrayList<Slip> yearsaleadd(int cnum){
			
			ArrayList<Slip> yearSaleList = new ArrayList<Slip>();
			String sql="SELECT cnum,sum(sflux),sum(sfee),sum(scardfee),sum(sdaysale),date_format(sdate,'%Y') "
					+ "FROM taxisaledata.slip "
					+ "WHERE cnum=? "
					+ "GROUP BY date_format(sdate,'%Y') "
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
				System.out.println("년 매출 리스트 호출 실패 !!   "+e);
			}
			return null;
		}
		
		//가격체크
		public String pricecheck(int cnum) {
			String sql ="select cprice from taxisaledata.company right join taxisaledata.member on (company.cnum=member.cnum) where member.cnum=?";
			
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, cnum);
				rs=ps.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
			} catch (Exception e) {
				System.out.println("가격체크 실패 !!  "+e);
			}
			
			return null;
		}
		

	
}
