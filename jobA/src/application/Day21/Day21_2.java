package application.Day21;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Day21_2 extends Application {

	@Override
	public void start(Stage ��������) throws Exception { 
		// TODO Auto-generated method stub
		
		//1. fxml ���� �ҷ����� : �����̳� 
		Parent parent = FXMLLoader.load(getClass().getResource("test.fxml"));
		//		parent : fxml����(����������) �� ��üȭ 
		//			FXMLLoader.load(getClass().getResource("���/fxml���ϸ�"));
		//2. �� ��ü ���� : ���� �����̳� �ֱ�
		Scene scene = new Scene(parent);
			
		//3. ���������� �� �ֱ�
		��������.setScene(scene);
		
		//4. �������� ����
		��������.show();
	}
	public static void main(String[] args) {
		launch(args); 
		
	}
}
