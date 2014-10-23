package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.KeyCategoryBean;
import com.netting.bean.KeyCategoryBean.KeywordBean;
import com.netting.bean.MixStylePageBean;
import com.netting.bean.TagToItemBean;
import com.netting.bean.TeJiaGoodsBean;
import com.netting.bean.TopTradeItem;
import com.netting.cache.dao.Admin_Tag_Item_Cache_DAO;
import com.netting.db.DataSourceFactory;


/**
 * 标签下商品数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_Tag_Item_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_Tag_Item_DAO.class);
	
	private static int pageSize = 20;
	
	/**
	 * 获取商品
	 * @param tag_id
	 * @return
	 */
	public static TeJiaGoodsBean getTagItemBean(String item_id)
	{
		TeJiaGoodsBean bean = null;
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` where `item_id`=?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, item_id);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new TeJiaGoodsBean();
				
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setStatus(rs.getInt("status"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setCommission(rs.getString("commission"));
				bean.setCommission_rate(rs.getString("commission_rate"));
				bean.setRank(rs.getInt("rank"));
				String update_time =rs.getString("update_time");
				if(update_time != null && !"".equals(update_time))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bean.setUpdate_time(sdf.format(new Date(Long.valueOf(update_time))));
				}
				bean.setClickURL(rs.getString("click_url"));
				bean.setRate(rs.getDouble("jfb_rate"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getTagItemBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 管理员获取标签下商品列表
	 * @param page
	 * @param status
	 * @param keyword
	 * @param ispad
	 * @return
	 */
	public static ArrayList<TeJiaGoodsBean> getTagItemList(int page, String tag_id)
	{
		DecimalFormat df = new DecimalFormat("#0.00");
		
		ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		if (page < 0) 
		{
			page = 1;
		}
		if (tag_id == null || tag_id.equals("") || tag_id.equals("null")) 
		{
			return list;
		}
		
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` a INNER JOIN `youhui_datamining`.`m_tagtoitem` b " +
				" ON b.`tag_id` = ? AND a.`item_id` = b.`item_id` and a.`status` = 0 and b.`rel_status` = 1 order by `rank` ASC limit ?,?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(tag_id));
			ps.setInt(2, pageSize * (page - 1));
			ps.setInt(3, pageSize);	
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				String discount = rs.getString("discount");
				if (discount == null || discount.equals(""))
				{
					bean.setDiscount("");
				}
				else
				{
					try
					{
						bean.setDiscount(df.format(Double.parseDouble(discount)));
					}
					catch (NumberFormatException ne)
					{
						bean.setDiscount("");
					}
				}
				bean.setPic_url(rs.getString("pic_url"));
				bean.setStatus(rs.getInt("status"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setCommission(rs.getString("commission"));
				String commission_rate_str = rs.getString("commission_rate");
				if (null == commission_rate_str || "null".equals(commission_rate_str) || "".equals(commission_rate_str))
				{
					bean.setCommission_rate("0");
				}
				else
				{
					bean.setCommission_rate(String.valueOf(Double.parseDouble(commission_rate_str) / 100));
				}
				
				bean.setRank(rs.getInt("rank"));
				String update_time =rs.getString("update_time");
				if(update_time != null && !"".equals(update_time))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bean.setUpdate_time(sdf.format(new Date(Long.valueOf(update_time))));
				}
				bean.setClickURL(rs.getString("click_url"));
				
				bean.setRate(rs.getDouble("jfb_rate"));
				
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getTagItemList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * 管理员获取标签商品列表页面数
	 * @param status
	 * @param keyword
	 * @param ispad
	 * @return
	 */
	public static int getTagItemListTotal(String tag_id) 
	{
		int totalPage = 0;
		
		String sql = "SELECT count(*) as count FROM  `youhui_datamining`.`m_tagtoitem` where `tag_id` = ? ;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(tag_id));
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
			logger.error("Admin_Tag_Item_DAO.getTagItemListTotal error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return totalPage;
	}

	/**
	 * 获取子标签
	 * @param tagId
	 * @return
	 */
	public static List<KeywordBean> getKeywords(String tagId) 
	{
		List<KeywordBean> list = new ArrayList<KeywordBean>();
		
		ResultSet rs = null;
		Connection conn = null;
		Statement s = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '" + tagId + "'  and `client_show`=1;";
			rs = s.executeQuery(sql);
			while (rs.next()) 
			{
				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setIcon(rs.getString("icon"));
				bean.setSex(rs.getInt("sex"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setItemids(getItemids(rs.getString("id"), conn));
				list.add(bean);
			}
		}
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getKeywords error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, s, conn);
		}
		return list;
	}
	
	public static List<KeywordBean> getKeywordsIsShow(String tagId,boolean isShowHide) 
	{
		List<KeywordBean> list = new ArrayList<KeywordBean>();
		String ishide = "";
		if(!isShowHide){
   			ishide = " and (`client_show` <> 3)";        //是否显示删除的
		}
		ResultSet rs = null;
		Connection conn = null;
		Statement s = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '" + tagId + "' "+ ishide +"ORDER BY `rank` ASC";
			rs = s.executeQuery(sql);
			while (rs.next()) 
			{
				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setIcon(rs.getString("icon"));
				bean.setSex(rs.getInt("sex"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setItemids(getItemids(rs.getString("id"), conn));
				list.add(bean);
			}
		}
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getKeywords error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, s, conn);
		}
		return list;
	}
	
	public static String getCtagids(String tagId) 
	{
		ResultSet rs = null;
		Connection conn = null;
		Statement s = null;
		String ctagids = "";
		try
		{
			conn = DataSourceFactory.getConnection();
			s = conn.createStatement();
			String sql = "SELECT * FROM youhui_datamining.m_discount_keywords where parent_id ='"+tagId+"';";
			rs = s.executeQuery(sql);
			while(rs.next()){
				ctagids = ctagids +rs.getString("id")+",";
			}
		}
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getCtagids error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, s, conn);
		}
		return ctagids;
	}
	
	public static String getItemids(String tagId ,Connection conn) 
	{
		String ret = "";
		try 
		{
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_tagtoitem` WHERE `tag_id` = '"+ tagId + "' AND `rel_status` = 1;";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) 
			{
				ret += rs.getString("item_id") + ",";
			}
		} 
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getItemids error:", e);
		}
		return ret;
	}
	
	/**
	 * 获取商品分类的集分宝比例
	 * @param catID
	 * @return
	 */
	public static double getCatRate(String catID) 
	{
		if (catID == null || catID.equals(""))
		{
			return 0;
		}
		double ret = 0;
		
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			s = conn.createStatement();
			
			String sql = "SELECT `rate` FROM `taobao_cat`.`cat` WHERE `cat_id` = "+ catID + ";";
			rs = s.executeQuery(sql);
			if (rs.next()) 
			{
				ret = rs.getDouble("rate");
			}
		} 
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getCatRate error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(rs, s, conn);
		}
		return ret;
	}
	
	/**
	 * 获取商品分类在亲包邮的子标签分类
	 * @param catID
	 * @return
	 */
	public static String getSonTagID(String catID) 
	{
		String sonTagID = "635";
		if (catID == null || catID.equals(""))
		{
			return sonTagID;
		}
		
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			s = conn.createStatement();
			
			String sql = "SELECT `tag_id` FROM `taobao_cat`.`cat_tag` WHERE `cat_id` = "+ catID + ";";
			rs = s.executeQuery(sql);
			if (rs.next()) 
			{
				sonTagID = rs.getString("tag_id");
			}
		} 
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getSonTagID error:", e);
			sonTagID = "635";
		}
		finally 
		{
			DataSourceFactory.closeAll(rs, s, conn);
		}
		return sonTagID;
	}
	
	/**
	 * 删除标签与商品的归属关系
	 * @return
	 */
	public static boolean delItem2Tag(String item_id, String tag_id)
	{
		boolean flag = false;
		
		String sql1 = "UPDATE `youhui_datamining`.`m_tagtoitem` SET `rel_status` = 2 WHERE `item_id` = '" + item_id + "' AND `tag_id` = '" + tag_id + "'";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql1);
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_Tag_Item_DAO.delItem2Tag error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 删除商品(更改商品的状态)
	 * @return
	 */
	public static boolean delItem(String item_id)
	{
		boolean flag = false;
		
		String sql1 = "UPDATE `youhui_datamining`.`m_tagtoitem` SET `rel_status` = 2 WHERE `item_id` = '" + item_id + "';";
		
		String sql2 = "UPDATE `youhui_datamining`.`m_discount_products` SET `status` = 1 WHERE `item_id` = '" + item_id + "';";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql1);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sql2);
			ps.executeUpdate();
			
			flag = true;
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_Tag_Item_DAO.delTagItem error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 删除569+UID商品(更改商品的状态)
	 * @return
	 */
	public static boolean del569Item(String tagID)
	{
		boolean flag = false;
		
		String sql1 = "UPDATE `youhui_datamining`.`m_tagtoitem` SET `rel_status` = 2 WHERE `tag_id` = '" + tagID + "';";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql1);
			ps.executeUpdate();
			
			flag = true;
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_Tag_Item_DAO.del569Item error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 添加标签与商品的归属关系(569亲包邮数据专用)
	 * @param item_id
	 * @param tag_id
	 * @return
	 */
	public static int addItem2Tag569(String item_id, String tag_id)
	{
		int rank = 0;
		if(tag_id == null || "".equals(tag_id) || item_id == null || "".equals(item_id))
		{
			return rank;
		}
		String sql = "";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			rank = getMinRank(tag_id, conn);
			
			String sql1 = "select `id` from `youhui_datamining`.`m_tagtoitem` where `tag_id`= "+ tag_id +" and `item_id`= "+ item_id +" ;";
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			if (rs.next())
			{
				String id = rs.getString("id");
				sql = "update `youhui_datamining`.`m_tagtoitem` set `rel_status` = 1,`rank`= "+ rank +" where `id`= "+ id +";";
			}
			else
			{
			    sql = "INSERT INTO `youhui_datamining`.`m_tagtoitem`(`tag_id`,`item_id`,`rank`) values ("+tag_id+","+ item_id +","+rank +");";
			}
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.addItem2Tag569 error:", e);
		} 
		finally
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return rank;
	}
	
	/**
	 * 添加标签与商品的归属关系
	 * @param item_id
	 * @param tag_id
	 * @return
	 */
	public static int addItem2Tag(String item_id, String tag_id)
	{
		int rank = 0;
		if(tag_id == null || "".equals(tag_id) || item_id == null||"".equals(item_id))
		{
			return rank;
		}
		String flag = isLocked(tag_id);	//查询该标签下锁定的商品个数
		
		String sql = "";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
//			rank = getMinRank(tag_id, conn);
			//从redis或者最小rank
			rank = Admin_Tag_Item_Cache_DAO.getSmallRank(tag_id);
			
			List<TagToItemBean> list = null;
			if(Integer.parseInt(flag) > 0 && Integer.parseInt(flag) <= 20) 
			{
				sql = "select * from `youhui_datamining`.`m_tagtoitem` where `tag_id`= "+ tag_id +" ";//查出前?
				sql = sql +" AND `rel_status` = 1 order by rank asc limit " + flag + ";";
				
				list = new ArrayList<TagToItemBean>();
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while (rs.next())
				{
					TagToItemBean bean = new TagToItemBean();
					
					bean.setId(rs.getString("id"));
					bean.setItemid(rs.getString("item_id"));
					bean.setRank(rs.getInt("rank"));
					bean.setTagid(tag_id);
					
					list.add(bean);
				}
			}
			
			String sql1 = "select `id` from `youhui_datamining`.`m_tagtoitem` where `tag_id`= "+ tag_id +" and `item_id`= "+ item_id +" ;";
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			if (rs.next())
			{
				String id = rs.getString("id");
				sql = "update `youhui_datamining`.`m_tagtoitem` set `rel_status` = 1,`rank`= "+ rank +" where `id`= "+ id +";";
			}
			else
			{
			    sql = "INSERT INTO `youhui_datamining`.`m_tagtoitem`(`tag_id`,`item_id`,`rank`) values ("+tag_id+","+ item_id +","+rank +");";
			}
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			if(list != null && list.size() > 0) //修改前?条数据rank
			{	
				int rank1 = rank - 1;
				for(int i = list.size() - 1; i >= 0; i--) 
				{
					sql = "update `youhui_datamining`.`m_tagtoitem` set `rank`= "+ rank1 +" where `id`= "+ list.get(i).getId() +" ;";
					rank1--;
					ps = conn.prepareStatement(sql);
					ps.executeUpdate();
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.addItem2Tag error:", e);
		} 
		finally
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return rank;
	}
	
	/**
	 * 子标签增加
	 * @param tagid
	 * @param itemid
	 * @param rank
	 * @return
	 */
	public static String addTagtoItem(String tagid, String itemid, String rank)
	{   
//		boolean flag = false;
		String minRank = "";
		if(tagid == null || "".equals(tagid))
		{
			return "";
		}
		
		String check_sql = "select `id` from `youhui_datamining`.`m_tagtoitem` where `tag_id` = ? and `item_id` = ?;";
		
		String insert_sql = "INSERT INTO `youhui_datamining`.`m_tagtoitem`(`tag_id`,`item_id`,`rank`) values("+tagid+","+ itemid +","+rank +");";
		
		String update_sql = "UPDATE `youhui_datamining`.`m_tagtoitem` SET `rel_status` = 1,`rank` = ? WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			rank = getMinRank(tagid, conn)+"";
			ps = conn.prepareStatement(check_sql);
			
			ps.setString(1, tagid);
			ps.setString(2, itemid);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				int id = rs.getInt("id");
				ps = conn.prepareStatement(update_sql);
				
				ps.setString(1, rank);
				ps.setInt(2, id);
				
				ps.executeUpdate();
			}
			else
			{
				ps = conn.prepareStatement(insert_sql);
				ps.executeUpdate();
			}
			minRank = rank;
		} 
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.addTagtoItem error:", e);
			minRank = "";
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return minRank;
	}
	
	/**
	 * 子标签删除
	 * @param tagid
	 * @param itemid
	 * @return
	 */
	public static boolean delTagtoItem(String tagid, String itemid)
	{       
		boolean result = false;
		if(tagid == null || "".equals(tagid))
		{
			return result;
		}
		
		String sql = "UPDATE `youhui_datamining`.`m_tagtoitem` SET `rel_status` = 2 where `item_id`= "+ itemid + " and `tag_id`= "+ tagid +";";
		
		Connection conn = null;
		Statement s = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			s = conn.createStatement();
			
			int i = s.executeUpdate(sql);
			if(i > 0) 
			{
				result = true;
			}
		} 
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.delTagtoItem error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return result;
	}
	
	/**
	 * 添加商品信息
	 * @param bean
	 * @return
	 */
	public static boolean addItem(TeJiaGoodsBean bean)
	{  
		boolean flag = false;
		
		String sql = "select * from `youhui_datamining`.`m_discount_products` where `item_id`= "+ bean.getItem_id();
		
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		try 
		{ 
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			rs = s.executeQuery(sql);
			if(rs.next())
			{
				sql = "update `youhui_datamining`.`m_discount_products` set `catid`='" + bean.getCatID() + "',`jfb_rate`=" + bean.getRate() + ",`status` = 0 ,`title`= '"+bean.getTitle()+"',"
					+ "`show_index`="+ bean.getShow_index() +",`price_high`= '"+bean.getPrice_high()+"',`price_low`='"+bean.getPrice_low()
					+ "',`discount`='"+ bean.getDiscount() +"',`pic_url`='"+bean.getPic_url()+ "',`click_url`='"+bean.getClickURL() 
					+ "',`commission`='"+bean.getCommission()+"',`commission_rate`='"+bean.getCommission_rate()+"',`update_time`='"
					+ System.currentTimeMillis() +"', `keyword`= '"+bean.getKeyword()+"', `width_m`= '"+bean.getWidth_m()+"', `height_m`= '"+bean.getHeight_m()
					+"',   `width_310`= '"+bean.getWidth_310()+"', `height_310`= '"
					+bean.getHeight_310()+"', `width_170`= '"+bean.getWidth_170()+"', `height_170`= '"+bean.getHeight_170()+"' ,`baoyou`='"+bean.getBaoyou()+"'  where `item_id`='"+bean.getItem_id()+"';";
				int i = s.executeUpdate(sql);
				if(i > 0) 
				{
					flag = true;
				}
			}
			else
			{
				sql = "INSERT INTO `youhui_datamining`.`m_discount_products`"
					+ "(`item_id`,`title`,`keyword`,"
					+ "`show_index`,`price_high`,`price_low`"
					+ ",`discount`,`pic_url`,`status`"
					+ ",`click_url`,`update_time`,`commission`,`commission_rate`,`width_m`,`height_m`,`width_b`,`height_b`,`width_310`,`height_310`,`width_170`,`height_170`,`jfb_rate`,`catid`,`baoyou`)VALUES('"
					+ bean.getItem_id()
					+ "','"
					+ bean.getTitle()
					+ "','"
					+ bean.getKeyword()
					+ "','"
					+ bean.getShow_index()
					+ "','"
					+ bean.getPrice_high()
					+ "','"
					+ bean.getPrice_low()
					+ "','"
					+ bean.getDiscount()
					+ "','"
					+ bean.getPic_url()
					+ "','"
					+ 0
					+ "','"
					+ bean.getClickURL()
					+ "','"
					+ System.currentTimeMillis()
					+ "','"
					+ bean.getCommission()
					+ "','"
					+ bean.getCommission_rate()
					+ "','"
					+ bean.getWidth_m()
					+ "','"
					+ bean.getHeight_m()
						+ "','"
					+ bean.getWidth_b()
					+ "','"
					+ bean.getHeight_b()
						+ "','"
					+ bean.getWidth_310()
					+ "','"
					+ bean.getHeight_310()
						+ "','"
					+ bean.getWidth_170()
					+ "','"
					+ bean.getHeight_170()
					+ "','"
					+ bean.getRate()
					+ "','"
					+ bean.getCatID()
					+ "','"
					+ bean.getBaoyou()
					+ "');";
				int i = s.executeUpdate(sql);
				if(i > 0) 
				{
					flag = true;
				}
			}
			
		}
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.addItem error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(rs, s, conn);
		}
		return flag;
	}
	
	
	/**
	 * 查询标签的封面商品
	 * @param keyword
	 * @return
	 */
	public static TeJiaGoodsBean getTagFaceItem(String tag_name)
	{
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` WHERE `keyword` = '"
				+ tag_name + "' AND `show_index` = '1';";
		
		TeJiaGoodsBean bean = null;
		
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
				bean = new TeJiaGoodsBean();
				
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setStatus(rs.getInt("status"));
				bean.setClickURL(rs.getString("click_url"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getTagFaceItem error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 根据标签ID获取有效商品列表(全部)
	 * @param tag
	 * @return
	 */
	public static ArrayList<TeJiaGoodsBean> getTagItemByTagID(String tag_id)
	{
		ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		
		String sql = "select * from `youhui_datamining`.`m_discount_products` join (SELECT * FROM `youhui_datamining`.`m_tagtoitem` where `tag_id` = ? AND `rel_status` = 1 order by `rank`) as t " +
				"where t.`item_id` = `m_discount_products`.`item_id`;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tag_id);
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setStatus(rs.getInt("status"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setCommission(rs.getString("commission"));
				bean.setCommission_rate(rs.getString("commission_rate"));
				bean.setRank(rs.getInt("rank"));
				String update_time =rs.getString("update_time");
				if(update_time != null && !"".equals(update_time))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bean.setUpdate_time(sdf.format(new Date(Long.valueOf(update_time))));
				}
				bean.setClickURL(rs.getString("click_url"));
				bean.setRate(rs.getDouble("jfb_rate"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getTagItemByTagID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	public static ArrayList<TeJiaGoodsBean> getTagItemByTagID(String tag_id,int page)
	{
		ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		if(page <= 0) page = 1;
		
		String sql = "SELECT * FROM youhui_datamining.m_discount_products order by update_time desc limit ?,? ;";
		String sql1 = "SELECT distinct(item_id) FROM youhui_datamining.m_mix_products where status=1;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			List<String> itemList = new ArrayList<String>();
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			while(rs.next()){
				itemList.add(rs.getString("item_id"));
			}
			
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page - 1) * 40);
			ps.setInt(2, 40);
			
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				if(itemList != null && itemList.size() >0){
					if(itemList.contains(rs.getString("item_id")))
						continue;
				}
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setStatus(rs.getInt("status"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setCommission(rs.getString("commission"));
				bean.setCommission_rate(rs.getString("commission_rate"));
//				bean.setRank(rs.getInt("rank"));
				String update_time =rs.getString("update_time");
				if(update_time != null && !"".equals(update_time))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bean.setUpdate_time(sdf.format(new Date(Long.valueOf(update_time))));
				}
				bean.setClickURL(rs.getString("click_url"));
				bean.setRate(rs.getDouble("jfb_rate"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getTagItemByTagID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	public static ArrayList<TeJiaGoodsBean> getTagListItem(String tagId,int page){
		ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		if(page <= 0) page = 1;
		
		String sql = "SELECT * FROM youhui_datamining.m_discount_products a,youhui_datamining.m_tagtoitem b where b.tag_id=? and a.item_id=b.item_id order by b.rank asc  limit ?,? ;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();			
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagId);
			ps.setInt(2, (page - 1) * 40);
			ps.setInt(3, 40);
			
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setStatus(rs.getInt("status"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setCommission(rs.getString("commission"));
				bean.setCommission_rate(rs.getString("commission_rate"));
//				bean.setRank(rs.getInt("rank"));
				String update_time =rs.getString("update_time");
				if(update_time != null && !"".equals(update_time))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					bean.setUpdate_time(sdf.format(new Date(Long.valueOf(update_time))));
				}
				bean.setClickURL(rs.getString("click_url"));
				bean.setRate(rs.getDouble("jfb_rate"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getTagItemByTagID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	public static int getTagListItemPage(String tagId){
		int count = 0;
		String sql = "SELECT count(*) as c FROM youhui_datamining.m_tagtoitem where tag_id=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagId);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				count = rs.getInt("c");
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getTagItemByTagIDPage error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		if(count % 40 == 0){
			count = count / 40;
		}else {
			count = count / 40 +1;
		}
		return count;
	}
	
	
	public static int getTagItemByTagIDPage(String tag_id)
	{
		int count = 0;
		
		String sql = "SELECT count(*) as c FROM youhui_datamining.m_discount_products ";
		
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
				count = rs.getInt("c");
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getTagItemByTagIDPage error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		if(count % 40 == 0){
			count = count / 40;
		}else {
			count = count / 40 +1;
		}
		return count;
	}
	
	
	/**
	 * 更新商品价格
	 * @param tag
	 * @return
	 */
	public static boolean updateTagItemPrice(ArrayList<TeJiaGoodsBean> list)
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			st = conn.createStatement();
			for (TeJiaGoodsBean bean : list)
			{
				String sql = "UPDATE `youhui_datamining`.`m_discount_products` SET `price_low` = '"+ bean.getPrice_low() + "', `discount` = '"+ bean.getPrice_low() + "', `update_time`='"
						+ System.currentTimeMillis() +"' WHERE `item_id` = '" + bean.getItem_id() + "';";
				st.addBatch(sql);
			}
			st.executeBatch();
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.updateTagItemPrice error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, st, conn);
		}
		
		return true;
	}
	
	/**
	 * 获取与指定商品ID关联的标签
	 * @param del_ItemId_List
	 * @return
	 */
	public static ArrayList<String> getTagListByItemID(String item_id)
	{
		ArrayList<String> tagid_list = new ArrayList<String>();
		
		String query_sql = "SELECT DISTINCT a.tag_id FROM `youhui_datamining`.`m_tagtoitem` a WHERE a.`rel_status` = 1 AND a.item_id = '" + item_id + "';";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query_sql);
			while (rs.next())
			{
				String tag_id = rs.getString("tag_id");
				if (tag_id != null && !tag_id.equals(""))
				{
					tagid_list.add(tag_id);
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getTagListByItemID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, st, conn);
		}
		
		return tagid_list;
	}
	
	/*
	 * 查询该标签下锁定的商品个数
	 */
	public static String isLocked(String tag_id) 
	{
		String lockstatue = "0";
		if(tag_id == null||"".equals(tag_id)) 
		{
			return "0";
		}
		
		String sql = "SELECT `lockstatue` FROM youhui_datamining.m_discount_keywords where id=?;";
		
		Connection conn = null;
		PreparedStatement prestmt = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			prestmt = conn.prepareStatement(sql);
			
			prestmt.setString(1, tag_id);
			
			rs = prestmt.executeQuery();
			if (rs.next()) 
			{
				lockstatue = rs.getString("lockstatue");
			}
		} 
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.isLocked error:", e);
			return "0";
		}
		finally
		{
			DataSourceFactory.closeAll(rs, prestmt, conn);
		}
		return lockstatue;
	}
	
	/*
	 * 锁定与解锁
	 */
	public static boolean unlock(String tagid,String statue) 
	{
		boolean flag = false;
		if(tagid == null||"".equals(tagid)) 
		{
			return false;
		}
		
		Connection conn = null;
		PreparedStatement prestmt = null;
		String sql = "update youhui_datamining.m_discount_keywords set lockstatue=? where `id`=?;";
		try {
			conn = DataSourceFactory.getConnection();
			prestmt = conn.prepareStatement(sql);
			
			prestmt.setString(1, statue);
			prestmt.setString(2, tagid);
			
			int i = prestmt.executeUpdate();
			if(i>0)
			{
				flag = true;
			}
		} 
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.unlock error:", e);
		}
		finally
		{
			DataSourceFactory.closeAll(null, prestmt, conn);
		}
		return flag;
	}
	
	/**
	 * 获取标签下最小的rank
	 */
	public static int getMinRank(String tag_id, Connection conn) 
	{
		int rank = 0;
		
		String sql = "select min(rank) as mrank from `youhui_datamining`.`m_tagtoitem` where `tag_id` = "+ tag_id +" AND `rel_status` = 1;";
		
		Statement st = null;
		ResultSet rs = null;
		try 
		{
			st = conn.createStatement();	

			rs = st.executeQuery(sql);
			if(rs.next())
			{
				rank = rs.getInt("mrank");
				rank--;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getMinRank error:", e);
			return rank;
		}
		
		return rank;
	}
	
	/**
	 * 获取标签下最小的TagToItemBean
	 * @param tagid
	 * @return
	 */
	public static TagToItemBean getMinRankBean(String tagid) 
	{
		TagToItemBean bean = null;
		
		String sql = "SELECT * FROM `youhui_datamining`.`m_tagtoitem` where `tag_id` = ? AND `rel_status` = 1 order by `rank` asc limit 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagid);
			
			rs = ps.executeQuery();
			if (rs.next()) 
			{
				bean = new TagToItemBean();
				
				bean.setItemid(rs.getString("item_id"));
				bean.setRank(rs.getInt("rank"));
				bean.setTagid(tagid);
			}
		} 
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getMinRankBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return bean;
	}
	
	/**
	 * 获取标签下最大的TagToItemBean
	 * @param tagid
	 * @return
	 */
	public static TagToItemBean getMaxRankBean(String tagid) 
	{
		TagToItemBean bean = null;
		
		String sql = "SELECT * FROM `youhui_datamining`.`m_tagtoitem` where `tag_id` = ? AND `rel_status` = 1 order by `rank` desc limit 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagid);
			
			rs = ps.executeQuery();
			if (rs.next()) 
			{
				bean = new TagToItemBean();
				
				bean.setItemid(rs.getString("item_id"));
				bean.setRank(rs.getInt("rank"));
				bean.setTagid(tagid);
			}
		} 
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.getMaxRankBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return bean;
	}
	
	/**
	 * 获取前一个TagToItemBean
	 * @param tagid
	 * @return
	 */
	public static TagToItemBean getPreRankBean(String rank,String tag_id) 
	{
		TagToItemBean bean = null;
		
		String sql = "SELECT * FROM `youhui_datamining`.`m_tagtoitem` where `rank` > ? AND `tag_id` = ? AND `rel_status` = 1 order by `rank` asc limit 1  ;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, rank);
			ps.setString(2, tag_id);
			
			rs = ps.executeQuery();
			if (rs.next()) 
			{
				bean = new TagToItemBean();
				
				bean.setItemid(rs.getString("item_id"));
				bean.setRank(rs.getInt("rank"));
				bean.setTagid(rs.getString("tag_id"));
			}
		} 
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getPreRankBean error:", e);
		} 
		finally
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return bean;
	}
	
	/**
	 * 获取后一个TagToItemBean
	 * @param tagid
	 * @return
	 */
	public static TagToItemBean getNextRankBean(String rank,String tag_id) 
	{
		TagToItemBean bean = null;
		
		String sql = "SELECT * FROM `youhui_datamining`.`m_tagtoitem` where `rank` < ? and `tag_id` = ? AND `rel_status` = 1 order by `rank` desc limit 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, rank);
			ps.setString(2, tag_id);
			
			rs = ps.executeQuery();
			if (rs.next()) 
			{
				bean = new TagToItemBean();
				
				bean.setItemid(rs.getString("item_id"));
				bean.setRank(rs.getInt("rank"));
				bean.setTagid(rs.getString("tag_id"));
			}
		}
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.getNextRankBean error:", e);
		} 
		finally
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return bean;
	}
	
	public static List<TagToItemBean> getRankItemList(String tagid) 
	{
		List<TagToItemBean> list = new ArrayList<TagToItemBean>();
		
		String flag = Admin_Tag_Item_DAO.isLocked(tagid) ;
		String sql = "select * from `youhui_datamining`.`m_tagtoitem` where `tag_id`= "+ tagid +" AND `rel_status` = 1";//查出前?
		
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			s = conn.createStatement();
			
			if(Integer.parseInt(flag) <= 20 && Integer.parseInt(flag) > 0)
			{
				sql = sql + " order by rank asc limit " + flag + ";";
				
				rs = s.executeQuery(sql);
				
				while(rs.next())
				{
					TagToItemBean bean = new TagToItemBean();
					
					bean.setItemid(rs.getString("item_id"));
					bean.setRank(rs.getInt("rank"));
					bean.setTagid(tagid);
					
					list.add(bean);
				}
			}
		} 
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.getRankItemList error:", e);
		}
		finally
		{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return list;
	}
	
	/**
	 * 交换两个商品的位置
	 * @param tagid
	 * @param itemId0
	 * @param rank0
	 * @param itemId1
	 * @param rank1
	 * @param type
	 * @return
	 */
	public static boolean moveProduct(String tagid, String itemId0, int rank0, String itemId1, int rank1, String type)
	{
		boolean flag = false;
		String sql = "update youhui_datamining.m_tagtoitem set rank = ? where item_id = ? and tag_id = ? AND `rel_status` = 1";
		
		Connection conn = null;
		PreparedStatement ps =null;
		try
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			if(type.equals("3"))//首位
			{	
				ps.setInt(1, rank1-1);
				ps.setString(2, itemId0);
				ps.setString(3, tagid);
				ps.addBatch();
			}
			else if(type.equals("4"))//末尾
			{	
				ps.setInt(1, rank1+1);
				ps.setString(2, itemId0);
				ps.setString(3, tagid);
				ps.addBatch();
			}
			else	//上下交换
			{	
				ps.setInt(1, rank0);
				ps.setString(2, itemId1);
				ps.setString(3, tagid);
				ps.addBatch();
				ps.setInt(1, rank1);
				ps.setString(2, itemId0);
				ps.setString(3, tagid);
				ps.addBatch();
			}
			
			int[] i = ps.executeBatch();
			
			if(i[0] > 0) 
			{
				flag = true;
			}
		}
		catch(SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.moveProduct error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return flag;
	}
	
	public static List<TagToItemBean> getLockedItemList(String tagid)
	{
		List<TagToItemBean> list = new ArrayList<TagToItemBean>();
		
		String flag = isLocked(tagid);
		if(flag.equals("0")) 
		{
			return null;
		}
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			st = conn.createStatement();	
			String sql = "select * from `youhui_datamining`.`m_tagtoitem` where `tag_id`= "+ tagid +" AND `rel_status` = 1 limit "+flag+";";

			rs = st.executeQuery(sql);
			while(rs.next()) 
			{
				TagToItemBean bean = new TagToItemBean();
				
				bean.setItemid(rs.getString("item_id"));
				bean.setRank(rs.getInt("rank"));
				bean.setTagid(tagid);
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getLockedItemList error:", e);
			return null;
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, st, conn);
		}
		return list;
	}
	
	/**
	 * 获取混排标签下商品信息
	 * @param tagid
	 * @param page
	 * @return
	 */
	public static List<MixStylePageBean> getMixStylePageByKeyword(String keyword,String page)
	{
		List<MixStylePageBean> list = new ArrayList<MixStylePageBean>();
		int pageInt = Integer.valueOf(page);
		if(pageInt <= 0) 
		{
			pageInt=1;
		}
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		String sql = "";
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			st = conn.createStatement();	

			if (keyword != null && !keyword.equals("")) 
			{
				sql = "select * from youhui_datamining.m_mix_pagetype  where status=1 and tag_id=(select id from youhui_datamining.m_discount_keywords where keyword='"+keyword+"')  ";
			}
			else
			{
				sql = "select * from youhui_datamining.m_mix_pagetype  where status=1   ";
			}
			sql += " order by rank desc   limit " + (pageInt - 1) * 15 + ",15";
			
			rs = st.executeQuery(sql);
			while(rs.next()) 
			{
				ArrayList<TeJiaGoodsBean> tjlist = getPPSBean(rs.getString("item_ids"));
				
				MixStylePageBean bean = new MixStylePageBean();
				
				bean.setId(rs.getString("id"));
				bean.setTypeid(rs.getString("typeid"));
				bean.setItem_ids(rs.getString("item_ids"));
				bean.setTag_id(rs.getString("tag_id"));
				bean.setRank(rs.getString("rank"));
				bean.setUpdatetime(rs.getString("updatetime"));
				bean.setStatus(rs.getString("status"));
				bean.setTjGoodsBeanList(tjlist);
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getMixStylePageByTagid error:", e);
			return null;
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, st, conn);
		}
		return list;
	}
	
	/**
	 * 根据keyword查询有效数据数
	 * @param keyword
	 * @return
	 */
	public static int getProductPageStyleListSize(String keyword)
	{
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "";
		try 
		{
			conn = DataSourceFactory.getConnection();
			if (keyword != null && !keyword.equals("")) 
			{
				sql = "select count(*) as count from youhui_datamining.m_mix_pagetype where status=1 and tag_id=(select id from youhui_datamining.m_discount_keywords where keyword='"+keyword+"')";
			}
			else 
			{
				sql = "select count(*) as count from youhui_datamining.m_mix_pagetype where status=1";
			}
			rs = conn.createStatement().executeQuery(sql);
			while (rs.next())
			{
				count = rs.getInt("count");
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally
		{
			DataSourceFactory.closeAll(rs, null, conn);
		}
		return count;
	}
	
	/**
	 * 获取混排标签下商品信息
	 * @param tagid
	 * @param page
	 * @return
	 */
	public static List<MixStylePageBean> getMixStylePageByTagid(String tagid,String page)
	{
		List<MixStylePageBean> list = new ArrayList<MixStylePageBean>();
		int pageInt = Integer.valueOf(page);
		if(pageInt<=0) 
		{
			pageInt=1;
		}
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		String sql = "";
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			st = conn.createStatement();	

			if (tagid != null && !tagid.equals("")) 
			{
				sql = "select * from youhui_datamining.m_mix_pagetype  where status=1 and tag_id='"+tagid+"'  ";
			}
			else 
			{
				sql = "select * from youhui_datamining.m_mix_pagetype  where status=1   ";
			}
			sql += " order by rank desc limit " + (pageInt - 1) * 15 + ",15";
			
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				ArrayList<TeJiaGoodsBean> tjlist = getPPSBean(rs.getString("item_ids"));
				
				MixStylePageBean bean = new MixStylePageBean();
				
				bean.setId(rs.getString("id"));
				bean.setTypeid(rs.getString("typeid"));
				bean.setItem_ids(rs.getString("item_ids"));
				bean.setTag_id(rs.getString("tag_id"));
				bean.setRank(rs.getString("rank"));
				bean.setUpdatetime(rs.getString("updatetime"));
				bean.setStatus(rs.getString("status"));
				bean.setTjGoodsBeanList(tjlist);
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getMixStylePageByTagid error:", e);
			return null;
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, st, conn);
		}
		return list;
	}
	
	/**
	 * 混排标签下的数量
	 * @param tag_id
	 * @return
	 */
	public static int getProductPageStyleListSizeByTagid(String tag_id) 
	{
		Connection conn = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "";
		try 
		{
			conn = DataSourceFactory.getConnection();
			if (tag_id != null && !tag_id.equals(""))  
			{
				sql = "select count(*) as count from youhui_datamining.m_mix_pagetype where status=1 and tag_id='"+tag_id+"'";
			}
			else  
			{
				sql = "select count(*) as count from youhui_datamining.m_mix_pagetype where status=1";
			}
			rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) 
			{
				count = rs.getInt("count");
			}
		} 
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getProductPageStyleListSizeByTagid error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, null, conn);
		}
		return count;
	}
	
	
	public static List<MixStylePageBean> getAllMixStylePageByTagid(String tagid)
	{
		List<MixStylePageBean> list = new ArrayList<MixStylePageBean>();

		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		String sql = "";
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			st = conn.createStatement();	

			if (tagid != null && !tagid.equals(""))
			{
				sql = "select * from youhui_datamining.m_mix_pagetype  where status=1 and tag_id='"+tagid+"'  ";
			}
			else 
			{
				sql = "select * from youhui_datamining.m_mix_pagetype  where status=1   ";
			}
			
			rs = st.executeQuery(sql);
			while(rs.next()) 
			{
				ArrayList<TeJiaGoodsBean> tjlist = getPPSBean(rs.getString("item_ids"));
				MixStylePageBean bean = new MixStylePageBean();
				
				bean.setId(rs.getString("id"));
				bean.setTypeid(rs.getString("typeid"));
				bean.setItem_ids(rs.getString("item_ids"));
				bean.setTag_id(rs.getString("tag_id"));
				bean.setRank(rs.getString("rank"));
				bean.setUpdatetime(rs.getString("updatetime"));
				bean.setStatus(rs.getString("status"));
				bean.setTjGoodsBeanList(tjlist);
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getAllMixStylePageByTagid error:", e);
			return null;
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, st, conn);
		}
		return list;
	}
	
	/**
	 * 根据id集合查出某个style页面显示的商品，有排序
	 * @param id
	 * @return
	 */
	public static ArrayList<TeJiaGoodsBean> getPPSBean(String id) 
	{
		Connection conn = null;
		PreparedStatement perstamt = null;
		ResultSet rs = null;
		
		ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		String[] array = id.split(",");
		try 
		{
			conn = DataSourceFactory.getConnection();
			String sql = "select * from youhui_datamining.m_discount_products where item_id =? and `status`=0 ";
			perstamt = conn.prepareStatement(sql);
			
			for(int i = 0; i < array.length; i++) 
			{
				perstamt.setString(1, array[i]);
				rs = perstamt.executeQuery();
				if (rs.next())
				{
					TeJiaGoodsBean bean = new TeJiaGoodsBean();
					
					bean.setItem_id(rs.getString("item_id"));
					bean.setTitle(rs.getString("title"));
					bean.setKeyword(rs.getString("keyword"));
					bean.setPrice_high(rs.getString("price_high"));
					bean.setPrice_low(rs.getString("price_low"));
					bean.setDiscount(rs.getString("discount"));
					bean.setPic_url(rs.getString("pic_url"));
					bean.setStatus(rs.getInt("status"));
					bean.setShow_index(rs.getInt("show_index"));
					bean.setCommission(rs.getString("commission"));
					bean.setCommission_rate(rs.getString("commission_rate"));
					bean.setClickURL(rs.getString("click_url"));
					bean.setRate(rs.getDouble("jfb_rate"));
					
					list.add(bean);
				}
			}

		} 
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.getPPSBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, perstamt, conn);
		}
		return list;
	}
	
	/**
	 * 根据商品id获取商品信息
	 * @param itemId
	 * @return
	 */
	public static TeJiaGoodsBean getDiscountProduct(String itemId) 
	{
		TeJiaGoodsBean bean = null;
		
		Connection conn = null;
		PreparedStatement perstamt = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` WHERE `item_id` = ? AND `status` = 0;";
			perstamt = conn.prepareStatement(sql);

			perstamt.setString(1, itemId);
			
			rs = perstamt.executeQuery();
			if (rs.next())
			{
				bean = new TeJiaGoodsBean();
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setStatus(rs.getInt("status"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setCommission(rs.getString("commission"));
				bean.setCommission_rate(rs.getString("commission_rate"));
				bean.setClickURL(rs.getString("click_url"));
				bean.setIsUpdateed(rs.getInt("isupdate"));
				bean.setRate(rs.getDouble("jfb_rate"));
				bean.setCatID(rs.getString("catid"));
			}

		} 
		catch (Exception e)
		{
			logger.error("Admin_Tag_Item_DAO.getDiscountProduct error:", e);
			return null;
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, perstamt, conn);
		}
		return bean;
	}
	
	/**
	 * 手动修改价格、图片、集分宝比例
	 * @param bean
	 * @return
	 */
	public static boolean updateDiscountProductImg(TeJiaGoodsBean bean)
	{       
		boolean result = false;
		Connection conn = null;
		PreparedStatement s =null;
		
		try 
		{ 
			conn = DataSourceFactory.getConnection();
			String sql = "update `youhui_datamining`.`m_discount_products` set `pic_url`=?,`price_low`=?,`isupdate` = 1,`jfb_rate`=?,`title`=? ,`click_url`=?,`keyword`=? where item_id = ?;";        
			s = conn.prepareStatement(sql);
			 
			s.setString(1, bean.getPic_url());
			s.setString(2, bean.getPrice_low());
			s.setString(3, String.valueOf(bean.getRate()));
			s.setString(4, bean.getTitle());
			s.setString(5, bean.getClickURL());
			s.setString(6, bean.getKeyword());
			s.setString(7, bean.getItem_id());
			
			int i = s.executeUpdate();           
			if (i > 0)
			{
				result = true;             
			}
		} 
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.updateDiscountProductImg error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return result;
	}
	
	/**
	 * 刷新标签下商品列表时删除无效商品:商品被删除或者商家不支持集分宝返利
	 * @param del_ItemId_List
	 * @return
	 */
	public static boolean delTagItemByTag(ArrayList<String> del_ItemId_List)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			for (String item_id : del_ItemId_List)
			{
				String sql1 = "UPDATE `youhui_datamining`.`m_tagtoitem` SET `rel_status` = 2 WHERE `item_id` = '" + item_id + "';";
				ps = conn.prepareStatement(sql1);
				ps.executeUpdate();
				
				String sql2 = "update `youhui_datamining`.`m_discount_products` set `status` = 1,`jfb_rate` = '0.0' WHERE `item_id` = '" + item_id + "';";
				ps = conn.prepareStatement(sql2);
				ps.executeUpdate();
				
				// 修改集分宝历史记录
				String sql3 = "SELECT MAX(`id`) AS lastid FROM `youhui_datamining`.`item_jfb_rate` WHERE `itemid` = '" + item_id + "' AND `end_time` = '0';";
				String sql4 = "UPDATE `youhui_datamining`.`item_jfb_rate` SET `end_time` = ? WHERE `id` = ?;";
				
				ps = conn.prepareStatement(sql3);
				rs = ps.executeQuery();
				if (rs.next())
				{
					String lastID = rs.getString("lastid");
					if (lastID != null && !"".equals(lastID) && !"null".equalsIgnoreCase(lastID))
					{
						String nowTimeMillis = System.currentTimeMillis() + "";
						
						ps = conn.prepareStatement(sql4);
						ps.setString(1, nowTimeMillis);
						ps.setString(2, lastID);
						ps.executeUpdate();
					}
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Tag_Item_DAO.delTagItemByTag error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return true;
	}
	
	/**
	 * 标签的集分宝比例修改后,修改需要修改的商品集分宝比例，同时修改并添加商品的集分宝变化历史记录
	 * @param bean
	 * @return
	 */
	public static boolean updateDiscountProductJfbRate(String tagID, String new_jfb_rate, String old_jfb_rate)
	{       
		boolean result = false;
		ArrayList<String> itemID_List = new ArrayList<String>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			String sql_query = "select a.item_id from `youhui_datamining`.`m_discount_products` a,`youhui_datamining`.`m_tagtoitem` b WHERE b.`tag_id` = ? AND a.`item_id` = b.`item_id` AND b.`rel_status` = 1 and a.`status` = 0 and a.`jfb_rate` = ?;";
			
			ps = conn.prepareStatement(sql_query);
			ps.setString(1, tagID);
			ps.setString(2, old_jfb_rate);
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				itemID_List.add(rs.getString("item_id"));
			}
			
			if (itemID_List == null || itemID_List.size() <= 0)
			{
				return true;
			}
			
			String sql1 = "SELECT MAX(`id`) AS lastid FROM `youhui_datamining`.`item_jfb_rate` WHERE `itemid` = ? AND `end_time` = '0';";
			String sql2 = "UPDATE `youhui_datamining`.`item_jfb_rate` SET `end_time` = ? WHERE `id` = ?;";
			String sql3 = "UPDATE `youhui_datamining`.`m_discount_products` a SET a.`jfb_rate` = ? WHERE a.`item_id` = ?;";
			String sql4 = "INSERT into `youhui_datamining`.`item_jfb_rate`(`itemid`,`jfb_rate`,`start_time`,`end_time`) VALUES (?,?,?,?);";
			
			ps = null;
			String nowTimeMillis = null;
			for (String item_id : itemID_List)
			{
				nowTimeMillis = System.currentTimeMillis() + "";
				ps = conn.prepareStatement(sql1);
				ps.setString(1, item_id);
				rs = ps.executeQuery();
				if (rs.next())
				{
					String lastID = rs.getString("lastid");
					if (lastID != null && !"".equals(lastID) && !"null".equalsIgnoreCase(lastID))
					{
						ps = conn.prepareStatement(sql2);
						ps.setString(1, nowTimeMillis);
						ps.setString(2, lastID);
						ps.executeUpdate();
					}
				}
				
				ps = conn.prepareStatement(sql3);
				ps.setString(1, new_jfb_rate);
				ps.setString(2, item_id);
				int res = ps.executeUpdate();
				if (res > 0)
				{
					ps = conn.prepareStatement(sql4);
					ps.setString(1, item_id);
					ps.setString(2, new_jfb_rate);
					ps.setString(3, nowTimeMillis);
					ps.setString(4, "0");
					ps.executeUpdate();
				}
			}
		} 
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.updateDiscountProductJfbRate error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return result;
	}
	
	public static void addItemJfbRateHistory(String item_id, String new_jfb_rate)
	{
		if (item_id == null || item_id.equalsIgnoreCase("null") || item_id.equals(""))
		{
			return;
		}
		double rate = isExit(item_id);
		if(rate == Double.parseDouble(new_jfb_rate)){
			return;
		}
		String sql1 = "SELECT MAX(`id`) AS lastid FROM `youhui_datamining`.`item_jfb_rate` WHERE `itemid` = ? AND `end_time` = '0';";
		String sql2 = "UPDATE `youhui_datamining`.`item_jfb_rate` SET `end_time` = ? WHERE `id` = ?;";
		String sql3 = "UPDATE `youhui_datamining`.`m_discount_products` a SET a.`jfb_rate` = ? WHERE a.`item_id` = ?;";
		String sql4 = "INSERT into `youhui_datamining`.`item_jfb_rate`(`itemid`,`jfb_rate`,`start_time`,`end_time`) VALUES (?,?,?,?);";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{ 
			String nowTimeMillis = System.currentTimeMillis() + "";
			
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, item_id);
			rs = ps.executeQuery();
			if (rs.next())
			{
				String lastID = rs.getString("lastid");
				if (lastID != null && !"".equals(lastID) && !"null".equalsIgnoreCase(lastID))
				{
					ps = conn.prepareStatement(sql2);
					ps.setString(1, nowTimeMillis);
					ps.setString(2, lastID);
					ps.executeUpdate();
				}
			}
			
			ps = conn.prepareStatement(sql3);
			ps.setString(1, new_jfb_rate);
			ps.setString(2, item_id);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sql4);
			ps.setString(1, item_id);
			ps.setString(2, new_jfb_rate);
			ps.setString(3, nowTimeMillis);
			ps.setString(4, "0");
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.addItemJfbRateHistory error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return;
	}
	
	/**
	 * 刷新REDIS缓存里面的商品数据
	 * @param tagID
	 * @return
	 */
	public static void updateDiscountProductCacheJfbRate(String tagID)
	{
		Connection conn = null;
		PreparedStatement s =null;
		ResultSet rs = null;
		try 
		{ 
			conn = DataSourceFactory.getConnection();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` a,`youhui_datamining`.`m_tagtoitem` b WHERE b.`tag_id` = ? AND a.`item_id` = b.`item_id` AND b.`rel_status` = 1 and a.`status` = 0;";        
			s = conn.prepareStatement(sql);
			s.setString(1, tagID);
			
			rs = s.executeQuery();
			while (rs.next()) 
			{
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setClickURL(rs.getString("click_url"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setCommission(rs.getString("commission"));
				bean.setCommission_rate(rs.getString("commission_rate"));
				bean.setHeight_b(rs.getInt("height_b"));
				bean.setHeight_m(rs.getInt("height_m"));
				bean.setHeight_310(rs.getInt("height_310"));
				bean.setHeight_170(rs.getInt("height_170"));
				
				bean.setWidth_m(rs.getInt("width_m"));
				bean.setWidth_b(rs.getInt("width_b"));
				bean.setWidth_310(rs.getInt("width_310"));
				bean.setWidth_170(rs.getInt("width_170"));

				bean.setRate(rs.getDouble("jfb_rate"));
				
				Admin_Tag_Item_Cache_DAO.add_update_Item(bean);
			}
		} 
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.updateDiscountProductCacheJfbRate error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, s, conn);
		}
		return;
	}
	
	/**
	 * 删除混排商品信息，status置为2
	 * @param bean, FeedbackBean
	 */
	public static boolean delProductPageStyle(String id) 
	{
		boolean result = false;
		
		String date = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = sdf.format(new Date());
		
		String sql = "update youhui_datamining.m_mix_pagetype set status=2,updatetime=? where id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, date);
			ps.setString(2, id);
			
			int i = ps.executeUpdate();
			if (i > 0)
			{
				result = true;
			}
		} 
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.delProductPageStyle error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return result;
	}
	
	/**
	 * 新增混排商品信息
	 * @param bean
	 * @return
	 */
	public static boolean addMixStyleItem(MixStylePageBean bean) 
	{
		boolean result = false;
		
		String date = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = sdf.format(new Date());
		
		String tag_id = bean.getTag_id();
		
		String sql = "insert into youhui_datamining.m_mix_pagetype (typeid,item_ids,rank,updatetime,status,tag_id) VALUES (?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;

		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getTypeid());
			ps.setString(2, bean.getItem_ids());
			ps.setInt(3, getMaxPosition());
			ps.setString(4, date);
			ps.setString(5, "1");
			ps.setString(6, tag_id);
			
			int i = ps.executeUpdate();
			if (i > 0) 
			{
				result = true;
			}
		} 
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.addMixStyleItem error:", e);
		} 
		finally
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return result;
	}
	
	public static int getMaxPosition()
	{
		int position = 0;
		
		String sql = "select max(rank) from youhui_datamining.m_mix_pagetype";
		
		Connection conn = null;
		ResultSet rs = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			rs = conn.createStatement().executeQuery(sql);
			if (rs.next())
			{
				position = rs.getInt(1);
			}
		} 
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getMaxPosition error:", e);
		}
		finally
		{
			DataSourceFactory.closeAll(rs, null, conn);
		}
		return position + 1;
	}
	
	/**
	 * 最小位置-1
	 */
	public static int getMinPosition()
	{
		int position = 0;
		
		String sql = "select min(rank) from youhui_datamining.m_mix_pagetype";
		
		Connection conn = null;
		ResultSet rs = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			rs = conn.createStatement().executeQuery(sql);
			if (rs.next())
			{
				position = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Item_DAO.getMinPosition error:", e);
		} 
		finally
		{
			DataSourceFactory.closeAll(rs, null, conn);
		}
		return position - 1;
	
	}
	
	/**
	 * 修改MixStylePageBean
	 */
	public static boolean updateProductPageStyle(MixStylePageBean bean) 
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String date = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = sdf.format(new Date());
		try 
		{
			conn = DataSourceFactory.getConnection();
			String sql = "update youhui_datamining.m_mix_pagetype set typeid=?,item_ids=?,updatetime=? where id=?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getTypeid());
			ps.setString(2, bean.getItem_ids());
			ps.setString(3, date);
			ps.setString(4, bean.getId());
			
			int i = ps.executeUpdate();
			if (i > 0)
			{
				result = true;
			}
		}
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.updateProductPageStyle error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return result;
	}
	
	/**
	 * 根据id获取MixStylePageBean
	 * @param id
	 * @return
	 */
	public static MixStylePageBean getProductPageStyleBean(String id) 
	{
		MixStylePageBean bean = new MixStylePageBean();
		Connection conn = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			String sql = "select * from youhui_datamining.m_mix_pagetype where id=" + id;
			rs = conn.createStatement().executeQuery(sql);
			
			while (rs.next()) 
			{
				ArrayList<TeJiaGoodsBean> tjlist = getPPSBean(rs.getString("item_ids"));
				bean.setId(rs.getString("id"));
				bean.setTypeid(rs.getString("typeid"));
				bean.setItem_ids(rs.getString("item_ids"));
				bean.setRank(rs.getString("rank"));
				bean.setUpdatetime(rs.getString("updatetime"));
				bean.setStatus(rs.getString("status"));
				bean.setTjGoodsBeanList(tjlist);
			}
		} 
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getProductPageStyleBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, null, conn);
		}
		return bean;
	}
	
	/**
	 * 查出此混排tag_id下下一位置的记录，供调整顺序使用
	 * @param rank
	 * @param tag_id
	 * @return
	 */
	public static MixStylePageBean getNextBean(String rank,String tag_id) 
	{
		MixStylePageBean nextbean = null;
		Connection conn = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			String sql = "select * from youhui_datamining.m_mix_pagetype where tag_id='" + tag_id + "' and rank<" + rank + " order by rank desc limit 1";
			rs = conn.createStatement().executeQuery(sql);
			while (rs.next())
			{
				nextbean = new MixStylePageBean();
				nextbean.setId(rs.getString("id"));
				nextbean.setTypeid(rs.getString("typeid"));
				nextbean.setItem_ids(rs.getString("item_ids"));
				nextbean.setRank(rs.getString("rank"));
				nextbean.setUpdatetime(rs.getString("updatetime"));
				nextbean.setStatus(rs.getString("status"));
			}
		}  
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getNextBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, null, conn);
		}
		return nextbean;
	}
	
	/**
	 * 查出此混排tag_id下前一位置的记录，供调整顺序使用
	 * @param rank
	 * @param tag_id
	 * @return
	 */
	public static MixStylePageBean getPreBean(String rank,String tag_id)
	{
		MixStylePageBean prebean = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DataSourceFactory.getConnection();
			String sql = "select * from youhui_datamining.m_mix_pagetype where tag_id = '" + tag_id + "' and rank>" + rank + " order by rank asc limit 1";
			
			rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) 
			{
				prebean = new MixStylePageBean();
				
				prebean.setId(rs.getString("id"));
				// prebean.setKeyword(rs.getString("keyword"));
				prebean.setTypeid(rs.getString("typeid"));
				prebean.setItem_ids(rs.getString("item_ids"));
				prebean.setRank(rs.getString("rank"));
				prebean.setUpdatetime(rs.getString("updatetime"));
				prebean.setStatus(rs.getString("status"));
			}
		}  
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getPreBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, null, conn);
		}
		return prebean;
	}
	
	/**
	 * 修改混排数据库位置
	 */
	public static boolean updatePosition(int id, int rank) 
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;

		try 
		{
			conn = DataSourceFactory.getConnection();
			String sql = "update youhui_datamining.m_mix_pagetype set rank=? where id=?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, rank);
			ps.setInt(2, id);
			
			int i = ps.executeUpdate();
			if (i > 0)
			{
				result = true;
			}
		} 
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getPreBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return result;
	}
	
	/**
	 * 获取时间段内交易量最多的商品列表
	 * @return
	 */
	public static List<TeJiaGoodsBean> getTopTradeItemFrom(long startTime,long endTime){
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT itemid,count(*) as c FROM tradefind.tradeorder where unix_timestamp(createtime) >? and unix_timestamp(createtime) < ? group by itemid order by c desc  limit ?,?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, startTime);
			ps.setLong(2, endTime);
			ps.setInt(3, 0);
			ps.setInt(4,200);
