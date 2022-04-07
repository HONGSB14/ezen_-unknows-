package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Product;

public class ProductDao {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public static ProductDao productDao = new ProductDao();
	
	public ProductDao () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3307/javafx?serverTimezone=UTC","root","1234");
		} catch (Exception e) {
			System.out.println("[DB연동 실패 ]"+e);
		}
	}



	
	//1.제품등록
	public boolean add(Product product) {
		try {
			String sql = "insert into product(pname,pimg,pcontent,pcategory,pprice,pacctivation,mnum) values(?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, product.getPname());
			ps.setString(2, product.getPimg());
			ps.setString(3, product.getPcontent());
			ps.setString(4, product.getPcategory());
			ps.setInt(5, product.getPprice());
			ps.setInt(6, product.getPacctivation());
			ps.setInt(7, product.getMnum());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	
	//2.모든 제품 출력 [tableview 사용 x -> arraylist 사용  o]
	public ArrayList<Product> list(String category ,String search) {
		ArrayList<Product> productlist = new ArrayList<>();
		
		try {
			String sql=null;
			//검색이 없을경우
			
			if(search ==null) {//검색이  없을경우
				 sql ="select * from product where pcategory =? order by pnum desc";
				ps= conn.prepareStatement(sql);
				ps.setString(1, category);
			}else{													//필드명 like %값%   ->    [값이 포함된걸 찾는 비교연산자]
				 sql ="select * from product where pcategory =? and pname like '%"+search+"%' order by pnum desc";
				ps= conn.prepareStatement(sql);
				ps.setString(1, category);
				//ps.setString(2, search);
			}
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getInt(9)         );
					productlist.add(product);	//리스트 객체 에 담기
			}
			return productlist;					//리스트 반환
		} catch (Exception e) {
			System.out.println("카테고리 오류 !"+e);
		}
		return null;							//만약 실패시 NULL 반환
	}
//	
	//3.제품조회
//	public boolean search() {}
	
	//4.제품삭제
	public boolean delete(int pnum) {
		try {
			String sql = "delete from product where pnum =? ";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pnum);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
				System.out.println("제품삭제오류!"+e);
		}
		return false;
	}
	
	//5.제품수정
	public boolean update(Product product) {
		try {
			String sql = "update product set pname=? ,pimg=?, pcontent=? ,pcategory=? ,pprice=? where pnum=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, product.getPname());
			ps.setString(2, product.getPimg());
			ps.setString(3, product.getPcontent());
			ps.setString(4, product.getPcategory());
			ps.setInt(5, product.getPprice());
			ps.setInt(6, product.getpnum());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("sql 오류123123"+e);
		}
		return false;
	}
	
	//6.상태변경
    public boolean activation(int pnum) {
    	try {
    		String sql = "select pacctivation from product where pnum=?";
        	ps=conn.prepareStatement(sql);
        	ps.setInt(1, pnum);
        	rs=ps.executeQuery();
        	if(rs.next()) { //검색 결과가 존재하면 다음레코드 가져오기
        		String upsql = null;
        		if(rs.getInt(1)==1){	//현재 제품의 상태가 거래중 이면
        			upsql = "update product set pacctivation=2 where pnum=?";
        		}else if(rs.getInt(1)==2) {//현재 제품의 상태가 판매완료이면
        			upsql = "update product set pacctivation=3 where pnum=?";
        		}else if (rs.getInt(1)==3) {//현재 제품의 상태가 판매 중이면
        			upsql = "update product set pacctivation=1 where pnum=?";
        		}
        		ps=conn.prepareStatement(upsql);
        		ps.setInt(1, pnum);
        		ps.executeUpdate();
        		return true;
        	}
		} catch (Exception e) {
			System.out.println("sql 오류!!!! "+e);
		}
    	
    	return false;
    	
    }
	
	
	
	
	
	
















}
