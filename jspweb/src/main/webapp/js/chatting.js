
$("#incontent").focus();


let websocket =null;

function enterkey(){
	
	if(window.event.keyCode==13){
		send();
	}
	
}


function sendbtn() {
		send();
}

start();

function start(){
	//스크립트 내에서 사용 되는 웹 소켓 클래스 (ws:// ip번호 / port 번호(HTTP 번호) / 현 프로젝트 명/ 서버소켓  )
	websocket = new WebSocket("ws://localhost:8080/jspweb/chatting");
	websocket.onOpen= function(e){
		onOpen(e);
	}
	websocket.onMessage =function(e){
		onmessage(e);
	}
	
	
	
}

function onOpen() {
	console.log(e);
}
function onmessage(e){
	console.log(e);
}
function send(){
		
		let msg=$("#incontent").val();
		websocket.send(msg);
		$("#incontent").val("");
		$("#incontent").focus();
	
}