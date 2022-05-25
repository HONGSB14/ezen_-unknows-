let pass= false;

$(function(){
	
	//차량 등록 시
	$("#carnum").keyup(function(){
		
		let carnum=$("#carnum").val();
		let carnumc=/^([0-9]{2,3})([가-힣]{1,1})([0-9]{4,4})$/;
		let carnumc2=/^([가-힣]{2,2})([0-9]{2,3})([가-힣]{1,1})([0-9]{4,4})$/;
		if(carnumc.test(carnum) || carnumc2.test(carnum) ){
			$("#carcheck2").html(""); pass=true;
		}else{
			$("#carcheck").html(""); 
			$("#carcheck2").html("차량 번호를 다시 확인해주세요."); pass=false;
		}
		
	});


});


function carResistration(){
	
	let check = true;

	if(pass==check){
		document.getElementById("carResistrationForm").submit();
	}else{
		alert("올바른 정보를 기입하여 주십시오.");
	}
	
};
