package action;

import net.sf.json.JSONObject;
import sql.SQLCommunicate;
import unit.User;

public class SearchUser {

	private User user = null;
	String action = null;
	private JSONObject j = new JSONObject();
	public JSONObject run(JSONObject json,String myPhone){
		action = json.getString("action");
		System.out.println(action);
		if ("5".equals(action)) {
			user = SQLCommunicate.selectUser(myPhone);
			j.put("User", user);
		}
		return j;
	}
}
