package cn.youhuiWeb.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.dao.MySQLDBIns;
import cn.youhuiWeb.bean.CatagoryAD;

public class CatagoryAdManager {

	private static CatagoryAdManager instance = null;
	
	public static CatagoryAdManager getInstance(){
		if(instance == null) instance = new CatagoryAdManager();
		return instance;
	}
	
	public List<CatagoryAD> getAds(String catagoryId){
		List<CatagoryAD> list = new ArrayList<CatagoryAD>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		PreparedStatement ps = null;
		try {
			long now = System.currentTimeMillis();
			String sql = "SELECT * FROM youhui_web.catagory_ad a, youhui_datamining.tyh_ad_up b  WHERE a.start_time < ? AND a.end_time > ? AND a.cat_id = b.id AND b.id = ? order by a.rank asc ";
			ps = conn.prepareStatement(sql);			
			ps.setLong(1, now);
			ps.setLong(2, now);
			ps.setString(3, catagoryId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				CatagoryAD ad = new CatagoryAD();
				
				ad.setId(rs.getString("a.id"));
				ad.setTitle(rs.getString("a.ad_title"));
				ad.setDescription(rs.getString("a.description"));
				ad.setUpdatetime(rs.getString("a.timestamp"));
				ad.setType(rs.getString("a.ad_type"));
				ad.setImg(rs.getString("a.img"));				
				ad.setActionType(rs.getString("a.action_type"));
				ad.setActionValue(rs.getString("a.action_value"));		
				ad.setStart_time(rs.getString("a.start_time"));
				ad.setEnd_time(rs.getString("a.end_time"));
				ad.setRank(rs.getInt("a.rank"));
				ad.setCatId(rs.getString("a.cat_id"));
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
