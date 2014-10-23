package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.netting.bean.Action;
import com.netting.bean.Sale;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;


/**
 * 推荐数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_Recom_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_Recom_DAO.class);
	
	private static int pageSize = 10;
	private static String serverHost ="http://b17.cn/";
	
	/**
	 * 获取推荐详细信息
	 * @param tag_id
	 * @return
	 */
	public static Sale getRecomBean(String id)
	{
		Sale bean = null;
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_sale` WHERE `id` = ?;";
		
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
				bean = new Sale();
				
				bean.setId(rs.getString("id"));
				bean.setParentId(rs.getString("parent_id"));
				bean.setDate(rs.getString("date"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
				bean.setDescription(rs.getString("description"));
				bean.setShow(rs.getString("show"));
				bean.setMemo(rs.getString("memo"));
				bean.setRank(rs.getLong("rank"));
				bean.setStartTime(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEndTime(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Recom_DAO.getRecomBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 管理员获取推荐列表
	 * @param page
	 * @param status
	 * @param keyword
	 * @param ispad
	 * @return
	 */
	public static ArrayList<Sale> getRecomList(int page, String keyword, int status)
	{
		ArrayList<Sale> list = new ArrayList<Sale>();
		if (page < 0) 
		{
			page = 1;
		}
		
		long nowTime = System.currentTimeMillis();
		
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_sale` WHERE `parent_id` = '2' AND `state` = 0 ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `title` LIKE '%" + keyword + "%'";
		}
		
		if (status == 1)// 尚未开始
		{
			sql = sql + " AND `start_time` > " + nowTime + " ";
		}
		else if (status == 2)// 正在进行
		{
			sql = sql + " AND `start_time` < " + nowTime + " AND `end_time` > " + nowTime;
		}
		else if (status == 3)// 已结束
		{
			sql = sql + " AND `end_time` < " + nowTime + " ";
		}
		
		if (status == 2)
		{			
			sql = sql + " ORDER BY `rank` ASC limit ?,?;";
		}
		else 
		{
			sql = sql + " ORDER BY `start_time` desc limit ?,?;";
		}
		
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
				Sale bean = new Sale();
				
				bean.setId(rs.getString("id"));
				bean.setParentId(rs.getString("parent_id"));
				bean.setDate(rs.getString("date"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
				bean.setDescription(rs.getString("description"));
				bean.setShow(rs.getString("show"));
				bean.setMemo(rs.getString("memo"));
				bean.setRank(rs.getLong("rank"));
				bean.setStartTime(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEndTime(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				
				Pattern pattern = Pattern.compile(serverHost);
				Matcher m_content = pattern.matcher(bean.getAction().getActionValue());
				String urlKey = "";
				if(m_content.find()) 
				{
					urlKey =bean.getAction().getActionValue().replaceAll(serverHost, "");
				}
				bean.setUrlKey(urlKey);
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Recom_DAO.getRecomList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * 管理员获取推荐列表页面数
	 * @param status
	 * @param keyword
	 * @param ispad
	 * @return
	 */
	public static int getRecomListTotal(String keyword,int status) 
	{
		int totalPage = 0;
		
		long nowTime = System.currentTimeMillis();
		String sql = "SELECT count(*) AS count FROM `youhui_datamining`.`tyh_sale` WHERE `parent_id` = '2' AND `state` = 0 ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `title` LIKE '%" + keyword + "%'";
		}
		if (status == 1)// 尚未开始
		{
			sql = sql + " AND `start_time` > " + nowTime + " ";
		}
		else if (status == 2)// 正在进行
		{
			sql = sql + " AND `start_time` < " + nowTime + " AND `end_time` > " + nowTime;
		}
		else if (status == 3)// 已结束
		{
			sql = sql + " AND `end_time` < " + nowTime + " ";
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
			logger.error("Admin_Recom_DAO.getRecomListTotal error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return totalPage;
	}

	/**
	 * 删除推荐(更改状态)
	 * @return
	 */
	public static boolean delRecom(String id)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`tyh_sale` SET `state`=1 WHERE `id` = '" + id + "';";
		
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
			logger.error("Admin_Recom_DAO.delRecom error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 更新推荐
	 * @param tag
	 * @return
	 */
	public static boolean updateRecom(Sale bean)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`tyh_sale` SET `title`=?,`rank`=?,`date`=?,`icon`=?,`description`=?, " +
				" `start_time`=?,`end_time`=?,`action_type`=?,`action_value`=?,`timestamp`=?,`memo`=?,`show`=? " +
				" WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getTitle());
			ps.setLong(2, bean.getRank());
			ps.setString(3, bean.getDate());
			ps.setString(4, bean.getIcon());
			ps.setString(5, bean.getDescription());
			ps.setLong(6, Long.parseLong(bean.getStartTime()));
			ps.setLong(7, Long.parseLong(bean.getEndTime()));
			ps.setString(8, bean.getAction().getActionType());
			ps.setString(9, bean.getAction().getActionValue());
			ps.setLong(10, System.currentTimeMillis());
			ps.setString(11, bean.getMemo());
			ps.setString(12, bean.getShow());
			ps.setString(13, bean.getId());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Recom_DAO.updateRecom error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 新增推荐
	 * @param tag
	 * @return
	 */
	public static Sale addRecom(Sale bean)
	{
		String sql1 = "SELECT max(rank) AS mrank FROM `youhui_datamining`.`tyh_sale` WHERE `parent_id` = '2';";
		
		String sql2 = "INSERT INTO `youhui_datamining`.`tyh_sale`(`parent_id`,`date`,`title`,`icon`,`description`," +
				" `rank`,`start_time`,`end_time`,`action_type`,`action_value`,`timestamp`,`state`,`memo`,`id`,`show`) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			
			int rank = 0;
			if (rs.next())
			{
				rank = rs.getInt("mrank");
			}
			rank ++;
			
			ps = conn.prepareStatement(sql2);
			
			ps.setString(1, bean.getParentId());
			ps.setString(2, bean.getDate());
			ps.setString(3, bean.getTitle());
			ps.setString(4, bean.getIcon());
			ps.setString(5, bean.getDescription());
			ps.setLong(6, rank);
			ps.setLong(7, Long.parseLong(bean.getStartTime()));
			ps.setLong(8, Long.parseLong(bean.getEndTime()));
			ps.setString(9, bean.getAction().getActionType());
			ps.setString(10, bean.getAction().getActionValue());
			ps.setLong(11, System.currentTimeMillis());
			ps.setInt(12, 0);
			ps.setString(13, bean.getMemo());
			ps.setString(14, bean.getId());
			ps.setString(15, bean.getShow());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				bean.setRank(rank);
			}
			else
			{
				return null;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Recom_DAO.addRecom error:", e);
			return null;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 获取推荐表的下一个ID
	 * @return
	 */
	public static String getRecomNextID()
	{
		String id = null;
		
		String sql = "SELECT max(id) AS maxid FROM `youhui_datamining`.`tyh_sale`;";
		
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
			logger.error("Admin_Recom_DAO.getRecomNextID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return id;
	}
	
	/**
	 * 获取前一个推荐
	 * @return
	 */
	public static Sale getPreSale(String state,String parent_id,String position)
	{
		Sale bean = null;

		long nowTime = System.currentTimeMillis();
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_sale` WHERE `state` =?  and `parent_id`=? and `rank` > ? ";
		sql = sql + " AND `start_time` < " + nowTime + " AND `end_time` > " + nowTime;
		sql = sql +" order by `rank` asc limit 1;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, state);
			ps.setString(2, parent_id);
			ps.setString(3, position);

			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new Sale();
				
				bean.setId(rs.getString("id"));
				bean.setParentId(rs.getString("parent_id"));
				bean.setDate(rs.getString("date"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
				bean.setDescription(rs.getString("description"));
				bean.setShow(rs.getString("show"));
				bean.setMemo(rs.getString("memo"));
				bean.setRank(rs.getLong("rank"));
				bean.setStartTime(rs.getString("start_time"));
				bean.setEndTime(rs.getString("end_time"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				
				Pattern pattern = Pattern.compile(serverHost);
				Matcher m_content = pattern.matcher(bean.getAction().getActionValue());
				String urlKey = "";
				if(m_content.find()) 
				{
					urlKey =bean.getAction().getActionValue().replaceAll(serverHost, "");
				}
				bean.setUrlKey(urlKey);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Recom_DAO.getPreSale error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 获取后一个广告
	 *  @param status
	 * @param platform
	 * @return
	 */
	public static Sale getNextSale(String state,String parent_id,String position)
	{
		Sale bean = null;
		
		long nowTime = System.currentTimeMillis();
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_sale` WHERE `state` =?  and `parent_id`=?    and  `rank` <? ";
		sql = sql + " AND `start_time` < " + nowTime + " AND `end_time` > " + nowTime;
		sql = sql +" order by `rank` desc limit 1;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, state);
			ps.setString(2, parent_id);
			ps.setString(3, position);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new Sale();
				
				bean.setId(rs.getString("id"));
				bean.setParentId(rs.getString("parent_id"));
				bean.setDate(rs.getString("date"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
				bean.setDescription(rs.getString("description"));
				bean.setShow(rs.getString("show"));
				bean.setMemo(rs.getString("memo"));
				bean.setRank(rs.getLong("rank"));
				bean.setStartTime(rs.getString("start_time"));
				bean.setEndTime(rs.getString("end_time"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				
				Pattern pattern = Pattern.compile(serverHost);
				Matcher m_content = pattern.matcher(bean.getAction().getActionValue());
				String urlKey = "";
				if(m_content.find()) 
				{
					urlKey =bean.getAction().getActionValue().replaceAll(serverHost, "");
				}
				bean.setUrlKey(urlKey);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Recom_DAO.getNextSale error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 获取最大的rank
	 * @param status
	 * @param platform
	 * @return
	 */
	public static int getMaxRank(String status,String platform)
	{
		int maxRank = 0;
		
		String sql = "SELECT max(rank) AS maxid FROM `youhui_datamining`.`tyh_sale` ;";
		
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
				maxRank = rs.getInt("maxid")+1;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Recom_DAO.getMaxRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return maxRank;
	}
	
	/**
	 * 获取最小的rank
	 * @param status
	 * @param platform
	 * @return
	 */
	public static int getMinRank(String status,String platform)
	{
		int minRank = 0;
		
		
		String sql = "SELECT min(rank) AS minid FROM `youhui_datamining`.`tyh_sale`;";
		
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
				minRank = rs.getInt("minid")-1;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Recom_DAO.getMinRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return minRank;
	}
	
	public static boolean updateRank(String id,long rank)
	{
		boolean flag = false;
		
		String sql = "update `youhui_datamining`.`tyh_sale` set `rank` =?  WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, rank);
			ps.setString(2, id);
			
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_Recom_DAO.updateRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
}
