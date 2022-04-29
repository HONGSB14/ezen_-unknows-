<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <% 
    	session.setAttribute("loginid", null);
    	response.sendRedirect("main.jsp");
    %>