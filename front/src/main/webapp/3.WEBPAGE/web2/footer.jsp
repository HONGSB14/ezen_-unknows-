<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#footer{clear: both; background-color: gray; color: white;}
	#footer_menu{padding: 20px; font-weight: bold;}
	#footer_menu li{
		display: inline;
		margin-right: 30px;
	}
	#footer .items{
		float: left;
		margin: 50px 0 0 100px;
	}
	#footer .h3{
		color: #088a69;
	}
	#footer .items ul { margin-top: 10px;}
	#footer .items li { margin-top: 10px;}
	#footer .items #phone{
	font-size: 25px; font-weight: bold;
	}
</style>

</head>
<body>
	<div id="footer">
	
		<div id="box">
			<ul id="footer_menu">
				<li>the 베이킹 소개</li>
				<li>개인정보처리방침</li>
				<li>저작권 정보</li>
				<li>이용 안내</li>
			</ul>
			
			<div class="items">
				<h3>문의전화</h3>
				<ul>
					<li>123-1234</li>
					<li>10~18 (점심시간 ####)</li>
				</ul>
			</div>
			<div class="items">
				<h3>the베이킹</h3>
				<ul>
					<li>주소:경기 제주시 애월</li>
					<li id="phone">전화:345-9436-3456</li>
					<li>팩스:345-943-63-4597776</li>
					<li>이메일:34594@363456</li>
				</ul>
			</div>
			<div class="items">
				<h3>입금정보</h3>
				<ul>
					<li>입금주 : 홍딸딸</li>
					<li>농협:345-9436-3456</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>