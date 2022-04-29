package dto;

public class Member {
	private int mnum;
	private String id;
	private String password;
	private String name;
	
	public Member() {
		super();
	}
	
	public Member(int mnum, String id, String password, String name) {
		super();
		this.mnum = mnum;
		this.id = id;
		this.password = password;
		this.name = name;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	




}
