package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.youhui.dao.MySQLDBIns;

public class ProfUserProfessorDAO {
	
	private static final Logger log = Logger.getLogger(ProfUserProfessorDAO.class);
	private static ProfUserProfessorDAO instance;
	
	private ProfUserProfessorDAO() {
	}
	
	public static ProfUserProfessorDAO getInstance() {
		if (instance == null)
			instance = new ProfUserProfessorDAO();
		return instance;
	}
	
	/**
	 * 更新用户订阅的达人
	 * @param uid
	 * @param addProfessorIds   添加的达人
	 * @param delProfessorIds   删除的达人
	 * @return
	 */
	public boolean updateUserProf(String uid, String addProfessorIds, String delProfessorIds){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String hasProfessorIds = getProfessorIds(uid, conn);
			String professorIds = "";
			

			
			if(hasProfessorIds == null || "".equals(hasProfessorIds)){
				professorIds = addProfessorIds;
			}else{
				professorIds = getNewProfessorIds(hasProfessorIds, addProfessorIds, delProfessorIds);
			}

			
			String sql = "insert into youhui_professor.tyh_prof_userprof (`uid`,`professor_ids`,`timestamp`) values (?,?,?) " +
					"on duplicate key update `professor_ids`=?, `timestamp`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, professorIds);
			ps.setLong(3, System.currentTimeMillis());
			ps.setString(4, professorIds);
			ps.setLong(5, System.currentTimeMillis());
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	private String getNewProfessorIds(String hasProfessorIds, String addProfessorIds, String delProfessorIds){
		String ret = hasProfessorIds;
		String[] addpros = addProfessorIds.split(",");
		String[] delpros = delProfessorIds.split(",");
		
		if(delpros.length > 0){
			for(String profId : delpros){
				ret = ret.replaceAll(profId, "");
			}
		}
		
		if(addpros.length > 0){
			for(String profId : addpros){
				if(!ret.contains(profId)){
					ret += "," + profId;		
				}
			}
		}
		
		ret = ret.replaceAll(",{2,}", ",");
		
		if(ret.indexOf(",") == 0){
			ret = ret.substring(1, ret.length());
		}
		
		if(ret.lastIndexOf(",") == ret.length()-1){
			ret = ret.substring(0, ret.length()-1);
		}
		
		return ret;
	}
	
	private String getProfessorIds(String uid, Connection conn){
		String professorIds = "";
		try{
			String sql = "select * from youhui_professor.tyh_prof_userprof where `uid` = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				professorIds = rs.getString("professor_ids");
			}
		}catch (SQLException e) {
			log.error(e, e);
		}
		return professorIds;
	}
	
	public String getProfessorIds(String uid){
		String professorIds = "";
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_professor.tyh_prof_userprof where `uid` = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				professorIds = rs.getString("professor_ids");
			}
		}catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return professorIds;
	}
	
	public static void main(String[] args) {
		System.out.println(ProfUserProfessorDAO.getInstance().getNewProfessorIds("2,5,1,7,8,9", "", "1,2,3"));  //
	}
}
