package dto;

public class Chat {

	private String chatname;
	private String chatcontent;
	private String chattime;
	
	public Chat() {
		// TODO Auto-generated constructor stub
	}

	public Chat(String chatname, String chatcontent, String chattime) {
		super();
		this.chatname = chatname;
		this.chatcontent = chatcontent;
		this.chattime = chattime;
	}

	public String getChatname() {
		return chatname;
	}

	public void setChatname(String chatname) {
		this.chatname = chatname;
	}

	public String getChatcontent() {
		return chatcontent;
	}

	public void setChatcontent(String chatcontent) {
		this.chatcontent = chatcontent;
	}

	public String getChattime() {
		return chattime;
	}

	public void setChattime(String chattime) {
		this.chattime = chattime;
	}
	
	
}
