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
			<h3 class="text-center titleline">���� �� </h3>
			
			<!--  ��ư -->
			<div >
				<a href="boardlist.jsp"><button class="btn btn-outline-info">��Ϻ���</button></a>
				<a href="boardlist.jsp"><button class="btn btn-outline-info">����</button></a> <!-- �ۼ��� ���� [ ��ȿ�� �˻� ] -->
				<a href="boardlist.jsp"><button class="btn btn-outline-info">����</button></a> <!-- �ۼ��ڸ� ���� [��ȿ�� �˻�]  -->
				<a href="boardlist.jsp"><button class="btn btn-outline-info">�亯</button></a> <!-- �ۼ��ڸ� ���� [��ȿ�� �˻�]  -->
			</div>
			<!-- �ۼ��� �Է��� ���� -->
			  <div>
			  	<div><span>�ۼ���</span><span>���缮</span></div>
			  	<div><span>���ǳ�¥</span><span>2022-04-24 10:10</span></div>
			  </div>
			  
			  <div>
			  	<input type="text" value="ȯ����.." class="form-control" readonly style="background-color: white;" >
			  </div>
			  
			  <div>
			  	<textarea rows="20" cols="" class="form-control" readonly="readonly" style="resize: none; background-color: white;">�ƴ� ����ü ���� ȯ�����ִ°ſ��� ȯ���� ���ּ���.</textarea>
			  </div>																<!-- style="resize: none" => textarea �� ũ�� ���� -->
			 
			 <div class="row my-4"> <!-- ÷�� ������ -->
			 	<h4>÷�λ���</h4>
			 	<div class="col-md-3">
			 		<img alt="" src="img/����ƨ���.gif" width="100%">
			 	</div>
			 	<div class="col-md-3">
			 		<img alt="" src="img/����ƨ���.gif" width="100%">
			 	</div>
			 	<div class="col-md-3">
			 		<img alt="" src="img/����ƨ���.gif" width="100%">
			 	</div>
			 	<div class="col-md-3">
			 		<img alt="" src="img/����ƨ���.gif" width="100%">
			 	</div>
			 </div>
			 
			  <br><br><br><Br>
			<!-- �����ڰ� �亯�� ���� -->
			<H3 class=""> ������ �亯����</H3>
			<div>
				<textarea rows="20" cols="" class="form-control" readonly="readonly" style="resize: none;">��!���� ���� �Ʊ� �����.. ȯ���� ����.</textarea>
			</div>
			
		</div>
	
	<br><br><br><br><br><br><br><br><br><br><br>
	<%@include file="footer.jsp" %>
	
</body>
</html>