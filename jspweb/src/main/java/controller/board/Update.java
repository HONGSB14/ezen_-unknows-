package controller.board;


import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dao.BoardDao;
import dto.Board;

/**
 * Servlet implementation class Update
 */
@WebServlet("/board/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uploadpath=request.getSession().getServletContext().getRealPath("/board/upload");
		MultipartRequest multi = new MultipartRequest(request , uploadpath , 1024*1024*10 , "UTF-8" ,new DefaultFileRenamePolicy());
		
		int bno=Integer.parseInt(request.getParameter("bno"));
		String title=multi.getParameter("btitle");
		String content=multi.getParameter("bcontent");
		String file=multi.getFilesystemName("bfile");
		
		//기존 파일 가져오기
		Board temp = BoardDao.getBoardDao().getboard(bno); // 파일 불러오기
		String oldfile= temp.getBfile();	//불러온 파일은 old file 
		
		if(file==null) { //새로운 첨부파일이 없다면
			file=oldfile;
		}else { // 새로운 첨부파일이 있으면
			//파일 업로드 (서버경로에있는 파일 불러오기)
			String upload =request.getSession().getServletContext().getRealPath("/board/upload/"+oldfile);
			//파일 객체화
			File file2 = new File(upload);
			//파일 삭제
			file2.delete();
		}
			//객체화
		Board board = new Board(bno, title, content, 0, 0, null, file, null);
		
		boolean result = BoardDao.getBoardDao().update(board);
		
		if(result) {
			response.sendRedirect("boardview.jsp?bno="+bno);
		}else {
			response.sendRedirect("boardview.jsp?bno="+bno);
		}
	}

}
