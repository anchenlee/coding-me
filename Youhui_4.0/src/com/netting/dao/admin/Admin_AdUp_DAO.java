package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.netting.bean.AdDown;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;

public class Admin_AdUp_DAO {

	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_AdUp_DAO.class);
	
	private static int pageSize = 10;
	
	public static List<AdDown> getAdsList(int page){
		if(page <= 0) page = 1;
		List<AdDown> list = new ArrayList<AdDown>();
		String sql = "SELECT * FROM youhui_datamining.tyh_ad_up where status=? order by rank desc  limit ?,?;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, (page-1)*pageSize);
			ps.setInt(3, pageSize);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				AdDown ad = new AdDown();
				ad.setActionType(rs.getString("action_type"));
				ad.setActionValue(rs.getString("action_value"));
				ad.setCreatetime(rs.getLong("create_time"));
				ad.setId(rs.getString("id"));
				ad.setImg(rs.getString("img"));
				ad.setRank(rs.getInt("rank"));
				ad.setStatus(rs.getInt("status"));
				ad.setTitle(rs.getString("title"));
				ad.setUpdateTime(rs.getLong("update_time"));
				ad.setUpdateDate(CodeUtil.getDateTimeStr(rs.getLong("update_time")));
				list.add(ad);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	public static int getAdsListPage(){
		int count = 0;
		String sql = "SELECT count(*) as c FROM youhui_datamining.tyh_ad_up where status=? ;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("c");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		if(count % pageSize == 0){
			count = count / pageSize;
		}else{
			count = count / pageSize + 1;
		}
		return count;
	}
	
	public static AdDown getAds(String id){
		AdDown ad = null;
		String sql = "SELECT * FROM youhui_datamining.tyh_ad_up where id=?;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DataSourceFactory.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				ad = new AdDown();
				ad.setActionType(rs.getString("action_type"));
				ad.setActionValue(rs.getString("action_value"));
				ad.setCreatetime(rs.getLong("create_time"));
				ad.setId(rs.getString("id"));
				ad.setImg(rs.getString("img"));
				ad.setRank(rs.getInt("rank"));
				ad.setStatus(rs.getInt("status"));
				ad.setTitle(rs.getString("title"));
				ad.setUpdateTime(rs.getLong("update_time"));
				ad.setUpdateDate(CodeUtil.getDateTimeStr(rs.getLong("update_time")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return ad;
	}
	
	/**
	 * 添加ads
	 * @param ad
	 * @return
	 */
	public static String addAds(AdDown ad){
		boolean flag = false;
		int rank = getRank();
		String id = getId();
		String sql = "insert into `youhui_datamining`.`tyh_ad_up`(img,title,action_type,action_value,rank,create_time,update_time,status,id) values(?,?,?,?,?,?,?,?,?) ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, ad.getImg());
			ps.setString(2, ad.getTitle());
			ps.setString(3, ad.getActionType());
			ps.setString(4, ad.getActionValue());
			ps.setInt(5, rank);
			ps.setLong(6, System.currentTimeMillis());
			ps.setLong(7, System.currentTimeMillis());
			ps.setInt(8, 1);
			ps.setString(9, id);
			
			ps.execute();
			flag = true;
		}
		catch (Exception e) 
		{
			logger.error("Admin_Ads_New_DAO.addAds error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return id;
	}
	
	public static String getId(){
		String id = "";
		String sql = "select max(id) as c from youhui_datamining.tyh_ad_up  ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs.next()){
				id = (rs.getInt("c")+1)+"";
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Ads_New_DAO.getId error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return id;
	}
	
	/**
	 * 获取插入时的rank
	 * @return
	 */
	public static int getRank(){
		int rank = 0;
		String sql = "select max(rank) as c from youhui_datamining.tyh_ad_up  ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs.next()){
				rank = rs.getInt("c")+1;
			}
		}
		catch (Exception e) 
		{
			logger.error("Admin_Ads_New_DAO.getRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return rank;
	}
	
	/**
	 * 修改Ads
	 * @param ad
	 * @return
	 */
	public static boolean updateAds(AdDown ad){
			boolean flag = false;
			String sql = "update youhui_datamining.tyh_ad_up set img = ?, title=?,action_type=?,action_value=?,update_time=? where id=? ";
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try 
			{
				conn = DataSourceFactory.getConnection();
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, ad.getImg());
				ps.setString(2, ad.getTitle());
				ps.setString(3, ad.getActionType());
				ps.setString(4, ad.getActionValue());
				ps.setLong(5, System.currentTimeMillis());
				ps.setString(6, ad.getId());
				
				ps.execute();
				flag = true;
			}
			catch (Exception e) 
			{
				logger.error("Admin_Ads_New_DAO.updateAds error:", e);
			} 
			finally 
			{
				DataSourceFactory.closeAll(rs, ps, conn);
			}
			return flag;
	}
	
	/**
	 * 删除ads
	 * @param id
	 * @return
	 */
	public static boolean delAds(String id){
		boolean flag = false;
		String sql = "update youhui_datamining.tyh_ad_up set status = ? where id=? ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 2);
			ps.setString(2, id);
			
			ps.execute();
			flag = true;
		}
		catch (Exception e) 
		{
			logger.error("Admin_Ads_New_DAO.delAds error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return flag;
	}
	
	/**
	 * 获取rank前一个
	 * @param id
	 * @return
	 */
	public static AdDown getPreAdDown(int rank){	
		AdDown ad = null;
		String sql = "SELECT * FROM youhui_datamining.tyh_ad_up where rank<? order by rank desc limit 1 ; ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rank);
			
			rs = ps.executeQuery();
			if(rs.next()){
				ad = new AdDown();
				ad.setActionType(rs.getString("action_type"));
				ad.setActionValue(rs.getString("action_value"));
				ad.setCreatetime(rs.getLong("create_time"));
				ad.setId(rs.getString("id"));
				ad.setImg(rs.getString("img"));
				ad.setRank(rs.getInt("rank"));
				ad.setStatus(rs.getInt("status"));
				ad.setTitle(rs.getString("title"));
				ad.setUpdateTime(rs.getLong("update_time"));
				ad.setUpdateDate(CodeUtil.getDateTimeStr(rs.getLong("update_time")));
			}
			
			ps.execute();
		}
		catch (Exception e) 
		{
			logger.error("Admin_Ads_New_DAO.getPreAdDown error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return ad;
	}
	
	
	/**
	 * 获取rank下一个
	 * @param id
	 * @return
	 */
	public static AdDown getNextAdDown(int rank){	
		AdDown ad = null;
		String sql = " SELECT * FROM youhui_datamining.tyh_ad_up where rank>? order by rank asc limit 1 ; ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rank);

			rs = ps.executeQuery();
			if(rs.next()){
				ad = new AdDown();
				ad.setActionType(rs.getString("action_type"));
				ad.setActionValue(rs.getString("action_value"));
				ad.setCreatetime(rs.getLong("create_time"));
				ad.setId(rs.getString("id"));
				ad.setImg(rs.getString("img"));
				ad.setRank(rs.getInt("rank"));
				ad.setStatus(rs.getInt("status"));
				ad.setTitle(rs.getString("title"));
				ad.setUpdateTime(rs.getLong("update_time"));
				ad.setUpdateDate(CodeUtil.getDateTimeStr(rs.getLong("update_time")));
			}
			
		}
		catch (Exception e) 
		{
			logger.error("Admin_Ads_New_DAO.getNextAdDown error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return ad;
	}
	
	/**
	 * 修改rank
	 * @param id
	 * @param rank
	 * @return
	 */
	public static boolean updateRank(String id,int rank){
		
		boolean flag = false;
		String sql = " update youhui_datamining.tyh_ad_up set rank=? where id=? ; ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rank);
			ps.setString(2, id);
			
			ps.executeUpdate();
			
			flag = true;
			
		}
		catch (Exception e) 
		{
			logger.error("Admin_Ads_New_DAO.updateRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 获取最小rank
	 * @return
	 */
	public static int getMaxRank(){
		int max = 0;
		String sql = " SELECT max(rank) as m FROM youhui_datamining.tyh_ad_up  ; ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs.next()){
				max = rs.getInt("m") +1;
			}
			
		}
		catch (Exception e) 
		{
			logger.error("Admin_Ads_New_DAO.updateRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return max;
	}
	
	/**
	 * 获取最小rank
	 * @return
	 */
	public static int getMinRank(){
		int min = 0;
		String sql = " SELECT min(rank) as m FROM youhui_datamining.tyh_ad_up  ; ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs.next()){
				min = rs.getInt("m")-1;
			}
			
		}
		catch (Exception e) 
		{
			logger.error("Admin_Ads_New_DAO.getMinRank error:", e);
		} 
		finally 
		{
			DataSourceFactory.closeAll(null, ps, conn);
		}
		return min;
	}
}
