package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.youhui.bean.ProfCategory;
import cn.youhui.bean.Professor;
import cn.youhui.dao.MySQLDBIns;

public class ProfCategoryDAO {

	private static final Logger log = Logger.getLogger(ProfCategoryDAO.class);
	
	private static ProfCategoryDAO instance;
	
	private static int pageSize = 6;
	
	private ProfCategoryDAO() {
	}
	
	public static ProfCategoryDAO getInstance() {
		if (instance == null){
			instance = new ProfCategoryDAO();
		}
		return instance;
	}
	
	/**
	 * 管理员获取达人分类列表
	 * @param page
	 * @param status 1,正常；2，删除
	 * @return
	 */
	public static ArrayList<ProfCategory> getCategoryList(int page)
	{
		ArrayList<ProfCategory> list = new ArrayList<ProfCategory>();
		
		String sql = "SELECT * FROM `youhui_professor`.`tyh_prof_category` WHERE `status`= 1 ORDER BY `sort` DESC limit ?,?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, pageSize * (page - 1));
			ps.setInt(2, pageSize);
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				ProfCategory bean = new ProfCategory();
				
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setDesc(rs.getString("desc"));
				bean.setSort(rs.getInt("sort"));
				bean.setStatus(rs.getInt("status"));
				
				list.add(bean);
			}
		}
		catch (Exception e) 
		{
			log.error("ProfCategoryDAO.getCategoryList error:", e);
		} 
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取总页数
	 * @param status
	 * @return
	 */
	public static int getCategoryTotal() 
	{
		int totalPage = 0;
		
		String sql = "SELECT count(`id`) AS count FROM `youhui_professor`.`tyh_prof_category` WHERE `status` = 1;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rtst = null;
		
		try 
		{
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			
			rtst = ps.executeQuery();
			int count = 0;
			if (rtst.next())
			{
				count = rtst.getInt("count");
			}
			
			totalPage = count / pageSize;
			int ys = count % pageSize;
			if(ys > 0)
			{
				totalPage++;
			}
		}
		catch (Exception e) 
		{
			log.error("ProfCategoryDAO.getCategoryTotal error:", e);
		} 
		finally 
		{
			MySQLDBIns.getInstance().release(conn);
		}
		
		return totalPage;
	}
	
	public static ArrayList<Professor> getProfessorListByCat(int page, String catID){
		ArrayList<Professor> list = new ArrayList<Professor>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_professor.tyh_professor where `status` = 1 AND `category` = ? order by `id` limit ?,?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, catID);
			ps.setInt(2, (page-1) * pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Professor bean = new Professor();
				bean.setId(rs.getString("id"));
				bean.setCategory(rs.getString("category"));
				bean.setIcon(rs.getString("icon"));
				bean.setDesc(rs.getString("desc"));
				bean.setName(rs.getString("name"));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error("ProfCategoryDAO.getProfessorListByCat error", e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public static int getProfessorListTotalByCat(String catID){
		int ret = 0;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(*) as acount from youhui_professor.tyh_professor where `status` = 1 AND `category` = ?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, catID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("acount");
				ret = count / pageSize;
				if(count % pageSize > 0){
					ret ++;
				}
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(ProfCategoryDAO.getCategoryTotal());
	}
}
