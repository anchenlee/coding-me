package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import cn.youhui.bean.SuperCouponBean;
import cn.youhui.platform.db.SQL;

public class SuperCouponDAO {

	private static SuperCouponDAO instance;
	public static SuperCouponDAO getInstance(){
		if(instance==null){
			instance=new SuperCouponDAO();
		}
		return instance;
	}
	private SuperCouponDAO(){}
	
	
	public boolean del(int id){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="delete from youhui_discount.super_coupon where id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,id);
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return false;
	}
	
	public int add(SuperCouponBean scb){
		Connection conn=null;
		int id=-1;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="insert into youhui_discount.super_coupon (item_id,item_title,coupon_address,timestamp,yhq_title) values (?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,scb.getItemId());
			ps.setString(2,scb.getItemTitle());
			ps.setString(3,scb.getCouponAddress());
			ps.setLong(4,scb.getTimestamp());
			ps.setString(5,scb.getYhqTitle());
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()){
				id=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return id;
	}
	
	public boolean update(SuperCouponBean scb){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_discount.super_coupon set coupon_address=?,yhq_title=?,item_id=?,item_title=? where id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,scb.getCouponAddress());
			ps.setString(2,scb.getYhqTitle());
			ps.setString(3,scb.getItemId());
			ps.setString(4,scb.getItemTitle());
			ps.setInt(5,scb.getId());
			if(ps.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return false;
	}
}
