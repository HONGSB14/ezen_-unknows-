<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dto.Car"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CarDao"%>
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
		ArrayList<Car> carList= CarDao.getcarDao().carlist(cnum);
		//날짜 객체 생성
		Date date = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date);
	%>
	<div class="container">
	
		<!-- 테이블 표 -->
		<div class="col-md-12 py-5 my-5">
			<!-- Car List 안내문구 -->
			<div class="col-md-12 text-center">
					<h1>Car List</h1>
			</div>	
			<div class="offset-4 col-md-4 py-5 my-5">
				<h5>현재날짜:<%=today%></h5>
				<table class="table table-bordered">
					<tr class="table-warning"><th>차 번호</th> <th>차량 종류</th> <th>차량 이름</th> <th>연료 종류</th></tr>
					<%
						for(Car car :carList){	
					%>
						<tr>
							<td><%=car.getCarNum() %></td>
							<td><%=car.getCarType() %></td>
							<td><%=car.getCarName() %></td>
							<td><%=car.getFuelType() %></td>
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