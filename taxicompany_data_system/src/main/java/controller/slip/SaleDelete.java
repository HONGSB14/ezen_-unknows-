package controller.slip;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SaleDao;
import dao.SlipDao;

/**
 * Servlet implementation class SaleDelete
 */
@WebServlet("/slip/SaleDelete")
public class SaleDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int snum=Integer.parseInt(request.getParameter("snum"));
		int cnum=Integer.parseInt(request.getParameter("cnum"));
		
		boolean result=SaleDao.getsaleDao().deleteSale(cnum, snum);
		if(result) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
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
