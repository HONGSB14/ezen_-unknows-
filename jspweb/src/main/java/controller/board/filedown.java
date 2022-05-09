package controller.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class filedown
 */
@WebServlet("/board/filedown")
public class filedown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public filedown() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); //요청시 한글 인코딩
		String bfile=request.getParameter("bfile");	//boardview 에있는 file 값을 서블릿쪽으로 bfile 값 데이터 담기
		 
		//2.서버에서 해당 파일 찾기
		String uploadpath =request.getSession().getServletContext().getRealPath("/board/upload/"+bfile); //진짜 경로를 서블릿 텍스트로 세션값을 가져오고 요청한다. 
		System.out.println(uploadpath);  //D://html//.metadata//.plugins//org.eclipse.wst.server.core//tmp0//wtpwebapps//jspweb//board//upload//캡처4.PNG
		
		
		//3. 파일 객체화
		File file= new File(uploadpath);
		
		//4.다운로드 형식 [ 브라우저 마다 다름
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(bfile,"UTF-8")+";");
						   // 다운로드 형식			   다운로드 화면에서 표시할 파일명   URLEncoder.endoer:URL 한글 인코딩 타입(파일에 한글이 있으면 인코딩을 한글로 쓰겠다라는 말)
		
		//5. 바이트 형식으로 내보내기 (통신)
		
		
		//6.입력 스트림[서버가 pc에 있는 파일을 스트립 가져오기 단계]   현재 파일은 서버에 있음 . 즉, pc에 저장되어있는 파일을 서버가 가져가기
		BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));	// filestream 이 아닌 상위클래스 BufferedInputStream 쓰는이유 : filestream 을 쓰면 출력스트림 때 respone 을 못씀. 
			
			//6-1.바이트 배열 선언
			byte [] bytes= new byte[(int)file.length()];	//파일 길이 (크기 = 용량) 만큼 배열 선언 (파일크기가 얼만큼일지 모르니 .length 로 가변성 확보)
			
			//6-2. 모든 통신 기초 단위는 바이트 이므로 바이트 읽어 오기
			fin.read(bytes);
		
		//7.출력스트림[서버가 pc내 파일을 내보내기 단계] 즉, 서버가 pc로 내보내기(응답하기)		
		BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream()); //respone 쓰는 이유 : (서블릿 ) 웹 응답 객체
			
			//7-1. 내보내기
			fout.write(bytes);
			
		//8.스트림 닫기 (기록 제거)
		fin.close();
		fout.close();
	}

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
