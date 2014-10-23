package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netting.bean.Action;
import com.netting.bean.Announcement;
import com.netting.bean.ButtonBean;
import com.netting.bean.SuiShouAction;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;
import com.netting.util.SuiShouActionUtil;

public class AdminAnnouncementDAO {

	private static int normalStatus = 0;
	
	private static int delStatus = 1;
	
	private static int pageSize = 20;
	
	public static List<Announcement> getAnnouncementList(int page,int status,String type){
		List<Announcement> list = new ArrayList<Announcement>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long time = System.currentTimeMillis();
		String sql = "SELECT * FROM youhui_datamining.tyh_announcement where  status = ? ";
		
		if("0".equals(type)){
			sql +=" and  start_time > "+time+" ";
		}else if("2".equals(type)){
			sql +=" and  end_time < "+time+" ";
		}else{
			sql += " and  start_time < "+time+" and  end_time > "+time+" ";
		}
		sql +="  order by start_time desc limit ?,?; ";
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, (page-1)*pageSize);
			ps.setInt(3, pageSize);
			
			rs = ps.executeQuery();
			while(rs.next()){
				Announcement bean = new Announcement();
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("title"));
				bean.setContentUrl(rs.getString("content_url"));
				bean.setDelayTime(rs.getLong("delay_time"));
				bean.setStartTime(rs.getLong("start_time"));
				bean.setEndTime(rs.getLong("end_time"));
				bean.setShowType(rs.getInt("show_type"));
				bean.setStatus(rs.getInt("status"));
				bean.setStartDate(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEndDate(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setLeftButton(new ButtonBean(rs.getString("left_button_name"),new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(rs.getString("left_button_action_type"), rs.getString("left_button_action_value"))),new Action(rs.getString("left_button_action_type"), rs.getString("left_button_action_value"))));
				bean.setRightButton(new ButtonBean(rs.getString("right_button_name"), new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(rs.getString("right_button_action_type"), rs.getString("right_button_action_value"))),new Action(rs.getString("right_button_action_type"), rs.getString("right_button_action_value"))));				
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return list;
	}
	
	
	public static List<Announcement> getAllAnnouncementList(){
		List<Announcement> list = new ArrayList<Announcement>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long time = System.currentTimeMillis();
		String sql = "SELECT * FROM youhui_datamining.tyh_announcement where  status = ? and end_time >"+time+"  ";

		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			
			rs = ps.executeQuery();
			while(rs.next()){
				Announcement bean = new Announcement();
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("title"));
				bean.setContentUrl(rs.getString("content_url"));
				bean.setDelayTime(rs.getLong("delay_time"));
				bean.setStartTime(rs.getLong("start_time"));
				bean.setEndTime(rs.getLong("end_time"));
				bean.setShowType(rs.getInt("show_type"));
				bean.setStatus(rs.getInt("status"));
				bean.setStartDate(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEndDate(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setLeftButton(new ButtonBean(rs.getString("left_button_name"),new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(rs.getString("left_button_action_type"), rs.getString("left_button_action_value"))),new Action(rs.getString("left_button_action_type"), rs.getString("left_button_action_value"))));
				bean.setRightButton(new ButtonBean(rs.getString("right_button_name"), new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(rs.getString("right_button_action_type"), rs.getString("right_button_action_value"))),new Action(rs.getString("right_button_action_type"), rs.getString("right_button_action_value"))));				
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return list;
	}
	
	public static int getAnnouncementListPage(int page,int status,String type){
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long time = System.currentTimeMillis();
		String sql = "SELECT count(*) as c FROM youhui_datamining.tyh_announcement where status = ? ";
		if("0".equals(type)){
			sql +=" and  start_time > "+time+" ";
		}else if("2".equals(type)){
			sql +=" and  end_time < "+time+" ";
		}else{
			sql += " and  start_time < "+time+" and  end_time > "+time+" ";
		}

		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		if(count % pageSize == 0){
			count = count / pageSize;
		}else{
			count = count / pageSize + 1;
		}
		return count;
	}
	
	public static Announcement getAnnouncement(String id){
		Announcement bean = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.tyh_announcement where id=?; ";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			if(rs.next()){
				bean = new Announcement();
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("title"));
				bean.setContentUrl(rs.getString("content_url"));
				bean.setDelayTime(rs.getLong("delay_time"));
				bean.setStartTime(rs.getLong("start_time"));
				bean.setStartDate(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEndTime(rs.getLong("end_time"));
				bean.setEndDate(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setShowType(rs.getInt("show_type"));
				bean.setStatus(rs.getInt("status"));
				bean.setDelType(rs.getInt("del_type"));
				bean.setLeftButton(new ButtonBean(rs.getString("left_button_name"),new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(rs.getString("left_button_action_type"), rs.getString("left_button_action_value"))) ,new Action(rs.getString("left_button_action_type"), rs.getString("left_button_action_value"))));
				bean.setRightButton(new ButtonBean(rs.getString("right_button_name"), new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(rs.getString("right_button_action_type"), rs.getString("right_button_action_value"))),new Action(rs.getString("right_button_action_type"), rs.getString("right_button_action_value"))));				
							
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return bean;
	}
	
	public static boolean addAnnouncement(Announcement bean){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into youhui_datamining.tyh_announcement(`title`,`content_url`,`status`,`create_time`,`update_time`,`start_time`,`end_time`,`delay_time`,`show_type`,`left_button_name`,`left_button_action_type`,`left_button_action_value`,`right_button_name`,`right_button_action_type`,`right_button_action_value`,`del_type`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn = DataSourceFactory.getConnection();
			long time = System.currentTimeMillis();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getTitle());
			ps.setString(2, bean.getContentUrl());
			ps.setInt(3, normalStatus);
			ps.setLong(4, time);
			ps.setLong(5, time);
			ps.setLong(6, bean.getStartTime());
			ps.setLong(7, bean.getEndTime());
			ps.setLong(8, bean.getDelayTime());
			ps.setInt(9, bean.getShowType());
			ps.setString(10, bean.getLeftButton().getName());
			ps.setString(11, bean.getLeftButton().getAction().getActionType());
			ps.setString(12, bean.getLeftButton().getAction().getActionValue());
			ps.setString(13, bean.getRightButton().getName());
			ps.setString(14, bean.getRightButton().getAction().getActionType());
			ps.setString(15, bean.getRightButton().getAction().getActionValue());
			ps.setInt(16, bean.getDelType());
			
			ps.executeUpdate();
			flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return flag;
	}
	
	public static boolean updateAnnouncement(Announcement bean){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = " update youhui_datamining.tyh_announcement set `title`=? ,`content_url`=?, `update_time`=?,`start_time`=? ,`end_time`=? ,`delay_time`=?,`show_type`=?,`del_type`=?,`left_button_name`=?,`right_button_name`=?,`left_button_action_type`=?,`left_button_action_value`=?,`right_button_action_type`=?,`right_button_action_value`=? where id=?";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bean.getTitle());
			ps.setString(2, bean.getContentUrl());
			ps.setLong(3, System.currentTimeMillis());
			ps.setLong(4, bean.getStartTime());
			ps.setLong(5, bean.getEndTime());
			ps.setLong(6, bean.getDelayTime());
			ps.setInt(7, bean.getShowType());
			ps.setInt(8, bean.getDelType());
			ps.setString(9, bean.getLeftButton().getName());
			ps.setString(10, bean.getRightButton().getName());
			ps.setString(11, bean.getLeftButton().getAction().getActionType());
			ps.setString(12, bean.getLeftButton().getAction().getActionValue());
			ps.setString(13, bean.getRightButton().getAction().getActionType());
			ps.setString(14, bean.getRightButton().getAction().getActionValue());
			ps.setString(15, bean.getId());
			
			ps.executeUpdate();
			flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	
	public static boolean delAnnouncement(String id){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = " update youhui_datamining.tyh_announcement set `status`=?  where id=?";
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, delStatus);
			ps.setString(2, id);
			ps.executeUpdate();
			flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
}
