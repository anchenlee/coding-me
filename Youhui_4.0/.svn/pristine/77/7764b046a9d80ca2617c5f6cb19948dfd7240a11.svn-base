package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.netting.bean.Activity_Bean;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;


/**
 * 活动数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_Activity_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_Activity_DAO.class);
	
	private static int pageSize = 10;
	
	/**
	 * 获取活动详细信息
	 * @param tag_id
	 * @return
	 */
	public static Activity_Bean getActivityBean(String id)
	{
		Activity_Bean bean = null;
		String sql = "SELECT * FROM `youhui_cn_fanli`.`tyh_activity` WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new Activity_Bean();
				
				bean.setId(rs.getString("id"));
				bean.setDescription(rs.getString("description"));
				bean.setName(rs.getString("name"));
				bean.setFrequency(rs.getInt("frequency"));
				bean.setJfbNum(rs.getInt("jfb_num"));
				bean.setKey(rs.getString("key"));
				bean.setRule(rs.getString("rule"));
				bean.setStartTime(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEndTime(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setTimestamp(CodeUtil.getDateTimeStr(rs.getLong("timestamp")));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Activity_DAO.getActivityBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 管理员获取活动列表
	 * @param page 页码
	 * @param keyword 关键字查询
	 * @return
	 */
	public static ArrayList<Activity_Bean> getActivityList(int page, String keyword)
	{
		ArrayList<Activity_Bean> list = new ArrayList<Activity_Bean>();
		if (page < 0) 
		{
			page = 1;
		}
		
		String sql = "SELECT * FROM `youhui_cn_fanli`.`tyh_activity` ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " WHERE `name` LIKE '%" + keyword + "%'";
		}
		sql = sql + " ORDER BY `timestamp` DESC limit ?,?;";
		
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
				Activity_Bean bean = new Activity_Bean();
				
				bean.setId(rs.getString("id"));
				bean.setDescription(rs.getString("description"));
				bean.setName(rs.getString("name"));
				bean.setFrequency(rs.getInt("frequency"));
				bean.setJfbNum(rs.getInt("jfb_num"));
				bean.setKey(rs.getString("key"));
				bean.setRule(rs.getString("rule"));
				bean.setStartTime(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEndTime(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setTimestamp(CodeUtil.getDateTimeStr(rs.getLong("timestamp")));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Activity_DAO.getActivityList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * 管理员获取活动列表页面数
	 * @param keyword
	 * @return
	 */
	public static int getActivityListTotal(String keyword) 
	{
		int totalPage = 0;
		
		String sql = "SELECT count(`id`) AS count FROM `youhui_cn_fanli`.`tyh_activity` ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " WHERE `name` LIKE '%" + keyword + "%'";
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rtst = ps.executeQuery();
			int count = 0;
			if (rtst.next())
			{
				count = rtst.getInt("count");
			}
			
			totalPage = count / pageSize;
			int ys = count % pageSize;
			if(ys > 0)
			{
				totalPage++;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Activity_DAO.getActivityListTotal error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return totalPage;
	}

	/**
	 * 更新活动
	 * @param tag
	 * @return
	 */
	public static boolean updateActivity(Activity_Bean bean)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_cn_fanli`.`tyh_activity` SET `name`=?,`description`=?,`frequency`=?, " +
					" `jfb_num`=?,`rule`=?,`start_time`=?,`end_time`=?,`timestamp`=? " +
					" WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getDescription());
			ps.setInt(3, bean.getFrequency());
			ps.setInt(4, bean.getJfbNum());
			ps.setString(5, bean.getRule());
			ps.setString(6, bean.getStartTime());
			ps.setString(7, bean.getEndTime());
			ps.setString(8, bean.getTimestamp());
			ps.setString(9, bean.getId());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Activity_DAO.updateActivity error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 新增活动
	 * @return
	 */
	public static boolean addActivity(Activity_Bean bean)
	{
		boolean flag = false;
		
		String sql = "INSERT INTO `youhui_cn_fanli`.`tyh_activity`(`name`,`description`,`frequency`,`jfb_num`,`rule`, " +
						" `start_time`,`end_time`,`timestamp`,`key`,`id`) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getDescription());
			ps.setInt(3, bean.getFrequency());
			ps.setInt(4, bean.getJfbNum());
			ps.setString(5, bean.getRule());
			ps.setString(6, bean.getStartTime());
			ps.setString(7, bean.getEndTime());
			ps.setString(8, bean.getTimestamp());
			ps.setString(9, bean.getKey());
			ps.setString(10, bean.getId());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Activity_DAO.addActivity error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 获取活动表的下一个ID
	 * @return
	 */
	public static String getActivityNextID()
	{
		String id = null;
		
		String sql = "SELECT max(`id`) AS maxid FROM `youhui_cn_fanli`.`tyh_activity`;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next())
			{
				int maxid = rs.getInt("maxid");
				id = String.valueOf(maxid + 1);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Gift_DAO.getGiftNextID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return id;
	}
	
	public static String getKey()
	{
		String key = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer sb = new StringBuffer();
		Random rm = new Random();
		int i = 8;
		while(i>0)
		{
			i--;
			int index = (int)(rm.nextFloat()*key.length());
			index = Math.min(index, key.length()-1);
			index = Math.max(0, index);
			sb.append(key.charAt(index));
		}
		return sb.toString();
	}
	
}
