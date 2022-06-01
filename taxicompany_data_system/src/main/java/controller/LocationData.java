package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarDao;
import dao.LocationDao;
import dto.Location;

/**
 * Servlet implementation class LocationData
 */
@WebServlet("/LocationData")
public class LocationData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tmX=request.getParameter("tmX");
		String tmY=request.getParameter("tmY");
		String carNum=request.getParameter("plainNo");
		int cnum= Integer.parseInt(request.getParameter("cnum"));
		String coordinate=tmY+","+tmX;
		
		Location location = new Location(cnum, coordinate, carNum, null);
		
		boolean result=LocationDao.getLocationDao().locationset(location);
		
		if(result) {
			System.out.println("db저장성공");
		}else {
			System.out.println("db저장실패");
			response.sendRedirect("/taxicompany_daya_system/errorpage.jsp");
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
