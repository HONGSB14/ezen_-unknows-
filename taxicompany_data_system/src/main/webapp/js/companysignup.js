//회사등록 유효성 검사 조건: 모두 true 라면 가입 성공
let pass=[false,false,false,false,false];

$(function(){ //문서 시작
	
	//가격 선택 란
	$("#selectprice").change(function(){

		let selectprice=$("#selectprice").val();
		
		if(selectprice=="1" || selectprice=="2" || selectprice=="3"){
			$("#pricecheck").html("가격이 선택되었습니다."); pass[0]=true;			
		}else{
			$("#pricecheck").html("");pass[0]=false;	
		}
	});
	
	//사업자 등록번호 입력 란 1
	$("#three").keyup(function(){
		
		let three=$("#three").val();
		let threec=/^[0-9]{3,3}$/;
		
		if(threec.test(three)){
		    $("#bncheck2").html("");	pass[1]=true;		
		}else{
			 $("#bncheck2").html("유효하지 않은 번호입니다.");	pass[1]=false;
		}
	});
	//사업자 등록번호 입력 란 2
	$("#two").keyup(function(){
		
		let two=$("#two").val();
		let twoc=/^[0-9]{2,2}$/;
		
		if(twoc.test(two)){
			 $("#bncheck2").html(""); pass[1]=true;		
		}else{
			 $("#bncheck2").html("유효하지 않은 번호입니다.");	pass[1]=false;
		}
	});
	//사업자 등록번호 입력 란 3
	$("#five").keyup(function(){
		
		let five=$("#five").val();
		let fivec=/^[0-9]{5,5}$/;
		
		if(fivec.test(five)){
		 	
		  $("#bncheck2").html("");
		  $("#bncheck").html("가입가능한 번호입니다.");	pass[1]=true;		
		}else{
			
		  $("#bncheck").html("");
		  $("#bncheck2").html("유효하지 않은 번호입니다.");	pass[1]=false;
		}
	});
	
	//회사명 입력 란
	$("#companyname").keyup(function(){
		
		
		
		let companyname=$("#companyname").val();
		let companynamec=/^[0-9,가-힣,A-Z,a-z]{1,20}$/;
		
		if(companynamec.test(companyname)){
		 $("#namecheck2").html("");
		 $("#namecheck").html("가입할 수 있는 회사명 입니다.");	pass[2]=true;
		 $("#cuum").ready(function(){
		
			const ran=Math.random();
			const random=Math.floor(ran*899999+100000);
			
			if(pass[2]== true && pass[1]== true){
					$("#cnum").val(random);
			}	
	});
		
		}else{
			$("#namecheck").html("");
		  $("#namecheck2").html("가입할 수 없는 회사명 입니다.");	pass[2]=false;	
		}
		
	});
	
	
	//결제은행 입력 란
	
	//신한
	$("#sinhanbank").click(function(){
		pass[3]=true;
		$('input[name="bank"]:checked').val();	
	});
	//카카오
	$("#kakaobank").click(function(){
		pass[3]=true;
		$('input[name="bank"]:checked').val();	
		
	});
	//국민
	$("#kbbank").click(function(){
		pass[3]=true;
		$('input[name="bank"]:checked').val();	
	});
	//농협
	$("#nhbank").click(function(){
		pass[3]=true;
		$('input[name="bank"]:checked').val();	
	});
	//기업
	$("#ibkbank").click(function(){
		pass[3]=true;
		$('input[name="bank"]:checked').val();	
	});
	//하나
	$("#hanabank").click(function(){
		pass[3]=true;
		$('input[name="bank"]:checked').val();	
	});
	//우리
	$("#wooribank").click(function(){
		pass[3]=true;
		$('input[name="bank"]:checked').val();	
	});
	//수협
	$("#shbank").click(function(){
		pass[3]=true; 
		$('input[name="bank"]:checked').val();	
	});
	
	
	//계좌번호 등록
	$("#account").keyup(function(){
		
		let account = $("#account").val();
		let accountc= /^[0-9]{12,12}$/;
		
		if(accountc.test(account)){
			$("#accountcheck2").html("");
			$("#accountcheck").html("사용가능한 계좌번호입니다."); pass[4]=true;
		}else{
			$("#accountcheck").html("");
			$("#accountcheck2").html("사용 할 수 없는 계좌번호입니다."); pass[4]=false;
		} 
		
		
		
	});
	

	
	
}); //문서 끝

function companysignup(){

	let check = true;
	
	for(let i = 0; i<pass.length; i++){
		
		if(pass[i] == false){
			check =false;
		}
	}
	if(check){
		document.getElementById("companysignupform").submit();
	}else{
		alert("필수 입력 사항이 모두 입력되지 않았습니다.")
	}
}
	
	
	
	


