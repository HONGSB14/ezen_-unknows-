<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

</head>


<body>
	
		
	
	<%@include file="header.jsp" %>
	
	<div class="col-md-12 py-5 px-5 row" style="background: #FFFFCC;"> 
		
		<div class="offset-2 col-md-6 text-left py-5">
			<p id="mainp">국내 최초 <br>택시 데이터 프로그램</p>
			<br>
			<p id="mainp2">나의 택시 데이터 경쟁력 TDS와 함께 <Strong>　UP!!　UP!!</Strong> </p>
		</div>
		<div class="col-md-4 px-5">
			<img  id="mainlogo" src="img/mainlogo.png">
		</div>
		
	</div>
	
	<div class="container">
		<div class="text-center py-5">
			<h1><a href="main.jsp">Taxi Company Data System</a></h1>
		</div>
	
		<div class="col-md-12 py-5">
			
				<div id="mainslide" class="carousel slide container carousel-fade" data-bs-ride="carousel"data-bs-interval="3000">
					<div class="carousel-inner">
						<div class="carousel-item active">	
							<img class="d-block w-100" src="img/mainimg1.jpg">
						</div>	
						<div class="carousel-item">
							<img class="d-block w-100" src="img/mainimg2.jpg">
						</div>	
						<div class="carousel-item">
							<img class="d-block w-100" src="img/mainimg3.jpg">
						</div>					
					</div>		
				</div>	
			
			<div class="text-center py-5">
				광고 (캐러셀)
			</div>	
		</div>
	</div>
	<script src="/taxicompany_data_system/js/main.js" type="text/javascript"></script>
	<%@include file ="footer.jsp" %>
	
</body>
</html>