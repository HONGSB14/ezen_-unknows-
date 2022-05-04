<%@page import="dto.Member"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file ="../header.jsp"%>
	<div class="container">
		<div class="row">
			
			<div class="col-md-3"> <!--  사이드바 -->
				사이드바
			</div>	
			
			<div class="col-md-9"> <!--  본문 -->
				<h3>회원정보</h3>
				<%
					//1.세션 호출  [로그인 시 저장된 회원 ID ]
					String mid=(String)session.getAttribute("login");
					//2.db호출
					Member member=MemberDao.getMemberDao().getmember(mid);
					//3.출력 . HTML에 객체 표현식
				%>
				회원번호: <%=member.getMno() %>		<Br>
				아이디 : <%=member.getMid() %>		<Br>
				이름 : <%=member.getMname() %>		<Br>
				연락처 : <%=member.getMphone() %>		<Br>
				이메일 : <%=member.getMemail() %>		<Br>
				배송주소 : <%=member.getMaddress() %>	<Br>	
				포인트 : <%=member.getMpoint() %>		<Br>
				가입날짜 : <%=member.getMdate() %>	    <Br>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
</body>
</html>