package action;

import net.sf.json.JSONObject;
import sql.SQLCommunicate;

public class Register {

	String myPhone = null;
	String password = null;
	String retur = null;
	JSONObject j = new JSONObject();
	public JSONObject run(JSONObject json){
		myPhone = json.getString("myPhone");
		password = json.getString("password");
		retur = SQLCommunicate.register(myPhone, password);
		j.put("retur", retur);
		return j;
	}
}
