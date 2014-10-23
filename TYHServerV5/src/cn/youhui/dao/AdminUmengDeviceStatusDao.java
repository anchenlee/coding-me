package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdminUmengDeviceStatusDao {
	
	private static final int LOGON = 1; 
	private static final int LOGOFF = 0;

	public static boolean changUmDevStatus(String device_token, String uid){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		int status = 0;
		if ("".equals(uid) || uid == null) {
			status = LOGOFF;
		}else {
			status = LOGON;
		}
		try 
		{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_datamining.umeng_device_status(device_token,status,create_time,update_time) values(?,?,?,?)	" +
						"ON DUPLICATE KEY UPDATE `status`=?, `update_time`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, device_token);
			ps.setInt(2, status);
			ps.setLong(3, System.currentTimeMillis());
			ps.setLong(4, System.currentTimeMillis());
			ps.setInt(5, status);
			ps.setLong(6, System.currentTimeMillis());
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			MySQLDBIns.getInstance().release( conn);
		}
		return flag;
	}
	
}
