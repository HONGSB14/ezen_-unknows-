let pass=[false,false,false,false,false]
$(function(){
	

	//차번호 유효성검사
	$("#carnum").keyup(function(){
		
		let carnum =$("#carnum").val();
		
		let carnumc=/^([0-9]{2,3})([가-힣]{1,1})([0-9]{4,4})$/;
		
		if(carnumc.test(carnum)){
			pass[0]=true;
		}else{
			pass[0]=false;
		}
		
	});
	
	
	//유량
	$("#flux").keyup(function(){
		
		let flux =$("#flux").val();
		
		let fluxc=/^([1-9]{1,1})([0-9]*)$/;
		let fluxc2="0";
		
		
		if(fluxc.test(flux)||fluxc2==flux){
			pass[1]=true;
		}else{
			pass[1]=false;
		}
		
	});
	
	//실입요금
	$("#fee").keyup(function(){
		
		let fee =$("#fee").val();
		
		let feec=/^([1-9]{1,1})([0-9]*)$/;
		let feec2="0";
		
		
		if(feec.test(fee)||feec2==fee){
			pass[2]=true;
		}else{
			pass[2]=false;
		}
		
	});
	
	//카드요금
	$("#cardfee").keyup(function(){
		
		let cardfee =$("#cardfee").val();
		
		let cardfeec=/^([1-9]{1,1})([0-9]*)$/;
		let cardfeec2="0";
	
		
		if(cardfeec.test(cardfee)||cardfeec2==cardfee){
			pass[3]=true;
		}else{
			pass[3]=false;
		}
		
	});
	
	//총 매출
	$("#daysale").keyup(function(){
		
		let daysale=$("#daysale").val();
		
		let daysalec=/^([1-9]{1,1})([0-9]*)$/;
		let daysalec2="0";
		
		
		if(daysalec.test(daysale)||daysalec2==daysale){
			pass[4]=true;
		}else{
			pass[4]=false;
		}
		
	});


	
});//문서종료

function salecheck(){
	let check = true;
	
	for(let i = 0; i<pass.length; i++){
		
		if(pass[i] == false){
			check =false;
		}
	}
	if(check){
		document.getElementById("saleform").submit();
	}else{
		alert("등록 할 수 없는 형식입니다.");
	}
}