package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


import cn.youhui.bean.UserBean;
import cn.youhui.dao.MySQLDBIns;
//import cn.youhui.shareapp.ShareAppActivityThread;
//import cn.youhui.shareapp.ShareRecordManager;
import cn.youhui.utils.ScaleUtils;
import cn.youhui.utils.StringUtils;
import cn.youhui.utils.TPool;
import cn.youhui.utils.UserUtil;

public class UserManager {
	
	 public static long monomark = 1000;         //用来标记用户是在推广活动中注册的
	
	private static UserManager instance = null;
	public static UserManager getInstance(){
		if(instance == null) instance = new UserManager();
		return instance;
	}
	
	public boolean addzfb(String uid, String zfb){
		boolean flag = false;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "insert into youhui_v3.user_zfb(`uid`,`zfb`,`timestamp`) values(?,?,?)";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, zfb);
			s.setLong(3, System.currentTimeMillis());
			int i = s.executeUpdate();
			if(i > 0){
				flag = true;
			}
			sql = "update youhui_v3.user set `zfb` = ? where uid = ?";
			s = conn.prepareStatement(sql);
			s.setString(1, zfb);
			s.setString(2, uid);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public String getZfb(String uid, Connection conn){
		String ret = null;
		try {
			String sql = "select * from youhui_v3.user_zfb where uid = ? order by `timestamp` desc";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				ret = rs.getString("zfb");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public boolean resetUserSex(String uid,String sex) {
		boolean flag = false;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql1 = "update youhui_v3.user set sex=? where uid=?";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			   s1.setString(1, sex);
				s1.setString(2, uid);
				int i = s1.executeUpdate();
				if(i>0) flag = true;
				s1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public boolean isUserExist(String uid, String taobaoNick){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "select uid from youhui_v3.user where uid=? and taobao_nick = ?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			s1.setString(2, taobaoNick);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public String getUserNick(String uid){
		if (uid == null || uid.equals("")){
			return null;
		}
		
		String taobao_nick = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "select taobao_nick from youhui_v3.user where uid=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				taobao_nick = rs.getString("taobao_nick");
			}
		}catch (SQLException e){
			taobao_nick = null;
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return taobao_nick;
	}
	
	public String getUserNickWithConn(String uid, Connection conn)
	{
		if (uid == null || uid.equals(""))
		{
			return null;
		}
		
		String taobao_nick = null;
		try
		{
			String sql1 = "select taobao_nick from youhui_v3.user where uid=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs = s1.executeQuery();
			if(rs.next())
			{
				taobao_nick = rs.getString("taobao_nick");
			}
		} 
		catch (SQLException e) 
		{
			taobao_nick = null;
		}
		return taobao_nick;
	}
	
	public boolean isPhoneExist(String phoneNum){
		boolean flag = false;
		if(phoneNum == null || "".equals(phoneNum)){
			return flag;
		}
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "select uid from youhui_v3.user where phone_num=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, phoneNum);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public String getNickOrPhoneWithConn(String uid, Connection conn){
		if (uid == null || uid.equals("")){
			return null;
		}
		
		String torp = null;
		try{
			String sql1 = "select taobao_nick,phone_num from youhui_v3.user where uid=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				torp = rs.getString("taobao_nick");
				if(torp == null || "".equals(torp)){
					torp = UserUtil.jmPhoneNum(rs.getString("phone_num"));
				}
			}
		}catch (SQLException e) {
			torp = null;
		}
		return torp;
	}
	
	/**
	 * 检测uid,phoneNum是否匹配
	 * @param phoneNum
	 * @param uid
	 * @return
	 */
	public boolean checkUidPhone(String phoneNum, String uid){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "select uid from youhui_v3.user where `phone_num`=? and `uid` = ?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, phoneNum);
			s1.setString(2, uid);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 验证密码
	 * @param uid
	 * @param password
	 * @return
	 */
	public boolean checkUidPassword(String uid, String password){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "select `uid` from `youhui_v3`.`user` where `uid` = ? and `password`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			s1.setString(2, password);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 绑定电话
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public boolean bindingByPhone(String phoneNum, String password ,String uid){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			flag = bindingByPhone(phoneNum, password, uid, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 绑定电话
	 * @param phoneNum
	 * @param password
	 * @param uid
	 * @param conn
	 * @return
	 */
	private boolean bindingByPhone(String phoneNum, String password ,String uid, Connection conn){
		boolean flag = false;
		try {
			String sql = "select phone_num from youhui_v3.user where `uid`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				String oldPhone = rs.getString("phone_num");
				if(oldPhone != null && !"".equals(oldPhone)){
					return flag;
				}
			}
			sql = "update youhui_v3.user set `phone_num`=?,`password`=? where `uid`=?;";
			s = conn.prepareStatement(sql);
			s.setString(1, phoneNum);
			s.setString(2, password);
			s.setString(3, uid);
			int i = s.executeUpdate();
			if(i > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 修改密码
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public boolean resetPassword(String password ,String phoneNum){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update youhui_v3.user set `password`=? where `phone_num`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, password);
			s.setString(2, phoneNum);
			int i = s.executeUpdate();
			if(i > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	
	/**
	 * 重新绑定手机号码
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public boolean resetPhoneNum(String phoneNum, String uid){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update youhui_v3.user set `phone_num`=? where `uid`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, phoneNum);
			s.setString(2, uid);
			int i = s.executeUpdate();
			if(i > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 注册
	 * @param phoneNum
	 * @param password
	 * @param uid
	 * @return
	 */
	public void registerByPhone(UserBean userBean){
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			int i = 0;
			int j = -1;
			String uid = null;
			while(i<10 && j <1){
				i++;
				uid = getRandomUid();
				String sql = "insert into `youhui_v3`.`user`(uid,imei,imsi,creat_time,active_time,last_time,platform,phone_num,password,taobao_nick) values (?,?,?,?,?,?,?,?,?,?);";
				PreparedStatement pstmt= conn.prepareStatement(sql);
				pstmt.setString(1, uid);
				pstmt.setString(2,userBean.getImei());
				pstmt.setString(3, userBean.getImsi());
				long now = System.currentTimeMillis();
				pstmt.setLong(4, now);
				pstmt.setLong(5, now);
				pstmt.setLong(6, now);
				pstmt.setString(7, userBean.getPlatform());
				pstmt.setString(8, userBean.getPhoneNum());
				pstmt.setString(9, userBean.getPassword());
				pstmt.setString(10, null);
				j = pstmt.executeUpdate();
			}
			if(j > 0){
				userBean.setUid(uid);
				if(userBean.getChannel() != null){
					saveChannel(uid, userBean.getChannel(), conn);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
	}
	
	/**
	 * 和之前的保持一致
	 * @return
	 */
	private String getRandomUid(){
		Random r = new Random();
		int a = r.nextInt(100000000);
		int b = 10000000 + a;
		return b+"";
	}
	
	public UserBean login(String phoneNum, String password){
		UserBean user = new UserBean();
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "select * from youhui_v3.user where `phone_num`=? and `password`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, phoneNum);
			s1.setString(2, password);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				user.setUid(rs.getString("uid"));
				user.setPhoneNum(rs.getString("phone_num"));
				user.setTaobaoNick(rs.getString("taobao_nick"));
				user.setTaobaoUid(rs.getString("taobao_uid"));
				user.setPassword(rs.getString("password"));
			}else{
				user = null;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return user;
	}
	
	public UserBean getUserByUid(String uid){
		UserBean user = new UserBean();
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_v3.user where `uid`=? ;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);			
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				user.setUid(rs.getString("uid"));
				user.setPhoneNum(rs.getString("phone_num"));
				user.setTaobaoNick(rs.getString("taobao_nick"));
				user.setTaobaoUid(rs.getString("taobao_uid"));
				user.setPassword(rs.getString("password"));
			}else{
				user = null;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return user;
	}
	
	/**
	 * uid登陆
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public UserBean loginByUid(String uid, String password){
		UserBean user = new UserBean();
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "select * from youhui_v3.user where `uid`=? and `password`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			s1.setString(2, password);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				user.setUid(rs.getString("uid"));
				user.setPhoneNum(rs.getString("phone_num"));
				user.setTaobaoNick(rs.getString("taobao_nick"));
			}else{
				user = null;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return user;
	}
	
	/**
	 * 获取uid绑定的手机
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public String getPhone(String uid){
		String phoneNum = null;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			phoneNum = getPhone(uid, conn);
		} catch (Exception e){
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return phoneNum;
	}

	public String getPhone(String uid, Connection conn){
		String phoneNum = null;
		try {
			String sql1 = "select phone_num from youhui_v3.user where `uid`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				phoneNum = rs.getString("phone_num");
			}else{
				phoneNum = null;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return phoneNum;
	}
	
	public UserBean getUser(String uid, Connection conn){
		UserBean user = new UserBean();
		try {
			String sql1 = "select taobao_uid,active_time from youhui_v3.user where `uid`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				user.setTaobaoUid(rs.getString("taobao_uid"));
				user.setActiveTime(rs.getLong("active_time"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 绑定淘宝帐号
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public UserBean bindingByTB(String taobaoNick, String taobaoUid ,String uid, String phoneNum){
		UserBean user = new UserBean();
		user.setTaobaoNick(taobaoNick);
		user.setTaobaoUid(taobaoUid);
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			if(!isExsitTBNick(taobaoNick, conn)){         //taobaonick 不存在
				String sql = "update youhui_v3.user set `taobao_nick`=?,`taobao_uid`=? where `uid`=?;";
				PreparedStatement s = conn.prepareStatement(sql);
				s.setString(1, taobaoNick);
				s.setString(2, taobaoUid);
				s.setString(3, uid);
				int i = s.executeUpdate();
				if(i > 0){
					user.setUid(uid);
				}
			}else if(isTBNickBindPhone(taobaoNick, conn)){          //taobaonick 已绑定了手机号
				user = null;
			}else{
				
				if(removePhone(uid, phoneNum, conn)){                    //移除手机号
					String password = getPassword(uid, conn);
					if(password != null && !"".equals(password)){
						String oldUid = getUid(taobaoNick, conn);
						if(!StringUtils.isEmpty(oldUid) && bindingByPhone(phoneNum, password, oldUid, conn)){         //将手机绑定在 老的淘宝帐号上
							user.setUid(oldUid);                 //返回老的uid
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return user;
	}
	
	/**
	 * taobaonick 是否存在
	 * 
	 */
	public boolean isExsitTBNick(String taobaoNick, Connection conn){
		boolean flag = false;
		try {
			String sql1 = "select uid from youhui_v3.user where `taobao_nick`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, taobaoNick);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 是否存在uid
	 * @param uid
	 * @param conn
	 * @return
	 */
	public boolean isExsitUid(String uid, Connection conn){
		boolean flag = false;
		try {
			String sql1 = "select uid from youhui_v3.user where `uid`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 是否存在uid
	 * @param uid
	 * @param conn
	 * @return
	 */
	public boolean isBindPhone(String uid){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "select `phone_num` from `youhui_v3`.`user` where `uid`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				String phoneNum = rs.getString("phone_num");
				if(!StringUtils.isEmpty(phoneNum)){
					flag = true;
				}
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 是否存在imei
	 * @param imei
	 * @param conn
	 * @return
	 */
	public boolean isExsitImei(String imei, Connection conn){
		boolean flag = false;
		try {
			String sql1 = "select uid from youhui_v3.user where `imei`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, imei);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 是否存在uid
	 * @param uid
	 * @param conn
	 * @return
	 */
	public boolean isExsitUid(String uid){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			flag = isExsitUid(uid, conn);
		} catch (Exception e){
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * taobaonick  上是否绑定了电话号码
	 */
	public boolean isTBNickBindPhone(String taobaoNick){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			flag = isTBNickBindPhone(taobaoNick, conn);
		} catch (Exception e){
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * taobaonick  上是否绑定了电话号码
	 * @param taobaoNick
	 * @param conn
	 * @return
	 */
	private boolean isTBNickBindPhone(String taobaoNick, Connection conn){
		boolean flag = false;
		try {
			String sql1 = "select phone_num from youhui_v3.user where `taobao_nick`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, taobaoNick);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				String phoneNum = rs.getString("phone_num");
				if(phoneNum != null && !"".equals(phoneNum) && !"null".equals(phoneNum)){
					flag = true;
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 移除电话号码，准备和taobaonick 绑定
	 * @param uid
	 * @param phoneNum
	 * @param conn
	 * @return
	 */
	private boolean removePhone(String uid, String phoneNum, Connection conn){
		boolean flag = false;
		try {
			String sql1 = "update `youhui_v3`.`user` set `phone_num` = concat('X', `phone_num`) where `uid`=? and phone_num =?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			s1.setString(2, phoneNum);
			int i = s1.executeUpdate();
			if(i > 0){
				flag = true;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 获取密码
	 * @param uid
	 * @param conn
	 * @return
	 */
	private String getPassword(String uid, Connection conn){
		String password = null;
		try {
			String sql1 = "select password from youhui_v3.user where `uid`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				password = rs.getString("password");
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return password;
	}
	
	/**
	 * 获取uid
	 * @param uid
	 * @param conn
	 * @return
	 */
	private String getUid(String taobaoNick, Connection conn){
		String uid = null;
		try {
			String sql1 = "select uid from youhui_v3.user where `taobao_nick`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, taobaoNick);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				uid = rs.getString("uid");
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return uid;
	}
	
	/**
	 * 用于测试删除手机号码
	 * @param phoneNum
	 * @return
	 */
	public boolean delPhone(String phoneNum){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "update `youhui_v3`.`user` set `phone_num` = ?,`password`=? where phone_num =?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, null);
			s1.setString(2, null);
			s1.setString(3, phoneNum);
			int i = s1.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public UserBean saveUser(UserBean userBean){
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			long now = System.currentTimeMillis();
			String uid = "";
			String sql = "select `uid`,`active_time` from youhui_v3.user where `taobao_nick`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userBean.getTaobaoNick());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				uid = rs.getString("uid");
				userBean.setUid(uid);
				userBean.setPhoneNum(getPhone(uid, conn));
				updateUser(userBean,now, conn);
				
				long activeTime = rs.getLong("active_time");
				if(activeTime == monomark){          //为在活动中注册，第一次绑定的情况   （非apk打包情况）
					updateActiveTime(userBean, now, conn);
//					if(ShareRecordManager.getInstance().isInValidTime(uid, conn)){
//						ShareRecordManager.getInstance().activeUid(uid, conn);
//						String fromUid = ShareRecordManager.getInstance().getFromUid(uid, conn);
//						if(fromUid != null){
//							doShare(userBean, fromUid, conn);
//						}
//					}
				}
				
//				if(userBean.getChannel() != null && userBean.getChannel().indexOf("ssyh_D") == 0){     //已经存在的老用户
//					String fromUid = "" + ScaleUtils.f60t10(userBean.getChannel().replaceAll("ssyh_D", ""));
//					ShareRecordManager.getInstance().saveOld(uid, userBean.getTaobaoNick(), fromUid, userBean.getPlatform());
//				}
				
			}else{               //新用户注册
				boolean flag = false;
				int j = 0;
				while(!flag&&j<10){
					j++;
					uid = getRandomUid();
					flag = addUser(uid, userBean, conn);
				}
				if(flag){
				   userBean.setUid(uid);
				   userBean.setNewUser(true);
				   if(userBean.getChannel() != null){
				      saveChannel(uid, userBean.getChannel(), conn);
				   }
				   if(userBean.getPlatform() != null){
					  savePlatform(uid, userBean.getPlatform(), conn);
				   }
					
//					if(userBean.getChannel() != null && userBean.getChannel().indexOf("ssyh_D") == 0){             // 为安卓用户分享产生用户
//						String fromUid = "" + ScaleUtils.f60t10(userBean.getChannel().replaceAll("ssyh_D", ""));
//						if(ShareRecordManager.getInstance().save(uid, userBean.getTaobaoNick(), fromUid, userBean.getPlatform())){
//							doShare(userBean, fromUid, conn);
//						}
//					}
					
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return userBean;
	}
	
	private boolean addUser(String uid, UserBean userBean, Connection conn){
		boolean flag = false;
		try{
			long now = System.currentTimeMillis();
			PreparedStatement pstmt= conn.prepareStatement("insert into `youhui_v3`.`user`(uid,imei,imsi,taobao_nick,taobao_uid,creat_time,active_time,last_time,platform,zfb,username) values (?,?,?,?,?,?,?,?,?,?,?);");
			pstmt.setString(1, uid);
			pstmt.setString(2, userBean.getImei());
			pstmt.setString(3, userBean.getImsi());
			pstmt.setString(4, userBean.getTaobaoNick());
			pstmt.setString(5, userBean.getTaobaoUid());
			pstmt.setLong(6, now);
			pstmt.setLong(7, now);
			pstmt.setLong(8, now);
			pstmt.setString(9, userBean.getPlatform());
			pstmt.setString(10, "");
			pstmt.setString(11, "");
		 	int i = pstmt.executeUpdate();
		 	if(i > 0){
		 		flag = true;
		 	}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	private void updateUser(UserBean userBean, long now, Connection conn){
		try {
			if(userBean == null || userBean.getImei() == null || userBean.getImsi() == null || "".equals(userBean.getImei()) || "".equals(userBean.getImsi())){
				return;
			}
			String sq = "update youhui_v3.user set imei = ?,imsi = ?, last_time=?, platform=?,taobao_uid=? where uid=?;";
			PreparedStatement ps= conn.prepareStatement(sq);
			ps.setString(1, userBean.getImei());
			ps.setString(2, userBean.getImsi());
			ps.setLong(3, now);
			ps.setString(4, userBean.getPlatform());
			ps.setString(5, userBean.getTaobaoUid());
			ps.setString(6, userBean.getUid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateActiveTime(UserBean userBean, long now, Connection conn){
		try {
			String sq = "update `youhui_v3`.`user` set active_time=? where uid=?;";
			PreparedStatement ps= conn.prepareStatement(sq);
			ps.setLong(1, now);
			ps.setString(2, userBean.getUid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 处理分享软件事务
//	 * @param userBean
//	 * @param fromUid
//	 * @param conn
//	 */
//	private void doShare(UserBean userBean, String fromUid, Connection conn){
//		if(isImeiRepeat(userBean.getImei(), conn)){
//			ShareRecordManager.getInstance().repeatUid(userBean.getUid(), conn);
//		}else if(!isLevel(userBean.getTaobaoUid())){
//			ShareRecordManager.getInstance().levelLowUid(userBean.getUid(), conn);
//		}else{
//			TPool.getInstance().doit(new ShareAppActivityThread(userBean.getUid(), fromUid));
//		}
//	}
	
	private boolean saveChannel(String uid, String channel, Connection conn){
		boolean flag = false;
		try {	
			PreparedStatement pstmt= conn.prepareStatement("insert into `youhui_v3`.`user_channel`(uid,channel,`timestamp`) values (?,?,?);");
			pstmt.setString(1, uid);
			pstmt.setString(2, channel);
			pstmt.setLong(3, System.currentTimeMillis());
			int i = pstmt.executeUpdate();
			if(i>0) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static boolean savePlatform(String uid, String platform, Connection conn){
		boolean flag = false;
		try {	
			PreparedStatement pstmt= conn.prepareStatement("insert into `youhui_v3`.`user_platform`(uid,platform,`timestamp`) values (?,?,?);");
			pstmt.setString(1, uid);
			pstmt.setString(2, platform);
			pstmt.setLong(3, System.currentTimeMillis());
			int i = pstmt.executeUpdate();
			if(i>0) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 是否存在重复的imei
	 * @param imei
	 * @return
	 */
	private boolean isImeiRepeat(String imei, Connection conn) {
		boolean flag = false;
		try {
			if(imei == null || "".equals(imei)){
				return true;
			}
			String sql = "select count(id) as ct from `youhui_v3`.`user` where `imei` = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, imei);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int num = rs.getInt("ct");
				if(num > 1){
					flag = true;
				}
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 等级是否高于一级
	 * @param taobaoUid
	 * @return
	 */
	public static boolean isLevel(String taobaoUid){
		int level = TaobaoManager.getUserLevel(taobaoUid);
		if(level >= 1){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否为新用户       （ 激活一天以内）
	 * @param uid 
	 * @param conn
	 * @return
	 */
	public boolean isNewUser(String uid, Connection conn){
		boolean isNew = false;
		try {
			String sql1 = "select active_time from youhui_v3.user where `uid`=?;";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs = s1.executeQuery();
			if(rs.next()){
				long activeTime = rs.getLong("active_time");
				long now = System.currentTimeMillis();
				if(now - activeTime < 24*60*60*1000){
					isNew = true;
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return isNew;
	}
	
	
	/**
	 * 检测用户是否为2013-09-10 这天 中华万年历渠道的新用户
	 * 若为iphone用户，直接返回true
	 * @return
	 */
	public boolean isNewZHWNLUser(String uid){
		boolean flag = false;
		long t0910 = 1378742400000l;
		long t0911 = 1378828800000l;
		if(System.currentTimeMillis() > t0911 || System.currentTimeMillis() < t0910){    //只在09-10这一天有效
			return flag;
		}
		String channel = "zhwnl";
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from `youhui_v3`.`user` where `uid`=? and `creat_time`>? and `creat_time`<?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setLong(2, t0910);   
			s.setLong(3, t0911);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				String platform = rs.getString("platform");
				if("iphone".equalsIgnoreCase(platform)){
					flag = true;
				}else if("android".equalsIgnoreCase(platform)){
					sql = "select * from `youhui_v3`.`user_channel` where `uid`=? and `channel`=?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, uid);
					ps.setString(2, channel);
					ResultSet r = ps.executeQuery();
					if(r.next()){
						flag = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 检测用户是否为2013-10-28 至 2013-11-15 这段时间通过分享产生的新用户
	 * @return
	 */
	public boolean isShareNewUser(String uid){
		boolean flag = false;
		long t1028 = 1382889600000l;
		long t1115 = 1384444800000l;
		if(System.currentTimeMillis() > t1115 || System.currentTimeMillis() < t1028){    //只在10.28 - 11.15 这段时间
			return flag;
		}
	
		if(!SignInManager.getInstance().isFirstSign(uid)){
			return flag;
		}
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from `youhui_v3`.`user_channel` where `uid`=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet r = ps.executeQuery();
			if(r.next()){
				String channel = r.getString("channel");
				if(channel.indexOf("ssyh_") == 0){
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	
	
	
	public boolean isInTop500(String uid){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select uid from youhui_cn_fanli.top500 where `uid`=? and `status`=0;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public boolean isInTop1000(String uid){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select uid from youhui_statistics.top1000 where `uid`=? and `status`=0;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			return false;
		}finally{		
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}

}
