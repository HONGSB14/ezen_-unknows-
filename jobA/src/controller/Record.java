package controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import dao.MemberDao;
import dao.ProductDao;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;

public class Record implements Initializable{
	
	@FXML
	private Label lblmtotal;
	
	@FXML
	private Label lblptotal;
	
	@FXML
	private Label lblbtotal;
	
	@FXML
	private BarChart mbc;
	
	@FXML
	private BarChart bbc;
	
	@FXML
	private BarChart pbc;
	
	@FXML
	private PieChart ppc;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//��ü ȸ�� ��
		int mtotal = MemberDao.memberDao.counttotal("member");
		lblmtotal.setText(mtotal+" ��");
		
		//��ü ��ǰ ��
		int ptotal = MemberDao.memberDao.counttotal("product");
		lblptotal.setText(ptotal+" ��");
		
		//��ü �Խù� ��
		int btotal = MemberDao.memberDao.counttotal("board");
		lblbtotal.setText(btotal+" ��");
		
		//3.��¥���� ȸ�� ���� ��
			
			//3-1.xy�� �迭 ����
			XYChart.Series series = new XYChart.Series<>(); // x,y�� �迭 ����
				//XYChart.Series : �迭 Ŭ���� 
					//XYChart.Data : �迭 ������ Ŭ���� [ x ���� ��, y���� ��]
				
	
					//��¥ ���� ȸ������ �� üũ ex))    [2022-04-11, 3]
									//Map �÷��� => Ű(��¥) , ��(���� ��) ���� �ϳ��� ��Ʈ�� ����
					//3-2.DB���� ������ ��������
					Map<String , Integer> map=MemberDao.memberDao.datetotal("member" , "mcince");
					
					//3-3.�����͸� �迭�� �߰�
					for(String key : map.keySet()) {//�ݺ���
						//3-1 :������ �迭 ����( ������: key= ��¥, value =������ �� 
						XYChart.Data data = new XYChart.Data<>(key,map.get(key));
						//3-2 : �ش� ������ ��ü�� �迭�� �߰�
						series.getData().add(data);
					}
					//������Ʈ
					mbc.getData().add(series);
				
				
		//4.��¥���� �Խù� ��� ��
				
			//4-1.�迭 ����
				XYChart.Series series2 = new XYChart.Series<>();
			
			//4-2. ������ ����
				Map<String , Integer> map2=MemberDao.memberDao.datetotal( "board" , "bdate" );
			
			//4-3. ��� ������ �߰�
				for(String key : map2.keySet()) {
					XYChart.Data data= new XYChart.Data<>(key, map2.get(key));
					
					series2.getData().add(data);
				}
			//4-4. ��Ʈ�� �迭�߰�
				bbc.getData().add(series2);
			
			
		//5.���� ��Ʈ : ī�װ� �� ����
			//5-1. ObservableList<���� ��Ʈ ������ �ڷ���> ����Ʈ�� ����
				ObservableList<PieChart.Data> pielist = FXCollections.observableArrayList();
			//5-2. pieChart.data ��ü ����
				Map<String, Integer> map3 = MemberDao.memberDao.countcategoty();
			//5-3. �����͸� ����Ʈ�� �߰�
				for(String key: map3.keySet()) {
					PieChart.Data temp = new PieChart.Data(key, map3.get(key));
					//�����͸� ����Ʈ�� �߰� 
					pielist.add(temp);
				}
			//5-4. ����Ʈ�� ������Ʈ�� �߰�	
				ppc.setData(pielist);	
				
//---------------------------------------------------------------------------------------------------------------------------
//	-----------------------------------------------------------------------------------------------------------------------------			
			//1. �迭 ���� 
				XYChart.Series series3 = new XYChart.Series<>();
				
					series3.setName("��������"); // * �迭�� 
				
					// 2. ������ ���� ( x�ప , y�ప )
					XYChart.Data data = new XYChart.Data<>("�ŵ���" , 10);
					// 3. �迭�� ������ �߰�
					series3.getData().add(data);
					
					XYChart.Data data2 = new XYChart.Data<>("��ȣ��" , 20);
					series3.getData().add(data2);
					
					XYChart.Data data3 = new XYChart.Data<>("���缮" , 15);
					series3.getData().add(data3);
					
				// 4. ��Ʈ�� �迭�߰�
				pbc.getData().add(series3);
					
				XYChart.Series series4 = new XYChart.Series<>();
					series4.setName("��������");

					XYChart.Data data4 = new XYChart.Data<>("�ŵ���" , 25); // 2. ������ ���� ( x�ప , y�ప )
					series4.getData().add(data4); // 3. �迭�� ������ �߰�
					
					XYChart.Data data5 = new XYChart.Data<>("��ȣ��" , 40);
					series4.getData().add(data5);
					
					XYChart.Data data6 = new XYChart.Data<>("���缮" , 50);
					series4.getData().add(data6);
			
				pbc.getData().add(series4);
		
	
	}

}
