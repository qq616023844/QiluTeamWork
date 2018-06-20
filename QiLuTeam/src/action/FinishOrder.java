package action;

import net.sf.json.JSONObject;
import sql.SQLCommunicate;

public class FinishOrder {
	String myPhone = null;
	String iD_Order = null;
	String retur = null;
	JSONObject j = new JSONObject();
	public JSONObject run(JSONObject json,String myPhone){
		this.myPhone = myPhone;
		iD_Order = json.getString("iD_Order");
		retur = SQLCommunicate.finishOrder(myPhone, iD_Order);
		j.put("retur", retur);
		return j;
	}
}
