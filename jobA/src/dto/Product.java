package dto;

public class Product {

	private int pnum ;
	private String pname ;
	private String pimg ;
	private String pcontent ;
	private String pcategory ;
	private int pprice ;
	private int pacctivation ;
	private String pdate;
	private int mnum ;

	public Product() {
		
	}

	public Product(int pnum, String pname, String pimg, String pcontent, String pcategory, int pprice,
			int pacctivation, String pdate, int mnum) {
		super();
		this.pnum = pnum;
		this.pname = pname;
		this.pimg = pimg;
		this.pcontent = pcontent;
		this.pcategory = pcategory;
		this.pprice = pprice;
		this.pacctivation = pacctivation;
		this.pdate = pdate;
		this.mnum = mnum;
	}

	
	
	public int getpnum() {
		return pnum;
	}

	public void setpnum(int pnum) {
		this.pnum = pnum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPimg() {
		return pimg;
	}

	public void setPimg(String pimg) {
		this.pimg = pimg;
	}

	public String getPcontent() {
		return pcontent;
	}

	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}

	public String getPcategory() {
		return pcategory;
	}

	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public int getPacctivation() {
		return pacctivation;
	}

	public void setPacctivation(int pacctivation) {
		this.pacctivation = pacctivation;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	
	
	
}
