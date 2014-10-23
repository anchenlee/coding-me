package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.AdminUser;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;

/**
 * 后台管理用户登录/登出数据操作类
 * @author HUBEILONG
 *
 */
public class AdminUser_DAO 
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(AdminUser_DAO.class);
	
	
	public static AdminUser checkAdminUser(String username, String password) 
	{
		AdminUser user = null;
		
		String sql = "SELECT * FROM `youhui_v4`.`admin_user` where `username` = ? and `password` = ?;";
		
		Connection conn = null;
		PreparedStatement prestmt = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			prestmt = conn.prepareStatement(sql);
			
			prestmt.setString(1, username);
			prestmt.setString(2, password);
			
			rtst = prestmt.executeQuery();
			if (rtst.next()) 
			{
				user = new AdminUser();
				
				user.setId(rtst.getString("id"));
				user.setUsername(rtst.getString("username"));
				user.setPassword(rtst.getString("password"));
				user.setRealname(rtst.getString("realname"));
				user.setLast_login_time(CodeUtil.getDateTimeStr(rtst.getLong("last_login_time")));
				user.setGroup(rtst.getInt("group"));
			}
		} 
		catch (Exception e) 
		{
			logger.error("AdminUser_DAO.checkAdminUser error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(rtst, prestmt, conn);
		}
		return user;
	}
	
	public static boolean updateUserLoginTime(String username) 
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_v4`.`admin_user` SET `last_login_time` = ? where `username` = ?;";
		
		Connection conn = null;
		PreparedStatement prestmt = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			prestmt = conn.prepareStatement(sql);
			
			prestmt.setString(1, String.valueOf(System.currentTimeMillis()));
			prestmt.setString(2, username);
			
			int i = prestmt.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		} 
		catch (Exception e) 
		{
			logger.error("AdminUser_DAO.updateUserLoginTime error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(rtst, prestmt, conn);
		}
		return flag;
	}
}
