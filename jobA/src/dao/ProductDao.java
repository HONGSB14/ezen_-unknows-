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
			System.out.println("[DB���� ���� ]"+e);
		}
	}



	
	//1.��ǰ���
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
	
	//2.��� ��ǰ ��� [tableview ��� x -> arraylist ���  o]
	public ArrayList<Product> list(String category ,String search) {
		ArrayList<Product> productlist = new ArrayList<>();
		
		try {
			String sql=null;
			//�˻��� �������
			
			if(search ==null) {//�˻���  �������
				 sql ="select * from product where pcategory =? order by pnum desc";
				ps= conn.prepareStatement(sql);
				ps.setString(1, category);
			}else{													//�ʵ�� like %��%   ->    [���� ���ԵȰ� ã�� �񱳿�����]
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
					productlist.add(product);	//����Ʈ ��ü �� ���
			}
			return productlist;					//����Ʈ ��ȯ
		} catch (Exception e) {
			System.out.println("ī�װ� ���� !"+e);
		}
		return null;							//���� ���н� NULL ��ȯ
	}
//	
	//3.��ǰ��ȸ
//	public boolean search() {}
	
	//4.��ǰ����
	public boolean delete(int pnum) {
		try {
			String sql = "delete from product where pnum =? ";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pnum);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
				System.out.println("��ǰ��������!"+e);
		}
		return false;
	}
	
	//5.��ǰ����
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
			System.out.println("sql ����123123"+e);
		}
		return false;
	}
	
	//6.���º���
    public boolean activation(int pnum) {
    	try {
    		String sql = "select pacctivation from product where pnum=?";
        	ps=conn.prepareStatement(sql);
        	ps.setInt(1, pnum);
        	rs=ps.executeQuery();
        	if(rs.next()) { //�˻� ����� �����ϸ� �������ڵ� ��������
        		String upsql = null;
        		if(rs.getInt(1)==1){	//���� ��ǰ�� ���°� �ŷ��� �̸�
        			upsql = "update product set pacctivation=2 where pnum=?";
        		}else if(rs.getInt(1)==2) {//���� ��ǰ�� ���°� �ǸſϷ��̸�
        			upsql = "update product set pacctivation=3 where pnum=?";
        		}else if (rs.getInt(1)==3) {//���� ��ǰ�� ���°� �Ǹ� ���̸�
        			upsql = "update product set pacctivation=1 where pnum=?";
        		}
        		ps=conn.prepareStatement(upsql);
        		ps.setInt(1, pnum);
        		ps.executeUpdate();
        		return true;
        	}
		} catch (Exception e) {
			System.out.println("sql ����!!!! "+e);
		}
    	
    	return false;
    	
    }
	
	
	
	
	
	
















}
