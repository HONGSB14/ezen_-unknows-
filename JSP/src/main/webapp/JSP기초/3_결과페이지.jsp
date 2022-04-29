<%@page import="javax.swing.plaf.synth.SynthOptionPaneUI"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   
   <!-- 전 페이지로 부터 요청(request) 
   		request : 서블릿에서 제공해주는 내장 객체 (미리 만들어진 객체)
   		request.getParameter("요청할 변수의 name 값")
   -->
   
   <%
   //스크립트 = 자바 언어를 작성하고 실행하는 공간
	String 이름 =request.getParameter("name");
   	String 색상 = request.getParameter("color");
   
   	//제어문
   	boolean admin=true;
   	if(!이름.equals("admin")){
   		admin=false;
   	}
   	
   	int 반복횟수= Integer.parseInt(request.getParameter("number"));
   %>
   
   
   <html>
   		<body style="background-color: <%=색상%>">
   			작성한 이름 : <%=이름 %><br>
   			선택한 색상 : <%=색상 %>
   			<%
   				if(admin==true){
   			%>		
   				<div> 관리자 이군요 .</div>
   			<%}%>	
   			
   			<% for(int i = 0 ; i<반복횟수 ; i++){
   				for(int s= 0; s<=i; s++){
   			%>	
   				<span style="color: white;"> ★ </span>
   				<%}%>
   				<br>	
   			<%}%>
   				<!--  피라미드  -->
   				<%for(int l=0; l<반복횟수; l++){ %>
   					
   					<%for(int k=0; k<반복횟수-l; k++){ %>
  						<span>　</span>
   						<%	for(int s=0; s<(반복횟수*2)-1; s++){ %>
   						<span>★</span>
   						<%}%>
   					<%}%>
   					<br>
   				<%}%>
   				
   				
   				
   			   			
   		
   		</body>
   </html>
   
   