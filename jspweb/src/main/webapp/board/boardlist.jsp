<%@page import="dao.MemberDao"%>
<%@page import="dto.Member"%>
<%@page import="dto.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="../header.jsp" %> 
		
		<div class="container">
		<h3>자유 게시판</h3>
		
	<%	
		//로그인 시 불러오기
		String mid=(String)session.getAttribute("login");
		if(mid !=null){
	%>
	
		<a href="boardwrite.jsp"><button>글쓰기</button></a>
	
	<%
		}
	%>
		
		<table class="table"> <!-- 테이블 -->
		<tr>	
			<th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>작성일</th>
		</tr>
			
		<!--  for 문 -->
		<% 
			//모든 게시물 호출
			ArrayList<Board> boardlist = BoardDao.getBoardDao().getboardlist();
			
		
			
				for(Board board : boardlist){
				
		%>		
			<tr>
				<td><%=board.getBno()%></td>
				<td><%=board.getBtitle()%></td>
				<td><%=board.getMid()%></td>
				<td><%=board.getBview()%></td>
				<td><%=board.getBdate()%></td>
			</tr>
		<% 
			}
		%>
		
		<tr>
	</table>
	</div>
	

	<%@include file="../footer.jsp" %>
</body>
</html>