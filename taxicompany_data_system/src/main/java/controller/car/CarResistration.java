package controller.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ApproveCarDao;
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
		String carNum = request.getParameter("selectCarNum");
		String carType = request.getParameter("selectCarType");
		String carName = request.getParameter("carname");
		String fule= request.getParameter("selectFuel");
		//차량 아이디 가져오기
		String carId=ApproveCarDao.getApproveCarDao().getCarid(carNum);
	
		Car car =new Car(cnum, carNum, carId, carType, carName, fule);
		
		//승인 차량 상태 변경 (사용가능->사용중)
		ApproveCarDao.getApproveCarDao().updateState(carNum);
		
		//차량 등록
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
