package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Product;
import dto.Room;
import dto.RoomLive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomDao {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public static RoomDao roomDao = new RoomDao();
	
	public RoomDao () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			System.out.println("[DB연동 실패 ] dao.RoomDao : "+e);
		}
	}
	
    //1.방 저장 메소드
	public boolean roomadd(Room room) {
		try {
			String sql= "insert into room(roname,roip) values(?,?)";	//테이블에 레코드를 추가하는 문법 : insert into 테이블명 (필드명1,필드명2) values (값1,값2);
			ps= conn.prepareStatement(sql);
			ps.setString(1, room.getRoname());
			ps.setString(2, room.getRoip());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("방 저장 실패 !: "+e);
		}
		
		return false;
	}

	//2.방 번호 호출 메소드[방번호= 포트번호 사용]
	public int getroomnum() { //가장 최근에 등록된 방 번호 가져오기
		try {
			String sql = "select max(ronum) from room"; 	//Max(테이블명) 은 테이블의 최대값
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("방번호 호출 실패: "+e);
		}
		return 0;
	}
	
	//3.모든 방 호출 메소드 [ 
	public ObservableList<Room> roomlist (){
		ObservableList<Room> roomList = FXCollections.observableArrayList();
		
		try {
			String sql = "select * from room order by ronum desc";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				Room room = new Room(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						0);
				roomList.add(room);
			}
			return roomList;
		} catch (Exception e) {
			System.out.println("모든 방 호출 실패 !! dao.RoomDao: "+e);
		}
		return null;
	}
	
	//4.채팅방 접속 명단 추가
	public boolean addroomlive (RoomLive roomlive ) {
		
		String sql = "insert into roomlive(ronum,mid) values(?,?)";
		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1, roomlive.getRonum());
			ps.setString(2, roomlive.getMid());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("채팅방 접속 명단 추가 실패!! dao.RoomDao : "+e);
		}
		return false;
	}
	
	//5.채팅방 접속된 모든 명단 호출
	public ArrayList<RoomLive> getroomlivelist(int ronum) {
			ArrayList<RoomLive> roomlivelist = new ArrayList<>();
			
			try {
				String  sql = "select * from roomlive where ronum=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, ronum);
				rs=ps.executeQuery();
				while(rs.next()) {
					RoomLive roomLive = new RoomLive(rs.getInt(1), rs.getInt(2), rs.getString(3));
					roomlivelist.add(roomLive);
							
				}
				return roomlivelist;
			} catch (Exception e) {
				System.out.println("채팅방 접속된 모든 명단 호출 실패!!  dao.RoomDao : "+e);
			}
			return null;
	}
	
	//6.채팅방 접속 명단 삭제
		public boolean roomlivedelete(String mid) {
			String sql= "delete from roomlive where mid=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, mid);
				ps.executeUpdate();
				return true;
			} catch (Exception e) {
				System.out.println("채팅방 접속 명단 삭제 실패! dao.RoomDao : "+e);
			}
			return false;
		}
	
	//7. 채팅방 삭제 [ 조건: 해당 채팅방에 접속명단이 0 이면]
		public boolean roomdelte(int ronum) {
			
			String sql = "select * from roomlive where ronum=?";
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1, ronum);
				rs=ps.executeQuery();
				if(rs.next()) {	//방에 사람이 존재하면 
					return false;
				}else {	//사람이 0명이면
					String sql2= "delete from room where ronum=?";
					ps=conn.prepareStatement(sql2);
					ps.setInt(1, ronum);
					ps.executeUpdate();
					return true;
				}
			} catch (Exception e) {
				System.out.println("채팅방 삭제 실패!! dao.RoomDao : "+e);
			}
			return false;
		}
}

	
