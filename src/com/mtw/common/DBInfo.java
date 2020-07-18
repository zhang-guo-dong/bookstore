package com.mtw.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInfo {
	public static String host;
	public static String port;
	public static String sid;
	public static String username;
	public static String password;
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.OracleDriver");
		String url="jdbc:oracle:thin:@"+host+":"+port+":"+sid;
		Connection conn=DriverManager.getConnection(url,username,password);
		return conn;
	}

}
