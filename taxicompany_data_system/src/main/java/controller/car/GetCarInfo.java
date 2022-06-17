package controller.car;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import dao.CarDao;
import dto.Car;

/**
 * Servlet implementation class GetCarNum
 */
@WebServlet("/car/GetCarInfo")
public class GetCarInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCarInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cnum=Integer.parseInt(request.getParameter("cnum"));
		ArrayList<Car> carList=CarDao.getcarDao().carlist(cnum);
		System.out.println(carList.toString());
		JSONArray ja= new JSONArray();
		for(Car car : carList) {
			String carNum=car.getCarNum();
			String carId=car.getCarId();
			try {
				JSONObject jo= new JSONObject();
				jo.put("carNum",carNum);
				jo.put("carId", carId);
				ja.put(jo);
			} catch (JSONException e) {
				System.out.println("JSON ERROR");
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("aplication/json");
		response.getWriter().print(ja);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
