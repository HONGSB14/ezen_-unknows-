<%@page import="dao.SaleDao"%>
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
</head>
<body>
		
	
		<%@include file ="header.jsp" %>
		
		<%
			session.getAttribute("cnum");
			ArrayList<Driver> driverlist=DriverDao.getDriverDao().driverlist(cnum);
			ArrayList<Car> carlisList = CarDao.getcarDao().carlist(cnum);
			String priceCheck=SaleDao.getsaleDao().pricecheck(cnum);
			
		%>
	

		<!--회사번호 -->
		<input type="hidden" value="<%=cnum%>" id="cnum">
	 	
	
	<div class="container text-center">
	

		<div class="col-md-12">
				
				<!-- ---------------------------------------------------회사 안내----------------------------------------------------- -->
				<div class="col-md-3">
					<span><strong id="welcome">welcome!!</strong>　company. <%=cnum%></span>
				</div>
				<!-- -------------------------------------------------------기사 등록----------------------------------------------------->
				<div class="col-md-12">
					<div class="row">
						<div class="offset-4 col-md-2 py-2 text-left">
							<a href="/taxicompany_data_system/driver/driver_registration.jsp"><button class="form-control">기사 등록</button></a>
						</div>
						<div class="col-md-2 py-2">
							<a href="/taxicompany_data_system/driver/driverlist.jsp"><button class="form-control">기사 목록보기</button></a>
						</div>
						<div class="col-md-2 py-2">
							<a href="/taxicompany_data_system/car/carResistration.jsp"><button class="form-control">차량 등록</button></a>
						</div>
						<div class="col-md-2 py-2">
							<a href="/taxicompany_data_system/car/carlist.jsp"><button class="form-control">차량 목록보기</button></a>
						</div>
						
					</div>
				</div>
			<!-- 안내 문구 -->
			
			
			<!-- 지도 -->
			<div id="map" style="width:100%;height:700px;">
				<!-- 로딩 스피너 -->
				<div class="my-5 py-5">
					<div class="spinner-border text-warning" role="status"></div>
				</div>
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=77754e23f7d3787ff007ce519a9c1d86&libraries=services,clusterer,drawing"></script>
			</div>
			<span id="mapinfo">	</span>
			<!--------------------------------------------------------------- 중간---------------------------------------------------------------->
				<div class="col-md-12 row">
					
					
					<div class="offset-4 col-md-4 text-center py-5 row">
						<img src="/taxicompany_data_system/img/taxidriving.gif">
					</div>
				
				</div>
				<%
					if(priceCheck.equals("3")){
				%>
				<!---------------------------------------------------------------- 현재 운행 리스트-------------------------------------------------->
				<div class="col-md-12 row">
					<h1>탑승 위치 데이터</h1>
					<!-- 통계 지도  버튼-->
					<div class="row py-5">
						<div class="col-md-4">
							<button onclick="onWeek('<%=cnum%>')" class="form-control">데이터 보기 (1주)</button>
						</div>
						<div class="col-md-4">
							<button onclick="onMonth('<%=cnum%>')" class="form-control">데이터 보기 (1달)</button>
						</div>
						<div  class="col-md-4">
							<button onclick="onYear('<%=cnum%>')" class="form-control">데이터 보기 (1년)</button>
						</div>
					</div>
					
					<!--지도 생성-->
					<div id="mapdata" style="width:100%;height:700px;">
						<div class=" py-5 my-5 text-center" id="info">
							<h4>상단 버튼을 클릭하면 지도가 나타납니다.</h4>
						</div>
					</div>
					
				
				</div>
			
			<% 		
				 }
			%>
		</div>
	</div>
	<script src="js/maininfo.js" type="text/javascript"></script>
	<%@include file ="footer.jsp" %>
	
</body>
</html>