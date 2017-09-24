package utils;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2Object {
	private Sql2o sql2o;

	private static Sql2Object instance = new Sql2Object();

	private Sql2Object() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.sql2o = new Sql2o("jdbc:mysql://107.180.56.180:3306/wap_sep", "musifyy_dbuser", "musifyy_dbpa$$");
//			this.sql2o = new Sql2o("jdbc:mysql://127.0.0.1:3306/wap_sep", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Sql2Object getInstance() {
		return instance;
	}

	public static Connection open() {
		return getInstance().sql2o.open();
	}
}