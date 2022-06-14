<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="javax.print.DocFlavor.INPUT_STREAM"%>
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
	 	//가격에 따른 서비스 제공 차이 값
		String priceCheck=SaleDao.getsaleDao().pricecheck(cnum);
	 	
		// 숫자 꾸미기
		DecimalFormat df = new DecimalFormat("#,###");
		//현재 시간 가져오기
		LocalDateTime now = LocalDateTime.now();
	 	String wedate=now.format(DateTimeFormatter.ISO_LOCAL_DATE);
		String year=wedate.split("-")[0];	//년도
		String month=wedate.split("-")[1];	//달
	 	String day=wedate.split("-")[2];	//일
	 	//매출 리스트담기용 리스트 생성
	 	ArrayList<Integer> saleList = new ArrayList<>();
		//날짜 가져오기용 리스트 생성
		ArrayList<String> saleDate = new ArrayList<>();
		
		int aYear=Integer.parseInt(year);
		int bYear=aYear-20;
		String cYear=Integer.toString(bYear); 
		String current=cYear+"-01-01";
		
		//달력 현재 날짜 -1일 변수 
		Calendar beforeDate= Calendar.getInstance();
		beforeDate.add(Calendar.DATE , -1);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String beforeDay=sdf.format(beforeDate.getTime());
				
		
		
	 %>
	<div class="container text-center">
		 	<div class="col-md-12 text-center py-5">
		 		<h1>매출 현황</h1>
		 	</div>
		 	<!-- 일보 리스트 부분 -->
		 	<div class="col-md-12">
		 		<div class="text-center">
		 			<h2>월 보 (<%=month%>월)</h2>
		 		</div>
		 	</div>
			 <!--버튼-->
			 <div class="row">	
				<div class="col-md-2">
				<span>company. <%=cnum%></span>
				</div>
				<!-- 매출 검색 버튼 -->
				<div class="offset-6 col-md-2 py-2">
					<button type="button" class="form-control" data-bs-toggle="modal" data-bs-target="#searchsale">매출 검색</button>	
				</div>
				<!-- 매출등록 버튼 -->
				<div class="col-md-2 py-2">
					<a href="sale/slip_registration.jsp"><button class="form-control">매출 등록</button></a>
				</div>
				
				<!-- 모달 -->
				<div class="modal fade" id="searchsale" tabindex="-1" aria-labelledby="searchsale" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
						<form action="Calender/CalenderMaker" method="get">
						<input type="hidden" value="<%=cnum%>" name="cnum">
							<div class="modal-header">
								<h5 class="modal-title" id="searchsaleModalLabel">매출검색</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
							</div>
							<div class="modal-body">
								<!-- 달력 날짜 선택 란 -->
								<input class="form-control" type="date" name="date" min="<%=current%>" max="<%=beforeDay%>">
							</div>
							<div class="modal-footer">
								<button type="submit" class="form-control" data-bs-dismiss="modal">Search</button>
								<button type="button" class="form-control" data-bs-dismiss="modal">Close</button>
							</div>
							</form>
						</div>
					</div>
				</div>
				
			</div>
			
			<!-- 테이블 표 -->
			<div class="col-md-12">
				<table class="table table-center table-bordered" id="mainsale">
					
					<!-- 총 수입 (일) -->
					<tr class="table-info"><th>날짜</th><th>유량 사용량 (L)</th><th>실입수입 (원)</th><th>카드 수입 (원)</th><th>총 매출 (원)</th><th>실 매출 (원)</th></tr>
					<%
						for(Slip slip :daySaleList){
							
							//날짜 가져오기 (월)
							String date= slip.getSdate().split(" ")[0]; //date 자르고 0번째 인덱스 ex))0000-11-22 
							String dateYear=date.split("-")[0];		//년 자르기	짜른 인덱스에서 0000 가져오기
							String dateMonth=date.split("-")[1];	//월 자르기	짜른 인덱스 에서 11  가져오기
							
							//급여계산
							int pay=(slip.getSdaysale())-(slip.getSdaysale()/10);
							//만약 오늘 날짜와 데이터날짜와 같다면 년/월
							if(month.equals(dateMonth) && year.equals(dateYear) ){
					%> 			
								<tr>
					<%
						//만약 오늘이라면 (날짜클릭 링크 제외)
						if(wedate.equals(date)){
					%>
									<td><%=slip.getSdate()%></td>																							<!-- 날짜 -->
									<td><%=df.format(slip.getSflux()) %></td>																				<!-- 총 유량 일 사용량 -->
									<td><%=df.format(slip.getSfee()) %></td>   																				<!-- 실입수입  일 합계 -->
									<td><%=df.format(slip.getScardfee()) %></td>   																			<!-- 카드 수입 일 합계 -->
									<td><%=df.format(slip.getSdaysale()) %></td>																			<!-- 총 매출 -->
									<td><%=df.format(pay)%></td>																							<!-- 실 매출 -->
					<%
						}else{
					%>		
									<td><a href="/taxicompany_data_system/sale/salesearch.jsp?current=<%=slip.getSdate()%>"><%=slip.getSdate()%></a></td>	<!-- 날짜 -->
									<td><%=df.format(slip.getSflux()) %></td>																				<!-- 총 유량 일 사용량 -->
									<td><%=df.format(slip.getSfee()) %></td>   																				<!-- 실입수입  일 합계 -->
									<td><%=df.format(slip.getScardfee()) %></td>   																			<!-- 카드 수입 일 합계 -->
									<td><%=df.format(slip.getSdaysale()) %></td>																			<!-- 총 매출 -->
									<td><%=df.format(pay)%></td>																							<!-- 실 매출 -->
					<%
						}
					%>																										
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
								String date=slip.getSdate().split(" ")[0]; //date 자르고 0번째 인덱스 ex))0000-11-22 
								String dateYear=date.split("-")[0];			//년 자르기	짜른 인덱스에서 0000 가져오기
								String dateMonth=date.split("-")[1];		//월 자르기	짜른 인덱스 에서 "11" 가져오기
									
								if(year.equals(dateYear) && month.equals(dateMonth) ){	
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
	<%
		if(priceCheck.equals("2") || priceCheck.equals("3")){
	%>
	<!----------------------------------차트부분 --------------------------------- -->
	<div class="col-md-12 py-5">
		<!-- 구글차트 선언 -->
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		
		<!--weekchart (pieChart) -->
		<div class="text-center"><h3>주 매출비교</h3></div>		
		<div id="pie_chart" class="offset-2">
		 <%
			//주 계산을 위한 인트 변환
		 	int today=Integer.parseInt(day);
		 	
		 	for(Slip slip : daySaleList){
		 		
		 		String weekYear=slip.getSdate().split("-")[0]; 	//년도
				String weekMonth=slip.getSdate().split("-")[1];	//달
		 		String weekDay=slip.getSdate().split("-")[2];	//일
		 		
		 		int dayDay2=Integer.parseInt(weekDay);
		 		int realDay=(today-dayDay2);
		 		
		 		if(realDay>=0 && realDay<7 && year.equals(weekYear) && month.equals(weekMonth) ||realDay<-24 ){
		 			saleList.add(slip.getSdaysale());
		 			saleDate.add(slip.getSdate());
		 			for(int i=0; i<saleDate.size(); i++){
		 %>		
		 		<input type="hidden" id="<%="weekDate"+i%>" value="<%=saleDate.get(i)%>">
				<input type="hidden" id="<%="weekSale"+i%>" value="<%=saleList.get(i)%>">
		 <%					
		 			}
		 		}
		 	}
			//데이터 관리와 리스트 재사용을위해 클리어
			saleList.clear();
			saleDate.clear();
		 %>
	 		
		</div>
		
		<!-- monthchart (column) -->
		<div class="text-center"><h3><%=month %>월 일별 매출비교</h3></div>	
		<div id="bar_chart" class="col-md-6" >
				 <!-- 월 매출 (일별)값 JS로 넘기 -->	
		 <% 
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
		</div>
		
		<!-- yearchart (line) -->
		<div class="text-center"><h3><%=year %>년 매출비교</h3></div>		
		<div id="line_chart" class="col-md-6">
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
			
			//데이터 관리와 리스트 재사용을위해 클리어
			saleList.clear();
			saleDate.clear();
			%>
		</div>
	</div>
	<%
		}
	%>
	<%@include file ="footer.jsp" %>
	<script src="/taxicompany_data_system/js/mainsales.js" type="text/javascript"></script>
</body>
</html>