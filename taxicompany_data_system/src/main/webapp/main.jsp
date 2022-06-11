<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<%@include file="header.jsp" %>
	<%
		session.getAttribute("login");
	%>
			<!-- 광고 란 -->
			<div class="col-md-12 py-5 px-5 row" style="background: #FFFFCC;"> 
				
				<div class="offset-2 col-md-6 text-left py-5">
					<p id="mainp"><strong>"국 내 최 초"</strong> <br>택시 매출정보 데이터 프로그램</p>
					<br>
					<p id="mainp2">나의 데이터 경쟁력 <br><Strong>TDS</Strong> 와 함께　UP!!　UP!! </p>
				</div>
				<div class="col-md-4 px-5">
					<img  id="mainlogo" src="img/mainlogo.png">
				</div>
			</div>
			<!--로그인 시 매출 화면 바로가기 -->
			<div class="text-center py-5">
				<%
					if(login != null){
				%>
						<h4>반갑습니다. <%=login%> 님!!</h4>
						<br>
						<h1><a href="main_sale.jsp">매출 확인 바로 가기</a></h1> 
				<%
					}			
				%>
			</div>
	<div class="container py-5">
		<div class="row py-5">
			<!-- 소개글 -->
			<div class="col-md-6">
				<h2 class="py-2">1.<strong>Why? </strong></h2>
				<h5>택시의 기본 매출을 담당 하는 유일한 국내 택시 데이터 사이트!!</h5>
				<h2 class="py-2">2.<strong>What?</strong></h2>
				<h5>택시의 매출등록 시스템 및 매출차트, 실시간 데이터, 매출출처의 데이터 까지 !! </h5>
				<h2 class="py-2">3.<strong>When?</strong> </h2>
				<h5>남들과는 다르게 <strong>"실시간"</strong> 으로 !!</h5>
				<h2 class="py-2">4.<strong>How?</strong> </h2>
				<h5>보다 <strong>"깔끔한"</strong> 인터페이스로  보다 더 알아보기 쉽게 !!</h5>
				<br>
				<h1 class="py-5"><a href="/taxicompany_data_system/company/companysignup.jsp">데이터로 회사의 경쟁력을 높이고 싶다면 ??</a></h1>
			</div>
			<!-- 이미지 (.gif) -->
			<div class="col-md-6 text-center py-3">
				<img class="img-fluid rounded float-end" alt="maindata" src="img/maindata.gif">
			</div>
		</div>
	</div>
	<!-- 매출확인 바로가기 -->
	<div class="container">
		<!-- 캐러셀 이미지 -->
		<div class="col-md-12 py-3">
			
				<div id="mainslide" class="carousel slide container carousel-fade" data-bs-ride="carousel"data-bs-interval="2000">
					<div class="carousel-inner">
						<div class="carousel-item active">	
							<img class="d-block w-100 img-fluid" src="img/mainimg1.jpg">
						</div>	
						<div class="carousel-item">
							<img class="d-block w-100 img-fluid" src="img/mainimg2.jpg">
						</div>	
						<div class="carousel-item">
							<img class="d-block w-100 img-fluid" src="img/mainimg3.jpg">
						</div>					
					</div>		
				</div>	
			<!-- 광고 -->
			<div class="text-center py-5">
				광고 (캐러셀)
			</div>
			
		</div>
	</div>
	<script src="/taxicompany_data_system/js/main.js" type="text/javascript"></script>
	<%@include file ="footer.jsp" %>
	
</body>
</html>