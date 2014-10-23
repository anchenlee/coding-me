package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.dao.MySQLDBIns;
import cn.youhui.bean.Action;
import cn.youhui.bean.Sale;


public class SaleManager {
	private static final Logger log = Logger.getLogger(SaleManager.class);
	private static SaleManager instance;
	
	private SaleManager() {
	}
	
	public static SaleManager getInstance() {
		if (instance == null)
			instance = new SaleManager();
		return instance;
	}
	
	public List<Sale> getSaleItems(){
		List<Sale> list = new ArrayList<Sale>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql = "select * from youhui_datamining.tyh_sale where `state`=0;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sale bean = new Sale();
				bean.setId(rs.getString("id"));
				bean.setParentId(rs.getString("parent_id"));
				bean.setDate(rs.getString("date"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
				bean.setDescription(rs.getString("description"));
				bean.setMemo(rs.getString("memo"));
				bean.setShow(rs.getString("show"));
				bean.setRank(rs.getLong("rank"));
				bean.setStartTime(rs.getLong("start_time"));
				bean.setEndTime(rs.getLong("end_time"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public List<Sale> getSaleItemIds(String paid){
		List<Sale> list = new ArrayList<Sale>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql = "select * from youhui_datamining.tyh_sale where `state`=0 and `parent_id`=? order by `rank` asc;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, paid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sale bean = new Sale();
				bean.setId(rs.getString("id"));
				bean.setParentId(rs.getString("parent_id"));
				bean.setDate(rs.getString("date"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
				bean.setDescription(rs.getString("description"));
				bean.setMemo(rs.getString("memo"));
				bean.setShow(rs.getString("show"));
				bean.setRank(rs.getLong("rank"));
				bean.setStartTime(rs.getLong("start_time"));
				bean.setEndTime(rs.getLong("end_time"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取今天之前的
	 * @return
	 */
	public List<Sale> getBeforeSaleItems(String paid){
		List<Sale> list = new ArrayList<Sale>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql = "select * from youhui_datamining.tyh_sale where `state`=0 and `parent_id` = ? and `start_time` < ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, paid);
			ps.setLong(2, getToday());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sale bean = new Sale();
				bean.setId(rs.getString("id"));
				bean.setParentId(rs.getString("parent_id"));
				bean.setDate(rs.getString("date"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
				bean.setDescription(rs.getString("description"));
				bean.setMemo(rs.getString("memo"));
				bean.setShow(rs.getString("show"));
				bean.setRank(rs.getLong("rank"));
				bean.setStartTime(rs.getLong("start_time"));
				bean.setEndTime(rs.getLong("end_time"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取今天之后的
	 * @return
	 */
	public List<Sale> getAfterSaleItems(String paid){
		List<Sale> list = new ArrayList<Sale>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql = "select * from youhui_datamining.tyh_sale where `state`=0 and `parent_id` = ? and `start_time` > ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, paid);
			ps.setLong(2, getToday());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sale bean = new Sale();
				bean.setId(rs.getString("id"));
				bean.setParentId(rs.getString("parent_id"));
				bean.setDate(rs.getString("date"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
				bean.setDescription(rs.getString("description"));
				bean.setMemo(rs.getString("memo"));
				bean.setShow(rs.getString("show"));
				bean.setRank(rs.getLong("rank"));
				bean.setStartTime(rs.getLong("start_time"));
				bean.setEndTime(rs.getLong("end_time"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取今天之前的
	 * @return
	 */
	public List<Sale> getSaleItems(String paid){
		List<Sale> list = new ArrayList<Sale>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql = "select * from youhui_datamining.tyh_sale where `state`=0 and `parent_id` = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, paid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sale bean = new Sale();
				bean.setId(rs.getString("id"));
				bean.setParentId(rs.getString("parent_id"));
				bean.setDate(rs.getString("date"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
				bean.setDescription(rs.getString("description"));
				bean.setMemo(rs.getString("memo"));
				bean.setShow(rs.getString("show"));
				bean.setRank(rs.getLong("rank"));
				bean.setStartTime(rs.getLong("start_time"));
				bean.setEndTime(rs.getLong("end_time"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	/**
	 * 获取今天晚上24点的时间戳
	 */
	public long getToday(){
		Calendar now = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		today.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE) + 1, 0, 0, 0);    
		return today.getTimeInMillis();
	}
	
}
