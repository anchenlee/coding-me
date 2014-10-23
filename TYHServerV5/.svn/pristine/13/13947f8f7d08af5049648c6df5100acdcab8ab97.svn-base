package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.CouponOrder;


public class CouponOrderDAO {
	private static CouponOrderDAO instance = null;
	private static final String TICKETINFO = "1";
	private static final String SECONDKILL = "2";
	private static final int ORDERCANCLE = -1;
	private CouponOrderDAO(){}
	
	public static CouponOrderDAO getInstance(){
		if(instance == null) instance = new CouponOrderDAO();
		return instance;
	}
	
	
	
	public CouponOrder getCouponOrderById(String order_id) {
		CouponOrder co = null;
		Connection conn = null;
		PreparedStatement s = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT * from `youhui_purchase`.`coupon_order` where orderid=? limit 1";
			s = conn.prepareStatement(sql);
			s.setString(1, order_id);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				co = new CouponOrder();
				co.setNumber(rs.getInt("number"));
				co.setCreatetime(rs.getLong("createtime"));
				co.setExpireTime(rs.getLong("expiretime"));
				co.setStatus(rs.getInt("status"));
				co.setUserid(rs.getString("userid"));
				co.setCouponid(rs.getString("couponid"));
				co.setId(rs.getInt("id"));
				co.setOrderid(rs.getString("orderid"));
				return co;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			MySQLDBIns.getInstance().release( conn);
		}
	}
	
	public List<CouponOrder> getUserOrders(String userid, int status) {
		Connection conn = null;
		PreparedStatement s = null;
		List<CouponOrder> cos = new ArrayList<CouponOrder>();
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT * from `youhui_purchase`.`coupon_order` where userid=? and status=?";
			s = conn.prepareStatement(sql);
			s.setString(1, userid);
			s.setInt(2, status);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				CouponOrder co = new CouponOrder();
				co.setNumber(rs.getInt("number"));
				co.setCreatetime(rs.getLong("createtime"));
				co.setStatus(rs.getInt("status"));
				co.setUserid(rs.getString("userid"));
				co.setOrderid(rs.getString("orderid"));
				cos.add(co);
			}
			return cos;
		} catch (Exception e) {
			e.printStackTrace();
			return cos;
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
	}
	
	
	public String getCouponOrderid(String couponid,String uid) {
		Connection conn = null;
		PreparedStatement s = null;
		String tmp="";
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT orderid from `youhui_purchase`.`coupon_order` where couponid=? and userid=?";
			s = conn.prepareStatement(sql);
			s.setString(1, couponid);
			s.setString(2,uid);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				tmp=rs.getString("orderid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			MySQLDBIns.getInstance().release( conn);
		}
		return tmp;
	}
	
}
