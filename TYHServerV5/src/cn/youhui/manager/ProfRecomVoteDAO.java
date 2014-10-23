package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.youhui.bean.ProfRecomVote;
import cn.youhui.dao.MySQLDBIns;

public class ProfRecomVoteDAO {

	private static final Logger log = Logger.getLogger(ProfRecomVoteDAO.class);
	private static ProfRecomVoteDAO instance;
	
	public static final int VOTE_GOOD = 1;
	public static final int VOTE_BAD = 2;
	
	private ProfRecomVoteDAO() {
	}
	
	public static ProfRecomVoteDAO getInstance() {
		if (instance == null)
			instance = new ProfRecomVoteDAO();
		return instance;
	}
	
	/**
	 * 获取用户对某条信息的打分情况
	 * @param recommendId
	 * @param uid
	 * @return
	 */
	public int getVote(String recommendId, String uid){
		int vote = -1;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select vote_value from youhui_professor.tyh_prof_vote where `recommend_id`=? and `uid`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, recommendId);
			ps.setString(2, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				vote = rs.getInt("vote_value");
			}
		}catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return vote;
	}
	
	/**
	 * 获取用户点过值的信息ID列表
	 */
	public String getGoodVoteRecoms(String uid){
		return getVoteRecoms(uid, VOTE_GOOD);
	}
	
	/**
	 * 获取用户点过不值的信息ID列表
	 */
	public String getBadVoteRecoms(String uid){
		return getVoteRecoms(uid, VOTE_BAD);
	}
	
	/**
	 * 获取用户打过分的信息ID列表
	 * @return
	 */
	private String getVoteRecoms(String uid, int voteValue){
		StringBuffer sb = new StringBuffer();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select recommend_id from youhui_professor.tyh_prof_vote where `uid`=? and `vote_value`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, voteValue);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				sb.append(rs.getString("recommend_id") + ",");
			}
		}catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return sb.toString();
	}
	
	public boolean addVote(ProfRecomVote vote){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_professor.tyh_prof_vote (`recommend_id`,`uid`,`vote_value`,`timestamp`) values (?,?,?,?)" +
					" on duplicate key update `vote_value`=? , `timestamp`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vote.getRecommendId());
			ps.setString(2, vote.getUid());
			ps.setInt(3, vote.getVoteValue());
			long now = System.currentTimeMillis();
			ps.setLong(4, now);
			ps.setInt(5, vote.getVoteValue());
			ps.setLong(6, now);
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
}
