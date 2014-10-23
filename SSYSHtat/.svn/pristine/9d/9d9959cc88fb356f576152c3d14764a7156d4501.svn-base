package cn.youhui.stat.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import cn.youhui.stat.util.ProjectInfo;
import cn.youhui.stat.util.SQL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StatMySqlModel {
	private static final String numToOrder[] = {"create","pay","close","invalid","success","create_i","pay_i","close_i","invalid_i","success_i"};
	
	private static StatMySqlModel instance = null;
	public StatMySqlModel() {}
	public static StatMySqlModel getInstance(){
		if(instance == null) instance = new StatMySqlModel();
		return instance;
	}
	
	public String getTodayPurchase(String pid){
		long today = getTodayTimestamp();
		Connection conn = null;
		PreparedStatement s = null;
		int hours[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "select hour(pay_time) h, count(1) c from youhui_fanli.tyh_report_trade where num_iid = ? and pay_timestamp > ? group by h;";
			s = conn.prepareStatement(sql);
			s.setString(1, pid);
			s.setLong(2, today);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int hour = rs.getInt("h");
				int count = rs.getInt("c");
				hours[hour] = count;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(s, conn);
		}
		return "{'p_count':"+organizeJson(hours)+"}";
	}
	
	public String getTodayStore(String pid){
		long today = getTodayTimestamp();
		Connection conn = null;
		PreparedStatement s = null;
		int hours[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "";
			if (ProjectInfo.DebugMode) {
				sql = "select hour(from_unixtime(timestamp/1000)) h, count(1) c from youhui_datamining.tyh_fav_uid2itemid where item_id=? and timestamp>? group by h;";
			}else {
				sql = "select hour(from_unixtime(timestamp/1000)) h, count(1) c from youhui_visit.tyh_fav_uid2itemid where item_id=? and timestamp>? group by h;";
			}
			s = conn.prepareStatement(sql);
			s.setString(1, pid);
			s.setLong(2, today);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int hour = rs.getInt("h");
				int count = rs.getInt("c");
				hours[hour] = count;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(s, conn);
		}
		return "{'s_count':"+organizeJson(hours)+"}";
	}
	
	public String getTodayBrowse(String pid) {
		long today = getTodayTimestamp();
		Connection conn = null;
		PreparedStatement s = null;
		int hours[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "";
			sql = "select hour(from_unixtime(timestamp/1000)) h, count(1) c from youhui_view.item_view where item_id=? and timestamp>? group by h;";
			s = conn.prepareStatement(sql);
			s.setString(1, pid);
			s.setLong(2, today);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				int hour = rs.getInt("h");
				int count = rs.getInt("c");
				hours[hour] = count;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(s, conn);
		}
		return "{'b_count':"+organizeJson(hours)+"}";
	}
	
	public String getTodayOrder(String pid){
		long today = getTodayTimestamp();
		Connection conn = null;
		PreparedStatement s = null;
		int orderHours[][] = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//create
							  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//pay
							  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//close
							  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//invalid
							  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//success
							  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//create_i
							  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//pay_i
							  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//close_i
							  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//invalid_i
							  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};//success_i
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "select orderstatus o, hour(createtime) h, count(1) c, sum(ordernum) s "
					+ "from tradefind.tradeorder where itemid =? and updatetime > ?"
					+ " group by o, h";
			s = conn.prepareStatement(sql);
			s.setString(1, pid);
			s.setLong(2, today);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				//System.out.println("1");
				String status = rs.getString("o");
				//System.out.println("+++++++++++++++++++++++++++++>"+status);
				int hour = rs.getInt("h");
				int count = rs.getInt("c");
				int sum = rs.getInt("s");
				if (status.equals("订单创建")) {
					orderHours[0][hour] = count;
					orderHours[5][hour] = sum;
				}else if (status.equals("订单付款")) {
					orderHours[1][hour] = count;
					orderHours[6][hour] = sum;
				}else if (status.equals("订单结算")) {
					orderHours[2][hour] = count;
					orderHours[7][hour] = sum;
				}else if (status.equals("订单失效")) {
					orderHours[3][hour] = count;
					orderHours[8][hour] = sum;
				}else if (status.equals("订单成功")) {
					orderHours[4][hour] = count;
					orderHours[9][hour] = sum;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(s, conn);
		}
		String re = "{";
		for (int i = 0; i < orderHours.length; i++) {
			re+= "'"+numToOrder[i]+"':"+organizeJson(orderHours[i]);
			if (i != orderHours.length-1) {
				re+=',';
			}
		}
		re+= "}";
		return re;
	}
	
	private String organizeJson(int[] hours){
		String re = "[";
		for (int i = 0; i < hours.length; i++) {
			re+= hours[i]+"";
			if (i != hours.length-1) {
				re+=",";
			}
		}
		re+="]";
		return re;
	}
	
	private long getTodayTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}
	public String FetchRealTimePidStat(String pid, String tag) {
		JsonParser jp = new JsonParser();
		JsonObject jo = new JsonObject();
		if (tag.equals("order")) {
			jo = jp.parse(getTodayOrder(pid)).getAsJsonObject();
		}else if (tag.equals("purchase")) {
			jo = jp.parse(getTodayPurchase(pid)).getAsJsonObject();
		}else if (tag.equals("store")) {
			jo = jp.parse(getTodayStore(pid)).getAsJsonObject();
		}else if (tag.equals("browse")) {
			jo = jp.parse(getTodayBrowse(pid)).getAsJsonObject();
		}
		return jo.toString();
	}
	
	public static void main(String[] args) {
		String pid = "19905624649";
		String re = StatMySqlModel.getInstance().FetchRealTimePidStat(pid, "order");	
		System.out.println(re);
	}
	
}
