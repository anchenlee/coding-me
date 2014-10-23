package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.SuperForWebBean;
import cn.youhui.platform.db.SQL;

public class SuperForWebDAO {

	private static SuperForWebDAO instance=null;
	public static SuperForWebDAO getInstance(){
		if(instance==null){
			instance=new SuperForWebDAO();
		}
		return instance;
	}
	private SuperForWebDAO(){}
	
	public List<SuperForWebBean> getAll(){
		List<SuperForWebBean> list=new ArrayList<SuperForWebBean>();
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select * from youhui_discount.super_web ;";
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				SuperForWebBean swb=new SuperForWebBean();
				swb.setId(rs.getInt("id"));
				swb.setBigImg(rs.getString("big_img"));
				swb.setSmallImg(rs.getString("small_img"));
				swb.setColor(rs.getString("color"));
				swb.setFromUid(rs.getString("from_uid"));
				swb.setSuperId(rs.getInt("super_id"));
				list.add(swb);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return list;
	}
	
	public int insertInfo(SuperForWebBean swb){
		Connection conn=null;
		int id=-1;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="insert into youhui_discount.super_web (small_img,big_img,color,super_id,from_uid) values (?,?,?,?,?);";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,swb.getSmallImg());
			st.setString(2,swb.getBigImg());
			st.setString(3,swb.getColor());
			st.setInt(4,swb.getSuperId());
			st.setString(5,swb.getFromUid());
			st.execute();
			ResultSet rs=st.getGeneratedKeys();
			
			if(rs.next()){
				id=rs.getInt(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return id;
	}
	
	public boolean updateInfo(SuperForWebBean swb){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_discount.super_web set small_img=?,big_img=?,color=?,from_uid=? where super_id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,swb.getSmallImg());
			st.setString(2,swb.getBigImg());
			st.setString(3,swb.getColor());
			st.setString(4,swb.getFromUid());
			st.setInt(5,swb.getSuperId());
			if(st.executeUpdate()>0){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return false;
	}

	public boolean ifExist(int id){
		Connection conn=null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select * from youhui_discount.super_web  where id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,id);
			if(st.executeQuery().next()){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return false;
	}
	
}
