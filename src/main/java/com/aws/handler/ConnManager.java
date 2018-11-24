package com.aws.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnManager {

	static String JDBC_DRIVER = null;
	static String DB_URL = null;
	static String USER = null;
	static String PASS = null;
	private static Connection conn = null;

	public static Connection createConnection() throws ClassNotFoundException,
			SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connectionUrl = "jdbc:mysql://score.cfracnybfkym.us-east-1.rds.amazonaws.com:3306/statistics";
			conn = DriverManager.getConnection(
					connectionUrl,"vikash", "qwerty1234");
			System.out.print("Connection obtained is:"+conn.toString());
		} catch (Exception e) {
			if (conn != null) {
				conn.close();
			}

		}
		return conn;
	}
	
	public static void closeConnection() throws ClassNotFoundException,
			SQLException {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			if (conn != null) {
				conn.close();
			}

		}
		
		
	}
}
