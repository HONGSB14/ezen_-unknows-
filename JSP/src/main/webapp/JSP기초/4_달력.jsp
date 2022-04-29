<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
	
	Calendar calendar = Calendar.getInstance();
	
	int year=2022;
	int month=4;
	boolean ch =false;
	//요청 변수가 있을 경우 
	if(request.getParameter("year") != null && request.getParameter("month") != null ){ //요청 변수가 null 이 아니면 
		 year= Integer.parseInt(request.getParameter("year"));	
	      month= Integer.parseInt(request.getParameter("month"));
	     
	      //만약에 월이 1 미만이면 연도-1  월12 설정 
	      if(month<1){
	    	  year--; month=12; 
	      }
	      //만약에 월이 12이상이거나 같다면 연도 1 증가 월 1 설정
			if(month>=12){
				month=1; year++;
			}
	      
	      //지원 불가 달력이면 1900 년 이하
	      
	      if(year<1900 || year>3200 || month<0 || month>12){
	    	  ch=true;
	      }
	}
			calendar.set(year , month-1, 1); // month= 0->1월 //11->12월
			int sweek= calendar.get(Calendar.DAY_OF_WEEK);//해당 월의 시작요일 구하기		
			int eday=  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//해당 월의 마지막요일 구하기		
			
	%>
	
	<!-- HTML 작성 공간 -->
	<%
		if(ch==true){
	%>
		<div>표현 할 수 없는 달력입니다.</div>
	<%}%>
	
	<form action="4_달력.jsp" method="get">
		<input type="text" name="year" placeholder="연도">
		<input type="text" name="month" placeholder="월">
		<input type="submit" value="검색">
	</form>
	
	
	
	<p>입력 연도: <%=year %></p>
	<p>입력 월: <%=month %></p>
	<form action="4_달력.jsp">
		<input type="text" name="year" value="<%=year %>">
		<input type="text" name="month" value="<%=month-1 %>">
		<input type="submit" value="이전">
	</form>
	
	<form action="4_달력.jsp">
		<input type="text" name="year" value="<%=year%>">
		<input type="text" name="month" value="<%=month+1 %>">
		<input type="submit" value="다음">
	</form>
	

	<table>
		<tr>
			<th>일요일 </th>
			<th>월요일 </th>
			<th>화요일 </th>
			<th>수요일 </th>
			<th>목요일 </th>
			<th>금요일 </th>
			<th>토요일</th>
		</tr>
		
		<tr>
			<!--  시작 요일 미만 공백으로 채움 -->
			<%for(int i = 1; i<sweek; i++){ %>
				<td> </td>
			<%}%>
			<!--  1일부터 마지막일까지 1씩 증가 하면서 출력 -->
			<%for(int i = 1; i<=eday; i++){ %>
				<td><%=i%></td>
				<!-- 7배수 마다 줄바꿈  [ 토요일 마다 ] -->
				<%if(sweek % 7==0){ %>
					</tr><tr>
				<%} %>
				<%sweek++;%> <!--  일 증가 할때마다 요일도 증가 -->
			<%} %>
		</tr>
	</table>
</body>
</html>