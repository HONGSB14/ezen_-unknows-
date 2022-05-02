package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {

	private Connection conn; 		// 1.DB 연동 클래스
	private PreparedStatement ps; 	// 2.연결된 DB내 SQL 조작
	private ResultSet rs; 			// 3.SQL 결과 레코드
	
	public MemberDao() {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC 드라이브 
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			
		}
	}
	




}
