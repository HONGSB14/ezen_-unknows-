<%@page import="dao.ApproveCarDao"%>
<%@page import="dto.ApproveCar"%>
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
		ArrayList<ApproveCar> approveCarList=ApproveCarDao.getApproveCarDao().approveCarList();
	%>
	<div class="Container">
		<!-- 차량 등록 란  -->
		<div id="driverSignup" class="offset-4 col-md-4 py-5">
		
			<div class="text-center">
				<h1>차량등록</h1>
			</div>
		
			<form method="get" action="../car/CarResistration" id="carResistrationForm">
				<input type="hidden" name="cnum" value="<%=cnum%>">
				<!-- 차량 등록 버튼 -->
				<div class="row">
				<div class="offset-10 col-md-2 py-2">
					<button class="form-control" type="submit" type="button">차량 등록</button>
				</div>
				
				</div>
				<table class="table table-warning text-center">
					<tr><th>차 번호</th> <th>차량 종류</th> <th>차량 이름</th> <th>연료 종류</th> <tr>
					<tr>
					
						
						<!-- 차량 번호 기입 란 -->
						<td>
							<select class="form-select" id="selectCarNum" name="selectCarNum">
							<%
								for(ApproveCar approveCar : approveCarList){
							%>
								<option value="<%=approveCar.getApCarNum()%>"><%=approveCar.getApCarNum()%></option>
							<%		
								}
							%>
							</select>
						</td>
						
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
			</form>	
		</div>
		<!-- 등록 된 차량 리스트 테이블 표 -->
		<div class="col-md-12">
			<div class="offset-4 col-md-4">
				<table class="table table-bordered text-center">
					<tr class="table-warning"><th>선택</th><th>차 번호</th> <th>차량 종류</th> <th>차량 이름</th> <th>연료 종류</th></tr>
					<%
						for(Car car :carList){	
					%>
						<tr>
							<td><input type="checkbox" class="form-check-input text-center" id="deletecar" value="<%=car.getCarNum()%>"></td>
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
		<!-- 승인 차량 테이블 표  -->
		<div class="text-center py-5">
			<h1>승인 차량 리스트</h1>
		</div>
		
		<div class="col-md-12">
			<div class="offset-4 col-md-4">
				<table class="table table-boarded text-center">
					<tr class="table-warning"><th>차량번호  (사용가능)</th><th>차량 아이디</th></tr>
					<%
						for(ApproveCar approveCar : approveCarList){ 
					%>
					<tr>
						<td><%=approveCar.getApCarNum()%></td>
						<td><%=approveCar.getApCarId() %></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
			
	</div>

	<%@include file ="../footer.jsp" %>
	
</body>
</html>