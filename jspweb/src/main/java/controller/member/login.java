package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;

/**
 * 
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		
		//1.요청
		String mid = request.getParameter("mid");
		String mpassword=request.getParameter("mpassword");
		//2.객체화 (데이터가 적기 떄문에 생략)
		//3. db처리
		int result=MemberDao.getMemberDao().login(mid,mpassword);
		//4.db결과
		if(result ==1) {
			//로그인 성공시 세션 부여	[세션: 서버에 메모리 할당-> 모든 페이지에서 동일한 메모리 사용 가능 ]
			HttpSession session =request.getSession();
											
			session.setAttribute("login", mid);	//세션에 값 저장
								//세션명 , 데이터
			
			//session.getMaxInactiveInterval();  세션 생명주기 설정 (초 단위);
			
			response.sendRedirect("/jspweb/main.jsp");	//페이지 전환
		}else if(result==2){
			response.sendRedirect("/jspweb/member/login.jsp?result=2"); //아이디 혹은 비밀번호가 다름
		}else {
			response.sendRedirect("/jspweb/errpage.jsp"); //DB오류
		}
		
		doGet(request, response);
	}

}
