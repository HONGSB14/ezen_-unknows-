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
		//키에 입력되는 값 flux 변수에 입력
		let flux =$("#flux").val();
		//천단위 쉼표 제거 
		flux=flux.replace(/,/g, "");
		//천단위 쉼표 생성변수
		let fluxcomma =flux.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		//flux 에 쉼표생성변수 입력
		$("#flux").val(fluxcomma);
		//유효성 검사
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
		fee=fee.replace(/,/g, "");
		let feecomma=fee.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		$("#fee").val(feecomma);
		
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
		
		cardfee=cardfee.replace(/,/g, "");
		let cardfeecomma=cardfee.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		$("#cardfee").val(cardfeecomma);
		
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
		
		daysale=daysale.replace(/,/g, "");
		let daysalecomma=daysale.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		$("#daysale").val(daysalecomma);
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
	//기본 체크값 true 로 설정
	let check = true;
	
	
	
	//유량 컴마제거
	let flux=$("#flux").val();
	fluxcheck=flux.replace(/,/g, "");
	$("#flux").val(fluxcheck);
	
	//실입요금 컴마제거
	let fee=$("#fee").val();
	 feecheck= fee.replace(/,/g, "");
	$("#fee").val(feecheck);
	
	//카드요금 컴마제거
	let cardfee=$("#cardfee").val();
	cardfeecheck=flux.replace(/,/g, "");
	$("#cardfee").val(cardfeecheck);
	
	//총 매출 컴마제거
	let daysale=$("#daysale").val();
	daysalecheck=flux.replace(/,/g, "");
	$("#daysale").val(daysalecheck);
	
	
	
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