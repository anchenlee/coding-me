package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;

import cn.youhui.dao.MySQLDBIns;


public class LuckAcTimesManager {

	private static LuckAcTimesManager instance = null;
	
	private LuckAcTimesManager(){}

	public static LuckAcTimesManager getInstance(){
		if(instance == null){
			instance = new LuckAcTimesManager();
		}
		return instance;
	}
	
	
	/**
	 * 加一次麦当劳抽奖机会
	 * @param uid
	 * @param acId
	 */
	public void addMcdOneTime(String uid){
		int acId = 1000;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into `youhui_luckac`.`luck_ac_times` (`uid`,`luck_ac_id`,`all_times`,`use_times`)values(?,?,?,?) ON DUPLICATE KEY UPDATE `all_times`=`all_times`+1;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, acId);
			s.setInt(3, 2);           //默认一次，加分享一次  共两次
			s.setInt(4, 0);
			s.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
	}
	
}
