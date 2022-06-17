package dao;

import java.util.ArrayList;

import dto.ApproveCar;

public class ApproveCarDao extends Dao {

	public ApproveCarDao() {
		super();// TODO Auto-generated constructor stub
	}
	
	
	static public ApproveCarDao approveCarDao = new ApproveCarDao();
	
	static public ApproveCarDao getApproveCarDao() {
		
		return approveCarDao;
		
	}
	//승인된 차량 리스트 불러오기
	public ArrayList<ApproveCar> approveCarList(){
		ArrayList<ApproveCar> list= new ArrayList<ApproveCar>();
		
		String sql="SELECT * FROM taxisaledata.approvecar WHERE apstate='1'";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				ApproveCar approveCar = new ApproveCar(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(approveCar);
			}
			return list;
		} catch (Exception e) {
			System.out.println("승인차량 리스트 호출 실패 !" +e);
		}
		
		return null;
	}
	
	//차량 아이디 값 가져오기
		public String getCarid(String carNum) {
			
			String sql="SELECT apcarid FROM taxisaledata.approvecar WHERE apcarnum='"+carNum+"'";
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
			} catch (Exception e) {
				System.out.println("B차량 아이디 값 가져오기 실패!" +e);
			}
			return null;
		}
	
	//차량 승인 값 바꾸기
	public void updateState(String carNum) {
		
		String sql="UPDATE taxisaledata.approvecar SET apstate='0' WHERE apcarnum='"+carNum+"'";
		try {
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		}catch (Exception e) {
			System.out.println("C차량 상태값 바꾸기 실패 !! "+e);
		}
		
	}
	
}
