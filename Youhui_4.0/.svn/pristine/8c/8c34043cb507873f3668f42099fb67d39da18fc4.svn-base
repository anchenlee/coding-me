package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.netting.bean.AdminUser;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;


/**
 * 后台管理用户数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_AdminUser_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_AdminUser_DAO.class);
	
	private static int pageSize = 15;
	
	/**
	 * 获取详细信息
	 * @param tag_id
	 * @return
	 */
	public static AdminUser getAdminUserBean(String id)
	{
		AdminUser bean = null;
		String sql = "SELECT * FROM `youhui_v4`.`admin_user` WHERE `id` = ?;";
		
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
				bean = new AdminUser();
				
				bean.setId(rs.getString("id"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setRealname(rs.getString("realname"));
				bean.setLast_login_time(CodeUtil.getDateTimeStr(rs.getLong("last_login_time")));
				bean.setGroup(rs.getInt("group"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.getAdminUserBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 管理员获取列表
	 * @return
	 */
	public static HashMap<String, String> getAdminUserMap()
	{
		HashMap<String, String> userMap = new HashMap<String, String>();
		
		String sql = "SELECT `username`,`realname` FROM `youhui_v4`.`admin_user`;";
		
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
				String username = rs.getString("username");
				String realname = rs.getString("realname");
				
				userMap.put(username, realname);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.getAdminUserMap error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return userMap;
	}
	
	/**
	 * 管理员获取列表
	 * @param page 页码
	 * @param keyword 关键字查询
	 * @return
	 */
	public static ArrayList<AdminUser> getAdminUserList(int page, String keyword)
	{
		ArrayList<AdminUser> list = new ArrayList<AdminUser>();
		if (page < 0) 
		{
			page = 1;
		}
		
		String sql = "SELECT * FROM `youhui_v4`.`admin_user` WHERE 1 = 1 ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `realname` LIKE '%" + keyword + "%'";
		}
		sql = sql + " ORDER BY `id` asc limit ?,?;";
		
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
				AdminUser bean = new AdminUser();
				
				bean.setId(rs.getString("id"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setRealname(rs.getString("realname"));
				bean.setLast_login_time(CodeUtil.getDateTimeStr(rs.getLong("last_login_time")));
				bean.setGroup(rs.getInt("group"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.getAdminUserList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	/**
	 * 管理员获取列表页面数
	 * @param status
	 * @param keyword
	 * @param ispad
	 * @return
	 */
	public static int getAdminUserListTotal(String keyword) 
	{
		int totalPage = 0;
		
		String sql = "SELECT count(`id`) AS count FROM `youhui_v4`.`admin_user` WHERE 1 = 1 ";
		if (keyword != null && !"".equals(keyword) && !keyword.equals("null")) 
		{
			sql = sql + " AND `realname` LIKE '%" + keyword + "%'";
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
			logger.error("Admin_AdminUser_DAO.getAdminUserListTotal error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return totalPage;
	}

	/**
	 * 删除
	 * @return
	 */
	public static boolean deleteAdminUser(String id)
	{
		boolean flag = false;
		
		String sql = "DELETE FROM `youhui_v4`.`admin_user` WHERE `id` = '" + id + "' AND `username` != 'admin';";
		
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
			logger.error("Admin_AdminUser_DAO.deleteAdminUser error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 删除用户与菜单的绑定关系
	 * @return
	 */
	public static boolean deleteAdminUserToMenu(String uid)
	{
		boolean flag = false;
		
		String sql = "DELETE FROM `youhui_v4`.`rel_user_menu` WHERE `uid` = '" + uid + "';";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			flag = true;
		}
		catch (Exception e) 
		{
			flag = false;
			logger.error("Admin_AdminUser_DAO.deleteAdminUserToMenu error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 更新
	 * @param bean
	 * @return
	 */
	public static boolean updateAdminUser(AdminUser bean)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_v4`.`admin_user` SET `password`=?,`realname`=? WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getPassword());
			ps.setString(2, bean.getRealname());
			ps.setString(3, bean.getId());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.updateAdminUser error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 新增
	 * @param tag
	 * @return
	 */
	public static boolean addAdminUser(AdminUser bean)
	{
		boolean flag = false;
		
		String sql = "INSERT INTO `youhui_v4`.`admin_user`(`username`,`password`,`realname`,`last_login_time`,`group`,`id`)" +
				" VALUES (?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getPassword());
			ps.setString(3, bean.getRealname());
			ps.setString(4, String.valueOf(System.currentTimeMillis()));
			ps.setInt(5, bean.getGroup());
			ps.setString(6, bean.getId());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.addAdminUser error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 获取下一个ID
	 * @return
	 */
	public static String getAdminUserNextID()
	{
		String id = null;
		
		String sql = "SELECT max(id) AS maxid FROM `youhui_v4`.`admin_user`;";
		
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
			logger.error("Admin_AdminUser_DAO.getAdminUserNextID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return id;
	}

	/**
	 * 检查用户名是否重复
	 * @param tag_id
	 * @return
	 */
	public static boolean checkAdminUsername(String username)
	{
		boolean flag = false;
		String sql = "SELECT count(`id`) AS count FROM `youhui_v4`.`admin_user` WHERE `username` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				int count = rs.getInt("count");
				if (count > 0)
				{
					flag = true;
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.getAdminUserBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 获取所有的菜单
	 * @return
	 */
	public static HashMap<String, String> getAllMenuMap()
	{
		HashMap<String, String> allMenuMap = new HashMap<String, String>();
		
		String sql = "SELECT `id`,`name` FROM `youhui_v4`.`m_menu`;";
		
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
				String id = rs.getString("id");
				String name = rs.getString("name");
				if (id != null && !id.equals("") && name != null && !name.equals(""))
				{
					allMenuMap.put(id, name);
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.getAllMenuMap error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return allMenuMap;
	}
	
	/**
	 * 获取用户的菜单
	 * @return String
	 */
	public static String getUserMenuList(String uid)
	{
		String userMenu = "";
		
		String sql = "SELECT `menuid` FROM `youhui_v4`.`rel_user_menu` WHERE `uid` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				String menuid = rs.getString("menuid");
				if (menuid != null && !menuid.equals(""))
				{
					userMenu = userMenu + menuid + ",";
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.getUserMenuList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return userMenu;
	}
	
	/**
	 * 检查用户是否已经拥有某个菜单的权限
	 * @param user_id
	 * @param menu_id
	 * @return
	 */
	public static boolean checkUserToMenu(String user_id, String menu_id)
	{
		boolean flag = false;
		
		String sql = "SELECT count(`id`) AS count FROM `youhui_v4`.`rel_user_menu` WHERE `uid` = ? AND `menuid` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, menu_id);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				int count = rs.getInt("count");
				if (count > 0)
				{
					flag = true;
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.checkUserToMenu error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 新增菜单权限
	 * @param tag
	 * @return
	 */
	public static boolean addUserToMenu(String user_id, String menu_id)
	{
		boolean flag = false;
		
		String sql = "INSERT INTO `youhui_v4`.`rel_user_menu`(`uid`,`menuid`) VALUES (?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user_id);
			ps.setString(2, menu_id);
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.addUserToMenu error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 删除菜单权限
	 * @param tag
	 * @return
	 */
	public static boolean delUserToMenu(String user_id, String menu_id)
	{
		boolean flag = false;
		
		String sql = "DELETE FROM `youhui_v4`.`rel_user_menu` WHERE `uid` = ? AND `menuid` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user_id);
			ps.setString(2, menu_id);
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_AdminUser_DAO.delUserToMenu error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
}
