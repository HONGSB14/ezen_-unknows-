$(function(){
	
	///////////////////////////////////////////////지도 생성///////////////////////////////////////////////////
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		 mapOption = { 
			 center: new kakao.maps.LatLng(), // 지도의 중심좌표
			 level: null // 지도의 확대 레벨
		};
				
	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer,mapOption); 

	
	///////////////////////////////////////////마커생성//////////////////////////////////////////////////////////
	var imageSrc = "/taxicompany_data_system/img/taximaker2.png", // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(30, 39), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
	
	// 마커가 표시될 위치입니다 
	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
	    markerPosition  = new kakao.maps.LatLng(); 
	
	// 마커를 생성합니다
	
	var marker = new kakao.maps.Marker({
	    position: markerPosition,
  		image: markerImage,
  		 clickable: true
	});

	marker.setMap(map);
	
	
	////////////////////////////////////////////////////인포인터 생성////////////////////////////////////////////////////
	
//	var iwContent = document.getElementById("carnum"), // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
//	    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
//	
//	// 인포윈도우를 생성합니다
//	var infowindow = new kakao.maps.InfoWindow({
//	    content : iwContent,
//	    removable : iwRemoveable
//	});
//	
//	// 마커에 클릭이벤트를 등록합니다
//	kakao.maps.event.addListener(marker, 'click', function() {
//	      // 마커 위에 인포윈도우를 표시합니다
//	      infowindow.open(map, marker);  
//	      $("#driverinfo").html("@@@@@@@@@@@@@@@운전자 정보 데이터 가져오기@@@@@@@@@@@@@");
//	      $("#driversaleinfo").html("@@@@@@@@@@@@@@운전자 하루 매출 데이터 가져오기@@@@@@@@@@@@");
//	});
	

	///////////////////////////////////////////////////////////버스 위치 ////////////////////////////////////////////
	
	//맵 새로고침
	//$('#map').load(location.href+' #map');
	
	//버스 위치정보 데이터 받아오기 (10초 간격)
	setInterval(function(){
	$.ajax({
		
		url:"http://ws.bus.go.kr/api/rest/buspos/getBusPosByVehId?serviceKey=V1D0RoBJCl1PTrNrdovcJHzbZkwiiyLMbHx%2FsQfaQfsvS0iIM3OQ2x91yr6PXyIFl0hj0ETaeC1Fvd0WoSMHmg%3D%3D&vehId=111033115",
		type:"get",
		dataType:"TEXT",
		success:function(data){
		
			if(data){
				
				$(data).find('itemList').each(function(){
					
					//데이터에서 좌표 불러오기 
					let tmX= $(this).find("tmX").text();
					let tmY= $(this).find("tmY").text();
					
					console.log(tmY,tmX);
					
					//좌표 값 DB에 저장
					$.ajax({
						url:"LocationData",
						data:{"tmX":tmX , "tmY":tmY},
						type:"get",
						success:function(setData){
							if(setData){
								console.log("1");
							}else{
								console.log("2");
							}
						}
					});
					
					//맵 생성
					mapOption = { 
						 center: new kakao.maps.LatLng(tmY, tmX), // 지도의 중심좌표
						 level: 2 // 지도의 확대 레벨
					};
					
					map = new kakao.maps.Map(mapContainer, mapOption); 
					//마커 생성
					maker= new kakao.maps.Marker ({
	
						position: new kakao.maps.LatLng(tmY,tmX),
						image: new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
						map:map
					});
					
				});
			}else{
				console.log("에러가 났어요~!");
			}
		}
	});
	},10000);

	
}); //실행문서코드 끝





