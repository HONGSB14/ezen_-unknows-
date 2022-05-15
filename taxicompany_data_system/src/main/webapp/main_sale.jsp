<%@page import="dao.SaleDao"%>
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
	 	//매출 리스트 호출
	 	ArrayList<Slip> daySaleList= SaleDao.getsaleDao().daysaleadd(cnum);
		ArrayList<Slip> monthSaleList= SaleDao.getsaleDao().monthsaleadd(cnum);
		ArrayList<Slip> yearSaleList= SaleDao.getsaleDao().yearsaleadd(cnum);
	 	DecimalFormat df = new DecimalFormat("#,###");
	 	LocalDateTime now = LocalDateTime.now();
		//현재 시간 가져오기
	 	String wedate=now.format(DateTimeFormatter.ISO_LOCAL_DATE);
		String year=wedate.split("-")[0];	//년도
		String month=wedate.split("-")[1];	//달
	 	String day=wedate.split("-")[2];	//일
		
	 %>
	 <!-- 월 매출 (일별)값 JS로 넘기 -->	
	 <% 
	 	//월 매출 리스트담기용 리스트 생성
	 	ArrayList<Integer> saleList = new ArrayList<>();
		//날짜 가져오기용 리스트 생성
		ArrayList<String> saleDate = new ArrayList<>();
		
		for(Slip slip : daySaleList){
			
			//비교를 위한 날짜값 가져오기
			String dayYear=slip.getSdate().split("-")[0]; 	//년도
			String dayMonth=slip.getSdate().split("-")[1];	//달
			
			
			//현재 날짜와 전송할려는 날짜가 일치하는지 판단여부
			if(year.equals(dayYear) && month.equals(dayMonth)){
				saleList.add(slip.getSdaysale());
				saleDate.add(slip.getSdate());	
				for(int i=0; i<saleList.size(); i++){
	%>
					<!-- 총 매출 전송 -->
					<input type="hidden" id="<%="daySale"+i%>" value="<%=saleList.get(i)%>">
					<!-- 총 매출의 날짜 전송 -->
					<input type="hidden" id="<%="dayDate"+i%>" value="<%=saleDate.get(i)%>">
	<%			
				}
			}
		}
		//데이터 관리와 리스트 재사용을위해 클리어
		saleList.clear();
		saleDate.clear();
	%>
	
	<!-- 년 매출(월별) 값 JS로 넘기기 -->
	<%
		for(Slip slip : monthSaleList){
			
			String monthYear=slip.getSdate().split("-")[0]; 	//년도
			String monthMonth=slip.getSdate().split("-")[1];	//달
			
			
			if(year.equals(monthYear)){
				saleList.add(slip.getSdaysale());
				saleDate.add(slip.getSdate().split("-")[1]);
				for(int i=0; i<saleList.size(); i++){
	%>
			<input type="hidden" id="<%="monthSale"+i%>" value="<%=saleList.get(i)%>"> 
			<input type="hidden" id="<%="monthDate"+i%>" value="<%=saleDate.get(i)%>"> 
	<% 					
				}
			}
		}
		saleList.clear();
		saleDate.clear();
	
	%>
	
	<div class="container text-center">
		 	<!-- 일보 리스트 부분 -->
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
				<a href="sale/slip_registration.jsp"><button class="form-control">매출 등록</button></a>	
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
							String dateMonth=date.split("-")[1];	//월 자르기	짜른 인덱스 에서 11 가져오기
							
							
							
							if(month.equals(dateMonth)){
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
								String dateMonth=date.split("-")[1];	//월 자르기	짜른 인덱스 에서 "11" 가져오기
								
								if(month.equals(dateMonth)){
						%>
									<tr>
										<td><%=df.format(slip.getSflux()) %></td>		<!-- 총 유량 	월 사용량 -->
										<td><%=df.format(slip.getSfee()) %></td>   		<!-- 실입수입	월 합계 -->
										<td><%=df.format(slip.getScardfee()) %></td>   	<!-- 카드 수입	월 합계 -->
										<td><%=df.format(slip.getSdaysale())%></td>	    <!-- 총 매출 -->
										<td colspan="2"><%=df.format(pay)%></td>		<!-- 실 매출 -->
									</tr>
						<%
								}
							}
						%>
				</table>
			</div>
	</div>
	
	<!-------------------------차트부분 --------------------------------- -->
	<div class="col-md-12 py-5">
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<!--weekchart (pieChart) -->
		<div class="offset-6">주 매출비교</div>		
		<div id="pie_chart" class="offset-2 col-md-4">
					
		</div>
		
		<!-- monthchart (column) -->
		<div class="offset-6"><%=month %>월 매출비교</div>	
		<div class="col-md-12" id="bar_chart">
					
		</div>
		
		<!-- yearchart (line) -->
		<div class="offset-6"><%=year %>년 매출비교</div>		
		<div id="line_chart" class="col-md-12">
					
		</div>
	</div>
	
	<%@include file ="footer.jsp" %>
	<script src="/taxicompany_data_system/js/chart.js" type="text/javascript"></script>
</body>
</html>