package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;


import com.netting.bean.AD_Bean;
import com.netting.bean.IPAD_Ad_Bean;
import com.netting.bean.SuiShouAction;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;
import com.netting.util.SuiShouActionUtil;


/**
 * 广告数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_AD_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_AD_DAO.class);
	
	private static int pageSize = 10;
	
	private static String serverHost ="http://b17.cn/";
	/**
	 * 获取广告详细信息
	 * @param tag_id
	 * @return
	 */
	public static AD_Bean getADBean(String id)
	{
		AD_Bean bean = null;
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE `id` = ?;";
		
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
				bean = new AD_Bean();
				
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("ad_title"));
				bean.setDescription(rs.getString("description"));
				bean.setUpdatetime(CodeUtil.getDateTimeStr(rs.getLong("timestamp")));
				bean.setType(rs.getString("ad_type"));
				bean.setImg(rs.getString("img"));
				
				String content = rs.getString("ad_content");
				bean.setContent(content);
				
				if (content != null && !"".equals(content))
				{
					JSONObject json = new JSONObject(content);
					if (json.has("img1"))
					{
						bean.setImg1(json.getString("img1"));
					}
					if (json.has("img2"))
					{
						bean.setImg2(json.getString("img2"));
					}
				}
				
				bean.setActionType(rs.getString("action_type"));
				bean.setActionValue(rs.getString("action_value"));
				bean.setPlatform(rs.getString("platform"));
				bean.setStart_time(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEnd_time(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setRank(rs.getInt("rank"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.getADBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	public static AD_Bean getADBeanNew(String id)
	{
		AD_Bean bean = null;
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE `id` = ?;";
		
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
				bean = new AD_Bean();
				
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("ad_title"));
				bean.setDescription(rs.getString("description"));
				bean.setUpdatetime((rs.getString("timestamp")));
				bean.setType(rs.getString("ad_type"));
				bean.setImg(rs.getString("img"));
				
				String content = rs.getString("ad_content");
				bean.setContent(content);
				
				if (content != null && !"".equals(content))
				{
					JSONObject json = new JSONObject(content);
					if (json.has("img1"))
					{
						bean.setImg1(json.getString("img1"));
					}
					if (json.has("img2"))
					{
						bean.setImg2(json.getString("img2"));
					}
				}
				
				bean.setActionType(rs.getString("action_type"));
				bean.setActionValue(rs.getString("action_value"));
				bean.setPlatform(rs.getString("platform"));
				bean.setStart_time((rs.getString("start_time")));
				bean.setEnd_time((rs.getString("end_time")));
				bean.setRank(rs.getInt("rank"));
				bean.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(rs.getString("action_type"), new String[]{rs.getString("action_value"),rs.getString("ad_title")})));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.getADBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 管理员获取广告列表
	 * @param page
	 * @param keyword
	 * @param status 广告状态：开始，未开始，已经结束
	 * @param platform 平台类型
	 * @return
	 */
	public static ArrayList<AD_Bean> getADList(int page, String keyword, int status, String platform)
	{
		ArrayList<AD_Bean> list = new ArrayList<AD_Bean>();
		
		long nowTime = System.currentTimeMillis();
		
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE 1 = 1 ";
		if (platform != null && !"".equals(platform) && !platform.equals("null") && !platform.equals("quanbu")) 
		{
			sql = sql + " AND `platform` = '" + platform + "' ";
		}
		
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `ad_title` LIKE '%" + keyword + "%'";
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
		
		if(status == 2)
		{
			sql = sql + " ORDER BY `rank` ASC  limit ?,?;";
		}
		else 
		{
			sql = sql + " ORDER BY `start_time` DESC,`timestamp` DESC  limit ?,?;";
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
				AD_Bean bean = new AD_Bean();
				
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("ad_title"));
				bean.setDescription(rs.getString("description"));
				bean.setUpdatetime(CodeUtil.getDateTimeStr(rs.getLong("timestamp")));
				bean.setType(rs.getString("ad_type"));
				bean.setImg(rs.getString("img"));
				
				String content = rs.getString("ad_content");
				bean.setContent(content);
				
				if (content != null && !"".equals(content))
				{
					JSONObject json = new JSONObject(content);
					if (json.has("img1"))
					{
						bean.setImg1(json.getString("img1"));
					}
					if (json.has("img2"))
					{
						bean.setImg2(json.getString("img2"));
					}
				}
				
				bean.setActionType(rs.getString("action_type"));
				bean.setActionValue(rs.getString("action_value"));
				bean.setPlatform(rs.getString("platform"));
				bean.setStart_time(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEnd_time(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setRank(rs.getInt("rank"));
				
				Pattern pattern = Pattern.compile(serverHost);
				Matcher m_content = pattern.matcher(bean.getActionValue());
				String urlKey = "";
				if(m_content.find()) 
				{
					urlKey =bean.getActionValue().replaceAll(serverHost, "");
				}
				bean.setUrlkey(urlKey);
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.getADList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * 管理员获取广告列表页面数
	 * @param keyword
	 * @param status
	 * @param platform
	 * @return
	 */
	public static int getADListTotal(String keyword, int status, String platform) 
	{
		int totalPage = 0;
		long nowTime = System.currentTimeMillis();
		
		String sql = "SELECT count(`id`) AS count FROM `youhui_datamining`.`tyh_ads` WHERE 1 = 1 ";
		if (platform != null && !"".equals(platform) && !platform.equals("null") && !platform.equals("quanbu")) 
		{
			sql = sql + " AND `platform` = '" + platform + "' ";
		}
		
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `ad_title` LIKE '%" + keyword + "%'";
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
			sql = sql + " AND `end_time` < " + nowTime + ";";
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
			logger.error("Admin_AD_DAO.getADListTotal error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return totalPage;
	}
	
	/**
	 * 管理员获取IPAD广告列表
	 * @return
	 */
	public static ArrayList<AD_Bean> get_IPAD_AD_List(String adids)
	{
		String adid_array[] = null;
		if (adids != null)
		{
			adid_array = adids.split(",");
		}
		ArrayList<AD_Bean> list = new ArrayList<AD_Bean>();
		
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE " +
				" `platform` = 'ipad2.0' " +
				" ORDER BY `start_time` DESC,`timestamp` DESC;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			OUT:while (rs.next())
			{
				AD_Bean bean = new AD_Bean();
				
				String ad_id = rs.getString("id");
				
				if (adid_array != null && adid_array.length > 0)
				{
					for (String id : adid_array)
					{
						if (id.equals(ad_id))
						{
							continue OUT;
						}
					}
				}
				
				bean.setId(ad_id);
				bean.setTitle(rs.getString("ad_title"));
				bean.setDescription(rs.getString("description"));
				bean.setUpdatetime(CodeUtil.getDateTimeStr(rs.getLong("timestamp")));
				bean.setType(rs.getString("ad_type"));
				bean.setImg(rs.getString("img"));
				
				String content = rs.getString("ad_content");
				bean.setContent(content);
				
				if (content != null && !"".equals(content))
				{
					JSONObject json = new JSONObject(content);
					if (json.has("img1"))
					{
						bean.setImg1(json.getString("img1"));
					}
					if (json.has("img2"))
					{
						bean.setImg2(json.getString("img2"));
					}
				}
				
				bean.setActionType(rs.getString("action_type"));
				bean.setActionValue(rs.getString("action_value"));
				bean.setPlatform(rs.getString("platform"));
				bean.setStart_time(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEnd_time(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setRank(rs.getInt("rank"));
				
				Pattern pattern = Pattern.compile(serverHost);
				Matcher m_content = pattern.matcher(bean.getActionValue());
				String urlKey = "";
				if(m_content.find()) 
				{
					urlKey =bean.getActionValue().replaceAll(serverHost, "");
				}
				bean.setUrlkey(urlKey);
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.get_IPAD_AD_List error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * 管理员获取已经在使用的IPAD广告列表
	 * @param adids
	 * @return
	 */
	public static ArrayList<AD_Bean> get_used_IPAD_AD_List(String adids)
	{
		ArrayList<AD_Bean> list = new ArrayList<AD_Bean>();
		
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			for(String adid : adids.split(","))
			{
				if (adid == null || adid.equals(""))
				{
					continue;
				}
				ps = conn.prepareStatement(sql);
				ps.setString(1, adid);
				
				rs = ps.executeQuery();
				if (rs.next())
				{
					AD_Bean bean = new AD_Bean();
					
					bean.setId(rs.getString("id"));
					bean.setTitle(rs.getString("ad_title"));
					bean.setDescription(rs.getString("description"));
					bean.setUpdatetime(CodeUtil.getDateTimeStr(rs.getLong("timestamp")));
					bean.setType(rs.getString("ad_type"));
					bean.setImg(rs.getString("img"));
					
					String content = rs.getString("ad_content");
					bean.setContent(content);
					
					if (content != null && !"".equals(content))
					{
						JSONObject json = new JSONObject(content);
						if (json.has("img1"))
						{
							bean.setImg1(json.getString("img1"));
						}
						if (json.has("img2"))
						{
							bean.setImg2(json.getString("img2"));
						}
					}
					
					bean.setActionType(rs.getString("action_type"));
					bean.setActionValue(rs.getString("action_value"));
					bean.setPlatform(rs.getString("platform"));
					bean.setStart_time(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
					bean.setEnd_time(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
					bean.setRank(rs.getInt("rank"));
					
					Pattern pattern = Pattern.compile(serverHost);
					Matcher m_content = pattern.matcher(bean.getActionValue());
					String urlKey = "";
					if(m_content.find()) 
					{
						urlKey =bean.getActionValue().replaceAll(serverHost, "");
					}
					bean.setUrlkey(urlKey);
					
					list.add(bean);
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.get_used_IPAD_AD_List error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * 管理员获取IPAD广告列表
	 * @return
	 */
	public static ArrayList<IPAD_Ad_Bean> getIPAD_ADList()
	{
		ArrayList<IPAD_Ad_Bean> list = new ArrayList<IPAD_Ad_Bean>();
		
		String sql = "SELECT * FROM `youhui_datamining`.`label_ipad` WHERE `status` = 1 ORDER BY `date`;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				IPAD_Ad_Bean bean = new IPAD_Ad_Bean();
				
				bean.setId(rs.getInt("id"));
				bean.setAdids(rs.getString("adids"));
				bean.setStyle(rs.getString("style"));
				bean.setRow(rs.getInt("row"));
				bean.setCol(rs.getInt("col"));
				bean.setWidth(rs.getInt("width"));
				bean.setHeight(rs.getInt("height"));
				bean.setStatus(rs.getString("status"));
				bean.setDate(rs.getString("date"));
				bean.setClassnames(rs.getString("classnames"));
				bean.setBgcolor(rs.getString("bgcolor"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.getIPAD_ADList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * 管理员获取IPAD广告实体
	 * @param ad_id
	 * @return
	 */
	public static IPAD_Ad_Bean get_IPAD_AD_BEAN(String ad_id)
	{
		IPAD_Ad_Bean bean = null;
		
		String sql = "SELECT * FROM `youhui_datamining`.`label_ipad` WHERE `id` = " + ad_id + ";";
		
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
				bean = new IPAD_Ad_Bean();
				
				bean.setId(rs.getInt("id"));
				bean.setAdids(rs.getString("adids"));
				bean.setStyle(rs.getString("style"));
				bean.setRow(rs.getInt("row"));
				bean.setCol(rs.getInt("col"));
				bean.setWidth(rs.getInt("width"));
				bean.setHeight(rs.getInt("height"));
				bean.setStatus(rs.getString("status"));
				bean.setDate(rs.getString("date"));
				bean.setClassnames(rs.getString("classnames"));
				bean.setBgcolor(rs.getString("bgcolor"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.get_IPAD_AD_BEAN error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return bean;
	}

	/**
	 * 删除广告(更改状态)
	 * @return
	 */
	public static boolean deleteAD(String id)
	{
		boolean flag = false;
		
		String sql = "DELETE FROM `youhui_datamining`.`tyh_ads` WHERE `id` = '" + id + "';";
		
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
			logger.error("Admin_AD_DAO.deleteAD error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 删除IPAD广告(更改状态)
	 * @return
	 */
	public static boolean delete_ipad_AD(String ipad_ad_id)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`label_ipad` SET `status` = 2 WHERE `id` = '" + ipad_ad_id + "';";
		
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
			logger.error("Admin_AD_DAO.delete_ipad_AD error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 更新IPAD广告
	 * @return
	 */
	public static boolean update_ipad_AD(String ipad_ad_id, String ad_ids, String bgcolor, String ipad_ad_style)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`label_ipad` SET `adids` = ?, `style` = ?, `bgcolor` = ? WHERE `id` = ? AND `status` = 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ad_ids);
			ps.setString(2, ipad_ad_style);
			ps.setString(3, bgcolor);
			ps.setString(4, ipad_ad_id);
			
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_AD_DAO.update_ipad_AD error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 更新IPAD广告顺序
	 * @return
	 */
	public static boolean update_ipad_AD_rank(String ipad_ad_id, String ad_ids)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`label_ipad` SET `adids` = ? WHERE `id` = ? AND `status` = 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ad_ids);
			ps.setString(2, ipad_ad_id);
			
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_AD_DAO.update_ipad_AD_rank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 新增IPAD广告
	 * @return
	 */
	public static boolean add_ipad_AD(IPAD_Ad_Bean ipad_ad_bean)
	{
		boolean flag = false;
		
		String sql = "INSERT INTO `youhui_datamining`.`label_ipad` " +
				" (`adids`,`style`,`classnames`,`bgcolor`,`row`,`col`,`width`,`height`,`status`,`date`) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?);";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ipad_ad_bean.getAdids());
			ps.setString(2, ipad_ad_bean.getStyle());
			ps.setString(3, ipad_ad_bean.getClassnames());
			ps.setString(4, ipad_ad_bean.getBgcolor());
			ps.setInt(5, ipad_ad_bean.getRow());
			ps.setInt(6, ipad_ad_bean.getCol());
			ps.setInt(7, ipad_ad_bean.getWidth());
			ps.setInt(8, ipad_ad_bean.getHeight());
			ps.setString(9, ipad_ad_bean.getStatus());
			ps.setString(10, CodeUtil.getDateTimeStr());
			
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_AD_DAO.add_ipad_AD error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 更新广告
	 * @return
	 */
	public static boolean updateAD(AD_Bean bean)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`tyh_ads` SET `ad_type`=?,`ad_content`=?,`start_time`=?,`end_time`=?,`timestamp`=?, " +
				" `ad_title`=?,`platform`=?,`description`=?,`img`=?,`action_type`=?,`action_value`=? " +
				" WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getType());
			ps.setString(2, bean.getContent());
			ps.setString(3, bean.getStart_time());
			ps.setString(4, bean.getEnd_time());
			ps.setString(5, bean.getUpdatetime());
			ps.setString(6, bean.getTitle());
			ps.setString(7, bean.getPlatform());
			ps.setString(8, bean.getDescription());
			ps.setString(9, bean.getImg());
			ps.setString(10, bean.getActionType());
			ps.setString(11, bean.getActionValue());
			ps.setInt(12, Integer.parseInt(bean.getId()));
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.updateAD error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 新增广告
	 * @return
	 */
	public static boolean addAD(AD_Bean bean)
	{
		boolean flag = false;
		
		String sql1 = "SELECT min(rank) as mrank FROM `youhui_datamining`.`tyh_ads`;";
		
		String sql2 = "INSERT INTO `youhui_datamining`.`tyh_ads`(`ad_type`,`ad_content`,`start_time`,`end_time`,`timestamp`,`ad_title`,`platform`,`description`,`img`,`action_type`,`action_value`,`id`,`rank`) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			// 获取最靠前的排名
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			int rank = 0;
			if (rs.next())
			{
				rank = rs.getInt("mrank");
			}
			rank--;
			bean.setRank(rank);
			
			ps = conn.prepareStatement(sql2);
			ps.setString(1, bean.getType());
			ps.setString(2, bean.getContent());
			ps.setString(3, bean.getStart_time());
			ps.setString(4, bean.getEnd_time());
			ps.setString(5, bean.getUpdatetime());
			ps.setString(6, bean.getTitle());
			ps.setString(7, bean.getPlatform());
			ps.setString(8, bean.getDescription());
			ps.setString(9, bean.getImg());
			ps.setString(10, bean.getActionType());
			ps.setString(11, bean.getActionValue());
			ps.setInt(12, Integer.parseInt(bean.getId()));
			ps.setInt(13, rank);
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.addAD error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return flag;
	}
	
	/**
	 * 获取广告表的下一个ID
	 * @return
	 */
	public static String getADNextID()
	{
		String id = null;
		
		String sql = "SELECT max(id) AS maxid FROM `youhui_datamining`.`tyh_ads`;";
		
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
			logger.error("Admin_AD_DAO.getADNextID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return id;
	}
	
	/**
	 * 获取前一个广告
	 * @return
	 */
	public static AD_Bean getPreAD(String status,String platform,String position)
	{
		AD_Bean bean = null;
		long nowTime =System.currentTimeMillis();
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE 1=1 ";
		if(platform!=null&&!"".equals(platform))
		{
			sql+=" and `platform`= '"+platform+"'";
		}
		if (status.equals("1"))// 尚未开始
		{
			sql = sql + " AND `start_time` > " + nowTime + " ";
		}
		else if (status.equals("2"))// 正在进行
		{
			sql = sql + " AND `start_time` < " + nowTime + " AND `end_time` > " + nowTime;
		}
		else if (status.equals("3"))// 已结束
		{
			sql = sql + " AND `end_time` < " + nowTime + " ";
		}
		
		sql+=" and  `rank` < ? order by `rank` desc limit 1;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, position);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new AD_Bean();
				
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("ad_title"));
				bean.setDescription(rs.getString("description"));
				bean.setUpdatetime(CodeUtil.getDateTimeStr(rs.getLong("timestamp")));
				bean.setType(rs.getString("ad_type"));
				bean.setImg(rs.getString("img"));
				
				String content = rs.getString("ad_content");
				bean.setContent(content);
				
				if (content != null && !"".equals(content))
				{
					JSONObject json = new JSONObject(content);
					if (json.has("img1"))
					{
						bean.setImg1(json.getString("img1"));
					}
					if (json.has("img2"))
					{
						bean.setImg2(json.getString("img2"));
					}
				}
				
				bean.setActionType(rs.getString("action_type"));
				bean.setActionValue(rs.getString("action_value"));
				bean.setPlatform(rs.getString("platform"));
				bean.setStart_time(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEnd_time(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setRank(rs.getInt("rank"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.getPreAD error:", e);
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
	public static AD_Bean getNextAD(String status,String platform,String position)
	{
		AD_Bean bean = null;
		long nowTime = System.currentTimeMillis();
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE 1=1";
		if(platform!=null&&!"".equals(platform))
		{
			sql+=" and `platform`= '"+platform+"'";
		}
		if (status.equals("1"))// 尚未开始
		{
			sql = sql + " AND `start_time` > " + nowTime + " ";
		}
		else if (status.equals("2"))// 正在进行
		{
			sql = sql + " AND `start_time` < " + nowTime + " AND `end_time` > " + nowTime;
		}
		else if (status.equals("3"))// 已结束
		{
			sql = sql + " AND `end_time` < " + nowTime + " ";
		}
		sql+=" and  `rank` > ? order by `rank` asc limit 1;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, position);

			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new AD_Bean();
				
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("ad_title"));
				bean.setDescription(rs.getString("description"));
				bean.setUpdatetime(CodeUtil.getDateTimeStr(rs.getLong("timestamp")));
				bean.setType(rs.getString("ad_type"));
				bean.setImg(rs.getString("img"));
				
				String content = rs.getString("ad_content");
				bean.setContent(content);
				
				if (content != null && !"".equals(content))
				{
					JSONObject json = new JSONObject(content);
					if (json.has("img1"))
					{
						bean.setImg1(json.getString("img1"));
					}
					if (json.has("img2"))
					{
						bean.setImg2(json.getString("img2"));
					}
				}
				
				bean.setActionType(rs.getString("action_type"));
				bean.setActionValue(rs.getString("action_value"));
				bean.setPlatform(rs.getString("platform"));
				bean.setStart_time(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEnd_time(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setRank(rs.getInt("rank"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.getNextAD error:", e);
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
		
		
		String sql = "SELECT max(rank) AS maxid FROM `youhui_datamining`.`tyh_ads` ;";
		
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
			logger.error("Admin_AD_DAO.getMaxRank error:", e);
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
		
		
		String sql = "SELECT min(rank) AS maxid FROM `youhui_datamining`.`tyh_ads`;";
		
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
				minRank = rs.getInt("maxid")-1;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AD_DAO.getMinRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return minRank;
	}
	
	/**
	 * 修改rank
	 * @param id
	 * @param rank
	 * @return
	 */
	public static boolean updateRank(String id,int rank)
	{
		boolean flag = false;
		
		String sql = "update `youhui_datamining`.`tyh_ads` set `rank` =?  WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rank);
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
			logger.error("Admin_AD_DAO.updateRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
}
