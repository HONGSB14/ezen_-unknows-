package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CompanyDao;
import dto.Company;

/**
 * Servlet implementation class CompanySignup
 */
@WebServlet("/CompanySignup")
public class CompanySignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanySignup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		String price=request.getParameter("price");
		String bn1=request.getParameter("three");
		String bn2 = request.getParameter("two");
		String bn3 = request.getParameter("five");
		String bnSum=(bn1+bn2+bn3);
		int bn= Integer.parseInt(bnSum);
		String companyName = request.getParameter("companyname");
		String bank=request.getParameter("bank");
		String account=request.getParameter("account");
		
		Company company =new Company(cnum, bn, companyName, price, bank, account);
		
		boolean result=CompanyDao.getCompanyDao().companysignup(company);
		
		if(result) {
			response.sendRedirect("/taxicompany_data_system/company/csignupsuccess.jsp?successcnum="+cnum);
			
		}else {
			response.sendRedirect("/taxicompany_data_system/errorpage.jsp");
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
