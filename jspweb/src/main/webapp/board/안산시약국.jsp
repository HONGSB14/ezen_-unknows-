<%@page import="java.time.LocalTime"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Calendar"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@include file="../header.jsp" %>
	
	
	<div class="container">
		<%
			//1. 공공데이터 포털의 json 형식의 데이터를 가져오기 [web json 페이지를 -> java 로 변환]
				//1. url 가져오기 [ URL : java.net 패키지]
				URL url = new URL("https://api.odcloud.kr/api/3035882/v1/uddi:5fae3cf5-bc15-4eba-87d8-8289b74e659b_201912202015?page=1&perPage=300&serviceKey=V1D0RoBJCl1PTrNrdovcJHzbZkwiiyLMbHx%2FsQfaQfsvS0iIM3OQ2x91yr6PXyIFl0hj0ETaeC1Fvd0WoSMHmg%3D%3D");
			
			//2. JAVA 에서 해당 URL 읽기  [바이트 스트림 ]
				//1.  BufferedReader : 외부 데이터 읽어올때 사용되는 클래스
				BufferedReader bf =  new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			
			//3.읽어오기 스트림 내 존재하는 바이트 모두 읽어오기
			String result= bf.readLine();
			
			//4.읽어온 스트림을 json 으로 형변환
			JSONObject object = new JSONObject(result);
			
			//5.JSON 객체에서 특정 키의 해당하는 데이터 가져오기
			JSONArray array = new JSONArray(object.get("data").toString());
			
		%>
		<!-- 검색 -->
		<form action="안산시약국.jsp">
			<input type="text" name="keyword" placeholder="주소">
			<input type="submit" value="검색">
		</form>
		<!-- 지도 -->
		<div id="map" style="width: 100% ; height: 350px;">
			<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=77754e23f7d3787ff007ce519a9c1d86&libraries=services,clusterer,drawing"></script>
		</div>
		
		<table class="table hover-table">
			<tr>
				<th>약국명</th>
				<th>대표전화번호</th>
				<th>주소</th>
				<th>월요일</th>
				<th>화요일</th>
				<th>수요일</th>
				<th>목요일</th>
				<th>금요일</th>
				<th>토요일</th>
				<th>일요일</th>
			</tr>	
		
		<%
			//영업여부  [ 자바에서 요일 구하기]
			Calendar calendar = Calendar.getInstance();
			//2.요일 배열
			String [] 요일 = {"일","월","화","수","목","금","토"};
			//3.현재 요일 구하기
			String 현재요일=요일[calendar.get(Calendar.DAY_OF_WEEK)-1]+"요일 운영";
			
			// 2. 현재 시간 구하기 
				// LocalTime : 시간 클래스 
					// 1. LocalTime.of( 시 , 분 , 초 ) : 시간형식으로 형변환
					// 2. LocalTime.now() : 현재 시간 구하기 
					int hour = calendar.get( Calendar.HOUR_OF_DAY );	// HOUR : 01시  // HOUR_OF_DAY: 13시
					int minute = calendar.get( Calendar.MINUTE );
					LocalTime 현재시간 =  LocalTime.of( hour , minute  ,  0 ); // 현재 시간 
					
					// LocalTime localTime2 = LocalTime.now();
					// System.out.println(  localTime2  );
		
			//JSON 출력
			for(int i=0; i<array.length(); i++ ){
				String keyword=request.getParameter("keyword");
				JSONObject jo = array.getJSONObject(i);
				if(keyword!=null){
					 //만약 검색이 있을 경우                //.인덱스 검색 
					 int index=jo.get("주소").toString().indexOf(keyword);
					 //-1 인덱스는 없다는 의미  continue->반복문 반복 
					 if(index == -1){ continue;} 
					 
				}
			
			//영업여부 판단  iterator 인덱스가 없는 (Set)리스트를 순회하는 인터페이스 
//			Iterator<JSONObject> iterator = jo.keys();
//			while(iterator.hasNext()){	//다음 객체가 있는지 확인
//				String key=iterator.next().toString();
//				if(key.equals(현재요일+"요일 운영")){
//					jo.put("영업여부",jo.get(key));
//				}
//			}
			 
			String[] keys=JSONObject.getNames(jo);
			String 영업여부 = "[영업종료]";
			// 3. 모든 key 반복문 돌려기 
				for( String key : keys ){
					// 4. 만약에 해당 key가 현재 요일 과 같으면서 -(영업시간없다.) 아니면
					if( key.equals(현재요일+"요일 운영") && !jo.getString(key).equals("-") ){
								
						String[] 영업시간 = jo.getString(key).split("~");	//  ~ 기준으로 open , close
						System.out.println( 영업시간[0]  +" , " + 영업시간[1] );
								
						if(  Integer.parseInt(  영업시간[1].split(":")[0] ) <= 24 ){
							LocalTime 여는시간 =  LocalTime.of(  
									Integer.parseInt( 영업시간[0].split(":")[0] )    , 
									Integer.parseInt( 영업시간[0].split(":")[1] ) , 0 );
									
							LocalTime 닫는시간 =  LocalTime.of(  
									Integer.parseInt( 영업시간[1].split(":")[0] )    , 
									Integer.parseInt( 영업시간[1].split(":")[1] ) , 0 );
							
							if( 현재시간.isAfter(여는시간) ){ // 현재시간이 여는시간보다 이후이면 
								영업여부 = "영업중:"+jo.getString(key); // 5. 영업여부에 시간 넣어주기 
								// 닫는시간 
								if( 현재시간.isAfter(닫는시간) ){ // 현재시간이 닫는시간보다 이후이면
									System.out.println( 닫는시간 );
									영업여부 = "[영업종료]";
								}
							}else{
								영업여부 = "[영업종료]";
							}
									
						}
								
					}
				}
				
		%>
			<tr onclick="map('<%=jo.get("주소")%>','<%=jo.get("약국명")%>','<%=영업여부%>')">
				<td><%=jo.get("약국명")%></td>
				<td><%=jo.get("대표전화") %></td>
				<td><%=jo.get("주소") %></td>
				<td><%=jo.get("월요일 운영") %></td>
				<td><%=jo.get("화요일 운영") %></td>
				<td><%=jo.get("수요일 운영") %></td>
				<td><%=jo.get("목요일 운영") %></td>
				<td><%=jo.get("금요일 운영") %></td>
				<td><%=jo.get("토요일 운영") %></td>
				<td><%=jo.get("일요일 운영") %></td>
			</tr>	
		<% 
			}
		%>
		</table>
	</div>
	
	
	
	
	<script src="/jspweb/js/kakaomap.js" type="text/javascript"></script>
	<%@include file="../footer.jsp" %>
</body>
</html>