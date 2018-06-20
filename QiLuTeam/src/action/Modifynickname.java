package action;

import net.sf.json.JSONObject;
import sql.SQLCommunicate;

public class Modifynickname {
	String myPhone = null;
	String nickname = null;
	String retur = null;
	JSONObject j = new JSONObject();
	public JSONObject run(JSONObject json,String myPhone){
		this.myPhone = myPhone;
		nickname = json.getString("nickname");
		retur = SQLCommunicate.modifynickname(myPhone, nickname);
		j.put("retur", retur);
		return j;
	}
}
