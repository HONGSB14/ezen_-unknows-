<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%@include file = "../header.jsp" %>
	<div class="container">
			
			<div class="col-md-12 text-center">
				<div class="offset-4 col-md-4">
					<form action="../login" method="post">
					
					<div class="col-md-4"> <!-- 아이디 -->
							아이디<input type="text" name="mid">
					</div>
					
					<div class="col-md-4">
						비밀번호 <input type="password" name="mpassword">
						<input type="submit" value="로그인">	
						<!-- 만약에 로그인 실패 시 -->
						
						<%
						String result=request.getParameter("result");	//login.servlet 에서 result 값을 받아옴
						
						if(result != null && result.equals("2")){	%><!-- result 값이 2 라면 알림창 -->	
							<br><span>동일한 회원정보가 없습니다.</span>						
						<%
						}
						%>	
					</div>
				
			
					
				</form>
				</div>
				
			</div>
			
				
				
	</div>
	<%@include file = "../footer.jsp" %>

</body>
</html>