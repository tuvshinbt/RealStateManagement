package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2Object {
	private Sql2o sql2o;

	private static Sql2Object instance = new Sql2Object();

	private Sql2Object() {
		// this.sql2o = new Sql2o("jdbc:mysql://107.180.56.180:3636/wap_sep",
		// "musifyy_dbuser", "musifyy_dbpa$$");

		try {
			Context initCtx = new InitialContext();
			Context envCtx = null;
			// TOMCAT
			envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envCtx.lookup("jdbc/realStateMgmt");
			this.sql2o = new Sql2o(dataSource);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		// jdbc/realStateMgmt

		// try {
		// Class.forName("com.mysql.jdbc.Driver");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// this.sql2o = new Sql2o("jdbc:mysql://localhost:3306/ohrt", "root",
		// "pass");
	}

	public static Sql2Object getInstance() {
		return instance;
	}

	public static Connection open() {
		return getInstance().sql2o.open();
	}
}