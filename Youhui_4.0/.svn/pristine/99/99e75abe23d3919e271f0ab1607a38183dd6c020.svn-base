package com.netting.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;

/**
 * 还原标签下商品列表的集分宝比例
 * @author YAOJIANBO
 *
 */
public class RestoreTagItemRateJob extends Thread 
{
	/**
	 * 初始化日志
	 */
	public static Log logger = LogFactory.getLog( RestoreTagItemRateJob.class );
	
	private String tag_id;
	
	private String tag_jfb_rate;
	
	public RestoreTagItemRateJob(String tag_id, String tag_jfb_rate) 
	{
		this.tag_id = tag_id;
		this.tag_jfb_rate = tag_jfb_rate;
	}

	@Override
	public void run() 
	{
		HashMap<String, String> itemID_catID_Map = new HashMap<String, String>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{ 
			long time = CodeUtil.getTimeMillis(CodeUtil.getDateStr(), "00:00:00");
			
			conn = DataSourceFactory.getConnection();
			String sql_query = "select a.`item_id`,a.`catid` from `youhui_datamining`.`m_discount_products` a,`youhui_datamining`.`m_tagtoitem` b WHERE b.`tag_id` = ? AND a.`item_id` = b.`item_id` and a.`status` = 0 AND b.`rel_status` = 1 and a.update_time < ? and a.`jfb_rate` != ?;";
			
			ps = conn.prepareStatement(sql_query);
			ps.setString(1, this.tag_id);
			ps.setString(2, time + "");
			ps.setString(3, this.tag_jfb_rate);
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				itemID_catID_Map.put(rs.getString("item_id"), rs.getString("catid"));
			}
			
			if (itemID_catID_Map == null || itemID_catID_Map.size() <= 0)
			{
				return;
			}
			
			String sql1 = "SELECT MAX(`id`) AS lastid FROM `youhui_datamining`.`item_jfb_rate` WHERE `itemid` = ? AND `end_time` = '0';";
			String sql2 = "UPDATE `youhui_datamining`.`item_jfb_rate` SET `end_time` = ? WHERE `id` = ?;";
			String sql3 = "UPDATE `youhui_datamining`.`m_discount_products` a SET a.`jfb_rate` = ? WHERE a.`item_id` = ?;";
			String sql4 = "INSERT into `youhui_datamining`.`item_jfb_rate`(`itemid`,`jfb_rate`,`start_time`,`end_time`) VALUES (?,?,?,?);";
			
			ps = null;
			String nowTimeMillis = null;
			Set<Entry<String, String>> set = itemID_catID_Map.entrySet();
			for (Entry<String, String> entry : set)
			{
				String item_id = entry.getKey();
				
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
				
				// 检查该商品所属分类的集分宝比例是否为0
				String cat_id = entry.getValue();
				double catRate = Admin_Tag_Item_DAO.getCatRate(cat_id);
				
				ps = conn.prepareStatement(sql3);
				if (catRate == 0)
				{
					ps.setString(1, "0");
				}
				else
				{
					ps.setString(1, this.tag_jfb_rate);
				}
				ps.setString(2, item_id);
				int res = ps.executeUpdate();
				if (res > 0)
				{
					ps = conn.prepareStatement(sql4);
					ps.setString(1, item_id);
					if (catRate == 0)
					{
						ps.setString(2, "0");
					}
					else
					{
						ps.setString(2, this.tag_jfb_rate);
					}
					ps.setString(3, nowTimeMillis);
					ps.setString(4, "0");
					ps.executeUpdate();
				}
			}
		} 
		catch (Exception e)
		{
			logger.error("RestoreTagItemRateJoberror:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		Admin_Tag_Item_DAO.updateDiscountProductCacheJfbRate(tag_id);
	}
}
