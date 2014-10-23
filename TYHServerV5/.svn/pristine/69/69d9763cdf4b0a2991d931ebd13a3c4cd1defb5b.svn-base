package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.Action;
import cn.youhui.bean.KeyCategoryBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.bean.SearchKeywords;
import cn.youhui.dao.MySQLDBIns;

public class GetSearchKeywordManager 
{
	public static String getSearchKeyword2Result()
	{
		StringBuffer result = new StringBuffer();
		StringBuffer sb;
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<resp>");
		
		try 
		{
			ArrayList<KeyCategoryBean> list = getSearchKeywordCategorys("1");
			sb = new StringBuffer();
			
			sb.append("<head><status>1000</status><desc><![CDATA[OK]]></desc></head>");
			
			sb.append("<data>");
			for (KeyCategoryBean bean : list) 
			{
				sb.append("<cat>")
					.append("<id>"+bean.getId()+"</id>")
					.append("<name><![CDATA["+bean.getName()+"]]></name>")
					.append("<icon><![CDATA["+bean.getIcon()+"]]></icon>")
					.append("</cat>");
			}
			sb.append("</data>");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			sb = new StringBuffer();
			sb.append("<head><status>1005</status><desc><![CDATA["+e.getMessage()+"]]></desc></head>");
		}
		result.append(sb);
		result.append("</resp>");
		
		return result.toString();
	}
	
	public static String getSearchKeywordChildrenResult(String catID)
	{
		StringBuffer result = new StringBuffer();
		StringBuffer sb;
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<resp>");
		
		try 
		{
			sb = new StringBuffer();
			sb.append("<head><status>1000</status><desc><![CDATA[OK]]></desc></head>");
			sb.append("<data>");
			
			sb.append("<tags>");
			List<KeywordBean> list = getSearchKeywords(catID);
			if(list != null){
				for(KeywordBean tag : list)
				{		
					sb.append("<tag>")
					.append("<name><![CDATA["+tag.getKeyword()+"]]></name>")
					.append("<icon><![CDATA["+tag.getIcon()+"]]></icon>");
					List<KeywordBean> clist = getSearchKeywords(tag.getId());
					if(clist != null){
						for(KeywordBean keyword : clist)
						{		
							sb.append("<item>")
							.append("<name><![CDATA["+keyword.getKeyword()+"]]></name>")
							.append("<icon><![CDATA["+keyword.getIcon()+"]]></icon>")
							.append("<action>")
							.append("<actiontype>").append(keyword.getAction().getActionType()).append("</actiontype>")
							.append("<actionvalue><![CDATA[").append(keyword.getAction().getActionValue()).append("]]></actionvalue>")
							.append("</action>")
							.append("</item>");
						}
					}
					sb.append("</tag>");
				}
			}
			sb.append("</tags>");

			sb.append("</data>");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			sb = new StringBuffer();
			sb.append("<head><status>1005</status><desc><![CDATA["+e.getMessage()+"]]></desc></head>");
		}
		result.append(sb);
		result.append("</resp>");
		
		return result.toString();
	}
	
	public static ArrayList<KeyCategoryBean> getSearchKeywordCategorys(String version)
	{
		if(!"1".equals(version) && !"0".equals(version))
		{
			version = "0";
		}
		
		ArrayList<KeyCategoryBean> list = new ArrayList<KeyCategoryBean>();
		
		Connection conn = MySQLDBIns.getInstance().getConnection();
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` " +
				"WHERE `status` = 0 AND `parent_id` = '0' and `version`="+ version +" order by rank desc;";
		try 
		{
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) 
			{
				KeyCategoryBean bean = new KeyCategoryBean();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setIcon(rs.getString("icon"));
				list.add(bean);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public static List<SearchKeywords> getSearchKeywordListCategorys(String version)
	{
		if(!"1".equals(version) && !"0".equals(version))
		{
			version = "0";
		}
		Connection conn = null;
		List<SearchKeywords> list = new ArrayList<SearchKeywords>();
		
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` " +
				"WHERE `status` = 0 AND `parent_id` = '0' and `version`="+ version +" order by rank desc;";
		try 
		{
			conn = MySQLDBIns.getInstance().getConnection();
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) 
			{
				SearchKeywords bean = new SearchKeywords();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setIcon(rs.getString("icon"));
				list.add(bean);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public static ArrayList<KeywordBean> getSearchKeywords(String catId )
	{
		if(catId == null || "".equals(catId))
		{
			return null;
		}
			
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		
		Connection conn = MySQLDBIns.getInstance().getConnection();
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `status` = 0 AND `parent_id` = '"
				+ catId + "'  ORDER BY `rank` DESC;";
		try 
		{
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
			{
				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setKeyword(rs.getString("name"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setIcon(rs.getString("icon"));
				bean.setRank(rs.getString("rank"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				
				/*
				bean.setChaye_icon(rs.getString("chaye_icon"));
				bean.setChayeIconSize(rs.getString("chaye_icon_size"));
				bean.setChayeAction(new Action(rs.getString("chaye_action_type"), rs.getString("chaye_action_value")));
				*/
				
				list.add(bean);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	
	public static List<SearchKeywords> getSearchKeywordsList(String catId )
	{
		if(catId == null || "".equals(catId))
		{
			return null;
		}
			
		List<SearchKeywords> list = new ArrayList<SearchKeywords>();
		
		Connection conn = MySQLDBIns.getInstance().getConnection();
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `status` = 0 AND `parent_id` = '"
				+ catId + "'  ORDER BY `rank` DESC;";
		try 
		{
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next())
			{
				SearchKeywords bean = new SearchKeywords();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setIcon(rs.getString("icon"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				
				list.add(bean);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public static SearchKeywords getKeywordIdByKeyword(String keyword){
		
		SearchKeywords bean = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `action_value` = ? ;";
		try 
		{
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, keyword);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				bean = new SearchKeywords();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setIcon(rs.getString("icon"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		} 
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		return bean;
	}
	
}
