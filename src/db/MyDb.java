package db;
import java.sql.*;
public class MyDb {
	private static Connection conn=null;
	public static Connection getConn() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
//		String url="jdbc:mysql://localhost:3306/sinograindb?characterEncoding=utf-8";
		String url="jdbc:mysql://192.168.31.5:3306/sinograindb?characterEncoding=utf-8";
		String user="root";
		String password="8326179";
		conn=DriverManager.getConnection(url, user, password);
		return conn;
	}
}
