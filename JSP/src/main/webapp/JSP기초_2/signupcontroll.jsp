<%@page import="dao.Dao"%>
<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   
   <%
      
       
         	
         	//컨트롤 페이지 사용
         	
         	//request : 요청  [서버가   ---- >   클라이언트 ]
         	request.setCharacterEncoding("UTF-8");
         	String id=request.getParameter("id");
         	String pwd=request.getParameter("password");
         	String name=request.getParameter("name");
         	
         	// dto 로 객체화
         	Member member = new Member(0 , id , pwd , name);
         	
         	//객체 (dto) -> dao (db)
         		Dao dao = new Dao();
         		boolean result=dao.signup(member);
         		if(result){
         			response.sendRedirect("main.jsp");
         		}
      %>