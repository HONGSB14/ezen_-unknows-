<%@page import="java.util.Calendar"%>
<%@page import="dao.SaleDao"%>
<%@page import="dao.SlipDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dto.Slip"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%@include file = "../header.jsp" %>

<%
		//세션 값
	  	session.getAttribute("cnum");
		//검색 날짜 가져오기
		String current=request.getParameter("current");
		//날짜 계산
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		String year=today.split("-")[0];
		String day=today.split("-")[2];
		//페이지 반환
		String result=request.getParameter("result");
		//테이블 뷰 리스트 생성 
		ArrayList<Slip> searchList = SaleDao.getsaleDao().searchList(cnum,current);
		//천 자리 표현식
		DecimalFormat df = new DecimalFormat("#,###");
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
		<!----------------------------------------------------- 매출등록 란 ------------------------------------------>
		<div class="col-md-12 text-center">
			<a href="/taxicompany_data_system/main_sale.jsp"><h1>SALES</h1></a>
				<!-- 매출등록 헤더 -->
				<div class="row">
					<div class="col-md-2">
					<h6> <%=current%></h6>
					</div>
					<div class="text-center">
					<h3>일보 등록</h3>
					</div>
				</div>
				 <div class="row">	
					<!--타코미터 리스트 버튼 -->
					<div class="offset-4 col-md-2 py-2">
						<a href="/taxicompany_data_system/tacometer/tacometerlist.jsp"><button class="form-control">타코미터 기록보기</button></a>
					</div>
					<!-- 매출 검색 버튼 -->
					<div class="col-md-2 py-2">
						<button type="button" class="form-control" data-bs-toggle="modal" data-bs-target="#searchsale">등록 날짜 검색</button>	
					</div>
					<!-- 매출 수정 버튼 -->
					<div class="col-md-2 py-2">
						<button class="form-control" onclick="saleUpdate(<%=cnum%>)">매출 수정</button>
					</div>
					
					<!-- 매출 삭제 버튼 -->
					<div class="col-md-2 py-2">
						<button class="form-control" onclick="saleDelete(<%=cnum%>)">매출 삭제</button>
					</div>
					
					<!-- 모달 -->
					<div class="modal fade" id="searchsale" tabindex="-1" aria-labelledby="searchsale" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
							<form action="../slip/SearchCurrent" method="get">
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
				<!-- 매출정보 기입 란 -->
				<div class="col-md-12">
					<form action="../slip/SearchRegistration" id="saleform" method="get">
						<!-- 회사 번호 넘기기 (hidden) -->
						<input type="hidden" name="cnum" value="<%=cnum%>">
						<input type="hidden" name="current" value="<%=current%>">
						<!-- 매출정보 뷰 -->
						<div class="row">
							<table class="table">
								<tr class="table-info"><th>차 번호</th><th>유량(L)</th><th>실입금액(원)</th><th>카드수입(원)</th><th>일 매출(원)</th><th>비고</th></tr>
								<tr>  	
									<td><input type="text" name="carnum" id="carnum" class="form-control"></td> 
									<td><input type="text" name="flux" id="flux"  class="form-control" autofocus="autofocus"></td> 
									<td><input type="text" name="fee" id="fee"  class="form-control"></td >
									<td><input type="text" name="cardfee" id="cardfee"   class="form-control"></td> 
									<td><input type="text" name="daysale" id="daysale"  class="form-control"></td> 
									<td><input type="text" name="note" id="note"  class="form-control"></td>
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
					</form>
					<!-- 등록/취소 (버튼) -->
						<div class="offset-5 col-md-2">
							<!-- 등록버튼 -->
							<div class="text-center">
							 	<input class="form-control" onclick="salecheck()" type="submit" value="등록">
							</div>
						</div>	
				</div>
		</div>
		<!--------------------------------------------------운송일보 표 ---------------------------------->
		<div class="col-md-12 text-center py-3">
				<h3 class="text-center">운송 일보</h3>
			<table class="table table-center table-bordered table-hover">
						<tr class="table-info"><th></th><th>차 번호</th><th>유량(L)</th><th>실입금액(원)</th><th>카드수입(원)</th><th>일 매출(원)</th><th>비고</th><th>날짜</th></tr>
						<%	
							//각각의 수정값을 구별하기위한 변수
							int i=0;
								for(Slip slip :searchList){		
						%>
									<tr onclick="updateClick('<%=slip.getSnum()%>')">
										<td><input class="form-check-input" type="checkbox" value="checkbox" name="salecheckbox" id="salecheckbox" onclick="check('<%=slip.getSnum()%>')"></td>
										<td><input class="form-control" type="text" value="<%=slip.getCarnum()%>" name="carnum" id="carnum2<%=i%>"></td>								<!-- 차량번호 -->
										<td><input class="form-control" type="text" value="<%=slip.getSflux()%>" name="flux" id="flux2<%=i%>"></td>									<!-- 유량 -->
										<td><input class="form-control" type="text" value="<%=slip.getSfee() %>" name="fee" id="fee2<%=i%>"></td>										<!-- 실입요금 -->
										<td><input class="form-control" type="text" value="<%=slip.getScardfee() %>" name="cardfee" id="cardfee2<%=i%>"></td>							<!-- 카드요금 -->
										<td><input class="form-control" type="text" value="<%=slip.getSdaysale() %>" name="daysale" id="daysale2<%=i%>"></td>							<!-- 총 매출 -->
										<td><input class="form-control" type="text" value="<%=slip.getSnote() %>" name="note" id="note2<%=i%>"></td>									<!-- 비고 -->	
										<td><input class="form-control" type="text" value="<%=slip.getSdate().split(" ")[0]%>" name="date" id="date2" disabled="disabled"></td>	<!-- 날짜 -->					
									</tr>
						<%	
									i++;
								}
						%>
						
				</table>
		</div>
	</div>
<%@include file ="../footer.jsp" %>
<script src="../js/slip.js" type="text/javascript"></script>
</body>
</html>