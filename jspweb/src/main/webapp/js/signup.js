// jquery(제이쿼리) : js 프레임워크
	// $ : 제이쿼리 ( 라이브러리 설치 : header파일 body 태그에 cdn 추가 )
// $( function() { 실행문; } ) : 문서 실행시 무조건 실행되는 함수
	// js식		id불러오기  .getElementById( id명 )
	// jquery식	id불러오기  $("#id명")
		// keyup(  ) : 해당 id에 키보드가 눌렸을때 [ 입력 되었을때 ]


//회원가입 통과를 위한 배열 생성 (체크스위치)
	let pass=[ false ,false, false, false, false ,false ,false];  // 배열 = [ ]
	
	

//아이디 체크
$(function(){  // 문서 열리면 해당 코드가 실행 
	
	// 아이디 체크
	$("#mid").keyup( function(){	// mid 가 입력될때마다 해당 함수 실행
		// 1. HTML 태그내 값 가져오기 
		let mid=document.getElementById("mid").value;
		// 2. HTML 태그id 가져오기
		let idchack = document.getElementById("idchack");
		
		// 정규표현식 : 특정한 규칙을 가지는 문자열의 집합 언어
		let idj = /^[a-zA-Z0-9]{5,15}$/;	// 한글을 제외한 영문+숫자 5~15 사이 문자열
			// /^ : 정규표현식 시작
			// $/ : 정규표현식 끝 
			// [a-z] : 소문자 찾음
			// [A-Z] : 대문자 찾음
			// [0-9] : 숫자 찾음
			// { 최소길이 , 최대길이 } : 문자 최소길이~최대길이 까지만 입력
				// 정규표현식.test( 변수 ) : 해당 변수가 정규표현식에 동일하면 true 다르면 false
		if( idj.test( mid) ){ // 정규표현식과 같으면
			
			//아이디 중복체크
				//비동기식 통신[목적 : 페이지 전환이 없이 ]
			$.ajax({
				url: "../Idcheck",			//해당 서블릿의 경로
				data: {"mid":mid},			//해당 경로로 보내는 데이터
				success :function(result){	//해당 경로에서 받은 데이터
					if(result==1){
						
						idchack.innerHTML="사용중인 아이디 입니다.";  pass[0] = false;
													//체크 스위치
						
					}else{
						idchack.innerHTML="사용가능한 아이디 입니다.";  pass[0]= true;
					}
				}
			});
			
			
		}else{
			idchack.innerHTML = "영문 , 숫자 포함 5~15길이로 입력해주세요.";	pass[0] = false;
		}
		
		console.log(pass[0]);
	}); // keyup end 
	// 비밀번호 체크 
	$("#mpassword").keyup( function(){  // 비밀번호 입력할때마다
		// let mpassword = document.getElementById("mpassword").value;  // js식
		let mpassword = $("#mpassword").val();  // jquery 식
		let mpasswordcheck = $("#mpasswordcheck").val();
		
		let passswordj = /^[a-zA-Z0-9]{5,15}$/; // 정규표현식
		
		if( passswordj.test( mpassword ) ){ // 정규표현식 같으면
			if( mpassword != mpasswordcheck ){ // 비밀번호 와 비밀번호체크 와 다르면
				// equals(x)  //  != ( o )
			$("#passwordchack").html("패스워드가 서로 다릅니다.");		pass[1] = false;
			}else{
				$("#passwordchack").html("");
				$("#passwordchack1").html("사용 가능한 비밀번호 입니다."); pass[1] =true;
			
			}
		}else{ // 정규현식 다르면
			$("#passwordchack").html("영소문자 5~15 사이로 입력해주세요!"); pass[1] = false;
		}
		
	}); // keyup end 
	
	// 비밀번호확인 체크 
	$("#mpasswordcheck").keyup( function(){  // 비밀번호 입력할때마다
		// let mpassword = document.getElementById("mpassword").value;  // js식
		let mpassword = $("#mpassword").val();  // jquery 식
		let mpasswordcheck = $("#mpasswordcheck").val();
		
		let passswordj = /^[a-zA-Z0-9]{5,15}$/; // 정규표현식
		
		if( passswordj.test( mpasswordcheck ) ){ // 정규표현식 같으면
			if( mpassword != mpasswordcheck ){ // 비밀번호 와 비밀번호체크 와 다르면
				// equals(x)  //  != ( o )
			$("#passwordchack").html("패스워드가 서로 다릅니다.");			pass[2] = false;
			}else{
				$("#passwordchack").html("");
				$("#passwordchack1").html("사용 가능한 비밀번호 입니다.");		pass[2] = true; pass[1] =true;
			}
		}else{ // 정규현식 다르면
			$("#passwordchack1").html("");
			$("#passwordchack").html("영소문자 5~15 사이로 입력해주세요!");		pass[2] = false;
		}
	console.log(pass[2]);
	}); // keyup end 
	
	// 이름 체크
	$("#mname").keyup( function(){ 
		let mname = $("#mname").val(); // 해당 id의 데이터 가져오기
		let namej = /^[가-힣]{2,10}$/;	// 한글만 2~10 정규표현식
		if( namej.test(mname) ){
			$("#namechack").html( "" );
			$("#namechack1").html( "사용가능한 이름입니다." );		pass[3] = true;
		}else{
			$("#namechack1").html( "" );	
			$("#namechack").html( "한글 2~10 사이만 가능합니다." );	pass[3] = false;
		}
	console.log(pass[3]);
	 }); // keyup end 
	 
	// 전화번호 체크 
	$("#mphone").keyup( function(){ 
		let mphone = $("#mphone").val();
		let phonej = /^([0-9]{2,3})-([0-9]{4,4})-([0-9]{4,4})$/;
		if( phonej.test(mphone) ){
			$("#phonechack").html( "" );
			$("#phonechack1").html( "사용가능한 번호 입니다." );					pass[4] = true;
		}else{
			$("#phonechack1").html( "" )
			$("#phonechack").html( "010-0000-0000 형식으로 입력해주세요." ); pass[4] = false;
		}
		console.log(pass[4]);
	}); // keyup end 
	 
	//이메일 체크
	$("#memail").keyup(function(){ 
		
		let memail = $("#memail").val();
		let memailaddress = $("#memailaddress").val();
	
		if(memailaddress==""){
			$("#emailcheck").html( "올바른 이메일 기입방식으로 입력하여주십시오.");	pass[5]= false;

		}else{
				let emailj = /^[a-zA-Z0-9]{3,20}$/;
				if( emailj.test(memail) ){
				//이메일 중복 체크
				let email = memail+"@"+memailaddress;
				
				$.ajax({
					url:"../emailcheck",
					data:{"email":email},
					success:function(result){
						
						if(result==1){
							$("#emailcheck").html("");
							$("#emailcheck1").html("사용가능한 이메일 입니다.");  
							pass[5] = true; 
						}else{
							$("#emailcheck").html("사용중인 이메일 입니다.");  
							pass[5] = false;
						}
					}		
				});
			}else{
				$("#emailcheck").html( "올바른 이메일 기입방식으로 입력하여주십시오.");	pass[5] = false;
		
			}
		}
		console.log(pass[5]);
	}); // keyup end 
	
	//이메일주소 목록상자 선택 시 
	$("#emailselect").change(function(){	
		
		let emailselect =$("#emailselect").val();
		if(emailselect==""){
			$("#memailaddress").attr("readonly" ,false);
			$("#memailaddress").val("");
			
			
			$("#memail").keyup( function(){ 
		
				let memail = $("#memail").val();
				console.log(memail);
				let emailj = /^([a-z0-9]{4,10}).([a-z]{3,3})$/;
				let emailk = /^([a-z0-9]{4,10}).([a-z]{2,2}).([a-z]{2,2})$/;
				if( emailj.test(memail) || emailk.test(memail) ){
					$("#emailchack").html("");
					$("#emailchack1").html( "사용가능한 이메일 입니다." );
				}else{
					$("#emailchack1").html("");
					$("#emailchack").html( "올바른 이메일 기입방식으로 입력하여주십시오." );
				}
			}); // keyup end 
			
			
		}else{
			$("#memailaddress").attr("readonly" ,true);
			$("#memailaddress").val(emailselect);
		}
	});
	
	//주소체크
	$("#sample4_detailAddress").keyup(function() {
	
		let address1=$("#sample4_postcode").val();	
		let address2=$("#sample4_roadAddress").val();	
		let address3=$("#sample4_jibunAddress").val();	
		let address4=$("#sample4_detailAddress").val();	
		
		if(address1==""||address2 ==""||address3==""||address4==""){
			$("#addresscheck1").html("");
			$("#addresscheck").html("모든 주소를 입력해주세요."); pass[6] =false;
			
		}else{
			$("#addresscheck").html("");
			$("#addresscheck1").html("사용가능한 주소입니다.");	pass[6] =true;
		}
		
		console.log(pass[6]);
	});
	
}); // 문서 열리면 해당 코드가 종료 


//폼 전송 메소드
function signup(){
	
	// pass 배열이 모두 true 이면 폼 전송
	let check = true;
	for(let i =0 ; i<pass.length; i++){
		
		if(pass[i]== false){
			check = false;
		}
	}
	//js 에서 form 전송하는 방법
	if(check){
		document.getElementById("signupform").submit();
	}else{
		alert("필수 입력사항이 입력되지 않았습니다.")
	}

}



// 다음 API JS
  function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
 
                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
  
function passwordchange(){
	$("#passwordbox").css("display","block");
}
    
 
 