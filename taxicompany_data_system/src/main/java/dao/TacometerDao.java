package dao;

public class TacometerDao extends Dao {

	public TacometerDao() {
		super();
	}
	

	static public TacometerDao tacometerDao = new TacometerDao();
	
	static public TacometerDao gettacometerDao() {
			
		return tacometerDao;
	}
	
}
