package action;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import sql.SQLConnect;
import util.CookieList;
//中枢，负责与前端交互并向后台分发任务
public class Hub extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String rec = null;
	String action = null;
	String cookiecode = null;
	String myPhone = null;
	JSONObject json = null;
	JSONObject j = null;
	String str = null;
	@Override
	public void init() throws ServletException {
		new SQLConnect().connect();
		try {
			System.setOut(new PrintStream(new File("C:/outLog.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get");
		rec = null;
		action = null;
		cookiecode = null;
		myPhone = null;
		json = null;
		j = null;
		str = null;
		
        System.out.println("---------------分割线--------------------");
        req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		rec = (String) req.getParameter("jsonall");
		System.out.println("recieve:"+rec);
        analysis(rec);
        //如果为登陆，则登陆。如果为其他，则判断用户cookie
        if ("13".equals(action)) {
        	System.out.println("doing login");
        	j = new Login().run(json);
        	System.out.println("has login");
		}else if("12".equals(action)){
			System.out.println("doing register");
			j = new Register().run(json);
			System.out.println("has register");
		}else{
	        if (cookiecode!=null) {
	        	System.out.println("cookiecode!=null");
	        	for(Cookie c:CookieList.cookielist){
	        		System.out.println("打印cookielist："+c.getName());
	        		if (c.getName().equals(cookiecode)) {
	        			myPhone = c.getValue();
						//action对应的操作
	        			System.out.println("执行action");
				        switch (action) {
					  		case "1":
					  		case "2":
					  			System.out.println("will run 1,2");
					  			j = new Shelves().run(json,myPhone);
					  			System.out.println("has run 1,2");
					  			break;
					  		case "3":
					  		case "4":
					  			System.out.println("will run 3,4");
					  			j = new Trade().run(json,myPhone);
					  			System.out.println("has run 3,4");
					  			break;
					  		case "5":
					  			System.out.println("will run 5");
					  			j = new SearchUser().run(json,myPhone);
					  			System.out.println("has run 5");
					  			break;
					  		case "6":
					  			System.out.println("will run 6");
					  			str = new SearchOrder().run(json,myPhone);
					  			System.out.println("has run 6");
					  			break;
					  		case "7":
					  		case "8":
					  		case "9":
					  		case "14":
					  		case "15":
					  			System.out.println("will run 7,8,9,10,14,15");
					  			str = new SearchGood().run(json,myPhone);
					  			System.out.println("has run 7,8,9,10,14,15");
					  			break;
					  		case "16":
					  			System.out.println("will run 16");
					  			j = new Modifypassword().run(json,myPhone);
					  			System.out.println("has run 16");
					  			break;
					  		case "17":
					  			System.out.println("will run 17");
					  			j = new Modifynickname().run(json,myPhone);
					  			System.out.println("has run 17");
					  			break;
					  		case "18":
					  			System.out.println("will run 18");
					  			j = new FinishOrder().run(json,myPhone);
					  			System.out.println("has run 18");
					  			break;
				  		}
						break;
					}
	        	}
			}
		}
        if (j==null) {
			j = new JSONObject();
			System.out.println("j is null");
		}
        if ("6".equals(action)||"7".equals(action)||"8".equals(action)||"9".equals(action)||"14".equals(action)||"15".equals(action)) {
        	System.out.println("response11:"+str);
            resp.getWriter().write(str);
		}else{
			System.out.println("response:"+j.toString());
	        resp.getWriter().write(j.toString());
		}
		
        
	}
	
	public void analysis(String rec)
    {
		json = JSONObject.fromObject(rec);
        action = json.getString("action");
        if (json.has("cookiecode")) {
			cookiecode = json.getString("cookiecode");
		}else{
			cookiecode = null;
		}
        System.out.println("has get cookiecode:"+cookiecode);
	}
}
