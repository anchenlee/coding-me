package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.youhui.dao.MySQLDBIns;

public class GiftActivityManager {
	
	private static final String activityId = "8";
	private static final int LIMIT_TIMES = 3;
	
	private static SimpleDateFormat ft1 = new SimpleDateFormat("yyyy/MM/dd");	
	private static GiftActivityManager instance = null;
	
	private GiftActivityManager(){}
	
	public static GiftActivityManager getInstance(){
		if(instance == null){
			instance = new GiftActivityManager();
		}
		return instance;
	}
	
	/**
	 * 是否超过一天的次数限制
	 * @param uid
	 * @return
	 */
	public boolean isLimitToday(String uid){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String today = ft1.format(new Date());
			String sql = "select count(id) as acount from youhui_cn_fanli.tyh_activity_join where uid = ? and activity_id = ? and `date`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, activityId);
			s.setString(3, today);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				int count = rs.getInt("acount");
				if(count > LIMIT_TIMES){
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
}
