package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.youhui.api.activity.ActivityClient;
import cn.youhui.api.activity.ActivityConfig;
import cn.youhui.api.activity.ActivityRequest;
import cn.youhui.bean.Activity;
import cn.youhui.bean.JiFenBaoTrade;
import cn.youhui.bean.SignInBean;
import cn.youhui.bean.UserAccount;
import cn.youhui.common.TPool;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.jfbad.SignIn;
import cn.youhui.jfbad.SignInLogThread;
import cn.youhui.jfbad.SignInRecordThread;
import cn.youhui.utils.DateUtil;
import cn.youhui.utils.FenHongUtil;

public class SignInManager {
	private static Map<Integer,Integer> scale = new LinkedHashMap<Integer,Integer>();
	private static SignInManager instance = null;
	public static String key = "signin";
	public static String code = "001";
	public static String newcode = "1";
	public static String activityName = "签到送集分宝";
	public static String memo = "随手优惠签到送集分宝";
	
	private static SimpleDateFormat ft1 = new SimpleDateFormat("yyyy/MM/dd");
	private static SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
	private static final int pageSize = 10;
	public static SignInManager getInstance(){
		if(instance == null) instance = new SignInManager();
		return instance;
	}
	
	private SignInManager(){    //avg: 7.81
		scale.put(5, 20);
		scale.put(6, 20);
		scale.put(7, 20);
		scale.put(8, 12);
		scale.put(9, 10);
		scale.put(10, 6);
		scale.put(11, 3);
		scale.put(12, 3);
		scale.put(13, 3);
		scale.put(14, 1);
		scale.put(15, 2);
	}
	
//	public synchronized SignInBean signInooo(String uid){
//		SignInBean ret = new SignInBean();
//		ret.setUid(uid);
//		Connection conn = MySQLDBIns.getInstance().getConnection();
//		try {
//			String sql = "select * from youhui_cn_fanli.tyh_activity_join where uid = ? and activity_id = ? order by `timestamp` desc;";
//			PreparedStatement s = conn.prepareStatement(sql);
//			s.setString(1, uid);
//			s.setString(2, code);
//			ResultSet rs = s.executeQuery();
//			int rDays = 1;
//			
//			if(rs.next()){
//				Date lastDate = rs.getDate("date");
//				long diffdays = DateUtil.differ(new Date(), lastDate);
//				if(diffdays == 0){                   //今天已签到过
//					ret.setJifenbaoNum(0);
//					ret.setRunningDays(0);
//					ret.setStatus(1);
//					return ret; 
//				}else if(diffdays == 1){             //昨天签到过
//					rDays = rs.getInt("running_days");
//					rDays++;
//				}
//			}
//			int jfb = getJFBNum(rDays);
////			if(UserManager.getInstance().isShareNewUser(uid)){     
////				jfb = 50;
////			}
//			
//			ret.setJifenbaoNum(jfb);
//			ret.setRunningDays(rDays);
//			ret.setStatus(2);
//			Activity activity = new Activity();
//			activity.setJfbNum(jfb);
//			activity.setId(code);
//			activity.setName(activityName);
//			String tradeId = getTradeId(uid);
//			ActivityJoinManager.getInstance().join(activity, uid, rDays, tradeId, conn);
//		} catch (SQLException e) {
//			ret= null;
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//		    MySQLDBIns.getInstance().release(conn);
//		}
//		return ret;
//	}
	
	
	/*
	public SignInBean signIn(String uid){
		SignInBean ret = new SignInBean();
		ret.setUid(uid);
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select `date`,`running_days` from youhui_cn_fanli.tyh_activity_join where uid = ? and activity_id = ? order by `id` desc limit 0,1;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, newcode);
			ResultSet rs = s.executeQuery();
			int rDays = 1;
			
			if(rs.next()){
				Date lastDate = null;
				String date = rs.getString("date");
				if(date != null && !"".equals(date)){
					lastDate = ft1.parse(date);
				}else{
					return null;
				}
				long diffdays = DateUtil.differ(new Date(), lastDate);
				if(diffdays == 0){                   //今天已签到过
					ret.setJifenbaoNum(0);
					ret.setRunningDays(0);
					ret.setStatus(1);
					return ret; 
				}else if(diffdays == 1){             //昨天签到过
					rDays = rs.getInt("running_days");
					rDays++;
				}
			}
			int jfb = getJFBNum(rDays);
			
			ActivityRequest request = new ActivityRequest(uid, key, getUniqueId(uid), jfb, rDays);
			int re = ActivityClient.execut(request);
			if(ActivityConfig.ACTIVITY_JOIN_SUCCESS == re){
				ret.setJifenbaoNum(jfb);
				ret.setRunningDays(rDays);
				ret.setStatus(2);
				
				TPool.getInstance().doit(new SignInRecordThread(new SignIn(uid, ft.format(new Date()), rDays, jfb)));
				
			}else{
				ret.setStatus(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	*/
	
