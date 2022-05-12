$(function(){
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		 mapOption = { 
			 center: new kakao.maps.LatLng(37.492825, 126.835671), // 지도의 중심좌표
			 level: 4 // 지도의 확대 레벨
		};
				
	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	var imageSrc = "/taxicompany_data_system/img/taxiMarker.jpg", // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(30, 39), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 39)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	
	// 마커가 표시될 위치입니다 
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
	    markerPosition  = new kakao.maps.LatLng(37.492825, 126.835671); 
	
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition,
  		image: markerImage
	});

	marker.setMap(map);
	
	// 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	var content =document.getElementById("customoverlay");
    
    // 커스텀 오버레이가 표시될 위치입니다 
	var position = new kakao.maps.LatLng(37.492825, 126.835671);  
	
	// 커스텀 오버레이를 생성합니다
	var customOverlay = new kakao.maps.CustomOverlay({
	    map: map,
	    position: position,
	    content: content,
	    yAnchor: 1 
	});

}); //실행문서코드 끝

