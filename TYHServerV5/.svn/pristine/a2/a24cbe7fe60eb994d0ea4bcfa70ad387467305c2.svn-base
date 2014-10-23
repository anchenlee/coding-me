package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class SuperItemTagsDAO {

	private static SuperItemTagsDAO instance=null;
	public static SuperItemTagsDAO getInstance(){
		if(instance==null){
			instance=new SuperItemTagsDAO();
		}
		return instance;
	}
	private SuperItemTagsDAO(){}
	
	public cn.youhui.bean.SuperItemTags getById(int id){
		Connection conn =null;
		cn.youhui.bean.SuperItemTags it=null;
		try{
			conn=MySQLDBIns.getInstance().getConnection();
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
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return it;
	}
}
