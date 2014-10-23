package cn.youhui.jfbad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.youhui.api.activity.ActivityClient;
import cn.youhui.api.activity.ActivityConfig;
import cn.youhui.api.activity.ActivityRequest;
import cn.youhui.common.Config;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.DateUtil;

/**
 * 集分宝广告点击
 * @author lijun
 * @since 2014-02-24
 */
public class JFBAdClickDAO {
	
	public static final int STATUS_CLICK = 1;    //已点击
	
	private static final int STATUS_LOAD = 2;     //已加载
	
	private static final int STATUS_FF = 3;     //已发放
	
	private static final int TYPE_NOJFB = 1;     //点击无集分宝
	private static final int TYPE_HASJFB = 2;     //点击有集分宝
	
	private static DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	private static JFBAdClickDAO instance = null;
	
	private JFBAdClickDAO(){}
	
	public static JFBAdClickDAO getInstance(){
		if(instance == null){
			instance = new JFBAdClickDAO();
		}
		return instance;
	}
	
	
	public boolean add(JFBAdClick adclick, Connection conn){
		boolean flag = false;
		try{
			String sql = "insert into `youhui_jfbad`.`ad_click` (`uid`,`taobao_nick`,`ad_id`," +
					"`jfb_num`,`click_time`,`date`,`status`,`is_from_sign`,`ac_unique_id`,`timestamp`,`type`,`ac_key`)values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adclick.getUid());
			ps.setString(2, adclick.getTaobaoNick());
			ps.setString(3, adclick.getAdId());
			ps.setInt(4, adclick.getJfbNum());
			ps.setLong(5, adclick.getClickTime());
			ps.setString(6, adclick.getDate());
			ps.setInt(7, STATUS_CLICK);
			ps.setInt(8, adclick.getIsFromSign());
			ps.setString(9, adclick.getAcUniqueId());
			ps.setLong(10, System.currentTimeMillis());
			ps.setInt(11, TYPE_NOJFB);
			ps.setString(12, adclick.getAcKey());
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
	 * 改为有集分宝
	 * @param adclick
	 * @return
	 */
	public boolean upHasJFB(String uid, String adId, Connection conn){
		boolean flag = false;
		try{
			String sql = "update `youhui_jfbad`.`ad_click` set `type`=? where `ad_id`=? and `uid` =? and `type`=? and `date`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, TYPE_HASJFB);
			ps.setString(2, adId);
			ps.setString(3, uid);
			ps.setInt(4, TYPE_NOJFB);
			ps.setString(5, df.format(new Date()));
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
	 * 发放集分宝
	 * @param adclick
	 * @return
	 */
	public JFbAdCallBackResponse fafangJFB(String uid, String adId, Connection conn){
		JFbAdCallBackResponse rsp = new JFbAdCallBackResponse();
		try{
			String sql = "select `type`,`jfb_num`,`ac_unique_id` from `youhui_jfbad`.`ad_click` where `ad_id`=? and `uid` =?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adId);
			ps.setString(2, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("type") == TYPE_HASJFB){            //此次点击是否有集分宝
					rsp.setSuccess(true);
					int jfbNum = rs.getInt("jfb_num");
					if(jfbNum > 0){
						ActivityRequest actRequest = new ActivityRequest(uid, Config.jfb_activity_JoinKey, rs.getString("ac_unique_id"), jfbNum);
						int re = ActivityClient.execut(actRequest);
						if (ActivityConfig.ACTIVITY_JOIN_SUCCESS == re){
							rsp.setJfbNum(jfbNum);
						}
					}
				}else{
					rsp.setSuccess(true);             //无集分宝 不需要发放
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return rsp;
	}
	
	/**
	 * 状态改为已加载
	 * @param adclick
	 * @return
	 */
	public boolean upHasLoad(String uid, String adId, Connection conn){
		boolean flag = false;
		try{
			String sql = "update `youhui_jfbad`.`ad_click` set `status`=? where `ad_id`=? and `uid` =? and `date`=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_LOAD);
			ps.setString(2, adId);
			ps.setString(3, uid);
			ps.setString(4, df.format(new Date()));
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
	 * 状态改为已发放
	 * @param adclick
	 * @return
	 */
	public boolean upHasFF(String uid, String adId, Connection conn){
		boolean flag = false;
		try{
			String sql = "update `youhui_jfbad`.`ad_click` set `status`=? where `ad_id`=? and `uid` =? and `status`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_FF);
			ps.setString(2, adId);
			ps.setString(3, uid);
			ps.setInt(4, STATUS_LOAD);
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
	 * 获取领取状态
	 * @param adclick
	 * @return
	 */
	public int getStatus(String uid, String adId, Connection conn){
		int status = 0;
		try{
			String sql = "select `is_from_sign`,`status`,`date` from `youhui_jfbad`.`ad_click` where `ad_id`=? and `uid` =? and `date`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adId);
			ps.setString(2, uid);
			ps.setString(3, df.format(new Date()));
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				status = rs.getInt("status");
				int isFromSign = rs.getInt("is_from_sign");
				String date = rs.getString("date");
				if(isFromSign == 1 && (status == STATUS_LOAD || status == STATUS_FF)){
					if(!SignInManager.getInstance().isRealSign(uid, date, conn)){         //实际没发放的情况
						status = -1;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	
	/**
	 * 获取今天 已经点击的广告个数  已经点击的送集分宝的广告个数   已经点击的广告ID
	 * @param uid
	 * @param conn
	 * @return
	 */
	public String[] getHasClick(String uid, Connection conn){
		String[] ret = new String[3];
		try{
			String sql = "select `ad_id`,`jfb_num`,`status`,`type` from `youhui_jfbad`.`ad_click` where `uid` =? and `click_time` > ? and `is_from_sign`=0 order by `click_time` desc;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setLong(2, DateUtil.getMidnight(new Date()));
			ResultSet rs = ps.executeQuery();
			int all = 0;
			int hasjfb = 0;
			StringBuffer ids = new StringBuffer();
			while(rs.next()){
				if(JFBAdDAO.TAOJINBI_AD_ID.equals(rs.getString("ad_id"))){
					continue;
				}
				if(rs.getInt("jfb_num") > 0 ){
					all++;
					if(rs.getInt("type") == TYPE_HASJFB){
						hasjfb++;
					}
				}
				ids.append(rs.getString("ad_id") + "#" + rs.getInt("status") + ",");
			}
			if(ids != null && ids.length() > 0){
				ret[2] = ids.substring(0, ids.length()-1);				
			}else{
				ret[2] = "";
			}
			ret[0] = all + "";
			ret[1] = hasjfb + "";
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		System.out.println(JFBAdClickDAO.getInstance().getHasClick("99991948", conn)[2]);
		MySQLDBIns.getInstance().release(conn);
	}
}
