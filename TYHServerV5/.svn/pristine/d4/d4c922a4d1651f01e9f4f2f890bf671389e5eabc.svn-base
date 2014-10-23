package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.youhui.bean.Activity;
import cn.youhui.bean.ActivityJoin;
import cn.youhui.bean.ActivityMingxi;
import cn.youhui.common.Config;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.log4ssy.api.Log4SSY;
import cn.youhui.log4ssy.utils.Enums.Debug;
import cn.youhui.log4ssy.utils.Enums.Event;
import cn.youhui.log4ssy.utils.Enums.Store;
import cn.youhui.log4ssy.utils.Enums.Type;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.TPool;

public class ActivityJoinManager {
	
	private static ActivityJoinManager instance = null;
	private static int pageSize = 20;
	private static SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
	private static SimpleDateFormat ft1 = new SimpleDateFormat("yyyyMMdd");
	
	private static final int NORMAL = 2;
	private static final int DELETE = 3;
	
	
	private ActivityJoinManager(){}
	
	public static ActivityJoinManager getInstance(){
		if(instance == null) instance = new ActivityJoinManager();
		return instance;
	}
	
	public List<ActivityJoin> getList(String uid, int pageInt){
		List<ActivityJoin> list = new ArrayList<ActivityJoin>();
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_cn_fanli.tyh_activity_join where uid = ? order by `timestamp` desc limit ?,?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.setInt(2, (pageSize * (pageInt - 1)));
			pstmt.setInt(3, pageSize);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				ActivityJoin bean = new ActivityJoin();
				bean.setActivityId(rs.getString("activity_id"));
				bean.setActivityName(rs.getString("activity_name"));
				bean.setDate(rs.getString("date"));
				bean.setId(rs.getString("id"));
				bean.setJfbNum(rs.getInt("jfb_num"));
				bean.setTimestamp(rs.getLong("timestamp"));
				bean.setStatus(rs.getInt("status"));
				list.add(bean);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public int getPageNum(String uid){
		int pageTotal = 0;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(*) as acount from youhui_cn_fanli.tyh_activity_join where uid = ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				int count = rs.getInt("acount");
				int ys = count%pageSize;
				pageTotal = (count-ys)/pageSize;
				if(ys > 0)
					pageTotal ++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return pageTotal;
	}
	
	
	public int join(Activity activity, String uid){
		return join(activity, uid, NORMAL);
	}
	
	public int join(Activity activity, String uid, int status){
		return join(activity, uid, 0, status);
	}
	
	public int join(Activity activity, String uid, int runningDay, int status){
		return join(activity, uid, runningDay, status, "" + System.currentTimeMillis());
	}
	
	public int join(Activity activity, String uid, String activityUniqueId, int status){
		return join(activity, uid, 0, status, activityUniqueId);
	}
	
	public int join(Activity activity, String uid, int runningDay, String activityUniqueId){
		return join(activity, uid, runningDay, NORMAL, activityUniqueId);
	}
	
	public int join(Activity activity, String uid, int runningDay,int status, String activityUniqueId){
		int ret = 0;	
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			
			if(activity == null || activity.getId() == null || "".equals(activity.getId()) || uid == null || "".equals(uid)){
				return ret;
			}
			
			if(isExsit(activity.getId(), activityUniqueId, conn)){
				ret = 2;
				return ret;
			}
			
			String sql = "insert into youhui_cn_fanli.tyh_activity_join(`id`,`uid`,`date`,`running_days`,`timestamp`,`jfb_num`,`activity_id`,`activity_name`,`status`,`activity_unique_id`) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(sql);
			String today = ft.format(new Date());
			
//			try {
//			if("8".equals(activity.getId())){
//				today = ft.format(ft1.parse(activityUniqueId.substring(0, 8)));
//			}
//			}catch (Exception e) {
//			}
			
			int n = -1;
			int j = 0;
			String tradeId = null;
			while(n<0 && j++<10){
				tradeId = getTradeId(uid);
				s.setString(1, tradeId);
				s.setString(2, uid);
				s.setString(3, today);
				s.setInt(4, runningDay);
				s.setLong(5, System.currentTimeMillis());
				s.setInt(6, activity.getJfbNum());
				s.setString(7, activity.getId());
				s.setString(8, activity.getName());
				s.setInt(9, status);
				s.setString(10, activityUniqueId);
				n = s.executeUpdate();
				if(n > 0){
					ret = 1;					
				}
			}
			if(n > 0){
				ActivityMingxi bean = new ActivityMingxi();
				bean.setActivityId(activity.getId());
				bean.setEvent(activity.getName());
				bean.setJfbNum(activity.getJfbNum());
				bean.setUid(uid);
				bean.setTradeId(tradeId);
				bean.setStatus(ActivityMingxiManager.Status_Get);
				ActivityMingxiManager.getInstance().add(bean);
				
//				newapi(tradeId,activity.getKey(),uid,activity.getJfbNum(),runningDay);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	public void newapi(String tradeId,String key,String uid,int jf,int runningDay){
		//测试新接口
		String url=Config.ACTIVITY_URL;
		String content="key="+key+"&uid="+uid+"&trade_id="+tradeId+"&jf="+jf+"&running_day="+runningDay;
		
		try{
			Log4SSY.Log(Debug.FALSE,"test-jiangjun", "uuuuuuuid", Event.CLICK, Type.TAG,Store.TAOBAO,tradeId , "activity-1", "{\"data\":\""+content+"\"}");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		TPool.getInstance().doit(new VisitThread(url, content,tradeId));
	}
	class VisitThread extends Thread{
		
		private String url = "";
		private String content = "";
		private String tradeId="";
		
		public VisitThread(String url, String content,String tradeId) {
			super();
			this.url = url;
			this.content = content;
			this.tradeId = tradeId;
		}
		
		public void run(){
			System.out.println(NetManager.getInstance().send(url,content));
		}
	}
	
	private boolean isExsit(String activityId, String uniqueId, Connection conn){
		boolean flag = false;	
		try {
			String sql = "select id from youhui_cn_fanli.tyh_activity_join where activity_id =? and `activity_unique_id` = ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, activityId);
			s.setString(2, uniqueId);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean join11(Activity activity, String uid, int runningDay, String outId, Connection conn){
		boolean flag = false;	
		try {
			String tradeId = getTradeId(activity.getId(), outId);
			String sql = "insert into youhui_cn_fanli.tyh_activity_join(`id`,`uid`,`date`,`running_days`,`timestamp`,`jfb_num`,`activity_id`,`activity_name`,`status`) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(sql);
			String today = ft.format(new Date());
			s.setString(1, tradeId);
			s.setString(2, uid);
			s.setString(3, today);
			s.setInt(4, runningDay);
			s.setLong(5, System.currentTimeMillis());
			s.setInt(6, activity.getJfbNum());
			s.setString(7, activity.getId());
			s.setString(8, activity.getName());
			s.setInt(9, NORMAL);
			int n = s.executeUpdate();
			if(n > 0){
				ActivityMingxi bean = new ActivityMingxi();
				bean.setActivityId(activity.getId());
				bean.setEvent(activity.getName());
				bean.setJfbNum(activity.getJfbNum());
				bean.setUid(uid);
				bean.setTradeId(tradeId);
				bean.setStatus(ActivityMingxiManager.Status_Get);
				if(ActivityMingxiManager.getInstance().add(bean)){
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	private String getTradeId(String activityId, String outTradeId){
		String today = ft1.format(new Date());
//		String actId = "000" + activityId;
//		actId = actId.substring(actId.length()-3, actId.length());
		String outId = "000000000" + outTradeId;
		outId = outId.substring(outId.length()-12, outId.length());
		return today  + outId;
	}
	
	private String getTradeId(String activityId, String outTradeId, String uid){
		if("1".equals(activityId)){
			return getTradeId("1", SignInManager.getInstance().getTradeId(uid));
		}else if("8".equals(activityId)){
			String actId = "000" + "8";
			actId = actId.substring(actId.length()-3, actId.length());
			outTradeId = actId + outTradeId;
			return getTradeId("8", outTradeId);
		}
		String today = ft1.format(new Date());
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
		String rank = getRankNum(8);
		return today + rank + cuid;
	}
	
	private String getTradeId(String uid){
		String today = ft1.format(new Date());
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
		String rank = getRankNum(8);
		return today + rank + cuid;
	}
	
	private String getRankNum(int size){
		StringBuffer sb = new StringBuffer();
		Random rm = new Random();
		for(int i = 0;i<size;i++){
			sb.append(rm.nextInt(10));
		}
		return sb.toString();
	}
	
	public long getLastJoinTime(String uid, String activityId){
		long lastTime = 0;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_cn_fanli.tyh_activity_join where `uid` = ? and `activity_id`=? order by `timestamp` desc;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, activityId);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				lastTime = rs.getLong("timestamp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return lastTime;
	}
	
	
	/**
	 * 获得的集分宝个数
	 * @param uid
	 * @param conn
	 * @return
	 */
	public int getGainJfbNum(String uid, Connection conn){
		int jfbNum = 0;
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			String sql = "select sum(jfb_num) as sumjfb from youhui_cn_fanli.tyh_activity_join where uid = ? and `status`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, NORMAL);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return jfbNum;
	}
	
	/**
	 * 获得的集分宝个数
	 * @param uid
	 * @param conn
	 * @return
	 */
	public int getGainJfbNum(String uid){
		int jfbNum = 0;
		if(uid == null || "".equals(uid))
			return jfbNum;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select sum(jfb_num) as sumjfb from youhui_cn_fanli.tyh_activity_join where uid = ? and `status`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, NORMAL);
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
	public static void main(String[] args) {
		Log4SSY.Log(Debug.TRUE,"test-jiangjun", "uuuuuuuid", Event.CLICK, Type.TAG,Store.TAOBAO,"ewrewrwerew" , "activity-1", "{\"data\":\"dfdsfdsfdsf\"}");
	}
	
	
	
}
