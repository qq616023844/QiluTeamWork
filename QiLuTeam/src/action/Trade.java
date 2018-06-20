package action;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

import net.sf.json.JSONObject;
import sql.SQLCommunicate;
import unit.Good;
import unit.Order;

public class Trade {
	private String action = null;
	private String good_id = null;
	private String myPhone = null;
	private String numberPhoneOther = null;
	private int useTime = 0;
	private String iD_Order = null;
	private Date tradeTime = new Date(); 
	private JSONObject j = new JSONObject();
	public JSONObject run(JSONObject json,String myPhone){
		action = json.getString("action");
		System.out.println(action);
		if ("3".equals(action)) {
			good_id = json.getString("good_id");
			this.myPhone = myPhone;
			numberPhoneOther = json.getString("numberPhoneOther");
			iD_Order = SQLCommunicate.buyOrBorrowGood(createOrder("3"));
			if (iD_Order!=null) {
				j.put("retur", "8");
				j.put("iD_Order", iD_Order);
			}else{
				j.put("retur", "9");
			}
		}
		if ("4".equals(action)) {
			good_id = json.getString("good_id");
			this.myPhone = myPhone;
			useTime = json.getInt("useTime");
			numberPhoneOther = json.getString("numberPhoneOther");
			iD_Order = SQLCommunicate.buyOrBorrowGood(createOrder("2"));
			if (iD_Order!=null) {
				j.put("retur", "10");
				j.put("iD_Order", iD_Order);
			}else{
				j.put("retur", "11");
			}
		}
		return j;
	}
	
	//´´½¨¶©µ¥
	public Order createOrder(String typel){
		Order order = new Order();
		Good good = SQLCommunicate.selectGood(good_id);
		System.out.println("Trade.createOrder.good_id:"+good.getGood_id());
		order.setGood(good);
		String iD_Order1 = RandomStringUtils.randomNumeric(10);
		order.setiD_Order(iD_Order1);
		order.setMyPhone(myPhone);
		order.setNumberPhoneOther(numberPhoneOther);
		order.setState(false);
		order.setTypel(typel);
		order.setUseTime(useTime);
		order.setTradeTime(tradeTime);
		return order;
	}
}
