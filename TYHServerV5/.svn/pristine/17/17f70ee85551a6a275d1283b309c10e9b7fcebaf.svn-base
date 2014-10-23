package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.youhui.dao.MySQLDBIns;

public class TradeDetailManager {

	private static TradeDetailManager instance = null;
	private TradeDetailManager(){
	}
	
	public static TradeDetailManager getInstance(){
		if(instance == null) instance = new TradeDetailManager();
		return instance;
	}
	
	public boolean add(String uid, String itemId, float price, String tradeId, int event){
		boolean flag = false;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "insert into youhui_exchange.exch_trade_detail(uid, item_id, price, trade_id, event, time) values(?,?,?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, itemId);
			s.setFloat(3, price);
			s.setString(4, tradeId);
			s.setInt(5, event);
			s.setLong(6, System.currentTimeMillis());
			int i = s.executeUpdate();
			if(i > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public boolean addtomingxi(String uid, String itemId, int count, float price, String tradeId){
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "insert into youhui_cn_fanli.duoduo_mingxi(ddusername,je,shijian,addtime,memo,istixian,pid,trade_id) values(?,?,?,?,?,?,?,?)";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setFloat(2, -count*price);
			s.setString(3, "商品兑换");
			s.setString(4, sdf.format(new Date()));
			s.setString(5, count+"件");
			s.setInt(6, 2);
			s.setString(7, itemId);
			s.setString(8, tradeId);
			int i = s.executeUpdate();
			if(i > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public static void main(String[] a){
		TradeDetailManager.getInstance().add("123", "1000", 10.0f, "1002", 2);
	}
}
