package action;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import net.sf.json.JSONObject;
import sql.SQLCommunicate;
import unit.Good;
import util.PictureToString;
public class SearchGood {
    private List<Good> good = new ArrayList<>();
	private String good_id = null;
	private Good good1 = null;
	private String action = null;
	private String title = null;
	private String classify = null;
	private Gson g = new Gson();
	private String str = null;
	public String run(JSONObject json,String myPhone){
		action = json.getString("action");
		System.out.println(action);
		if ("7".equals(action)) {
			good_id = json.getString("good_id");
			good1 = SQLCommunicate.selectGood(good_id);
			if (good1!=null) {
				good1.setPicture(PictureToString.change(good1.getPic_location()));
				good1.setPic_location(null);
				good.add(good1);
				str = g.toJson(good);
			}

		}
		if ("8".equals(action)) {
			title = json.getString("title");
			good = SQLCommunicate.selectGoodByTitle(title);
			if (good!=null) {
				for(int i=0;i<good.size();i++){
					good.get(i).setPicture(PictureToString.change(good.get(i).getPic_location()));
					good.get(i).setPic_location(null);
			}

			}
			str = g.toJson(good);
		}
		if ("9".equals(action)) {
			classify = json.getString("classify");
			good = SQLCommunicate.selectGoodByClassify(classify);
			if (good!=null) {
				for(int i=0;i<good.size();i++){
					good.get(i).setPicture(PictureToString.change(good.get(i).getPic_location()));
					good.get(i).setPic_location(null);
				}
			}

			str = g.toJson(good);
		}
		if ("14".equals(action)) {
			good = SQLCommunicate.selectAll();
			if (good!=null) {
				for(int i=0;i<good.size();i++){
					System.out.println("×ª»¯Í¼Æ¬ÖÐ");
					good.get(i).setPicture(PictureToString.change(good.get(i).getPic_location()));
					good.get(i).setPic_location(null);
				}
			}

			str = g.toJson(good);
			
		}
		if ("15".equals(action)) {
			good = SQLCommunicate.selectGoodByPhone(myPhone);
			if (good!=null) {
				for(int i=0;i<good.size();i++){
					good.get(i).setPicture(PictureToString.change(good.get(i).getPic_location()));
					good.get(i).setPic_location(null);
			}
			}
			str = g.toJson(good);
		}
		return str;

	}
}
