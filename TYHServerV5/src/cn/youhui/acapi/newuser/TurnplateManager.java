package cn.youhui.acapi.newuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import cn.youhui.api.activity.ActivityClient;
import cn.youhui.api.activity.ActivityRequest;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.manager.UserManager;

/**
 * 转盘抽奖
 * @author lijun
 * @since 2014-7-6
 */
public class TurnplateManager {
	
	private static Map<Integer,Integer> scale = new LinkedHashMap<Integer,Integer>();
	
	private static final int STATUS_INIT = 0;  //初始
	private static final int STATUS_HAS_GET = 1;  //已领取
	
	private static final int RET_SUCCESS = 1;      //抽奖成功
	private static final int RET_EXIST_IMEI = 2;   //已存在的imei
	
	private static final String ACTIVITY_KEY = "m05zsj62";
	
	private static final long OUT_TIME = 24*60*60*1000;
	
	private static Map<Integer, Integer> levelJfbNum =  new LinkedHashMap<Integer,Integer>();   //奖励个数
	
	private TurnplateManager(){
		
		levelJfbNum.put(1, 5);
		levelJfbNum.put(2, 10);
		levelJfbNum.put(3, 50);
		levelJfbNum.put(4, 100);
		levelJfbNum.put(5, 0);
	}
	
	private static TurnplateManager instance = null;
	
	public static TurnplateManager getInstance(){
		if(instance == null){
			instance = new TurnplateManager();
		}
		return instance;
	}

