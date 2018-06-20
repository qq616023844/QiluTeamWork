package unit;

import java.util.Date;

public class Order {
	private String typel = null;
	//1表示出租，2借入，3买入，4卖出
	private boolean state = false;
	//false表示进行中，true表示已完成
	private Good good = null;
	private Date tradeTime = null;
	private int useTime = 0;
	private String numberPhoneOther = null;
	private String myPhone = null;
	private String iD_Order = null;
	
	
	public String getTypel() {
		return typel;
	}
	public void setTypel(String typel) {
		this.typel = typel;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public int getUseTime() {
		return useTime;
	}
	public void setUseTime(int useTime) {
		this.useTime = useTime;
	}
	public String getNumberPhoneOther() {
		return numberPhoneOther;
	}
	public void setNumberPhoneOther(String numberPhoneOther) {
		this.numberPhoneOther = numberPhoneOther;
	}
	public String getMyPhone() {
		return myPhone;
	}
	public void setMyPhone(String myPhone) {
		this.myPhone = myPhone;
	}
	public String getiD_Order() {
		return iD_Order;
	}
	public void setiD_Order(String iD_Order) {
		this.iD_Order = iD_Order;
	}
	
}
