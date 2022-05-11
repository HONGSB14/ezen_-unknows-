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
			<span>company. <%=cnum %></span>
			</div>
			<!-- 지도 -->
			<div id="map" class="col-md-12">
				지도
			</div>
			<!-- 운전자 정보 -->
				<div class="col-md-12 row">
					<!-- 운전자 상세정보 -->
					<div class="col-md-6">
						Driver Info 
					</div>
					<!-- 운전자 일 매출 차트 -->
					<div class="col-md-6">
						Driver Day Sales graph
					</div>
					
				</div>
		
			   
		<%@include file ="footer.jsp" %>
		</div>
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=77754e23f7d3787ff007ce519a9c1d86"></script>
	<script src="../js/main.js" type="text/javascript"></script>
	
</body>
</html>