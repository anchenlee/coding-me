package cn.suishou.some.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.suishou.some.db.SQL;

/**
 * 分享软件送集分宝活动
 * @author lijun
 * @since 2013-10-25
 */
public class ShareAppDAO {
	
	private static ShareAppDAO instance = null;
	
	//这个活动的ID
	private static final String beShareActivityId = "11";
	
	//这个活动的ID
	private static final String shareActivityId = "12";
	
	private ShareAppDAO(){}

	public static ShareAppDAO getInstance(){
		if(instance == null){
			instance = new ShareAppDAO();
		}
		return instance;
	}
	
	/**
	 * 检测用户是否参加了此活动
	 */
	public boolean isJoin(String uid){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = SQL.getInstance().getConnection();
			String sql = "select * from `youhui_cn_fanli`.`tyh_activity_join` where `uid`=? and `activity_id`=?;";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, uid);
			state.setString(2, beShareActivityId);
			ResultSet rs = state.executeQuery();
			if(rs.next()){
				flag = true;
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return flag;
	}
	
	/**
	 * 计算用户获得的集分宝数
	 */
	public int getJFBNum(String uid){
		int jfb = 0;
		Connection conn = null;
		try{
			conn = SQL.getInstance().getConnection();
			String sql = "select sum(jfb_num) as jfb_sum from `youhui_cn_fanli`.`tyh_activity_join` where `uid`=? and `activity_id`=?;";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, uid);
			state.setString(2, shareActivityId);
			ResultSet rs = state.executeQuery();
			if(rs.next()){
				jfb = rs.getInt("jfb_sum");
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return jfb;
	}
	
	/**
	 * 获取参加活动的前n名
	 */
	public Map<String, Integer> getFirst(int n){
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		Connection conn = null;
		try{
			conn = SQL.getInstance().getConnection();
			String sql = "select sum(jfb_num) as jfb_sum, uid from `youhui_cn_fanli`.`tyh_activity_join` where `activity_id`=? group by `uid` order by `jfb_sum` desc limit 0,?";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, shareActivityId);
			state.setInt(2, n);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				map.put(getNick(rs.getString("uid"), conn), rs.getInt("jfb_sum"));
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return map;
	}
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	private String getNick(String uid, Connection conn){
		String nick = "";
		try{
			String sql = "select taobao_nick from `youhui_v3`.`user` where `uid`=?";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, uid);
			ResultSet rs = state.executeQuery();
			if(rs.next()){
				nick = rs.getString("taobao_nick");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(nick == null || nick == ""){
			nick = "****";
		}else{
			nick = nick.substring(0, 1) + "**" + nick.substring(nick.length() -1, nick.length());
		}
		
		return nick;
	}
	
	public static void main(String[] args) {
		System.out.println(ShareAppDAO.getInstance().getJFBNum("40505605"));
//		System.out.println(ShareAppDAO.getInstance().getFirst(1));
	}
}
