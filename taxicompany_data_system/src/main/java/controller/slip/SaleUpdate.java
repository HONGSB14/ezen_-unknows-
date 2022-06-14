package controller.slip;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SaleDao;
import dto.Slip;


/**
 * Servlet implementation class SaleUpdate
 */
@WebServlet("/slip/SaleUpdate")
public class SaleUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String date=request.getParameter("date");
		String note=request.getParameter("note");
		String carnum=request.getParameter("carnum");
		int cnum=Integer.parseInt(request.getParameter("cnum"));
		int snum=Integer.parseInt(request.getParameter("snum"));
		int fee=Integer.parseInt(request.getParameter("fee"));
		int cardfee=Integer.parseInt(request.getParameter("cardfee"));
		int flux=Integer.parseInt(request.getParameter("flux"));
		int daysale=Integer.parseInt(request.getParameter("daysale"));
		
		Slip slip = new Slip(cnum, snum, carnum, flux, fee, cardfee, daysale, note, date);

		boolean result = SaleDao.getsaleDao().updateSale(slip);
		
		if(result){
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
