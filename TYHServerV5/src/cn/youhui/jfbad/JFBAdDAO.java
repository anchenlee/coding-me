package cn.youhui.jfbad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.SuiShouAction;
import cn.youhui.common.Config;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.OuterCode;


/**
 * 集分宝广告数据
 * @author lijun
 * @since 2014-02-24
 */
public class JFBAdDAO {

	private static final int STATUS_NORMAL = 1;    //正常
	private static final int STATUS_FAIL = 2;     //失效
	private static final int STATUS_DONE = 3;     //已发完
	private static final int STATUS_STOP = 4;     //暂停
	
	public static final int TYPE_AD = 1;
	public static final int TYPE_SIGN = 2;
	public static final int TYPE_L_AD = 3;    //长期广告
	public static final int TYPE_L_SIGN = 4;  //长期签到
	
	public static final String TAOJINBI_AD_ID = "91";       //淘金币广告ID
	private static final String TAOJINBI_BUTTON = "领取淘金币";
	
	private static final String BUTTON_VISIT = "查看商品";
	private static final String BUTTON_GAIN = "领取集分宝";
	
	private static final String GAIN_INFO = "点击领取X个集分宝";
	
	private static final int CLICK_TYPE_ITEM = 0;
	private static final int CLICK_TYPE_URL = 1;
	
	private static JFBAdDAO instance = null;
	
	private JFBAdDAO(){}
	
	public static JFBAdDAO getInstance(){
		if(instance == null){
			instance = new JFBAdDAO();
		}
		return instance;
	}
	
