package cn.youhui.itemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.youhui.platform.db.SQL;

public class MUserDAO {

	private static MUserDAO instance=null;
	public static MUserDAO getInstance(){
		if(instance==null){
			instance=new MUserDAO();
		}
		return instance;
	}
	private MUserDAO(){}
	
	public int getIdByUsername(String username){
		int id=0;
		Connection conn = null;
		PreparedStatement ps  = null;
		String sql="select id  from  youhui_v3.m_user where username=?";
		try{
			conn=SQL.getInstance().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				id=rs.getInt("id");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return id;
	}
	
	
}
