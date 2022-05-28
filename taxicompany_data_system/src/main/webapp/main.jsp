<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<body>
	
	
	
		
	
	<%@include file="header.jsp" %>
	<%
		session.getAttribute("login");
	%>
	<div class="container py-5">
		<div class="row py-5">
			<!-- 소개글 -->
			<div class="col-md-6 text-center">
				안녕하세요?
			</div>
			<!-- 이미지 (gif) -->
			<div class="col-md-6 text-center py-3">
				<img class="img-fluid rounded float-end" alt="maindata" src="img/maindata.gif">
			</div>
		</div>
	</div>
	<div class="col-md-12 py-5 px-5 row" style="background: #FFFFCC;"> 
		
		<div class="offset-2 col-md-6 text-left py-5">
			<p id="mainp">국내 최초 <br>택시 데이터 프로그램</p>
			<br>
			<p id="mainp2">나의 택시 데이터 경쟁력 <br><Strong>TDS</Strong> 와 함께　UP!!　UP!! </p>
		</div>
		<div class="col-md-4 px-5">
			<img  id="mainlogo" src="img/mainlogo.png">
		</div>
		
	</div>
	<!-- 매출확인 바로가기 -->
	<div class="container">
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
		<!-- 캐러셀 이미지 -->
		<div class="col-md-12 py-5">
			
				<div id="mainslide" class="carousel slide container carousel-fade " data-bs-ride="carousel"data-bs-interval="3000">
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