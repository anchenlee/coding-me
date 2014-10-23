package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.netting.bean.Gift_Bean;
import com.netting.bean.UserBean;
import com.netting.db.DataSourceFactory;


/**
 * 终端用户数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_User_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_User_DAO.class);
	
	private static int pageSize = 10;
	
	/**
	 * 管理员获取列表
	 * @param page 页码
	 * @param keyword 关键字查询
	 * @param status 礼物状态
	 * @return
	 */
	public static ArrayList<UserBean> getUserBeanList(int page, String keyword)
	{
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		if (page < 0) 
		{
			page = 1;
		}
		
		String sql = "SELECT * FROM `youhui_v3`.`user` ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " WHERE `taobao_nick` LIKE '%" + keyword + "%'  OR `uid` LIKE '%"+keyword+"%' ";
		}
		sql = sql + " ORDER BY `creat_time` DESC limit ?,?;";
		
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
				UserBean userBean = new UserBean();
				
				userBean.setId(rs.getString("id"));
				userBean.setUid(rs.getString("uid"));
				userBean.setImei(rs.getString("imei"));
				userBean.setUsername(rs.getString("username"));
				userBean.setImsi(rs.getString("imsi"));
				userBean.setTaobao_nick(rs.getString("taobao_nick"));
				userBean.setTaobao_uid(rs.getString("taobao_uid"));
				userBean.setFrom_uid(rs.getString("from_uid"));
				userBean.setSex(rs.getString("sex"));
				userBean.setCity(rs.getString("location_city"));
				userBean.setProvince(rs.getString("location_province"));
				
				list.add(userBean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_User_DAO.getUserBeanList error:", e);
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
	public static int getUserBeanListTotal(String keyword) 
	{
		int totalPage = 0;
		
		String sql = "SELECT count(`id`) AS count FROM `youhui_v3`.`user` ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " WHERE `taobao_nick` LIKE '%" + keyword + "%'  OR `uid` LIKE '%"+keyword+"%' ";
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
			logger.error("Admin_User_DAO.getUserBeanList error:", e);
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

}
