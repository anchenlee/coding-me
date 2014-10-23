package cn.suishou.some.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import cn.suishou.some.config.Config;


public class SQL {
	
	static SQL instance = new SQL();

	public static SQL getInstance() {
		if (instance == null)
			instance = new SQL();
		return instance;
	}

	private SQL() {}

	public Connection getConnection() {
		Connection trueConn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			trueConn = DriverManager.getConnection(Config.getMysqlUrl(), Config.getMysqlUser(), Config.getMysqlPass());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return trueConn;
	}

	public boolean release(Statement state, Connection conn) {
		try {
			if(state != null){
				state.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
		}
		conn = null;
		return true;
	}

}
