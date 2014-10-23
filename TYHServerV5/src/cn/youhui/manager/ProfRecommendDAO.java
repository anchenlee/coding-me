package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.bean.ProfRecommend;
import cn.youhui.dao.MySQLDBIns;

/**
 * 达人推荐商品数据
 * @author lijun
 *
 */
public class ProfRecommendDAO {

	private static final Logger log = Logger.getLogger(ProfRecommendDAO.class);
	private static ProfRecommendDAO instance;
	
	private static int pageSize = 10;
	
	private static final int NORMAL = 1;
	private static final int DELETE = 2;
	
	private ProfRecommendDAO() {
	}
	
	public static ProfRecommendDAO getInstance() {
		if (instance == null)
			instance = new ProfRecommendDAO();
		return instance;
	}
	
	public List<ProfRecommend> getListByProf(String professorId, int page){
		List<ProfRecommend> list = new ArrayList<ProfRecommend>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_professor.tyh_prof_recommend where `professor_id`=? and `status`=? order by `timestamp` desc limit ?,?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, professorId);
			ps.setInt(2, NORMAL);
			ps.setInt(3, (page-1)*pageSize);
			ps.setInt(4, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProfRecommend bean = new ProfRecommend();
				bean.setId(rs.getString("id"));
				bean.setItemId(rs.getString("item_id"));
				bean.setItemImg(rs.getString("item_img"));
				bean.setImgSize(rs.getString("img_size"));
				bean.setProfessorId(rs.getString("professor_id"));
				bean.setRecommendReaSon(rs.getString("recommend_reason"));
				bean.setProfessor(ProfessorDAO.getInstance().getProfessor(bean.getProfessorId(), conn));
				bean.setItemTitle(rs.getString("item_title"));
				bean.setItemPrice(rs.getDouble("item_price"));
				bean.setItemPromPrice(rs.getDouble("item_promprice"));
				bean.setVoteBadNum(rs.getInt("vote_bad"));
				bean.setVoteGoodNum(rs.getInt("vote_good"));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public int getTotalPage(String professorId){
		int ret = 0;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(*) as acount from youhui_professor.tyh_prof_recommend where `professor_id`=? and `status`=?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, professorId);
			ps.setInt(2, NORMAL);
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
	
	public List<ProfRecommend> getListByItem(String itemId, int page){
		List<ProfRecommend> list = new ArrayList<ProfRecommend>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_professor.tyh_prof_recommend where `item_id`=? and `status`=? order by `timestamp` desc limit ?,?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, itemId);
			ps.setInt(2, NORMAL);
			ps.setInt(3, (page-1)*pageSize);
			ps.setInt(4, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProfRecommend bean = new ProfRecommend();
				bean.setId(rs.getString("id"));
				bean.setItemId(rs.getString("item_id"));
				bean.setItemImg(rs.getString("item_img"));
				bean.setImgSize(rs.getString("img_size"));
				bean.setProfessorId(rs.getString("professor_id"));
				bean.setRecommendReaSon(rs.getString("recommend_reason"));
				bean.setProfessor(ProfessorDAO.getInstance().getProfessor(bean.getProfessorId(), conn));
				bean.setItemTitle(rs.getString("item_title"));
				bean.setItemPrice(rs.getDouble("item_price"));
				bean.setItemPromPrice(rs.getDouble("item_promprice"));
				bean.setVoteBadNum(rs.getInt("vote_bad"));
				bean.setVoteGoodNum(rs.getInt("vote_good"));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public int getTotalPageByItem(String itemId){
		int ret = 0;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(*) as acount from youhui_professor.tyh_prof_recommend where `item_id`=? and `status`=?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, itemId);
			ps.setInt(2, NORMAL);
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
	
	public List<ProfRecommend> getList(int page){
		List<ProfRecommend> list = new ArrayList<ProfRecommend>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_professor.tyh_prof_recommend where `status`=? order by `timestamp` desc limit ?,?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NORMAL);
			ps.setInt(2, (page-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProfRecommend bean = new ProfRecommend();
				bean.setId(rs.getString("id"));
				bean.setItemId(rs.getString("item_id"));
				bean.setItemImg(rs.getString("item_img"));
				bean.setImgSize(rs.getString("img_size"));
				bean.setProfessorId(rs.getString("professor_id"));
				bean.setRecommendReaSon(rs.getString("recommend_reason"));
				bean.setProfessor(ProfessorDAO.getInstance().getProfessor(bean.getProfessorId(), conn));
				bean.setItemTitle(rs.getString("item_title"));
				bean.setItemPrice(rs.getDouble("item_price"));
				bean.setItemPromPrice(rs.getDouble("item_promprice"));
				bean.setVoteBadNum(rs.getInt("vote_bad"));
				bean.setVoteGoodNum(rs.getInt("vote_good"));
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
			String sql = "select count(*) as acount from youhui_professor.tyh_prof_recommend where `status`=?;";
		
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
	
	/**
	 * 根据用户订阅的professorids 获取推荐的信息
	 * @param professorId
	 * @param page
	 * @return
	 */
	public List<ProfRecommend> getListByProfs(String professorIds, int page){
		List<ProfRecommend> list = new ArrayList<ProfRecommend>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			list = getHasProf(professorIds, page, conn);
			if(list != null && list.size() > 0){
				if(list.size() < pageSize){
					list.addAll(getNotHasProf(professorIds, 0, pageSize - list.size(), conn));
				}
			}else{
				int hasnum = getHasProfNum(professorIds, conn);
				int start =(pageSize - hasnum % pageSize) + pageSize * (page - hasnum/pageSize -1);
				list = getNotHasProf(professorIds, start, pageSize, conn);
			}
			
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取用户订阅达人的推荐信息
	 * @return
	 */
	private List<ProfRecommend> getHasProf(String professorIds, int page, Connection conn){
		List<ProfRecommend> list = new ArrayList<ProfRecommend>();
		try{
			String sql = "select * from youhui_professor.tyh_prof_recommend where `professor_id` in (" + professorIds + ") and `status`=? order by `timestamp` desc limit ?,?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NORMAL);
			ps.setInt(2, (page-1)*pageSize);
			ps.setInt(3, pageSize);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProfRecommend bean = new ProfRecommend();
				bean.setId(rs.getString("id"));
				bean.setItemId(rs.getString("item_id"));
				bean.setItemImg(rs.getString("item_img"));
				bean.setImgSize(rs.getString("img_size"));
				bean.setProfessorId(rs.getString("professor_id"));
				bean.setRecommendReaSon(rs.getString("recommend_reason"));
				bean.setProfessor(ProfessorDAO.getInstance().getProfessor(bean.getProfessorId(), conn));
				bean.setItemTitle(rs.getString("item_title"));
				bean.setItemPrice(rs.getDouble("item_price"));
				bean.setItemPromPrice(rs.getDouble("item_promprice"));
				bean.setVoteBadNum(rs.getInt("vote_bad"));
				bean.setVoteGoodNum(rs.getInt("vote_good"));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		}
		return list;
	}
	
	/**
	 * 获取用户订阅达人的推荐信息数量
	 * @return
	 */
	private int getHasProfNum(String professorIds, Connection conn){
		int num = 0;
		try{
			String sql = "select count(id) as acount from youhui_professor.tyh_prof_recommend where `professor_id` in (" + professorIds + ") and `status`=?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NORMAL);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				num = rs.getInt("acount");
			}
			
		} catch (SQLException e) {
			log.error(e, e);
		}
		return num;
	}
	

	/**
	 * 获取用户没有订阅达人的推荐信息
	 * @param start 开始位置
	 * @param num 获取个数
	 * @param conn
	 * @return
	 */
	private List<ProfRecommend> getNotHasProf(String professorIds, int start, int num, Connection conn){
		List<ProfRecommend> list = new ArrayList<ProfRecommend>();
		try{
			String sql = "select * from youhui_professor.tyh_prof_recommend where `professor_id` not in (" + professorIds + ") and `status`=? order by `timestamp` desc limit ?,?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NORMAL);
			ps.setInt(2, start);
			ps.setInt(3, num);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProfRecommend bean = new ProfRecommend();
				bean.setId(rs.getString("id"));
				bean.setItemId(rs.getString("item_id"));
				bean.setItemImg(rs.getString("item_img"));
				bean.setImgSize(rs.getString("img_size"));
				bean.setProfessorId(rs.getString("professor_id"));
				bean.setRecommendReaSon(rs.getString("recommend_reason"));
				bean.setProfessor(ProfessorDAO.getInstance().getProfessor(bean.getProfessorId(), conn));
				bean.setItemTitle(rs.getString("item_title"));
				bean.setItemPrice(rs.getDouble("item_price"));
				bean.setItemPromPrice(rs.getDouble("item_promprice"));
				bean.setVoteBadNum(rs.getInt("vote_bad"));
				bean.setVoteGoodNum(rs.getInt("vote_good"));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		}
		return list;
	}
	
	/**
	 * 更新分数
	 * @param recommendId
	 * @param voteType  
	 * @return 1:值,2:不值,3:不值改值,4值改不值
	 */
	public boolean updateVote(String recommendId, int voteType){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String set = null;
			
			if(voteType == 1){
				set = " vote_good = vote_good + 1 ";
			}else if(voteType == 2){
				set = " vote_bad = vote_bad + 1 ";
			}else if(voteType == 3){
				set = " vote_good = vote_good + 1, vote_bad = vote_bad - 1 ";
			}else if(voteType == 4){
				set = " vote_bad = vote_bad + 1, vote_good = vote_good - 1 ";
			}else{
				return flag;
			}
			String sql = "update youhui_professor.tyh_prof_recommend set "+ set +" where id = ?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, recommendId);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
			
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public static void main(String[] args) {
		System.out.println(ProfRecommendDAO.getInstance().getListByProfs("1,2",1).get(0).getItemImg());
	}
}