	/**
	 * 根据imei 抽奖
	 * @param imei
	 * @return
	 */
	public int[] turn(String imei, String imsi, String ip, boolean isException, String platform){
		int ret[] = {0,0};
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			if(isExistImei(imei, conn)){
				ret[0] = RET_EXIST_IMEI;
			}else{
				int resultCode = 0;
				boolean isOld = UserManager.getInstance().isExsitImei(imei, conn);
				if(isException){
					resultCode = 1;
				}else if(imei != null && imei.equals(imsi)){
					resultCode = 1;
				}else if(isExistImsi(imsi, conn)){
					resultCode = 1;
				}else{
					int ipTimes = getIpTimes(ip, conn);
					if(ipTimes >= 5){
						resultCode = 1;
					}else{
						resultCode = getRandom(isOld);
						if(resultCode == 5 && ipTimes >= 2){
							resultCode = 4;
						}
					}
				}
				if(resultCode == 5){
					if(!hasMcdCoupon(isOld, conn)){       //若优惠券发完
						resultCode = 4;
					}
				}
				if(add(imei, imsi, ip, resultCode,platform, isOld, conn)){
					ret[0] = RET_SUCCESS;
					ret[1] = resultCode;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(null, conn);
		}
		return ret;
	}
	
	private int getRandom(boolean isOld){
		if(isOld){
			return getRandomForOld();
		}else{
			return getRandomForNew();
		}
	}
	
	private int getRandomForNew(){    
		long now = System.currentTimeMillis();
		long t819 = 1408464000000l;
		long t820 = 1408550400000l;
		long t822 = 1408723200000l;
		long t824 = 1408896000000l;
		long t825 = 1408982400000l;
		if(now < t819){        
			scale.put(1, 0);
			scale.put(2, 5);
			scale.put(3, 5);
			scale.put(4, 20);
			scale.put(5, 70);
		}else if(now < t820){        
			scale.put(1, 0);
			scale.put(2, 5);
			scale.put(3, 5);
			scale.put(4, 30);
			scale.put(5, 60);
		}else if(now < t822){        
			scale.put(1, 0);
			scale.put(2, 5);
			scale.put(3, 5);
			scale.put(4, 40);
			scale.put(5, 50);
		}else if(now < t824){
			scale.put(1, 0);
			scale.put(2, 5);
			scale.put(3, 5);
			scale.put(4, 60);
			scale.put(5, 30);
		}else{
			scale.put(1, 0);
			scale.put(2, 5);
			scale.put(3, 5);
			scale.put(4, 60);
			scale.put(5, 30);
		}
		
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
	
	private int getRandomForOld(){    
		long now = System.currentTimeMillis();
		long t819 = 1408464000000l;
		long t820 = 1408550400000l;
		long t822 = 1408723200000l;
		long t824 = 1408896000000l;
		long t825 = 1408982400000l;
		if(now < t819){        
			scale.put(1, 0);
			scale.put(2, 10);
			scale.put(3, 10);
			scale.put(4, 30);
			scale.put(5, 50);
		}else if(now < t820){        
			scale.put(1, 0);
			scale.put(2, 10);
			scale.put(3, 20);
			scale.put(4, 50);
			scale.put(5, 20);
		}else if(now < t822){        
			scale.put(1, 0);
			scale.put(2, 10);
			scale.put(3, 20);
			scale.put(4, 60);
			scale.put(5, 10);
		}else if(now < t824){
			scale.put(1, 0);
			scale.put(2, 10);
			scale.put(3, 20);
			scale.put(4, 60);
			scale.put(5, 10);
		}else{
			scale.put(1, 0);
			scale.put(2, 10);
			scale.put(3, 20);
			scale.put(4, 50);
			scale.put(5, 20);
		}
		
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
	
	/**
	 * 添加中奖记录
	 * @param imei
	 * @param ip
	 * @param resultCode
	 * @return
	 */
	private boolean add(String imei, String imsi, String ip, int resultCode,String platform, boolean isOld, Connection conn){
		boolean flag = false;
		PreparedStatement ps = null;
		try{
			String sql = "insert into `youhui_luckac`.`newuser_turnplate` (`imei`,`ip`,`result_code`,`status`,`create_time`,`update_time`,`imsi`,`platform`,`is_old`)" +
					" values (?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			long now = System.currentTimeMillis();
			ps.setString(1, imei);
			ps.setString(2, ip);
			ps.setInt(3, resultCode);
			ps.setInt(4, STATUS_INIT);
			ps.setLong(5, now);
			ps.setLong(6, now);
			ps.setString(7, imsi);
			ps.setString(8, platform);
			ps.setInt(9, isOld ? 1:0);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * imei 是否已存在
	 * @param imei
	 * @param ip
	 * @param resultCode
	 * @return
	 */
	public boolean isExistImei(String imei){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			flag = isExistImei(imei, conn);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(null, conn);
		}
		return flag;
	}
	
	/**
	 * imei 是否已存在
	 * @param imei
	 * @param ip
	 * @param resultCode
	 * @return
	 */
	private boolean isExistImei(String imei, Connection conn){
		boolean flag = false;
		java.sql.PreparedStatement ps = null;
		try{
			String sql = "select imei from `youhui_luckac`.`newuser_turnplate` where `imei`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, imei);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * imsi 是否已存在
	 * @param imsi
	 * @param ip
	 * @param resultCode
	 * @return
	 */
	private boolean isExistImsi(String imsi, Connection conn){
		boolean flag = false;
		java.sql.PreparedStatement ps = null;
		try{
			String sql = "select imsi from `youhui_luckac`.`newuser_turnplate` where `imsi`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, imsi);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ip 抽奖次数
	 * @param ip
	 * @param resultCode
	 * @return
	 */
	private int getIpTimes(String ip, Connection conn){
		int ret = 0;
		java.sql.PreparedStatement ps = null;
		try{
			String sql = "select count(id) as ac from `youhui_luckac`.`newuser_turnplate` where `ip`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ip);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ret = rs.getInt("ac");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
    
	/**
	 * 是否参加过
	 * @param imei
	 * @return
	 */
	public boolean hasJoin(String imei){
		boolean flag = false;
		java.sql.PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select `result_code` from `youhui_luckac`.`newuser_turnplate` where `imei`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, imei);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 返回没领奖的  奖品等级
	 * @param imei
	 * @return
	 */
	public int getNoGetGiftResult(String imei){
		int ret = 0;
		java.sql.PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select `result_code` from `youhui_luckac`.`newuser_turnplate` where `imei`=? and `status`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, imei);
			ps.setInt(2, STATUS_INIT);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ret = rs.getInt("result_code");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return ret;
	}
	
	/**
	 * 中了话费，并没有领取话费的
	 * @param imei
	 * @param uid
	 * @return
	 */
	public boolean isNogetHuafei(String imei, String uid){
		boolean flag = false;
		java.sql.PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select `result_code` from `youhui_luckac`.`newuser_turnplate` where `imei`=? and `get_uid`=? and `result_code`=? and `create_time`>?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, imei);
			ps.setString(2, uid);
			ps.setInt(3, 5);
			ps.setLong(4, 1408291200000l);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				 if(5 == rs.getInt("result_code")){
					 sql = "select `id` from youhui_luckac.luck_huafei where `imei` = ?;";
					 ps = conn.prepareStatement(sql);
					 ps.setString(1, imei);
					 ResultSet r = ps.executeQuery();
					 if(!r.next()){
						 flag = true;
					 }
				 }
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 一段时间发出麦当劳券的数量
	 * @param startTime
	 * @param endTime
	 * @param conn
	 * @return
	 */
	private int getMCDNum(long startTime, long endTime, Connection conn){
		int num = 0;
		java.sql.PreparedStatement ps = null;
		try{
			String sql = "select count(id) as ac from `youhui_luckac`.`newuser_turnplate` where `create_time`>? and `create_time`<?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, startTime);
			ps.setLong(2, endTime);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				num = rs.getInt("ac");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return num;
	}
	
	/**
	 * 是否有优惠券
	 * @param conn
	 * @return
	 */
	private boolean hasMcdCoupon(boolean isOld, Connection conn){
		boolean flag = false;
		java.sql.PreparedStatement ps = null;
		try{
			int type = 0;     //新用户
			if(isOld){
				type = 1;
			}
			
			String sql = "select `id`,`num` FROM youhui_luckac.mcd_nums where `type`=? and `end_time`>? and `start_time`<?;";
			ps = conn.prepareStatement(sql);
			long now = System.currentTimeMillis();
			ps.setInt(1, type);
			ps.setLong(2, now);
			ps.setLong(3, now);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int num = rs.getInt("num");
				String id = rs.getString("id");
				if(num > 0){
					flag = true;
					subMcdCoupon(id, conn);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	private void subMcdCoupon(String id, Connection conn){
		java.sql.PreparedStatement ps = null;
		try{
			String sql = "update youhui_luckac.mcd_nums set num = num -1 where `id` = ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 是否存在没领取的
	 * @param imei
	 * @return
	 */
	public boolean hasNoGetGift(String imei){
		boolean flag = false;
		java.sql.PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select `result_code` from `youhui_luckac`.`newuser_turnplate` where `imei`=? and `status`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, imei);
			ps.setInt(2, STATUS_INIT);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	
	/**
	 * 领取奖励
	 * @param imei
	 * @param uid
	 * @return
	 */
	public int[] getGift(String imei, String uid){
		int ret[] = {0,0};
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			
//			if(!UserManager.getInstance().isNewUser(uid, conn)){
//				ret[0] = 2;
//				return ret;
//			}
			if(isTimeOut(imei, conn)){
				ret[0] = 3;
				return ret;
			}
			
			String sql = "select `id` from `youhui_luckac`.`newuser_turnplate` where `get_uid`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet r = ps.executeQuery();
			if(r.next()){
				ret[0] = 2;
				return ret;
			}
			
			sql = "update `youhui_luckac`.`newuser_turnplate` set `get_uid`=?, `status`=?,`update_time`=? where `imei`=? and `status`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, STATUS_HAS_GET);
			ps.setLong(3, System.currentTimeMillis());
			ps.setString(4, imei);
			ps.setInt(5, STATUS_INIT);
			if(ps.executeUpdate() > 0){
				ret[0] = 1;
				sql = "select result_code from `youhui_luckac`.`newuser_turnplate` where `imei`=?";
				PreparedStatement s = conn.prepareStatement(sql);
				s.setString(1, imei);
				ResultSet rs = s.executeQuery();
				if(rs.next()){
					ret[1] = rs.getInt("result_code");
					sendGift(uid, ret[1]);
				}
			}else{
				ret[0] = 2;  //已经参加过了
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return ret;
	}
	
	/**
	 * 是否过期
	 * @param imei
	 * @param conn
	 * @return
	 */
	private boolean isTimeOut(String imei, Connection conn){
		boolean flag = false;
		java.sql.PreparedStatement ps = null;
		try{
			String sql = "select `create_time` from `youhui_luckac`.`newuser_turnplate` where `imei`=? and `result_code`=5 `status`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, imei);
			ps.setInt(2, STATUS_INIT);
			long now = System.currentTimeMillis();
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
//				long t719 = 1405699200000l;
				long createTime = rs.getLong("create_time");
				if(now > (createTime + OUT_TIME)){
					flag = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 发放奖励
	 * @param uid
	 * @param ret 几等奖
	 */
	private void sendGift(String uid, int level){
		if(level <= 4){
			ActivityRequest acrequest = new ActivityRequest(uid, ACTIVITY_KEY, uid + System.currentTimeMillis(), levelJfbNum.get(level), 0);
			try {
				ActivityClient.execut(acrequest);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(level == 5){
//			McdActivityManager.purchaseMCD(uid);
		}
	}
	
	/**
	 * 是否中的最大奖 5
	 * @param imei
	 * @param conn
	 * @return
	 */
	public boolean isLuck5(String imei, String uid){
		boolean flag = false;
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select `create_time` from `youhui_luckac`.`newuser_turnplate` where `imei`=? and `get_uid`=? and `result_code`=5;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, imei);
			ps.setString(2, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				long t801 = 1406736000000l;
				long createTime = rs.getLong("create_time");
				if(createTime > t801){
					flag = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	public static void main(String[] args) {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		System.out.println(TurnplateManager.getInstance().luckDeviceInfoSave("mei", "imsi", "sdfs", "12.454.424.11.1", null, "platform"));
		MySQLDBIns.getInstance().release(conn);
	}
	
	/**
	 * 存储用户设备信息
	 * @param imei
	 * @param imsi
	 * @param userAgent
	 * @param data
	 */
	public boolean luckDeviceInfoSave(String imei, String imsi ,String userAgent , String ip, String [] data, String platform){
		boolean flag = false;
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_luckac.luck_device_info(mac,android_id,installtion_id,first_installtion_id,ip,imei,imsi,user_agent,create_time,`update_time`,`platform`) values(?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, data[0]);
			ps.setString(2, data[1]);
			ps.setString(3, data[2]);
			ps.setString(4, data[3]);
			ps.setString(5, ip);
			ps.setString(6, imei);
			ps.setString(7, imsi);
			ps.setString(8, userAgent);
			ps.setLong(9, System.currentTimeMillis());
			ps.setLong(10, System.currentTimeMillis());
			ps.setString(11, platform);
			if(ps.executeUpdate() > 0){
					flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
}
