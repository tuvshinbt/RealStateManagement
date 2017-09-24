package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection instance = new DBConnection();
	private Connection connection;

	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				connection = DriverManager.getConnection("jdbc:mysql://107.180.56.180:3306/wap_sep", "musifyy_dbuser",
						"musifyy_dbpa$$");
			} catch (SQLException e) {
				System.err.println("Connection Failed! Check output console");
				e.printStackTrace();
				return;
			}

			if (connection != null) {
				System.out.println("You made it, take control your database now!");
			} else {
				System.err.println("Failed to make connection!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DBConnection getInstance() {
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

}
