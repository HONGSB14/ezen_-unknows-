<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
		<%
		String mid =request.getParameter("mid");	
		%>
		<%@include file = "../header.jsp" %>
		
		<div class="container py-5">
			<div class="col-md-12 py-5">
				<div class="text-center py-3">
					<a href="../main.jsp"><h1>Taxi Company Data System</h1></a>
				</div>
				<div class="offset-4 col-md-4 py-5">
					
					<form action="../member/PwdUpdate" method="post" id="pwdupdate">
						<input type="hidden" name="mid" value="<%=mid%>">
						<div class="py-2">
							비밀번호 변경
							<input type="password" class="form-control" id="password" placeholder="변경할 비밀번호를 입력해주세요.">
						</div>
						<span id="pwdcheck"></span>
						<span id="pwdcheck2" class="check"></span>
						<div class="py-2">
							비밀번호 확인
							<input type="password" class="form-control" id="passwordcheck" name="pwd" placeholder="변경할 비밀번호를 다시 한번 입력해주세요.">
						</div>
						<span id="pwdcheck3"></span>
						<span id="pwdcheck4" class="check"></span>
						<div class="py-5">
							<button class="form-control py-3" onclick="pwdupdate()" type="button">변경하기</button>	
						</div>
					</form>
					
				</div>
			</div>
		</div>
		<script src="../js/pwdupdate.js" type="text/javascript"></script>
</body>
</html>