package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.netting.db.DataSourceFactory;

public class AdminAppconfigDAO {

	public static String getAppconfig(){
		String content = "";
		String sql = "select * from youhui_datamining.tyh_config where ckey = 'appconfig';";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				content = rs.getString("value");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return content;
	}
}
