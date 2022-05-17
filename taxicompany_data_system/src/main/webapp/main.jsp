<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">


</style>
</head>


<body>
	
		
	
	<%@include file="header.jsp" %>
	
	<div class="col-md-12 py-5 px-5 row">
		
		<div class="offset-2 col-md-6 text-left py-5">
			<p style="font-size: 55px;">국내 최초 <BR>택시 데이터 프로그램
			</p> 		
		</div>
		<div class="col-md-4 px-5">
			<img style="width: 100%" src="img/maingif.gif">
		</div>

	</div>
	
	<div class="container">
		<div class="text-center">
			<h1><a href="main.jsp">Taxi Company Data System</a></h1>
		</div>
	
		<div class="col-md-12 py-5">
			<div class="">
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
			</div>
			<div class="text-center py-5">
				광고 (캐러셀)
			</div>	
		</div>
	</div>
	
	<%@include file ="footer.jsp" %>
	
</body>
</html>