package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.taobao.api.domain.Check;


import cn.youhui.bean.ActivityMingxi;
import cn.youhui.bean.UserAccount;
import cn.youhui.bean.UserJFBAccount;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.StringUtils;

public class ActivityMingxiManager {
	
	private static ActivityMingxiManager instance = null;
	public static final int Status_Disposeing = 10;    
	public static final int Status_Success = 1;
	public static final int Status_Fail = 2;
	public static final int Status_Get = 5;  //获得集分宝的情况，其他都为提现集分宝时的状态
	
	private ActivityMingxiManager(){}
	
	public static ActivityMingxiManager getInstance(){
		if(instance == null) instance = new ActivityMingxiManager();
		return instance;
	}
	
	public boolean add(ActivityMingxi bean){
		boolean flag = false;
		Connection conn = null;
		try {
			conn =  MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_cn_fanli.tyh_activity_mingxi(`uid`,`jfb_num`,`event`,`activity_id`,`trade_id`,`timestamp`,`status`) values(?,?,?,?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, bean.getUid());
			s.setInt(2, bean.getJfbNum());
			s.setString(3, bean.getEvent());
			s.setString(4, bean.getActivityId());
			s.setString(5, bean.getTradeId());
			s.setLong(6, System.currentTimeMillis());
			s.setInt(7, bean.getStatus());
			int i = s.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	
	public int getYue(String uid){
		int jfbNum = 0;
		if(StringUtils.isEmpty(uid))
			return jfbNum;
		Connection conn = MySQLDBIns.getInstance().getReadConnection();
		try {
			String sql = "select sum(jfb_num) as sumjfb from youhui_cn_fanli.tyh_activity_mingxi where uid = ? and `status` <> ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, Status_Fail);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return jfbNum;
	}
	
	/**
	 * 获得的总的集分宝数
	 * @param uid
	 * @return
	 */
	public int getAllGet(String uid){
		int jfbNum = 0;
		if(StringUtils.isEmpty(uid))
			return jfbNum;
		Connection conn = MySQLDBIns.getInstance().getReadConnection();
		try {
			String sql = "select sum(jfb_num) as sumjfb from youhui_cn_fanli.tyh_activity_mingxi where uid = ? and `status`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, Status_Get);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return jfbNum;
	}
	
	public List<String> getDisposeingList(Connection conn){
		List<String> list = new ArrayList<String>();
		try {
			String sql = "select * from youhui_cn_fanli.tyh_activity_mingxi where `status`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setInt(1, Status_Disposeing);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				list.add(rs.getString("trade_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateStatus(Map<String, Integer> map, Connection conn){
		try {
			String sql = "update youhui_cn_fanli.tyh_activity_mingxi set `status`=? where `trade_id` = ? and `status`=?;";
			if(map != null){
				PreparedStatement s = conn.prepareStatement(sql);
				conn.setAutoCommit(false);
				for(Map.Entry<String, Integer> m : map.entrySet()){		
					if(m.getValue() == JiFenBaoTradeManager.Status_Success || m.getValue() == JiFenBaoTradeManager.Status_Fail){
					s.setInt(1, m.getValue());
					s.setString(2, m.getKey());
					s.setInt(3, Status_Disposeing);
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
	 * 获得的总的集分宝数
	 * @param uid
	 * @return
	 */
	public int[] getAllold(String uid){
		int jfbNum[] = {0,0,0,0};
		if(StringUtils.isEmpty(uid))
			return jfbNum;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			String sql = "select sum(jfb_num) as sumjfb,`status` from youhui_cn_fanli.tyh_activity_mingxi where uid = ? group by `status`;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				if(Status_Success == rs.getInt("status")){
					jfbNum[0] = -rs.getInt("sumjfb");                //提现成功的集分宝
				}else if(Status_Get == rs.getInt("status")){
					jfbNum[1] = rs.getInt("sumjfb");                 //获得的所有集分宝
				}else if(Status_Disposeing == rs.getInt("status")){
					jfbNum[2] = -rs.getInt("sumjfb");                //正在处理的集分宝
				}
			}
			jfbNum[3] = -jfbNum[0]+jfbNum[1]-jfbNum[2];               //集分宝余额
			jfbNum[0] = jfbNum[0]+jfbNum[2];                          //已提现集分宝
		} catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return jfbNum;
	}
	
	/**
	 * 获得uid的集分宝信息          不通过mingxi表 查询
	 * @param uid
	 * @return 0, 提现成功的   1,获得的   2,处理中的  3,余额
	 */
	public int[] getAlloo(String uid){
		Connection conn = null;
		int jfbNum[] = {0,0,0,0};
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			int jfb[] = JiFenBaoTradeManager.getInstance().getTXJfbNum(conn, uid);
			jfbNum[0] = jfb[0];                                       //提现成功集分宝
			jfbNum[2] = jfb[1];                                       //正在处理中集分宝
			jfbNum[1] = ActivityJoinManager.getInstance().getGainJfbNum(uid, conn);   //获得的集分宝
			
			jfbNum[3] = -jfbNum[0]+jfbNum[1]-jfbNum[2];               //集分宝余额
		} catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return jfbNum;
	}
	
	/**
	 * 获得uid的集分宝信息       
	 * @param uid
	 * @return 0, 提现成功的   1,获得的   2,处理中的  3,余额
	 */
	public int[] getAll(String uid){
		Connection conn = null;
		int jfbNum[] = {0,0,0,0};
		if(uid == null || "".equals(uid))
			return jfbNum;
		try {
			conn = MySQLDBIns.getInstance().getReadConnection();
			jfbNum[0] = -JiFenBaoMXManager.getInstance().getTXJfbNum(uid, conn);      //提现成功的
			jfbNum[1] = JiFenBaoMXManager.getInstance().getGainJfbNum(uid, conn);   //获得的集分宝
			jfbNum[2] = -JiFenBaoMXManager.getInstance().getChkJfbNum(uid, conn);   //正在处理中集分宝
			
			jfbNum[3] = -jfbNum[0]+jfbNum[1]-jfbNum[2];               //集分宝余额
		} catch(Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return jfbNum;
	}
	
	/**
	 * 获得uid的集分宝信息       
	 * @param uid
	 */
	public UserJFBAccount getUserJfbAccount(String uid, Connection conn){
		UserJFBAccount uja = new UserJFBAccount();
		if(uid == null || "".equals(uid))
			return null;
		try {
			int txnum = -JiFenBaoMXManager.getInstance().getTXJfbNum(uid, conn);      //提现成功的
			int gainnum = JiFenBaoMXManager.getInstance().getGainJfbNum(uid, conn);   //获得的集分宝
			int chktxnum = -JiFenBaoMXManager.getInstance().getChkJfbNum(uid, conn);   //正在处理中集分宝
			int flnum = JiFenBaoMXManager.getInstance().getFanliJfbNum(uid, conn);
			uja.setChkTxNum(chktxnum);
			uja.setGainNum(gainnum);
			uja.setTxNum(txnum);
			uja.setFlNum(flnum);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return uja;
	}
	
	public UserAccount getUserAccount(String uid, Connection conn){
		UserAccount bean = new UserAccount();
		if(StringUtils.isEmpty(uid)){
			return null;
		}
		try{
			int txnum = -JiFenBaoMXManager.getInstance().getTXJfbNum(uid, conn);      //提现成功的  （包括花费的）
			int chktxnum = -JiFenBaoMXManager.getInstance().getChkJfbNum(uid, conn);  //正在处理中集分宝
			int paynum = -JiFenBaoMXManager.getInstance().getPayJfbNum(uid, conn);    //花费的集分宝
			int gainnum = JiFenBaoMXManager.getInstance().getGainJfbNum(uid, conn);   //获得的集分宝
			int flnum = JiFenBaoMXManager.getInstance().getFanliJfbNum(uid, conn);
			int chkFLnum = JiFenBaoMXManager.getInstance().getCheckFanliJfbNum(uid, conn);
			bean.setFlAddNum(flnum - chkFLnum);
			bean.setFlCheckNum(chkFLnum);
			bean.setAcAddNum(gainnum - (flnum - chkFLnum));
			bean.setPayNum(paynum);
			bean.setTxNum(txnum - paynum + chktxnum);
			bean.setTxCheckNum(chktxnum);
			bean.setYuE(gainnum - bean.getTxNum() - paynum);
			bean.setUid(uid);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public static void main(String[] args) {
		Connection conn = MySQLDBIns.getInstance().getConnection();
//		System.out.println(ActivityMingxiManager.getInstance().getUserJfbAccount("20842824", conn).getGainNum());
		System.out.println(ActivityMingxiManager.getInstance().getUserAccount("108612506", conn));
		MySQLDBIns.getInstance().release(conn);
	}
}
