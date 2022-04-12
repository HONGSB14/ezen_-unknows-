package dto;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Member {	//������ ��

	
	private int mnum ;		//ȸ����ȣ
	private String mid ;	//���̵�
	private String mpwd ;	//�н�����
	private String memail ;	//�̸���
	private String madd ;	//�ּ�
	private int mpoint;		//����Ʈ
	private String mcince;	//������


	
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

	//���Ϻ����� �޼ҵ�
	public static void sendmail(String �޴»���̸��� , String ����) {
		//1.�����»���� ����
		String �����»���̸���= "sbin014@naver.com";
		String �����»���̸��Ϻ�й�ȣ="��й�ȣ";
		
		//2.ȣ��Ʈ ����	[���̹� ���� =����]						
		Properties properties = new Properties(); 			//�÷��� �����ӿ�ũ[map �÷���]
		properties.put("mail.smtp.host","smtp.naver.com");	//ȣ��Ʈ �ּ� 
		properties.put("mail.smtp.port", 587);				//ȣ��Ʈ ��Ʈ��ȣ
		properties.put("mail.smtp.auth", "true");			//�����»���̸��� ����
		properties.put("mail.smtp.ssl.protocols","TLSv1.2");//���ȿ��� ���� ����
		
		//3.����ó��[Session : java.mail ��Ű�� ]
		Session session = Session.getDefaultInstance(properties, new Authenticator() {	//Sesssion.getfaultinstance(������ü,������ü)
		
			@Override	//�����»���� �̸��� �ּ� , ��й�ȣ ���� ���ִ� �޼ҵ� ����
			protected PasswordAuthentication getPasswordAuthentication() {
	
				return new PasswordAuthentication(�����»���̸���,�����»���̸��Ϻ�й�ȣ);
		
			}
		});
		
		//4.���� ������
		try {	
			MimeMessage message = new MimeMessage(session);										//Mime �������� :���ڿ��� ǥ�� ����[����]
			message.setFrom(new InternetAddress(�����»���̸���));									//�����»��
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(�޴»���̸���));		//�޴»��
			
			//��������
			message.setSubject("����ȸ�� ��й�ȣ ã��");		
			message.setText("ȸ������ ��й�ȣ : "+ ���� );
			
			//����
			Transport.send(message);
		} catch (Exception e) {
			System.out.println("�������� ���� "+e);
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
