package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.netting.bean.Opt_Log;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;


/**
 * 后台管理员操作日志数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_OPT_Log_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_OPT_Log_DAO.class);
	
	public static int pageSize = 20;
	
	/**
	 * 管理员获取操作日志列表
	 * @param page
	 * @param username
	 * @param menu_id
	 * @param opt_type
	 * @param start_opt_time
	 * @param end_opt_time
	 * @param keyword
	 * @return
	 */
	public static ArrayList<Opt_Log> getOptLogList(int page, String username, String menu_id, String opt_type,
			String start_opt_time, String end_opt_time, String keyword)
	{
		ArrayList<Opt_Log> list = new ArrayList<Opt_Log>();
		
		String sql = "SELECT * FROM `youhui_v4`.`opt_log` WHERE 1 = 1";
		if (username != null && !"".equals(username) && !username.equals("all")) 
		{
			sql = sql + " AND `username` = '" + username + "' ";
		}
		if (menu_id != null && !"".equals(menu_id) && !menu_id.equals("all")) 
		{
			sql = sql + " AND `menu_id` = '" + menu_id + "' ";
		}
		if (opt_type != null && !"".equals(opt_type) && !opt_type.equals("all")) 
		{
			sql = sql + " AND `opt_type` = '" + opt_type + "' ";
		}
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `content` LIKE '%" + keyword + "%' ";
		}
		if (start_opt_time != null && !"".equals(start_opt_time) && !start_opt_time.equals("null")) 
		{
			sql = sql + " AND `opt_time` >= '" + start_opt_time + "' ";
		}
		if (end_opt_time != null && !"".equals(end_opt_time) && !end_opt_time.equals("null")) 
		{
			sql = sql + " AND `opt_time` <= '" + end_opt_time + "' ";
		}
		sql = sql + " ORDER BY `opt_time` DESC limit ?,?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, pageSize * (page - 1));
			ps.setInt(2, pageSize);	
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				Opt_Log bean = new Opt_Log();
				
				bean.setId(rs.getString("id"));
				bean.setUsername(rs.getString("username"));
				bean.setMenu_id(rs.getString("menu_id"));
				bean.setContent(rs.getString("content"));
				bean.setOpt_time(CodeUtil.getDateTimeStr(rs.getLong("opt_time")));
				bean.setOpt_type(rs.getString("opt_type"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_OPT_Log_DAO.getOptLogList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	/**
	 * 管理员获取列表页面数
	 * @param keyword
	 * @return
	 */
	public static int getOptLogListTotal(String username, String menu_id, String opt_type,
			String start_opt_time, String end_opt_time, String keyword) 
	{
		int count = 0;
		
		String sql = "SELECT count(`id`) AS count FROM `youhui_v4`.`opt_log` WHERE 1 = 1 ";
		if (username != null && !"".equals(username) && !username.equals("all")) 
		{
			sql = sql + " AND `username` = '" + username + "' ";
		}
		if (menu_id != null && !"".equals(menu_id) && !menu_id.equals("all")) 
		{
			sql = sql + " AND `menu_id` = '" + menu_id + "' ";
		}
		if (opt_type != null && !"".equals(opt_type) && !opt_type.equals("all")) 
		{
			sql = sql + " AND `opt_type` = '" + opt_type + "' ";
		}
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `content` LIKE '%" + keyword + "%' ";
		}
		if (start_opt_time != null && !"".equals(start_opt_time) && !start_opt_time.equals("null")) 
		{
			sql = sql + " AND `opt_time` >= '" + start_opt_time + "' ";
		}
		if (end_opt_time != null && !"".equals(end_opt_time) && !end_opt_time.equals("null")) 
		{
			sql = sql + " AND `opt_time` <= '" + end_opt_time + "' ";
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			rtst = ps.executeQuery();
			if (rtst.next())
			{
				count = rtst.getInt("count");
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_OPT_Log_DAO.getOptLogListTotal error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return count;
	}

	
	/**
	 * 新增操作日志
	 * @param tag
	 * @return
	 */
	public static boolean addOpt_Log(String username, String menu_id, String content, String opt_type)
	{
		String sql = "INSERT INTO `youhui_v4`.`opt_log`(`username`,`menu_id`,`content`,`opt_time`,`opt_type`)  VALUES (?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, menu_id);
			ps.setString(3, content);
			ps.setLong(4, System.currentTimeMillis());
			ps.setString(5, opt_type);
			//ps.setString(6, params);
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_OPT_Log_DAO.addOpt_Log error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
	}

}
