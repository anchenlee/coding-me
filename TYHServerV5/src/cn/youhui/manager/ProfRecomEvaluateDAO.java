package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.bean.ProfRecomEvaluate;
import cn.youhui.dao.MySQLDBIns;

public class ProfRecomEvaluateDAO {

	private static final Logger log = Logger.getLogger(ProfRecomEvaluateDAO.class);
	private static ProfRecomEvaluateDAO instance;
	
	private static int pageSize = 4;
	
	private static final int NORMAL = 1;
	private static final int DELETE = 2;
	
	private ProfRecomEvaluateDAO() {
	}
	
	public static ProfRecomEvaluateDAO getInstance() {
		if (instance == null)
			instance = new ProfRecomEvaluateDAO();
		return instance;
	}
	
	public List<ProfRecomEvaluate> getList(String recommendId, int page){
		List<ProfRecomEvaluate> list = new ArrayList<ProfRecomEvaluate>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_professor.tyh_prof_evaluate where `recommend_id`=? and `status`=? order by `timestamp` desc limit ?,?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, recommendId);
			ps.setInt(2, NORMAL);
			ps.setInt(3, (page-1)*pageSize);
			ps.setInt(4, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProfRecomEvaluate bean = new ProfRecomEvaluate();
				bean.setId(rs.getString("id"));
				bean.setEvaluate(rs.getString("evaluate"));
				bean.setRecommendId(rs.getString("recommend_id"));
				bean.setTaobaoNick(rs.getString("taobao_nick"));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public int getTotalPage(String recommendId){
		int ret = 0;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(*) as acount from youhui_professor.tyh_prof_evaluate where `recommend_id`=? and `status`=?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, recommendId);
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
	
	public boolean addEvaluate(ProfRecomEvaluate evaluate){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_professor.tyh_prof_evaluate (`recommend_id`,`uid`,`taobao_nick`,`evaluate`,`timestamp`,`status`) values (?,?,?,?,?,?)" +
					" on duplicate key update `evaluate`=? , `timestamp`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, evaluate.getRecommendId());
			ps.setString(2, evaluate.getUid());
			ps.setString(3, evaluate.getTaobaoNick());
			ps.setString(4, evaluate.getEvaluate());
			ps.setLong(5, System.currentTimeMillis());
			ps.setInt(6, NORMAL);
			ps.setString(7, evaluate.getEvaluate());
			ps.setLong(8, System.currentTimeMillis());
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
	
	public static void main(String[] args) {
		System.out.println(ProfRecomEvaluateDAO.getInstance().getTotalPage("1"));
	}
}
