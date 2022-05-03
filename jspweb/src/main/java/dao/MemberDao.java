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
		
		String sql = "selete * from member where mid="+mid;
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
		
		String sql="INSERT INTO member (mid,mpassword,mname,mphone,memail,maddress) values (?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
		
			ps.setString(1, member.getMid());
			ps.setString(2, member.getMpassword());
			ps.setString(3, member.getMname());
			ps.setString(4, member.getMphone());
			ps.setString(5, member.getMemail());
			ps.setString(6, member.getMaddress());
			
			ps.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.println("dao 회원가입 실패"+e);
		}
		
		return false;
	}

	
	public boolean emailcheck(String email) {
		
		String sql="select * from member where memail="+email;
		try {
			ps=conn.prepareStatement(sql); rs=ps.executeQuery(); if(rs.next()) return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	public int login(String mid , String mpassword){
		
		String sql="select * from member where mid='"+mid+"' and mpassword = '"+mpassword+"'";
		//String sql ="select * from member where mid=? and mpassword=?";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				return 1; //동일 한 값  = 1
			}
			return 2; //동일한 값 없으면 = 2
		} catch (Exception e) {
			System.out.println("login 오류 : "+e);
		}
		return 3;	//db오류이면 = 3
	}
}
