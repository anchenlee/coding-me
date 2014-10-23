package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.TimeUtil;
import com.netting.bean.HotKeywordBean;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;

/**
 * 热门关键词
 * @author belonghu
 *
 */
public class AdminHotKeywordDAO {

	private static int pageSize = 30;
	
	private static int normalStatus = 0;
	
	private static int delStatus = 1;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static HotKeywordBean getADBean(String id){
		HotKeywordBean bean = null;
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_hot_keyword` WHERE `id` = ?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			
			rs = ps.executeQuery();
			if (rs.next())
			{
				bean = new HotKeywordBean();
				bean.setId(rs.getString("id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setRank(rs.getInt("rank"));
				bean.setTimestamp(rs.getLong("timestamp"));
				bean.setStatus(rs.getString("status"));
				
			}
		}
		catch (Exception e){
			
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return bean;
	}
	
	public static List<HotKeywordBean> getHotKeywordList(int page){
		List<HotKeywordBean> list = new ArrayList<HotKeywordBean>();
		if(page <=0){
			page = 1;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_hot_keyword` where status='0' order by rank desc limit ?,?;";
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*pageSize);
			ps.setInt(2, pageSize);
			
			rs = ps.executeQuery();
			while (rs.next()){
				HotKeywordBean bean = new HotKeywordBean();
				bean.setId(rs.getString("id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setRank(rs.getInt("rank"));
				bean.setTimestamp(rs.getLong("timestamp"));
				bean.setStatus(rs.getString("status"));
				bean.setTimestampStr(CodeUtil.getDateTimeStr(rs.getLong("timestamp")));
				list.add(bean);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return list;
	}
	
	public static int getHotKeywordListPage(){
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT count(*) as c FROM `youhui_datamining`.`tyh_hot_keyword` ;";
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if (rs.next()){
				count = rs.getInt("c");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		if(count % pageSize == 0){
			count = count / pageSize;
		}else{
			count = count / pageSize +1;
		}
		return count;
	}
	
	public static boolean addHotKeyword(String keyword){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into youhui_datamining.tyh_hot_keyword(`keyword`,`rank`,`timestamp`,`status`) values(?,?,?,?) ;";
		ResultSet rs = null;
		int rank = getMaxRank();
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, keyword);
			ps.setInt(2, rank);
			ps.setLong(3, System.currentTimeMillis());
			ps.setInt(4, normalStatus);
			ps.executeUpdate();
			flag = true;
		}
		catch (Exception e){
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return flag;
	}
	
	public static int getMaxRank(){
		int rank = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT max(rank) as m FROM youhui_datamining.tyh_hot_keyword;  ;";
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				rank = rs.getInt("m")+1;
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return rank;
	}
	
	public static int getMinRank(){
		int rank = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT min(rank) as m FROM youhui_datamining.tyh_hot_keyword;  ;";
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				rank = rs.getInt("m")-1;
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return rank;
	}
	
	public static boolean updateRank(String id,int rank){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update youhui_datamining.tyh_hot_keyword set `rank`=? where id=? ;";
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, rank);
			ps.setString(2, id);
			ps.executeUpdate();
			flag = true;
		}
		catch (Exception e){
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return flag;
	}
	
	
	public static boolean delHotKeyWord(String id){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update youhui_datamining.tyh_hot_keyword set `status`=? where id=? ;";
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, delStatus);
			ps.setString(2, id);
			ps.executeUpdate();
			flag = true;
		}
		catch (Exception e){
			e.printStackTrace();
		}finally {
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return flag;
	}
	
	
	
}
