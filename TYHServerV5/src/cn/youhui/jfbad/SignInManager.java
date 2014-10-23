package cn.youhui.jfbad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.youhui.api.activity.ActivityClient;
import cn.youhui.api.activity.ActivityConfig;
import cn.youhui.api.activity.ActivityRequest;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.common.TPool;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.DateUtil;

/**
 * 签到
 * @author lijun
 * @since 2014-02-24
 */
public class SignInManager {
	 
	public static final String key = "signin";
	
	private static final String SIGN_ACTIVITY_ID = "1";
	
	private static final int STATUS_NOSIGN = 0;   //未签到
	
	private static final int STATUS_SIGN = 1;      //已签到，未点击广告
	
	private static final int STATUS_CLICKAD = 2;    //已点击广告
	
	private static DateFormat ft = new SimpleDateFormat("yyyyMMdd");
	
	private static SignInManager instance = null;
	
	private SignInManager(){}
	
	public static SignInManager getInstance(){
		if(instance == null){
			instance = new SignInManager();
		}
		return instance;
	}
	
	/**
	 * 获取今天签到情况
	 * @param uid
	 * @return
	 */
	/*
	public SignIn getSignIn(String uid){
		SignIn si = new SignIn();
		si.setUid(uid);
		Connection conn = null;
		PreparedStatement s = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select `date`,`running_days`,`jfb_num`,`status` from `youhui_cn_fanli`.`tyh_signin` where `uid` = ? order by `create_time` desc limit 0,1";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			int jfbnum = 0;
			int rdays = 0;
			Date ne = null;
			if(rs.next()){
				String date = rs.getString("date");
				if(date != null && !"".equals(date)){
					ne = ft.parse(date);
				}
				rdays = rs.getInt("running_days");
				jfbnum = rs.getInt("jfb_num");
				if(ne != null && DateUtil.differ(ne, new Date())==0){      //今天已经签到过
					si.setStatus(rs.getInt("status"));
					si.setJfbNum(jfbnum);
					if(STATUS_SIGN == si.getStatus()){
						JFBAd ad = JFBAdDAO.getInstance().getSignAd();
						if(ad != null){
							ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 1)));
							si.setJfbad(ad);
						}
					}
				}else{
					si.setStatus(STATUS_NOSIGN);
				}
			}else{
				si.setStatus(STATUS_NOSIGN);
			}
			if(ne == null || DateUtil.differ(ne, new Date()) > 1){
				rdays = 0;
			}
			si.setRunningDays(rdays);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return si;
	}
	*/
	
	/**
	 * 获取今天签到情况
	 * @param uid
	 * @return
	 */
	public SignIn getSignIn(String uid){
		SignIn si = new SignIn();
		si.setUid(uid);
		Connection conn = null;
		PreparedStatement s = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			int rd[] = cn.youhui.manager.SignInManager.getInstance().getDays(uid, conn);
			
				if(rd[0] == -2){      //今天已经签到过
					si.setRunningDays(rd[1]);
					si.setStatus(rd[2]);
					si.setJfbNum(rd[4]);
//					if(STATUS_SIGN == si.getStatus()){
//						JFBAd ad = JFBAdDAO.getInstance().getSignAd();
//						if(ad != null){
//							ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 1)));
//							si.setJfbad(ad);
//						}
//					}
				}else if(rd[0] == -3){
					si.setStatus(STATUS_NOSIGN);
					si.setRunningDays(rd[1]);
				}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return si;
	}
	
	/**
	 * 签到
	 * @param uid
	 * @return
	 */
	/*
	public synchronized SignIn sign(String uid){
		SignIn si = new SignIn();
		si.setUid(uid);
		long now = System.currentTimeMillis();
		si.setCreateTime(now);
		si.setDate(ft.format(new Date(now)));
		Connection conn = null;
		PreparedStatement s = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select `ad_id`,`date`,`running_days`,`jfb_num`,`status` from `youhui_cn_fanli`.`tyh_signin` where `uid` = ? order by `create_time` desc limit 0,1;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			int rDays = 1;
			if(rs.next()){
				Date lastDate = ft.parse(rs.getString("date"));
				long diffdays = DateUtil.differ(new Date(), lastDate);
				if(diffdays == 0){                   //今天已签到过
					si.setJfbNum(rs.getInt("jfb_num"));
					si.setRunningDays(rs.getInt("running_days"));
					si.setStatus(rs.getInt("status"));
					if(STATUS_SIGN == si.getStatus()){
						JFBAd ad = JFBAdDAO.getInstance().getSignAd();
						if(ad != null){
							ad.setNeedCallBack(true);
							ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 1)));
							si.setJfbad(ad);
						}
					}else if(STATUS_CLICKAD == si.getStatus()){
						JFBAd ad = JFBAdDAO.getInstance().getAd(rs.getString("ad_id"), uid, conn);
						ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 1)));
						si.setJfbad(ad);
					}
					return si; 
				}else if(diffdays == 1){             //昨天签到过
					rDays = rs.getInt("running_days");
					rDays++;
				}
			}else{
				int rd = cn.youhui.manager.SignInManager.getInstance().getRunningDays(uid, conn);
				if(rd < 0){
					return null;
				}else{
					rDays = rd + 1;
				}
			}
			int jfbNum = cn.youhui.manager.SignInManager.getInstance().getJFBNum(rDays);
			si.setRunningDays(rDays);
			si.setJfbNum(jfbNum);
			if(!add(si, conn)){
				si.setStatus(STATUS_NOSIGN);
				si.setRunningDays(0);
			}else{
				si.setStatus(STATUS_SIGN);
				JFBAd ad = JFBAdDAO.getInstance().getSignAd();
				ad.setNeedCallBack(true);
				ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 1)));
				si.setJfbad(ad);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return si;
	}
	*/
	