//			System.out.println(ps);
			rs = ps.executeQuery();
			while(rs.next()){
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				bean.setItem_id(rs.getString("itemid"));
				bean.setRank(rs.getInt("c"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}	
		return list;
	}
	
	/**
	 * 插入时间段内交易量最多的商品信息
	 * @param list
	 * @return
	 */
	public static boolean insertTopTradeItem(List<TeJiaGoodsBean> list,long startTime,long endTime){
		boolean flag = false;
		if(list == null || list.size() == 0){
			return flag;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into youhui_datamining.top_trade_item(item_id,start_time,end_time,buy_count,update_date) values(?,?,?,?,?)";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			for(TeJiaGoodsBean bean : list){
				ps.setString(1, bean.getItem_id());
				ps.setLong(2, startTime);
				ps.setLong(3, endTime);
				ps.setInt(4, bean.getRank());
				ps.setLong(5, System.currentTimeMillis());
				ps.addBatch();
			}
			
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(null, ps, conn);
		}	
		return flag;
	}
	
	public static List<TeJiaGoodsBean> getTopTradeItem(long startTime,long endTime,int page){
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.top_trade_item  where start_time=? and end_time=? order by buy_count desc limit ?,?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, startTime);
			ps.setLong(2, endTime);
			ps.setInt(3, (page - 1) *pageSize);
			ps.setInt(4, pageSize);
