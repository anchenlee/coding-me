package cn.youhuiWeb.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.dao.MySQLDBIns;
import cn.youhuiWeb.bean.AD;

public class AdManager {

	private static AdManager instance = null;
	
	public static AdManager getInstance(){
		if(instance == null) instance = new AdManager();
		return instance;
	}
	
	public List<AD> getAds(){
		List<AD> list = new ArrayList<AD>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		PreparedStatement ps = null;
		try {
			long now = System.currentTimeMillis();
			String sql = "SELECT * FROM youhui_web.ad WHERE start_time < ? AND end_time > ? order by rank asc ";
			ps = conn.prepareStatement(sql);			
			ps.setLong(1, now);
			ps.setLong(2, now);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				AD ad = new AD();
				
				ad.setId(rs.getString("id"));
				ad.setTitle(rs.getString("ad_title"));
				ad.setDescription(rs.getString("description"));
				ad.setUpdatetime(rs.getString("timestamp"));
				ad.setType(rs.getString("ad_type"));
				ad.setImg(rs.getString("img"));				
				ad.setActionType(rs.getString("action_type"));
				ad.setActionValue(rs.getString("action_value"));		
				ad.setStart_time(rs.getString("start_time"));
				ad.setEnd_time(rs.getString("end_time"));
				ad.setRank(rs.getInt("rank"));
				
				list.add(ad);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			MySQLDBIns.getInstance().release(ps,conn);
		}
	}
	
}