	public SignInBean signIn(String uid){
		SignInBean ret = new SignInBean();
		ret.setUid(uid);
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {			
			int rDays = 1;
			
			int rt[] = getDays(uid, conn);
			
			if(rt[0] == -2){      //今天签到
				ret.setJifenbaoNum(0);
				ret.setRunningDays(rt[1]);
				ret.setStatus(1);
				return ret; 
			}else if(rt[0] == -3){
				rDays = rt[1] + 1;
			}else{
				return null;
			}
			
			int jfb = 0;
			long now = System.currentTimeMillis();
			if(now < 1405357200000l){
				jfb = getJFBNum(rDays);
			}else{
				jfb = getJFBNum(uid, rDays);
			}
			
			ActivityRequest request = new ActivityRequest(uid, key, getUniqueId(uid), jfb, rDays);
			int re = ActivityClient.execut(request);
			if(ActivityConfig.ACTIVITY_JOIN_SUCCESS == re){
				ret.setJifenbaoNum(jfb);
				ret.setRunningDays(rDays);
				ret.setStatus(2);
//				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 9){
					TPool.getInstance().doit(new SignInRecordThread(new SignIn(uid, ft.format(new Date()), rDays, jfb)));
//				}
				
			}else{
				ret.setStatus(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	public int[] getDays(String uid, Connection conn){
		int num[] = cn.youhui.jfbad.SignInManager.getInstance().getRunningDays(uid, conn);    
		if(num[1] == 0){       // sign表没有获取到
			num = getRunningDays(uid, conn);
		}
		return num;
	}
	
	/**
	 * 获取连续签到天数
	 * @param uid
	 * @param conn
	 * @return 签到状态（-3未签到，-2已签到）|获得集分宝个数| 领取状态|领取广告（for 新版）
	 */
	public int[] getRunningDays(String uid, Connection conn){
		int[] ret = {-1,0,2,0,0};
		int flag = -1;
		String d = "";
		try {
			String sql = "select `date`,`running_days`,`jfb_num` from youhui_cn_fanli.tyh_activity_join where uid = ? and activity_id = ? order by `id` desc limit 0,1;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, newcode);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				flag = 0;
				Date lastDate = null;
				String date = rs.getString("date");
				if(date != null && !"".equals(date)){
					lastDate = ft1.parse(date);
					long diffdays = DateUtil.differ(new Date(), lastDate);
					if(diffdays == 0){   //今天签到过
						ret[0] = -2;
						ret[1] = rs.getInt("running_days");
						ret[4] = rs.getInt("jfb_num");
					}else if(diffdays == 1){   //昨天签到过
						ret[0] = -3;
						ret[1] = rs.getInt("running_days");
						flag = 1;
						d = date + "," + diffdays;
					}else{
						ret[0] = -3;
						ret[1] = 0;
						flag = 2;
					}
					
				}
			}else{
				ret[0] = -3;
				ret[1] = 0;
				flag = 3;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = 4;
		}
		if(ret[1] == 0){
//			if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 9){
//				TPool.getInstance().doit(new SignInLogThread(uid, 1, flag, d));
//			}
		}
		
		return ret;
	}
	
	
	/**
	 * 今天签到次数是否超过
	 * @param uid
	 * @return
	 */
	public boolean isLimitToday(String uid){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String today = ft1.format(new Date());
			String sql = "select count(id) as acount from youhui_cn_fanli.tyh_activity_join where uid = ? and activity_id = ? and `date`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, newcode);
			s.setString(3, today);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				int count = rs.getInt("acount");
				if(count > 1){
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public String getUniqueId(String uid){
		String today = ft.format(new Date());
		return today + uid;
	}
	
	 //签到活动每天生成唯一的订单号，防止一天多次签到
	private String getTradeId11(String uid){
		String today = ft.format(new Date());
		int uidi = 1;
		if(uid.length() >= 6){
			uidi = Integer.parseInt(uid.substring(uid.length() - 4, uid.length()));
		}else{
			uidi = Integer.parseInt(uid);
		}
		int todayi = Integer.parseInt(today.substring(today.length()-5, today.length()));
		int ranki = uidi * todayi;
		String rankbef = "000000000" + ranki;
		String ret = rankbef.substring(rankbef.length() - 9, rankbef.length());
		return ret;
	}
	
	public String getTradeId(String uid){
		String today = ft.format(new Date());
		int i = 8 - uid.length();
		String uuid = uid;
		if(i >= 0){
			for(;i>0;i--){
				uuid = "0"+uuid;
			}
		}else{
			uuid = uuid.substring(-i, uuid.length());
		}
		String cuid = "";
		for(int j = 0; j < uuid.length()/2; j++){
			int s = Integer.parseInt(String.valueOf(uuid.charAt(j)));
			int e = Integer.parseInt(String.valueOf(uuid.charAt(uuid.length()-j-1)));
			cuid += (s + e)%10;
		}
		int uidi;
		if(uid.length() >= 6){
			uidi = Integer.parseInt(uid.substring(uid.length() - 6, uid.length()));
		}else{
			uidi = Integer.parseInt(uid);
		}
		int todayi = Integer.parseInt(today.substring(today.length()-4, today.length()));
		int ranki = uidi * todayi;
		String rankbef = "00000000" + ranki;
		String rank = rankbef.substring(rankbef.length() - 8, rankbef.length());
		
		return rank + cuid;
	}
	
	/**
	 * 是否为第一次签到
	 */
	public boolean isFirstSign(String uid){
		boolean flag = false;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select * from youhui_cn_fanli.tyh_activity_join where uid = ? and activity_id = ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, newcode);
			ResultSet rs = s.executeQuery();
			if(!rs.next()){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 实时发送的，停用
	 */
	public SignInBean signInold(String uid){
		SignInBean ret = new SignInBean();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select * from youhui_cn_fanli.tyh_activity_join where uid = ? order by `timestamp` desc;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			int rDays = 1;
			if(rs.next()){
				Date lastDate = rs.getDate("date");
				long diffdays = DateUtil.differ(new Date(), lastDate);
				if(diffdays == 0){                   //今天已签到过
					ret.setJifenbaoNum(0);
					ret.setRunningDays(0);
					ret.setStatus(2);
					return ret; 
				}else if(diffdays == 1){             //昨天签到过
					rDays = rs.getInt("running_days");
					rDays++;
				}
			}
			int jfb = getJFBNum(rDays);
			ret.setJifenbaoNum(jfb);
			ret.setRunningDays(rDays);
			ret.setStatus(1);
			String tradeId = ft.format(new Date()) + code + uid;
			int status = JiFenBaoTradeManager.Status_Init;
			String today = ft1.format(new Date());
			sql = "insert into youhui_cn_fanli.tyh_activity_join(`uid`,`date`,`running_days`,`timestamp`,`jfb_num`,`status`) values(?,?,?,?,?,?)";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, today);
			s.setInt(3, rDays);
			s.setLong(4, System.currentTimeMillis());
			s.setInt(5, jfb);
			s.setInt(6, status);
			int n = s.executeUpdate();
			String signId = "";
			if(n > 0){
				rs = conn.createStatement().executeQuery("SELECT LAST_INSERT_ID();");
				if(rs.next()){
					signId = rs.getString(1);
				}
				JiFenBaoTrade jfbb = new JiFenBaoTrade();
				jfbb.setFrom(code);
				jfbb.setFromId(signId);
				jfbb.setJiFenBaoNum(jfb);
				jfbb.setStatus(JiFenBaoTradeManager.Status_UnInit);
				jfbb.setTradeId(tradeId);
				jfbb.setUid(uid);
				jfbb.setMemo(memo);
				String zfb = UserManager.getInstance().getZfb(uid, conn);
				if(zfb != null && !"".equals(zfb)){
					jfbb.setStatus(JiFenBaoTradeManager.Status_Init);
					jfbb.setZfb(zfb);
				}
			//	JiFenBaoTradeManager.getInstance().add(jfbb);
			}
		} catch (SQLException e) {
			ret= null;
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	public SignInBean getSignInDetail(String uid){
		SignInBean ret = new SignInBean();
		ret.setUid(uid);
		Connection conn = null;
		PreparedStatement s = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select `date`,`running_days` from youhui_cn_fanli.tyh_activity_join where uid = ? and activity_id = ? order by `id` desc limit 0,1;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, newcode);
			ResultSet rs = s.executeQuery();
			int jfbnum = 0;
			int rdays = 0;
			Date ne = null;
			if(rs.next()){
				String date = rs.getString("date");
				if(date != null && !"".equals(date)){
					ne = ft1.parse(date);
				}
				rdays = rs.getInt("running_days");
//				jfbnum = rs.getInt("jfbsum");
			}
			ret.setStatus(0);
			if(ne != null && DateUtil.differ(ne, new Date())==0){
				ret.setStatus(1);                                  //今天已签到过
			}
			if(ne == null || DateUtil.differ(ne, new Date()) > 1){
				rdays = 0;
			}
			ret.setRunningDays(rdays);
			ret.setJifenbaoNum(jfbnum);
		} catch (SQLException e) {
			ret= null;
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(uid);
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	/**
	 * 实时发送的，需要返回未到帐的，停用
	 * @param uid
	 * @return
	 */
	public SignInBean getSignInDetailold(String uid){
		SignInBean ret = new SignInBean();
		Connection conn = null;
		PreparedStatement s = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select *,sum(jfb_num)as jfbsum from (select * from youhui_cn_fanli.tyh_activity_join where uid = ? order by `timestamp` desc) as t group by `status`;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			int jfbnum = 0;
			int notjfbnum = 0;
			int rdays = 0;
			Date ne = null;
			while(rs.next()){
				if(ne == null){
					ne = rs.getDate("date");		
					rdays = rs.getInt("running_days");
				}else if(rs.getDate("date").after(ne)){
					ne = rs.getDate("date");
					rdays = rs.getInt("running_days");
				}
				int status = rs.getInt("status");
				if(status == 1){
					jfbnum = rs.getInt("jfbsum");
				}else if(status == 0){
					notjfbnum = rs.getInt("jfbsum");
				}
			}
			if(ne != null && DateUtil.differ(ne, new Date())==0){
				ret.setStatus(1);                                  //今天已签到过
			}
			if(ne == null || DateUtil.differ(ne, new Date()) > 1){
				rdays = 0;
			}
			ret.setRunningDays(rdays);
			ret.setJifenbaoNum(jfbnum);
//			ret.setNotjfbNum(notjfbnum);
		} catch (SQLException e) {
			ret= null;
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	
	public int getJFBNum(int days){
		int ret = 0;
		if(days == 0){
			return ret;
		}else if(days < 5){
			ret = days;
		}else{
			ret = getRandom();
		}
		return ret;
	}
	
	public int getJFBNum(String uid, int days){
		int ret = 0;
		UserAccount ua = UserAccountManager.getInstance().getUserAccount(uid);
		int level = ua.getLevel();
		if(level == FenHongUtil.LEVEL_NO_HEART){
			if(days <=3){
				ret = 1;
			}else{
				ret = getRandom(1, 3);
			}
		}else if(level == FenHongUtil.LEVEL_HEART){
			if(days <=3){
				ret = 2;
			}else{
				ret = getRandom(2, 4);
			}
		}else if(level == FenHongUtil.LEVEL_DIAMOND){
			if(days <=3){
				ret = 3;
			}else{
//				ret = getRandom(3, 8);
				Map<Integer,Integer> scale2 = new LinkedHashMap<Integer,Integer>();
				scale2.put(3, 20);
				scale2.put(4, 40);
				scale2.put(5, 25);
				scale2.put(6, 6);
				scale2.put(7, 5);
				scale2.put(8, 4);
				ret = getRandom(scale2);
			}
		}else if(level == FenHongUtil.LEVEL_CROWN){
			if(days <=3){
				ret = 5;
			}else{
//				ret = getRandom(5, 15);
				Map<Integer,Integer> scale3 = new LinkedHashMap<Integer,Integer>();
				scale3.put(5, 9);
				scale3.put(6, 12);
				scale3.put(7, 15);
				scale3.put(8, 15);
				scale3.put(9, 10);
				scale3.put(10, 9);
				scale3.put(11, 8);
				scale3.put(12, 7);
				scale3.put(13, 6);
				scale3.put(14, 5);
				scale3.put(15, 4);
				ret = getRandom(scale3);
			}
		}else if(level == FenHongUtil.LEVEL_GOLDENCROWN){
			if(days <=3){
				ret = 8;
			}else{
//				ret = getRandom(8, 20);
				Map<Integer,Integer> scale4 = new LinkedHashMap<Integer,Integer>();
				scale4.put(8, 10);
				scale4.put(9, 15);
				scale4.put(10, 20);
				scale4.put(11, 10);
				scale4.put(12, 9);
				scale4.put(13, 8);
				scale4.put(14, 5);
				scale4.put(15, 4);
				scale4.put(16, 3);
				scale4.put(17, 3);
				scale4.put(18, 1);
				scale4.put(19, 1);
				scale4.put(20, 1);
				ret = getRandom(scale4);
			}
		}else if(level == FenHongUtil.LEVEL_SUPER){
			if(days <=3){
				ret = 10;
			}else{
				ret = getRandom(10, 30);
			}
		}
		return ret;
	}
	
	/**
	 * 
	 * @param uid
	 * @param days
	 * @return
	 */
	public String getNextJFBNum(String uid, int days){
		String ret = "";
		UserAccount ua = UserAccountManager.getInstance().getUserAccount(uid);
		int level = ua.getLevel();
		if(level == FenHongUtil.LEVEL_NO_HEART){
			if(days <=3){
				ret = "1";
			}else{
				ret = "1-3";
			}
		}else if(level == FenHongUtil.LEVEL_HEART){
			if(days <=3){
				ret = "2";
			}else{
				ret = "2-4";
			}
		}else if(level == FenHongUtil.LEVEL_DIAMOND){
			if(days <=3){
				ret = "3";
			}else{
				ret = "3-8";
			}
		}else if(level == FenHongUtil.LEVEL_CROWN){
			if(days <=3){
				ret = "5";
			}else{
				ret = "5-15";
			}
		}else if(level == FenHongUtil.LEVEL_GOLDENCROWN){
			if(days <=3){
				ret = "8";
			}else{
				ret = "8-20";
			}
		}else if(level == FenHongUtil.LEVEL_SUPER){
			if(days <=3){
				ret = "10";
			}else{
				ret = "10-30";
			}
		}
		return ret;
	}
	
	//根据比例获取随机数
	private int getRandom(){    
		int sum = 0;
		for(Map.Entry<Integer, Integer> m : scale.entrySet()){
			sum += m.getValue();
		}
		int ran = new Random().nextInt(sum);
		for(Map.Entry<Integer, Integer> m : scale.entrySet()){
			if(ran - m.getValue() < 0)
				return m.getKey();
			else ran = ran - m.getValue();
		}
		return 0;
	}
	
	private int getRandom(int start, int end){    
		int ran = 0;
		int sub = end - start + 1;
		if(sub < 0){
			return ran;
		}
		ran = new Random().nextInt(sub) + start;
		return ran;
	}
	
	private int getRandom(Map<Integer,Integer> scale){    
		int sum = 0;
		for(Map.Entry<Integer, Integer> m : scale.entrySet()){
			sum += m.getValue();
		}
		int ran = new Random().nextInt(sum);
		for(Map.Entry<Integer, Integer> m : scale.entrySet()){
			if(ran - m.getValue() < 0)
				return m.getKey();
			else ran = ran - m.getValue();
		}
		return 0;
	}
	
	private void getRand(){
		Map<Integer,Integer> scale2 = new LinkedHashMap<Integer,Integer>();
		scale2.put(3, 20);
		scale2.put(4, 40);
		scale2.put(5, 25);
		scale2.put(6, 6);
		scale2.put(7, 5);
		scale2.put(8, 4);
		
		Map<Integer,Integer> scale3 = new LinkedHashMap<Integer,Integer>();
		scale3.put(5, 9);
		scale3.put(6, 12);
		scale3.put(7, 15);
		scale3.put(8, 15);
		scale3.put(9, 10);
		scale3.put(10, 9);
		scale3.put(11, 8);
		scale3.put(12, 7);
		scale3.put(13, 6);
		scale3.put(14, 5);
		scale3.put(15, 4);
		
		Map<Integer,Integer> scale4 = new LinkedHashMap<Integer,Integer>();
		scale4.put(8, 10);
		scale4.put(9, 15);
		scale4.put(10, 20);
		scale4.put(11, 10);
		scale4.put(12, 9);
		scale4.put(13, 8);
		scale4.put(14, 5);
		
		
		
	}

	
	public List<SignInBean> getSiginHistory(String uid, String page){
		List<SignInBean> ret = new ArrayList<SignInBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		int pageInt = 1;
		if(page != null && !"".equals(page))
			pageInt = Integer.parseInt(page);
		int pageNum = getPageNum(uid);
		if(pageNum == 0) return null;
		if(pageInt < 1) pageInt = 1;
		else if(pageInt > pageNum) pageInt = pageNum; 
		try {
			String sql = "select * from youhui_cn_fanli.tyh_activity_join where uid = ? order by timestamp desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, pageSize*(pageInt - 1));
			s.setInt(3, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				SignInBean bean = new SignInBean();
				bean.setDate(rs.getString("date"));
				bean.setJifenbaoNum(rs.getInt("jfb_num"));
				bean.setRunningDays(rs.getInt("running_days"));
				bean.setUid(rs.getString("uid"));
				ret.add(bean);
			}
		} catch (SQLException e) {
			ret= null;
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	public int getPageNum(String uid){
		Connection conn = MySQLDBIns.getInstance().getConnection();
		int page = 0;
		try {
			String sql = "select count(*) as scount from youhui_cn_fanli.tyh_activity_join where uid = ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			int count = 0;
			if(rs.next()){
				count = rs.getInt("scount");
			}
			int ys = count%pageSize;
			count = count - ys;
			page = count/pageSize;
			if(ys != 0){
				page++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return page;
	}
	
	/**
	 * 实时发送的，停用
	 * @param map
	 * @param conn
	 */
	public void updateStatus(Map<String, Integer> map, Connection conn){
		try {
			String sql = "update youhui_cn_fanli.tyh_activity_join set `status`=? where `id` = ?;";
			if(map != null){
				PreparedStatement s = conn.prepareStatement(sql);
				conn.setAutoCommit(false);
				for(Map.Entry<String, Integer> m : map.entrySet()){		
					if(m.getValue() != JiFenBaoTradeManager.Status_Init){
					s.setInt(1, m.getValue());
					s.setString(2, m.getKey());
					s.addBatch();
					}
				}
				conn.setAutoCommit(true);
				s.executeBatch();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取还未成功的订单，实时发送的，停用
	 * @param conn
	 * @return
	 */
	public List<String> getNotSuccess(Connection conn){
		List<String> list = new ArrayList<String>();
		try {
			String sql = "select * from youhui_cn_fanli.tyh_activity_join  where `status` <> ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setInt(1, JiFenBaoTradeManager.Status_Success);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				list.add(rs.getString("id"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
//		long s = System.currentTimeMillis();
//		Connection conn = MySQLDBIns.getInstance().getConnection();
//		System.out.println(SignInManager.getInstance().getDays("14312678", conn));
//		MySQLDBIns.getInstance().release(conn);
		
//		System.out.println( Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		
		System.out.println(SignInManager.getInstance().getJFBNum("100034074", 20));
	}
	
}
