<%@page import="dto.Driver"%>
<%@page import="dao.DriverDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%@include file="../header.jsp" %>
	
	<%
		session.getAttribute("cnum");	
		//드라이버 리스트 불러오기
		ArrayList<Driver> driverlist = DriverDao.getDriverDao().driverlist(cnum);
	%>
	<div class="Container">
		<!-- 기사 등록 란  -->
		<div id="driverSignup" class="offset-4 col-md-4 py-5">
			<div class="text-center">
				<h1>기사등록</h1>
			</div>
			<form method="get" action="../driver/DriverSignup" id="driverSignupForm">
				<input type="hidden" name="cnum" value="<%=cnum%>">
				<table class="table table-warning">
					<tr><th>이름</th> <th>기타사항</th> <tr>
					<tr>
					<td><input type="text" class="form-control" id="dname" name="dname"></td>
					<td><input type="text" class="form-control" id="dnote" name="dnote"></td>
					</tr>
				</table>
				<div class="text-center">
				<span id="namecheck"></span>	
				<span id="namecheck2"></span>	
				</div>
			</form>	
			<!-- 기사 등록 버튼 -->
			<div class="offset-4 col-md-4 py-2">
				<button class="form-control" onclick="driverSignup()" type="button">기사 등록</button>
			</div>
		</div>
		<!-- 테이블 표 -->
		<div class="col-md-12">
			<div class="offset-4 col-md-4">
				<table class="table table-bordered">
					<tr class="table-warning"><th>운전자 ID</th><th>운전자 이름</th><th>기타 사항</th><th>등록 날짜</th></tr>
					<%
						for(Driver driver : driverlist){
						
						
					%>
							<tr>
								<td><%=driver.getDnum()%></td>
								<td><%=driver.getDname()%></td>
								<td><%=driver.getDnote()%></td>
								<td><%=driver.getDdate().split(" ")[0]%></td>
							</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
	</div>
	
	<script src="/taxicompany_data_system/js/driversignup.js" type="text/javascript"></script>
	
	
	
	<%@include file ="../footer.jsp" %>
</body>
</html>