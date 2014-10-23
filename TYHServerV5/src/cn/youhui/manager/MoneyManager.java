package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.youhui.dao.MySQLDBIns;

public class MoneyManager {
	private DecimalFormat df = new DecimalFormat("0.00");
	private static MoneyManager instance = null;
	private static final long limitTime = 3*24*60*60*1000;
    
	private MoneyManager(){
	}
	
	public static MoneyManager getInstance(){
		if(instance == null) instance = new MoneyManager();
		return instance;
	}
	
	public float getInReview(String uid){
		float fanli = 0;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select * from `youhui_cn_fanli`.`duoduo_tradelist` where outer_code =? or outer_code=?";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, "B"+uid);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				long paytime = sdf.parse(rs.getString("pay_time")).getTime();
				if(new Date().getTime() - paytime < limitTime)
					fanli += Float.parseFloat(df.format(rs.getFloat("fxje")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return fanli;
	}
	
	public float getFanli(String uid){
		float fanli = 0;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select sum(fxje) as fanli from `youhui_cn_fanli`.`duoduo_tradelist` where outer_code =? and `checked`=2";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				fanli = Float.parseFloat(df.format(rs.getFloat("fanli")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return fanli;
	}
	
	public float getFanli(String uid, Connection conn){
		float fanli = 0;
		try {
			String sql = "select sum(fxje) as fanli from `youhui_cn_fanli`.`duoduo_tradelist` where outer_code =? and `checked`=2";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				fanli = Float.parseFloat(df.format(rs.getFloat("fanli")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fanli;
	}
	
	public int getTradeFanli(String uid, Connection conn){
		int fanli = 0;
		try {
			String sql = "SELECT sum(commission_jfb) as c  FROM youhui_fanli.tyh_report_trade where uid=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				fanli = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fanli;
	}
	
	
	public float getWaihui(String uid){
		float waihui = 0;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select sum(fxje) as waihui from `youhui_cn_fanli`.`duoduo_tradelist` where outer_code =?";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, "B"+uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				waihui = Float.parseFloat(df.format(rs.getFloat("waihui")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return waihui;
	}
	
	public float getEarn(String uid){
		float fanli = 0;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select sum(fxje) as fanli from `youhui_cn_fanli`.`duoduo_tradelist` where outer_code =? or outer_code =?";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, "B"+uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				fanli = Float.parseFloat(df.format(rs.getFloat("fanli")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return fanli;
	}
	
	public float getTixian(String uid){
		float expend = 0;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sq = "select sum(txje) as txje from `youhui_cn_fanli`.`duoduo_tixian` where ddusername =? and txstate <>2";
			PreparedStatement s = conn.prepareStatement(sq);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				expend = Float.parseFloat(df.format(rs.getFloat("txje")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return expend;
	}
	
	public float getExch(String uid){
		float expend = 0;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select sum(prom_price*count) as expend from `youhui_exchange`.`exch_order` where uid =? and status <>3";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				expend += Float.parseFloat(df.format(rs.getFloat("expend")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return expend;
	}
	
	public float getExpend(String uid){
		float expend = 0;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sq = "select sum(txje) as txje from `youhui_cn_fanli`.`duoduo_tixian` where ddusername =? and txstate <>2";
			PreparedStatement s = conn.prepareStatement(sq);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				expend = Float.parseFloat(df.format(rs.getFloat("txje")));
			} 
			String sql = "select sum(prom_price*count) as expend from `youhui_exchange`.`exch_order` where uid =? and status <>3";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			rs = s.executeQuery();
			if(rs.next()) {
				expend += Float.parseFloat(df.format(rs.getFloat("expend")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return expend;
	}
	
	public float getYue(String uid){
		return Float.parseFloat(df.format(getEarn(uid) - getExpend(uid) - getInReview(uid)));
	}
	
}
