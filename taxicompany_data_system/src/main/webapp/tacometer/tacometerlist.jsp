<%@page import="dto.Tacometer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.TacometerDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../header.jsp" %>
<%
	session.getAttribute("cnum");
	
	Date date =new Date();
	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
	String today=sdf.format(date);
	ArrayList<Tacometer> tacoList= TacometerDao.gettacometerDao().getTacoList(cnum);
%>
<div class="container">
	<div class="col-md-12 text-center py-5">
		<h1>TACOMETER LIST</h1>
		<!-- 오늘 날짜 -->
		<div class="col-md-2">
		<h3><%=today%></h3>
		</div>
		<!-- 타코미터 리스트 -->
		<table class="table">
			<tr class="table-info"><th>차량 번호</th><th>총 주행거리 (KM)</th><th>주행요금 (원)</th><th>기사이름</th><th>기사ID</th><th>등록 시간</th></tr>
			<%
				for(Tacometer tacometer : tacoList){
					
					if(tacometer.getTdate().split(" ")[0].equals(today)){
			%>
					<tr>
						<td><%=tacometer.getCarNum()%></td>
						<td><%=tacometer.getTotalMileage() %></td>
						<td><%=tacometer.getMileageFee() %></td>
						<td><%=tacometer.getDname() %></td>
						<td><%=tacometer.getDnum() %></td>
						<td><%=tacometer.getTdate().split(" ")[1]%></td>
					</tr>
			<% 		
					}
				}
			%>
		</table>
	
	
	
	
	</div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>