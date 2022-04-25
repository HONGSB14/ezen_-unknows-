<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 현재 위치(파일)에 다른 파일 포함하기 -->
	<%@include file = "header.jsp" %>
	<br><br><br><br><br><br>
	<!--  캐러셀 : 이미지 슬라이드  -->
	<div id="cs" class="carousel slide" data-bs-ride="carousel" data-bs-interval="2000">
								<!-- 슬라이드시간 : 밀리초(1000/1초)  -->
		<!-- 슬라이드 위치 표시 : 인디케이터 -->
		<div class="carousel-indicators">
			<button data-bs-target="#cs" data-bs-slide-to="0" class="active"></button>
			<button data-bs-target="#cs" data-bs-slide-to="1"></button>
			<button data-bs-target="#cs" data-bs-slide-to="2"></button>
			
		</div>
		 <!-- 캐러셀 내용물 -->
		<div class="carousel-inner">
			<div class="carousel-item active"> <img alt="" src="img/main1.jpg"> </div>
			<div class="carousel-item "> <img alt="" src="img/main2.jpg"> </div>
			<div class="carousel-item "> <img alt="" src="img/main3.jpg"> </div>
			
		</div>
		<!-- 이미지 이동 버튼 -->
		<button class="carousel-control-prev" data-bs-target="#cs" data-bs-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</button>
		<button class="carousel-control-next" data-bs-target="#cs" data-bs-slide="next">
			<span class="carousel-control-next-icon"></span>
		</button>
		
	</div> <!-- 캐러셀 end -->
	<br><br>
	
	<div class="container text-center" >
		<hr><!-- 실선 -->
		<h3>new ARRIVAL</h3>
		<p>신상품</p>
	</div>
	
	<div class="container">
		<div class="row">
		<%for(int i=0; i<8; i++) {%>
			<div class=" col-md-3 col-sm-6">
				<div class="card"><!--  카드형식 -->
					<img alt="" src="img/바지튕기기.gif">
					<div class="card-body">
						<p class="item">
						<span class="title">스탠다드 오버 치마핏</span>
						 <br>
						 아이유가 입고다니는 와후와후와후
							<span class="content">[1+1] 맞춤기장 뿅뿅뿅!!
							</span>
							<br>
							<span class="price1">79,000원</span> 
							<span class="price2">75,900원</span>
							<br>
							<span class="badge bg-warning text-dark my-3 ">주문폭주</span>
							<span class="badge bg-warning text-dark my-3 ">품절임박</span>
							
						</p>
					</div>
				</div> 
			</div>
			<%} %>
		</div>
	</div>
	<Br>
	<Br>
	<Br>
	<%@include file = "footer.jsp" %>
	
</body>
</html>