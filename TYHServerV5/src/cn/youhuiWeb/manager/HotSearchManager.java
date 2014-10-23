package cn.youhuiWeb.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.dao.MySQLDBIns;
import cn.youhuiWeb.bean.HotSearch;

public class HotSearchManager {

	private static HotSearchManager instance = null;
	
	public static HotSearchManager getInstance(){
		if(instance == null) instance = new HotSearchManager();
		return instance;
	}
	
	public List<HotSearch> getHotSearchList(String catagoryId){
		List<HotSearch> list = new ArrayList<HotSearch>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		PreparedStatement ps = null;
		try {			
			String sql = "SELECT * FROM youhui_web.hot_search a, youhui_datamining.tyh_ad_up b  WHERE a.cat_id = b.id AND b.id = ? order by a.rank asc ";
			ps = conn.prepareStatement(sql);	
			
			ps.setString(1, catagoryId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				HotSearch hotSearch = new HotSearch();
				
				hotSearch.setId(rs.getString("a.id"));
				hotSearch.setCatId(rs.getString("a.cat_id"));
				hotSearch.setIsHot(rs.getString("a.is_hot"));
				hotSearch.setKeyword(rs.getString("a.keyword"));
				hotSearch.setRank(rs.getInt("a.rank"));
				hotSearch.setTimestamp(rs.getString("a.timestamp"));	
				
				list.add(hotSearch);
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
