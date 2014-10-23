package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.netting.bean.Gift_Bean;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;


/**
 * 礼物数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_Gift_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_Gift_DAO.class);
	
	private static int pageSize = 10;
	private static String serverHost ="http://b17.cn/";
	
	/**
	 * 获取礼物详细信息
	 * @param tag_id
	 * @return
	 */
	public static Gift_Bean getGiftBean(String id)
	{
		Gift_Bean gift = null;
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_gifts` WHERE `id` = ?;";
		
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
				gift = new Gift_Bean();
				
				gift.setId(rs.getString("id"));
				gift.setTitle(rs.getString("gift_title"));
				gift.setDescription(rs.getString("gift_desc"));
				gift.setUpdatetime(CodeUtil.getDateTimeStr(rs.getLong("update_time")));
				gift.setClickname(rs.getString("clickname"));
				gift.setImg(rs.getString("img"));
				gift.setImgIpad(rs.getString("img_ipad"));
				gift.setActionType(rs.getString("action_type"));
				gift.setActionValue(rs.getString("action_value"));
				gift.setType(rs.getString("type"));
				gift.setStart_time(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				gift.setEnd_time(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				gift.setRank(rs.getInt("rank"));
				gift.setStatus(rs.getInt("status"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Gift_DAO.getGiftBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return gift;
	}
	
	/**
	 * 管理员获取礼物列表
	 * @param page 页码
	 * @param keyword 关键字查询
	 * @param status 礼物状态
	 * @return
	 */
	public static ArrayList<Gift_Bean> getGiftList(int page, String keyword, int status, String timeStatus)
	{
		ArrayList<Gift_Bean> list = new ArrayList<Gift_Bean>();
		if (page < 0) 
		{
			page = 1;
		}
		
		long nowTime = System.currentTimeMillis();
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_gifts` WHERE `status` = ? ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `gift_title` LIKE '%" + keyword + "%'";
		}
		if (timeStatus.equals("1"))// 尚未开始
		{
			sql = sql + " AND `start_time` > " + nowTime + " ";
		}
		else if (timeStatus.equals("2"))// 正在进行
		{
			sql = sql + " AND `start_time` < " + nowTime + " AND `end_time` > " + nowTime;
		}
		else if (timeStatus.equals("3") )// 已结束
		{
			sql = sql + " AND `end_time` < " + nowTime + " ";
		}
		if(status == 1)
		{
			sql = sql + " ORDER BY `rank` ASC  limit ?,?;";
		}
		else 
		{
			sql = sql + " ORDER BY `start_time` DESC,`update_time` DESC  limit ?,?;";
		}
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, status);
			ps.setInt(2, pageSize * (page - 1));
			ps.setInt(3, pageSize);	
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				Gift_Bean gift = new Gift_Bean();
				
				gift.setId(rs.getString("id"));
				gift.setTitle(rs.getString("gift_title"));
				gift.setDescription(rs.getString("gift_desc"));
				gift.setUpdatetime(CodeUtil.getDateTimeStr(rs.getLong("update_time")));
				gift.setClickname(rs.getString("clickname"));
				gift.setImg(rs.getString("img"));
				gift.setImgIpad(rs.getString("img_ipad"));
				gift.setActionType(rs.getString("action_type"));
				gift.setActionValue(rs.getString("action_value"));
				gift.setType(rs.getString("type"));
				gift.setStart_time(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				gift.setEnd_time(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				gift.setRank(rs.getInt("rank"));
				gift.setStatus(rs.getInt("status"));
				String urlKey = "";
				Pattern pattern = Pattern.compile(serverHost);
				Matcher m_content = pattern.matcher(gift.getActionValue());
				if(m_content.find()) 
				{
					urlKey =gift.getActionValue().replaceAll(serverHost, "");
				}
				gift.setUrlKey(urlKey);
				list.add(gift);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Gift_DAO.getGiftList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * 管理员获取礼物列表页面数
	 * @param status
	 * @param keyword
	 * @param ispad
	 * @return
	 */
	public static int getGiftListTotal(String keyword, int status,String timeStatus)
	{
		int totalPage = 0;
		long nowTime = System.currentTimeMillis();
		
		String sql = "SELECT count(*) AS count FROM `youhui_datamining`.`tyh_gifts` WHERE `status` = ? ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `gift_title` LIKE '%" + keyword + "%'";
		}
		if (timeStatus.equals("1"))// 尚未开始
		{
			sql = sql + " AND `start_time` > " + nowTime + " ";
		}
		else if (timeStatus.equals("2"))// 正在进行
		{
			sql = sql + " AND `start_time` < " + nowTime + " AND `end_time` > " + nowTime;
		}
		else if (timeStatus.equals("3") )// 已结束
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
			ps.setInt(1, status);
			
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
			logger.error("Admin_Gift_DAO.getGiftListTotal error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return totalPage;
	}

	/**
	 * 删除礼物(更改状态)
	 * @return
	 */
	public static boolean updateGiftStatus(String id, int status)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`tyh_gifts` SET `status` = " + status + " WHERE `id` = '" + id + "';";
		
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
			logger.error("Admin_Gift_DAO.updateGiftStatus error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * (更改rank)
	 * @return
	 */
	public static boolean updateGiftRank(String id, int rank)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`tyh_gifts` SET `rank` = " + rank + " WHERE `id` = '" + id + "';";
		
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
			logger.error("Admin_Gift_DAO.updateGiftRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 更新礼物
	 * @param tag
	 * @return
	 */
	public static boolean updateGift(Gift_Bean gift)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`tyh_gifts` SET `gift_title`=?,`gift_desc`=?,`clickname`=?,`img`=?,`img_ipad`=?, " +
				" `action_type`=?,`action_value`=?,`type`=?,`start_time`=?,`end_time`=?,`status`=?,`update_time`=? " +
				" WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, gift.getTitle());
			ps.setString(2, gift.getDescription());
			ps.setString(3, gift.getClickname());
			ps.setString(4, gift.getImg());
			ps.setString(5, gift.getImgIpad());
			ps.setString(6, gift.getActionType());
			ps.setString(7, gift.getActionValue());
			ps.setString(8, gift.getType());
			ps.setString(9, gift.getStart_time());
			ps.setString(10, gift.getEnd_time());
			ps.setInt(11, gift.getStatus());
			ps.setString(12, gift.getUpdatetime());
			ps.setString(13, gift.getId());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Gift_DAO.updateGift error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 新增礼物
	 * @param tag
	 * @return
	 */
	public static Gift_Bean addGift(Gift_Bean gift)
	{
		String sql1 = "SELECT min(rank) as mrank FROM `youhui_datamining`.`tyh_gifts`;";
		
		String sql2 = "INSERT INTO `youhui_datamining`.`tyh_gifts`(`gift_title`,`gift_desc`,`clickname`,`img`,`img_ipad`, " +
				" `action_type`,`action_value`,`type`,`start_time`,`end_time`,`status`,`update_time`,`rank`,`id`) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
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
			rank--;
			gift.setRank(rank);
			
			ps = conn.prepareStatement(sql2);
			
			ps.setString(1, gift.getTitle());
			ps.setString(2, gift.getDescription());
			ps.setString(3, gift.getClickname());
			ps.setString(4, gift.getImg());
			ps.setString(5, gift.getImgIpad());
			ps.setString(6, gift.getActionType());
			ps.setString(7, gift.getActionValue());
			ps.setString(8, gift.getType());
			ps.setString(9, gift.getStart_time());
			ps.setString(10, gift.getEnd_time());
			ps.setInt(11, gift.getStatus());
			ps.setLong(12, System.currentTimeMillis());
			ps.setInt(13, gift.getRank());
			ps.setString(14, gift.getId());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				return gift;
			}
			else
			{
				return null;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Gift_DAO.addGift error:", e);
			return null;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
	}
	
	/**
	 * 获取礼物表的下一个ID
	 * @return
	 */
	public static String getGiftNextID()
	{
		String id = null;
		
		String sql = "SELECT max(id) AS maxid FROM `youhui_datamining`.`tyh_gifts`;";
		
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
	
	/**
	 * 获取礼物表的最小一个rank
	 * @return
	 */
	public static int getGiftMinRank()
	{
		int id = 999999999;
		
		String sql = "SELECT min(rank) AS minrank FROM `youhui_datamining`.`tyh_gifts`;";
		
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
				int minrank = rs.getInt("minrank");
				id = minrank - 1;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Gift_DAO.getGiftMinRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return id;
	}
	
	/**
	 * 获取礼物表的最大一个rank
	 * @return
	 */
	public static int getGiftMaxRank()
	{
		int id = 999999999;
		
		String sql = "SELECT max(rank) AS maxrank FROM `youhui_datamining`.`tyh_gifts`;";
		
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
				int maxrank = rs.getInt("maxrank");
				id = maxrank + 1;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Gift_DAO.getGiftMaxRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return id;
	}
	
	/**
	 * 获取前一个Gift_Bean
	 * @param position
	 * @param status
	 * @return
	 */
	public static Gift_Bean getPreGift_Bean(String position,String status)
	{
		Gift_Bean gift = null;
		long nowTime = System.currentTimeMillis();
		String sql = "SELECT  * FROM `youhui_datamining`.`tyh_gifts` where status=? and rank<? ";
		
		sql = sql + " AND `start_time` < " + nowTime + " AND `end_time` > " + nowTime +" order by rank desc limit 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setString(2, position);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				gift = new Gift_Bean();
				gift.setId(rs.getString("id"));
				gift.setTitle(rs.getString("gift_title"));
				gift.setDescription(rs.getString("gift_desc"));
				gift.setUpdatetime(CodeUtil.getDateTimeStr(rs.getLong("update_time")));
				gift.setClickname(rs.getString("clickname"));
				gift.setImg(rs.getString("img"));
				gift.setImgIpad(rs.getString("img_ipad"));
				gift.setActionType(rs.getString("action_type"));
				gift.setActionValue(rs.getString("action_value"));
				gift.setType(rs.getString("type"));
				gift.setStart_time(rs.getString("start_time"));
				gift.setEnd_time(rs.getString("end_time"));
				gift.setRank(rs.getInt("rank"));
				gift.setStatus(rs.getInt("status"));
				String urlKey = "";
				Pattern pattern = Pattern.compile(serverHost);
				Matcher m_content = pattern.matcher(gift.getActionValue());
				if(m_content.find()) 
				{
					urlKey =gift.getActionValue().replaceAll(serverHost, "");
				}
				gift.setUrlKey(urlKey);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Gift_DAO.getPreGift_Bean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return gift;
	}
	
	/**
	 * 获取下一个Gift_Bean
	 * @param id
	 * @param status
	 * @return
	 */
	public static Gift_Bean getNextGift_Bean(String position,String status)
	{
		Gift_Bean gift = null;
		long nowTime = System.currentTimeMillis();
		String sql = "SELECT  * FROM `youhui_datamining`.`tyh_gifts` where status=? and rank>? ";
		
		sql = sql + " AND `start_time` < " + nowTime + " AND `end_time` > " + nowTime +" order by rank asc limit 1;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setString(2, position);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				gift = new Gift_Bean();
				gift.setId(rs.getString("id"));
				gift.setTitle(rs.getString("gift_title"));
				gift.setDescription(rs.getString("gift_desc"));
				gift.setUpdatetime(CodeUtil.getDateTimeStr(rs.getLong("update_time")));
				gift.setClickname(rs.getString("clickname"));
				gift.setImg(rs.getString("img"));
				gift.setImgIpad(rs.getString("img_ipad"));
				gift.setActionType(rs.getString("action_type"));
				gift.setActionValue(rs.getString("action_value"));
				gift.setType(rs.getString("type"));
				gift.setStart_time(rs.getString("start_time"));
				gift.setEnd_time(rs.getString("end_time"));
				gift.setRank(rs.getInt("rank"));
				gift.setStatus(rs.getInt("status"));
				String urlKey = "";
				Pattern pattern = Pattern.compile(serverHost);
				Matcher m_content = pattern.matcher(gift.getActionValue());
				if(m_content.find()) 
				{
					urlKey =gift.getActionValue().replaceAll(serverHost, "");
				}
				gift.setUrlKey(urlKey);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Gift_DAO.getNextGift_Bean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return gift;
	}
	

}
