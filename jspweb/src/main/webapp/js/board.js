
$(document).ready(function(){
    //섬머 노트
    $('#summernote').summernote({
        placeholder: 'Hello Bootstrap 5',
        tabsize: 2,
        height: 100,
        minHeight :250 , //최소높이
        maxHeight : null,   //최대높이
        lang :'ko-KR'       //언어

    });

});
   
function filedelete(bno){ 
	
	//비동기
	$.ajax({
		url:"filedelete", //서블릿 통신
		data:{"bno":bno}, //통신 데이터 : {변수명:값 , 변수명2 : 값}
		success : function(result){
			if(result=="1"){
				
				alert("첨부파일 삭제 완료!!");
				location.reload(); //현재페이지 새로고침
			}else{
				
				alert("첨부파일 삭제 실패!!");
			}	
		}
	});
	
}
