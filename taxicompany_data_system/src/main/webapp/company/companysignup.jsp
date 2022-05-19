<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">


</head>
<body>
	<%@include file="../header.jsp" %>
	
	
	<div id="companysignup" class="container">
		<!-- 기입란 -->
		<div class="col-md-12 py-5">
			<div class="offset-4 col-md-4">
							
			<form id="companysignupform" method="get" action="../CompanySignup">
				<div class="py-3">
					<!-- 가격 선택 란 -->
					<div class="col-md-4 text-left">가격선택 (월)<Strong class="must_do">*</Strong></div>
					<div>
						<select name="price" class="form-control text-center" id="selectprice">
						
							<option id="first">원하시는 가격을 선택해 주세요.</option>
							<option value="1">120,000원</option>
							<option value="2">150,000원</option>
							
						</select>
					</div>
					<!-- 중복체크 안내표시 -->
					<span id="pricecheck"></span>
				</div>
				
				<!-- 사업자 등록번호 입력 란  -->
				<div class="py-3">
					<div class="col-md-4 text-left">사업자 등록번호<Strong class="must_do">*</Strong></div>
					<div class="row">
						<div class="col-md-2">
							<input id="three" name="three" type="text" class="form-control">
						</div>
						
						<div class="col-md-3 text-bottom">
						<input id="two" name="two" type="text" class="form-control">
						</div>
						
						<div class="col-md-7">
						<input id="five" name="five" type="text" class="form-control">
						</div>
					</div>
					<!-- 중복체크 안내표시 -->
					<span id="bncheck"></span>
					<span class="check" id="bncheck2"></span>
				</div>
				
				<!-- 회사이름  입력 란 -->
				<div class="py-3">
					<div class="col-md-4 text-left">회사 이름<Strong class="must_do">*</Strong></div>
					<div>
						<input type="text" class="form-control" name="companyname" id="companyname">
					</div>
					<!-- 중복체크 안내표시 -->
					<span id="namecheck"></span>
					<span class="check" id="namecheck2"></span>
				</div>
				
				<!-- 회사가입고유번호  입력 란 -->
				<div class="py-3">
					<div class="col-md-4 text-left">가입고유번호<Strong class="must_do">*</Strong></div>
					<div>
						 <input value="" type="text" name="cnum" id="cnum" class="form-control" readonly="readonly" >
					</div>
				</div>
				<!-- 결제은행 입력 란-->
				<div class="py-3">
					<div class="col-md-4 text-left">결제은행<Strong class="must_do">*</Strong></div>
					<div class="py-2">
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="bank" id="sinhanbank" value="sinhan">
						  <label class="form-check-label" for="sinhanbank">신한은행</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="bank" id="kakaobank" value="kakao">
						  <label class="form-check-label" for="kakaobank">카카오뱅크</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="bank" id="kbbank" value="kb">
						  <label class="form-check-label" for="kbbank">KB국민은행</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="bank" id="nhbank" value="nh">
						  <label class="form-check-label" for="nhbank">농협</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="bank" id="ibkbank" value="ibk">
						  <label class="form-check-label" for="ibkbank">IBK기업은행</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="bank" id="hanabank" value="hanabank">
						  <label class="form-check-label" for="hanabank">하나은행</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="bank" id="wooribank" value="woori">
						  <label class="form-check-label" for="wooribank">우리은행</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="bank" id="shbank" value="sh">
						  <label class="form-check-label" for="ishbank">수협</label>
						</div>
					</div>
				</div>
				
				<!-- 은행 계좌번호 입력 란-->
				<div class="py-3">
					<div class="col-md-4 text-left">계좌번호<Strong class="must_do">*</Strong></div>
					<div>
					 	<input type="text" class="form-control" name="account" id="account" placeholder="-를 제외하고 입력하여 주십시오.">
					</div>
					<span id="accountcheck"></span>
					<span class="check" id="accountcheck2"></span>
				</div>
				
			</form>
			<!-- 등록버튼 -->
			<div class="offset-4 col-md-4 py-5">
				<input class="form-control" type="submit" onclick="companysignup()" value="등록하기">
			</div>
			
			</div>
		</div>
	</div>
	<script src="../js/companysignup.js" type="text/javascript"></script>
	<%@include file ="../footer.jsp" %>
</body>
</html>