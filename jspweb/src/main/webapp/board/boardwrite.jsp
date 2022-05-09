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
	
	<div class="container">
			
			<a href="boardlist.jsp"><button>뒤로가기</button></a>
			<H3>글쓰기</H3>
			<form action="../board/Write" method="post" enctype="multipart/form-data">  <!-- controller/board/Write 로 경로설정 --> 
													<!-- (1.) form 전송 인코딩타입: form 은 기본으로 "문자"밖에 전송 못하기 때문에 enctype 설정  -->
													<!-- (2.) cos.jar 라이브러리 추가  -->
				제목 : <input type="text" name="btitle">
				내용 :<textarea name="bcontent" id="summernote"></textarea>
				첨부파일: <input type="file" name="bfile">
				<input type="submit" value="작성하기">
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