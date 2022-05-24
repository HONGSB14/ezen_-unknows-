<%@page import="dao.TacometerDao"%>
<%@page import="dto.Tacometer"%>
<%@page import="dto.Car"%>
<%@page import="dao.CarDao"%>
<%@page import="dto.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DriverDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">

</style>

</head>
<body>
		
	
		<%@include file ="header.jsp" %>
		
		<%
			session.getAttribute("cnum");
			ArrayList<Driver> driverlist=DriverDao.getDriverDao().driverlist(cnum);
			
			ArrayList<Car> carlisList = CarDao.getcarDao().carlist(cnum);
			
			
		%>
		
	 
	<div class="container text-center">
	

		<div class="col-md-12">
				
				<!-- ---------------------------------------------------회사 안내----------------------------------------------------- -->
				<div class="col-md-3">
					<span><strong id="welcome">welcome!!</strong>　company. <%=cnum%></span>
				</div>
				<!-- -------------------------------------------------------기사 등록----------------------------------------------------->
				<div class="col-md-12">
					<div class="row">
						<div class="offset-8 col-md-2 py-2 text-left">
							<a href="/taxicompany_data_system/driver/driver_registration.jsp"><button class="form-control">기사등록</button></a>
						</div>
						<div class="col-md-2 py-2">
							<a href="/taxicompany_data_system/car/carResistration.jsp"><button class="form-control">차량등록</button></a>
						</div>
					</div>
				</div>
				
				
			
		
			<!-- 지도 -->
			<div id="map" style="width:100%;height:350px;">
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=77754e23f7d3787ff007ce519a9c1d86"> </script>
				<!-- 오버레이 -->
				<div class="customoverlay" id="customoverlay">
					<a href="https://map.kakao.com/link/map/11394059" target="_blank">
					<!--인포인터 ( 차량 번호 가져오기) -->
					<span id="carnum">
						
					</span>
					</a>
				</div>
			</div>
			<!------------------------------------------------ 운전자 정보---------------------------------------------------------------->
				<div class="col-md-12 row">
					
					<!-- 운전자 상세정보 -->
					<div class="col-md-4" >
						Driver info
						<!--  드라이버 정보 가져오기  -->
						<div id="driverinfo">
							
						</div>
					</div>
					<div class="col-md-4 text-center py-5 row">
						<img src="/taxicompany_data_system/img/taxidriving.gif">
					</div>
					<!-- 운전자 일 매출 차트 -->
					<div class=" col-md-4">
						Driver Day Sales graph
						<!--  드라이버 매출정보 가져오기  -->
						<div id="driversaleinfo">
							
						</div>
					</div>
				</div>
				
				<!---------------------------------------------------------------- 현재 운행 리스트----------------------------------------------  -->
				<div class="col-md-12 row">
					<h1>현재 운행중인 차량 정보</h1>
				</div>
				
						
				<div class="col-md-12">
					<table class="table table-bordered">
						<tr class="table-secondary"><th>ID</th><th>이름</th><th>차량번호</th><th>기타사항</th><th>출차 시간</th></tr>
						<tr>
							<td>543534</td>
							<td>홍길동</td>
							<td>123가1234</td>
							<td>기타사항</td>
							<td>12:00~</td>
						</tr>
					</table>
				</div>
		
	  	<script src="js/maininfo.js" type="text/javascript"></script>
		<%@include file ="footer.jsp" %>
		
		
		</div>
	</div>
	
	
	
</body>
</html>