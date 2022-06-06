<%@page import="dto.Slip"%>
<%@page import="dao.SaleDao"%>
<%@page import="java.util.ArrayList"%>
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
	String current=request.getParameter("current");
	ArrayList<Slip> searchList = SaleDao.getsaleDao().searchList(cnum,current);
%>

	<div class="container">
		<div class="container">
		<!----------------------------------------------------- 매출등록 란 ------------------------------------------>
		<div class="col-md-12 text-center">
			<a href="/taxicompany_data_system/main_sale.jsp"><h1>SALES</h1></a>
				<!-- 매출등록 헤더 -->
				<div class="row">
					<div class="col-md-2">
					<!-- 검색 날짜 표시-->
					<h3><%=current%></h3>
					</div>
					<div class="text-center">
					<h3>일보 검색 내역</h3>
					</div>
				</div>
				
		</div>
		<!------------------------------------------- 운송일보 표-------------------------------------------->
		<div class="col-md-12 text-center py-5">
				<h3 class="text-center">운송 일보</h3>
			<table class="table table-center table-bordered table-hover">
					<tr class="table-info"><th>차 번호</th><th>유량(L)</th><th>실입금액(원)</th><th>카드수입(원)</th><th>일 매출(원)</th><th>비고</th><th>날짜</th></tr>
					<%
							for(Slip slip :searchList){		
					%>
								<tr>
									<td><%=slip.getCarnum()%></td>
									<td><%=slip.getSflux()%></td>
									<td><%=slip.getSfee() %></td>
									<td><%=slip.getScardfee() %></td>
									<td><%=slip.getSdaysale() %></td>
									<td><%=slip.getSnote()%></td>
									<td><%=slip.getSdate() %></td>
								</tr>
					<%	
							}
					%>
					
			</table>
		</div>
	</div>
	
	<%@include file ="../footer.jsp" %>
	<script src="../js/slip.js" type="text/javascript"></script>
	</div>
</body>
</html>