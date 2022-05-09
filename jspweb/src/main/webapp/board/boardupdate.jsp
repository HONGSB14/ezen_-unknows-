<%@page import="dao.BoardDao"%>
<%@page import="dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- 게시판썸머노트 CSS -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<!--  부트스트랩 3버전 -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	
</head>
<body>
	<%@include file="../header.jsp" %>
	
	<%
		//bno가져오기
		int bno=Integer.parseInt(request.getParameter("bno")) ;
	
		Board board =BoardDao.getBoardDao().getboard(bno);
	%>
	
	<div class="container">
			
			<a href="boardlist.jsp"><button>뒤로가기</button></a>
			<H3>글쓰기</H3>
			<form action="../board/Update?bno=<%=board.getBno()%>" method="post" enctype="multipart/form-data">  <!-- controller/board/Write 로 경로설정 --> 
													<!-- (1.) form 전송 인코딩타입: form 은 기본으로 "문자"밖에 전송 못하기 때문에 enctype 설정  -->
													<!-- (2.) cos.jar 라이브러리 추가  -->
				제목 : <input type="text" name="btitle" value="<%=board.getBtitle()%>">
				<br>
				내용 :<textarea name="bcontent" id="summernote"><%=board.getBcontent()%></textarea>
				<br>
				첨부파일: 
				<%
					if(board.getBfile()==null){
				%>	
					<span>첨부된 파일이 없습니다.</span>
				<% 
					}else{
				%>
					<%=board.getBfile()%> 
					<button type="button" onclick="filedelete(<%=board.getBno()%>)">파일삭제</button>
				<%
					}
				%>
				
																					<!-- bno를 board.js 로 인수 넘기기 -->
				<input type="submit" value="수정하기">
				<input type="reset" value="취소"> 
				
			</form>
	</div>
	
	<%@include file="../footer.jsp" %>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<!-- 사용자 정의 -->
	<script src="/jspweb/js/board.js" type="text/javascript"></script>
</body>
</html>