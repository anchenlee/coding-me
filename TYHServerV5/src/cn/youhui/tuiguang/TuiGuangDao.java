/**
 * 
 */
package cn.youhui.tuiguang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.youhui.dao.MySQLDBIns;

/**
 * 
 * @author lujiabin
 * @since 2014-9-9
 */
public class TuiGuangDao {
	//status说明：0：超时，1：调了推广商接口，2：信息不匹配，3：未登录,4：未调推广商借口,-1:初始值,5:回调了，但请求接口失败
	public static final int INITIAL = -1;
	public static final int TIME_OUT = 0;
	public static final int DO_CALLBACK = 1;
	public static final int INFO_ERROR = 2;
	public static final int NOT_LOGIN = 3;
	public static final int NOT_CALLBACK = 4;
	public static final int FAIL_CALLBACK = 5;
	
	private static TuiGuangDao instance;
	
	public static TuiGuangDao getInstance() {
		return instance == null ? new TuiGuangDao() : instance;
	}
	
	/**
	 * 根据idfa获取设备ID
	 * @param idfa
	 * @return
	 */
	public String getDid(String idfa) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `Did` FROM `suishou_tg`.`device` where `idfa`=?;";
		String did = "";
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, idfa);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				did = rs.getString("Did");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return did;
	}
	
	/**
	 * 根据设备id获取推广商ID
	 * @param did
	 * @return
	 */
	public String getSpid(String did) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `spid` FROM `suishou_tg`.`device` where `Did`=?;";
		String spid = "";
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, did);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				spid = rs.getString("spid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return spid;
	}
	
	/**
	 * 保存激活数据
	 * @param did
	 * @param spid
	 * @param activatetime
	 * @return
	 */
	public boolean saveActivateInfo(String did,String spid,long activatetime,String device_token) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `suishou_tg`.`activate` (`Did`, `spid`, `create_time`,`device_token`) VALUES (?, ?, ?, ?);";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, did);
			ps.setString(2, spid);
			ps.setLong(3, activatetime);
			ps.setString(4, device_token);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 获取一个设备记录更新时间
	 * @param did
	 * @return
	 */
	public long getUpdateTime(String did) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `update_time` from `suishou_tg`.`device` where `Did`=?";
		long time = 0;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, did);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				time = rs.getLong("update_time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return time;
	}
	
	/**
	 * 获取记录创建时间
	 * @param did
	 * @return
	 */
	public long getCreateTime(String did) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `create_time` from `suishou_tg`.`device` where `Did`=?";
		long time = 0;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, did);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				time = rs.getLong("create_time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return time;
	}
	
	/**
	 * 设置状态码
	 * @param status
	 * @param did
	 * @return
	 */
	public boolean setStatus(int status,String device_token,int pre_status){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `suishou_tg`.`device` SET `status`=?, `update_time`=? WHERE `device_token`=? and `status`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setLong(2, System.currentTimeMillis());
			ps.setString(3, device_token);
			ps.setInt(4, pre_status);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 根据推广商ID获取在那一个环节回调
	 * @param spid
	 * @return
	 */
	public String getIsCallback(String spid) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `callback` FROM `suishou_tg`.`tg_partner` where `spid`=?;";
		String callback = "";
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, spid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				callback = rs.getString("callback");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return callback;
	}
	
	/**
	 * 保存回调信息
	 * @param did
	 * @param spid
	 * @param activatetime
	 * @return
	 */
	public boolean saveCallbackInfo(String did,String spid,long activatetime,String device_token) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `suishou_tg`.`callback` (`Did`, `spid`, `create_time`,`device_token`) VALUES (?, ?, ?,?);";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, did);
			ps.setString(2, spid);
			ps.setLong(3, activatetime);
			ps.setString(4, device_token);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 保存注册信息
	 * @param did
	 * @param spid
	 * @param activatetime
	 * @param uid
	 * @return
	 */
	public boolean saveRegisterInfo(String did,String spid,long activatetime,String uid,String device_token) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `suishou_tg`.`register` (`Did`, `spid`, `uid`, `create_time`,`device_token`) VALUES (?, ?, ?, ?, ?);";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, did);
			ps.setString(2, spid);
			ps.setString(3, uid);
			ps.setLong(4, activatetime);
			ps.setString(5, device_token);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 保存签到信息
	 * @param did
	 * @param spid
	 * @param activatetime
	 * @param uid
	 * @return
	 */
	public boolean saveSignInfo(String did,String spid,long activatetime,String uid,String device_token) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `suishou_tg`.`sign_in` (`Did`, `spid`, `uid`, `create_time`,`device_token`) VALUES (?, ?, ?, ?,?);";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, did);
			ps.setString(2, spid);
			ps.setString(3, uid);
			ps.setLong(4, activatetime);
			ps.setString(5, device_token);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 检查是否有激活记录
	 * @param did
	 * @param spid
	 * @return
	 */
	public boolean isActivateExist(String did,String spid) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `id` FROM `suishou_tg`.`activate` where `Did`=? and `spid`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, did);
			ps.setString(2, spid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 检查是否有注册记录
	 * @param did
	 * @param spid
	 * @return
	 */
	public boolean isRegisterExist(String did,String spid) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `id` FROM `suishou_tg`.`register` where `Did`=? and `spid`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, did);
			ps.setString(2, spid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 检查是否有签到记录
	 * @param did
	 * @param spid
	 * @return
	 */
	public boolean isSignExist(String did,String spid) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `id` FROM `suishou_tg`.`sign_in` where `Did`=? and `spid`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, did);
			ps.setString(2, spid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 获取设备唯一标识号
	 * @param idfa
	 * @return
	 */
	public String getDeviceToken(String idfa) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `device_token` FROM `suishou_tg`.`device` where `idfa`=?;";
		String device_token = "";
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, idfa);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				device_token = rs.getString("device_token");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return device_token;
	}
	/**
	 * 获取米迪回调URL
	 * @param idfa
	 * @return
	 */
	public String getMiDiCallback(String idfa){
		String tmp="";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "select * from  `suishou_tg`.`device`  WHERE `idfa`=? ;";
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,idfa);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				String callback=rs.getString("ad_callback");
				String appid=rs.getString("appid");
				String mac=rs.getString("mac");
				if(callback==null||callback.equals("")){
					tmp="http://api.miidi.net/cas/advAck.bin?appid="+appid+"&idfa="+idfa+"&mac="+mac;
				}else{
					tmp=callback;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return tmp;
	}
	
	/**
	 * 获得回调地址
	 * @param idfa
	 * @return
	 */
	public String getCallback(String idfa){
		String tmp="";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "select `ad_callback` from  `suishou_tg`.`device`  WHERE `idfa`=? ;";
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,idfa);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				String callback=rs.getString("ad_callback");
				if(callback != null && !callback.equals("")){
					tmp=callback;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return tmp;
	}
	
	/**
	 * 更新总表激活时间
	 * @param Did
	 * @param activatetime
	 * @return
	 */
	public boolean updateCenterTable1(String Did,long activatetime){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `suishou_tg`.`device` SET `activate_time`=?, `update_time`=? WHERE `Did`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, activatetime);
			ps.setLong(2, activatetime);
			ps.setString(3, Did);
			if(ps.executeUpdate()>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 更新总表注册时间
	 * @param Did
	 * @param registertime
	 * @return
	 */
	public boolean updateCenterTable2(String Did,long registertime,String uid){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `suishou_tg`.`device` SET `register_time`=?, `update_time`=?, `uid`=? WHERE `Did`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, registertime);
			ps.setLong(2, registertime);
			ps.setString(3, uid);
			ps.setString(4, Did);
			if(ps.executeUpdate()>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 更新总表签到时间
	 * @param Did
	 * @param signtime
	 * @return
	 */
	public boolean updateCenterTable3(String Did,long signtime,String uid){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `suishou_tg`.`device` SET `sign_in_time`=?, `update_time`=?, `uid`=? WHERE `Did`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, signtime);
			ps.setLong(2, signtime);
			ps.setString(3, uid);
			ps.setString(4, Did);
			if(ps.executeUpdate()>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 更新总表回调时间
	 * @param Did
	 * @param callbacktime
	 * @return
	 */
	public boolean updateCenterTable4(String Did,long callbacktime){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `suishou_tg`.`device` SET `callback_time`=?, `update_time`=? WHERE `Did`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, callbacktime);
			ps.setLong(2, callbacktime);
			ps.setString(3, Did);
			if(ps.executeUpdate()>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 获取状态
	 * @param device_token
	 * @return
	 */
	public String getStatus(String device_token){
		String status="";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "select `status` from  `suishou_tg`.`device`  WHERE `device_token`=? ;";
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,device_token);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=rs.getString("status");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return status;
	}
	
	/**
	 * 获取多盟回调所需信息
	 * @param device_token
	 * @return
	 */
	public String[] getDomobInfo(String device_token){
		String[] info={"",""};
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "select `mac`,`create_time` from  `suishou_tg`.`device`  WHERE `device_token`=? ;";
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,device_token);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				info[0] = rs.getString("mac");
				info[1] = rs.getString("create_time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return info;
	}
}
