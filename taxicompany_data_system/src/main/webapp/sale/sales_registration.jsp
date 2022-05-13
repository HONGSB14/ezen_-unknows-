
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.LocalDate"%>
<%@page import="dto.Slip"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.SlipDao"%>
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
		//페이지 반환
		String result=request.getParameter("result");
		//테이블 뷰 리스트 생성 
		ArrayList<Slip> sliplist= SlipDao.getSlipDao().sliplist(cnum);
		//천 자리 표현식
		DecimalFormat df = new DecimalFormat("#,###");
		LocalDateTime now = LocalDateTime.now();
		
		int time=now.getHour();
	%>
	
	<div class="container">
		<!----------------------------------------------------- 매출등록 란 ------------------------------------------>
		<div class="col-md-12 text-center">
			<a href="/taxicompany_data_system/main_sale.jsp"><h1>SALES</h1></a>
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
						<!-- 매출정보 뷰 -->
						<div class="row">
							<table class="table">
								<tr class="table-info"><th>차 번호</th><th>유량(L)</th><th>실입금액(원)</th><th>카드수입(원)</th><th>일 매출(원)</th><th>비고</th></tr>
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
						<div class="col-md-12">
							<% 
								if(result !=null){	
							%>
								<span class="check">기입하신 정보는 등록 할 수 없습니다. 다시한번 확인해 주십시오.</span>
							<%
								}else{
							%>
								<span>　</span>
							<%
								}
							%>
						</div>
						<!-- 등록/취소 (버튼) -->
						<div class="offset-4 col-md-4 row">
							<!-- 등록버튼 -->
							<div class="offset-3 col-md-3">
							 <input class="form-control" onclick="salecheck()" type="submit" value="등록">
							</div>
							<!-- 취소버튼 -->
							<div class="col-md-3">
								<input class="form-control" type="reset" value="취소">
							</div>
						</div>	
					</form>
				</div>
		</div>
		<!------------------------------------------- 운송일보 표  ------------------------------------------>
		
		<!--------------------------------------------------운송일보 오전 ---------------------------------->
		<div class="col-md-12 text-center py-3">
				<h3>운송 일보 (오전)</h3>
			<table class="table table-center table-bordered">
					<tr class="table-info"><th>차 번호</th><th>유량(L)</th><th>실입금액(원)</th><th>카드수입(원)</th><th>일 매출(원)</th><th>비고</th><th>날짜</th></tr>

				<%
				
					for(Slip slip : sliplist){ 
						
						String ssplit= slip.getSdate();	
					 	String sdate=ssplit.split(" ")[0];
					 	String stime=ssplit.split(" ")[1];
					 	int hour=Integer.parseInt(stime.split(":")[0]);
					 	if(sdate.equals(today)){
					 		if(hour<12){
				%>
							<tr>
									<td><%=slip.getCarnum() %></td>						<!-- 차 번호 -->
									<td><%=df.format(slip.getSflux()) %></td>			<!-- 유량 -->
									<td><%=df.format(slip.getSfee()) %></td>			<!-- 실입금액 -->
									<td><%=df.format(slip.getScardfee()) %></td>		<!-- 신용카드 -->
									<td><%=df.format(slip.getSdaysale()) %></td>		<!-- 일 매출-->
									<td><%=slip.getSnote() %></td> 						<!-- 비고  -->
									<td><%=sdate%></td> 								<!-- 날짜 -->
							</tr>
				<%
					 		}
						} 
					}
				%>
					
					
			</table>
		</div>
		<!--------------------------------------------------운송일보 오후 ---------------------------------->
		<div class="col-md-12 text-center py-3">
				<h3>운송 일보 (오후)</h3>
			<table class="table table-center table-bordered">
					<tr class="table-info"><th>차 번호</th><th>유량(L)</th><th>실입금액(원)</th><th>카드수입(원)</th><th>일 매출(원)</th><th>비고</th><th>날짜</th></tr>
				<%
					for(Slip slip : sliplist){ 

						String ssplit= slip.getSdate();	
					 	String sdate=ssplit.split(" ")[0];
					 	String stime=ssplit.split(" ")[1];
					 	int hour=Integer.parseInt(stime.split(":")[0]);
					 	
					 	if(sdate.equals(today)){
					 		if(12<hour){
				%>
								<tr>
									<td><%=slip.getCarnum() %></td>						<!-- 차 번호 -->
									<td><%=df.format(slip.getSflux()) %></td>			<!-- 유량 -->
									<td><%=df.format(slip.getSfee()) %></td>			<!-- 실입금액 -->
									<td><%=df.format(slip.getScardfee()) %></td>		<!-- 신용카드 -->
									<td><%=df.format(slip.getSdaysale()) %></td>		<!-- 일 매출-->
									<td><%=slip.getSnote() %></td> 						<!-- 비고  -->
									<td><%=sdate%></td> 								<!-- 날짜 -->
								</tr>
				<%
					 		}
						} 
					}
				%>	
			</table>
		</div>
	</div>
	
	<%@include file ="../footer.jsp" %>
	<script src="../js/sales.js" type="text/javascript"></script>
</body>
</html>