<%@page import="dto.Company"%>
<%@page import="dao.CompanyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
 <%@include file="../header.jsp" %>
 <%
 	int cnum2=Integer.parseInt(request.getParameter("successcnum"));
 	Company company=CompanyDao.getCompanyDao().selectCompany(cnum2);
 %>
 	<div class="container">
 		<div class="text-center py-4" >
			<h2>Thank You!!　<%=company.getCname() %></h2> 
		</div>
		<div class="col-md-12 text-center">
			<div class="text-center" style="background: #FFFFCC;">
				<h2> 회사 가입 정보 </h2>
			</div>
				<div class="py-5">
				     회사 명 :　 <%=company.getCname() %>
					 <br>
					 <br>
				 가입 등록 번호 :　 <%=company.getCnum() %>
					 <br>
					 <br>
				 결제 은행    :　<%=company.getCbank()%>
				 	<br>
				 	<br>
				 	<%
				 		if(company.getCprice().equals("1")){
				 	%>
				 	결제 타입 :　  30,000원 (매출 표 제공)
				 	<% 		
				 		}if(company.getCprice().equals("2")){
				 		
				 	%>
				 	결제 타입 :　  60,000원 (매출 표 + 매출 차트 제공)
				 	<%		
				 		}if(company.getCprice().equals("3")){
				 	%>
				 　　	결제 타입 : 　 90,000원 (매출 표 + 매출 차트 제공+택시 빅 데이터 제공 )
				 	<%
				 		}
				 	%>
				</div>
				
				<div class="offset-5 col-md-2">
				<a href="../main.jsp"><button class="form-control">홈으로</button></a>
				</div>
		</div>
 	</div>
 
 <%@include file ="../footer.jsp" %>
</body>
</html>