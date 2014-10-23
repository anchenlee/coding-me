package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminTurnplatAction {
	
	public static boolean isHasGet(String imei,long time){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			String sql = "select status from youhui_luckac.newuser_turnplate where imei = ? and create_time > ?";
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, imei);
			ps.setLong(2, time);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int status = rs.getInt("status");
				if (status != 0) {
					flag = true;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		MySQLDBIns.getInstance().release( conn);
		return flag;
	}

}
