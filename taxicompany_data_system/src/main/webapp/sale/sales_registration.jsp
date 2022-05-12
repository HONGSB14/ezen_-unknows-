
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file = "../header.jsp" %>
	<%
		//세션 값
		session.getAttribute("cnum");	
		//날짜 계산
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		
	%>
	<div class="container">
		<!----------------------------------------------------- 매출등록 란 ------------------------------------------>
		<div class="col-md-12 text-center">
				<!-- 매출등록 헤더 -->
				<div class="row">
					<div class="col-md-2">
					<h6> <%=today %></h6>
					</div>
					<div class="offset-2 col-md-4">
					<h3>일보 등록</h3>
					</div>
				</div>
	
				<!-- 매출정보 기입 란 -->
				<div class="col-md-12">
					<form action="../slip/SalesRegistration" id="saleform" method="get">
						<!-- 회사 번호 넘기기 (hidden) -->
						<input type="hidden" name="cnum" value="<%=cnum%>">
						<div class="row">
							<table class="table">
								<tr class="table-info"><th>차 번호</th><th>유량</th><th>실입금액</th><th>신용카드</th><th>일 매출</th><th>비고</th></tr>
								<tr>  	
									<td><input type="text" name="carnum" id="carnum" class="form-control"></td> 
									<td> <input type="text" name="flux" id="flux"  class="form-control" autofocus="autofocus"></td> 
									<td><input type="text" name="fee" id="fee"  class="form-control"></td >
									<td><input type="text" name="cardfee" id="cardfee"   class="form-control"></td> 
									<td> <input type="text" name="daysale" id="daysale"  class="form-control"></td> 
									<td> <input type="text" name="note" id="note"  class="form-control"></td> 
								</tr>
							</table>
						</div>
						<!-- 등록/취소 (버튼) -->
						<div class="offset-4 col-md-4 row">
							<div class="offset-3 col-md-3">
							 <input class="form-control" onclick="salecheck()" type="submit" value="등록">
							</div>
							<div class="col-md-3">
								<input class="form-control" type="reset" value="취소">
							</div>
						</div>	
					</form>
				</div>
		</div>
		
		
		
		<!------------------------------------------- 운송일보 표 (날짜별로 보는게 가능할까?) ------------------------------------------>
		<div class="col-md-12 text-center py-3">
				<h3>운송 일보</h3>
			<table class="table table-center table-bordered">
					<tr class="table-info"><th>차 번호</th><th>유량</th><th>실입금액</th><th>신용카드</th><th>일 매출</th><th>비고</th><th>날짜</th></tr>
					<!-- for 문으로 등록  -->
					<tr>
						<td>1598</td>		<!-- 차 번호 -->
						<td>29.270</td>		<!-- 유량 -->
						<td>131,000원</td>	<!-- 실입금액 -->
						<td>6,900원</td>		<!-- 신용카드 -->
						<td>137,900원</td>	<!-- 일 매출-->
						<td>밥먹다 체함</td> 	<!-- 비고  -->
						<td>2022-05-12</td> <!-- 날짜 -->
					</tr>
			</table>
		</div>
	</div>
	
	<%@include file ="../footer.jsp" %>
	<script src="../js/sales.js" type="text/javascript"></script>
</body>
</html>