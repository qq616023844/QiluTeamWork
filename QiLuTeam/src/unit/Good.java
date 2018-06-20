package unit;

public class Good {
	
	private String good_id;    //自定义的物品id
    private String title;        //名字
    private double price;        //价格
    private String myPhone;      //用户名
    private String classify;    //分类
    private String contact;     //联系方式
    private String typel;            //1：共享
                                     //0：出售
    private String nickname;     //用户昵称
    private String address;           //地址
    private String pic_location;    //图片地址
    private String picture;         //图片
    private int maxTime;//最长租用时间
    
    
	public String getGood_id() {
		return good_id;
	}
	public void setGood_id(String good_id) {
		this.good_id = good_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMyPhone() {
		return myPhone;
	}
	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTypel() {
		return typel;
	}
	public void setTypel(String typel) {
		this.typel = typel;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPic_location() {
		return pic_location;
	}
	public void setPic_location(String pic_location) {
		this.pic_location = pic_location;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

}
