package cn.youhui.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import cn.youhui.common.Config;

/*
 * 数据库连接及释放 ，通过连接TOMCAT 连接池 获取数据库的缓冲，并对数据库进行回收处理。
 * 配置 web.xml 及CONTEXT.XML 来修改连接池的配置信息。
 // */
public class MySQLDBIns {
//	static Logger log = Logger.getLogger(MySQLDBIns.class) ;
	
	static MySQLDBIns instance = new MySQLDBIns();;

	public static MySQLDBIns getInstance() {
		if (instance == null)
			instance = new MySQLDBIns();
		return instance;
	}

	private MySQLDBIns() {}

	public Connection getConnection() {
		return this.getConnectionFromDatabase();
	}
	
	public Connection getReadConnection(){
		Connection trueConn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			trueConn = DriverManager.getConnection(Config.GLB_MYSQL_URL_R, Config.GLB_MYSQL_USER_R, Config.GLB_MYSQL_PWD_R);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return trueConn;
	}

	
	public Connection getRealConnection(){
		return this.getConnectionFromDatabase();
	}

	// 连接数据库
	public Connection getConnectionFromDatabase() {
		
		Connection trueConn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			trueConn = DriverManager.getConnection(Config.GLB_MYSQL_URL, Config.GLB_MYSQL_USER, Config.GLB_MYSQL_PWD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return trueConn;
	}
	
      public Connection getConnection2() {
		
		Connection trueConn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			trueConn = DriverManager.getConnection(Config.GLB_MYSQL_URL_2, Config.GLB_MYSQL_USER_2, Config.GLB_MYSQL_PWD_2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return trueConn;
	}

	public boolean release(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
		return true;
	}

	public boolean release(Statement s, Connection conn) {
		try {
			if(s != null){
				s.close();
				s = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
