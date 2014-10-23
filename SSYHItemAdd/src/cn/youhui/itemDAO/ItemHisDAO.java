package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.itemadd.dataadapter.ItemHisBean;
import cn.youhui.platform.db.SQL;

public class ItemHisDAO {

	private static ItemHisDAO instance=null;
	public static ItemHisDAO getInstance(){
		if(instance==null){
			instance=new ItemHisDAO();
		}
		return instance;
	}
	private ItemHisDAO(){}
	
//	public List<ItemHisBean> getInfo(String itemId){
//		Connection conn = null;
//		PreparedStatement ps  = null;
//		List<ItemHisBean> list=new ArrayList<ItemHisBean>();
//		try{
//			conn=SQL.getInstance().getConnection();
//			String sql="select * from youhui_datamining.Item_add_his where item_id=?";
//			ps=conn.prepareStatement(sql);
//			ps.setString(1,itemId);
//			ResultSet rs=ps.executeQuery();
//			while(rs.next()){
//				ItemHisBean ib=new ItemHisBean();
//				ib.setItemId(itemId);
//				ib.setType(rs.getString("type"));
//				ib.setTokId(rs.getString("tok_id"));
//				ib.setTokName(rs.getString("tok_name"));
//				list.add(ib);
//			}
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally{
//			SQL.getInstance().release(ps, conn);
//		}
//		return list;
//	}
	
	public boolean insert(String itemId,String type,String tokId,String tokName){
		
		Connection conn = null;
		PreparedStatement ps  = null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="insert into youhui_datamining.Item_add_his (item_id,type,tok_id,tok_name) values (?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, itemId);
			ps.setString(2, type);
			ps.setString(3, tokId);
			ps.setString(4, tokName);
			if(ps.executeUpdate()>0){
				return true;
			}

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
	}
	
	public boolean ifExist(String itemId){
		Connection conn = null;
		PreparedStatement ps  = null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select * from youhui_datamining.Item_add_his where item_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,itemId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return false;
	}
	
	public void delete(String itemId){
		Connection conn = null;
		PreparedStatement ps  = null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="delete from youhui_datamining.Item_add_his where item_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,itemId);
			ps.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
	}
	
}
