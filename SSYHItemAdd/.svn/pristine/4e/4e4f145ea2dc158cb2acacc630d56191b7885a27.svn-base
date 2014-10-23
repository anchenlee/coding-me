package cn.youhui.platform.db;

import java.sql.*;

import cn.youhui.platform.config.Config;


public class SQL {

	private static SQL instance = new SQL();

	public static SQL getInstance() {
		if (instance == null)
			instance = new SQL();
		return instance;
	}

	private SQL() {
	}


	public Connection getConnection() {
		Connection trueConn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			trueConn = DriverManager.getConnection(Config.getSQLUrl(), Config.getSQLUser(), Config.getSQLPWD());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return trueConn;
	}

	public void release(Statement statement, Connection conn) {
		try {
			if (conn != null) conn.close();
			if (statement != null) statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		statement = null;
	}

}
