<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dto.Slip"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.SlipDao"%>
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
		//세션 값 (회사번호)
		session.getAttribute("cnum");
	 	//일 매출 리스트 호출
	 	ArrayList<Slip> daySaleList= SlipDao.getSlipDao().daysaleadd(cnum);
		ArrayList<Slip> monthSaleList= SlipDao.getSlipDao().monthsaleadd(cnum);
		ArrayList<Slip> yearSaleList= SlipDao.getSlipDao().yearsaleadd(cnum);
	 	DecimalFormat df = new DecimalFormat("#,###");
	 	LocalDateTime now = LocalDateTime.now();
		//시간
	 	String wedate=now.format(DateTimeFormatter.ISO_LOCAL_DATE);
	 	
	 	String month=wedate.split("-")[1];
	 
	 	
	%>
	
	<div class="container text-center">
		 	<div class="col-md-12">
		 		<div class="offset-4 col-md-4">
		 			<h2>월 보 (<%=month%>월)</h2>
		 		</div>
		 	</div>
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
			<table class="table table-center table-bordered" id="mainsale">
				
				<!-- 총 수입 (일) -->
				<tr class="table-info"><th>유량 사용량 (L)</th><th>실입수입 (원)</th><th>카드 수입 (원)</th><th>총 매출 (원)</th><th>실 매출 (원)</th><th>날짜</th></tr>
				<%
					for(Slip slip :daySaleList){
						
						//급여계산
						int pay=(slip.getSdaysale())-(slip.getSdaysale()/10);
						//날짜 가져오기 (월)
						String date= slip.getSdate().split(" ")[0]; //date 자르고 0번째 인덱스 ex))0000-11-22 
						String datemonth=date.split("-")[1];	//월 자르기	짜른 인덱스 에서 "11" 가져오기
						
						
						
						if(month.equals(datemonth)){
				%>			
							<tr>
								<td><%=df.format(slip.getSflux()) %></td>		<!-- 총 유량 일 사용량 -->
								<td><%=df.format(slip.getSfee()) %></td>   		<!-- 실입수입  일 합계 -->
								<td><%=df.format(slip.getScardfee()) %></td>   	<!-- 카드 수입 일 합계 -->
								<td><%=df.format(slip.getSdaysale()) %></td>	<!-- 총 매출 -->
								<td><%=df.format(pay)%></td>					<!-- 실 매출 -->
								<td><%=slip.getSdate() %></td>					<!-- 날짜 -->
							</tr>
				<%
						}
					}
				%>
					<!-- 총 수입 (월)-->
					<tr class="table-info">
					<th class="text-center" colspan="6">합계</th>
					</tr>
				<tr class="table-info"><th>유량 총 사용량</th><th>실입 총 수입</th><th>카드 총 수입</th><th>총 매출 </th><th colspan="2">총 실매출</th></tr>
					
					<%
						for (Slip slip : monthSaleList){
							//급여계산
							int pay=(slip.getSdaysale())-(slip.getSdaysale()/10);
							//날짜 가져오기 (월)
							String date= slip.getSdate().split(" ")[0]; //date 자르고 0번째 인덱스 ex))0000-11-22 
							String datemonth=date.split("-")[1];	//월 자르기	짜른 인덱스 에서 "11" 가져오기
							
							if(month.equals(datemonth)){
					%>
								<tr>
									<td><%=df.format(slip.getSflux()) %></td>		<!-- 총 유량 	월 사용량 -->
									<td><%=df.format(slip.getSfee()) %></td>   		<!-- 실입수입	월 합계 -->
									<td><%=df.format(slip.getScardfee()) %></td>   	<!-- 카드 수입	월 합계 -->
									<td><%=df.format(slip.getSdaysale()) %></td>	<!-- 총 매출 -->
									<td colspan="2"><%=df.format(pay)%></td>		<!-- 실 매출 -->
								</tr>
					<%
							}
						}
					%>
					
					
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