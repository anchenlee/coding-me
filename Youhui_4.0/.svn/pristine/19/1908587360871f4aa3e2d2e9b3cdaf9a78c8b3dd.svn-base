package com.netting.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;


import com.netting.conf.Config;
import com.netting.conf.SysConf;




/**
 * 数据连接工厂（连接池）
 * @author YAOJIANBO
 * @version 1.0 
 */
public class DataSourceFactory
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(DataSourceFactory.class);
	
	/**
	 * 数据源对象
	 */
	private static DataSource dataSource;
	
	public static Connection getConnection() {
		return getConnectionFromDatabase();
	}
	
	public static Connection getConnectionFromDatabase() {
		
		Connection trueConn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			trueConn = DriverManager.getConnection(Config.GLB_MYSQL_URL, Config.GLB_MYSQL_USER, Config.GLB_MYSQL_PWD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return trueConn;
	}
	
	
	
	
	
	
	
//	public static void init_dataSource()
//	{
//		dataSource = null;
//		dataSource = new DataSource();
//		
//		PoolProperties prop = new PoolProperties();
//		prop.setUrl(SysConf.url);
//		prop.setDriverClassName(SysConf.driverClass);
//		prop.setUsername(SysConf.username);
//		prop.setPassword(SysConf.password);
//		prop.setJmxEnabled(true);
//		prop.setTestWhileIdle(false);
//		prop.setTestOnBorrow(true);
//		prop.setValidationQuery("SELECT 1");
//		prop.setTestOnReturn(false);
//		prop.setValidationInterval(30000);
//		prop.setTimeBetweenEvictionRunsMillis(30000);
//		prop.setMaxActive(20);
//		prop.setInitialSize(15);
//		prop.setMaxWait(10000);
//		prop.setRemoveAbandonedTimeout(500);
//		prop.setMinEvictableIdleTimeMillis(30000);
//		prop.setMaxIdle(15);
//		prop.setMinIdle(10);
//		prop.setLogAbandoned(true);
//		prop.setRemoveAbandoned(false);
//		prop.setFairQueue(true);
//		prop.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" + 
//		"org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
//		
//		dataSource.setPoolProperties(prop);
//	}
	
	/**
	 * 获取数据源对象
	 * @return DataSource
	 */
//	public static DataSource getDataSource()
//	{
//		if (null == dataSource)
//		{
//			init_dataSource();
//			
//			return dataSource;
//		}
//		else 
//		{
//			return dataSource;
//		}
//	}
	
	/**
	 * 获取数据库DB连接
	 * @return
	 * @throws SQLException 
	 */
//	public static Connection getConnection() throws SQLException
//	{
//		return getDataSource().getConnection();
//	}
	
	/**
	 * 关闭DB操作所有已开启的组件
	 * @param rs 结果集
	 * @param st SQL执行载体
	 * @param conn DB连接
	 */
	public static void closeAll(ResultSet rs, Statement st, Connection conn)
	{
		try
		{
			// 关闭结果集
			if (rs != null)
			{
				rs.close();
			}
			// 关闭SQL语句执行载体
			if (st != null)
			{
				st.close();
			}
			// 关闭数据库连接
			if (conn != null)
			{
				conn.close();
			}
		}
		catch (Exception e)
		{
			logger.error("DB closed error：", e);
		}
	}
	
	public static void destory()
	{
		if (null != dataSource)
		{
			dataSource.close();
		}
	}
	
}
