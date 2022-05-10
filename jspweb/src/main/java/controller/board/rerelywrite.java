package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.MemberDao;
import dto.Reply;

/**
 * Servlet implementation class rerelywrite
 */
@WebServlet("/board/rerelywrite")
public class rerelywrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rerelywrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int rindex = Integer.parseInt(request.getParameter("rno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		String rcontent= request.getParameter("rrcontent");
		String mid = (String)request.getSession().getAttribute("login");
		int mno= MemberDao.getMemberDao().getmno(mid);
		
		Reply reply= new Reply(0, rcontent, null, rindex, bno, mno, mid);
		
		boolean result = BoardDao.getBoardDao().replywrite(reply);
		if(result) {
			response.getWriter().print(1);
		}else {
			response.getWriter().print(2);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
