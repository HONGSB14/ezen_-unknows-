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
		
		ArrayList<Tacometer> locationList=TacometerDao.gettacometerDao().getlocation(cnum);
		
		ArrayList<String> location = new ArrayList<String>();
		ArrayList<String> date = new ArrayList<String>();
		
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
			if((day-7)<tDay && sum==sum2) {
				location.add(tacometer.getStartLocation());
				date.add(tacometer.getTdate());
			}
			
		}
		
		if(locationList != null) {
			response.getWriter().print(location);
			response.getWriter().print(date);
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
