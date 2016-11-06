package config;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class ConnectionFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/estudando";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
