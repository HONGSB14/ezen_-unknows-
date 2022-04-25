<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
	<style type="text/css">
	
	*{box-sizing: border-box;  }
	#mainimage{
			
			width:1400px;
			height: 700px;
			background-image: url("img/main.jpg");
			background-repeat: no-repeat;
			background-position: top center;
			text-align: center;
			margin: 0 auto;
	}
	#textbox{
		width: 1400px;
		height: 100px;
		margin: 0 auto;
	}
	#textboxA{
		width:690px;
		height: 98px;
		float: left;
	}
	#a{
		float: left;
		padding-left: 100px;
		padding-top: 10px;
	}
	#a1{
		float: right;
		padding-right: 100px;
		padding-top: 25px;
	}
	#textboxB{
		width:690px;
		height: 98px;
		float: right;
	}
	#b{
		float: left;
		padding-top: 10px;
	}
	#b1{
		float: right;
		padding-right:200px;
		padding-top: 25px;
	}
		
	</style>
</head>
<body>
	<div id="mainimage">
	</div>
	
	<div id="textbox">
		<div id="textboxA">
			<div style="width: 100px;" id="a"><img alt="" src="img/icon1.png"></div>
			<div id="a1"><strong>예약안내</strong><br>스튜디오 대여는 사전에 인터넷 예약을 하셔야합니다.<br>예약은 1일 이전까지 가능합니다.</div>
		</div>
		<div id="textboxB">
			<div id="b" style="width: 100px;"><img alt="" src="img/icon2.png"></div>
			<div id="b1"><strong>포토 갤러리</strong><br>취업사진,가족사진,프로필사진,보정 사진 등 개인 또<br>는 단체가 블라블라블라.</div>
		</div>
	</div>

</body>
</html>