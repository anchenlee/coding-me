package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.HotKeyword;
import cn.youhui.dao.MySQLDBIns;

public class HotKeywordManager {

	public static List<HotKeyword> getHotKeywordList(){
		List<HotKeyword> list = new ArrayList<HotKeyword>();
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT * FROM youhui_datamining.tyh_hot_keyword where status =0 order by rank desc;";
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				HotKeyword bean = new HotKeyword();
				bean.setKeyword(rs.getString("keyword"));
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	
}
