<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dto.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DriverDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%@include file="../header.jsp"%>
	<% 
		//세션값
		session.getAttribute("cnum");	
		//리스트 객체
		ArrayList<Driver> driverlist= DriverDao.getDriverDao().driverlist(cnum); 
		//날짜 객체 생성
		Date date = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date);
	%>
	<div class="container">
		<!-- 테이블 표 -->
		<div class="col-md-12 py-5 my-5">
			<!-- Driver List 안내문구 -->
			<div class="col-md-12 text-center">
				<h1>Driver List</h1>
			</div>	
			<div class="offset-4 col-md-4 py-5 my-5">
				<h5>현재날짜:<%=today%></h5>
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
	
	<%@include file="../footer.jsp"%>
</body>
</html>