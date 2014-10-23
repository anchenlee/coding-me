package cn.suishou.some.luckac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 参加抽奖记录
 * @author lijun
 * @since 2014-6-9
 */
public class LuckAcRecordDAO {
	
	private static final int STATUS_ZJ = 1;   //中了
	private static final int STATUS_MZJ = 2;   //没中

	private static LuckAcRecordDAO instance = null;
	
	private LuckAcRecordDAO(){}

	public static LuckAcRecordDAO getInstance(){
		if(instance == null){
			instance = new LuckAcRecordDAO();
		}
		return instance;
	}
	
	/**
	 * 添加记录
	 */
	public void add(String uid, LuckActivity ac, boolean isZJ, String ip, String imei, boolean isOldUser,Connection conn){
		int status = isZJ ? STATUS_ZJ : STATUS_MZJ;
		try{
			String sql = "insert into `youhui_luckac`.`luck_ac_record` (`uid`,`luck_ac_id`,`luck_ac_desc`,`timestamp`,`status`,`ip`,`is_old_user`)values(?,?,?,?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, ac.activityId);
			s.setString(3, ac.activityDesc);
			s.setLong(4, System.currentTimeMillis());
			s.setInt(5, status);
			s.setString(6, ip);
			s.setInt(7, isOldUser ? 1:0);
			s.executeUpdate();
			if(isZJ){
				addZj(uid, ac, ip, imei,isOldUser, conn);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 中奖记录
	 * @param uid
	 * @param ac
	 * @param isZJ
	 * @param ip
	 * @param conn
	 */
	public void addZj(String uid, LuckActivity ac, String ip, String imei, boolean isOldUser, Connection conn){
		try{
			String sql = "insert into `youhui_luckac`.`luck_ac_zj` (`uid`,`luck_ac_id`,`luck_ac_desc`,`timestamp`,`imei`,`ip`,`is_old_user`)values(?,?,?,?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, ac.activityId);
			s.setString(3, ac.activityDesc);
			s.setLong(4, System.currentTimeMillis());
			s.setString(5, imei);
			s.setString(6, ip);
			s.setInt(7, isOldUser ? 1:0);
			s.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取中奖次数
	 * @param uid
	 * @return
	 */
	public int getZJTime(String uid, int acId, Connection conn){
		int ret = 0;
		try{
			String sql = "select count(id) as acount from `youhui_luckac`.`luck_ac_zj` where `uid` = ? and `luck_ac_id` = ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, acId);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				ret = rs.getInt("acount");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 获取一个imei下中奖次数
	 * @param uid
	 * @return
	 */
	public int getImeiZJTime(String imei, int acId, Connection conn){
		int ret = 0;
		try{
			String sql = "select count(id) as acount from `youhui_luckac`.`luck_ac_zj` where `imei` = ? and `luck_ac_id` = ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, imei);
			s.setInt(2, acId);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				ret = rs.getInt("acount");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 获取一个ip下中奖次数
	 * @param uid
	 * @return
	 */
	public int getIPZJTime(String ip, int acId, Connection conn){
		int ret = 0;
		try{
			String sql = "select count(id) as acount from `youhui_luckac`.`luck_ac_zj` where `ip` = ? and `luck_ac_id` = ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, ip);
			s.setInt(2, acId);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				ret = rs.getInt("acount");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
}
