package action;

import java.sql.SQLException;

import org.apache.commons.lang.RandomStringUtils;

import net.sf.json.JSONObject;
import sql.SQLCommunicate;
import unit.Good;

public class Shelves {
	private String action = null;
    private String title = null;
    private String typel = null;
    //typelΪ2��ʾ����,Ϊ1��ʾ����        
    private String classify = null;
    //��Ʒ�ķ���
    private String picture = null;
    private double price = 0.00;
    private String address = null;
    private int maxTime = 0;
    //�ʹ��ʱ��
	private String contact = null;	
	private String pic_location = null;
	private String good_id = null;
	String retur = null;
	JSONObject j = new JSONObject();
	public JSONObject run(JSONObject json,String myPhone){
		action = json.getString("action");
		System.out.println(action);
		if ("1".equals(action)) {
			Good good = new Good();
			title = json.getString("title");
			System.out.println("������ȡ1");
			typel = json.getString("typel");
			System.out.println("������ȡ2");
			classify = json.getString("classify");
			System.out.println("������ȡpicture");
			picture = json.getString("picture");
			System.out.println("��ȡ��picture");
			price = json.getDouble("price");
			System.out.println("������ȡ3");
			address = json.getString("address");
			System.out.println("������ȡ4");
			maxTime = json.getInt("maxTime");
			System.out.println("������ȡ5");
			contact = json.getString("contact");
			System.out.println("������ȡ6");
			good_id = RandomStringUtils.randomNumeric(10);
			good.setGood_id(good_id);
			good.setTitle(title);
			good.setTypel(typel);
			good.setClassify(classify);
			good.setPrice(price);
			good.setAddress(address);
			good.setMaxTime(maxTime);
			good.setContact(contact);
			System.out.println("ͼƬ");
			pic_location = "D:/TestSpace/"+RandomStringUtils.randomNumeric(10)+".png";
			util.StringToPicture.change(pic_location, picture);
			good.setPic_location(pic_location);
			System.out.println("ִ���ϼ����ݿ����");
		    retur = SQLCommunicate.PutonShelves(myPhone, good);
		    System.out.println("retur:"+retur);
		}
		if ("2".equals(action)) {
			good_id = json.getString("good_id");
				retur = SQLCommunicate.PulloffShelves(myPhone, good_id);
		}

		j.put("retur", retur);
		return j;
	}
}
