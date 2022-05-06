package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDao;
import dao.MemberDao;
import dto.Board;

/**
 * Servlet implementation class write
 */
@WebServlet("/board/Write") // jsp 파일이 들어올 수 있는 경로 지정
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Write() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//(3.)COS 저장 경로
		//String uploadpath="C:\\Users\\504\\git\\ezen_-unknows-\\jspweb\\src\\main\\webapp\\board\\upload";
			
		//(3-2)서버 저장 경로
		//서버 경로 찾기 : requset.getSesscion().getServeletContext().getRealpath(경로);
		String uploadpath=request.getSession().getServletContext().getRealPath("/board/upload");
		
		//(4.) 첨부파일 업로드 [MultipartRequest : cos라이브러리 제공]
		MultipartRequest multl = new MultipartRequest(request, uploadpath,  1024*1024*10,    "UTF-8",   new DefaultFileRenamePolicy());
													//요청방식	    파일저장경로    파일최대용량허용범위    인코딩타입     보안방식:동일한 파일이 있을경우 자동 이름 변환
		
		request.setCharacterEncoding("UTF-8");
		//boardWritre.jsp 에서 input 에 입력된 name 값 가져오기
		String btitle= multl.getParameter("btitle");
		String bcontent= multl.getParameter("bcontent");
		//(5.) 첨부파일 : getFilesystemname
		String bfile= multl.getFilesystemName("bfile");	
		
		//서버에 저장된 세션값 (회원번호) 가져오기
		HttpSession session = request.getSession();
		String mid =(String)session.getAttribute("login");
		int mno= MemberDao.getMemberDao().getmno(mid);
		
		
		
		Board board =new Board(0, btitle, bcontent, mno, 0, null, bfile, null);
		
		boolean result =BoardDao.getBoardDao().write(board);
		
		if(result) { response.sendRedirect("/jspweb/board/boardlist.jsp");
		
		}else {
			response.sendRedirect("/jspweb/board/baordwrite.jsp");
		}
	}

}
