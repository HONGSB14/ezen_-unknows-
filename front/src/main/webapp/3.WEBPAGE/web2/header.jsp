<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		li{list-style-type: nonoe;}
		a{text-decoration: none; color: black;}
		#header{height: 200px;}
		#box{width:1100px; margin: 0 auto;}
		#logo{ margin:50px 0 0 50px}
		#logo{float: left;}
		#menus{float: right; text-align: right;}
		#menus li{display: inline;}
		
		@font-face {
		  	font-family: 'SuncheonB';
		    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2202-2@1.0/SuncheonB.woff') format('woff');
		    font-weight: normal;
		    font-style: normal;
		}
		
		#top_menu{ margin: 30px 0px;}
		#main_menu li{font-family:"SuncheonB"; font-size: 20px; margin: 50px 0 0 100px;}
	</style>

</head>
<body>
	<div id="header"><!-- 헤더 전체 -->
		<div id="box"><!-- 헤더 박스권 -->
			<div id="logo"> <!--  로고 -->
				<img alt="" src="img/logo.png">
			
			</div>
			
			<div id="menus"> <!-- 메뉴 -->
				<ul id="top_menu"><!-- 상단메뉴 -->
					<li>김지영(jkkim)님 | </li>
					<li><a href="#">로그아웃</a> |</li>
					<li><a href="#">정보수정</a> </li>
				</ul>
					
				<ul id="main_menu">
					<li><a href="index.jsp">HOME</a> </li>
					<li><a href="#">출석부</a> </li>
					<li><a href="galley.jsp">작품갤러리</a> </li>
					<li><a href="#">게시판</a> </li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>