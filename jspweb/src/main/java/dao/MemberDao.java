package dao;

public class MemberDao extends Dao {
	//자식 클래스로 객체 생성 시 (부모 클래스의 생성자 호출)
	
	public MemberDao() {
		super();	//부모클래스 생성자 호출
	}
	
	public static MemberDao memberDao = new MemberDao(); //dao 호출시 반복되는 new 연산자
	
	public static MemberDao getMemberDao() {
		
		return memberDao;
	}
	
	
	public boolean idsame() {
		
		
		return false;
	}

	
	public boolean signup() {
		
		String sql="";
		
		return false;
	}
	
}
