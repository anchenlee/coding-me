package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.youhui.dao.MySQLDBIns;

/**
 * 高弘扬推荐系统模型
 * @author lijun
 * @since 2014-02-21
 */
public class RecomSysMode {
	
	private static RecomSysMode instance;
	
	private RecomSysMode() {
	}
	
	public static RecomSysMode getInstance() {
		if (instance == null)
			instance = new RecomSysMode();
		return instance;
	}
	
	/**
	 * 是否在推荐模型两百人中
	 * @param lastTime
	 * @return
	 */
	public boolean isInUser200(String uid){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select id from `tmp`.`user2` where uid = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
}
