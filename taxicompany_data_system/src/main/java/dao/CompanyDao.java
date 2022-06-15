package dao;

import dto.Company;

public class CompanyDao extends Dao {
	
	public CompanyDao() {
		super();
	}
	
	public static CompanyDao companyDao = new CompanyDao();
	
	public static CompanyDao getCompanyDao() {
		
		return companyDao;
	
	}
	
	//회사 등록
	public boolean companysignup(Company company) {
		String sql="insert into company (cnum,crn,cprice,cname,cbank,caccout) values(?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, company.getCnum());
			ps.setInt(2, company.getCrn());
			ps.setString(3, company.getCprice());
			ps.setString(4, company.getCname());
			ps.setString(5, company.getCbank());
			ps.setString(6, company.getCaccout());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("companysignup err!!" +e);
		}
		
		return false;
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
	
	//회사 번호 중복 체크
	public boolean cnumcheck(int cnum) {
		String sql ="SELECT cnum FROM company WHERE cnum=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("회사번호 중복체크 실패  "+e);
		}
		
		return false;
	}
	
	
	//회사 정보 가져오기
	public Company selectCompany(int cnum) {
		String sql ="SELECT * FROM company WHERE cnum=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			if(rs.next()){
				Company company = new Company(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4) , rs.getString(5), rs.getString(6));
				return company;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
}
