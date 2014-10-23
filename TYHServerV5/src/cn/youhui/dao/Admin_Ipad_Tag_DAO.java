package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import cn.youhui.bean.Action;
import cn.youhui.bean.Tag_Bean;
import cn.youhui.utils.CodeUtil;

import com.netting.bean.IpadTagBean;

/**
 * 
 * @author 
 */
public class Admin_Ipad_Tag_DAO
{
	
	public static boolean addIpadTag(String ipadTagId,String x, String y, String martix)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try 
		{
			String sql = "insert into youhui_datamining.tyh_ipad_tags(ipad_tag_id,x,y,enable,create_time,update_time,martix) values(?,?,?,?,?,?,?);";
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ipadTagId);
			ps.setString(2, x);
			ps.setString(3, y);
			ps.setString(4, "1");
			ps.setLong(5, System.currentTimeMillis());
			ps.setLong(6, System.currentTimeMillis());
			ps.setString(7, martix);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		MySQLDBIns.getInstance().release( conn);
		return flag;
	}
	
	public static boolean delIpadTag(String x, String y, String martix)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try 
		{
			String sql = "update youhui_datamining.tyh_ipad_tags set enable = 0 where x=? and y=? and martix=? and enable = 1";
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, x);
			ps.setString(2, y);
			ps.setString(3, martix);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		MySQLDBIns.getInstance().release( conn);
		return flag;
	}
	
	public static boolean updateIpadTag(String ipadTagId, String x, String y, String martix)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try 
		{
			String sql = "update youhui_datamining.tyh_ipad_tags set ipad_tag_id = ? where x=? and y=? and martix=? enable = 1";
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ipadTagId);
			ps.setString(2, x);
			ps.setString(3, y);
			ps.setString(4, martix);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		MySQLDBIns.getInstance().release( conn);
		return flag;
	}
	
	public static String getMartixIds()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String value = "";
		try 
		{
			String sql = "select martix from youhui_datamining.tyh_ipad_tags group by martix;";
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				value += rs.getString("martix") + ",";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		MySQLDBIns.getInstance().release( conn);
		return value;
	}
	
	public static List<IpadTagBean> getIpadTagsByMartix(String martix)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IpadTagBean> list = new ArrayList<IpadTagBean>();
		try 
		{
			String sql = "select * from youhui_datamining.tyh_ipad_tags where martix = ? and enable = 1 and action_type != '' and action_type is not null;";
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, martix);
			rs = ps.executeQuery();
			while(rs.next()){
				IpadTagBean bean = new IpadTagBean();
				bean.setId(rs.getString("id"));
				bean.setActionType(rs.getString("action_type"));
				bean.setActionValue(rs.getString("action_value"));
				bean.setImg(rs.getString("img"));
				bean.setRegularTimeImg(rs.getString("regular_time_img"));
				bean.setRegularStartTime(rs.getLong("regular_start_time"));
				bean.setRegularEndTime(rs.getLong("regular_end_time"));
				bean.setMartix(rs.getString("martix"));
				bean.setX(rs.getString("x"));
				bean.setY(rs.getString("y"));
				bean.setDivIds(rs.getString("div_ids"));
				bean.setEnable(rs.getString("enable"));
				bean.setCreateTime(rs.getString("create_time"));
				bean.setUpdateTime(rs.getString("update_time"));
				bean.setTag_action(new Action(bean.getActionType(), bean.getActionValue()));
				list.add(bean);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		MySQLDBIns.getInstance().release( conn);
		return list;
	}
	
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
			conn = MySQLDBIns.getInstance().getConnection();
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
		catch (Exception e) {
			e.printStackTrace();
		} 
		MySQLDBIns.getInstance().release( conn);
		return bean;
	}
}
