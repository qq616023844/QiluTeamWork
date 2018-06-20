package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.mysql.jdbc.Connection;

import unit.Good;
import unit.Order;
import unit.User;

public class SQLCommunicate {

	static Connection conn = (Connection) SQLConnect.conn;
	//登陆
	public static String login(String myPhone,String password){
		String sql = "select password from `user` where myPhone = '"+myPhone+"'";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if(rs.next()){			
				if (rs.getString("password").equals(password)) {
					return "1";
				}else{
					return "3";
				}
			}else{
				return "2";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
	}
	//注册账号
	public static String register(String myPhone,String password){
		String sql = "insert into `user` values(?,?,?,?,?) ";
		try {
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, myPhone);
			pstat.setDouble(2, 0.00);
			pstat.setString(3, "nickname");
			pstat.setInt(4, 0);
			pstat.setString(5, password);
			pstat.executeUpdate();
			return "14";
		} catch (SQLException e) {
			e.printStackTrace();
			return "15";
		}
	}
	//修改密码
	public static String modifypassword(String myPhone,String password){
		String sql = "update `user` set password = '"+password+"' where myPhone = '"+myPhone+"'";
			try {
				conn.createStatement().executeUpdate(sql);
				return "18";
			} catch (SQLException e) {
				e.printStackTrace();
				return "20";
			}
	}
	//修改昵称
	public static String modifynickname(String myPhone,String nickname){
		String sql = "update `user` set nickname = '"+nickname+"' where myPhone = '"+myPhone+"'";
			try {
				conn.createStatement().executeUpdate(sql);
				return "21";
			} catch (SQLException e) {
				e.printStackTrace();
				return "22";
			}
	}
	//完成订单
	public static String finishOrder(String myPhone,String iD_Order){
		String sql = "update `order` set state = true where numberPhoneOther = '"+myPhone+"' and iD_Order = '"+iD_Order+"'";
			try {
				conn.createStatement().executeUpdate(sql);
				return "23";
			} catch (SQLException e) {
				e.printStackTrace();
				return "24";
			}
	}
	//上架商品
	public static String PutonShelves(String myPhone,Good good){
		String sql = "insert into `good` values(?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, good.getGood_id());
			pstat.setString(2, good.getTitle());
			pstat.setDouble(3, good.getPrice());
			pstat.setString(4, myPhone);
			pstat.setString(5, good.getClassify());
			pstat.setString(6, good.getTypel());
			pstat.setString(7, good.getAddress());
			pstat.setString(8, good.getPic_location());
			pstat.setInt(9, good.getMaxTime());
			pstat.setString(10, good.getContact());
			pstat.setBoolean(11, true);
			pstat.executeUpdate();
			return "4";
		} catch (SQLException e) {
			e.printStackTrace();
			return "5";
		}	
	}
	//售出商品
	public static String sellShelves(String myPhone,String good_id){
		String sql = "update `good` set doo = false where good_id = '"+good_id+"'";
		
			try {
				conn.createStatement().executeUpdate(sql);
				return "6";
			} catch (SQLException e) {
				e.printStackTrace();
				return "7";
			}
	}
	//下架商品
	public static String PulloffShelves(String myPhone,String good_id){
		String sql = "delete from `good` where good_id = '"+good_id+"'";
			try {
				conn.createStatement().executeUpdate(sql);
				return "6";
			} catch (SQLException e) {
				e.printStackTrace();
				return "7";
			}
	}
	//买入或借入商品
	public static String buyOrBorrowGood(Order order){
			String re = sellShelves(order.getNumberPhoneOther(), order.getGood().getGood_id());
			boolean re1 = addOrder(order);
			if (re.equals("6")|re1==true) {
				return order.getiD_Order();
			}else{
				return null;
			}
	}
	//根据手机号搜索商品
	public static List<Good> selectGoodByPhone(String myPhone){
		List<Good> list = new ArrayList<Good>();
		String sql = "select * from `good` where myPhone = '"+myPhone+"' and doo = true ";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while(rs.next()){
				Good good = new Good();
				good.setGood_id(rs.getString("good_id"));
				good.setTitle(rs.getString("title"));
				good.setPrice(rs.getDouble("price"));
				good.setMyPhone(rs.getString("myPhone"));
				good.setClassify(rs.getString("classify"));
				good.setTypel(rs.getString("typel"));
				good.setAddress(rs.getString("address"));
				good.setPic_location(rs.getString("pic_location"));
				good.setMaxTime(rs.getInt("maxTime"));
				good.setContact(rs.getString("contact"));
				list.add(good);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//搜索个人信息
	public static User selectUser(String myPhone){
		String sql = "select * from `user` where myPhone = '"+myPhone+"'";
		User user = null;
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) {
				user = new User();
				user.setMyPhone(myPhone);
				user.setBalance(rs.getDouble("balance"));
				user.setNickname(rs.getString("nickname"));
				user.setCreditScore(rs.getInt("creditScore"));
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//添加订单信息
	public static boolean addOrder(Order order){
		String sql = "insert into `order` values(?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, order.getiD_Order());
			pstat.setString(2, order.getTypel());
			pstat.setBoolean(3, false);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp newdate = Timestamp.valueOf(sdf.format(order.getTradeTime()));//把时间转换
			pstat.setTimestamp(4, newdate);  
			pstat.setInt(5, order.getUseTime());
			pstat.setString(6, order.getNumberPhoneOther());
			pstat.setString(7, order.getMyPhone());
			pstat.setString(8, order.getGood().getGood_id());
			pstat.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	//搜索订单信息
	public static List<Order> selectOrder(String myPhone){
		List<Order> list = new ArrayList<Order>();
		String sql = "select * from `order` where myPhone = '"+myPhone+"'or numberPhoneOther = '"+myPhone+"'";
	    try {
			ResultSet rs1 = conn.createStatement().executeQuery(sql);
			while(rs1.next()){
				Order order = new Order();
				Good good = new Good();
				sql = "select * from `Good` where good_id = '"+rs1.getString("good_id")+"'";
				ResultSet rs2 = conn.createStatement().executeQuery(sql);
				if (rs2.next()) {
					good.setAddress(rs2.getString("address"));
					good.setGood_id(rs2.getString("good_id"));
					good.setTitle(rs2.getString("title"));
					good.setPrice(rs2.getDouble("price"));
					good.setMyPhone(rs2.getString("myPhone"));
					good.setClassify(rs2.getString("classify"));
					good.setContact(rs2.getString("contact"));
					good.setTypel(rs2.getString("typel"));
					good.setMaxTime(rs2.getInt("maxTime"));
					good.setPic_location(rs2.getString("pic_location"));
					order.setGood(good);
					order.setiD_Order(rs1.getString("iD_Order"));
					order.setMyPhone(rs1.getString("myPhone"));
					order.setNumberPhoneOther(rs1.getString("numberPhoneOther"));
					order.setState(rs1.getBoolean("state"));
					order.setTypel(rs1.getString("typel"));
					Timestamp ts = rs1.getTimestamp("tradeTime");
					Date date = new Date(ts.getTime());
					order.setTradeTime(date);
					order.setUseTime(rs1.getInt("useTime"));
					list.add(order);
				}
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//根据单个商品id搜索商品
	public static Good selectGood(String good_id){
		String sql = "select * from `good` where good_id = '"+good_id+"' and doo = true ";
		Good good = null; 
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) {
				good = new Good();
				good.setGood_id(good_id);
				good.setTitle(rs.getString("title"));
				good.setPrice(rs.getDouble("price"));
				good.setMyPhone(rs.getString("myPhone"));
				good.setClassify(rs.getString("classify"));
				good.setTypel(rs.getString("typel"));
				good.setAddress(rs.getString("address"));
				good.setPic_location(rs.getString("pic_location"));
				good.setMaxTime(rs.getInt("maxTime"));
				good.setContact(rs.getString("contact"));
			}
			return good;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return good;
		}
	}
	//搜索全部产品
	public static List<Good> selectAll(){
		List<Good> list = new ArrayList<>();
		String sql = "select * from `good` where doo = true ";
		Good good = null; 
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				good = new Good();
				good.setGood_id(rs.getString("good_id"));
				good.setTitle(rs.getString("title"));
				good.setPrice(rs.getDouble("price"));
				good.setMyPhone(rs.getString("myPhone"));
				good.setClassify(rs.getString("classify"));
				good.setTypel(rs.getString("typel"));
				good.setAddress(rs.getString("address"));
				good.setPic_location(rs.getString("pic_location"));
				good.setMaxTime(rs.getInt("maxTime"));
				good.setContact(rs.getString("contact"));
				list.add(good);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return null;
		}
	}
	//根据名称搜索商品
	public static List<Good> selectGoodByTitle(String title){
		List<Good> list = new ArrayList<>();
		String sql = "select * from `good` where title like '%"+title+"%' and doo = true ";
		Good good = null; 
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				good = new Good();
				good.setGood_id(rs.getString("good_id"));
				good.setTitle(rs.getString("title"));
				good.setPrice(rs.getDouble("price"));
				good.setMyPhone(rs.getString("myPhone"));
				good.setClassify(rs.getString("classify"));
				good.setTypel(rs.getString("typel"));
				good.setAddress(rs.getString("address"));
				good.setPic_location(rs.getString("pic_location"));
				good.setMaxTime(rs.getInt("maxTime"));
				good.setContact(rs.getString("contact"));
				list.add(good);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//根据分类搜索商品
	public static List<Good> selectGoodByClassify(String classify){
		List<Good> list = new ArrayList<>();
		String sql = "select * from `good` where classify like '%"+classify+"%' and doo = true ";
		Good good = null; 
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				good = new Good();
				good.setGood_id(rs.getString("good_id"));
				good.setTitle(rs.getString("title"));
				good.setPrice(rs.getDouble("price"));
				good.setMyPhone(rs.getString("myPhone"));
				good.setClassify(rs.getString("classify"));
				good.setTypel(rs.getString("typel"));
				good.setAddress(rs.getString("address"));
				good.setPic_location(rs.getString("pic_location"));
				good.setMaxTime(rs.getInt("maxTime"));
				good.setContact(rs.getString("contact"));
				list.add(good);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
