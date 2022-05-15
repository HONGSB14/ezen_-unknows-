package controller.slip;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SlipDao;
import dto.Slip;

/**
 * Servlet implementation class SalesRegistration
 */
@WebServlet("/slip/SlipRegistration")
public class SlipRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlipRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int cnum =Integer.parseInt(request.getParameter("cnum"));
		String carnum=request.getParameter("carnum");
		int sflux=Integer.parseInt(request.getParameter("flux"));
		int sfee=Integer.parseInt(request.getParameter("fee"));
		int scardfee=Integer.parseInt(request.getParameter("cardfee"));
		int sdaysale=Integer.parseInt(request.getParameter("daysale"));
		String snote=request.getParameter("note");
		Slip slip = new Slip(cnum,0,carnum,sflux,sfee,scardfee,sdaysale,snote,null);
		boolean result=SlipDao.getSlipDao().addSlip(slip);
		
		if(result) {
			//일단 새로고침으로 . 나중에 ajax 써야할 수도 ?
			response.sendRedirect("/taxicompany_data_system/sale/slip_registration.jsp");
		}else {
			response.sendRedirect("/taxicompany_data_system/sale/slip_registration.jsp?result=false");	
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
