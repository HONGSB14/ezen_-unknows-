package application.Day22;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Day22_1 extends Application{

	@Override
	public void start(Stage ��������) throws Exception {
			//�������� �̸� ���ϱ� [�ƹ��ų�]
		//1.�� �������� �ۼ��� ���� [ fxml ] �� ��ü�� ��������
		Parent parent = FXMLLoader.load(getClass().getResource("dbtest.fxml"));
		//2. �� ��ü�� ���� ������ fxml ��ü�� �־��ش�
		Scene scene = new Scene(parent);		
		��������.setScene(scene);
		��������.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
