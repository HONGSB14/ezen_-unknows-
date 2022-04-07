package application.Day26;

import java.net.InetAddress;

public class Day26_1 {
	
		/*
		 	네트워크: 두 대 이상의 컴퓨터들을 연결하고 통신 할 수 있는 것
				통신망: 전자신호를 통해 통신하는 모든 기기가 서로 통신하기 위한 하나의 망
					
					프로토콜: 컴퓨터 내부 , 컴퓨터 사이 데이터 교환 방식을 정의 하는 규칙[ 체계 ]
				    	SMTP: 메일 전송 프로토콜 
				    	IP	: 인터넷 프로토콜
				    
				    TCP/IP
				    	
				    	TCP : Transmission Control Protocol : 통신 제어
				    	IP	: Internet Protocol address 	: 통신 인식번호 [주소-PC주소-집주소] :0.0.0.0 ~255.
				    		우리집<------------우편------------>친구집
				    										 친구집의 집주소
				    										
				    		
				    		집 컴퓨터<-------------------------->네이버 컴퓨터[홈페이지]
				    											www.naver.com [도메인 주소 : 문자이기 떄문에 외우기가 쉬움]
																223.130.195.200[IP 주소 : 숫자이기떄문에 사람이 외우기 어려움]
																숫자주소(IP) ----> 문자주소(DNS)
		*/
	
	public static void main(String[] args) {
		
		try {
			//1. 자바에서 ip 주소 확인
			InetAddress inetAddress = InetAddress.getLocalHost();
				//InetAddress 네트워크 정보를 저장하는 클래스
					//InetAddress.getLocalHost();	//로컬(현pc)의 정보 호출
						//자바 외 통신할 경우 일반예외 발생
			System.out.println(inetAddress);
			System.out.println("현재 pc이름: "+inetAddress.getHostName());
			System.out.println("현재 pc의 ip주소 : "+inetAddress.getHostAddress());
			
			//2.네이버 주소의 ip주소 확인
			InetAddress inetAddress2 = InetAddress.getByName("www.naver.com");
			System.out.println("네이버 pc 정보 객체 :"+inetAddress2);
			System.out.println("네이버 pc의 이름:"+inetAddress2.getHostName());
			System.out.println("네이버 pc의 ip주소 :"+inetAddress2.getHostAddress());
			
			//3.페이스북 회사에 IP주소 확인
			InetAddress inetAddress3 = InetAddress.getByName("www.facebook.com");
			System.out.println("페이스북 pc 정보 객체 :"+inetAddress3);
			System.out.println("페이스북 pc의 이름:"+inetAddress3.getHostName());
			System.out.println("페이스북 pc의 ip주소 :"+inetAddress3.getHostAddress());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			
	
	}
}
