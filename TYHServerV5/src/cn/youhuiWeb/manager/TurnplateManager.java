package cn.youhuiWeb.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import cn.youhui.api.activity.ActivityClient;
import cn.youhui.api.activity.ActivityRequest;
import cn.youhui.dao.MySQLDBIns;

/**
 * 转盘抽奖
 * @author liuxj
 * @since 2014-8-19
 */
public class TurnplateManager {	
	private static final int RET_SUCCESS = 1;      //抽奖成功
	private static final int RET_TODAY_NOT_ALLOWED = 2;   //uid 今天已抽过奖
	
	
	private static final String ACTIVITY_KEY = "4dobqmh6";

	private static Map<Integer, Integer> levelJfbNum =  new LinkedHashMap<Integer,Integer>();   //奖励个数
	
	private TurnplateManager(){
		
		levelJfbNum.put(1, 1);
		levelJfbNum.put(2, 5);
		levelJfbNum.put(3, 10);
		levelJfbNum.put(4, 50);
		levelJfbNum.put(5, 100);
	}
	
	private static TurnplateManager instance = null;
	
	public static TurnplateManager getInstance(){
		if(instance == null){
			instance = new TurnplateManager();
		}
		return instance;
	}

	public int[] turn(String uid, String ip){
		int ret[] = {0,0};		
		try{
			
			if(!isUidAllowed(uid)){
				ret[0] = RET_TODAY_NOT_ALLOWED;
			}else{
				int resultCode = getRandom();
				if(add(uid, ip, resultCode)){
					ret[0] = RET_SUCCESS;
					ret[1] = resultCode;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	private int getRandom(){
		 int i =  new Random().nextInt(100);
		 if(i<40) return 1;			 
		 else if(i<70) return 2;
		 else if(i<85) return 3;
		 else if(i<95) return 4;			 
		 else return 5;
	}	
	
	/**
	 * 添加中奖记录
	 */
	private boolean add(String uid, String ip, int resultCode){
		boolean flag = false;
		PreparedStatement ps = null;
		Connection conn = null;	
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_web.turnplate (uid,ip,result_code,create_time)" +
					" values (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			long now = System.currentTimeMillis();
			ps.setString(1, uid);
			ps.setString(2, ip);
			ps.setInt(3, resultCode);
			ps.setLong(4, now);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally {
			MySQLDBIns.getInstance().release(ps,conn);
		}		
	}
	
	private boolean isUidAllowed(String uid){
		boolean flag = true;
		PreparedStatement ps = null;
		Connection conn = null;	
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select create_time from youhui_web.turnplate where uid=? order by create_time desc limit 1";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				long timeStamp = rs.getLong("create_time");
				long now = System.currentTimeMillis();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(sdf.format(now).equals(sdf.format(timeStamp))){
					flag = false;
				}
			}
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally {
			MySQLDBIns.getInstance().release(ps,conn);
		}
		
	}	

	public boolean sendGift(String uid, int level){
		ActivityRequest acrequest = new ActivityRequest(uid, ACTIVITY_KEY, uid + System.currentTimeMillis(), levelJfbNum.get(level), 0);
		try {
			ActivityClient.execut(acrequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		int i=0;
		while(true){
			i++;
			int num = TurnplateManager.getInstance().getRandom();
			
			System.out.println("--------"+i+":"+num);
		}
		
	}
	
}
