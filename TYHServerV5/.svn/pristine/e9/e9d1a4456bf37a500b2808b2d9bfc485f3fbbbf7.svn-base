package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.Announcement;
import cn.youhui.bean.ButtonBean;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.CodeUtil;
import cn.youhui.utils.SuiShouActionUtil;


public class AnnouncementManager {

	public static List<Announcement> getAnnouncementList(int page,int status){
		List<Announcement> list = new ArrayList<Announcement>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.tyh_announcement where start_time<? and end_time>? and status = ?;";
		
		try {
			long time = System.currentTimeMillis();
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, time);
			ps.setLong(2, time);
			ps.setInt(3, status);
			
			rs = ps.executeQuery();
			while(rs.next()){
				Announcement bean = new Announcement();
				bean.setId(rs.getInt("id"));
				bean.setTitle(rs.getString("title"));
				bean.setContentUrl(rs.getString("content_url"));
				bean.setCreateTime(rs.getLong("create_time"));
				bean.setUpdateTime(rs.getLong("update_time"));
				bean.setStartTime(CodeUtil.getDateTimeStr(rs.getLong("start_time")));
				bean.setEndTime(CodeUtil.getDateTimeStr(rs.getLong("end_time")));
				bean.setStatus(rs.getInt("status"));
				bean.setDelayTime(rs.getLong("delay_time"));
				bean.setLeftDismissType(rs.getInt("left_dismiss_type"));
				bean.setRightDismissType(rs.getInt("right_dismiss_type"));
				bean.setIsCanDisMiss(rs.getInt("isCanDismiss"));
				bean.setLeftButtonName(rs.getString("left_button_name"));
				bean.setRightButtonName(rs.getString("right_button_name"));
				bean.setLeftButtonAction(rs.getString("left_button_action"));
				bean.setRightButtonAction(rs.getString("right_button_action"));
				bean.setLeftButtonType(rs.getString("left_button_type"));
				bean.setRightButtonType(rs.getString("right_button_type"));	
				bean.setButtonControl(rs.getString("button_control"));				
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
}
