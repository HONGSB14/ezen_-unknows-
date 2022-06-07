
let pass=[false,false,false,false];

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
	$("#daysale").focus(function(){
		
		//실입요금 컴마제거
		let fee=$("#fee").val();
		feecheck= fee.replace(/,/g, "");
		
		//카드요금 컴마제거
		let cardfee=$("#cardfee").val();
		cardfeecheck=cardfee.replace(/,/g, "");

		//더하기 연산을 하기 위해 숫자로 변환
		let feesum=parseInt(feecheck);
		let cardsum=parseInt(cardfeecheck);
		let sum=feesum+cardsum;
		
		//연산한 값을 다시 문자열로 변환
		let sumsum=String(sum);
		
		//천단위컴마 실행
		let sumcomma=sumsum.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		
		//컴마를 daysale에 삽입
		$("#daysale").val(sumcomma);
		
	});


	
});//문서종료

//유효성 검사
function salecheck(){

	//유량 컴마제거
	let flux=$("#flux").val();
	fluxcheck=flux.replace(/,/g, "");
	$("#flux").val(fluxcheck);
	
	//실입요금 컴마제거
	let fee=$("#fee").val();
	 feecheck= fee.replace(/,/g, "");
	$("#fee").val(feecheck);
	
	//카드요금 제거
	let cardfee=$("#cardfee").val();
	cardfeecheck=cardfee.replace(/,/g, "");
	$("#cardfee").val(cardfeecheck);
	
	//총 매출 컴마제거
	let daysale=$("#daysale").val();
	daysalecheck=daysale.replace(/,/g, "");
	$("#daysale").val(daysalecheck);
	

	//기본 체크값 true 로 설정
	let check = true;
	
	for(let i = 0; i<pass.length; i++){
		
		if(pass[i] == false){
			check = false;
		}
	}
	if(check){
		document.getElementById("saleform").submit();
	}else{
		alert("등록 할 수 없는 형식입니다.");
	}
}

//컴마 제거 함수
function commaDelete(){
	//유량 컴마제거
	let flux=$("#flux").val();
	fluxcheck=flux.replace(/,/g, "");
	$("#flux").val(fluxcheck);
	
	//실입요금 컴마제거
	let fee=$("#fee").val();
	 feecheck= fee.replace(/,/g, "");
	$("#fee").val(feecheck);
	
	//카드요금 제거
	let cardfee=$("#cardfee").val();
	cardfeecheck=cardfee.replace(/,/g, "");
	$("#cardfee").val(cardfeecheck);
	
	//총 매출 컴마제거
	let daysale=$("#daysale").val();
	daysalecheck=daysale.replace(/,/g, "");
	$("#daysale").val(daysalecheck);
}

//삭제 버튼
function saleDelete(cnum){
	alert("정말 삭제를 진행하시겠습니까?");
	
	
 	let check=$('input:checkbox[name="salecheckbox"]').is(":checked") == true
 	let date=$("#date2").val();
 	
 	if(check==true){

			$.ajax({
				url:"../slip/SaleDelete",
				data:{"date":date,"cnum":cnum},
				success:function(data){
					if(data==1){
						alert("선택 하신 매출이 삭제 되었습니다.");
						location.reload();
					}else{
						alert("삭제진행 오류가 발생하였습니다. 관리자에게 문의해주십시오.");
						location.reload();
					}
				}
			});
	}
}

//업데이트 버튼
function saleUpdate(cnum){
	
	let date=$("#date2").val();
	let carnum=$("#carnum2").val();
	let flux=$("#flux2").val();
	let fee=$("#fee2").val();
	let cardfee=$("#cardfee2").val();
	let daysale=$("#daysale2").val();
	let note=$("#note2").val();
	
	commaDelete();
		
		$.ajax({
				url:"../slip/SaleUpdate",
				data:{"cnum":cnum,"date":date,"carnum":carnum,"fee":fee,"cardfee":cardfee,"note":note,"flux":flux,"daysale":daysale},
				success:function(data){
					if(data==1){
						alert("매출수정이 완료 되었습니다.");
						location.reload();
					}else{
						alert("수정진행 오류가 발생하였습니다. 관리자에게 문의해주십시오.");
						location.reload();
					}
				}
			});
}
