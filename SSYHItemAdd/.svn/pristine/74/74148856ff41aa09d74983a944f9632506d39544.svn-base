package cn.youhui.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import cn.youhui.bean.Action;
import cn.youhui.bean.TagBean;
import cn.youhui.platform.db.SQL;
import cn.youhui.platform.util.TimeUtil;


public class AdminTagDAO {

	
	public static TagBean getTagBeanByKeyword(String keyword)
	{
		TagBean bean = new TagBean();
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where `keyword`=?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, keyword);
			
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
				bean.setTagAction(new Action(actionType, actionValue));
				
				bean.setDescription(rtst.getString("description"));
				bean.setIsPadTag(rtst.getInt("istag"));
				bean.setPad_icon(rtst.getString("tag_icon"));
				bean.setPad_rank(rtst.getInt("tag_rank"));
				bean.setSmall_show(rtst.getString("show"));
				
				String chayeActionType = rtst.getString("chaye_action_type");
				String chayeActionValue = rtst.getString("chaye_action_value");
				bean.setChayeAction(new Action(chayeActionType, chayeActionValue));
				
				bean.setChaye_icon(rtst.getString("chaye_icon"));
				bean.setChaye_icon_size(rtst.getString("chaye_icon_size"));
				bean.setLock_num(rtst.getInt("lockstatue"));
				bean.setHeat(rtst.getString("heat"));
				
				bean.setBgcolor(rtst.getString("bgcolor"));
				// 集分宝返利比例
				bean.setJfb_rate(rtst.getString("jfb_rate"));
				
				bean.setHas_son_tag_all(rtst.getString("has_son_tag_all"));
				bean.setDefault_son_tag_id(rtst.getString("default_son_tag_id"));
				bean.setStartTime(TimeUtil.getDateTimeStr(rtst.getLong("sale_time")));
				
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
		
		return bean;
	}
	
	public static TagBean getTagBean(String tagId)
	{
		TagBean bean = new TagBean();
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where `id`=?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, tagId);
			
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
				bean.setTagAction(new Action(actionType, actionValue));
				
				bean.setDescription(rtst.getString("description"));
				bean.setIsPadTag(rtst.getInt("istag"));
				bean.setPad_icon(rtst.getString("tag_icon"));
				bean.setPad_rank(rtst.getInt("tag_rank"));
				bean.setSmall_show(rtst.getString("show"));
				
				String chayeActionType = rtst.getString("chaye_action_type");
				String chayeActionValue = rtst.getString("chaye_action_value");
				bean.setChayeAction(new Action(chayeActionType, chayeActionValue));
				
				bean.setChaye_icon(rtst.getString("chaye_icon"));
				bean.setChaye_icon_size(rtst.getString("chaye_icon_size"));
				bean.setLock_num(rtst.getInt("lockstatue"));
				bean.setHeat(rtst.getString("heat"));
				
				bean.setBgcolor(rtst.getString("bgcolor"));
				// 集分宝返利比例
				bean.setJfb_rate(rtst.getString("jfb_rate"));
				
				bean.setHas_son_tag_all(rtst.getString("has_son_tag_all"));
				bean.setDefault_son_tag_id(rtst.getString("default_son_tag_id"));
				bean.setStartTime(TimeUtil.getDateTimeStr(rtst.getLong("sale_time")));
				
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
		
		return bean;
	}
	
	public static TagBean getTagBean(String tagId,Connection conn)
	{
		TagBean bean = new TagBean();
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` where `id`=?;";
		
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, tagId);
			
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
				bean.setTagAction(new Action(actionType, actionValue));
				
				bean.setDescription(rtst.getString("description"));
				bean.setIsPadTag(rtst.getInt("istag"));
				bean.setPad_icon(rtst.getString("tag_icon"));
				bean.setPad_rank(rtst.getInt("tag_rank"));
				bean.setSmall_show(rtst.getString("show"));
				
				String chayeActionType = rtst.getString("chaye_action_type");
				String chayeActionValue = rtst.getString("chaye_action_value");
				bean.setChayeAction(new Action(chayeActionType, chayeActionValue));
				
				bean.setChaye_icon(rtst.getString("chaye_icon"));
				bean.setChaye_icon_size(rtst.getString("chaye_icon_size"));
				bean.setLock_num(rtst.getInt("lockstatue"));
				bean.setHeat(rtst.getString("heat"));
				
				bean.setBgcolor(rtst.getString("bgcolor"));
				// 集分宝返利比例
				bean.setJfb_rate(rtst.getString("jfb_rate"));
				
				bean.setHas_son_tag_all(rtst.getString("has_son_tag_all"));
				bean.setDefault_son_tag_id(rtst.getString("default_son_tag_id"));
				bean.setStartTime(TimeUtil.getDateTimeStr(rtst.getLong("sale_time")));
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		return bean;
	}
	
	public static List<TagBean> getTags(String parentId){
		String sql = "SELECT `id`,`keyword` FROM `youhui_datamining`.`m_discount_keywords` where `parent_id` = ? and `effective`='0' ";
		List<TagBean> list = new ArrayList<TagBean>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, parentId);
			
			rtst = ps.executeQuery();
			while (rtst.next()){
				TagBean bean = new TagBean();
				bean.setTag_id(rtst.getString("id"));
				bean.setTag_name(rtst.getString("keyword"));
				list.add(bean);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally 
		{
			SQL.getInstance().release(ps, conn);
		}
		
		return list;
	}
}
