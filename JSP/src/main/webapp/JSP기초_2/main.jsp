<%@page import="java.util.ArrayList"%>
<%@page import="dao.Dao"%>
<%@page import="dto.Board"%>
<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h3> 회원제 게시판</h3>
		
		
		
		<%
			
			String logid=(String)session.getAttribute("loginid"); //로그인 유지
			
		if(logid != null){
		%>
		<div>안녕하세요 </div>
		<a href="write.jsp"><button>글쓰기버튼</button></a>
		<a href="logout.jsp"><button>로그아웃</button></a>
		<a href="memberout.jsp"><button>회원탈퇴</button></a>
		
		<% 	
		}else{
			%>
				<a href="signup.jsp"><button>회원가입</button></a>
				<form action="logincontroll.jsp">
				<input type="text" name="id">
				<br>
				<input type="password" name="password">
				<input type="submit" value="로그인">
			    </form>
		<% } %>

	
	
	
	
	
	<table>
		<tr>
			<th>번호</th> <th>작성일</th> <th>작성자</th> <th>제목</th> <th>내용</th>
		</tr>
		<tr>
			<%Dao dao = new Dao();
			
			ArrayList<Board> boardList = dao.list();
			for(Board temp : boardList){%>
					<tr>
					<td><%=temp.getBno()    %></td>
					<td><%=temp.getDate()   %></td>
					<td><%=temp.getWriter() %></td>
					<td><a href="view.jsp?bno=<%=temp.getBno()%>"><%=temp.getTitle()%></a></td>
						<!-- href="파일명?변수명=값" get 방식으로 변수를 보낼 수 있음-->
					</tr>
					
				<%}%>
		

	</table>

</body>
</html>