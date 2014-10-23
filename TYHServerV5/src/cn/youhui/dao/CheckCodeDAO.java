package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckCodeDAO {

	private static final int STATUS_VALID = 0;
	private static final int STATUS_OCCUPIED = 1;
	private static final int STATUS_BUY = 1;
	public static final int PAYSUCCESS = 1;
	public static final int PAYFAIL = 0;
	
	public static boolean verifyCode(String code,String itemId){
		Connection conn = null;
		PreparedStatement s = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(1) as c from `youhui_purchase`.`second_kill` where code=? and status=?  and paid=? and pid=?;";
			s = conn.prepareStatement(sql);
			s.setString(1, code);
			s.setInt(2, STATUS_OCCUPIED);
			s.setInt(3, STATUS_BUY);
			s.setString(4,itemId);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				if (rs.getInt("c") > 0) {
					return true;
				}else {
					return false;
				}
			}
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			MySQLDBIns.getInstance().release( conn);
		}
	}
	
}
