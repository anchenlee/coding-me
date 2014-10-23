package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.youhui.dao.MySQLDBIns;

public class TaobaoCatManager {
	
private static TaobaoCatManager instance = null;
	
	public static TaobaoCatManager getInstance(){
		if(instance == null) instance = new TaobaoCatManager();
		return instance;
	}

	public double getRateByCatId(String catId) {
		double rate = 0;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT `rate` FROM `taobao_cat`.`cat` where cat_id = ?;";
			conn = MySQLDBIns.getInstance().getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, catId);
			rs = statement.executeQuery();
			if(rs.next()) {
				rate = rs.getDouble("rate");
			}
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return rate;
	}
	
}
