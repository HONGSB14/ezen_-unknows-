<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

</head>
<body>
	<%@include file ="../header.jsp"%>
	
		<%
		String mid=(String)session.getAttribute("login");
		
		%>
	
	<div class="container">

		<div class="row">
					
			<div class="col-md-3"> <!--  사이드바 -->
				사이드바
			</div>	
			
			<div class="col-md-9"> <!--  본문 -->
				
					 <input type="password" name="mpassword" id="mpassword">
					<button id="btnconfirm" onclick="passwordcheck('<%=mid%>')">탈퇴</button>
					<span id="checkmsg"></span>
					<button id="btndelete" style="display: none;" onclick="mdelete('<%=mid%>')">탈퇴승인</button>
				
			</div>
		</div>
	</div>
	<script src="../js/info.js"  type="text/javascript"></script>
	
	
	<%@include file="../footer.jsp" %>
</body>
</html>