package cn.youhuiWeb.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.youhui.dao.MySQLDBIns;
import cn.youhuiWeb.bean.HotRecommendView;

public class HotRecommendManager {

	private static HotRecommendManager instance = null;
	
	public static HotRecommendManager getInstance(){
		if(instance == null) instance = new HotRecommendManager();
		return instance;
	}
	
	public HotRecommendView getHotRecommendView()
	{	
		String sql = "SELECT * FROM youhui_web.hot_recommend_view";
				
		Connection conn = MySQLDBIns.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next())
			{
				HotRecommendView hotRecommendView = new HotRecommendView();				
				hotRecommendView.setId(rs.getString("id"));
				hotRecommendView.setContent(rs.getString("content"));
				return hotRecommendView;
			}else{
				return null;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		} 
		finally 
		{
			MySQLDBIns.getInstance().release(ps, conn);
		}		
	}
}
