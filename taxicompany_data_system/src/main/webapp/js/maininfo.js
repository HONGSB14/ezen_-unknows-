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

	///////////////////////////////////////////////////////////버스 위치 ////////////////////////////////////////////

	let cnum=$("#cnum").val();
	//버스 위치정보 데이터 받아오기 (10초 간격)
	setInterval(function(){
	$.ajax({
		
		url:"http://ws.bus.go.kr/api/rest/buspos/getBusPosByVehId?serviceKey=V1D0RoBJCl1PTrNrdovcJHzbZkwiiyLMbHx%2FsQfaQfsvS0iIM3OQ2x91yr6PXyIFl0hj0ETaeC1Fvd0WoSMHmg%3D%3D&vehId=111033115",
		type:"get",
		dataType:"TEXT",
		success:function(data){
			if(data){
				console.log(data);
				$(data).find('itemList').each(function(){
					
					//데이터에서 좌표 불러오기 
					let tmX= $(this).find("tmX").text();
					let tmY= $(this).find("tmY").text();
					let plainNo=$(this).find("plainNo").text();
					console.log(tmY,tmX);
					
					//좌표 값 DB에 저장
					$.ajax({
						url:"LocationData",
						data:{"tmY":tmY, "tmX":tmX ,"plainNo":plainNo , "cnum":cnum},
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



//지도통계 (1주)
function onWeek(cnum){
	
	//지도 생성
	var map = new kakao.maps.Map(document.getElementById('mapdata'), { // 지도를 표시할 div
			 center : new kakao.maps.LatLng(37.49311801652184, 126.8372818518843), // 지도의 중심좌표 
			 level : 10 // 지도의 확대 레벨 
	});
	setInterval(function(){
    //json 형태의 데이터 가져오기
		$.ajax({
				
			url:"datainfo/WeekInfo",
			data:{"cnum":cnum},
			success:function(dataLocation){
				
				if(dataLocation){
					
					// 마커 클러스터러 생성 
					var clusterer = new kakao.maps.MarkerClusterer({
							 map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
							 averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
							 minLevel: 4 // 클러스터 할 최소 지도 레벨 
					});
					//저장용 배열 생성
					let y=[];
					let x=[];
					let markers=[];
					//데이터 생성
					
					for(let i=0; i<dataLocation.length; i++){
							
						y[i]=dataLocation[i]["locationY"];
						x[i]=dataLocation[i]["locationX"];
						//마커 생성
						 var marker =new kakao.maps.Marker({  
						  position : new kakao.maps.LatLng(y[i], x[i])
						  
						 });
						markers.push(marker);
					};
					  
				      // 클러스터러에 마커들을 추가합니다
				      clusterer.addMarkers(markers);
				      
				}else{
					console.log("error: null");
				}
			}
		});	
	},5000);
}



//지도통계 (1달)
function onMonth(cnum){
	
	//지도 생성
	var map = new kakao.maps.Map(document.getElementById('mapdata'), { // 지도를 표시할 div
			 center : new kakao.maps.LatLng(37.49311801652184, 126.8372818518843), // 지도의 중심좌표 
			 level : 10 // 지도의 확대 레벨 
	});
	setInterval(function(){
    //json 형태의 데이터 가져오기
		$.ajax({
				
			url:"datainfo/MonthInfo",
			data:{"cnum":cnum},
			success:function(dataLocation){
				
				if(dataLocation){
					
					// 마커 클러스터러 생성 
					var clusterer = new kakao.maps.MarkerClusterer({
							 map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
							 averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
							 minLevel: 4 // 클러스터 할 최소 지도 레벨 
					});
					//저장용 배열 생성
					let y=[];
					let x=[];
					let markers=[];
					//데이터 생성
					
					for(let i=0; i<dataLocation.length; i++){
							
						y[i]=dataLocation[i]["locationY"];
						x[i]=dataLocation[i]["locationX"];
						//마커 생성
						 var marker =new kakao.maps.Marker({  
						  position : new kakao.maps.LatLng(y[i], x[i])
						  
						 });
						markers.push(marker);
					};
					  
				      // 클러스터러에 마커들을 추가합니다
				      clusterer.addMarkers(markers);
				      
				}else{
					console.log("error: null");
				}
			}
		});	
	},5000);
}



//지도통계 (1년)
function onYear(cnum){
	
	
	//지도 생성
	var map = new kakao.maps.Map(document.getElementById('mapdata'), { // 지도를 표시할 div
			 center : new kakao.maps.LatLng(37.49311801652184, 126.8372818518843), // 지도의 중심좌표 
			 level : 10 // 지도의 확대 레벨 
	});
	setInterval(function(){
    //json 형태의 데이터 가져오기
		$.ajax({
				
			url:"datainfo/YearInfo",
			data:{"cnum":cnum},
			success:function(dataLocation){
				
				if(dataLocation){
					
					// 마커 클러스터러 생성 
					var clusterer = new kakao.maps.MarkerClusterer({
							 map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
							 averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
							 minLevel: 4 // 클러스터 할 최소 지도 레벨 
					});
					//저장용 배열 생성
					let y=[];
					let x=[];
					let markers=[];
					//데이터 생성
					
					for(let i=0; i<dataLocation.length; i++){
							
						y[i]=dataLocation[i]["locationY"];
						x[i]=dataLocation[i]["locationX"];
						//마커 생성
						 var marker =new kakao.maps.Marker({  
						  position : new kakao.maps.LatLng(y[i], x[i])
						  
						 });
						markers.push(marker);
					};
					  
				      // 클러스터러에 마커들을 추가합니다
				      clusterer.addMarkers(markers);
				      
				}else{
					console.log("error: null");
				}
			}
		});	
	},5000);
}




