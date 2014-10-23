package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.Action;
import com.netting.bean.PushMessage;
import com.netting.conf.SysConf;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;


/**
 * 推送消息数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_PushMessage_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_PushMessage_DAO.class);
	
	private static int pageSize = 10;
	
	/**
	 * 获取消息实体
	 * @param tag_id
	 * @return
	 */
	public static PushMessage getPushMessageBean(String pid)
	{
		PushMessage bean = null;
		String sql = "SELECT * FROM `youhui_pushmessage`.`tyh_pushmessage` WHERE `pid` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, pid);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new PushMessage();
				
				bean.setId(rs.getString("id"));
				bean.setpId(rs.getString("pid"));
				bean.setTitle(rs.getString("title"));
				bean.setContent(rs.getString("content"));
				bean.setClientVersion(rs.getString("client_version"));
				bean.setCode(rs.getInt("code"));
				bean.setStarttime(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEndtime(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setFormula(rs.getString("formula"));
				bean.setQuery(rs.getString("query"));
				
				String platform = rs.getString("platform");
				if (platform != null && !platform.equals(""))
				{
					if (platform.indexOf("iphone") > -1)
					{
						bean.setPlatform("iphone");
					}
					else
					{
						bean.setPlatform(platform);
					}
				}
				
				bean.setMode(rs.getInt("mode"));
				bean.setRange(rs.getInt("range"));
				bean.setIcon(rs.getString("icon"));
				bean.setAction(new Action(rs.getString("action_type"),rs.getString("action_value")));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_PushMessage_DAO.getPushMessageBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 管理员获取消息列表
	 * @param page
	 * @param keyword
	 * @param range 
	 * @param platform 
	 * @return
	 */
	public static ArrayList<PushMessage> getPushMessageList(int page, String keyword, String platform)
	{
		ArrayList<PushMessage> list = new ArrayList<PushMessage>();
		
		String sql = "SELECT * FROM `youhui_pushmessage`.`tyh_pushmessage` WHERE (`status` = '0' or status='2') ";
		if (platform != null && !"".equals(platform) && !platform.equals("all")) 
		{
			sql = sql + " AND `platform` LIKE '%" + platform + "%'";
		}
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `title` LIKE '%" + keyword + "%'";
		}
		sql = sql + " ORDER BY `pid` DESC limit ?,?;";
		
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
				PushMessage bean = new PushMessage();
				
				bean.setId(rs.getString("id"));
				bean.setpId(rs.getString("pid"));
				bean.setTitle(rs.getString("title"));
				bean.setContent(rs.getString("content"));
				bean.setClientVersion(rs.getString("client_version"));
				bean.setCode(rs.getInt("code"));
				bean.setStarttime(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEndtime(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setFormula(rs.getString("formula"));
				bean.setQuery(rs.getString("query"));
				
				String platform_query = rs.getString("platform");
//				if (platform_query != null && !platform_query.equals(""))
//				{
//					if (platform_query.indexOf("iphone") > -1)
//					{
//						bean.setPlatform("iphone");
//					}
//					else
//					{
//						bean.setPlatform(platform_query);
//					}
//				}
				bean.setPlatform(platform_query);
				bean.setMode(rs.getInt("mode"));
				bean.setRange(rs.getInt("range"));
				bean.setIcon(rs.getString("icon"));
				bean.setAction(new Action(rs.getString("action_type"),rs.getString("action_value")));
				
				Pattern pattern = Pattern.compile(SysConf.hostUrl);
				Matcher m_content = pattern.matcher(bean.getAction().getActionValue());
				String urlKey = "";
				if(m_content.find()) 
				{
					urlKey =bean.getAction().getActionValue().replaceAll(SysConf.hostUrl, "");
				}
				bean.setUrlKey(urlKey);
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_PushMessage_DAO.getPushMessageList error:", e);
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
	 * @param range
	 * @param platform
	 * @return
	 */
	public static int getPushMessageListTotal(String keyword, String platform) 
	{
		int totalPage = 0;
		
		String sql = "SELECT count(`id`) AS count FROM `youhui_pushmessage`.`tyh_pushmessage` WHERE (`status` = '0' or status='2') ";
		if (platform != null && !"".equals(platform) && !platform.equals("all")) 
		{
			sql = sql + " AND `platform` LIKE '%" + platform + "%'";
		}
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `title` LIKE '%" + keyword + "%'";
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
			logger.error("Admin_PushMessage_DAO.getPushMessageListTotal error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return totalPage;
	}

	/**
	 * 删除(更改状态)
	 * @return
	 */
	public static boolean deletePushMessage(String pid)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_pushmessage`.`tyh_pushmessage` SET `status` = '1' WHERE `pid` = '" + pid + "';";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_PushMessage_DAO.deletePushMessage error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 更新
	 * @return
	 */
	public static boolean updatePushMessage(PushMessage bean)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_pushmessage`.`tyh_pushmessage` SET `code`=?,`content`=?,`start_time`=?,`end_time`=?,`mode`=?, " +
				" `range`=?,`platform`=?,`client_version`=?,`formula`=?,`query`=?,`action_type`=?,`action_value`=?,`title`=?,`icon`=? " +
				" WHERE `pid` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, bean.getCode());
			ps.setString(2, bean.getContent());
			ps.setString(3, bean.getStarttime());
			ps.setString(4, bean.getEndtime());
			ps.setInt(5, bean.getMode());
			ps.setInt(6, bean.getRange());
			ps.setString(7, bean.getPlatform());
			ps.setString(8, bean.getClientVersion());
			ps.setString(9, bean.getFormula());
			ps.setString(10, bean.getQuery());
			ps.setString(11, bean.getAction().getActionType());
			ps.setString(12, bean.getAction().getActionValue());
			ps.setString(13, bean.getTitle());
			ps.setString(14, bean.getIcon());
			ps.setString(15, bean.getpId());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_PushMessage_DAO.updatePushMessage error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 获取消息表的下一个ID
	 * @return
	 */
	public static String getMessageNextID()
	{
		String id = null;
		
		String sql = "SELECT max(id) AS maxid FROM `youhui_pushmessage`.`tyh_pushmessage`;";
		
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
			logger.error("Admin_PushMessage_DAO.getMessageNextID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return id;
	}
	
	/**
	 * 新增
	 * @return
	 */
	public static boolean addPushMessage(PushMessage bean)
	{
		boolean flag = false;
		
		String sql = "INSERT INTO `youhui_pushmessage`.`tyh_pushmessage`(`title`,`code`,`content`,`start_time`,`end_time`,`mode`,`range`,`platform`,`client_version`,`formula`,`query`,`action_type`,`action_value`,`pid`,`icon`,`status`,`id`) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getTitle());
			ps.setInt(2, bean.getCode());
			ps.setString(3, bean.getContent());
			ps.setString(4, bean.getStarttime());
			ps.setString(5, bean.getEndtime());
			ps.setInt(6, bean.getMode());
			ps.setInt(7, bean.getRange());
			ps.setString(8, bean.getPlatform());
			ps.setString(9, bean.getClientVersion());
			ps.setString(10, bean.getFormula());
			ps.setString(11, bean.getQuery());
			ps.setString(12, bean.getAction().getActionType());
			ps.setString(13, bean.getAction().getActionValue());
			ps.setString(14, bean.getpId());
			ps.setString(15, bean.getIcon());
			ps.setString(16, "0");
			ps.setString(17, bean.getId());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_PushMessage_DAO.addPushMessage error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return flag;
	}
	
	/**
	 * 插入消息发送记录表
	 * @param pid
	 * @param title
	 * @param devtoken
	 * @param platform
	 * @param state
	 * @return
	 */
	public static boolean addPushState(String pid, String title, String devtoken, String platform, int state)
	{  
		boolean flag = false;
		
		String sql = "INSERT INTO `youhui_pushmessage`.`tyh_push_state`(`push_pid` ,`push_title` ,`devtoken` ,`state`, `timestamp`,`platform`) VALUES (?,?,?,?,?,?);";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, pid);
			ps.setString(2, title);
			ps.setString(3, devtoken);
			ps.setInt(4, state);
			ps.setLong(5, System.currentTimeMillis());
			ps.setString(6, platform);
			
			int i = ps.executeUpdate();
			if(i > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_PushMessage_DAO.addPushState error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return flag;
	}
	
	public static boolean addUidToMsg(String pid, Map<String, String> msgvalue, String startTime)
	{  
		boolean flag = false;
		
		if(startTime == null || startTime.equals("") || startTime.equals("0")) 
		{
			startTime = String.valueOf(new Date().getTime());
		}
		
		String sql = "INSERT INTO `youhui_pushmessage`.`tyh_uid_pmsg`( `uid` ,`pid` ,`content_value`,`start_time`,`state`) VALUES (?,?,?,?,?);";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			
			for(Map.Entry<String, String> m : msgvalue.entrySet())
			{
				ps.setString(1, m.getKey());
				ps.setString(2, pid);
				ps.setString(3, m.getValue());
				ps.setString(4, startTime);
				ps.setString(5, "0");
				
				ps.addBatch();
			}
			conn.setAutoCommit(true);
			
			int i[] = ps.executeBatch();
			if(i[0] > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_PushMessage_DAO.addUidToMsg error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return flag;
	}
}
