<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


  <% 
   String 비밀번호=request.getParameter("id");
   System.out.print("html에서 아이디 요청:  "+비밀번호);
   %>
<% 
   String 아이디=request.getParameter("password");
   System.out.print("html에서 비밀번호 요청: "+아이디);
   %>
   
 
  
