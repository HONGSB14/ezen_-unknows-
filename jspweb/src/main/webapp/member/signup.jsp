<%@page import="controller.Idcheck"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		
		span{color: red;}
		.checkinfo{color: blue;}
	
	</style>
</head>
<body>

<%@include file = "../header.jsp" %>

<div class="container" style="border: solid 1px black ;  border-radius: 20px;">
	<div class="col-md-12 text-center" >
		<form  id="signupform" action="../signup" method="post">
			<div class="offset-4 col-md-4">
					<div class="offset-4 col-md-4"> <!--  아이디 입력 -->
					아이디  <input type="text" id="mid" name="id" placeholder="user id">
					<br>
					<span id="idchack"></span>
					<span id="idchack1" class="checkinfo"></span>
				</div>
				
				<div class="offset-4 col-md-4"> <!-- 비밀번호 입력 -->
					비밀번호  <input type="password"  name="password" id="mpassword" placeholder="user password">
					<br>
					비밀번호 확인  <input type="password"  id="mpasswordcheck" placeholder="password chack">
					<br>
			
					<span id="passwordchack" ></span>
					<span id="passwordchack1" class="checkinfo"></span>
				</div>
				
				<div class="offset-4 col-md-4">
					이름 <input type="text" id="mname"  name="mname" placeholder="user name">
					<br>
					<span id="namechack"></span>
					<span id="namechack1" class="checkinfo"></span>
				</div>
				
				<div class="offset-4 col-md-4">
					연락처  <input type="text" id="mphone" name="mphone"  placeholder="user phone">
					<br>
					<span id="phonechack"></span>
					<span id="phonechack1" class="checkinfo"></span>
				</div>
			
				<div class="offset-4 col-md-4">
					이메일  <input type="text" id="memail" name="memail"  placeholder="user email">@
					<input type="text" id="memailaddress"  name="memailaddress" placeholder="직접입력">
					<select id="emailselect">
					<option value="">직접입력</option>
					<option value="naver.com">naver.com</option>
					<option value="nate.com">nate.com</option>
					<option value="daum.net">daum.net</option>
					</select>
					<br>
					<span id="emailcheck"></span>
					<span id="emailcheck1" class="checkinfo"></span>
				</div>
			
				<div class="offset-4 col-md-4">
						<!-- 다음 주소 API -->
					<input type="text" id="sample4_postcode"  name="address1" placeholder="우편번호">
					<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="sample4_roadAddress"  name="address2" placeholder="도로명주소">
					<input type="text" id="sample4_jibunAddress" name="address3"  placeholder="지번주소">
					<span id="guide" style="color:#999;display:none"></span>
					<input type="text" id="sample4_detailAddress"  name="address4"placeholder="상세주소">
					<br>
					<span id="addresscheck"></span>
					<span id="addresscheck1" class="checkinfo"></span>
					<button onclick="signup()" type="button">가입하기</button>
				</div>
		
			</div>
		</form>
	
	</div>
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../js/signup.js" type="text/javascript"></script>

<%@include file = "../footer.jsp"%>

</body>
</html>