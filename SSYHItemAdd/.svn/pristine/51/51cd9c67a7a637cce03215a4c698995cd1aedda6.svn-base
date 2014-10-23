package cn.youhui.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.KeywordToItem;
import cn.youhui.bean.Searchkeyword;
import cn.youhui.platform.db.SQL;


public class AdminSearchKeyWordDAO {

	
	public static Searchkeyword getSearchKeyword(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		Searchkeyword bean = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM youhui_datamining.m_search_keywords where id=? ;";
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()){
				bean = new Searchkeyword();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SQL.getInstance().release(ps, conn);
		}
		return bean;
	}
	
	public static int addKeywordItem(KeywordToItem keywordItem){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rank = getMinKeywordItemRank(keywordItem.getId());
		String sql = " insert into youhui_datamining.m_keywordtoitem(keyword_id,item_id,rank,rel_status) values(?,?,?,?)  on duplicate key update rel_status=?;";
		
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, keywordItem.getId());
			ps.setString(2, keywordItem.getItemId());
			ps.setInt(3, rank);
			ps.setInt(4, 1);
			ps.setInt(5, 1);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SQL.getInstance().release(ps, conn);
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
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, keywordId);
			
			rs = ps.executeQuery();
			if(rs.next()){
				rank = rs.getInt("c")-1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SQL.getInstance().release(ps, conn);
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
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()){
				parentId = rs.getString("parent_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}finally {
			SQL.getInstance().release(ps, conn);
		}
		return parentId;
	}
	
	public static List<Searchkeyword> getKeywordByItemid(String itemid){
		List<Searchkeyword> list = new ArrayList<Searchkeyword>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT a.id,a.name FROM youhui_datamining.m_search_keywords a,youhui_datamining.m_keywordtoitem b  where b.item_id=? and a.id = b.keyword_id and b.rel_status= '1';";
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemid);
			
			rs = ps.executeQuery();
			while(rs.next()){
				Searchkeyword bean = new Searchkeyword();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			SQL.getInstance().release(ps, conn);
		}
		return list;
	}
	
	public static List<Searchkeyword> getKeywordByItemid(String itemid,Connection conn){
		List<Searchkeyword> list = new ArrayList<Searchkeyword>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT a.id,a.name FROM youhui_datamining.m_search_keywords a,youhui_datamining.m_keywordtoitem b  where b.item_id=? and a.id = b.keyword_id and b.rel_status= '1';";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemid);
			
			rs = ps.executeQuery();
			while(rs.next()){
				Searchkeyword bean = new Searchkeyword();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean delKeywordItem(String keywordId,String itemid){
		boolean flag = false;
		
		String sql1 = "UPDATE `youhui_datamining`.`m_keywordtoitem` SET `rel_status` = 2 WHERE `item_id` = '" + itemid + "' AND `keyword_id` = '" + keywordId + "'";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
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
			e.printStackTrace();
		} 
		finally 
		{
			SQL.getInstance().release(ps, conn);
		}
		
		return flag;
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
			conn = SQL.getInstance().getConnection();
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
			e.printStackTrace();
		} 
		finally 
		{
			SQL.getInstance().release(ps, conn);
		}
		return list;
	}
	
	public static List<Searchkeyword> getKeywordList( String version, String status,String parentId,Connection conn)
	{
		ArrayList<Searchkeyword> list = new ArrayList<Searchkeyword>();
		
		String sql = "SELECT id,name FROM `youhui_datamining`.`m_search_keywords` WHERE `parent_id`=? and `version` = ? AND `status` = ? ";

		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
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
//				bean.setParent_id(rs.getString("parent_id"));
//				bean.setIcon(rs.getString("icon"));
//				bean.setAction_type(rs.getString("action_type"));
//				bean.setAction_value(rs.getString("action_value"));
//				bean.setVersion(rs.getString("version"));
//				bean.setRank(rs.getString("rank"));
//				bean.setStatus(rs.getString("status"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return list;
	}
	
	public static List<String> getAllKeywordByItemid(String itemid){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String sql = "SELECT * FROM youhui_datamining.m_keywordtoitem where item_id= ? ";

		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemid);	
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				list.add(rs.getString("keyword_id"));
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return list;
	}
	
	/**
	 * 查询name是否存在
	 * @param name
	 * @return
	 */
	public static String getKeywordId(String name){
		String sql1 = "SELECT id FROM youhui_datamining.m_search_keywords where name=?;";
		String id = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next())
			{
				id=rs.getString("id");
			}
		}
		catch (Exception e) 
		{
			return "";
		} 
		finally 
		{
			SQL.getInstance().release(ps, conn);
		}
		return id;
	}
	
	/**
	 * 手动插入keyword
	 * @param name
	 * @return
	 */
	public static String insertKeyword(String name){
		
		String id = getKeywordId(name);
		if(!"".equals(id)){
			return id;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into youhui_datamining.m_search_keywords(id,name,shoudong) values(?,?,?)";
		
		try {
			conn = SQL.getInstance().getConnection();
			id =getMaxId(conn)+"";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, 1);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}finally 
		{
			SQL.getInstance().release(ps, conn);
		}
		return id;
	}
	
	/**
	 * 获取最大的id
	 * @param conn
	 * @return
	 */
	public static int getMaxId(Connection conn){
		int id = 0;
		PreparedStatement ps = null;
		String sql = "select max(id) as ma from youhui_datamining.m_search_keywords ";
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt("ma")+1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return id;
	}
	
	/**
	 * 获取手动添加的keyword
	 * @return
	 */
	public static List<Searchkeyword> getShoudongKeywordList(){
		List<Searchkeyword> list = new ArrayList<Searchkeyword>();
		
		String sql = "SELECT id,name FROM `youhui_datamining`.`m_search_keywords` WHERE `shoudong`=? ";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);	
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				Searchkeyword bean = new Searchkeyword();
				
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
//				bean.setParent_id(rs.getString("parent_id"));
//				bean.setIcon(rs.getString("icon"));
//				bean.setAction_type(rs.getString("action_type"));
//				bean.setAction_value(rs.getString("action_value"));
//				bean.setVersion(rs.getString("version"));
//				bean.setRank(rs.getString("rank"));
//				bean.setStatus(rs.getString("status"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} finally 
		{
			SQL.getInstance().release(ps, conn);
		}
		return list;
	}
	
}
