<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<!-- ���� ��ġ(����)�� �ٸ� ���� �����ϱ� -->
	<%@include file = "header.jsp" %>
	<br><br><br><br><br><br>
	<!--  ĳ���� : �̹��� �����̵�  -->
	<div id="cs" class="carousel slide" data-bs-ride="carousel" data-bs-interval="2000">
								<!-- �����̵�ð� : �и���(1000/1��)  -->
		<!-- �����̵� ��ġ ǥ�� : �ε������� -->
		<div class="carousel-indicators">
			<button data-bs-target="#cs" data-bs-slide-to="0" class="active"></button>
			<button data-bs-target="#cs" data-bs-slide-to="1"></button>
			<button data-bs-target="#cs" data-bs-slide-to="2"></button>
			
		</div>
		 <!-- ĳ���� ���빰 -->
		<div class="carousel-inner">
			<div class="carousel-item active"> <img alt="" src="img/main1.jpg"> </div>
			<div class="carousel-item "> <img alt="" src="img/main2.jpg"> </div>
			<div class="carousel-item "> <img alt="" src="img/main3.jpg"> </div>
			
		</div>
		<!-- �̹��� �̵� ��ư -->
		<button class="carousel-control-prev" data-bs-target="#cs" data-bs-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</button>
		<button class="carousel-control-next" data-bs-target="#cs" data-bs-slide="next">
			<span class="carousel-control-next-icon"></span>
		</button>
		
	</div> <!-- ĳ���� end -->
	<br><br>
	
	<div class="container text-center" >
		<hr><!-- �Ǽ� -->
		<h3>new ARRIVAL</h3>
		<p>�Ż�ǰ</p>
	</div>
	
	<div class="container">
		<div class="row">
		<%for(int i=0; i<8; i++) {%>
			<div class=" col-md-3 col-sm-6">
				<div class="card"><!--  ī������ -->
					<img alt="" src="img/����ƨ���.gif">
					<div class="card-body">
						<p class="item">
						<span class="title">���Ĵٵ� ���� ġ����</span>
						 <br>
						 �������� �԰�ٴϴ� ���Ŀ��Ŀ���
							<span class="content">[1+1] ������� �лл�!!
							</span>
							<br>
							<span class="price1">79,000��</span> 
							<span class="price2">75,900��</span>
							<br>
							<span class="badge bg-warning text-dark my-3 ">�ֹ�����</span>
							<span class="badge bg-warning text-dark my-3 ">ǰ���ӹ�</span>
							
						</p>
					</div>
				</div> 
			</div>
			<%} %>
		</div>
	</div>
	<Br>
	<Br>
	<Br>
	<%@include file = "footer.jsp" %>
	
</body>
</html>