package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.FrameTitle;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.SuiShouActionUtil;

public class FrameTitleManager {

	public static List<FrameTitle> getFrameTitleList(String platform){
		List<FrameTitle> list = new ArrayList<FrameTitle>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.tyh_ad_up where status = '1' and (version = 'cell' or version = 'custom') order by rank desc;";
		
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()){
				FrameTitle bean = new FrameTitle();
				bean.setActionType(rs.getString("action_type"));
				bean.setActionValue(rs.getString("action_value"));
				String actionType = rs.getString("action_type");
				if ("tagStyleCategory".equals(actionType)) {
					actionType = "tagStyleGrid";
				}
				bean.setSsac(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,actionType, new String[]{rs.getString("action_value"),rs.getString("title")})));
				bean.setId(rs.getString("id"));
				bean.setImg(rs.getString("img"));
				bean.setHiddenImg(rs.getString("hidden_img"));
				bean.setTitle(rs.getString("title"));
				bean.setRank(rs.getInt("rank"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		
		return list;
	}
	
	public static List<FrameTitle> getIpadFrameTitleList(String platform){
		List<FrameTitle> list = new ArrayList<FrameTitle>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.tyh_ad_up where status = '1' and (version = 'pad' or version = 'custom') order by rank desc;";
		
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()){
				FrameTitle bean = new FrameTitle();
				bean.setActionType(rs.getString("action_type"));
				bean.setActionValue(rs.getString("action_value"));
				String actionType = rs.getString("action_type");
				if ("tagStyleCategory".equals(actionType)) {
					actionType = "tagStyleGrid";
				}
				bean.setSsac(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,actionType, new String[]{rs.getString("action_value"),rs.getString("title")})));
				bean.setId(rs.getString("id"));
				bean.setImg(rs.getString("img"));
				bean.setHiddenImg(rs.getString("hidden_img"));
				bean.setTitle(rs.getString("title"));
				bean.setRank(rs.getInt("rank"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		
		return list;
	}
	
	public static String getActionValue(String id){
		String value = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT action_value FROM youhui_datamining.tyh_ad_up where `id`=?;";
		
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				value = rs.getString("action_value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		
		return value;
	}
	

}
