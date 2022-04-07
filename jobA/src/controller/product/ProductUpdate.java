package controller.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ProductUpdate implements Initializable{
	private String pimage =null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Product product = ProductControl.select;
		txtpname.setText(product.getPname());
		txtpcontent.setText(product.getPcontent());
		txtpprice.setText(product.getPprice()+"");
		txtpath.setText(product.getPimg());
		if(product.getPcategory().equals("�����Ƿ�")) {opt1.setSelected(true);}
		if(product.getPcategory().equals("�����Ƿ�")) {opt2.setSelected(true);}
		if(product.getPcategory().equals("�±ǿ�ǰ")) {opt3.setSelected(true);}
		if(product.getPcategory().equals("����±ǿ�ǰ")) {opt4.setSelected(true);}
	
	}

    @FXML
    private Button btnupdate;

    @FXML
    private TextField txtpname;

    @FXML
    private TextArea txtpcontent;

    @FXML
    private TextField txtpprice;

    @FXML
    private Button btnimg;

    @FXML
    private RadioButton opt1;

    @FXML
    private ToggleGroup hello;

    @FXML
    private RadioButton opt2;

    @FXML
    private RadioButton opt3;

    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton opt4;

    @FXML
    private ImageView imp;

    @FXML
    private Button btnback;

    @FXML
    private Label txtpath;

    @FXML
    private Label miribogi;

    @FXML
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/product/product.fxml");
    }

    @FXML
    void imgadd(ActionEvent event) {
    	
    	//1. ���� ���� Ŭ���� 
    		FileChooser fileChooser = new FileChooser();
    	
    	//2.���ϼ��� [����] ���� 
    		fileChooser.getExtensionFilters().add(new ExtensionFilter("�̹�������:image file","*png","*jpg","*jepg","*gif"));
    	
    	//3.���ο� stage���� ���� ����ȭ�� ���� (������ â)
    		File file=fileChooser.showOpenDialog(new Stage());
	    		//���ϼ��ð�ü.showOpenDialog(�������� �̸�) ;
	    		//fileChooser ȭ�鿡�� ���õ� ������ file Ŭ���� ��üȭ
    	
    	//4.���� ���� ��� ǥ��
    		txtpath.setText("���ϰ��:" +file.getPath());
    	
    	//5.���ϰ�� 
    		pimage = file.toURI().toString();
    		
//    			System.out.println(file.getPath());				//��� ���м�: get path ->\
//    			System.out.println(file.toURI());				//��� ���м�:	toURI 	 ->/
//    			System.out.println(file.toURI().toString());	//���ڿ� ��ȯ
    	
    	
    	//6.�̸����� �̹�����Ʈ�ѿ� ����
    		Image image = new Image(pimage);	//�ش� �̹����� ��ΰ��� / ���еǾ� �־����.
    		miribogi.setText("");
    		imp.setImage(image);	
    		
    	//* ���õ� ������ ���� ������Ʈ ������ ����(�̵�) �ؿ���
    		try {
    			
    			//1.���� �Է� ��Ʈ��
    			FileInputStream inputStream = new FileInputStream(file); //file: fileChooser ���� ���õ� ���� ��ü inputstream �� �ֱ�
        		
    			//2.���� ��� ��Ʈ��
    			File copyfile = new File("C:\\Users\\504\\git\\ezen_-unknows-\\jobA\\src\\img\\"+file.getName()); //���ο� ��� ����
        		FileOutputStream outputStream = new FileOutputStream(copyfile);
				
        		//3.����Ʈ �迭 ����
        		byte [] bytes = new byte [1024*1024*1024];
        		
        		//4.�ݺ����� �̿��� inputstream�� ���� ��Ʈ�� ��� �о����
        		int size;
        		while(( size =inputStream.read(bytes))>0) {	//�о�� ����Ʈ�� 0���� ������ �ݺ��� ����[ �о�� ����Ʈ�� ���� ����]
        				outputStream.write(bytes, 0 ,size);
        		}
        		//5.����� ū ��쿡�� ��Ʈ�� ���� �ʼ�~!
        		inputStream.close();
        		outputStream.close();
        		//* ���ϸ� DB����
        		pimage= copyfile.toURI().toString();
    		} catch (Exception e) {
				System.out.println("���Ͽ���:"+e);
			}
    }

    @FXML
    void update(ActionEvent event) {
    		
    		if(pimage==null) { // ���� ������ �������� 
    			pimage= ProductControl.select.getPimg();
    		}
    		String pcategory = null;
        	if(opt1.isSelected()) {//���࿡ opt1 �̶�� fxid �� ��Ʈ���� ���õǾ����� 
        		pcategory="�����Ƿ�";
        	}
        	if(opt2.isSelected()) {
        		pcategory="�����Ƿ�";
        	}
        	if(opt3.isSelected()) {
        		pcategory ="�±ǿ�ǰ";
        	}
        	if(opt4.isSelected()) {
        		pcategory ="����±ǿ�ǰ";
        	}
    			
    		
    		Product upProduct = new Product(ProductControl.select.getpnum(),
    				txtpname.getText(), 
    				pimage, 
    				txtpcontent.getText(), 
    				pcategory, 
    				Integer.parseInt(txtpprice.getText()),
    				0, 
    				null, 
    				0);
    		
    		
    		boolean result = ProductDao.productDao.update(upProduct);
    		if(result) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setHeaderText("�����Ϸ�!");
    				alert.showAndWait();
    				Home.home.loadpage("/view/product/product.fxml");
    			
    		}else {
    			Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("��������!");
				alert.showAndWait();
				
    		}
    }

	
	
}
