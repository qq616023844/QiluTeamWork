package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnect 
{
	private String driver = "com.mysql.jdbc.Driver";
	// URL指向要访问的数据库名
	private String url = "jdbc:mysql://localhost:3306/qiluteam?useUnicode=true&characterEncoding=utf8";
	// MySQL配置时的用户名
	private String user = "root";
	// Java连接MySQL配置时的密码
	private String password = "1997927";
	static Connection conn = null;

	public void connect()
	{
			try 
			{
				// 加载驱动程序
				Class.forName(driver);
				// 连续数据库
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
