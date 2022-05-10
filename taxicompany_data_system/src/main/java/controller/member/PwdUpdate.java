package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

/**
 * Servlet implementation class PwdUpdate
 */
@WebServlet("/member/PwdUpdate")
public class PwdUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdUpdate() {
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
		
		String mpwd=request.getParameter("pwd");
		String mid= request.getParameter("mid");
		
		boolean result=MemberDao.getMemberDao().updatepwd(mpwd,mid);
		
		if(result) {
			response.sendRedirect("/taxicompany_data_system/member/login.jsp");
		}else {
			response.sendRedirect("/taxicompany_data_system/member/findpasswordsuccess.jsp?result=false");
		}
	}

}
