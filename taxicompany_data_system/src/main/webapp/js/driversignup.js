let pass=false;
$(function(){

	$("#dname").keyup(function(){
		
		let dname =$("#dname").val();
	    let dnamec= /^[가-힣,a-z,A-Z]{2,10}$/;
	
	    if(dnamec.test(dname)){
	        $("#namecheck2").html("");
	        $("#namecheck").html("멋진이름이네요!");  pass=true;
	    }else{
	        $("#namecheck").html("");
	        $("#namecheck2").html("아쉽지만 등록할 수 없는 이름입니다."); pass=false;
	    }
		
		
		
	});
});

function driverSignup(){
	
	let check = true;

	if(pass==check){
		document.getElementById("driverSignupForm").submit();
	}else{
		alert("올바른 정보를 기입하여 주십시오.");
	}
	
};
