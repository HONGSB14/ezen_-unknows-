package controller.driver;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DriverDao;
import dto.Driver;

/**
 * Servlet implementation class DriverSignup
 */
@WebServlet("/driver/DriverSignup")
public class DriverSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverSignup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Random random =new Random();
		
		int cnum=Integer.parseInt(request.getParameter("cnum"));
		String dname=request.getParameter("dname");
		String dnote=request.getParameter("dnote");
		int dnum = random.nextInt(999999)+100000;
		Driver  driver = new Driver(cnum, dnum, dname, dnote, null);
		boolean result=DriverDao.getDriverDao().driversignup(driver);

		if(result) {
			response.sendRedirect("/taxicompany_data_system/driver/driver_registration.jsp");
			System.out.println(1);
		}else{
			response.sendRedirect("/taxicompany_data_system/errorpage.jsp");
			System.out.println(2);
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