	/**
	 * 签到
	 * @param uid
	 * @return
	 */
	public SignIn sign(String uid){
		SignIn si = new SignIn();
		si.setUid(uid);
		long now = System.currentTimeMillis();
		si.setCreateTime(now);
		si.setDate(ft.format(new Date(now)));
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			int rDays = 1;
			int rd[] = cn.youhui.manager.SignInManager.getInstance().getDays(uid, conn);
			if(rd[0] == -2){                   //今天已签到过
				si.setRunningDays(rd[1]);
				si.setStatus(rd[2]);
				si.setJfbNum(rd[4]);
				if(STATUS_SIGN == si.getStatus()){
					JFBAd ad = JFBAdDAO.getInstance().getSignAd();
					if(ad != null){
						ad.setNeedCallBack(true);
						ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 1)));
						si.setJfbad(ad);
					}
				}else if(STATUS_CLICKAD == si.getStatus()){
					if(0 != rd[3]){
						JFBAd ad = JFBAdDAO.getInstance().getAd("" + rd[3], uid, conn);
						ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 1)));
						si.setJfbad(ad);
					}
				}
				return si;  
			}else if(rd[0] == -3){
				rDays = rd[1]+1;
			}else{
				return null;
			}
		
			int jfbNum = 0;
			long t715 = 1405357200000l;
			if(now < t715){
				jfbNum = cn.youhui.manager.SignInManager.getInstance().getJFBNum(rDays);
			}else{
				jfbNum = cn.youhui.manager.SignInManager.getInstance().getJFBNum(uid, rDays);
			}
			si.setRunningDays(rDays);
			si.setJfbNum(jfbNum);
			if(!add(si, conn)){
				si.setStatus(STATUS_NOSIGN);
				si.setRunningDays(0);
			}else{
				si.setStatus(STATUS_SIGN);
				JFBAd ad = JFBAdDAO.getInstance().getSignAd();
				if(ad != null){
					ad.setNeedCallBack(true);
					ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 1)));
					si.setJfbad(ad);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return si;
	}
	
	/**
	 * 获取连续签到天数     
	 * @param uid
	 * @param conn
	 * @return   签到状态（-3未签到，-2已签到）|获得集分宝个数| 领取状态|领取广告（for 新版）
	 */
	public int[] getRunningDays(String uid, Connection conn){
		int[] ret = {-1,0,-1,0,0};
		int flag = -1;
		String d = "";
		try {
			String sql = "select `ad_id`,`date`,`running_days`,`jfb_num`,`status` from `youhui_cn_fanli`.`tyh_signin` where `uid` = ? order by `create_time` desc limit 0,1;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				flag = 0;
				Date lastDate = null;
				String date = rs.getString("date");
				if(date != null && !"".equals(date)){
					lastDate = ft.parse(date);
					long diffdays = DateUtil.differ(new Date(), lastDate);
					if(diffdays == 0){   //今天签到过
						ret[0] = -2;
						ret[1] = rs.getInt("running_days");
						ret[2] = rs.getInt("status");
						ret[3] = rs.getInt("ad_id");
						ret[4] = rs.getInt("jfb_num");
					}else if(diffdays == 1){   //昨天签到过
						ret[0] = -3;
						ret[1] = rs.getInt("running_days");
						flag = 1;
					}else{
						ret[0] = -3;
						ret[1] = 0;
						flag = 2;
						d = date + "," + diffdays;
					}
				}
			}else{
				ret[0] = -3;
				ret[1] = 0;       //没查询到
				flag = 3;
			}
		} catch (Exception e) {
			flag = 4;
			e.printStackTrace();
		}
		if(ret[1] == 0){
//			if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 9){
//				TPool.getInstance().doit(new SignInLogThread(uid, 2, flag, d));
//			}
		}
		
		return ret;
	}
	
	public SignIn get(String uid, String date){
		SignIn si = new SignIn();
		si.setUid(uid);
		long now = System.currentTimeMillis();
		si.setCreateTime(now);
		si.setDate(ft.format(new Date(now)));
		Connection conn = null;
		PreparedStatement s = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select `date`,`running_days`,`jfb_num`,`status` from `youhui_cn_fanli`.`tyh_signin` where `uid` = ? order by `create_time` desc limit 0,1;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			int rDays = 1;
			if(rs.next()){
				Date lastDate = ft.parse(rs.getString("date"));
				long diffdays = DateUtil.differ(new Date(), lastDate);
				if(diffdays == 0){                   //今天已签到过
					si.setJfbNum(rs.getInt("jfb_num"));
					si.setRunningDays(rs.getInt("running_days"));
					si.setStatus(rs.getInt("status"));
					if(STATUS_SIGN == si.getStatus()){
						JFBAd ad = JFBAdDAO.getInstance().getSignAd();
						ad.setNeedCallBack(true);
						ad.setAction(new SuiShouAction(JFBAdManager.getInstance().getSkipUrl(uid, ad.getAdId(), 1)));
						si.setJfbad(ad);
					}
					return si; 
				}else if(diffdays == 1){             //昨天签到过
					rDays = rs.getInt("running_days");
					rDays++;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return si;
	}
	
	/**
	 * 点击广告
	 * @param uid
	 * @return
	 */
	public JFbAdCallBackResponse clickSignAd(String uid, String adId, Connection conn){
		JFbAdCallBackResponse rsp = new JFbAdCallBackResponse();
		try {
			String sql = "update `youhui_cn_fanli`.`tyh_signin` set `status`=?,`ad_id`=?,`update_time`=? where `uid`=? and `date`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_CLICKAD);
			ps.setString(2, adId);
			ps.setLong(3, System.currentTimeMillis());
			ps.setString(4, uid);
			String date = ft.format(new Date());
			ps.setString(5, date);
			if(ps.executeUpdate() > 0){
				sql = "select `jfb_num`,`running_days` from `youhui_cn_fanli`.`tyh_signin` where `uid`=? and `date`=?;";
				ps = conn.prepareStatement(sql);
				ps.setString(1, uid);
				ps.setString(2, date);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					rsp.setSuccess(true);
					int jfbNum = rs.getInt("jfb_num");
					ActivityRequest request = new ActivityRequest(uid, key, getUniqueId(uid), jfbNum, rs.getInt("running_days"));
					int re = ActivityClient.execut(request);
					if(ActivityConfig.ACTIVITY_JOIN_SUCCESS == re){
						rsp.setJfbNum(jfbNum);
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rsp;
	}
	
	private String getUniqueId(String uid){
		String today = ft.format(new Date());
		return today + uid;
	}
	
	private boolean add(SignIn si, Connection conn){
		boolean flag = false;
		try{
			String sql = "insert into `youhui_cn_fanli`.`tyh_signin` (`uid`,`date`,`running_days`,`jfb_num`," +
					"`status`,`create_time`,`update_time`)values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, si.getUid());
			ps.setString(2, si.getDate());
			ps.setInt(3, si.getRunningDays());
			ps.setInt(4, si.getJfbNum());
			ps.setInt(5, STATUS_SIGN);
			ps.setLong(6, si.getCreateTime());
			ps.setLong(7, si.getCreateTime());
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 老的签到方式签到后，记录到这里
	 * @param si
	 * @param conn
	 * @return
	 */
	public boolean addFromOld(SignIn si){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into `youhui_cn_fanli`.`tyh_signin` (`uid`,`date`,`running_days`,`jfb_num`," +
					"`status`,`create_time`,`update_time`)values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			long now = System.currentTimeMillis();
			ps.setString(1, si.getUid());
			ps.setString(2, si.getDate());
			ps.setInt(3, si.getRunningDays());
			ps.setInt(4, si.getJfbNum());
			ps.setInt(5, STATUS_CLICKAD);
			ps.setLong(6, now);
			ps.setLong(7, now);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 是否真正签到发放了集分宝
	 * @return
	 */
	public boolean isRealSign(String uid, String date, Connection conn){
		boolean flag = false;
		try{
			String sql = "SELECT id FROM `youhui_cn_fanli`.`tyh_activity_join` where `uid` = ? and id like ? and activity_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, date + "%");
			ps.setString(3, SIGN_ACTIVITY_ID);
			ResultSet rs = ps.executeQuery();
			System.out.println(",,,,,,,,,,,issin...." + ps.toString());
			if(rs.next()){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void main(String[] args) {
//		Connection conn = MySQLDBIns.getInstance().getConnection();
////		System.out.println(SignInManager.getInstance().getRunningDays("30789026", conn));
//		System.out.println(SignInManager.getInstance().isRealSign("108612506", "20140507", conn));
//		MySQLDBIns.getInstance().release(conn);
		
		SignIn s = SignInManager.getInstance().sign("100030217");
		
		System.out.println(s.toXml());
		
	}
}
