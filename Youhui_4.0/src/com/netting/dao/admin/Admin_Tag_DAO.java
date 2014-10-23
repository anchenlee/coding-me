package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.Action;
import com.netting.bean.TagIcon;
import com.netting.bean.TagToItemBean;
import com.netting.bean.Tag_Bean;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;


/**
 * 标签(类目)TAG数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_Tag_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_Tag_DAO.class);
	
	private static int pageSize = 20;
	
	private static final String tagdefalutUid = "00000000";
	
	private static final String tagdefalutUidInIpad = "00000001";
	
	/**
	 * 获取标签(类目)
	 * @param tag_id
	 * @return
	 */
	public static Tag_Bean getTagBean(String tag_id)
	{
		Tag_Bean bean = new Tag_Bean();
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where `id`=?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, tag_id);
			
			rtst = ps.executeQuery();
			if (rtst.next())
			{
				bean.setTag_id(rtst.getString("id"));
				bean.setTag_name(rtst.getString("keyword"));
				bean.setSex(rtst.getInt("sex"));
				bean.setParent_tag_id(rtst.getString("parent_id"));
				bean.setRank(rtst.getInt("rank"));
				bean.setGoods_count(rtst.getInt("goods_count"));
				bean.setPhone_icon(rtst.getString("icon"));
				bean.setStatus(rtst.getInt("client_show"));
				
				String actionType = rtst.getString("action_type");
				String actionValue = rtst.getString("action_value");
				bean.setTag_action(new Action(actionType, actionValue));
				
				bean.setDescription(rtst.getString("description"));
				bean.setIsPadTag(rtst.getInt("istag"));
				bean.setPad_icon(rtst.getString("tag_icon"));
				bean.setPad_rank(rtst.getInt("tag_rank"));
				bean.setSmall_show(rtst.getString("show"));
				
				String chayeActionType = rtst.getString("chaye_action_type");
				String chayeActionValue = rtst.getString("chaye_action_value");
				bean.setChaye_action(new Action(chayeActionType, chayeActionValue));
				
				bean.setChaye_icon(rtst.getString("chaye_icon"));
				bean.setChaye_icon_size(rtst.getString("chaye_icon_size"));
				bean.setLock_num(rtst.getInt("lockstatue"));
				bean.setHeat(rtst.getString("heat"));
				
				bean.setBgcolor(rtst.getString("bgcolor"));
				// 集分宝返利比例
				bean.setJfb_rate(rtst.getString("jfb_rate"));
				
				bean.setHas_son_tag_all(rtst.getString("has_son_tag_all"));
				bean.setDefault_son_tag_id(rtst.getString("default_son_tag_id"));
				bean.setStartTime(CodeUtil.getDateTimeStr(rtst.getLong("sale_time")));
				
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getTagBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 查询标签名
	 * @param tagID
	 * @return
	 */
	public static String getTagName(String tagID)
	{
		String tagName = null;
		
		String sql = "SELECT `keyword` FROM `youhui_datamining`.`m_discount_keywords` where `id`=?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagID);
			
			rtst = ps.executeQuery();
			if (rtst.next())
			{
				tagName = rtst.getString("keyword");
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getTagName error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return tagName;
	}
	
	/**
	 * 添加标签的时候检查标签名是否重复
	 * @param tagID
	 * @return
	 */
	public static boolean checkTagNameRepeat(String tagName, String parent_id)
	{
		boolean flag = false;
		
		String sql = "SELECT `keyword` FROM `youhui_datamining`.`m_discount_keywords` where `keyword` = ? AND `parent_id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagName);
			ps.setString(2, parent_id);
			
			rtst = ps.executeQuery();
			if (rtst.next())
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getTagName error:", e);
			flag = false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 查询标签的集分宝比例
	 * @param tagID
	 * @return
	 */
	public static String getTagJfbRate(String tagID)
	{
		String jfbRate = null;
		
		String sql = "SELECT `jfb_rate` FROM `youhui_datamining`.`m_discount_keywords` where `id`=?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagID);
			
			rtst = ps.executeQuery();
			if (rtst.next())
			{
				jfbRate = rtst.getString("jfb_rate");
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getTagJfbRate error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return jfbRate;
	}
	
	/**
	 * 管理员获取页面上方的快捷链接列表
	 * @param status
	 * @param parent_id
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getHeaderTagList(int status, int parent_id)
	{
		ArrayList<HashMap<String, String>> tagList = new ArrayList<HashMap<String, String>>();
		String sql = "SELECT `id`,`keyword` FROM `youhui_datamining`.`m_discount_keywords` where `client_show` = ? AND `parent_id` = ? and `effective`='0' ";
		if (status == 1)
		{
			sql = sql + " order by `rank` ASC;";
		}
		else
		{
			sql = sql + " order by `timestamp` DESC;";
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, status);
			ps.setInt(2, parent_id);
			
			rtst = ps.executeQuery();
			while (rtst.next())
			{
				HashMap<String, String> tagMap = null;
				String tag_id = rtst.getString("id");
				String tag_name = rtst.getString("keyword");
				if (tag_id != null && !tag_id.equals("") && tag_name != null && !tag_name.equals(""))
				{
					tagMap = new HashMap<String, String>();
					tagMap.put(tag_id, tag_name);
					tagList.add(tagMap);
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getHeaderTagList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return tagList;
	}
	
	/**
	 * 管理员获取标签(类目)列表
	 * @param page
	 * @param status
	 * @param keyword
	 * @param ispad
	 * @return
	 */
	public static ArrayList<Tag_Bean> getTagList(int page, int status, String keyword, int ispad, int parent_id)
	{
		ArrayList<Tag_Bean> list = new ArrayList<Tag_Bean>();
		if (page < 0) 
		{
			page = 1;
		}
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where  `parent_id` = ? and `effective`='0' ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND (`keyword` like '%" + keyword + "%' or `description` like '%" + keyword + "%')";
		}
		if (ispad == 1) 
		{
			sql = sql + "  AND `istag` = 1 ";
		}
		if (status == 1 || status == 2 || status == 3)
		{
			sql = sql + "AND `client_show` = " + status;
		}
		if (status == 1)
		{
			sql = sql + " order by `rank` ASC limit ?,?;";
		}
		else
		{
			sql = sql + " order by `timestamp` DESC limit ?,?;";
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, parent_id);
			ps.setInt(2, pageSize * (page - 1));
			ps.setInt(3, pageSize);	
			
			rtst = ps.executeQuery();
			while (rtst.next())
			{
				Tag_Bean bean = new Tag_Bean();
				
				bean.setTag_id(rtst.getString("id"));
				bean.setTag_name(rtst.getString("keyword"));
				bean.setSex(rtst.getInt("sex"));
				bean.setParent_tag_id(rtst.getString("parent_id"));
				bean.setRank(rtst.getInt("rank"));
				bean.setGoods_count(rtst.getInt("goods_count"));
				bean.setPhone_icon(rtst.getString("icon"));
				bean.setStatus(rtst.getInt("client_show"));
				
				String actionType = rtst.getString("action_type");
				String actionValue = rtst.getString("action_value");
				bean.setTag_action(new Action(actionType, actionValue));
				
				bean.setDescription(rtst.getString("description"));
				bean.setIsPadTag(rtst.getInt("istag"));
				bean.setPad_icon(rtst.getString("tag_icon"));
				bean.setPad_rank(rtst.getInt("tag_rank"));
				bean.setSmall_show(rtst.getString("show"));
				
				String chayeActionType = rtst.getString("chaye_action_type");
				String chayeActionValue = rtst.getString("chaye_action_value");
				bean.setChaye_action(new Action(chayeActionType, chayeActionValue));
				
				bean.setChaye_icon(rtst.getString("chaye_icon"));
				bean.setChaye_icon_size(rtst.getString("chaye_icon_size"));
				bean.setLock_num(rtst.getInt("lockstatue"));
				bean.setHeat(rtst.getString("heat"));
				
				bean.setBgcolor(rtst.getString("bgcolor"));
				// 集分宝返利比例
				bean.setJfb_rate(rtst.getString("jfb_rate"));
				
				bean.setHas_son_tag_all(rtst.getString("has_son_tag_all"));
				bean.setDefault_son_tag_id(rtst.getString("default_son_tag_id"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getTagList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return list;
	}
	
	public static String getSonTagID(String parentTagID)
	{
		String sonTagID = "";
		
		String sql = "SELECT `id` FROM `youhui_datamining`.`m_discount_keywords` where `parent_id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, parentTagID);
			rtst = ps.executeQuery();
			
			while (rtst.next())
			{
				String tagID = rtst.getString("id");
				sonTagID = sonTagID + tagID + ",";
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getSonTagID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return sonTagID;
	}
	
	/**
	 * 管理员获取标签(类目)列表页面数
	 * @param status
	 * @param keyword
	 * @param ispad
	 * @return
	 */
	public static int getTagListTotal(int status, String keyword, int ispad, int parent_id) 
	{
		int totalPage = 0;
		
		String sql = "SELECT count(`id`) as count FROM `youhui_datamining`.`m_discount_keywords` where `parent_id` = ? ";
		
		if (status != 4)
		{
			sql = sql + "AND `client_show` = " + status;
		}
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND (`keyword` like '%" + keyword + "%' or `description` like '%" + keyword + "%')";
		}
		if (ispad == 1) 
		{
			sql = sql + " AND `istag` = 1";
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, parent_id);
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
			logger.error("Admin_Tag_DAO.getTagListTotal error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return totalPage;
	}
	
	/**
	 * 获取 新增/编辑 标签时使用的展示内容(标签系)
	 * @param parentID
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> getTagActionType_Value_Map(String parentID) 
	{
		// 显示
		ArrayList<HashMap<String, String>> headTagList_1 = Admin_Tag_DAO.getHeaderTagList(1, Integer.parseInt(parentID));
    	// 隐藏
		ArrayList<HashMap<String, String>> headTagList_2 = Admin_Tag_DAO.getHeaderTagList(2, Integer.parseInt(parentID));
		
		if (null != headTagList_1 && headTagList_1.size() > 0 
				&& null != headTagList_2 && headTagList_2.size() > 0)
		{
			headTagList_1.addAll(headTagList_2);
		}
		
		return headTagList_1;
    	
	}
	
	/**
	 * 更新标签(类目)
	 * @param tag
	 * @return
	 */
	public static boolean updateTag(Tag_Bean tag)
	{
		boolean flag = false;
		
		String sql1 = "UPDATE `youhui_datamining`.`m_discount_keywords` SET " +
				" `keyword`=?,`icon`=?,`client_show`=?,`action_type`=?,`action_value`=?,`description`=?,`istag`=?,`heat`=?," +
				" `show`=?,`chaye_action_type`=?,`chaye_action_value`=?,`chaye_icon`=?,`chaye_icon_size`=?,`bgcolor`=?,`show`=?, " +
				" `sex`=?,`tag_icon`=?,`jfb_rate`=?,`has_son_tag_all`=?,`default_son_tag_id`=? ,`effective`=?,sale_time=? WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql1);
			
			ps.setString(1, tag.getTag_name());
			ps.setString(2, tag.getPhone_icon());
			ps.setInt(3, tag.getStatus());
			ps.setString(4, tag.getTag_action().getActionType());
			ps.setString(5, tag.getTag_action().getActionValue());
			ps.setString(6, tag.getDescription());
			ps.setInt(7, tag.getIsPadTag());
			ps.setString(8, tag.getHeat());
			ps.setString(9, tag.getSmall_show());
			ps.setString(10, tag.getChaye_action().getActionType());
			ps.setString(11, tag.getChaye_action().getActionValue());
			ps.setString(12, tag.getChaye_icon());
			ps.setString(13, tag.getChaye_icon_size());
			ps.setString(14, tag.getBgcolor());
			ps.setString(15, tag.getSmall_show());
			ps.setInt(16, tag.getSex());
			ps.setString(17, tag.getPad_icon());
			ps.setString(18, tag.getJfb_rate());
			ps.setString(19, tag.getHas_son_tag_all());
			ps.setString(20, tag.getDefault_son_tag_id());
			ps.setString(21, tag.getIsEffective());
			ps.setString(22, tag.getStartTime());
			
			ps.setString(23, tag.getTag_id());
			
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.updateTag error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 更新商品归属的标签名称
	 * @param tagID
	 * @return
	 */
	public static boolean updateProductTagName(String oldTagName, String newTagName)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`m_discount_products` SET `keyword` = ? WHERE `keyword` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, newTagName);
			ps.setString(2, oldTagName);
			
			int resp = ps.executeUpdate();;
			if (resp > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.updateProductTagName error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 新增标签(类目)
	 * @param tag
	 * @return 新增的标签的ID
	 */
	public static String addTag(Tag_Bean tag)
	{
		String tag_id = null;
		
		String sql1 = "select min(`rank`) as min_rank from `youhui_datamining`.`m_discount_keywords` " +
							"where `parent_id` = '" + tag.getParent_tag_id() + "';";
		
		String sql2 = "INSERT INTO `youhui_datamining`.`m_discount_keywords` " +
				" (`keyword`,`sex`,`parent_id`,`icon`,`client_show`,`action_type`,`action_value`,`description`,`istag`,`tag_icon`," +
				" `show`,`chaye_action_type`,`chaye_action_value`,`chaye_icon`,`chaye_icon_size`,`heat`,`bgcolor`,`rank`,`timestamp`,`jfb_rate`,sale_time) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		String sql3 = "UPDATE `youhui_datamining`.`m_discount_keywords` SET `action_value` = `id` WHERE `id` = ?";
		
		String sql4 = "UPDATE `youhui_datamining`.`m_discount_keywords` SET `chaye_action_value` = `id` WHERE `id` = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			int rank = 0;
			
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			if (rs.next())
			{
				rank = rs.getInt("min_rank");
			}
			rank--;
			
			ps = conn.prepareStatement(sql2);
			
			ps.setString(1, tag.getTag_name());
			ps.setInt(2, tag.getSex());
			ps.setString(3, tag.getParent_tag_id());
			ps.setString(4, tag.getPhone_icon());
			ps.setInt(5, tag.getStatus());
			ps.setString(6, tag.getTag_action().getActionType());
			ps.setString(7, tag.getTag_action().getActionValue());
			ps.setString(8, tag.getDescription());
			ps.setInt(9, tag.getIsPadTag());
			ps.setString(10, tag.getPad_icon());
			ps.setString(11, tag.getSmall_show());
			ps.setString(12, tag.getChaye_action().getActionType());
			ps.setString(13, tag.getChaye_action().getActionValue());
			ps.setString(14, tag.getChaye_icon());
			ps.setString(15, tag.getChaye_icon_size());
			ps.setString(16, tag.getHeat());
			ps.setString(17, tag.getBgcolor());
			ps.setInt(18, rank);
			ps.setString(19, String.valueOf(System.currentTimeMillis()));
			ps.setString(20, tag.getJfb_rate());
			ps.setString(21, tag.getStartTime());
			
			int res = ps.executeUpdate();
			if (res > 0)
			{
				ps = conn.prepareStatement("SELECT LAST_INSERT_ID();");
				rs = ps.executeQuery();
				if(rs.next())
				{
					tag_id = rs.getString(1);
				}
			}
			
			if (tag_id != null && !tag_id.equals(""))
			{
				if (tag.getTag_action().getActionValue().equals("default"))
				{
					ps = conn.prepareStatement(sql3);
					ps.setString(1, tag_id);
					ps.executeUpdate();
				}
				
				if (tag.getChaye_action().getActionValue().equals("default"))
				{
					ps = conn.prepareStatement(sql4);
					ps.setString(1, tag_id);
					ps.executeUpdate();
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.addTag error:", e);
			return null;
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return tag_id;
	}
	
	/**
	 * 将tag_id从用户默认的标签列表里删除(DB数据库)</p>
	 * @param tag_id
	 * @return
	 */
	public static boolean delDefaultUserTag(String tag_id)
	{
		boolean flag = false;
		
		String sql1 = "DELETE FROM `youhui_datamining`.`tyh_user_tag` WHERE `uid` = ? AND `tagid` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql1);
			
			ps.setString(1, "00000000");
			ps.setString(2, tag_id);
			
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_Tag_DAO.delDefaultUserTag error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 将tag_id添加到用户默认的标签列表里(DB数据库)</p>
	 * 默认用户名：00000000
	 * @param tag_id
	 * @return
	 */
	public static boolean addDefaultUserTag(String tag_id)
	{
		boolean flag = false;
		
		String sql0 = "SELECT * FROM `youhui_datamining`.`tyh_user_tag` WHERE `uid` = ? AND `tagid` = ?;";
		
		String sql1 = "SELECT max(rank) as rank FROM `youhui_datamining`.`tyh_user_tag` WHERE `uid` = ?;";
		String sql2 = "INSERT INTO `youhui_datamining`.`tyh_user_tag`(`uid`,`tagid`,`rank`) VALUES (?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql0);
			ps.setString(1, "00000000");
			ps.setString(2, tag_id);
			rs = ps.executeQuery();
			if(rs.next()) 
			{
				return false;
			}
			
			int rank = 0;
			ps = conn.prepareStatement(sql1);
			ps.setString(1, "00000000");
			rs = ps.executeQuery();
			if(rs.next()) 
			{
				rank = rs.getInt("rank");
			}
			
			ps = conn.prepareStatement(sql2);
			ps.setString(1, "00000000");
			ps.setString(2, tag_id);
			ps.setInt(3, rank + 1);
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;		
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_Tag_DAO.addDefaultUserTag error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 将tag_id添加到IPAD用户默认的标签列表里(DB数据库)</p>
	 * IPAD默认用户名：00000001
	 * @param tag_id
	 * @return
	 */
	public static boolean addDefaultUserIpadTag(String tag_id)
	{
		boolean flag = false;
		
		String sql0 = "SELECT * FROM `youhui_datamining`.`tyh_user_tag_ipad` WHERE `uid` = ? AND `tagid` = ?;";
		
		String sql1 = "SELECT max(rank) as rank FROM `youhui_datamining`.`tyh_user_tag_ipad` WHERE `uid` = ?;";
		String sql2 = "INSERT INTO `youhui_datamining`.`tyh_user_tag_ipad`(`uid`,`tagid`,`rank`) VALUES (?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql0);
			ps.setString(1, "00000001");
			ps.setString(2, tag_id);
			rs = ps.executeQuery();
			if(rs.next()) 
			{
				return false;
			}
			
			int rank = 0;
			ps = conn.prepareStatement(sql1);
			ps.setString(1, "00000001");
			rs = ps.executeQuery();
			if(rs.next()) 
			{
				rank = rs.getInt("rank");
			}
			
			ps = conn.prepareStatement(sql2);
			ps.setString(1, "00000001");
			ps.setString(2, tag_id);
			ps.setInt(3, rank + 1);
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;		
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_Tag_DAO.addDefaultUserIpadTag error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 将tag_id从用户默认的标签列表里删除(DB数据库)</p>
	 * @param tag_id
	 * @return
	 */
	public static boolean delDefaultUserIpadTag(String tag_id)
	{
		boolean flag = false;
		
		String sql1 = "DELETE FROM `youhui_datamining`.`tyh_user_tag_ipad` WHERE `uid` = ? AND `tagid` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql1);
			
			ps.setString(1, "00000001");
			ps.setString(2, tag_id);
			
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_Tag_DAO.delDefaultUserIpadTag error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 获取tagdefalutUid tagid
	 * @param tagid 
	 * @return
	 */
	public static TagToItemBean getUserTag(String tagid,String ispad)
	{
		TagToItemBean bean = null;
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		String table = "";
		String uid = "";
		if(ispad.equals("0"))
		{
			table = "tyh_user_tag";
			uid = tagdefalutUid;
		}
		else if(ispad.equals("1"))
		{
			table = "tyh_user_tag_ipad";
			uid = tagdefalutUidInIpad;
		}
		String sql = "SELECT * FROM `youhui_datamining`.`"+table+"` WHERE `uid` = ? AND `tagid` = ?;";
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			
			s.setString(1, uid);
			s.setString(2, tagid);
			
			rs = s.executeQuery();
			
			if(rs.next())
			{
				bean = new TagToItemBean();
				bean.setItemid(uid);
				bean.setRank(rs.getInt("rank"));
				bean.setTagid(tagid);
			}

		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.getUserTag error:", e);

		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return bean;
	}
	
	/**
	 * 获取前一个UserTag
	 * @param rank
	 * @return
	 */
	public static TagToItemBean getPreUserTag(int rank,String ispad)
	{
		TagToItemBean bean = null;
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		String table = "";
		String uid = "";
		if(ispad.equals("0"))
		{
			table = "tyh_user_tag";
			uid = tagdefalutUid;
		}
		else if(ispad.equals("1"))
		{
			table = "tyh_user_tag_ipad";
			uid = tagdefalutUidInIpad;
		}
		String sql = "SELECT * FROM `youhui_datamining`.`"+table+"` WHERE `uid` = ? AND `rank` < ? order by rank desc limit 1;";
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			
			s.setString(1, uid);
			s.setInt(2, rank);

			rs = s.executeQuery();
			
			if(rs.next())
			{
				bean = new TagToItemBean();
				bean.setItemid(uid);
				bean.setRank(rs.getInt("rank"));
				bean.setTagid(rs.getString("tagid"));
			}

		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.getPreUserTag error:", e);

		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return bean;
	}
	
	/**
	 * 获取后一个UserTag
	 * @param rank
	 * @return
	 */
	public static TagToItemBean getNextUserTag(int rank,String ispad)
	{
		TagToItemBean bean = null;
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		String table = "";
		String uid = "";
		if(ispad.equals("0"))
		{
			table = "tyh_user_tag";
			uid = tagdefalutUid;
		}
		else if(ispad.equals("1"))
		{
			table = "tyh_user_tag_ipad";
			uid = tagdefalutUidInIpad;
		}
		String sql = "SELECT * FROM `youhui_datamining`.`"+table+"` WHERE `uid` = ? AND `rank` > ? order by rank asc limit 1;";
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			
			s.setString(1, uid);
			s.setInt(2, rank);
			
			rs = s.executeQuery();
			
			if(rs.next())
			{
				bean = new TagToItemBean();
				bean.setItemid(uid);
				bean.setRank(rs.getInt("rank"));
				bean.setTagid(rs.getString("tagid"));
			}

		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.getNextUserTag error:", e);

		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return bean;
	}
	
	/**
	 * 获取最大的UserTag rank
	 * @return
	 */
	public static int getMaxUserTagRank(String ispad)
	{
		int rank = 0;
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		String table = "";
		String uid = "";
		if(ispad.equals("0"))
		{
			table = "tyh_user_tag";
			uid = tagdefalutUid;
		}
		else if(ispad.equals("1"))
		{
			table = "tyh_user_tag_ipad";
			uid = tagdefalutUidInIpad;
		}
		String sql = "SELECT max(rank) as maxRank FROM `youhui_datamining`.`"+table+"` WHERE `uid` = ? ;";
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			
			s.setString(1, uid);
			
			rs = s.executeQuery();
			
			if(rs.next())
			{
				rank = rs.getInt("maxRank");
			}
			rank ++;
		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.getMaxUserTagRank error:", e);

		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return rank;
	}
	
	/**
	 * 获取最小UserTag rank
	 * @return
	 */
	public static int getMinUserTagRank(String ispad)
	{
		int rank = 0;
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		String table = "";
		String uid = "";
		if(ispad.equals("0"))
		{
			table = "tyh_user_tag";
			uid = tagdefalutUid;
		}
		else if(ispad.equals("1"))
		{
			table = "tyh_user_tag_ipad";
			uid = tagdefalutUidInIpad;
		}
		String sql = "SELECT min(rank) as minRank FROM `youhui_datamining`.`"+table+"` WHERE `uid` = ? ;";
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			
			s.setString(1, uid);
			
			rs = s.executeQuery();
			
			if(rs.next())
			{
				rank = rs.getInt("minRank");
			}
			rank--;
		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.getMinUserTagRank error:", e);

		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return rank;
	}
	
	/**
	 * 修改tyh_user_tag 表tagIds顺序
	 * @param tagIds
	 * @return
	 */
	public static boolean updateDefalutTagId(String tagId,int rank,String ispad){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement s = null;
		String table = "";
		String uid = "";
		if(ispad.equals("0"))
		{
			table = "tyh_user_tag";
			uid = tagdefalutUid;
		}
		else if(ispad.equals("1"))
		{
			table = "tyh_user_tag_ipad";
			uid = tagdefalutUidInIpad;
		}
		String sql = "update `youhui_datamining`.`"+table+"` set rank=?  where `uid` =? and tagid=? ;";
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			s.setInt(1, rank);
			s.setString(2, uid);
			s.setString(3, tagId);

			int i = s.executeUpdate();
			if(i > 0)
			{
				flag = true;
			}
		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.updateDefalutTagId error:", e);
			return false;
		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return flag;
	}
	
	public static String getIdsByParentID(String status, String ispad, String parent_id)
	{
		StringBuffer ids = new StringBuffer();
		
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where  `client_show` = ? AND `parent_id` = ? ";
		

		if (ispad.equals("1")) 
		{
			sql = sql + "  AND `istag` = 1 ";
		}
		sql = sql + " order by `rank` asc ;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setString(2, parent_id);

			rtst = ps.executeQuery();
			while (rtst.next())
			{
				ids.append(rtst.getString("id"));
				ids.append(",");
			}
			if(ids.length() > 1 && ids.charAt(ids.length()-1) == ',')
			{
				ids.deleteCharAt(ids.length()-1);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getIdsByParentID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return ids.toString();
	}
	
	public static String getIdsByParentIDSale(String status, String ispad, String parent_id)
	{
		StringBuffer ids = new StringBuffer();
		
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where  `client_show` = ? AND `parent_id` = ? ";
		

		if (ispad.equals("1")) 
		{
			sql = sql + "  AND `istag` = 0 ";
		}
		sql = sql + " order by `rank` asc ;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setString(2, parent_id);

			rtst = ps.executeQuery();
			while (rtst.next())
			{
				ids.append(rtst.getString("id"));
				ids.append(",");
			}
			if(ids.length() > 1 && ids.charAt(ids.length()-1) == ',')
			{
				ids.deleteCharAt(ids.length()-1);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getIdsByParentID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return ids.toString();
	}
	
	/**
	 * 获取前一个Tag_Bean
	 * @param parent
	 * @param status
	 * @param id
	 * @param isPad
	 * @return
	 */
	public static Tag_Bean getPreTag_Bean(String status , String parent ,String isPad,String position)
	{
		Tag_Bean bean = null;
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where `client_show` = ? AND `parent_id` = ?  and `rank`<? ";
		if (isPad.equals("1")) 
		{
			sql = sql + "  AND `istag` = 1 ";
		}
		sql = sql + " order by `rank` desc limit 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setString(2, parent);
			ps.setString(3, position);
			
			rtst = ps.executeQuery();
			if (rtst.next())
			{
				bean = new Tag_Bean();
				bean.setTag_id(rtst.getString("id"));
				bean.setTag_name(rtst.getString("keyword"));
				bean.setSex(rtst.getInt("sex"));
				bean.setParent_tag_id(rtst.getString("parent_id"));
				bean.setRank(rtst.getInt("rank"));
				bean.setGoods_count(rtst.getInt("goods_count"));
				bean.setPhone_icon(rtst.getString("icon"));
				bean.setStatus(rtst.getInt("client_show"));
				
				String actionType = rtst.getString("action_type");
				String actionValue = rtst.getString("action_value");
				bean.setTag_action(new Action(actionType, actionValue));
				
				bean.setDescription(rtst.getString("description"));
				bean.setIsPadTag(rtst.getInt("istag"));
				bean.setPad_icon(rtst.getString("tag_icon"));
				bean.setPad_rank(rtst.getInt("tag_rank"));
				bean.setSmall_show(rtst.getString("show"));
				
				String chayeActionType = rtst.getString("chaye_action_type");
				String chayeActionValue = rtst.getString("chaye_action_value");
				bean.setChaye_action(new Action(chayeActionType, chayeActionValue));
				
				bean.setChaye_icon(rtst.getString("chaye_icon"));
				bean.setChaye_icon_size(rtst.getString("chaye_icon_size"));
				bean.setLock_num(rtst.getInt("lockstatue"));
				bean.setHeat(rtst.getString("heat"));
				
				bean.setBgcolor(rtst.getString("bgcolor"));
				// 集分宝返利比例
				bean.setJfb_rate(rtst.getString("jfb_rate"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getPreTag_Bean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return bean;
	}
	
	/**
	 * 获取后一个Tag_Bean
	 * @param parent
	 * @param tag_id
	 * @param id
	 * @param idPad
	 * @return
	 */
	public static Tag_Bean getNextTag_Bean(String status , String parent ,String isPad,String position)
	{
		Tag_Bean bean = null;
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where `client_show` = ? AND `parent_id` = ?  and `rank`>? ";
		if (isPad.equals("1")) 
		{
			sql = sql + "  AND `istag` = 1 ";
		}
		sql = sql + " order by `rank` asc limit 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setString(2, parent);
			ps.setString(3, position);
			
			rtst = ps.executeQuery();
			if (rtst.next())
			{
				bean = new Tag_Bean();
				bean.setTag_id(rtst.getString("id"));
				bean.setTag_name(rtst.getString("keyword"));
				bean.setSex(rtst.getInt("sex"));
				bean.setParent_tag_id(rtst.getString("parent_id"));
				bean.setRank(rtst.getInt("rank"));
				bean.setGoods_count(rtst.getInt("goods_count"));
				bean.setPhone_icon(rtst.getString("icon"));
				bean.setStatus(rtst.getInt("client_show"));
				
				String actionType = rtst.getString("action_type");
				String actionValue = rtst.getString("action_value");
				bean.setTag_action(new Action(actionType, actionValue));
				
				bean.setDescription(rtst.getString("description"));
				bean.setIsPadTag(rtst.getInt("istag"));
				bean.setPad_icon(rtst.getString("tag_icon"));
				bean.setPad_rank(rtst.getInt("tag_rank"));
				bean.setSmall_show(rtst.getString("show"));
				
				String chayeActionType = rtst.getString("chaye_action_type");
				String chayeActionValue = rtst.getString("chaye_action_value");
				bean.setChaye_action(new Action(chayeActionType, chayeActionValue));
				
				bean.setChaye_icon(rtst.getString("chaye_icon"));
				bean.setChaye_icon_size(rtst.getString("chaye_icon_size"));
				bean.setLock_num(rtst.getInt("lockstatue"));
				bean.setHeat(rtst.getString("heat"));
				
				bean.setBgcolor(rtst.getString("bgcolor"));
				// 集分宝返利比例
				bean.setJfb_rate(rtst.getString("jfb_rate"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getNextTag_Bean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return bean;
	}
	
	/**
	 * 获取最大的rank
	 * @param parent
	 * @param tag_id
	 * @param id
	 * @param idPad
	 * @return
	 */
	public static int getMaxRank(String status , String parent ,String isPad)
	{
		int rank = 0;
		String sql = "SELECT max(rank) as maxrank FROM `youhui_datamining`.`m_discount_keywords` where `client_show` = ? AND `parent_id` = ?   ";
		if (isPad.equals("1")) 
		{
			sql = sql + "  AND `istag` = 1 ";
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setString(2, parent);
			
			rtst = ps.executeQuery();
			if (rtst.next())
			{
				rank = rtst.getInt("maxrank");
			}
			rank++;
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getMaxRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return rank;
	}
	
	/**
	 * 获取最小的rank
	 * @param parent
	 * @param tag_id
	 * @param id
	 * @param idPad
	 * @return
	 */
	public static int getMinRank(String status , String parent ,String isPad)
	{
		int rank = 0;
		String sql = "SELECT min(rank) as minrank FROM `youhui_datamining`.`m_discount_keywords` where `client_show` = ? AND `parent_id` = ?   ";
		if (isPad.equals("1")) 
		{
			sql = sql + "  AND `istag` = 1 ";
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setString(2, parent);
			
			rtst = ps.executeQuery();
			if (rtst.next())
			{
				rank = rtst.getInt("minrank");
			}
			rank--;
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getMinRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return rank;
	}
	
	/**
	 * 修改Tag_Bean的rank
	 * @param parent
	 * @param tag_id
	 * @param id
	 * @param idPad
	 * @return
	 */
	public static boolean updateRank(int rank, String id)
	{
		boolean flag = false;
		String sql = "update youhui_datamining.m_discount_keywords set `rank` = ? where `id`=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, rank);
			ps.setString(2, id);
			
			int i = ps.executeUpdate();
			if(i>0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.updateRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return flag;
	}
	
	/**
	 * 查询所有tagUser
	 * @return
	 */
	public static List<String> getAllUser(String ispad)
	{
		List<String> list = new ArrayList<String>();
		String table = "";
		if(ispad.equals("0"))
		{
			table = "tyh_user_tag";
		}
		else if(ispad.equals("1"))
		{
			table = "tyh_user_tag_ipad";
		}
		String sql = "SELECT distinct(uid) FROM `youhui_datamining`.`"+table+"`  ;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rtst = ps.executeQuery();
			while(rtst.next())
			{
				list.add(rtst.getString("uid"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.getAllUser error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		return list;
	}
	
	/**
	 * 新添加的tag同步到所有user
	 * @param list
	 * @param tagid
	 * @param rank
	 * @return
	 */
	public static boolean synchronizeAllUserTag(List<String> list, String tagid, int rank, String ispad)
	{
		boolean flag = false;
		
		String table = "";
		if(ispad.equals("0"))
		{
			table = "tyh_user_tag";
		}
		else if(ispad.equals("1"))
		{
			table = "tyh_user_tag_ipad";
		}
		
		Connection conn = null;
		Statement st = null;
		ResultSet rtst = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			st = conn.createStatement();
			conn.setAutoCommit(false);
			
			// String check_sql = "";
			String update_sql = ""; 
			for(String uid : list)
			{
				/*
				check_sql = "select `id` from `youhui_datamining`.`" + table + "` where `tagid` = " + tagid + " and `uid` = '" + uid + "';";
				ps = conn.prepareStatement(check_sql);
				rtst = ps.executeQuery();
				if(rtst.next())
				{
					String id = rtst.getString("id");
					update_sql = "update `youhui_datamining`.`" + table + "` set `rank` = " + rank + " where `id` = " + id + ";";
				}
				else
				{
					update_sql = "insert into `youhui_datamining`.`" + table + "` (`tagid`, `rank`, `uid`)  values (" + tagid + "," + rank + ",'" + uid + "');";
				}
				*/
				update_sql = "INSERT INTO `youhui_datamining`.`" + table + "` (`uid`,`tagid`,`rank`) VALUES ('" + uid + "'," + tagid + "," + rank + ") ON DUPLICATE KEY UPDATE rank = " + rank + ";";
				st.addBatch(update_sql);
			}
			
			st.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_DAO.synchronizeAllUserTag error:", e);
			return true;
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, st, conn);
		}
		return flag;
	}
	
	/**
	 * 查询user tag 中最小的rank
	 * @param ispad
	 * @return
	 */
	public static int getMinAllUserTagRank(String ispad,String position)
	{
		int rank = 0;
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		String table = "";
		if(ispad.equals("0"))
		{
			table = "tyh_user_tag";
		}
		else if(ispad.equals("1"))
		{
			table = "tyh_user_tag_ipad";
		}
		String sql = "";
		if(position.equals("0"))
		{
			sql = "SELECT min(rank) as minRank FROM `youhui_datamining`.`"+table+"`  ;";
		}
		else if(position.equals("1"))
		{			
			sql = "SELECT max(rank) as minRank FROM `youhui_datamining`.`"+table+"`  ;";
		}
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			
			
			rs = s.executeQuery();
			
			if(rs.next())
			{
				rank = rs.getInt("minRank");
			}
			if(position.equals("0"))
			{				
				rank--;
				if(rank == 1)
				{					
					rank--;
				}
			}
			else if(position.equals("1"))
			{
				rank++;
				if(rank == 1)
				{
					rank++;
				}
			}
		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.getMinUserTagRank error:", e);

		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return rank;
	}
	
	/**
	 * 获取标签下图片
	 * @param tagId
	 * @return
	 */
	public static List<TagIcon> getTagIconList(String tagId){
		List<TagIcon> list = new ArrayList<TagIcon>();
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.tyh_tag_icon where tag_id = ? and status='0';";
		
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			s.setString(1, tagId);
			
			rs = s.executeQuery();
			
			while(rs.next()){
				TagIcon bean = new TagIcon();
				bean.setId(rs.getString("id"));
				bean.setIcon(rs.getString("icon"));
				bean.setTagId(rs.getString("tag_id"));
				bean.setBeginTime(CodeUtil.getDateTimeStr(rs.getLong("begin_time")));
				bean.setEndTime(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				
				list.add(bean);
			}
			
		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.getTagIconList error:", e);
		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		
		return list;
	}
	
	/**
	 * 获取标签图片详细信息
	 * @param id
	 * @return
	 */
	public static TagIcon getTagIcon(String id){
		TagIcon bean = null;
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.tyh_tag_icon where id = ? ;";
		
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			s.setString(1, id);
			
			rs = s.executeQuery();
			
			if(rs.next()){
				bean = new TagIcon();
				bean.setId(rs.getString("id"));
				bean.setIcon(rs.getString("icon"));
				bean.setTagId(rs.getString("tag_id"));
				bean.setBeginTime(CodeUtil.getDateTimeStr(rs.getLong("begin_time")));
				bean.setEndTime(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				
			}
			
		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.getTagIcon error:", e);
		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		
		return bean;
	}
	
	/**
	 * 新增标签下图片信息
	 * @param bean
	 * @return
	 */	
	public static boolean addTagIcon(TagIcon bean){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement s = null;
		String sql = "insert into youhui_datamining.tyh_tag_icon(tag_id,icon,begin_time,end_time,status) values(?,?,?,?,?);";
		
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			s.setString(1, bean.getTagId());
			s.setString(2, bean.getIcon());
			s.setString(3, bean.getBeginTime());
			s.setString(4, bean.getEndTime());
			s.setInt(5, 0);
			
			s.executeUpdate();
			flag = true;
			
		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.addTagIcon error:", e);
		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return flag;
	}
	
	/**
	 * 修改标签下图片信息
	 * @param bean
	 * @return
	 */
	public static boolean updateTagIcon(TagIcon bean){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement s = null;
		String sql = "update youhui_datamining.tyh_tag_icon set tag_id=?, icon=?,begin_time=?,end_time=?,status=? where id=?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			s.setString(1, bean.getTagId());
			s.setString(2, bean.getIcon());
			s.setString(3, bean.getBeginTime());
			s.setString(4, bean.getEndTime());
			s.setInt(5, 0);
			s.setString(6, bean.getId());
			
			s.executeUpdate();
			flag = true;
			
		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.updateTagIcon error:", e);
		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return flag;
	}
	
	/**
	 * 检查时间段内标签下是否有图片信息
	 * @param tag_id
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean checkExitTagIcon(String tag_id,long beginTime,long endTime){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement s = null;
		
		String sql = "SELECT * FROM youhui_datamining.tyh_tag_icon where tag_id =? and (begin_time>?   or  end_time<?) ;";
		ResultSet rs = null;
		
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			s.setString(1, tag_id);
			s.setLong(2, endTime);
			s.setLong(3, beginTime);
			
			rs = s.executeQuery();
			if(rs.next()){
				flag = true;
			}
			
		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.checkExitTagIcon error:", e);
		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return flag;
	}
	
	/**
	 * 删除标签图片
	 * @param id
	 * @return
	 */
	public static boolean delTagIcon(String id){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement s = null;
		String sql = "update youhui_datamining.tyh_tag_icon set status=? where id=?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			s.setString(1, "1");
			s.setString(2, id);
			
			s.executeUpdate();
			flag = true;
			
		} catch (SQLException e) {
			logger.error("Admin_Tag_DAO.delTagIcon error:", e);
		}finally{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return flag;
	}
	
	public static void main(String[] args) {
		List<String> allUserList = Admin_Tag_DAO.getAllUser("0");
		int rank = Admin_Tag_DAO.getMinAllUserTagRank("0","0");
		//数据库同步			
		boolean flag = Admin_Tag_DAO.synchronizeAllUserTag(allUserList, "246", rank,"0");
		System.out.println(flag);
	}
}
