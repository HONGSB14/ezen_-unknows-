<%@page import="dto.Board"%>
<%@page import="dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	int bno= Integer.parseInt(request.getParameter("bno"));
	Dao dao = new Dao();
	Board board =dao.get(bno);
	
	//만약 로그인된 정보와 작성자가 동일할 경우 삭제 / 수정 표시
	String loginid=(String)session.getAttribute("loginid");
	
	if(loginid != null||loginid.equals(board.getWriter())){%>
		<a href="bupdate.jsp"></a>
		<a href="bdeletecontroller.jsp?bno=<%=bno %>"></a>
	<% }%>

<a href="#">목록보기</a>
<a href="#">수정</a>
<a href="#">삭제</a>

<DIV> 제목: </DIV>
<DIV> 작성자: </DIV>
<DIV> 작성일: </DIV>
<DIV> 내용: </DIV>


</body>
</html>