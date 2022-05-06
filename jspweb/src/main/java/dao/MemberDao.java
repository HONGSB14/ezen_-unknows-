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
	
	//개별 회원정보 출력 [인수 : 세션에 저장된 회원 id ] 
	public Member getmember(String mid) {
		String sql ="select * from member where mid=?";
	
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, mid);
			rs=ps.executeQuery();
			if(rs.next()) {
				//패스워드를 제외한 모든 플디의 값을 객체화
				Member member = new Member(rs.getInt(1),rs.getString(2),null,rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9));
				return member;
			}
		} catch (Exception e) {
			System.out.println("member정보 오류 : "+e);
		}
		return null;
	}
	//회원탈퇴 비밀번호 확인 메소드
	public boolean passwordcheck(String mid ,String mpassword) {
	
		String sql ="select * from member where mid=? and mpassword= ?";
			
	try {
		ps=conn.prepareStatement(sql);
		ps.setString(1, mid);
		ps.setString(2, mpassword);
		rs=ps.executeQuery();
		if(rs.next()) {
			return true;
		}
	} catch (Exception e) {
		System.out.println(e);
	}
	return false;
	}


	//회원 삭제
	public boolean delete(String mid) {
		
		try {
			String sql = "delete from member where mid =?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, mid);
			System.out.println(mid);
			ps.executeUpdate();
			System.out.println("탈퇴성공");
			return true;
		} catch (Exception e) {
			System.out.println("오류 :" +e);
		}
		return false;
	}

	// 회원 수정 메소드 
	public boolean update( Member member ) {
		try {
		if( member.getMpassword() == null ) { // 패스워드 변경이 없을때 
			String sql ="update member set mname=? ,  mphone=? , memail=?,"
					+ "maddress = ? where mno=?";
				ps = conn.prepareStatement(sql);
				ps.setString( 1 , member.getMname() );
				ps.setString( 2 , member.getMphone() );
				ps.setString( 3 , member.getMemail() );
				ps.setString( 4 , member.getMaddress() );
				ps.setInt( 5, member.getMno() );
		}else {	// 패스워드가 변경이 있을때 
			String sql ="update member set mname=? , mpassword = ? ,  mphone=? , memail=?,"
					+ "maddress = ? where mno=?";
				ps = conn.prepareStatement(sql);
				ps.setString( 1 , member.getMname() );
				ps.setString( 2 , member.getMpassword() );
				ps.setString( 3 , member.getMphone() );
				ps.setString( 4 , member.getMemail() );
				ps.setString( 5 , member.getMaddress() );
				ps.setInt( 6 , member.getMno() );
		}
			ps.executeUpdate(); return true;
		}catch (Exception e) {} return false;
	}
	
	//회원 번호 출력 메소드
	public int getmno(String mid) {
		String sql =" select mno from member where mid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, mid);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("회원번호 출력 실패 :"  +e);
		}
		return 0;
	};
	
	public String getid(int mno) {
		
		String sql="select mid from member where mno=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getString("");
				
			}
		} catch (Exception e) {
			System.out.println("아이디 가져오기 실패 :"  +e);
		}
		return null;
	}
	

}


