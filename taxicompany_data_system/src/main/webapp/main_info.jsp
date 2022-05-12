<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



</head>
<body>
	<%
		session.getAttribute("cnum");
	%>
	
	<%@include file = "../header.jsp" %>
	
	<div class="container text-center">
		
		<div class="col-md-12">
			<div class="col-md-2">
			<span>welcome company. <%=cnum %></span>
			</div>
			<!-- 지도 -->
			<div id="map" style="width:100%;height:350px;">
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=77754e23f7d3787ff007ce519a9c1d86"> </script>
				<!-- 오버레이 -->
				<div class="customoverlay" id="customoverlay">
					<a href="https://map.kakao.com/link/map/11394059" target="_blank">
					<span class="title">3159</span>
					</a>
				</div>
			</div>
			<!-- 운전자 정보 -->
				<div class="col-md-12 row">
					<!-- 운전자 상세정보 -->
					<div class="col-md-5" >
						Driver Info 
					</div>
					<div class="col-md-2"></div>
					<!-- 운전자 일 매출 차트 -->
					<div class="col-md-5">
						Driver Day Sales graph
					</div>
					
				</div>
		
	   <script src="js/maininfo.js" type="text/javascript"></script>
		<%@include file ="footer.jsp" %>
		
		
		</div>
	</div>
	
	
	
</body>
</html>