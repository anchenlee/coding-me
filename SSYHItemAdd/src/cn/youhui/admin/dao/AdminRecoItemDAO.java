package cn.youhui.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.ProfRecom;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.platform.config.param;
import cn.youhui.platform.db.SQL;
import cn.youhui.platform.util.SuiShouActionUtil;

public class AdminRecoItemDAO {

	private static int normalStatus = 1;
	
	private static int delStatus = 2;
	
	private static int reloadNum = 200;
	
	public static boolean addRecoItem(ProfRecom bean){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into youhui_datamining.tyh_reco_items(`item_id`,`rank`,`timestamp`,`status`,`fav_num`) values(?,?,?,?,?) ON DUPLICATE KEY UPDATE `timestamp`=? ,`rank`=?,`status`=? ";
//		int rank = getMaxRank();
		
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getItemId());
			ps.setLong(2, bean.getRank());
			ps.setLong(3, System.currentTimeMillis());
			ps.setInt(4, normalStatus);
			ps.setInt(5, bean.getFavNum());
			ps.setLong(6, System.currentTimeMillis());
			ps.setLong(7, bean.getRank());
			ps.setInt(8, normalStatus);
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	public static boolean setRank(long rank1,long rank2){
		Connection conn=null;
		try {
			conn = SQL.getInstance().getConnection();
			String sql="update youhui_datamining.tyh_reco_items set rank=rank-1 where rank<=? and rank<>?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setLong(1,rank2);
			st.setLong(2, rank1);
			if(st.executeUpdate()>0){
				return true;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return false;
	}
	public static boolean setRank2(long rank1,long rank2){
		Connection conn=null;
		try {
			conn = SQL.getInstance().getConnection();
			String sql="update youhui_datamining.tyh_reco_items set rank=? where rank=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setLong(1,rank2);
			st.setLong(2, rank1);
			if(st.executeUpdate()>0){
				return true;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return false;
	}
	
	public static long getItemRank(String itemId){
		long rank = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = " SELECT rank FROM youhui_datamining.tyh_reco_items where item_id=?;";
		ResultSet rs = null;
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemId);
			rs = ps.executeQuery();
			if(rs.next()){
				rank = rs.getLong("rank");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return rank;
	}
	
	public static long getItemRank(String itemId,Connection conn){
		long rank = 0;
		PreparedStatement ps = null;
		String sql = " SELECT rank FROM youhui_datamining.tyh_reco_items where item_id=?;";
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemId);
			rs = ps.executeQuery();
			if(rs.next()){
				rank = rs.getLong("rank");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rank;
	}
	
	
	public static int getMaxRank(){
		int rank = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = " SELECT max(*) as c FROM youhui_datamining.tyh_reco_items;";
		ResultSet rs = null;
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				rank = rs.getInt("c");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return rank;
	}
	
	public static boolean delRecoItem(String itemId){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = " update youhui_datamining.tyh_reco_items set status=? where item_id=?;";
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, delStatus);
			ps.setString(2, itemId);
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	public static List<TeJiaGoodsBean> getRecoItemList(){
		List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = " SELECT * FROM youhui_datamining.tyh_reco_items a,youhui_datamining.m_discount_products b where a.status='1' and a.item_id=b.item_id;";
		ResultSet rs = null;
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				
				list.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return list;
	}
	
	public static boolean isExit(String itemid){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select * from youhui_datamining.tyh_reco_items where item_id=? and status=?;";
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemid);
			ps.setInt(2, normalStatus);

			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	public static boolean isExit(String itemid,Connection conn){
		boolean flag = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select * from youhui_datamining.tyh_reco_items where item_id=? and status=?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemid);
			ps.setInt(2, normalStatus);

			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static List<ProfRecom> getListNew(){
		List<ProfRecom> list = new ArrayList<ProfRecom>();
		Connection conn = null;
		try{
			conn = SQL.getInstance().getConnection();
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
				if(reason == null || "".equals(reason)){
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
				bean.setAction(new SuiShouAction(SuiShouActionUtil.getSuiShouActionUrl("tagStyleItem", rs.getString("item_id"))));
				bean.setActionType(param.ACTION_TYPE4PAD);
				bean.setActionValue(rs.getString("item_id"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQL.getInstance().release(null, conn);
		}
		return list;
	}
	
	public static List<String> getListNew2(){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		try{
			conn = SQL.getInstance().getConnection();
			String sql = "SELECT * FROM youhui_datamining.tyh_reco_items a ,youhui_datamining.m_discount_products b where a.item_id=b.item_id and a.status=1 order by a.timestamp desc limit ? ;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 1000);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("item_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQL.getInstance().release(null, conn);
		}
		return list;
	}
	
	
	public static void main(String[] args) {
		
		ProfRecom bean = new ProfRecom();
		addRecoItem(bean);
	}
	
}
