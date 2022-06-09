
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
		String year =today.split("-")[0];
		//달력 날짜 생성
		int cYear=Integer.parseInt(year);
		//페이지 반환
		String result=request.getParameter("result");
		//테이블 뷰 리스트 생성 
		ArrayList<Slip> sliplist= SlipDao.getSlipDao().sliplist(cnum);
		//천 자리 표현식
		DecimalFormat df = new DecimalFormat("#,###");
		
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
									<h5 class="modal-title" id="searchsaleModalLabel">등록 날짜 검색</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
								</div>
								<div class="modal-body">
									<!-- 달력 날짜 선택 란 -->
									<div class="container">
										<div class="col-md-12 text-center">
											<h5>날짜선택</h5>
											<div class="row">
												<!-- 년도 선택 란 -->
												<div class="col-md-1 py-2">
													년:
												</div>
												
													<div class="col-md-3">
														<select class="form-select" id="cYear" name="cYear">	
															<%
																for(int i=0; i<31; i++){
															%>	
																<option class="text-center" value="<%=cYear-i%>"><%=cYear-i%></option>
															<%
																} 
															%>
														</select>
													</div>
													<!-- 월 선택 란 -->
													<div class="col-md-1 py-2">
														월:
													</div>
													<div class="col-md-3">
														<select class="form-select" id="cMonth" name="cMonth">	
															<%
																for(int i=1; i<13; i++){
																	if(i<10){
															%>	
																<option class="text-center" value="0<%=i%>"><%=i%></option>
															<%
																	}else{
															%>
																<option class="text-center" value="<%=i%>"><%=i%></option>
															<% 			
																	}
																} 
															%>
														</select>
													</div>
													<div class="col-md-1">
														일:
													</div>
													<div class="col-md-3">
														<select class="form-select" id="cDay" name="cDay">	
															<%
																for(int i=1; i<32; i++){
																	if(i<10){
															%>	
																<option class="text-center" value="0<%=i%>"><%=i%></option>
															<%
																	}else{
															%>
																<option class="text-center" value="<%=i%>"><%=i%></option>
															<% 			
																	}
																} 
															%>
														</select>
													</div>
												</div>
										</div>
									</div>
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
					<form action="../slip/SlipRegistration" id="saleform" method="get">
						<!-- 회사 번호 넘기기 (hidden) -->
						<input type="hidden" name="cnum" value="<%=cnum%>">
						<!-- 매출정보 뷰 -->
						<div class="row">
							<table class="table">
								<tr class="table-info"><th>차 번호</th><th>유량(L)</th><th>실입금액(원)</th><th>카드수입(원)</th><th>일 매출(원)</th><th>비고</th></tr>
								<tr>  	
									<td><input type="text" name="carnum" id="carnum" class="form-control"></td> 						<!-- 차량번호 -->
									<td><input type="text" name="flux" id="flux"  class="form-control" autofocus="autofocus"></td> 		<!-- 유량 -->
									<td><input type="text" name="fee" id="fee"  class="form-control"></td >								<!-- 실입요금 -->
									<td><input type="text" name="cardfee" id="cardfee"   class="form-control"></td> 					<!-- 카드요금 -->
									<td><input type="text" name="daysale" id="daysale"  class="form-control"></td> 						<!-- 총 매출 -->
									<td><input type="text" name="note" id="note"  class="form-control"></td> 							<!-- 비고 -->
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
		<!------------------------------------------- 운송일보 표-------------------------------------------->
		
		<!--------------------------------------------------운송일보 오전 ---------------------------------->
		<div class="col-md-12 text-center py-3">
				<h3 class="text-center">운송 일보 (오전)</h3>
			<table class="table table-center table-bordered table-hover">
					<tr class="table-info"><th></th><th>차 번호</th><th>유량(L)</th><th>실입금액(원)</th><th>카드수입(원)</th><th>일 매출(원)</th><th>비고</th><th>날짜</th></tr>

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
									<td><input class="form-check-input" type="checkbox" value="checkbox" name="salecheckbox" id="salecheckbox" onclick="check('<%=slip.getSnum()%>')"></td>	<!-- 체크박스 -->
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
				<h3 class="text-center">운송 일보 (오후)</h3>
			<table class="table table-center table-bordered">
					<tr class="table-info"><th></th><th>차 번호</th><th>유량(L)</th><th>실입금액(원)</th><th>카드수입(원)</th><th>일 매출(원)</th><th>비고</th><th>날짜</th></tr>
				<%
					for(Slip slip : sliplist){ 

						String ssplit= slip.getSdate();	
					 	String sdate=ssplit.split(" ")[0];
					 	String stime=ssplit.split(" ")[1];
					 	int hour=Integer.parseInt(stime.split(":")[0]);
					 	
					 	if(sdate.equals(today)){
					 		if(12<=hour){
				%>
								<tr>
									<td><input class="form-check-input" type="checkbox" value="checkbox" name="salecheckbox" id="salecheckbox" onclick="check('<%=slip.getSnum()%>')"></td>	<!-- 체크박스 -->
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
	<script src="../js/slip.js" type="text/javascript"></script>
</body>
</html>