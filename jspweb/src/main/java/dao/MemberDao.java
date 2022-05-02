package dao;

import dto.Member;

public class MemberDao extends Dao {
	//자식 클래스로 객체 생성 시 (부모 클래스의 생성자 호출)
	
	public MemberDao() {
		super();	//부모클래스 생성자 호출
	}
	
	public static MemberDao memberDao = new MemberDao(); //dao 호출시 반복되는 new 연산자
	
	public static MemberDao getMemberDao() {
		
		return memberDao;
	}
	
	//아이디 중복체크
	public boolean idcheck(String mid) {
		
		String sql = "selete *from member where mid="+mid;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		} catch (Exception e) {
			
		}
		return false;
	}

	
	public boolean signup(Member member) {
		
		String sql="INSERT INTO jspweb.member (mno,mid,mpassword,mname,mphone,memail,maddress,mdate)valuse (?,?,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, member.getMno());
			ps.setString(2, member.getMid());
			ps.setString(3, member.getMpassword());
			ps.setString(4, member.getMname());
			ps.setString(5, member.getMphone());
			ps.setString(6, member.getMemail());
			ps.setString(7, member.getMaddress());
			ps.setString(8, member.getMdate());
			ps.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("dao 회원가입 실패");
		}
		
		return false;
	}

	
	
}
