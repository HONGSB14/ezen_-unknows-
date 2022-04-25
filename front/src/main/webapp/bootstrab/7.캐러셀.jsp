<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	.carousel-item{width: 100%; height: 100%;}
</style>
</head>
<body>

	<%@include file="1.설치.jsp" %>
	<div i class="container">	<!-- 박스권 -->
		
		<div id="cs" class="carousel slide carousel-fade" data-bs-ride="carousel" data-bs-interval="1500"> <!-- 캐러셀 (1000/1초)-->
			
			<!-- 캐러셀 [내용위치] 하단 버튼 :인디 케이터 -->
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#cs" data-bs-slide-to="0" class="active"></button> <!--사진순서 -->
				<button type="button" data-bs-target="#cs" data-bs-slide-to="1"></button> 
				<button type="button" data-bs-target="#cs" data-bs-slide-to="2"></button> 
			</div>
			
			<div class="carousel-inner">
				<div class="carousel-item active"> <!-- 캐러셀 내용 -->
					<img alt="" src="../덤블도어.jpg">
				</div>
				
				<div class="carousel-item"> <!-- 캐러셀 내용 -->
					<img alt="" src="../덤블도어2.jpg">
				</div>
				
				<div class="carousel-item"> <!-- 캐러셀 내용 -->
					<img alt="" src="../덤블도어3.jpeg">
				</div>
		
				<!-- 캐러셀 이전 버튼 -->
				<button type="button" class="carousel-control-prev" data-bs-target="#cs" data-bs-slide="prev">
					<span class="carousel-control-prev-icon"></span>
				</button>
				<!-- 캐러셀 다음 버튼 -->
				<button type="button" class="carousel-control-next" data-bs-target="#cs" data-bs-slide="next">
					<span class="carousel-control-next-icon"></span>
				</button>

			</div>
		</div>
	</div>
</body>
</html>