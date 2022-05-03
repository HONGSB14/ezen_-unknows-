<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%@include file = "../header.jsp" %>
	<div class="container">
			<form action="../login" method="post">
				로그인:<input type="text" name="mid">
				<br>
				비밀번호: <input type="password" name="mpassword">
				<input type="submit" value="로그인">	
				<!-- 만약에 로그인 실패 시 -->
					
					<%
					
					String result=request.getParameter("result");
					if(result != null && result.equals("2")){	%>
							<br><span>동일한 회원정보가 없습니다.</span>						
					<%
					}
					%>
				
					
			</form>
	</div>
	<%@include file = "../footer.jsp" %>

</body>
</html>