package controller.datainfo;

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
 * Servlet implementation class YearInfo
 */
@WebServlet("/datainfo/YearInfo")
public class YearInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YearInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
int cnum=Integer.parseInt(request.getParameter("cnum"));
		
		ArrayList<Tacometer> locatinList= TacometerDao.gettacometerDao().getlocation(cnum);
		//날짜 객체 생성
		Date newDate = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(newDate);
		int year=Integer.parseInt(date.split("-")[0]);

		
		JSONArray ja= new JSONArray();
		try {
			for(Tacometer tacometer : locatinList) {
				//날짜별로 값을 넘기기위한 시간 쪼개기 
				String today=tacometer.getTdate().split(" ")[0];
				int tacoYear=Integer.parseInt( today.split("-")[0]);				
		
				if( year==tacoYear) {
					
					if(tacometer.getStartLocation() !=null) {
					
						String locationY=tacometer.getStartLocation().split(",")[0];
						String locationX=tacometer.getStartLocation().split(",")[1];
						JSONObject jo = new JSONObject();
						jo.put("locationY", locationY);
						jo.put("locationX", locationX);
						jo.put("date", tacometer.getTdate());
						ja.put(jo);
					}else {
						System.out.println("NULL POINER");
					}
				}
			}
		} catch (JSONException e) {
			System.out.println("JSON ERROR");
		}
		
		if(locatinList != null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(ja);
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
