
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
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
	
	//현재 시간 가져오기
	//날짜 계산
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String wedate = sdf.format(date);
	String year=wedate.split("-")[0];	//년도
	String month=wedate.split("-")[1];  //월
	String day=wedate.split("-")[2];	//일
	//달력 현재 날짜까지 검색 변수 생성
	int aYear=Integer.parseInt(year);
	int bYear=aYear-20;
	String cYear=Integer.toString(bYear); 
	String currented=cYear+"-01-01";
	//달력 현재 날짜 -1일 변수 
	Calendar beforeDate= Calendar.getInstance();
	beforeDate.add(Calendar.DATE , -1);
	String beforeDay=sdf.format(beforeDate.getTime());
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
					<span>company. <%=cnum%></span>
					</div>
					<div class="text-center">
					<h3>매출 검색 내역</h3>
					</div>
				</div>
				
		</div>
		<!------------------------------------------- 운송일보 표-------------------------------------------->
		<div class="col-md-12 text-center">
			<!--버튼-->
			 <div class="row">	
				<!-- 매출 검색 버튼 -->
				<div class="offset-6 col-md-2 py-2">
					<button type="button" class="form-control" data-bs-toggle="modal" data-bs-target="#searchday">매출 검색</button>	
				</div>
				<!-- 매출 삭제 버튼 -->
				<div class="col-md-2 py-2">
					<button class="form-control" onclick="saleDelete(<%=cnum%>)">매출 삭제</button>
				</div>
				<!-- 매출 수정 버튼 -->
				<div class="col-md-2 py-2">
					<button class="form-control" onclick="saleUpdate('<%=cnum%>')">매출 수정</button>
				</div>
				<!-- 모달 -->
				<div class="modal fade" id="searchday" tabindex="-1" aria-labelledby="searchday" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
						<form action="../Calender/CalenderMaker" method="get">
						<input type="hidden" value="<%=cnum%>" name="cnum">
							<div class="modal-header">
								<h5 class="modal-title" id="searchsaleModalLabel">매출검색</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
							</div>
							<div class="modal-body">
								<!-- 달력 날짜 선택 란 -->
									<input class="form-control" type="date" name="date" min="<%=currented%>" max="<%=beforeDay%>">
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
		</div>
			<!-- 검색내역 출력 란 -->
			<div class="col-md-12">
				<table class="table table-center table-bordered table-hover text-center">
						<tr class="table-info"><th></th><th>차 번호</th><th>유량(L)</th><th>실입금액(원)</th><th>카드수입(원)</th><th>일 매출(원)</th><th>비고</th><th>날짜</th></tr>
						<%
								//각각의 수정값을 구별하기위한 변수
							    int i=0;
								for(Slip slip :searchList){	
								
						%>
									<tr onclick="updateClick('<%=slip.getSnum()%>')">
										<td><input class="form-check-input" type="checkbox" name="salecheckbox" id="salecheckbox" onclick="dcheck('<%=slip.getSnum()%>')"></td>
										<td><input class="form-control" type="text" value="<%=slip.getCarnum()%>" name="carnum" id="carnum2<%=i%>"></td>				<!-- 차량번호 -->
										<td><input class="form-control" type="text" value="<%=slip.getSflux()%>" name="flux" id="flux2<%=i%>"></td>						<!-- 유량 -->
										<td><input class="form-control" type="text" value="<%=slip.getSfee() %>" name="fee" id="fee2<%=i%>"></td>						<!-- 실입요금 -->
										<td><input class="form-control" type="text" value="<%=slip.getScardfee() %>" name="cardfee" id="cardfee2<%=i%>"></td>			<!-- 카드요금 -->
										<td><input class="form-control" type="text" value="<%=slip.getSdaysale() %>" name="daysale" id="daysale2<%=i%>"></td>			<!-- 총 매출 -->
										<td><input class="form-control" type="text" value="<%=slip.getSnote()%>" name="note" id="note2<%=i%>"></td>						<!-- 비고 -->
										<td><input class="form-control" type="text" value="<%=slip.getSdate()%>" name="date" id="date2" disabled="disabled"></td>	    <!-- 날짜 -->
									</tr>
						<%	
								   
									i++;
								}
						%>
						
				</table>
			</div>
		</div>
	</div>
	
	<%@include file ="../footer.jsp" %>
	<script src="../js/slip.js" type="text/javascript"></script>
</body>
</html>