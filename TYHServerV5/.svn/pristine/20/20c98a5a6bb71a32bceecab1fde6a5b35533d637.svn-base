package cn.youhui.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.Action;
import cn.youhui.bean.AdBean;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.ActionChangeUtil;
import cn.youhui.utils.OuterCode;

public class AdManager {

	private static AdManager instance = null;
	public static AdManager getInstance(){
		if(instance == null) instance = new AdManager();
		return instance;
	}
	
	public List<AdBean> getAds(String platform, String uid, String version_code)
	{
		List<AdBean> list = new ArrayList<AdBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			long now = System.currentTimeMillis();
			String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE (`platform` like '"+platform+"%' or `platform` = 'all') and `start_time` < '"+now+"' AND `end_time` > '"+now+"' order by `rank` asc;";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				AdBean ad = new AdBean();
				String id = rs.getString("id");
				ad.setAd_id(id);
				ad.setAd_title(rs.getString("ad_title"));
				ad.setAd_desc(rs.getString("ad_content"));
				ad.setAd_type(rs.getString("ad_type"));
				ad.setStarttime(rs.getString("start_time"));
				ad.setAddtime(rs.getString("timestamp"));
				ad.setEndtime(rs.getString("end_time"));
				ad.setAd_platform(rs.getString("platform"));
				ad.setDescription(rs.getString("description"));
				ad.setImg(rs.getString("img"));
				
				String action_type = rs.getString("action_type");
				String action_value = rs.getString("action_value");
				Action action = ActionChangeUtil.lowVersionItemAction(id, uid, platform, "6", action_type, action_value, version_code);
				
				ad.setActiontype(action.getActionType());
				ad.setActionvalue(action.getActionValue());
				ad.setAction(action);
				
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
	
	public List<AdBean> getAd(String id, String platform, String uid, String version_code){
		List<AdBean> list = new ArrayList<AdBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			long now = System.currentTimeMillis();
			String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE `id`='"+ id +"' and `start_time` < "+now+" AND `end_time` > "+now+";";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				AdBean ad = new AdBean();
				ad.setAd_id(rs.getString("id"));
				ad.setAd_title(rs.getString("ad_title"));
				ad.setAd_desc(rs.getString("ad_content"));
				ad.setAd_type(rs.getString("ad_type"));
				ad.setStarttime(rs.getString("start_time"));
				ad.setAddtime(rs.getString("timestamp"));
				ad.setEndtime(rs.getString("end_time"));
				ad.setAd_platform(rs.getString("platform"));
				ad.setDescription(rs.getString("description"));
				ad.setImg(rs.getString("img"));
				
				String action_type = rs.getString("action_type");
				String action_value = rs.getString("action_value");
				Action action = ActionChangeUtil.lowVersionItemAction(id, uid, platform, "6", action_type, action_value, version_code);
				
				ad.setActiontype(action.getActionType());
				ad.setActionvalue(action.getActionValue());
				ad.setAction(action);
				
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
	
	/**
	 * 测试的看到的广告
	 * @param platform
	 * @return
	 */
	public List<AdBean> getAdsForQy(String platform, String uid, String version_code){
		List<AdBean> list = new ArrayList<AdBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE (`platform` like '"+platform+"%' or `platform` = 'all') order by `start_time` desc limit 0,7;";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				AdBean ad = new AdBean();
				String id = rs.getString("id");
				ad.setAd_id(id);
				ad.setAd_title(rs.getString("ad_title"));
				ad.setAd_desc(rs.getString("ad_content"));
				ad.setAd_type(rs.getString("ad_type"));
				ad.setStarttime(rs.getString("start_time"));
				ad.setAddtime(rs.getString("timestamp"));
				ad.setEndtime(rs.getString("end_time"));
				ad.setAd_platform(rs.getString("platform"));
				ad.setDescription(rs.getString("description"));
				ad.setImg(rs.getString("img"));
				
				String action_type = rs.getString("action_type");
				String action_value = rs.getString("action_value");
				Action action = ActionChangeUtil.lowVersionItemAction(id, uid, platform, "6", action_type, action_value, version_code);
				
				ad.setActiontype(action.getActionType());
				ad.setActionvalue(action.getActionValue());
				ad.setAction(action);
				
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
	

	private AdBean getAdById(String id, String platform, String uid, String version_code){
		AdBean ad = new AdBean();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`tyh_ads` WHERE id = '"+id+"';";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				ad.setAd_id(rs.getString("id"));
				ad.setAd_title(rs.getString("ad_title"));
				ad.setAd_desc(rs.getString("ad_content"));
				ad.setAd_type(rs.getString("ad_type"));
				ad.setStarttime(rs.getString("start_time"));
				ad.setAddtime(rs.getString("timestamp"));
				ad.setEndtime(rs.getString("end_time"));
				ad.setAd_platform(rs.getString("platform"));
				ad.setDescription(rs.getString("description"));
				ad.setImg(rs.getString("img"));
				
				String action_type = rs.getString("action_type");
				String action_value = rs.getString("action_value");
				Action action = ActionChangeUtil.lowVersionItemAction(id, uid, platform, "6", action_type, action_value, version_code);
				
				ad.setActiontype(action.getActionType());
				ad.setActionvalue(action.getActionValue());
				ad.setAction(action);
				
			}else{
				ad = null;
			}
			return ad;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
	}
	
	/**
	 * 获取top500用户广告
	 * @return
	 */
	public AdBean getTop500Ad(String platform, String uid, String version_code){
		if("iphone".equalsIgnoreCase(platform)){
			return getAdById("10049",platform, uid, version_code);
		}else{
			return getAdById("10050",platform, uid, version_code);
		}
	}
}
