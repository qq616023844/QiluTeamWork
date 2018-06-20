package action;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.RandomStringUtils;

import net.sf.json.JSONObject;
import sql.SQLCommunicate;
import unit.User;
import util.CookieList;

public class Login {

	String myPhone = null;
	String password = null;
	String retur = null;
	User user = null;
	JSONObject j = new JSONObject();
	
	public JSONObject run(JSONObject json){
		String myPhone = json.getString("myPhone");
		String password = json.getString("password");
		retur = SQLCommunicate.login(myPhone, password);
		System.out.println("login"+retur);
		j.put("retur", retur);
		if (retur.equals("1")) {
			String key = RandomStringUtils.randomNumeric(30);
			Cookie cookie = new Cookie(key, myPhone);
			cookie.setMaxAge(60*60*60);
			CookieList.cookielist.add(cookie);
			user = SQLCommunicate.selectUser(myPhone);
			j.put("cookiecode", key);
			j.put("balance", user.getBalance());
			j.put("nickname", user.getNickname());
			j.put("myPhone", user.getMyPhone());
			j.put("creditScore", user.getCreditScore());
		}
		return j;
	}
}
