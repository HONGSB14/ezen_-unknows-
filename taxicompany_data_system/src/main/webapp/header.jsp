<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TaxiCompany Data System</title>
	<!-- 부트스트랩 css cdn -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
	<!-- 사용자정의 css -->
	<link href="/taxicompany_data_system/css/main.css" rel="stylesheet">
	
	
</head>
<body>
		<%
			String login=(String)session.getAttribute("login");
			Integer cnum=(Integer)session.getAttribute("cnum");
		%>
		
		
		<div class="text-center py-3">
			
			<div class="col-md-12 row">
				<div class="col-md-3">	
					<a href="/taxicompany_data_system/main.jsp"><img alt="로고홈" src="/taxicompany_data_system/img/mainlogo.png" style="height: 140px; width: 150px;"></a>		
				</div>
				
				
				<div class="offset-5 col-md-4 py-5">
					
					<%
						if(login==null){
					%>
							<a class="headerinfo" href="/taxicompany_data_system/product_info.jsp">제품소개 | </a>	
							<a class="headerinfo" href="/taxicompany_data_system/company/companysignup.jsp">가입하기 |</a> 
							<a class="headerinfo" href="/taxicompany_data_system/member/login.jsp">로그인 |</a> 
							<a class="headerinfo" href="/taxicompany_data_system/member/agreementpage.jsp">회원가입</a>	
					<%
						}else{
					%>
							<span style="color: blue;">Hello!!  <%=login%> </span> 
							<a class="headerinfo" href="/taxicompany_data_system/product_info.jsp">　제품소개 | </a>
							<a class="headerinfo" href="/taxicompany_data_system/main_info.jsp?cnum=<%=cnum%>">매출 데이터 보기 |</a>
							<a class="headerinfo" href="/taxicompany_data_system/main_sale.jsp?cnum=<%=cnum%>">매출등록하기 |</a>
							<a class="headerinfo" href="/taxicompany_data_system/member/Logout">로그아웃 </a>	
					<%
						}
					%>
				</div>
				
			</div>
			
		</div>
		
	<!-- 부트스트랩 js cdn -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- 사용자정의 js -->
	<script src="/taxicompany_data_system/js/main.js" type="text/javascript"></script>
	<!-- jquery 최신 cdn -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

</body>
</html>