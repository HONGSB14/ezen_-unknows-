<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>

	<%@include file = "../header.jsp" %>
	<%
		session.getAttribute("cnum");
	%>
	
	<div class="container text-center">
		 <!-- 등록 버튼-->
		 <div class="row">	
			<div class="col-md-2">
			<span>company. <%=cnum %></span>
			</div>
			<div class=" offset-8 col-md-1">
			<a href="sale/sales_registration.jsp"><button class="form-control">매출 등록</button></a>	
			</div>
			<div class="col-md-1">
			<a href="sale/driver_registration.jsp"><button class="form-control">기사 등록</button></a>	
			</div>
		</div>
		<!-- 테이블 표 -->
		<div class="col-md-12">
			<table class="table table-center table-bordered">
				<tr class="table-info"><th>차 번호</th><th>유량</th><th>실입금액</th><th>신용카드</th><th>일 매출</th><th>비고</th></tr>
				<!-- for 문으로 등록  -->
				<tr><td>1653</td><td>29.270</td><td>131,000원</td><td>6,900원</td><td>137,900원</td><td></td></tr>
			</table>
		
		</div>
		
		<div class="col-md-12 row ">
			
			<div class="col-md-4">
				<div class="offset-4 col-md-4">
				137,900원
				</div>
				Week (pie Chart) 
			</div>
			
			<div class="col-md-4">
				<div class="offset-4 col-md-4">
					764,564,430원
				</div>
				month(Bar Chart)
			</div>
			
			<div class="col-md-4">
				<div class="offset-4 col-md-4">
					793,482,246,246원
					</div>
				year(Line Chart)
			</div>
		</div>
	</div>
	
	<%@include file ="footer.jsp" %>
</body>
</html>