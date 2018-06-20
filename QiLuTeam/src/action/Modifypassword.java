package action;

import net.sf.json.JSONObject;
import sql.SQLCommunicate;

public class Modifypassword {
	String myPhone = null;
	String password = null;
	String retur = null;
	JSONObject j = new JSONObject();
	public JSONObject run(JSONObject json,String myPhone){
		this.myPhone = myPhone;
		password = json.getString("password");
		retur = SQLCommunicate.modifypassword(myPhone, password);
		j.put("retur", retur);
		return j;
	}
}
