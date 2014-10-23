package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class SuperCouponDAO {

	private static SuperCouponDAO instance;
	public static SuperCouponDAO getInstance(){
		if(instance==null){
			instance=new SuperCouponDAO();
		}
		return instance;
	}
	private SuperCouponDAO(){}
	
	public boolean ifIn(String itemId){
		Connection conn=null;
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="select * from youhui_discount.super_coupon where item_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,itemId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return false;
	}
}
