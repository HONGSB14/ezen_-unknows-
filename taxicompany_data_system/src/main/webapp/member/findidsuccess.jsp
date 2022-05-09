<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%
		String mid=request.getParameter("mid");
		String mname=request.getParameter("mname");
	%>
	<%@include file="../header.jsp" %>
	
	<div class="container">
		<div class="col-md-12">
			
			<%
				if(mid != null){
			%>	
				<div class="container">
					<!--  꾸미기 추가 요망 -->
					<div class="col-md-12 text-center py-3">
						<div class="py-3">
						<a href="../main.jsp"><h1>Taxi Company Data System</h1></a>
						</div>
						<div class="py-5">
							<h1>아이디 정보</h1>
							<div class="offset-4 col-md-4 py-2">
								<div class="py-3">
										<div class="py-5">
											<H3>어서오세요 <%=mname%>님!! </H3>
										</div>
									<%=mname%> 회원님이 가입하신 아이디는 "  <%=mid%>  " 입니다.
								</div>
								<div class="py-5">
									<a href="../member/login.jsp"><button class="form-control">확인</button></a>
								</div>		
							</div>
						</div>
						
					</div>
				</div>
	
			<%
				}
			%>
			
			
		</div>
	</div>
</body>
</html>