package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnect 
{
	private String driver = "com.mysql.jdbc.Driver";
	// URLָ��Ҫ���ʵ����ݿ���
	private String url = "jdbc:mysql://localhost:3306/qiluteam?useUnicode=true&characterEncoding=utf8";
	// MySQL����ʱ���û���
	private String user = "root";
	// Java����MySQL����ʱ������
	private String password = "1997927";
	static Connection conn = null;

	public void connect()
	{
			try 
			{
				// ������������
				Class.forName(driver);
				// �������ݿ�
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
