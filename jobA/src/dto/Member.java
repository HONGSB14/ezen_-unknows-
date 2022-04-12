package dto;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Member {	//데이터 모델

	
	private int mnum ;		//회원번호
	private String mid ;	//아이디
	private String mpwd ;	//패스워드
	private String memail ;	//이메일
	private String madd ;	//주소
	private int mpoint;		//포인트
	private String mcince;	//가입일


	
	public Member() {}

	public Member(int mnum, String mid, String mpwd, String memail, String madd, int mpoint, String mcince) {
		super();
		this.mnum = mnum;
		this.mid = mid;
		this.mpwd = mpwd;
		this.memail = memail;
		this.madd = madd;
		this.mpoint = mpoint;
		this.mcince = mcince;
	}

	//메일보내기 메소드
	public static void sendmail(String 받는사람이메일 , String 내용) {
		//1.보내는사람의 정보
		String 보내는사람이메일= "sbin014@naver.com";
		String 보내는사람이메일비밀번호="비밀번호";
		
		//2.호스트 설정	[네이버 기준 =고정]						
		Properties properties = new Properties(); 			//컬렉션 프레임워크[map 컬렉션]
		properties.put("mail.smtp.host","smtp.naver.com");	//호스트 주소 
		properties.put("mail.smtp.port", 587);				//호스트 포트번호
		properties.put("mail.smtp.auth", "true");			//보내는사람이메일 인증
		properties.put("mail.smtp.ssl.protocols","TLSv1.2");//보안연결 버전 설정
		
		//3.인증처리[Session : java.mail 패키지 ]
		Session session = Session.getDefaultInstance(properties, new Authenticator() {	//Sesssion.getfaultinstance(설정객체,인증객체)
		
			@Override	//보내는사람의 이메일 주소 , 비밀번호 인증 해주는 메소드 구현
			protected PasswordAuthentication getPasswordAuthentication() {
	
				return new PasswordAuthentication(보내는사람이메일,보내는사람이메일비밀번호);
		
			}
		});
		
		//4.메일 보내기
		try {	
			MimeMessage message = new MimeMessage(session);										//Mime 프로토콜 :전자우편 표준 포멧[형식]
			message.setFrom(new InternetAddress(보내는사람이메일));									//보내는사람
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(받는사람이메일));		//받는사람
			
			//메일전송
			message.setSubject("결투회원 비밀번호 찾기");		
			message.setText("회원님의 비밀번호 : "+ 내용 );
			
			//전송
			Transport.send(message);
		} catch (Exception e) {
			System.out.println("메일전송 실패 "+e);
		}
	}
	
	public int getMnum() {
		return mnum;
	}


	public void setMnum(int mnum) {
		this.mnum = mnum;
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	public String getMpwd() {
		return mpwd;
	}


	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}


	public String getMemail() {
		return memail;
	}


	public void setMemail(String memail) {
		this.memail = memail;
	}


	public String getMadd() {
		return madd;
	}


	public void setMadd(String madd) {
		this.madd = madd;
	}


	public int getMpoint() {
		return mpoint;
	}


	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}


	public String getMcince() {
		return mcince;
	}


	public void setMcince(String mcince) {
		this.mcince = mcince;
	}
}
