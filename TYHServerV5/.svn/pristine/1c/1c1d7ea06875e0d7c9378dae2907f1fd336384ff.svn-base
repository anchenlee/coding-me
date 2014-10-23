package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.youhui.api.activity.ActivityRequest;
import cn.youhui.bean.Activity;
import cn.youhui.dao.MySQLDBIns;

public class ActivityExceptionManager {
	
private ActivityExceptionManager(){}

    private static ActivityExceptionManager instance = null;
    
    private static final int STATUS_INIT = 0;
    
	public static ActivityExceptionManager getInstance(){
		if(instance == null) instance = new ActivityExceptionManager();
		return instance;
	}
	
	public boolean addException(ActivityRequest request, Activity activity){
		boolean flag = false;	
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_cn_fanli.tyh_activity_join_exception(`uid`,`activity_id`,`activity_unique_id`,`activity_name`,`jfb_num`,`status`,`insert_time`) values(?,?,?,?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, request.getUid());
			s.setString(2, activity.getId());
			s.setString(3, request.getActivityUniqueId());
			s.setString(4, activity.getName());
			s.setInt(5, request.getJfbNum());
			s.setInt(6, STATUS_INIT);
			s.setLong(7, System.currentTimeMillis());
			int n = s.executeUpdate();
			if(n > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
}
