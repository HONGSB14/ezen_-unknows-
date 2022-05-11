package dao;

import java.util.ArrayList;

import dto.Chat;

public class ChatDao extends Dao{
	
	public ChatDao() {
		super();
	}

	public static ChatDao chatDao = new ChatDao();
	
	public static ChatDao getChatDao() {
		
		return chatDao;
	}
	
	
	public ArrayList<Chat> getchatlist(String nowtime){
		
		ArrayList<Chat> chatList = null; //채팅리스트를 위한 어레이 리스트
		
		String sql = "Select * from chat where chattime > ? odrer by chattime"; // 채팅타임이 나우타임보다 높다면 채팅타임을 정렬
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, nowtime);
			rs=ps.executeQuery();
			while(rs.next()) {
				Chat chat = new Chat();
				chat.setChatname(rs.getString("chatname"));
				chat.setChatcontent(rs.getString("chatcontent"));
				chat.setChattime(rs.getString("chattime"));
				chatList.add(chat);
			}
			return chatList;
		} catch (Exception e) {
			System.out.println("오우 리스트 에러났어~ !!    "+e);
		}
		return null;	
	}
}
