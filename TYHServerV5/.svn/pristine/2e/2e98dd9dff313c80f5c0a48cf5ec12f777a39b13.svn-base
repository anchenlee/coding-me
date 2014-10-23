package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.ProfRecom;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.StringUtils;
import cn.youhui.utils.SuiShouActionUtil;
import cn.youhui.utils.Util;

public class ProfRecomDAO {

	private static int pageSize = 20;
	
	private static final int NORMAL = 1;
	private static final int DELETE = 2;
	private static final int reloadNum = 100;//首页商品reload个数
	
	
    private static ProfRecomDAO instance;
	
	private ProfRecomDAO() {
	}
	
	public static ProfRecomDAO getInstance() {
		if (instance == null)
			instance = new ProfRecomDAO();
		return instance;
	}
	
	public List<ProfRecom> getList(long lastTime, int page,String platform){
		List<ProfRecom> list = new ArrayList<ProfRecom>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select 1 as is_item,id,item_id,item_img as img,img_size,professor_id,recommend_reason,item_title as title,item_price,item_promprice,vote_bad," +
					"vote_good,`timestamp`,'tagStyleItem' as action_type, item_id as action_value from youhui_professor.tyh_prof_recommend where `status`=? and `timestamp`<? union select 0 as is_item,id,'' as item_id, img,img_size, professor_id," +
					"'' as recommend_reason,title, 0 as item_price, 0 as item_promprice,0 as vote_bad,0 as vote_good,`timestamp`,action_type,action_value from youhui_professor.tyh_prof_recom_ad where `status` = ? and `timestamp`<? order by `timestamp` desc limit ?,?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NORMAL);
			ps.setLong(2, lastTime);
			ps.setInt(3, NORMAL);
			ps.setLong(4, lastTime);
			ps.setInt(5, (page-1)*pageSize);
			ps.setInt(6, pageSize);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ProfRecom bean = new ProfRecom();
				bean.setId(rs.getString("id"));
				bean.setItemId(rs.getString("item_id"));
				bean.setItemImg(rs.getString("img"));
				bean.setImgSize(rs.getString("img_size"));
				bean.setProfessorId(rs.getString("professor_id"));
				bean.setRecommendReaSon(rs.getString("recommend_reason"));
				bean.setItemTitle(rs.getString("title"));
				bean.setItemPrice(rs.getDouble("item_price"));
				bean.setItemPromPrice(rs.getDouble("item_promprice"));
				bean.setVoteBadNum(rs.getInt("vote_bad"));
				bean.setVoteGoodNum(rs.getInt("vote_good"));
				bean.setIsItem(rs.getInt("is_item"));
				bean.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,rs.getString("action_type"), new String[]{rs.getString("action_value"),rs.getString("title")})));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public List<ProfRecom> getList(String platform){
		List<ProfRecom> list = new ArrayList<ProfRecom>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select 1 as is_item,id,item_id,item_img as img,img_size,professor_id,recommend_reason,item_title as title,item_price,item_promprice,vote_bad," +
					"vote_good,`timestamp`,'tagStyleItem' as action_type, item_id as action_value, rank from youhui_professor.tyh_prof_recommend where `status`=? " +
					" union " +
					" select 0 as is_item,id,'' as item_id, img,img_size, professor_id," +
					" '' as recommend_reason,title, 0 as item_price, 0 as item_promprice,0 as vote_bad,0 as vote_good,`timestamp`,action_type,action_value,rank from youhui_professor.tyh_prof_recom_ad where `status` = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NORMAL);
			ps.setInt(2, NORMAL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ProfRecom bean = new ProfRecom();
				bean.setId(rs.getString("id"));
				bean.setItemId(rs.getString("item_id"));
				bean.setItemImg(rs.getString("img"));
				bean.setImgSize(rs.getString("img_size"));
				bean.setProfessorId(rs.getString("professor_id"));
				bean.setRecommendReaSon(rs.getString("recommend_reason"));
				bean.setItemTitle(rs.getString("title"));
				bean.setItemPrice(rs.getDouble("item_price"));
				bean.setItemPromPrice(rs.getDouble("item_promprice"));
				bean.setVoteBadNum(rs.getInt("vote_bad"));
				bean.setVoteGoodNum(rs.getInt("vote_good"));
				bean.setTimestamp(rs.getLong("timestamp"));
				bean.setRank(rs.getLong("rank"));
				bean.setIsItem(rs.getInt("is_item"));
				bean.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,rs.getString("action_type"),new String[]{rs.getString("action_value"),rs.getString("title")})));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public List<ProfRecom> getListNew(String platform){
		List<ProfRecom> list = new ArrayList<ProfRecom>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT * FROM youhui_datamining.tyh_reco_items a ,youhui_datamining.m_discount_products b where a.item_id=b.item_id and a.status=1 order by a.timestamp desc limit ? ;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reloadNum);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ProfRecom bean = new ProfRecom();
				bean.setId(rs.getString("id"));
				bean.setItemId(rs.getString("item_id"));
				bean.setItemImg(rs.getString("pic_url"));
				bean.setImgSize(rs.getString("width_b")+"x"+rs.getString("height_b"));
				bean.setProfessorId("");
				String reason = rs.getString("reco_reason");
				if(StringUtils.isEmpty(reason)){
					reason = rs.getString("title");
				}
				String picCutUrl = rs.getString("pic_cut_url");
				if(picCutUrl == null || "".equals(picCutUrl)){
					picCutUrl = rs.getString("pic_url");
				}
//				if(picCutUrl != null && !"".equals(picCutUrl)){
//					picCutUrl = Util.getSimpleImg(picCutUrl, "400x400");
//				}
				bean.setItemImg(picCutUrl);
				bean.setRecommendReaSon(reason);
				bean.setItemTitle(rs.getString("title"));
				bean.setItemPrice(rs.getDouble("price_high"));
				bean.setItemPromPrice(rs.getDouble("price_low"));
				bean.setVoteBadNum(0);
				bean.setVoteGoodNum(0);
				bean.setTimestamp(rs.getLong("timestamp"));
				bean.setRank(rs.getLong("rank"));
				bean.setIsItem(1);
				bean.setFavNum(rs.getInt("fav_num"));
				bean.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl(platform,"tagStyleItem", rs.getString("item_id"))));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	
	public int getTotalPage(long lastTime){
		int ret = 0;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(*) as acount from youhui_professor.tyh_prof_recommend where `status`=? and `timestamp` < ? union select count(*) as acount from youhui_professor.tyh_prof_recom_ad where `status`=? and `timestamp` < ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, NORMAL);
			ps.setLong(2, lastTime);
			ps.setInt(3, NORMAL);
			ps.setLong(4, lastTime);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			if(rs.next()) {
				count += rs.getInt("acount");
			}
			ret = count / pageSize;
			if(count % pageSize > 0){
				ret ++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	/**
	 * 获取在time时间后  更新的条数
	 * @param time
	 * @return
	 */
	public int getUpAmount(long time){
		int amount = 0;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(id) as acount from youhui_professor.tyh_prof_recommend where `timestamp` > ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, time);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				amount = rs.getInt("acount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return amount;
	}
	
	/**
	 * 获取日期时间
	 * @param mills
	 * @return
	 */
	public static String getDateTimeStr(long mills)
	{
		if (mills > 0l)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(new Date(mills));
			return dateStr;
		}
		else
		{
			return "";
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(ProfRecomDAO.getInstance().getListNew().size());
	}
	
}
