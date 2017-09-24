package utils;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2Object {
	private Sql2o sql2o;

	private static Sql2Object instance = new Sql2Object();

	private Sql2Object() {
		this.sql2o = new Sql2o("jdbc:mysql://107.180.56.180:3636/wap_sep", "musifyy_dbuser", "musifyy_dbpa$$");
	}

	public static Sql2Object getInstance() {
		return instance;
	}

	public static Connection open() {
		return getInstance().sql2o.open();
	}
}