/**
 * 
 */
package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author lujiabin
 * @since 2014-8-26
 */
public class AdwoThreadDao {
	private static AdwoThreadDao instance;
	
	public static AdwoThreadDao getInstance() {
		return instance == null ? new AdwoThreadDao() : instance;
	}
	
	
	public boolean getMacAddress(String idfa) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `idfa` from `suishou_tg`.`data_with_adwo` where `idfa`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, idfa);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	public boolean getMacAddress4midi(String idfa) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `idfa` from `suishou_tg`.`data_with_mid` where `idfa`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, idfa);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	public void update(long activatetime,String uid,int status,String idfa){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `suishou_tg`.`data_with_adwo` SET `callback_status`=?, `activate_time`=?, `uid`=? WHERE `idfa`=? and `callback_status`=-1;";
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setLong(2, activatetime);
			ps.setString(3, uid);
			ps.setString(4, idfa);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
	}
	public void update4midi(long activatetime,String uid,int status,String idfa){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `suishou_tg`.`data_with_mid` SET `status`=?, `activate_time`=?, `uid`=? WHERE `idfa`=? and `status`=-1;";
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setLong(2, activatetime);
			ps.setString(3, uid);
			ps.setString(4, idfa);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
	}
	
	public boolean update2(long activatetime,String uid,int status,String idfa){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `suishou_tg`.`data_with_adwo` SET `callback_status`=?, `login_timestamp`=?, `uid`=? WHERE `idfa`=? and `callback_status`=3;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setLong(2, activatetime);
			ps.setString(3, uid);
			ps.setString(4, idfa);
			int row = ps.executeUpdate();
			if(row > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	public String getCallback(String idfa){
		String tmp="";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "select * from  `suishou_tg`.`data_with_mid`  WHERE `idfa`=? ;";
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,idfa);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				String callback=rs.getString("ad_callback");
				String appid=rs.getString("appid");
				String mac=rs.getString("mac");
				if(callback==null||callback.equals("")){
					tmp="http://api.miidi.net/cas/advAck.bin?appid="+appid+"&idfa="+idfa+"&mac="+mac;
				}else{
					tmp=callback;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return tmp;
	}
	
	public boolean update24midi(long activatetime,String uid,int status,String idfa){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `suishou_tg`.`data_with_mid` SET `status`=?, `login_timestamp`=?, `uid`=? WHERE `idfa`=? and `status`=3;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setLong(2, activatetime);
			ps.setString(3, uid);
			ps.setString(4, idfa);
			int row = ps.executeUpdate();
			if(row > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	public long getCreateTime(String idfa) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT create_time from `suishou_tg`.`data_with_adwo` where `idfa`=?";
		long time = 0;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, idfa);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				time = rs.getLong("create_time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return time;
	}
	public long getCreateTime4midi(String idfa) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT create_timestamp from `suishou_tg`.`data_with_mid` where `idfa`=?";
		long time = 0;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, idfa);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				time = rs.getLong("create_timestamp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return time;
	}
	
}
