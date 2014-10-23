package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.youhui.dao.MySQLDBIns;


public class FavBrandDAO {
	
	private static final int HAS_NEW = 1;      //有更新
	private static final int HAS_BROWSE = 0;   //已浏览过了
	
	private static FavBrandDAO instance = null;
	
	public static FavBrandDAO getInstance(){
		if(instance == null) instance = new FavBrandDAO();
		return instance;
	}
	
	/**
	 * 是否还存在没浏览过的品牌
	 * @param uid
	 * @return
	 */
	public boolean hasNewBrand(String uid){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select brand_id from youhui_bdsh.fav_brand where uid=? and has_new=? limit 1;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, HAS_NEW);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
}
