package action;

import java.util.List;

import com.google.gson.Gson;

import net.sf.json.JSONObject;
import sql.SQLCommunicate;
import unit.Order;
import util.PictureToString;
public class SearchOrder {
	String action = null;
	private List<Order> orderlist = null;
	private String str = null;
	private Gson g = new Gson();
	public String run(JSONObject json,String myPhone){
		action = json.getString("action");
		System.out.println(action);
		if ("6".equals(action)) {
			orderlist = SQLCommunicate.selectOrder(myPhone);
			if (orderlist!=null) {
				for(int i=0;i<orderlist.size();i++){
					orderlist.get(i).getGood().setPicture(PictureToString.change(orderlist.get(i).getGood().getPic_location()));
					orderlist.get(i).getGood().setPic_location(null);
				}
			}
			str = g.toJson(orderlist);
		}
		return str;
	}
}
