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
   		
   		<hr>
  <table style="margin: auto; font-size: 20px;">
  <tr><td>아이디</td><td><input type="text"></input></td></tr>
  <tr><td>비밀번호</td><td><input type="password"></input></td></tr>
  <tr><td>비밀번호 확인</td><td><input type="password"></input></td></tr>
  <tr><td>이름</td><td><input type="text"></input></td></tr>
  <tr><td>이메일</td><td><input type="text">@<select style="font-size: 20px"><option>직접입력</option><option>nate.com</option><option>naver.com</option></select></td></tr>
  <tr><td>이메일 수신</td> <td> <input type="radio" name="sns">예<input type="radio" name="sns">아니오</td></tr>
  <tr><td>가입 경로</td> <td> <input type="checkbox" name="친구추천">친구추천 <input type="checkbox" name="인터넷">인터넷 <input type="checkbox" name="기타">기타</td></tr>
  <tr><td>인사말</td> <td> <textarea rows="5" cols="40"></textarea> </td> </tr>
  </table>

   	</div>
   
   <%@include file="footer.jsp" %>

</body>
</html>