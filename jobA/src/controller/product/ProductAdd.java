package controller.product;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import controller.home.Home;
import controller.login.Login;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ProductAdd implements Initializable{
	
	Alert alert = new Alert(AlertType.INFORMATION);
	
	private String pimage =null;//�޼ҵ� �ۿ��� �����ϴ� ����: imgadd �޼ҵ��  add�޼ҵ忡�� ����ϱ� ����
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	 @FXML
	    private Button btnadd;

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
	    private ToggleGroup category;

	    @FXML
	    private RadioButton opt2;

	    @FXML
	    private RadioButton opt3;

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
 
    //��ǰ��� �޼ҵ�
    @FXML
    void add(ActionEvent event) {
    	//1.��Ʈ�ѿ� �Էµ� ������ ��������
    	String pname = txtpname.getText();
    	String pcontent = txtpcontent.getText();
    	int pprice =Integer.parseInt(txtpprice.getText());
    	
    	String pcategory = null;
    	if(opt1.isSelected()) {//���࿡ opt1 �̶�� fxid �� ��Ʈ��(������ư)�� ���õǾ����� 
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
    	int mnum = Login.member.getMnum(); 	// (�α������� �ҷ�����) loginpane ��  �α��� ������ �α��� ������ �̹� ��Ƴ��⶧���� 
    	
    	//2.��üȭ
    	Product product = new Product(0, pname, pimage, pcontent, pcategory, pprice, 1, null, mnum);
    	//3.DBó��
    	boolean result= ProductDao.productDao.add(product);
    	//4.���ó��
    		if(result) {
    			
    			alert.setHeaderText("��ǰ ��� �Ϸ�");
    			alert.showAndWait();
    			Home.home.loadpage("/view/product/product.fxml");
    		}else {
    			alert.setHeaderText("��ǰ ��� ����");
				alert.showAndWait();
    		}
    }
   
    //�ڷΰ��� �޼ҵ�
    @FXML
    void back(ActionEvent event) {
    	Home.home.loadpage("/view/product/product.fxml");
    }
    
    //�̹��� ��� �޼ҵ�
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

}
