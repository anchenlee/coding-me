package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.Activity;
import cn.youhui.bean.ActivityJoin;
import cn.youhui.bean.JiFenBaoTrade;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.ramdata.ActivityCacher;
import cn.youhui.utils.DateUtil;
import cn.youhui.utils.TimeUtil;

/**
 * 集分宝帐户明细
 * @author lijun
 *
 */
public class JiFenBaoMXManager {

private static JiFenBaoMXManager instance = null;

    private static final int TYPE_FL = 1;    //返利
    private static final int TYPE_AC = 2;    //参加活动
    private static final int TYPE_TX = 3;    //提现集分宝
    private static final int TYPE_PURCHASE = 4;  //集分宝兑换购买
    
    private static final int STATUS_SUCCESS = 2;    //成功
    public static final int STATUS_Review = 5;   //审核中
    
    private static final long flCheckTime = 15*24*60*60*1000;     //返利审核时间
    
    private static final int pageSize = 20;
    
	
	private JiFenBaoMXManager(){}
	
	public static JiFenBaoMXManager getInstance(){
		if(instance == null) instance = new JiFenBaoMXManager();
		return instance;
	}
	
	
	/**
	 * 获得的集分宝数
	 * @param uid
	 * @return
	 */
	public int getGainJfbNum(String uid){
		int jfbNum = 0;
		if(uid == null || "".equals(uid))
			return jfbNum;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?) and `status`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, TYPE_AC);
			s.setInt(4, STATUS_SUCCESS);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return jfbNum;
	}
	
	/**
	 * 获得的集分宝数
	 * @param uid
	 * @return
	 */
	public int getIncomeJfbNum(String uid, String startTime, String endTime){
		int jfbNum = 0;
		if(uid == null || "".equals(uid))
			return jfbNum;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?) and (`status`=? or `status`=?)  and `apply_time` > ?  and `apply_time` < ?   ;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, TYPE_AC);
			s.setInt(4, STATUS_SUCCESS);
			s.setInt(5, STATUS_Review);
			s.setString(6,startTime );
			s.setString(7,endTime );
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return jfbNum;
	}
	
	/**
	 * 收入的总页数
	 * @param uid
	 * @return
	 */
	public int getIncomePageNum(String uid, String startTime, String endTime){
		int pageNum = 0;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select count(1) as sumpage from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?) and (`status`=? or `status`=?)  and `apply_time` > ?  and `apply_time` < ?   ;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, TYPE_AC);
			s.setInt(4, STATUS_SUCCESS);
			s.setInt(5, STATUS_Review);
			s.setString(6,startTime );
			s.setString(7,endTime );
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				pageNum = rs.getInt("sumpage");
				int ys = pageNum%pageSize;
				pageNum = (pageNum-ys)/pageSize;
				if(ys > 0){
					pageNum ++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return pageNum;
	}
	
	/**
	 * 获得的集分宝数
	 * @param uid
	 * @return
	 */
	public int getPayJfbNum(String uid, String startTime, String endTime){
		int jfbNum = 0;
		if(uid == null || "".equals(uid))
			return jfbNum;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?) and (`status`=? or `status`=?)  and `apply_time` > ?  and `apply_time` < ?   ;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setInt(3, TYPE_PURCHASE);
			s.setInt(4, STATUS_SUCCESS);
			s.setInt(5, STATUS_Review);
			s.setString(6,startTime );
			s.setString(7,endTime );
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return jfbNum;
	}
	
	/**
	 * 获得的集分宝数
	 * @param uid
	 * @return
	 */
	public int getGainJfbNum(String uid, Connection conn){
		int jfbNum = 0;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?) and `status`=?;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, TYPE_AC);
			s.setInt(4, STATUS_SUCCESS);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return jfbNum;
	}
	
	/**
	 * 获取提现成功的集分宝数   ，  包括购买抽奖等花掉的
	 * @param uid
	 * @return
	 */
	public int getTXJfbNum(String uid, Connection conn){
		int jfbNum = 0;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`>=? and `status`=?;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setInt(3, STATUS_SUCCESS);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return jfbNum;
	}
	
	
	/**
	 * 获取花费的集分宝数 （包括抽奖，秒杀等）
	 * @param uid
	 * @return
	 */
	public int getPayJfbNum(String uid, Connection conn){
		int jfbNum = 0;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and `status`=?;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_PURCHASE);
			s.setInt(3, STATUS_SUCCESS);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return jfbNum;
	}
	
	/**
	 * 获取审核中的集分宝数
	 * @param uid
	 * @return
	 */
	public int getChkJfbNum(String uid, Connection conn){
		int jfbNum = 0;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and `status`<?;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setInt(3, STATUS_SUCCESS);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return jfbNum;
	}
	
	/**
	 * 集分宝余额
	 * @param uid
	 * @return
	 */
	public int getYue(String uid, Connection conn){
		int jfbNum = 0;
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `status`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, STATUS_SUCCESS);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jfbNum;
	}
	
	
	/**
	 * 集分宝获取记录
	 * @param uid
	 * @return
	 */
	public List<ActivityJoin> getAcList(String uid, int page){
		List<ActivityJoin> list = new ArrayList<ActivityJoin>();
		if(uid == null || "".equals(uid))
			return null;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select `ac_date`,`trade_id`,`jfb_num`,`type`,`ac_activity_id`,`ac_activity_name`,`fl_item_title`,`fl_pay_timestamp`,`apply_time`,`status` from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?) and `jfb_num`>0 order by `apply_time` desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, TYPE_AC);
			s.setInt(4, (pageSize * (page - 1)));
			s.setInt(5, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				ActivityJoin ac = new ActivityJoin();
				ac.setId(rs.getString("trade_id"));
				ac.setJfbNum(rs.getInt("jfb_num"));
				ac.setTimestamp(rs.getLong("apply_time"));
				if(TYPE_AC == rs.getInt("type")){
					String activityId = rs.getString("ac_activity_id");
					String acName = rs.getString("ac_activity_name");
					if("18".equals(activityId)){
						acName = getFHAcName(rs.getString("ac_date"), rs.getInt("jfb_num"));
					}
					ac.setActivityName(acName);
					if(SignInManager.code.equals(activityId)){
						activityId = SignInManager.newcode;
					}
					ac.setActivityId(activityId);
					Activity a = ActivityCacher.getInstance().getActivity(activityId);
					String img = "";
					if(a != null){
						img = a.getImg();
					}
					ac.setIcon(img);
				}else{
					ac.setActivityName(rs.getString("fl_item_title"));
					ac.setExpireTime(rs.getLong("fl_pay_timestamp") + flCheckTime); 
					String img = TradeImgDAO.getInstance().getTradeImg(rs.getString("trade_id"), conn);
					if(img != null && !"".equals(img)){
						img = img + "_80x80.jpg";
					}
					ac.setIcon(img);
				}
				ac.setStatus(rs.getInt("status"));
				list.add(ac);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	private String getFHAcName(String date, int jfbNum){
		if(date != null && !"".equals(date)){
			String dat[] = date.split("/");
			if(dat.length == 3){
				int yi = Integer.parseInt(dat[0]);
				int moni = Integer.parseInt(dat[1]) - 1;
				String year = "";
				String month = "";
				
				if(moni == 0){        //一月份
					year = (yi -1) + "";
					month = "12";
				}else{
					year = yi + "";
					month = moni + "";
					if(moni < 10){
						month = "0" + month;
					}
				}
				
				return year + "年" + month + "月分红奖励" + jfbNum + "个集分宝";
			}
		}
		return "";
	}
	/**
	 * 集分宝获取记录页数
	 * @return
	 */
	public int getAcPageNum(String uid){
		int pageTotal = 0;
		if(uid == null || "".equals(uid))
			return pageTotal;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select count(id) as acount from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, TYPE_AC);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				int count = rs.getInt("acount");
				int ys = count%pageSize;
				pageTotal = (count-ys)/pageSize;
				if(ys > 0){
					pageTotal ++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return pageTotal;
	}
	
	/**
	 * 获取提现记录
	 * @param uid
	 * @param page
	 * @return
	 */
	public List<JiFenBaoTrade> getTXTradeList(String uid, int page){
		List<JiFenBaoTrade> list = new ArrayList<JiFenBaoTrade>();
		Connection conn = null;
		try {
			if(page < 1){
				page = 1;
			}
			String uidPrefix = uid.substring(0, 1);
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select 2 as `type`,-`jfb_num`*100 as `je`, `tx_zfb` as `zfb`, `status`/2 as `status`, `tx_error` as `error`, `apply_time`, `trade_id` as id from `youhui_fanli`.`tyh_trade_mx_" + uidPrefix + "` where `uid`=? and `type` = ? union select 1 as `type`,`txje`*100 as `je`,`zfb` ,`txstate` as `status`, `why` as `error`, `applytime` as `apply_time`, `id` from `youhui_cn_fanli`.`duoduo_tixian` where `ddusername`=? order by `apply_time` desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setString(3, uid);
			s.setInt(4, (page-1)*pageSize);
			s.setInt(5, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				JiFenBaoTrade trade = new JiFenBaoTrade();
				trade.setType(rs.getInt("type"));
				trade.setJiFenBaoNum(rs.getInt("je"));
				trade.setZfb(rs.getString("zfb"));
				int status = rs.getInt("status");
				trade.setStatus(status);
				trade.setError(rs.getString("error"));
				trade.setTimestamp(rs.getLong("apply_time"));
				list.add(trade);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取提现列表页数
	 * @param uid
	 * @return
	 */
	public int getTXTradePageNum(String uid){
		int totalPage = 0;
		Connection conn = null;
		try {
			String uidPrefix = uid.substring(0, 1);
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select count(*) as acount from `youhui_fanli`.`tyh_trade_mx_" + uidPrefix + "` where `uid`=? and `type`=? union select count(*) as acount from `youhui_cn_fanli`.`duoduo_tixian` where `ddusername`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setString(3, uid);
			ResultSet rs = s.executeQuery();
			int count = 0;
			while(rs.next()){
				count += rs.getInt("acount");
			}
			int ys = count%pageSize;
			totalPage = (count-ys)/pageSize;
			if(ys > 0)
				totalPage ++;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return totalPage;
	}
	
	/**
	 * 获取集分宝提现记录
	 * @param uid
	 * @param page
	 * @return
	 */
	public List<JiFenBaoTrade> getJFBTXTradeList(String uid, int page){
		List<JiFenBaoTrade> list = new ArrayList<JiFenBaoTrade>();
		Connection conn = null;
		try {
			if(page < 1){
				page = 1;
			}
			String uidPrefix = uid.substring(0, 1);
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select `type`, -`jfb_num`*100 as `je`, `tx_zfb` as `zfb`, `status`/2 as `status`, `tx_error` as `error`, `apply_time`, `trade_id` as id, `fl_item_title` from `youhui_fanli`.`tyh_trade_mx_" + uidPrefix + "` where `uid`=? and (`type`=? or `type`=?) order by `apply_time` desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setInt(3, TYPE_PURCHASE);
			s.setInt(4, (page-1)*pageSize);
			s.setInt(5, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				JiFenBaoTrade trade = new JiFenBaoTrade();
				trade.setJiFenBaoNum(rs.getInt("je"));
				if(TYPE_PURCHASE == rs.getInt("type")){
					trade.setZfb(rs.getString("fl_item_title"));
				}else{
					trade.setZfb(rs.getString("zfb"));
				}
				
				int status = rs.getInt("status");
				trade.setStatus(status);
				trade.setError(rs.getString("error"));
				trade.setTimestamp(rs.getLong("apply_time"));
				trade.setType(2);
				list.add(trade);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取集分宝购物奖励记录
	 * @param uid
	 * @param page
	 * @return
	 */
	public List<ActivityJoin> getIpadJFBShoppingTradeList(String uid, int page){
		List<ActivityJoin> list = new ArrayList<ActivityJoin>();
		Connection conn = null;
		try {
			if(page < 1){
				page = 1;
			}
			String uidPrefix = uid.substring(0, 1);
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select `ac_date`,`trade_id`,`jfb_num`,`type`,`ac_activity_id`,`ac_activity_name`,`fl_item_title`,`fl_pay_timestamp`,`apply_time`,`status` from youhui_fanli.tyh_trade_mx_"+ uidPrefix +" where uid = ? and `type` = ? order by `apply_time` desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, (page-1)*pageSize);
			s.setInt(4, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				ActivityJoin ac = new ActivityJoin();
				ac.setId(rs.getString("trade_id"));
				ac.setJfbNum(rs.getInt("jfb_num"));
				ac.setTimestamp(rs.getLong("apply_time"));
				if(TYPE_AC == rs.getInt("type")){
					String activityId = rs.getString("ac_activity_id");
					String acName = rs.getString("ac_activity_name");
					if("18".equals(activityId)){
						acName = getFHAcName(rs.getString("ac_date"), rs.getInt("jfb_num"));
					}
					ac.setActivityName(acName);
					if(SignInManager.code.equals(activityId)){
						activityId = SignInManager.newcode;
					}
					ac.setActivityId(activityId);
					Activity a = ActivityCacher.getInstance().getActivity(activityId);
					String img = "";
					if(a != null){
						img = a.getImg();
					}
					ac.setIcon(img);
				}else{
					ac.setActivityName(rs.getString("fl_item_title"));
					ac.setExpireTime(rs.getLong("fl_pay_timestamp") + flCheckTime); 
					String img = TradeImgDAO.getInstance().getTradeImg(rs.getString("trade_id"), conn);
					if(img != null && !"".equals(img)){
						img = img + "_80x80.jpg";
					}
					ac.setIcon(img);
				}
				ac.setStatus(rs.getInt("status"));
				list.add(ac);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取集分宝签到奖励记录
	 * @param uid
	 * @param page
	 * @return
	 */
	public List<ActivityJoin> getIpadJFBSigninTradeList(String uid, int page){
		List<ActivityJoin> list = new ArrayList<ActivityJoin>();
		Connection conn = null;
		try {
			if(page < 1){
				page = 1;
			}
			String uidPrefix = uid.substring(0, 1);
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select `ac_date`,`trade_id`,`jfb_num`,`type`,`ac_activity_id`,`ac_activity_name`,`fl_item_title`,`fl_pay_timestamp`,`apply_time`,`status` from youhui_fanli.tyh_trade_mx_"+ uidPrefix +" where uid = ? and `type` = ? and `ac_activity_id`=1 order by `apply_time` desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_AC);
			s.setInt(3, (page-1)*pageSize);
			s.setInt(4, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				ActivityJoin ac = new ActivityJoin();
				ac.setId(rs.getString("trade_id"));
				ac.setJfbNum(rs.getInt("jfb_num"));
				ac.setTimestamp(rs.getLong("apply_time"));
				if(TYPE_AC == rs.getInt("type")){
					String activityId = rs.getString("ac_activity_id");
					String acName = rs.getString("ac_activity_name");
					if("18".equals(activityId)){
						acName = getFHAcName(rs.getString("ac_date"), rs.getInt("jfb_num"));
					}
					ac.setActivityName(acName);
					if(SignInManager.code.equals(activityId)){
						activityId = SignInManager.newcode;
					}
					ac.setActivityId(activityId);
					Activity a = ActivityCacher.getInstance().getActivity(activityId);
					String img = "";
					if(a != null){
						img = a.getImg();
					}
					ac.setIcon(img);
				}else{
					ac.setActivityName(rs.getString("fl_item_title"));
					ac.setExpireTime(rs.getLong("fl_pay_timestamp") + flCheckTime); 
					String img = TradeImgDAO.getInstance().getTradeImg(rs.getString("trade_id"), conn);
					if(img != null && !"".equals(img)){
						img = img + "_80x80.jpg";
					}
					ac.setIcon(img);
				}
				ac.setStatus(rs.getInt("status"));
				list.add(ac);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取集分宝购物奖励
	 * @param uid
	 * @return
	 */
	public int getIpadJFBShoppingTradePageNum(String uid){
		int totalPage = 0;
		Connection conn = null;
		try {
			String uidPrefix = uid.substring(0, 1);
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select count(*) as acount from `youhui_fanli`.`tyh_trade_mx_" + uidPrefix + "` where `uid`=? and `type`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			ResultSet rs = s.executeQuery();
			int count = 0;
			while(rs.next()){
				count += rs.getInt("acount");
			}
			int ys = count%pageSize;
			totalPage = (count-ys)/pageSize;
			if(ys > 0)
				totalPage ++;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return totalPage;
	}
	
	/**
	 * 获取集分宝购物奖励
	 * @param uid
	 * @return
	 */
	public int getIpadAllPayPageNum(String uid){
		int totalPage = 0;
		Connection conn = null;
		try {
			String uidPrefix = uid.substring(0, 1);
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select count(*) as acount from `youhui_fanli`.`tyh_trade_mx_" + uidPrefix + "` where `uid`=? and (`type`=? or `type`=?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setInt(3, TYPE_PURCHASE);
			ResultSet rs = s.executeQuery();
			int count = 0;
			while(rs.next()){
				count += rs.getInt("acount");
			}
			int ys = count%pageSize;
			totalPage = (count-ys)/pageSize;
			if(ys > 0)
				totalPage ++;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return totalPage;
	}
	
	/**
	 * 获取集分宝签到奖励
	 * @param uid
	 * @return
	 */
	public int getIpadJFBSigninTradePageNum(String uid){
		int totalPage = 0;
		Connection conn = null;
		try {
			String uidPrefix = uid.substring(0, 1);
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select count(*) as acount from `youhui_fanli`.`tyh_trade_mx_" + uidPrefix + "` where `uid`=? and `type`=? and `ac_activity_id`=1;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_AC);
			ResultSet rs = s.executeQuery();
			int count = 0;
			while(rs.next()){
				count += rs.getInt("acount");
			}
			int ys = count%pageSize;
			totalPage = (count-ys)/pageSize;
			if(ys > 0)
				totalPage ++;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return totalPage;
	}
	
	/**
	 * 获取集分宝提现列表页数
	 * @param uid
	 * @return
	 */
	public int getJFBTXTradePageNum(String uid){
		int totalPage = 0;
		Connection conn = null;
		try {
			String uidPrefix = uid.substring(0, 1);
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select count(*) as acount from `youhui_fanli`.`tyh_trade_mx_" + uidPrefix + "` where `uid`=? and (`type`=? or `type`=?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setInt(3, TYPE_PURCHASE);
			ResultSet rs = s.executeQuery();
			int count = 0;
			while(rs.next()){
				count += rs.getInt("acount");
			}
			int ys = count%pageSize;
			totalPage = (count-ys)/pageSize;
			if(ys > 0)
				totalPage ++;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return totalPage;
	}
	
	/**
	 * 获取现金提现列表
	 * @param uid
	 * @param page
	 * @return
	 */
	public List<JiFenBaoTrade> getXJTXTradeList(String uid, int page){
		List<JiFenBaoTrade> list = new ArrayList<JiFenBaoTrade>();
		Connection conn = null;
		try {
			if(page < 1){
				page = 1;
			}
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select 1 as `type`,`txje`*100 as `je`,`zfb` ,`txstate` as `status`, `why` as `error`, `applytime` as `apply_time`, `id` from `youhui_cn_fanli`.`duoduo_tixian` where `ddusername`=? order by `apply_time` desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, (page-1)*pageSize);
			s.setInt(3, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				JiFenBaoTrade trade = new JiFenBaoTrade();
				trade.setType(rs.getInt("type"));
				trade.setJiFenBaoNum(rs.getInt("je"));
				trade.setZfb(rs.getString("zfb"));
				int status = rs.getInt("status");
				trade.setStatus(status);
				trade.setError(rs.getString("error"));
				trade.setTimestamp(rs.getLong("apply_time"));
				trade.setType(1);
				list.add(trade);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取现金提现列表页数
	 * @param uid
	 * @return
	 */
	public int getXJTXTradePageNum(String uid){
		int totalPage = 0;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select count(*) as acount from `youhui_cn_fanli`.`duoduo_tixian` where `ddusername`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			int count = 0;
			while(rs.next()){
				count += rs.getInt("acount");
			}
			int ys = count%pageSize;
			totalPage = (count-ys)/pageSize;
			if(ys > 0){
				totalPage ++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return totalPage;
	}
	
	public List<String> getUids(String uidPrefix, Connection conn){
		List<String> uids = new ArrayList<String>();
		try {
			String sql = "select uid from `youhui_fanli`.`tyh_trade_mx_" + uidPrefix + "` group by uid;";
			PreparedStatement s = conn.prepareStatement(sql);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				String uid = rs.getString("uid");
				if(uid != null && !"".equals(uid)){
					uids.add(uid);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return uids;
	}
	
	/**
	 * 用户获取集分宝情况全部
	 * @param uid
	 * @param page
	 * @return
	 */
	public List<ActivityJoin> getGainJFB(String uid){
		List<ActivityJoin> list = new ArrayList<ActivityJoin>();
		if(uid == null || "".equals(uid))
			return null;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select `trade_id`,`jfb_num`,`type`,`ac_activity_id`,`apply_time`,`status` from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?) and `jfb_num`>0 order by `apply_time` desc;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, TYPE_AC);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				ActivityJoin ac = new ActivityJoin();
				ac.setId(rs.getString("trade_id"));
				ac.setJfbNum(rs.getInt("jfb_num"));
				ac.setTimestamp(rs.getLong("apply_time"));
				ac.setStatus(rs.getInt("status"));
				if(TYPE_AC == rs.getInt("type")){
					String activityId = rs.getString("ac_activity_id");
					if(SignInManager.code.equals(activityId)){
						activityId = SignInManager.newcode;
					}
					ac.setActivityId(activityId);
				}else{
					ac.setActivityId("fanli");
				}
				list.add(ac);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 本月返利获得的集分宝数  包含审核中的
	 * @param uid
	 * @return
	 */
	public int getMonthGainJfbNum(String uid, Connection conn){
		int jfbNum = 0;
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and (`status`=? or `status`=?) and `apply_time`>? and `apply_time`<?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, STATUS_SUCCESS);
			s.setInt(4, STATUS_Review);
			s.setLong(5, DateUtil.getMonthFirst(new Date()));
			s.setLong(6, System.currentTimeMillis());
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jfbNum;
	}
	
	/**
	 * 月返利获得的集分宝数  包含审核中的
	 * @param uid
	 * @return
	 */
	public int getMonthGainJfbNumByMonth(String uid, Connection conn,String year,String month){
		int jfbNum = 0;
		int yearInt = 0;
		int monthInt = 0;
		try {
			yearInt = Integer.parseInt(year);
			monthInt = Integer.parseInt(month);
		} catch (Exception e) {
			return jfbNum;
		}
		String data = year+"-"+month+"-01 00:00:00";
		String moweiData = "";
		if(monthInt == 12){
			moweiData = (yearInt+1)+"-"+"01-01 00:00:00";
		}else{
			moweiData = yearInt+"-"+(monthInt+1)+"-01 00:00:00";
		}
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and (`status`=? or `status`=?) and `apply_time`>? and `apply_time`<?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, STATUS_SUCCESS);
			s.setInt(4, STATUS_Review);			
			s.setLong(5, TimeUtil.getTimeMillis(data));
			s.setLong(6, TimeUtil.getTimeMillis(moweiData));			
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jfbNum;
	}
	
	/**
	 * 返利获得的集分宝数
	 * @param uid
	 * @return
	 */
	public int getFanliJfbNum(String uid, Connection conn){
		int jfbNum = 0;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and (`status`=? or `status`=?);";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, STATUS_SUCCESS);
			s.setInt(4, STATUS_Review);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return jfbNum;
	}
	
	/**
	 * 审核中的返利集分宝数
	 * @param uid
	 * @return
	 */
	public int getCheckFanliJfbNum(String uid, Connection conn){
		int jfbNum = 0;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and `status`=?;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, STATUS_Review);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return jfbNum;
	}
	
	public int getFanliJfbNumByMonth(String uid, Connection conn,String year,String month){
		int jfbNum = 0;
		if(uid == null || "".equals(uid))
			return jfbNum;
		int yearInt = 0;
		int monthInt = 0;
		try {
			yearInt = Integer.parseInt(year);
			monthInt = Integer.parseInt(month);
		} catch (Exception e) {
			return jfbNum;
		}
		String moweiData = "";
		if(monthInt == 12){
			moweiData = (yearInt+1)+"-"+"01-01 00:00:00";
		}else{
			moweiData = yearInt+"-"+(monthInt+1)+"-01 00:00:00";
		}
		try {
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and (`status`=? or `status`=?) and insert_time<?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, STATUS_SUCCESS);
			s.setInt(4, STATUS_Review);
			s.setLong(5, TimeUtil.getTimeMillis(moweiData));
//			System.out.println(s);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jfbNum;
	}
	
	
	public static int getJFB(int mothJfbNum, double levelRate, double fanliRate){
		double rate = (double)(levelRate + fanliRate)/100;
		int jfb = (int)(mothJfbNum * rate);
		if(mothJfbNum * rate - jfb + 0.001 >= 0.5){
			jfb ++;
		}
		return jfb;
	}
	public static void main(String[] args) {
//		System.out.println(JiFenBaoMXManager.getInstance().getTXTradePageNum("21172545"));
//		System.out.println(JiFenBaoMXManager.getInstance().getAcList("108612506", 5).size());
		System.out.println(JiFenBaoMXManager.getInstance().getJFBTXTradePageNum("108612506"));
	}
	
	/**
	 * 购物赠送集分宝数
	 * @param uid
	 * @return
	 */
	public int getShoppingJfbNum(String uid,String startTime, String endTime){
		int shoppingjfb = 0;
		Connection conn = null;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return shoppingjfb;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and (`status`=?  or `status`=?)  and `apply_time` > ?  and `apply_time` < ?  ;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, STATUS_SUCCESS);
			s.setInt(4, STATUS_Review);
			s.setString(5,startTime );
			s.setString(6,endTime );
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				shoppingjfb = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return shoppingjfb;
	}
	
	/**
	 * 活动赠送集分宝
	 * @param uid
	 * @return
	 */
	public int getActivityJfbNum(String uid ,String startTime, String endTime){
		int activityjfb = 0;
		Connection conn = null;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return activityjfb;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and `ac_activity_id` !=1 and (`status`=?  or `status`=?)  and `apply_time` > ?  and `apply_time` < ?  ;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_AC);
			s.setInt(3, STATUS_SUCCESS);
			s.setInt(4, STATUS_Review);
			s.setString(5,startTime );
			s.setString(6,endTime );
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				activityjfb = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return activityjfb;
	}
	
	/**
	 * 签到奖励集分宝
	 * @param uid
	 * @return
	 */
	public int getSigninJfbNum(String uid,String startTime, String endTime){
		int signinjfb = 0;
		Connection conn = null;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return signinjfb;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and `ac_activity_id`=1 and (`status`=?  or `status`=?)  and `apply_time` > ?  and `apply_time` < ?  ;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_AC);
			s.setInt(3, STATUS_SUCCESS);
			s.setInt(4, STATUS_Review);
			s.setString(5,startTime );
			s.setString(6,endTime );
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				signinjfb = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return signinjfb;
	}
	
	/**
	 * 提现集分宝
	 * @param uid
	 * @return
	 */
	public int getTXJfbNum(String uid,String startTime,String endTime){
		int txjfb = 0;
		Connection conn = null;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return txjfb;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and (`status`=?  or `status`=?)  and `apply_time` > ?  and `apply_time` < ?  ;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setInt(3, STATUS_SUCCESS);
			s.setInt(4, STATUS_Review);
			s.setString(5,startTime );
			s.setString(6,endTime );
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				txjfb = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return txjfb;
	}
	
	/**
	 * 获得的集分宝数
	 * @param uid
	 * @return
	 */
	public int getPayPageNum(String uid, String startTime, String endTime){
		int pageNum = 0;
		Connection conn = null;
		PreparedStatement s = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select count(1) as sumpage from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and (`status`=?  or `status`=?)  and `apply_time` > ?  and `apply_time` < ?  ;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setInt(3, STATUS_SUCCESS);
			s.setInt(4, STATUS_Review);
			s.setString(5,startTime );
			s.setString(6,endTime );
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				pageNum = rs.getInt("sumpage");
				int ys = pageNum%pageSize;
				pageNum = (pageNum-ys)/pageSize;
				if(ys > 0){
					pageNum ++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return pageNum;
	}
	
	/**
	 * 集分宝购买
	 * @param uid
	 * @return
	 */
	public int getuseJfbToPayNum(String uid,String startTime ,String endTime){
		int usejfb = 0;
		Connection conn = null;
		PreparedStatement s = null;
		if(uid == null || "".equals(uid))
			return usejfb;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `type`=? and (`status`=?  or `status`=?)  and `apply_time` > ?  and `apply_time` < ?  ;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_PURCHASE);
			s.setInt(3, STATUS_SUCCESS);
			s.setInt(4, STATUS_Review);
			s.setString(5,startTime );
			s.setString(6,endTime );
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				usejfb = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(s, null);
		}
		return usejfb;
	}
	
	/**
	 * 集分宝收入
	 * @param uid
	 * @return
	 */
	public List<ActivityJoin> getIncomeListByDate(String uid, int page,String startTime, String endTime){
		List<ActivityJoin> list = new ArrayList<ActivityJoin>();
		if(uid == null || "".equals(uid))
			return null;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select `ac_date`,`trade_id`,`jfb_num`,`type`,`ac_activity_id`,`ac_activity_name`,`fl_item_title`,`fl_pay_timestamp`,`apply_time`,`status`,`tx_zfb` from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?) and `jfb_num`>0 and `apply_time` > ?  and `apply_time` < ?  order by `apply_time` desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_FL);
			s.setInt(3, TYPE_AC);
			s.setString(4, startTime);
			s.setString(5, endTime);
			s.setInt(6, (pageSize * (page - 1)));
			s.setInt(7, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				ActivityJoin ac = new ActivityJoin();
				ac.setId(rs.getString("trade_id"));
				ac.setJfbNum(rs.getInt("jfb_num"));
				ac.setTimestamp(rs.getLong("apply_time"));
				if(TYPE_AC == rs.getInt("type")){
					String activityId = rs.getString("ac_activity_id");
					String acName = rs.getString("ac_activity_name");
					if("18".equals(activityId)){
						acName = getFHAcName(rs.getString("ac_date"), rs.getInt("jfb_num"));
					}
					ac.setActivityName(acName);
					if(SignInManager.code.equals(activityId)){
						activityId = SignInManager.newcode;
					}
					ac.setActivityId(activityId);
					Activity a = ActivityCacher.getInstance().getActivity(activityId);
					String img = "";
					if(a != null){
						img = a.getImg();
					}
					ac.setIcon(img);
				}else {
					ac.setActivityId("item");
					ac.setActivityName(rs.getString("fl_item_title"));
					ac.setExpireTime(rs.getLong("fl_pay_timestamp") + flCheckTime); 
					String img = TradeImgDAO.getInstance().getTradeImg(rs.getString("trade_id"), conn);
					if(img != null && !"".equals(img)){
						img = img + "_80x80.jpg";
					}
					ac.setIcon(img);
				}
				ac.setStatus(rs.getInt("status"));
				ac.setZfb(rs.getString("tx_zfb") == null ? "" : rs.getString("tx_zfb"));
				list.add(ac);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 集分宝支出
	 * @param uid
	 * @return
	 */
	public List<ActivityJoin> getPayListByDate(String uid, int page,String startTime, String endTime){
		List<ActivityJoin> list = new ArrayList<ActivityJoin>();
		if(uid == null || "".equals(uid)){
			return null;
		}
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select `ac_date`,`trade_id`,`jfb_num`,`type`,`ac_activity_id`,`ac_activity_name`,`fl_item_title`,`fl_pay_timestamp`,`apply_time`,`status`,`tx_zfb` from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?) and (`status`=? or `status`=?)  and `apply_time` > ?  and `apply_time` < ?  order by `apply_time` desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setInt(3, TYPE_PURCHASE);
			s.setInt(4, STATUS_SUCCESS);
			s.setInt(5, STATUS_Review);
			s.setString(6, startTime);
			s.setString(7, endTime);
			s.setInt(8, (pageSize * (page - 1)));
			s.setInt(9, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				ActivityJoin ac = new ActivityJoin();
				ac.setId(rs.getString("trade_id"));
				ac.setJfbNum(rs.getInt("jfb_num"));
				ac.setTimestamp(rs.getLong("apply_time"));
				ac.setActivityId(rs.getString("type"));
				ac.setStatus(rs.getInt("status"));
				ac.setZfb(rs.getString("tx_zfb") == null ? "" : rs.getString("tx_zfb"));
				System.out.println(ac.getZfb());
				if(TYPE_TX == rs.getInt("type")){
					ac.setActivityName(rs.getString("ac_activity_name"));
					String img = "http://static.etouch.cn/suishou/ad_img/7d3a0hqv9.png";
					ac.setIcon(img);
				}else{
					ac.setActivityName(rs.getString("fl_item_title"));
					ac.setExpireTime(rs.getLong("fl_pay_timestamp") + flCheckTime); 
					String img = "http://static.etouch.cn/suishou/ad_img/7cph9zcsj.png";
					ac.setIcon(img);
				}
				list.add(ac);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	
	/**
	 * 集分宝所有支出
	 * @param uid
	 * @return
	 */
	public List<ActivityJoin> getAllPayList(String uid, int page){
		List<ActivityJoin> list = new ArrayList<ActivityJoin>();
		if(uid == null || "".equals(uid)){
			return null;
		}
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String uidp = uid.substring(0, 1);
			String sql = "select `ac_date`,`trade_id`,`jfb_num`,`type`,`ac_activity_id`,`ac_activity_name`,`fl_item_title`,`fl_pay_timestamp`,`apply_time`,`status`,`tx_zfb` from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and (`type`=? or `type`=?)  order by `apply_time` desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, TYPE_TX);
			s.setInt(3, TYPE_PURCHASE);
			s.setInt(4, (pageSize * (page - 1)));
			s.setInt(5, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				ActivityJoin ac = new ActivityJoin();
				ac.setId(rs.getString("trade_id"));
				ac.setJfbNum(rs.getInt("jfb_num"));
				ac.setTimestamp(rs.getLong("apply_time"));
				ac.setActivityId(rs.getString("type"));
				ac.setStatus(rs.getInt("status"));
				ac.setZfb(rs.getString("tx_zfb") == null ? "" : rs.getString("tx_zfb"));
				System.out.println(ac.getZfb());
				if(TYPE_TX == rs.getInt("type")){
					ac.setActivityName(rs.getString("ac_activity_name"));
					String img = "http://static.etouch.cn/suishou/ad_img/7d3a0hqv9.png";
					ac.setIcon(img);
				}else{
					ac.setActivityName(rs.getString("fl_item_title"));
					ac.setExpireTime(rs.getLong("fl_pay_timestamp") + flCheckTime); 
					String img = "http://static.etouch.cn/suishou/ad_img/7cph9zcsj.png";
					ac.setIcon(img);
				}
				list.add(ac);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
}
