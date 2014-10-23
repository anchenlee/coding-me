package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import cn.youhui.api.activity.ActivityConfig;
import cn.youhui.bean.Activity;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.DateUtil;

public class ActivityManager{
	
	private static ActivityManager instance = null;
	
	private ActivityManager() {
	}

	public static ActivityManager getInstance() {
		if (instance == null)
			instance = new ActivityManager();
		return instance;
	}
	
	public Activity getActivityByKey(String key){
		Activity bean = new Activity();
		Connection conn = null;
		try {
			conn =  MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_cn_fanli.tyh_activity where `key`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, key);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				bean.setId(rs.getString("id"));
				bean.setDescription(rs.getString("description"));
				bean.setName(rs.getString("name"));
				bean.setFrequency(rs.getInt("frequency"));
				bean.setJfbNum(rs.getInt("jfb_num"));
				bean.setKey(rs.getString("key"));
				bean.setRule(rs.getString("rule"));
				bean.setStartTime(rs.getLong("start_time"));
				bean.setEndTime(rs.getLong("end_time"));
				bean.setTimestamp(rs.getLong("timestamp"));
			}else{
				bean = null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return bean;
	}
	
	public boolean isExsitKey(String key){
		boolean flag = false;
		Connection conn = null;
		try {
			conn =  MySQLDBIns.getInstance().getConnection();
			String sql = "select id from youhui_cn_fanli.tyh_activity where `key`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, key);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public int joinActivity(String key, String uid){
		int ret = ActivityConfig.ACTIVITY_EXCEPTION;
		Connection conn = null;
		try {
			conn =  MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_cn_fanli.tyh_activity where `key`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, key);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				long startTime = rs.getLong("start_time");
				long endTime = rs.getLong("end_time");
				if(System.currentTimeMillis() < startTime){
					ret = ActivityConfig.ACTIVITY_NOT_START;          //活动未开始
					return ret;
				}
				if(System.currentTimeMillis() > endTime){
					ret = ActivityConfig.ACTIVITY_HAS_END;            //活动已结束
					return ret;
				}
				String activityId = rs.getString("id");
				long lastJoinTime = ActivityJoinManager.getInstance().getLastJoinTime(uid, activityId);
				int frequency = rs.getInt("frequency");
				boolean allow = false;
				if(frequency == 1){          //只能参加一次
					if(lastJoinTime == 0){
						allow = true;
					}
				}else if(frequency == 2){     //一天参加一次
					if(checkDay(lastJoinTime)){
						allow = true;
					}
				}else if(frequency == 3){
					if(checkWeek(lastJoinTime)){  //一周参加一次
						allow = true;
					}
				}else if(frequency == 4){      //每次都可以参加
					allow = true;
				}
				if(!allow){
					ret = ActivityConfig.ACTIVITY_HAS_JOIN;            //已参加过
				}else{
					ret = ActivityConfig.ACTIVITY_CHECK_PASS;       //通过
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	/**
	 * 检测lastTime是否在今天,在则返回false,不在则返回true   //lastTime < now
	 * @param lastTime
	 * @return
	 */
	private boolean checkDay(long lastTime){
		long diff = DateUtil.differ(new Date(), new Date(lastTime));
		if(diff == 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 检测lastTime是否在本周,在则返回false,不在则返回true   //lastTime < now
	 * @param lastTime
	 * @return
	 */
	private boolean checkWeek(long lastTime){
		if(new Date(lastTime).getDay() > new Date().getDay() || DateUtil.differ(new Date(), new Date(lastTime)) >= 7){
			return true;
		}else {
		    return false;
		}
	}
	
}
