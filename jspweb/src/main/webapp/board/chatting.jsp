<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/jspweb/css/chatting.css" rel="stylesheet">
</head>
<body>
	<%@include file="../header.jsp" %>
	<div class="container m-5">
		<!-- 채팅 관련 구역 -->
		<div class="col-sm-6 offset-3">
			
			
			<div class="row">
				<!-- 접속자 목록 표시 구역  =js -->
				<div class="col-sm-4">
					<h5 class="enter_title">접속자 목록</h5>
					
					<!-- 접속자 정보 box -->
					<div class="row">	
						<!-- 프로필 이미지 표시 구역 -->
						<div class="col-sm-4">
							<img alt="" src="../img/티모.jpg" class="rounded-circle" width="100%">
							
						</div>
						<!-- 접속자 이름, 기능 -->
						<div class="col-sm-8">
							<div class="member_name"> 티모</div>
						</div>
						<div class="btnbox">
							<span>귓말</span>
							<span>친추</span>
						</div>
						
					</div>
					
				</div>
				
				
				
				<!-- 채팅창 구역 -->
				<div class="col-sm-8">
					<!-- 메시지 표시 구역 -->
					<div class="contentbox">
						<!-- 보내는 메시지 -->
						<div class="secontent">
							<span class="date"> 오전 10:07 </span>
							<span class="content"> 안녕하세요 </span>
						</div>
						
						<div class="alarm">
							<span>"티모"님이 두두두둥장</span>
						</div>
						
						<!-- 받는 메시지 -->
						<div class="row g-0">
							<div class="col-sm-1 mx-2">
								<img alt="" src="../img/티모2.jpg" class="rounded-circle" width="100%">
							</div>
							
							<div class="col-sm-9">
								<div class="recontent">
									<div class="member_name"> 
										티모
									</div>
									<span class="content">하이</span>
									<span class="date">오전 10시:07</span>
								</div>
							</div>
						</div>
					</div>
					
						
						<div class="row g-0">
						 	<!-- 입력창 -->
							<div class="col-sm-10">
								<textarea rows="1" cols="" class="form-control" onkeyup="enterkey()" id="incontent"></textarea>
							</div>
							<!-- 전송버튼 -->
							<div class="col-sm-2">
								<button id="sendbtn" class="form-control" onclick="sendbtn()">전송</button>
							</div>
							
							<div class="btnbox">
								<span>첨부파일</span>
								<span>이모티콘</span>
							</div>
						</div>
				</div>
			</div>
		</div>
		
	</div>
	
	<%@include file="../footer.jsp" %>
	<script type="text/javascript" src="/jspweb/js/chatting.js"></script>
</body>
</html>