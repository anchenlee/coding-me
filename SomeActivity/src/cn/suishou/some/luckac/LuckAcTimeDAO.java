package cn.suishou.some.luckac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LuckAcTimeDAO {

	private static LuckAcTimeDAO instance = null;
	
	private LuckAcTimeDAO(){}

	public static LuckAcTimeDAO getInstance(){
		if(instance == null){
			instance = new LuckAcTimeDAO();
		}
		return instance;
	}
	
	/**
	 * 是否有抽奖次数
	 * @param uid
	 * @param acId
	 * @param conn
	 * @return
	 */
	public int hasEnoughTime(String uid, LuckActivity ac, Connection conn){
		int ret = 0;
		try{
			String sql = "select `uid`,`all_times`,`use_times` from `youhui_luckac`.`luck_ac_times` where `uid`=? and `luck_ac_id`=?;";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, uid);
			state.setInt(2, ac.activityId);
			ResultSet rs = state.executeQuery();
			if(rs.next()){
				if(rs.getInt("all_times") > rs.getInt("use_times")){
					ret = 0;
					addOneTime(uid, ac.activityId, conn);
				}else{
					ret = rs.getInt("use_times");
				}
			}else{
				if(ac.initJoinTime > 0){
					add(uid, ac.activityId, ac.initJoinTime, conn);
					ret = 0;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 默认已用一次
	 * @param uid
	 * @param acId
	 * @param times
	 * @param conn
	 */
	public void add(String uid, int acId, int times, Connection conn){
		try{
			String sql = "insert into `youhui_luckac`.`luck_ac_times` (`uid`,`luck_ac_id`,`all_times`,`use_times`)values(?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, acId);
			s.setInt(3, times);
			s.setInt(4, 1);
			s.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用一次
	 * @param uid
	 * @param acId
	 * @param times
	 * @param conn
	 */
	public void addOneTime(String uid, int acId, Connection conn){
		try{
			String sql = "update `youhui_luckac`.`luck_ac_times` set `use_times`=`use_times`+1 where `uid`=? and `luck_ac_id`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, acId);
			s.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
