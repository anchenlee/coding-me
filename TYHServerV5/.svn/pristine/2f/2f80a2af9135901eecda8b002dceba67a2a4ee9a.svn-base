package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cn.youhui.bean.SecondKill;
import cn.youhui.common.Config;
import cn.youhui.utils.DateUtil;
import cn.youhui.utils.OuterCode;

public class SecondKillDAO {

	private static final int STATUS_VALID = 0;
	private static final int STATUS_OCCUPIED = 1;
	private static final int STATUS_BUY = 1;
	private static final int STATUS_USED = 1;
	private static final int STATUS_UNUSED = 0;
	public static final int PAYSUCCESS = 1;
	public static final int PAYFAIL = 0;
	private static final String CACHELOCK = "LOCK.SECONDKILL";
	private static SecondKillDAO instance=null;
	public static SecondKillDAO getInstance(){
		if(instance == null){
			instance=new SecondKillDAO();
		}
		return instance;
	}
	private SecondKillDAO(){}
	
	
	/**
	 * 获得串码
	 * @param orderid
	 * @return
	 */
	public JsonObject getSecondKillOrderByOid(String orderid){
		JsonObject jo = new JsonObject();
		Connection conn = null;
		PreparedStatement s = null;
		JsonArray ja = new JsonArray();
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select used,code, title, couponid, img, paid,pid from `youhui_purchase`.`second_kill` where orderid=?;";
			s = conn.prepareStatement(sql);
			s.setString(1, orderid);
			ResultSet rs = s.executeQuery();
			System.out.println(s);
			if (rs.next()) {
				String code = rs.getString("code");
				String title = rs.getString("title");
				String couponid = rs.getString("couponid");
				String img = rs.getString("img");
				String paid = rs.getString("paid");
				int used=rs.getInt("used");
				String pid=rs.getString("pid");
				
				
				jo.addProperty("used", used);
				jo.addProperty("orderid", orderid);
				jo.addProperty("code", code);
				jo.addProperty("title", title);
				jo.addProperty("couponid", couponid);
				jo.addProperty("img", img);
				jo.addProperty("paid", paid);
				jo.addProperty("pid", pid);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release( conn);
		}
		return jo;
	}
	
	/**
	 * 获得用户串码列表
	 * @param uid
	 * @return
	 */
	public String getSecondKillOrder(String uid){
		Connection conn = null;
		PreparedStatement s = null;
		JsonArray ja = new JsonArray();
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select usetime,used,code, title, couponid, img, paid,pid from `youhui_purchase`.`second_kill` where get_uid=? and status=?;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, STATUS_OCCUPIED);
			ResultSet rs = s.executeQuery();
			
			System.out.println(s);
			while (rs.next()) {
				String code = rs.getString("code");
				String title = rs.getString("title");
				String couponid = rs.getString("couponid");
				String orderId="";
				long expiretime=0;
				String expire="";
				String img = rs.getString("img");
				String paid = rs.getString("paid");
				int used=rs.getInt("used");
				long usetime=rs.getLong("usetime");
				
				String[] str=getOrderId(couponid, uid, conn);
				orderId=str[0];
				if(paid.equals("0")){
					expiretime=Long.parseLong(str[1]);
					
					if(new Date().getTime()>expiretime){
						paid="3";//未付款的情况下 如果过期 则失效
					}else{
						expire=DateUtil.getRestTimeCH3(expiretime);
					}
				}
				
				String pid=rs.getString("pid");
				JsonObject jo = new JsonObject();
				jo.addProperty("used", used);
				jo.addProperty("orderid", orderId);
				jo.addProperty("code", code);
				jo.addProperty("title", title);
				jo.addProperty("couponid", couponid);
				jo.addProperty("img", img);
				jo.addProperty("paid", paid);
				jo.addProperty("expire", expire);
				
				if(usetime!=0){
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					jo.addProperty("usetime", sdf.format(usetime));
				}
				
				String outerCode = OuterCode.getOuterCode(uid, "", "0", "17", "");
				String clickURL=Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" +pid ;
				
				jo.addProperty("click_url", clickURL);
				jo.addProperty("pid", pid);
				ja.add(jo);
			}
			return ja.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return ja.toString();
		}finally{
			MySQLDBIns.getInstance().release( conn);
		}
	}
	
	
	public String[] getOrderId(String couponId,String uid,Connection conn){
		String[] str=new String[2];
		try{
			String sql="select expiretime,orderid from youhui_purchase.coupon_order where couponid=? and userid=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,couponId);
			st.setString(2,uid);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				String orderid =rs.getString("orderid");
				String expiretime=rs.getString("expiretime");
				str[0]=orderid;
				str[1]=expiretime;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
	public SecondKill getInfo(int typeId,String type){
		Connection conn=null;
		SecondKill sk=new SecondKill();
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="select * from youhui_discount.second_kill where type_id=? and type=? ;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,typeId);
			st.setString(2,type);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				
				sk.setKillNum(rs.getInt("kill_num"));
				sk.setKillPrice(rs.getInt("kill_price"));
				sk.setRemainNum(rs.getInt("remain_num"));
			}
			
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return sk;
	}
	
	public boolean insertInfo(SecondKill sk){
		Connection conn=null;
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="insert into youhui_discount.second_kill (type_id,type,kill_price,kill_num,kill_start_timestamp,kill_end_timestamp,remain_num) values (?,?,?,?,?,?,?);";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,sk.getTypeId());
			st.setString(2,sk.getType());
			st.setDouble(3,sk.getKillPrice());
			st.setInt(4,sk.getKillNum());
			st.setLong(5,sk.getKillStartTimestamp());
			st.setLong(6,sk.getKillEndTimestamp());
			st.setInt(7,sk.getKillNum());
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return false;
	}
	
	public boolean setNum(int num,String type,int typeId){
		Connection conn=null;
		try{
			conn= MySQLDBIns.getInstance().getConnection();
			String sql="update youhui_discount.second_kill set remain_num=? where type_id=? and type=?; ";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,num);
			st.setInt(2,typeId);
			st.setString(3,type);
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return false;
	}
	
	/**
	 * 设置secondkill串码是否被使用
	 */
	public String useSecondKillCode(String code) {
		JsonObject jo = new JsonObject();
		Connection conn = null;
		PreparedStatement s = null;
		long usetime=new Date().getTime();
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update `youhui_purchase`.`second_kill` set used= ?,usetime=? where code=? and status=? and paid=? and used=?;";
			s = conn.prepareStatement(sql);
			s.setInt(1, STATUS_USED);
			s.setLong(2, usetime);
			s.setString(3, code);
			s.setInt(4, STATUS_OCCUPIED);
			s.setInt(5, STATUS_BUY);
			s.setInt(6, STATUS_UNUSED);
			if (s.executeUpdate() <= 0) {
				jo.addProperty("status", -1);
				jo.addProperty("msg", "No available code!");
			}else {
				jo.addProperty("status", 1);
				jo.addProperty("msg", "succeed");
			}
		}catch (Exception e) {
			e.printStackTrace();
			jo.addProperty("status", 10);
			jo.addProperty("msg", "Error!");
		}finally{
			MySQLDBIns.getInstance().release(s, conn);
		}
		return jo.toString();
	}
}
