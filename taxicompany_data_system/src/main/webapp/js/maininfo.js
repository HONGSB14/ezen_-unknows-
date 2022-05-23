$(function(){
	///////////////////////////////////////////////지도 생성///////////////////////////////////////////////////
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		 mapOption = { 
			 center: new kakao.maps.LatLng(37.492825, 126.835671), // 지도의 중심좌표
			 level: 4 // 지도의 확대 레벨
		};
				
	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	
	
	
	
	///////////////////////////////////////////마커생성//////////////////////////////////////////////////////////
	var imageSrc = "/taxicompany_data_system/img/taximaker2.png", // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(30, 39), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	
	// 마커가 표시될 위치입니다 
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
	    markerPosition  = new kakao.maps.LatLng(37.492825, 126.835671); 
	
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition,
  		image: markerImage,
  		 clickable: true
	});

	marker.setMap(map);
	
	
	////////////////////////////////////////////////////인포인터 생성////////////////////////////////////////////////////
	var iwContent = document.getElementById("carnum"), // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
	
	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
	    content : iwContent,
	    removable : iwRemoveable
	});
	
	// 마커에 클릭이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
	      // 마커 위에 인포윈도우를 표시합니다
	      infowindow.open(map, marker);  
	      $("#driverinfo").html("@@@@@@@@@@@@@@@운전자 정보 데이터 가져오기@@@@@@@@@@@@@");
	      $("#driversaleinfo").html("@@@@@@@@@@@@@@운전자 하루 매출 데이터 가져오기@@@@@@@@@@@@");
	});
	
	
	let carNumInfo= $("#carNumInfo");
	
	
	
}); //실행문서코드 끝

