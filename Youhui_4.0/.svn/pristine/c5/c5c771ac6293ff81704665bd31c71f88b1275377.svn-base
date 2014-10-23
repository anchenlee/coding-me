package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.netting.bean.KeywordToItem;
import com.netting.bean.Searchkeyword;
import com.netting.db.DataSourceFactory;


/**
 * 搜索关键字数据库操作业务类</p>
 * @author YAOJIANBO
 */
public class Admin_SearchKeyWord_DAO
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_SearchKeyWord_DAO.class);
	
	private static int pageSize = 10;
	
	/**
	 * 详细信息
	 * @param id
	 * @return
	 */
	public static Searchkeyword getKeywordBean(String id)
	{
		Searchkeyword bean = null;
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `id` = ?;";
		
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
				bean = new Searchkeyword();
				
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setParent_id(rs.getString("parent_id"));
				bean.setIcon(rs.getString("icon"));
				bean.setAction_type(rs.getString("action_type"));
				bean.setAction_value(rs.getString("action_value"));
				bean.setVersion(rs.getString("version"));
				bean.setRank(rs.getString("rank"));
				bean.setStatus(rs.getString("status"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.getKeywordBean error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 管理员获取关键字列表
	 * @param page
	 * @param version
	 * @param parent_id
	 * @return
	 */
	public static ArrayList<Searchkeyword> getKeywordList(int page, String version, String parent_id, String status)
	{
		ArrayList<Searchkeyword> list = new ArrayList<Searchkeyword>();
		
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `parent_id` = ? AND `version` = ? AND `status` = ? ";
		sql = sql + " ORDER BY `rank` DESC limit ?,?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, parent_id);
			ps.setString(2, version);
			ps.setString(3, status);
			ps.setInt(4, pageSize * (page - 1));
			ps.setInt(5, pageSize);	
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				Searchkeyword bean = new Searchkeyword();
				
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setParent_id(rs.getString("parent_id"));
				bean.setIcon(rs.getString("icon"));
				bean.setAction_type(rs.getString("action_type"));
				bean.setAction_value(rs.getString("action_value"));
				bean.setVersion(rs.getString("version"));
				bean.setRank(rs.getString("rank"));
				bean.setStatus(rs.getString("status"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.getKeywordList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * 获取总页数
	 * @param version
	 * @param parent_id
	 * @return
	 */
	public static int getKeywordListTotal(String version, String parent_id, String status) 
	{
		int totalPage = 0;
		
		String sql = "SELECT count(`id`) AS count FROM `youhui_datamining`.`m_search_keywords` WHERE `parent_id` = ? AND `version` = ? AND `status` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, parent_id);
			ps.setString(2, version);
			ps.setString(3, status);
			
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
			logger.error("Admin_SearchKeyWord_DAO.getKeywordListTotal error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rtst, ps, conn);
		}
		
		return totalPage;
	}

	/**
	 * 删除关键字
	 * @return
	 */
	public static boolean deleteKeyword(String id)
	{
		boolean flag = false;
		
		String sql = "DELETE FROM `youhui_datamining`.`m_search_keywords` WHERE `id` = '" + id + "';";
		
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
			logger.error("Admin_SearchKeyWord_DAO.deleteKeyword error:", e);
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
	public static boolean updateKeyword(Searchkeyword bean)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`m_search_keywords` SET `name`=?,`icon`=?,`action_type`=?,`action_value`=? WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getIcon());
			ps.setString(3, bean.getAction_type());
			ps.setString(4, bean.getAction_value());
			ps.setString(5, bean.getId());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.updateKeyword error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 隐藏搜索关键字
	 * @return
	 */
	public static boolean updateKeywordStatus(String id, String status)
	{
		boolean flag = false;
		
		String sql = "UPDATE `youhui_datamining`.`m_search_keywords` SET `status` = ? WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setString(2, id);
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.updateKeywordStatus error:", e);
			return false;
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 新增关键字
	 * @return
	 */
	public static boolean addKeyword(Searchkeyword bean)
	{
		boolean flag = false;
		
		String sql1 = "SELECT max(`rank`) as mrank FROM `youhui_datamining`.`m_search_keywords`;";
		
		String sql2 = "INSERT INTO `youhui_datamining`.`m_search_keywords`(`id`,`name`,`parent_id`,`icon`,`action_type`,`action_value`,`version`,`rank`) " +
				" VALUES (?,?,?,?,?,?,?,?)";
		
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
			rank++;
			bean.setRank(String.valueOf(rank));
			
			ps = conn.prepareStatement(sql2);
			ps.setString(1, bean.getId());
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getParent_id());
			ps.setString(4, bean.getIcon());
			ps.setString(5, bean.getAction_type());
			ps.setString(6, bean.getAction_value());
			ps.setString(7, bean.getVersion());
			ps.setString(8, bean.getRank());
			
			int i = ps.executeUpdate();
			if(i > 0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.addKeyword error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return flag;
	}
	
	/**
	 * 获取表的下一个ID
	 * @return
	 */
	public static String getNextID()
	{
		String id = null;
		
		String sql = "SELECT max(id) AS maxid FROM `youhui_datamining`.`m_search_keywords`;";
		
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
			logger.error("Admin_SearchKeyWord_DAO.getNextID error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return id;
	}
	
	/**
	 * 检查关键字名称是否重复
	 * @return
	 */
	public static boolean checkSearchKeywordRepeat(String keyword_name, String keyword_id,  String parent_id, int type)
	{
		if (keyword_name == null || keyword_name.equals(""))
		{
			return false;
		}
		boolean flag = false;
		
		String sql = "SELECT `id` AS key_id FROM `youhui_datamining`.`m_search_keywords` WHERE `name` = '" + keyword_name + "' AND `parent_id` = " + parent_id;
		if (type == 1)
		{
			sql = sql + ";";
		}
		else if (type == 2 && keyword_id != null && !keyword_id.equals(""))
		{
			sql = sql + " AND `id` != " + keyword_id + ";";
		}
		
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
				String key_id = rs.getString("key_id");
				if (key_id != null && !key_id.equals(""))
				{
					flag = true;
				}
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.checkSearchKeywordRepeat error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 获取前一个Searchkeyword
	 * @param parent_id
	 * @param position
	 * @param version
	 * @return
	 */
	public static Searchkeyword gerPreSearchkeyword(String parent_id,String position,String version,String status)
	{
		Searchkeyword bean = null;
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `parent_id` = ? AND `version` = ? and `rank`>  ? AND `status` = ?  order by rank asc limit 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, parent_id);
			ps.setString(2, version);
			ps.setString(3, position);
			ps.setString(4, status);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new Searchkeyword();
				
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setParent_id(rs.getString("parent_id"));
				bean.setIcon(rs.getString("icon"));
				bean.setAction_type(rs.getString("action_type"));
				bean.setAction_value(rs.getString("action_value"));
				bean.setVersion(rs.getString("version"));
				bean.setRank(rs.getString("rank"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.gerPreSearchkeyword error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 获取后一个Searchkeyword
	 * @param parent_id
	 * @param position
	 * @param version
	 * @return
	 */
	public static Searchkeyword gerNextSearchkeyword(String parent_id,String position,String version,String status)
	{
		Searchkeyword bean = null;
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `parent_id` = ? AND `version` = ? and `rank`<  ? and `status`=?  order by rank desc limit 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, parent_id);
			ps.setString(2, version);
			ps.setString(3, position);
			ps.setString(4, status);
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new Searchkeyword();
				
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setParent_id(rs.getString("parent_id"));
				bean.setIcon(rs.getString("icon"));
				bean.setAction_type(rs.getString("action_type"));
				bean.setAction_value(rs.getString("action_value"));
				bean.setVersion(rs.getString("version"));
				bean.setRank(rs.getString("rank"));
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.gerNextSearchkeyword error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	/**
	 * 获取表的最大rank
	 * @return
	 */
	public static int getMaxRank()
	{
		int id = 0;
		
		String sql = "SELECT max(rank) AS maxid FROM `youhui_datamining`.`m_search_keywords`;";
		
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
				id = maxid + 1;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.getMaxRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return id;
	}
	
	/**
	 * 获取表的最小rank
	 * @return
	 */
	public static int getMinRank()
	{
		int id = 0;
		
		String sql = "SELECT min(rank) AS rank FROM `youhui_datamining`.`m_search_keywords`;";
		
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
				int maxid = rs.getInt("rank");
				id = maxid - 1;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.getMinRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return id;
	}
	
	/**
	 * 修改rank
	 * @param id
	 * @param rank
	 * @return
	 */
	public static boolean updateRank(String id,String rank)
	{
		boolean flag = false;
		String sql = "update `youhui_datamining`.`m_search_keywords` set `rank`=? where `id`=?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, rank);
			ps.setString(2, id);
			
			int i = ps.executeUpdate();
			if(i>0) 
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.updateRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return flag;
	}
	
	/**
	 * 获取热门搜索关键词
	 * @return
	 */
	public static List<Searchkeyword> getHotSearchKeyword(int page,String startTime,String endTime){
		List<Searchkeyword> list = new ArrayList<Searchkeyword>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT keyword,count(*) as c FROM youhui_datamining.tyh_keyword_count where timestamp>? and timestamp<? group by keyword  order by c desc limit ?,?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, startTime);
			ps.setString(2, endTime);
			ps.setInt(3, (page-1)*100);
			ps.setInt(4, 100);
//			System.out.println(ps);
			rs = ps.executeQuery();
			while(rs.next()){
				Searchkeyword bean = new Searchkeyword();
				bean.setName(rs.getString("keyword"));
				bean.setRank(rs.getString("c"));
				list.add(bean);
			}
		} catch (SQLException e) {
			logger.error("Admin_SearchKeyWord_DAO.getHotSearchKeyword error:", e);
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	public static int addKeywordItem(KeywordToItem keywordItem){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rank = getMinKeywordItemRank(keywordItem.getId());
		String sql = " insert into youhui_datamining.m_keywordtoitem(keyword_id,item_id,rank,rel_status) values(?,?,?,?)  on duplicate key update rel_status=?;";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, keywordItem.getId());
			ps.setString(2, keywordItem.getItemId());
			ps.setInt(3, rank);
			ps.setInt(4, 1);
			ps.setInt(5, 1);
			
			ps.execute();
		} catch (SQLException e) {
			logger.error("Admin_SearchKeyWord_DAO.addKeywordItem error:", e);
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		
		return rank;
	}
	
	public static int getMinKeywordItemRank(String keywordId){
		int rank = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT min(rank) as c FROM youhui_datamining.m_keywordtoitem where keyword_id=?;";
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, keywordId);
			
			rs = ps.executeQuery();
			if(rs.next()){
				rank = rs.getInt("c")-1;
			}
			
		} catch (SQLException e) {
			logger.error("Admin_SearchKeyWord_DAO.getMinKeywordItemRank error:", e);
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return rank;
	}
	
	public static String getKeywordParentId(String id){
		String parentId = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT parent_id FROM youhui_datamining.m_search_keywords where id=?;";
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()){
				parentId = rs.getString("parent_id");
			}
			
		} catch (SQLException e) {
			logger.error("Admin_SearchKeyWord_DAO.getMinKeywordItemRank error:", e);
			return "";
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return parentId;
	}
	
	
	public static List<Searchkeyword> getKeywordList( String version, String status,String parentId)
	{
		ArrayList<Searchkeyword> list = new ArrayList<Searchkeyword>();
		
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `parent_id`=? and `version` = ? AND `status` = ? ";

		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, parentId);
			ps.setString(2, version);
			ps.setString(3, status);	
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				Searchkeyword bean = new Searchkeyword();
				
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setParent_id(rs.getString("parent_id"));
				bean.setIcon(rs.getString("icon"));
				bean.setAction_type(rs.getString("action_type"));
				bean.setAction_value(rs.getString("action_value"));
				bean.setVersion(rs.getString("version"));
				bean.setRank(rs.getString("rank"));
				bean.setStatus(rs.getString("status"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_SearchKeyWord_DAO.getKeywordList error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
}
