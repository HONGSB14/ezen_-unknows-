<%@page import="dao.CarDao"%>
<%@page import="dto.Car"%>
<%@page import="java.util.ArrayList"%>
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
		ArrayList<Car> carList = CarDao.getcarDao().carlist(cnum); 
	%>
	<div class="Container">
		<!-- 기사 등록 란  -->
		<div id="driverSignup" class="offset-4 col-md-4 py-5">
			<div class="text-center">
				<h1>차량등록</h1>
			</div>
			<form method="get" action="../car/CarResistration" id="carResistrationForm">
				<input type="hidden" name="cnum" value="<%=cnum%>">
				
				<table class="table table-warning">
					<tr><th>차 번호</th> <th>차량 종류</th> <th>차량 이름</th> <th>연료 종류</th> <tr>
					<tr>
						
						<!-- 차량 번호 기입 란 -->
						<td><input type="text" class="form-control" id="carnum" name="carnum"></td>
						
						<!-- 차량 종류 선택 란 -->
						<td>
							<select class="form-select" id="selectCarType" name="selectCarType">
								<option value="세단">세단</option>
								<option value="왜건">왜건</option>
								<option value="SUV">SUV</option>
								<option value="해치백">해치백</option>
								<option value="리무진">리무진</option>
								<option value="벤">벤</option>
								<option value="전기차">전기차</option>
							</select>
						</td>
						
						<!-- 차량 이름 기입란 -->
						<td><input type="text" class="form-control" id="carname" name="carname"></td>
						
						<!-- 차량 연료종류 선택 란 -->
						<td>
							<select class="form-select" id="selectFuel" name="selectFuel">
								<option value="가솔린">휘발유 (가솔린)</option>
								<option value="다젤">경유 (디젤)</option>
								<option value="가스">가스 (LPG)</option>
								<option value="전기">전기 ( Electric )</option>
							</select>
						 </td>
						 
					</tr>
				</table>
				<div class="text-center">
					<span id="carcheck"></span>	
					<span id="carcheck2"></span>	
				</div>
			</form>	
			<!-- 차량 등록 버튼 -->
			<div class="offset-4 col-md-4 py-2">
				<button class="form-control" onclick="carResistration()" type="button">차량 등록</button>
			</div>
		</div>
		<!-- 테이블 표 -->
		<div class="col-md-12">
			<div class="offset-4 col-md-4">
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
	
	<script src="/taxicompany_data_system/js/carResistration.js" type="text/javascript"></script>
	
	<%@include file ="../footer.jsp" %>
	
</body>
</html>