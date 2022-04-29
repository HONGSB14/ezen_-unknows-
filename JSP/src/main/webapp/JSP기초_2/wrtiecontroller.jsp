<%@page import="dao.Dao"%>
<%@page import="dto.Board"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    <%
    
    	request.setCharacterEncoding("UTF-8"); //요청시 데이터 인코딩 타입 설정
    	String title	=request.getParameter("title");	//제목
    	String content	= request.getParameter("content");	//내용
    	String writer 	= (String)session.getAttribute("loginid"); 	//작성자
    	Date date 		= new Date(); //현재 시스템 시간
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //날짜 형 변환
    	String nowdate = dateFormat.format(date);
    	// 작성자는 로그인 성공시 세션에서 가져오기
    	//세션 호출시 기본값자료형이 object -> 형 변환(1.자동 형변환 2.강제 형변환)    	
    	
    	//객체화
    	Board board = new Board(0, title, content, writer, nowdate);
    	
    	Dao dao =new Dao();
    		boolean result =dao.write(board);
    		
    		if(result){ response.sendRedirect("main.jsp");}
    %>