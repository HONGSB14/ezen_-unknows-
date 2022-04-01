package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Member;

public class MemberDao {	//DB 접근객체
	
	private Connection con; 		//DB연동시 사용되는 클래스: DB연동클래스
	private PreparedStatement ps;	//연결된 DB 내 SQL 조작할때 사용되는 인터페이스
	private ResultSet rs;			//결과물을 조작하는 인터페이스
	
	
	//DB연동 할때마다 객체 선언 시 불필요한 코드 될 수 있기 때문에
	public static MemberDao memberDao = new MemberDao();	//DB연동 객체 
	
	//constructor
	
	public MemberDao () {
		//DB연동 
		try {
			//1.DB드라이버 가져오기
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2.DB주소 연결
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
			
		} catch (Exception e) {
			System.out.println("[DB연동 실패 ]"+e);
		}				
	}
	

	//method 
	
	//아디 중복 체크 메소드(인수: 아이디를 인수로 받아 db에 존재하는지 체크)
	public boolean idcheck(String id) {
		
		try {
			
			//1.SQL 작성
			//검색 : select * from 테이블명 where 조건(필드명=값) 	*은 모든필드  
			String sql ="select *from member where mid=?";
			
			//2.SQL 조작
			ps =con.prepareStatement(sql);
			ps.setString(1, id);
			
			//3.SQL 실행
			rs=ps.executeQuery(); // select 실행 -> 검색은 결과물이 존재 -> resultset 0
				//resultset 처음 기본 값은 null이에요.  : null----next()---->결과 레코드
			
			//4.SQL 결과
			if(rs.next()) {//만약 다음 결과물이 존재한다면 => 해당 아이디가 존재 => 중복o
				return true;	//해당아이디는 중복이 존재 (검색은 찾는것이기 때문에 찾았으면트루! 즉 true 가 중복)
			}
				
		} catch (Exception e) {
			System.out.println("아이디 중복체크 sql 오류"+e);
		}
		return false;//중복 x
	}
	
	//1. 회원가입 메소드	(인수:DB에 넣을 아이디,비밀번호,이메일,주소)
	public boolean signup(Member member) {
		try {
			//1. SQL 작성		[회원번호(자동번호 =auto) , 포인트 (가입시0)  제외한 모든 필드 삽입]
			String sql = "insert into member(mid , mpwd, memail , madd, mpoint ,mcince )values(?,?,?,?,?,?)";
			//2. SQL 조작
			ps= con.prepareStatement(sql);		// prepareStatement 인터페이스 내 연결된 DB에 SQL 넣기
			ps.setString(1, member.getMid());	//1번 ? 에 아이디 넣어주기
			ps.setString(2, member.getMpwd());	//2번 ? 에 비밀번호 넣어주기
			ps.setString(3, member.getMemail());//3번 ? 에 이메일 넣어주기
			ps.setString(4, member.getMadd());	//4번 ? 에 주소 넣어주기
			ps.setInt	(5, member.getMpoint());	//5번 ? 에 포인트 넣어주기
			ps.setString(6, member.getMcince());//6번 ? 에 가입일 넣어주기
			
			//3. SQL 실행		//insert 실행 -> 삽입은 결과물이 없기때문에  -> resultset 할 필요가 없음
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("[SQL연동 실패]"+e);
		}
		return false;
	}
	
	//2. 로그인 메소드(인수:로그인 시 필요한 아이디,비밀번호)
	public boolean login(String id, String pwd) {
		
		try {
			//1.SQL 작성
			//연산자 and : 조건1 and 조건2 	&&
			//연산자 or  : 조건1 or 조건2	||
			String sql = "select *from member where mid=? and mpwd=?";
			
			//2.SQL 조작
			ps=con.prepareStatement(sql);
			ps.setString(1, id);	//첫번째 ? id변수 넣기
			ps.setString(2, pwd);	//두번째 ? pwd변수 넣기
			//3.SQL 실행
			rs=ps.executeQuery();
			//4.SQL 결과
			if(rs.next()) {			//select 시 결과물이 있으면
				return true;		//아이디와 비밀번호가 동일하면 -> 로그인 성공
			}
		} catch (Exception e) {
			System.out.println("login sql 실패"+e);
		}
		return false;				//로그인 실패
	}
	//3. 아이디찾기 메소드(인수:아이디찾기 시 필요한 이메일)
	public String findid(String email) {
		
		try {
			//1.SQL 작성
			String sql = "select * from member where memail =?";
			//2.SQL 조작
			ps= con.prepareStatement(sql);
			ps.setString(1, email);
			//3.SQL 실행
			rs=ps.executeQuery();
			//4.SQL 결과
		
			if(rs.next()) {	
				return rs.getString(2);
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	//4. 비밀번호 찾기 메소드(인수:비밀번호 찾기시 필요한 아이디,이메일)
	public String findpwd(String id , String email) {
		
		try {
			String sql = "select* from member where mid=? and memail=? ";
			ps= con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, email);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getString(3);
			}
		} catch (Exception e) {
			
		}
		
		return null;
	}

	

















}