	/**
	 * 获取签到广告
	 * @param uid
	 * @param adId
	 * @return
	 */
	public JFBAd getSignAd(){
		JFBAd ad = new JFBAd();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from `youhui_jfbad`.`jfb_ad` where `status`=? and (`type` =? or `type`=?) and `start_time`< ? and `end_time`>? and `has_click_times`<`all_times` order by `priority` desc,rand() limit 0,1;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_NORMAL);
			ps.setInt(2, TYPE_SIGN);
			ps.setInt(3, TYPE_L_SIGN);
			long now = System.currentTimeMillis();
			ps.setLong(4, now);
			ps.setLong(5, now);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ad.setAdId(rs.getString("ad_id"));
				ad.setAdName(rs.getString("ad_name"));
				ad.setAction(new SuiShouAction(rs.getString("click_url")));
				ad.setImg(rs.getString("img"));
				ad.setTitle(rs.getString("title"));
				ad.setPerNum(rs.getInt("per_num"));
			}else{
				ad = null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return ad;
	}
	
	
	/**
	 * 添加广告到list
	 * @param num  广告个数
	 * @param hasJfb 有集分宝的广告个数
	 * @return
	 */
	public void addAds(List<JFBAd> list, String uid, int num, int hasJfb){
//		List<JFBAd> list = new ArrayList<JFBAd>();
		Connection conn = null;
		try{
//			if(num ==0){
//				return list;
//			}
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from `youhui_jfbad`.`jfb_ad` where `status`=? and (`type` =? or `type`=?) and `start_time`< ? and `end_time`>? and `has_click_times`<`all_times` order by `priority` desc, rand();";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_NORMAL);
			ps.setInt(2, TYPE_AD);
			ps.setInt(3, TYPE_L_AD);
			long now = System.currentTimeMillis();
			ps.setLong(4, now);
			ps.setLong(5, now);
			ResultSet rs = ps.executeQuery();
			int hjfb = 0;
			int alladd = 0;
			List<JFBAd> tmpList = new ArrayList<JFBAd>();
			while(rs.next()){
				if(TAOJINBI_AD_ID.equals(rs.getString("ad_id"))){
					continue;
				}
				
				if(rs.getInt("per_num") > 0 && rs.getInt("all_jfb_times") > rs.getInt("has_click_times")){     //有集分宝
					if(hjfb < hasJfb){
						hjfb++;
					}else{
						continue;
					}
				}
				if(alladd < num){     //总数
					alladd ++;
				}else{
					break;
				}
				
				JFBAd ad = new JFBAd();
				ad.setAdId(rs.getString("ad_id"));
				ad.setAdName(rs.getString("ad_name"));
				ad.setClickUrl(rs.getString("click_url"));
				ad.setImg(rs.getString("img"));
				ad.setIpadImg(rs.getString("img2"));
				ad.setTitle(rs.getString("title"));
				ad.setPerNum(rs.getInt("per_num"));
				
				if(ad.getPerNum() > 0){
					ad.setGainInfo(GAIN_INFO.replace("X","" + rs.getInt("per_num")));
					ad.setButton(BUTTON_GAIN);
					ad.setNeedCallBack(true);
				}else{
					ad.setGainInfo(ad.getTitle());
					ad.setButton(BUTTON_VISIT);
					ad.setNeedCallBack(false);
				}
				ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 0)));
				
				if(list != null && list.contains(ad)){
					continue;
				}
				
				tmpList.add(ad);
			}
			if(tmpList != null && tmpList.size() > 0){
				list.addAll(0, tmpList);
			}
			JFBAd tjbAd = getAd(TAOJINBI_AD_ID, uid, conn);
			if(tjbAd != null){
				tjbAd.setButton(TAOJINBI_BUTTON);
				list.add(tjbAd);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
	}
	
	/**
	 * 获取广告列表 
	 * @param ids 广告Id
	 * @return
	 */
	public List<JFBAd> getAdList(String uid, String... ids){
		List<JFBAd> list = new ArrayList<JFBAd>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			if(ids != null){
				for(String idandstatus : ids){
					String[] idandst = idandstatus.split("#");
					String adId = idandst[0];
					String status = idandst[1];
					JFBAd ad = getAd(adId,uid, conn);
					if(status.equals(JFBAdClickDAO.STATUS_CLICK) && ad.getPerNum() > 0){      //点击了，还未领取
						ad.setButton(BUTTON_GAIN);
						ad.setGainInfo(GAIN_INFO.replace("X", ""+ad.getPerNum()));
						ad.setNeedCallBack(true);
					}else{
						ad.setButton(BUTTON_VISIT);
						ad.setGainInfo(ad.getTitle());
						ad.setNeedCallBack(false);
					}
					ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 0)));
					list.add(ad);
				}
			}else{
				return list;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取广告信息
	 * @param adId
	 * @param conn
	 * @return
	 */
	public JFBAd getAd(String adId, String uid, Connection conn){
		JFBAd ad = new JFBAd();
		try{
			String sql = "select * from `youhui_jfbad`.`jfb_ad` where `ad_id`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ad.setAdId(rs.getString("ad_id"));
				ad.setAdName(rs.getString("ad_name"));
				ad.setImg(rs.getString("img"));
				ad.setIpadImg(rs.getString("img2"));
				ad.setTitle(rs.getString("title"));
				ad.setPerNum(rs.getInt("per_num"));
				ad.setGainInfo(rs.getString("title"));
				ad.setType(rs.getInt("type"));
				ad.setItemId(rs.getString("item_id"));
				if(CLICK_TYPE_ITEM == rs.getInt("itemid_or_yhq")){
					String itemId = rs.getString("item_id");
					String outerCode = OuterCode.getOuterCode(uid, "", "0", "12", "");
					ad.setClickUrl(Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" + itemId);
				}else if(CLICK_TYPE_URL == rs.getInt("itemid_or_yhq")){
					ad.setClickUrl(rs.getString("click_url"));
				}
				ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 0)));
			}else{
				ad = null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ad;
	}
	
	/**
	 * 是否为签到的广告
	 * @param adId
	 * @param conn
	 * @return
	 */
	public boolean isSignAd(String adId, Connection conn){
		boolean flag = false;
		try{
			String sql = "select `type` from `youhui_jfbad`.`jfb_ad` where `ad_id`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int type = rs.getInt("type");
				if(TYPE_SIGN == type || TYPE_L_SIGN == type){
					flag = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 添加点击次数
	 * @param adId
	 * @param conn
	 * @return
	 */
	public boolean addClick(String adId, Connection conn){
		boolean flag = false;
		try{
			String sql = "update `youhui_jfbad`.`jfb_ad` set `has_click_times`=`has_click_times`+1 where `ad_id`=? and `status`=? and `has_click_times`<`all_times`;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adId);
			ps.setInt(2, STATUS_NORMAL);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void main(String[] args) {
//		System.out.println(JFBAdDAO.getInstance().getSignAd().toXml());
	}
	
}
