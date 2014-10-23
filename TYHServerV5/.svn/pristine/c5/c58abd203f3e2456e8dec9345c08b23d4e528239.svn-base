package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.bean.Professor;
import cn.youhui.dao.MySQLDBIns;

/**
 * 达人数据
 * @author lijun
 *
 */
public class ProfessorDAO {
	
	private static final Logger log = Logger.getLogger(ProfessorDAO.class);
	private static ProfessorDAO instance;
	
	private static int pageSize = 5;
	
	private static final int NORMAL = 1;
	private static final int DELETE = 2;
	
	private ProfessorDAO() {
	}
	
	public static ProfessorDAO getInstance() {
		if (instance == null)
			instance = new ProfessorDAO();
		return instance;
	}
	
	public List<Professor> getList(int page){
		List<Professor> list = new ArrayList<Professor>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_professor.tyh_professor where `status`=? order by `id` limit ?,?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NORMAL);
			ps.setInt(2, (page-1)*pageSize);
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
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public int getTotalPage(){
		int ret = 0;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(*) as acount from youhui_professor.tyh_professor where `status`=?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NORMAL);
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
	
	public Professor getProfessor(String professorId){
		Professor ret = new Professor();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_professor.tyh_professor where `id`=?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, professorId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ret.setCategory(rs.getString("category"));
				ret.setIcon(rs.getString("icon"));
				ret.setDesc(rs.getString("desc"));
				ret.setId(rs.getString("id"));
				ret.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			log.error(e, e);
		}finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	public Professor getProfessor(String professorId, Connection conn){
		Professor ret = new Professor();
		try{
			String sql = "select * from youhui_professor.tyh_professor where `id`=?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, professorId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ret.setCategory(rs.getString("category"));
				ret.setIcon(rs.getString("icon"));
				ret.setDesc(rs.getString("desc"));
				ret.setId(rs.getString("id"));
				ret.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			log.error(e, e);
		}
		return ret;
	}
}
