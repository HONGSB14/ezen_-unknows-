package controller.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarDao;
import dto.Car;

/**
 * Servlet implementation class CarRresistration
 */
@WebServlet("/car/CarResistration")
public class CarResistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarResistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("UTF-8");
		int cnum= Integer.parseInt(request.getParameter("cnum"));
		String carNum = request.getParameter("carnum");
		String carType = request.getParameter("selectCarType");
		String carName = request.getParameter("carname");
		String fule= request.getParameter("selectFuel");
		
		Car car =new Car(cnum, carNum, carType, carName, fule);
		System.out.println(car.toString());
		boolean result = CarDao.getcarDao().carresistration(car);
		
		if(result) {
			response.sendRedirect("/taxicompany_data_system/car/carResistration.jsp");
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
