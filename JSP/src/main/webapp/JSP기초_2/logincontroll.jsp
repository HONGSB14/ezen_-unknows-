<%@page import="dto.Member"%>
<%@page import="dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%



	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");

	Dao dao = new Dao();
	Member member = new Member();
	boolean result= dao.login(id, pwd);
	
	System.out.println(result);
	
	if(result){
		response.sendRedirect("main.jsp");
		session.setAttribute("loginid", id);
		System.out.println("내가졌다..");
		//서블릿 내장 객체 :session -> 서버 내 필드 생성 // 모든 페이지[파일] 에서 접근 할 수 있는 메모리
		//브라우저 마다 메모리가 별도생성
	}else{
		System.out.println("오류났어~ 고쳐~ 킹받쥬? 개빡치쥬?");
	}
%>
<p>로그인 컨트롤러</p>
</body>
</html>