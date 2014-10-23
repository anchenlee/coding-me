package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.FanliBean;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.TimeUtil;

public class TradeManager {
	

	private static TradeManager instance = null;
	private static int pageSize = 10;
	
	private TradeManager(){
	}
	
	public static TradeManager getInstance(){
		if(instance == null) instance = new TradeManager();
		return instance;
	}
	
	public boolean hideTrade(String uid, String tradeId){
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update youhui_cn_fanli.duoduo_tradelist set `ishide`=? where ( outer_code = ? or outer_code = ? ) and trade_id = ?";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setInt(1, 1);
			s.setString(2, uid);
			s.setString(3, "B"+uid);
			s.setString(4, tradeId);
			int i = s.executeUpdate();
			if(i > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public List<FanliBean> getMonthTradeHistory(String uid){
		List<FanliBean> list = new ArrayList<FanliBean>();
		if(uid == null || "".equals(uid))
			return list;
		Connection conn = null;
		ResultSet rs = null;
		String uidp = uid.substring(0, 1);
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT * FROM youhui_fanli.tyh_trade_mx_"+ uidp +" where uid =? and insert_time>? and (`status`='2' or `status`='5') and  insert_time<? and type='1';";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
//			s.setLong(2, 0);
			s.setLong(2, TimeUtil.getTimeMillis(getNowMonth()+" 00:00:00"));
			s.setLong(3, System.currentTimeMillis());
			
			rs = s.executeQuery();
			while(rs.next()){
				FanliBean bean = new FanliBean();
				bean.setNum(rs.getDouble("jfb_num")+"");
				bean.setOrderid(rs.getString("trade_id"));
				bean.setState(rs.getString("status"));
				bean.setTime(rs.getString("fl_pay_time"));
				bean.setPrice(getOrderImg(rs.getString("trade_id"), conn));//图片信息
				if(rs.getString("fl_item_title") != null && rs.getString("fl_item_title").length() >12) {				
					bean.setName(rs.getString("fl_item_title").substring(0,12)+"...");
				}else{					
					bean.setName(rs.getString("fl_item_title"));
				}
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		
		return list;
	}
	
	public List<FanliBean> getMonthTradeHistoryByMonth(String uid, String year,String month){
		List<FanliBean> list = new ArrayList<FanliBean>();
		if(uid == null || "".equals(uid))
			return list;
		int yearInt = 0;
		int monthInt = 0;
		try {
			yearInt = Integer.parseInt(year);
			monthInt = Integer.parseInt(month);
		} catch (Exception e) {
			return list;
		}
		String uidp = uid.substring(0, 1);
		String data = year+"-"+month+"-01 00:00:00";
		String moweiData = "";
		if(monthInt == 12){
			moweiData = (yearInt+1)+"-"+"01-01 00:00:00";
		}else{
			moweiData = yearInt+"-"+(monthInt+1)+"-01 00:00:00";
		}
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT * FROM youhui_fanli.tyh_trade_mx_"+ uidp +" where uid =? and insert_time>? and insert_time<? and type='1' and (`status`='2' or `status`='5');";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
//			s.setLong(2, 0);
			s.setLong(2, TimeUtil.getTimeMillis(data));
			s.setLong(3, TimeUtil.getTimeMillis(moweiData));
//			System.out.println(s);
			rs = s.executeQuery();
			while(rs.next()){
				FanliBean bean = new FanliBean();
				bean.setNum(rs.getDouble("jfb_num")+"");
				bean.setOrderid(rs.getString("trade_id"));
				bean.setState(rs.getString("status"));
				bean.setTime(rs.getString("fl_pay_time"));
				bean.setPrice(getOrderImg(rs.getString("trade_id"), conn));//图片信息
				if(rs.getString("fl_item_title") != null && rs.getString("fl_item_title").length() >12) {				
					bean.setName(rs.getString("fl_item_title").substring(0,12)+"...");
				}else{					
					bean.setName(rs.getString("fl_item_title"));
				}
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		
		return list;
	}
	
	public int getMonthTradeHistoryPage(String uid){
		int count = 0;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT count(*) as c FROM youhui_fanli.tyh_report_trade where uid =? and insert_time>? and insert_time<?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setLong(2, 0);
//			s.setLong(2, TimeUtil.getTimeMillis(getNowMonth()+" 00:00:00"));
			s.setLong(3, System.currentTimeMillis());
			
			rs = s.executeQuery();
			if(rs.next()){
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		if(count % pageSize == 0){
			count = count / pageSize;
		}else{
			count = count / pageSize + 1;
		}
		return count;
	}
	
	
	public static String getOrderImg(String orderId,Connection conn){
		String img = "";
		ResultSet rs = null;
		try {

			String sql = "SELECT * FROM youhui_fanli.trade_img where trade_id=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, orderId);
			
			rs = s.executeQuery();
			if(rs.next()){
				img = rs.getString("img");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static String getNowMonth(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");//可以方便地修改日期格式
		String hehe = dateFormat.format( now ); 
		return hehe+"-01";
	}
	
	public static void main(String[] args) {
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");//可以方便地修改日期格式
		String hehe = dateFormat.format( now ); 
		System.out.println(hehe); 
	}
}
