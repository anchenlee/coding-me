package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.Menu;
import com.netting.db.DataSourceFactory;

/**
 * 后台管理Menu栏目数据操作类
 * @author YAOJIANBO
 *
 */
public class Menu_DAO 
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Menu_DAO.class);
	
	/**
	 * 根据用户的ID获取左侧栏目列表
	 * @param admin_user_id
	 * @return
	 */
	public static ArrayList<Menu> getMenuListByUID(String admin_user_id, int parent) 
	{
		ArrayList<Menu> menu_list = new ArrayList<Menu>();
		
		String sql = "SELECT * FROM `youhui_v4`.`m_menu` where `status` = 1 AND `parent` = ? AND `id` IN " +
				"(SELECT b.`menuid` FROM `youhui_v4`.`rel_user_menu` b WHERE b.`uid` = ?);";
		
		Connection conn = null;
		PreparedStatement prestmt = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			prestmt = conn.prepareStatement(sql);
			
			prestmt.setInt(1, parent);
			prestmt.setInt(2, Integer.parseInt(admin_user_id));
			
			rtst = prestmt.executeQuery();
			while (rtst.next()) 
			{
				Menu menu_bean = new Menu();
				menu_bean.setId(rtst.getString("id"));
				menu_bean.setName(rtst.getString("name"));
				menu_bean.setUrl(rtst.getString("url"));
				menu_bean.setStatus(rtst.getString("status"));
				menu_bean.setIcon(rtst.getString("icon"));
				menu_list.add(menu_bean);
			}
		} 
		catch (Exception e) 
		{
			logger.error("Menu_DAO.getMenuListByUID error:", e);
		}
		finally 
		{
			DataSourceFactory.closeAll(rtst, prestmt, conn);
		}
		
		return menu_list;
	}
}