//			System.out.println(ps);
			
			rs = ps.executeQuery();
			while(rs.next()){
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				bean.setItem_id(rs.getString("item_id"));
//				bean.setTitle(rs.getString("title"));
//				bean.setPrice_high(rs.getString("price_high"));
//				bean.setPrice_low(rs.getString("price_low"));
//				bean.setPic_url(rs.getString("pic_url"));
				bean.setClickURL("http://item.taobao.com/item.htm?id="+rs.getString("item_id"));
				bean.setRank(rs.getInt("buy_count"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}	
		return list;
	}
	
	public static int getTopTradeItemPage(long startTime,long endTime){
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) as c FROM youhui_datamining.top_trade_item a where a.start_time=? and a.end_time=? ;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, startTime);
			ps.setLong(2, endTime);
			
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		if(count % pageSize == 0){
			count = count/pageSize;
		}else{
			count = count/pageSize +1;
		}
		return count;
	}
	
	public static List<String> getKeywordsForPaixu(String tagId) 
	{
		List<String> list = new ArrayList<String>();
		
		ResultSet rs = null;
		Connection conn = null;
		Statement s = null;
		try
		{
			conn = DataSourceFactory.getConnection();
			s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '" + tagId + "'  and `client_show`=1;";
			rs = s.executeQuery(sql);
			while (rs.next()) 
			{
//				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
//				bean.setId(rs.getString("id"));
//				bean.setCategoryId(rs.getString("parent_id"));
//				bean.setKeyword(rs.getString("keyword"));
//				bean.setIcon(rs.getString("icon"));
//				bean.setSex(rs.getInt("sex"));
//				bean.setSearchTimes(rs.getString("search_times"));
//				bean.setItemids(getItemids(rs.getString("id"), conn));
				list.add(rs.getString("id"));
			}
		}
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Item_DAO.getKeywords error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, s, conn);
		}
		return list;
	}
	public static List<TagToItemBean> getTopTagItem(String tag_id,int total){
		List<TagToItemBean> list = new ArrayList<TagToItemBean>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.m_tagtoitem where tag_id=? order by rank asc limit ?,?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			int begin = Integer.parseInt(isLocked(tag_id));
			ps = conn.prepareStatement(sql);
			ps.setString(1, tag_id);
			ps.setInt(2, begin);
			ps.setInt(3, total);
			
			rs = ps.executeQuery();
			while(rs.next()){
				TagToItemBean bean = new TagToItemBean();
				bean.setId(rs.getString("id"));
				bean.setItemid(rs.getString("item_id"));
				bean.setRank(rs.getInt("rank"));
				bean.setTagid(rs.getString("tag_id"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return list;
	}
	
	public static boolean updateTagItemRank(TagToItemBean bean){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "update youhui_datamining.m_tagtoitem set rank=? where tag_id=? and item_id=?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bean.getRank());
			ps.setString(2, bean.getTagid());
			ps.setString(3, bean.getItemid());
			
			ps.execute();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return flag;
	}
	
	public static boolean insertAddItemLogs(String itemId,String tagId,String shijian,long costTime){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into youhui_datamining.add_item_logs(item_id,tag_id,shijian,cost_time,timestamp) values(?,?,?,?,?)";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemId);
			ps.setString(2, tagId);
			ps.setString(3, shijian);
			ps.setLong(4, costTime);
			ps.setLong(5, System.currentTimeMillis());
			
			ps.execute();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return flag;
	}
	
	public static int getTopTradeItemPage(String startTime,String endTime){
		int count = 100;
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String sql = "";
//		
//		try {
//			conn = DataSourceFactory.getConnection();
//			ps = conn.prepareStatement(sql);
//			
//			rs = ps.executeQuery();
//			if(rs.next()){
//				count = rs.getInt("c");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return 100;
//		}finally {
//			DataSourceFactory.closeAll(null, ps, conn);
//		}	
		return count;
	}
	
	public static double isExit(String itemid){
		double rate = -1;;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT jfb_rate FROM youhui_datamining.item_jfb_rate where itemid=? order by id desc limit 1;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemid);
			
			rs = ps.executeQuery();
			if(rs.next()){
				rate = rs.getDouble("jfb_rate");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return rate;
	}
	
	
}
