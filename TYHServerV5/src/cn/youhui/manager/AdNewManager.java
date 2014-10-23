package cn.youhui.manager;

import java.lang.annotation.Retention;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.Ad;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.SuiShouActionUtil;

public class AdNewManager {

	private static AdNewManager instance = null;
	
	public static AdNewManager getInstance(){
		if(instance == null) instance = new AdNewManager();
		return instance;
	}
	
	public List<Ad> getAds(String platform){
		List<Ad> list = new ArrayList<Ad>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			long now = System.currentTimeMillis();
			String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE (`platform` = ? or `platform` = 'all') and `start_time` < ? AND `end_time` > ? order by `rank` asc;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, platform);
			s.setLong(2, now);
			s.setLong(3, now);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				Ad ad = new Ad();
				ad.setId(rs.getString("id"));
				ad.setTitle(rs.getString("ad_title"));
				ad.setDescription(rs.getString("description"));
				ad.setImg(rs.getString("img"));
				ad.setRank(rs.getInt("rank"));
				ad.setStart_time(rs.getString("start_time"));
				ad.setEnd_time(rs.getString("end_time"));
				ad.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,rs.getString("action_type"), new String[]{rs.getString("action_value"),rs.getString("ad_title")})));
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
	
	public Ad getAd(String adId,String platform){
		Ad ad = new Ad();
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE `id`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, adId);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				ad.setId(rs.getString("id"));
				ad.setTitle(rs.getString("ad_title"));
				ad.setDescription(rs.getString("description"));
				ad.setImg(rs.getString("img"));
				ad.setRank(rs.getInt("rank"));
				ad.setStart_time(rs.getString("start_time"));
				ad.setEnd_time(rs.getString("end_time"));
				ad.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,rs.getString("action_type"), new String[]{rs.getString("action_value"),rs.getString("ad_title")})));
			}else{
				ad = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return ad;
	}
}
