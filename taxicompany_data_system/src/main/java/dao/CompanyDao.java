package dao;

public class CompanyDao extends Dao {
	
	public CompanyDao() {
		super();
	}
	
	public static CompanyDao companyDao = new CompanyDao();
	
	public static CompanyDao getCompanyDao() {
		
		return companyDao;
	
	}
	
	//회사 유효성 체크
	
	public boolean comapnycheck(int crn, String cname) {
		
		String sql ="SELECT * FROM company WHERE crn=? AND cname=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, crn);
			ps.setString(2, cname);
			rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("회사 유효성 체크 실패 ! " +e);
		}
		return false;
	}
	
	
	//회사 고유번호 체크
	public String findcnum(int crn,String cname) {
		String sql ="SELECT cnum FROM company WHERE crn=? AND cname=?";
		try {
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, crn);
			ps.setString(2, cname);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getString("cnum");
			}
		} catch (Exception e) {
			System.out.println("회사 고유번호 찾기 실패 ! " +e);
		}
		return null;
	}
}
