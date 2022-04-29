<%@page import="dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
 

int bno= Integer.parseInt(request.getParameter("bno"));

Dao dao = new Dao();

boolean result = dao.ddelete(bno);

if(result){
	
	response.sendRedirect("main.jsp");
}

%>