package application.Day22;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;






public class Day22_2 {
	//1.field
	private Connection connection;
	
	//2.constructor
	
	public Day22_2() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection =DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
			//jdbc:mysql://IP주소(로컬[본인pc] 이면 localhost):port번호/DB이름?DB시간설정,계정명,"DB비밀번호
			//JDBC : JAVA DATABASE CONNECTION
			
			System.out.println("[db연동 성공]");
		} catch (Exception e) {
			System.out.println("[db연동 실패]");
		}
		
	}
	
	//3.method
	
	//데이터 저장
	public boolean write(String 작성자, String 내용) {
		try {
			//1.SQL 작성 [DB에 쓰기]
			//DB내 테이블에 데이터 저장 : insert into 테이블명(필드명,필드명)  values (필드명 1의 값 , 필드명 2의 값)
			String sql = "insert into test(writer,content) values(?,?)";
			
			//2.SQL 설정 [연결된 DB 에 SQL 설정]
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, 작성자); // SQL 내 작성한 첫번째 ? 에 변수 넣기	[1 : 첫번째]
			ps.setString(2, 내용);  // SQL 내 작성한 두번째 ? 에 변수 넣기	[2 : 두번째]
			
			//3.SQL 실행
			ps.executeUpdate();
			
			//성공시 true
			return true;
		
		} catch (Exception e) {
			System.out.println("sql연결실패"+e);
			//오류사유 
		}
		
		
		
		
		
		//실패시 false
		
		return false;
	}
	
	//데이터 호출	[반환타입이 arraylist 사용한 이유 : 여려줄 [레코드]를 가져오기 위해서
	public ArrayList<데이터> get(){
		
		//* DB에서 가져온 데이터를 저장할  리스트 선언
		ArrayList<데이터> 데이터목록 = new ArrayList<>();
		try {
			//1.SQL 작성 [데이터 호출 ]
			//select *(모든필드) form 테이블명; 모든 데이터 가져오기 // 레코드가 없을때 까지
			String sql = "select * from test";
		
			//2.SQL 조작 [DB와 연결된 객체와 조작인터페이스 연결
			PreparedStatement ps = connection.prepareStatement(sql);
		
			//3.SQL 실행
			ResultSet rs=ps.executeQuery(); //결과물[한줄씩=레코드] 가져오기
			
			//결과물 하나가 아니고 여려개이므로 반복문 사용해서 
			//한줄씩 객체화 -> 다음줄[레코드]리스트 저장
			
			while(rs.next()) { //다음 줄 [레코드 ] 가 있으면				
				데이터 temp = new 데이터(rs.getInt(1),rs.getString(2),rs.getString(3));  // 해당줄[레코드]의 첫번째 필드 [세로] 값 가져오기
				데이터목록.add(temp);
			}
			//반복문 종료되면 리스트 반환
		
		//* 성공시 데이터 목록 반환
		return 데이터목록;
			
		} catch (Exception e) {
			System.out.println("SQL연결실패 "+e);
		}
		
		//실패 시 
		return null;
	}
}
