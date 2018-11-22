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

		Properties pr = new Properties();
		try {
			/*pr.load(DWLabConnManager.class.getClassLoader()
					.getResourceAsStream("application.properties"));
		
			System.out.print("asdf"+pr.getProperty("RMBOTDB.driverClassName"));*/
			Class.forName("com.mysql.jdbc.Driver");
			
			//String connectionUrl = pr.getProperty("RMBOTDB.connectionUrl") ;
			String connectionUrl = "jdbc:mysql://localhost:3306/mydb";
			
			conn = DriverManager.getConnection(
					connectionUrl,"root", "root");
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
