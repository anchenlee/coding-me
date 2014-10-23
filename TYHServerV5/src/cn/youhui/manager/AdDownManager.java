package cn.youhui.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.AdDown;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.SuiShouActionUtil;

public class AdDownManager {
	
	private static final int STATUS_NORMAL = 1;      //正常
	private static final int STATUS_DELETE = 2;      //删除

	private static AdDownManager instance = null;
	
	private AdDownManager(){}
	
	public static AdDownManager getInstance(){
		if(instance == null) instance = new AdDownManager();
		return instance;
	}
	
	public List<AdDown> getList(String platform){
		List<AdDown> list = new ArrayList<AdDown>();
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT * FROM youhui_datamining.tyh_ad_down where `status`=? order by `rank` asc limit 3;";
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_NORMAL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				AdDown ad = new AdDown();
				ad.setImg(rs.getString("img"));
				ad.setId(rs.getString("id"));
				ad.setTitle(rs.getString("title"));
				ad.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,rs.getString("action_type"), new String[]{rs.getString("action_value"),rs.getString("title")})));
				list.add(ad);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
	}
}
