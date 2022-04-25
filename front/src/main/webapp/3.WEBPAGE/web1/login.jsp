<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%@include file="header.jsp"%> 
   
   <%@include file="mainimage.jsp"%> 
   
   	<div id="contents">
   		  <p style="text-align: center; font-size: 30px; font-weight: bolder; ">로그인</p>
			  <table style="margin: 0 auto;" >
			  	<tr><td>아이디</td><td><input type="text"></td><td rowspan="2"><button style="height: 65px ; width: 70px;">로그인</button></td></tr>
			  	<tr><td>비밀번호</td><td><input type="password"></td></tr>	  
			  </table>
			  

			   	
   	</div>
   
   <%@include file="footer.jsp" %>
   
</body>
</html>