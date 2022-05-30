package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import dao.TacometerDao;
import dto.Tacometer;

/**
 * Servlet implementation class DataInfo
 */
@WebServlet("/DataInfo")
public class DataInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int cnum=Integer.parseInt(request.getParameter("cnum"));
		
		//여기서 jsonarray 로 바로 못쓰나?
		ArrayList<Tacometer> locationList=TacometerDao.gettacometerDao().getlocation(cnum);
		
		JSONObject jo = new JSONObject();
		JSONArray array=new JSONArray();
	
		Date date2 = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date2);
		int day=Integer.parseInt( today.split("-")[2]);
		int month=Integer.parseInt( today.split("-")[1]);
		int year=Integer.parseInt( today.split("-")[0]);
		int sum= year+month;
		
		
		
		for(Tacometer tacometer : locationList) {
			String current=tacometer.getTdate().split(" ")[0];
			
			int tDay=Integer.parseInt(current.split("-")[2]);
			int tMonth=Integer.parseInt(current.split("-")[1]);
			int tYear=Integer.parseInt(current.split("-")[0]);
			int sum2=tYear+tMonth;
			
			if((day-tDay)<7 || sum==sum2) {
				try {
					jo.put("location",tacometer.getStartLocation());
					jo.put("date",tacometer.getTdate());
				} catch (JSONException e) {
					System.out.println("json err~!");
				}
				array.put(jo);	
			}
		}
		
		
		
		
		
		
		
		if(locationList != null) {
			response.setContentType("application/json");
			response.getWriter().print(array);		
		}else {
			
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
