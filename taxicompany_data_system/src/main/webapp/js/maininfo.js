//회사번호
let cnum=$("#cnum").val();
//차량 번호 배열
let carNum=[];
//차량등록 시 아이디 값을 가져와야함
let vehId=[];
//맵 표시 유무 안내 공백설정
$("mapinfo").html("");
//각회사의 차량 정보 가져오기
	$.ajax({
		
		url:"car/GetCarInfo",
		data:{"cnum":cnum},
		success:function(data){
			for(let i=0; i<data.length; i++){
				
				carNum.push(data[i]["carNum"]);
				vehId.push(data[i]["carId"]);
			}
		}
	});

$(function(){
	//마커저장배열
	let markers=[];

	///////////////////////////////////////////////지도 생성///////////////////////////////////////////////////
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		 mapOption = { 
			 center: new kakao.maps.LatLng(37.49311801652184, 126.8372818518843), // 지도의 중심좌표
			 level: 5 // 지도의 확대 레벨
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
	
	var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
	    content : iwContent,
	    removable : iwRemoveable
	});
	

	///////////////////////////////////////////////////////////차량 위치 ////////////////////////////////////////////
	
	setInterval(function(){
		//마커 제거	
		for (var i = 0; i < markers.length; i++) {
	        markers[i].setMap(null);
	    }            
		//차량 번호 만큼 반복문 (다수 차량 지도 표시를 위해 )
		for(let i=0; i<carNum.length;i++){
			//마커 초기화 (기존 마커 제거)	
			marker.setMap(null);
			$.ajax({
																																									   //차량 아이디 vehId[i]//차량 아이디값 변수를 이용하여 등록  111033115 
				url:"http://ws.bus.go.kr/api/rest/buspos/getBusPosByVehId?serviceKey=V1D0RoBJCl1PTrNrdovcJHzbZkwiiyLMbHx%2FsQfaQfsvS0iIM3OQ2x91yr6PXyIFl0hj0ETaeC1Fvd0WoSMHmg%3D%3D&vehId="+vehId[i],
				type:"get",
				dataType:"TEXT",
				success:function(data){
						
					if(data){
			
						$(data).find('itemList').each(function(){
			
							//데이터에서 좌표 불러오기  (타코미터 저장용)
							let tmX= $(this).find("tmX").text();
							let tmY= $(this).find("tmY").text();
							let plainNo=$(this).find("plainNo").text();
							//차량번호 유효성 검사
							if(carNum[i]==plainNo){
							
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
										
					
								//마커 생성
								marker= new kakao.maps.Marker ({
									position: new kakao.maps.LatLng(tmY,tmX),
									image: new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
									map:map
								});
								
								//마커를 배열에 저장
								markers.push(marker);		
								
							}else{
								$("#map").html("");
								$("#mapinfo").html("현재 등록한 차량이 존재하지 않습니다. 다시한번 확인해 주십시오.");
							}
						});
					}else{
						console.log("에러가 났어요~!");
					}
				}
			});
		}
	},5000);
	
}); //실행문서코드 끝



//지도통계 (1주)
function onWeek(cnum){
	//버튼 클릭 시 안내문구 가리기
	$("#info").html("");
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
	},7000);
}



//지도통계 (1달)
function onMonth(cnum){
	//버튼 클릭 시 안내문구 가리기
	$("#info").html("");
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
	//버튼 클릭 시 안내문구 가리기
	$("#info").html("");
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




