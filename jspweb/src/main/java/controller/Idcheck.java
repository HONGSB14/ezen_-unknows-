package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

/**
 * Servlet implementation class idcheck
 */

//URL:프로젝트명/경로
@WebServlet("/Idcheck")
public class Idcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Idcheck() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String mid = request.getParameter("mid");
		//1. Dao 이용한 해당 id 가 있는지 체크
		MemberDao.getMemberDao();
		//2. 만약 해당 아이디가 존재하면 1  존재하지 않으면 2
		
		System.out.println("js 와 통신");
		
		//요청할때 request
		//응답할때 response
			//ajax 에게 데이터를 보내기
		
		response.getWriter().print(1);
		
		response.getWriter().print(2);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
