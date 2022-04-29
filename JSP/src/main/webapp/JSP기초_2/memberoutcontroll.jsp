<%@page import="dto.Member"%>
<%@page import="dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
	String pwd = request.getParameter("pwd");
	Dao memberdao = new Dao();
	Member member = new Member();
%>

