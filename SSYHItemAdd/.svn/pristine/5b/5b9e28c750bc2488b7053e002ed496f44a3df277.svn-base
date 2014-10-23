package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.SuperItemTags;
import cn.youhui.cache.dao.superItemTagsCacher;
import cn.youhui.platform.db.SQL;

public class SuperItemTagsDAO {
	private static int STATUS_NOT_IN_USE=1;
	private static int STATUS_IN_USE=0;
	private static SuperItemTagsDAO instance=null;
	public static SuperItemTagsDAO getInstance(){
		if(instance==null){
			instance=new SuperItemTagsDAO();
		}
		return instance;
	}
	private SuperItemTagsDAO(){}
	
	public  boolean del(int id){
		Connection conn =null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update youhui_discount.item_tag set status=? where id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,STATUS_NOT_IN_USE);
			st.setInt(2,id);
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(SuperItemTags sit){
		Connection conn =null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="update  youhui_discount.item_tag set `name`=?,`desc`=?,`img`=? where id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,sit.getName());
			st.setString(2,sit.getDesc());
			st.setString(3, sit.getImg());
			st.setLong(4,sit.getId());
			if(st.executeUpdate()>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return false;
	}   
	
	public int add(String name,String desc,long timestamp,String img){
		int id=-1;
		Connection conn =null;
		try{
			conn=SQL.getInstance().getConnection();
			String sql="insert into youhui_discount.item_tag (`name`,`desc`,`timestamp`,`img`) values (?,?,?,?)";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,name);
			st.setString(2,desc);
			st.setLong(3,timestamp);
			st.setString(4, img);
			st.execute();
			System.out.println(st);
			ResultSet rs=st.getGeneratedKeys();
			if(rs.next()){
				id=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return id;
	}
	
	public cn.youhui.bean.SuperItemTags getById(int id,Connection conn){
		cn.youhui.bean.SuperItemTags it=null;
		try{
			String sql="select * from youhui_discount.item_tag where id=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,id);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				it=new cn.youhui.bean.SuperItemTags();
				it.setId(id);
				it.setName(rs.getString("name"));
				it.setDesc(rs.getString("desc"));
				it.setTimestamp(rs.getLong("timestamp"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return it;
	}
	
	public List<SuperItemTags> getAllTags(){
		Connection conn =null;
		List<SuperItemTags> list=new ArrayList<SuperItemTags>();
		try{
			conn=SQL.getInstance().getConnection();
			String sql="select * from youhui_discount.item_tag where status=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,STATUS_IN_USE);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				cn.youhui.bean.SuperItemTags it=new cn.youhui.bean.SuperItemTags();
				it.setId(rs.getInt("id"));
				it.setName(rs.getString("name"));
				it.setDesc(rs.getString("desc"));
				it.setTimestamp(rs.getLong("timestamp"));
				it.setImg(rs.getString("img"));
				list.add(it);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null,conn);
		}
		return list;
	}
	
}
