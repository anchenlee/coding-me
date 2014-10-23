package cn.youhui.manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


import cn.youhui.bean.UserAccount;
import cn.youhui.dao.MySQLDBIns;

/**
 * 用户帐户信息操作
 * @author lijun
 * @since 2014-7-3
 */
public class UserAccountManager {
	
	private static UserAccountManager instance = null;
	
	private UserAccountManager(){}
	
	public static UserAccountManager getInstance(){
		if(instance == null){
			instance = new UserAccountManager();
		}
		return instance;
	}

	public boolean addUserAccount(UserAccount ua, Connection conn){
		boolean flag = false;
		PreparedStatement s = null;
		try {
			String sql = "insert into `youhui_fanli`.`tyh_user_account` (`uid`,`ac_add_jfb`, `fl_add_jfb`, `fl_check_jfb`, `fl_xj`," +
					" `tx_jfb`, `tx_check_jfb`, `pay_jfb`, `yue_jfb`,`update_time`) values (?,?,?,?,?,?,?,?,?,?)  ON DUPLICATE KEY UPDATE " +
					"`ac_add_jfb`=?, `fl_add_jfb`=?, `fl_check_jfb`=?, `fl_xj`=?, `tx_jfb`=?, `tx_check_jfb`=?, `pay_jfb`=?, `yue_jfb`=?,`update_time`=?;";
			s = conn.prepareStatement(sql);
			s.setString(1, ua.getUid());
			s.setInt(2, ua.getAcAddNum());
			s.setInt(3, ua.getFlAddNum());
			s.setInt(4, ua.getFlCheckNum());
			s.setDouble(5, ua.getFlXJ());
			s.setInt(6, ua.getTxNum());
			s.setInt(7, ua.getTxCheckNum());
			s.setInt(8, ua.getPayNum());
			s.setInt(9, ua.getYuE());
			s.setDate(10, new Date(System.currentTimeMillis()));
			s.setInt(11, ua.getAcAddNum());
			s.setInt(12, ua.getFlAddNum());
			s.setInt(13, ua.getFlCheckNum());
			s.setDouble(14, ua.getFlXJ());
			s.setInt(15, ua.getTxNum());
			s.setInt(16, ua.getTxCheckNum());
			s.setInt(17, ua.getPayNum());
			s.setInt(18, ua.getYuE());
			s.setDate(19, new Date(System.currentTimeMillis()));
			int i = s.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch (Exception e) { 
			e.printStackTrace();
		}
		return flag;
	}
	
	public UserAccount getUserAccount(String uid){
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			return getUserAccount(uid, conn);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return null;
	}
	
	private UserAccount getUserAccount(String uid, Connection conn){
		UserAccount ua = new UserAccount();
		PreparedStatement s = null;
		try{
			String sql = "select * from `youhui_fanli`.`tyh_user_account` where uid = ?;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				ua.setAcAddNum(rs.getInt("ac_add_jfb"));
				ua.setFlAddNum(rs.getInt("fl_add_jfb"));
				ua.setFlCheckNum(rs.getInt("fl_check_jfb"));
				ua.setFlXJ(rs.getDouble("fl_xj"));
				ua.setPayNum(rs.getInt("pay_jfb"));
				ua.setTxCheckNum(rs.getInt("tx_check_jfb"));
				ua.setTxNum(rs.getInt("tx_jfb"));
				ua.setUid(rs.getString("uid"));
				ua.setYuE(rs.getInt("yue_jfb"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ua;
	}
	
	public void updateUserAccount(){
		new Thread(){
			public void run() {
				updateUserAccount(1);
			};
		}.start();
		new Thread(){
			public void run() {
				updateUserAccount(2);
			};
		}.start();
		new Thread(){
			public void run() {
				updateUserAccount(3);
			};
		}.start();
		new Thread(){
			public void run() {
				updateUserAccount(4);
			};
		}.start();
		new Thread(){
			public void run() {
				updateUserAccount(5);
			};
		}.start();
		new Thread(){
			public void run() {
				updateUserAccount(6);
			};
		}.start();
		new Thread(){
			public void run() {
				updateUserAccount(7);
			};
		}.start();
		new Thread(){
			public void run() {
				updateUserAccount(8);
			};
		}.start();
		new Thread(){
			public void run() {
				updateUserAccount(9);
			};
		}.start();
	}
	
	public void updateUserAccount(int uidpre){
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			List<String> uids = JiFenBaoMXManager.getInstance().getUids(uidpre + "", conn);
			System.out.println(uidpre + " :  " + uids.size());
			for(String uid : uids){
				UserAccount ua = ActivityMingxiManager.getInstance().getUserAccount(uid, conn);
				ua.setUid(uid);
				addUserAccount(ua, conn);
				System.out.println(uid);
			}
			System.out.println(uidpre + "..........................ok");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
	}
	
	public void check(){
		new Thread(){
			public void run() {
				check(1);
			};
		}.start();
		new Thread(){
			public void run() {
				check(2);
			};
		}.start();
		new Thread(){
			public void run() {
				check(3);
			};
		}.start();
		new Thread(){
			public void run() {
				check(4);
			};
		}.start();
		new Thread(){
			public void run() {
				check(5);
			};
		}.start();
		new Thread(){
			public void run() {
				check(6);
			};
		}.start();
		new Thread(){
			public void run() {
				check(7);
			};
		}.start();
		new Thread(){
			public void run() {
				check(8);
			};
		}.start();
		new Thread(){
			public void run() {
				check(9);
			};
		}.start();
	}
	
	public void check(int uidpre){
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			List<String> uids = JiFenBaoMXManager.getInstance().getUids(uidpre + "", conn);
			System.out.println(uidpre + "............." + uids.size());
			for(String uid : uids){
				UserAccount ua = ActivityMingxiManager.getInstance().getUserAccount(uid, conn);
				UserAccount ua1 = getUserAccount(uid, conn);
				if(!ua.equals(ua1)){
					System.out.println(uid + ",,,error..................");
				}
			}
			System.out.println(uidpre + " : .............................done");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
	}
	
	public static void main(String[] args) {
		UserAccountManager.getInstance().updateUserAccount();
//		System.out.println(UserAccountManager.getInstance().getUserAccount("00111"));
//		UserAccount ua = new UserAccount();
//		UserAccount ua1 = new UserAccount();
//		System.out.println(ua.equals(ua1));
	}
}
