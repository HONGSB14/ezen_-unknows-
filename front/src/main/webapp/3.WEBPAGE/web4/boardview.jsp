<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%@include file="header.jsp" %>
		<br><br><br><br><br><br><br><br><br><br><br>
		<div class="container">
			<h3 class="text-center titleline">문의 글 </h3>
			
			<!--  버튼 -->
			<div >
				<a href="boardlist.jsp"><button class="btn btn-outline-info">목록보기</button></a>
				<a href="boardlist.jsp"><button class="btn btn-outline-info">수정</button></a> <!-- 작성자 가능 [ 유효성 검사 ] -->
				<a href="boardlist.jsp"><button class="btn btn-outline-info">삭제</button></a> <!-- 작성자만 가능 [유효성 검사]  -->
				<a href="boardlist.jsp"><button class="btn btn-outline-info">답변</button></a> <!-- 작성자만 가능 [유효성 검사]  -->
			</div>
			<!-- 작성자 입력한 내용 -->
			  <div>
			  	<div><span>작성자</span><span>유재석</span></div>
			  	<div><span>문의날짜</span><span>2022-04-24 10:10</span></div>
			  </div>
			  
			  <div>
			  	<input type="text" value="환불좀.." class="form-control" readonly style="background-color: white;" >
			  </div>
			  
			  <div>
			  	<textarea rows="20" cols="" class="form-control" readonly="readonly" style="resize: none; background-color: white;">아니 도대체 언제 환불해주는거에요 환불좀 해주세요.</textarea>
			  </div>																<!-- style="resize: none" => textarea 의 크기 고정 -->
			 
			 <div class="row my-4"> <!-- 첨부 사진들 -->
			 	<h4>첨부사진</h4>
			 	<div class="col-md-3">
			 		<img alt="" src="img/바지튕기기.gif" width="100%">
			 	</div>
			 	<div class="col-md-3">
			 		<img alt="" src="img/바지튕기기.gif" width="100%">
			 	</div>
			 	<div class="col-md-3">
			 		<img alt="" src="img/바지튕기기.gif" width="100%">
			 	</div>
			 	<div class="col-md-3">
			 		<img alt="" src="img/바지튕기기.gif" width="100%">
			 	</div>
			 </div>
			 
			  <br><br><br><Br>
			<!-- 관리자가 답변한 내용 -->
			<H3 class=""> 관리자 답변내용</H3>
			<div>
				<textarea rows="20" cols="" class="form-control" readonly="readonly" style="resize: none;">쉿!나의 작은 아기 고양이.. 환불은 없다.</textarea>
			</div>
			
		</div>
	
	<br><br><br><br><br><br><br><br><br><br><br>
	<%@include file="footer.jsp" %>
	
</body>
</html>