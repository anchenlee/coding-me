package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.bean.Action;
import cn.youhui.bean.SaleNew;
import cn.youhui.dao.MySQLDBIns;

public class SaleNewManager {

	private static final Logger log = Logger.getLogger(SaleNewManager.class);
	private static SaleNewManager instance;
	
	private static int pageSize = 3;
	
	private SaleNewManager() {
	}
	
	public static SaleNewManager getInstance() {
		if (instance == null)
			instance = new SaleNewManager();
		return instance;
	}
	
	public List<SaleNew> getHeadSaleItems(){
		return getSaleItems(1, 1);
	}
	
	public List<SaleNew> getBodySaleItems(int page){
		return getSaleItems(2, page);
	}
	
	private List<SaleNew> getSaleItems(int position, int page){
		List<SaleNew> list = new ArrayList<SaleNew>();
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_datamining.tyh_sale_new where `position`=? and `status`=1 order by `id` limit ?,?;";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, position);
			ps.setInt(2, (page-1)*pageSize);
			ps.setInt(3, pageSize);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SaleNew bean = new SaleNew();
				bean.setId(rs.getString("id"));
				bean.setCommission(rs.getDouble("commission"));
				bean.setCommissionRate(rs.getDouble("commission_rate"));
				bean.setDesc(rs.getString("desc"));
				bean.setId(rs.getString("id"));
				bean.setImg(rs.getString("img"));
				bean.setItemId(rs.getString("item_id"));
				bean.setPositioin(rs.getInt("position"));
				bean.setPrice(rs.getDouble("price"));
				bean.setPromPrice(rs.getDouble("prom_price"));
				bean.setSaleDesc(rs.getString("sale_desc"));
				bean.setTitle(rs.getString("title"));
				bean.setType(rs.getInt("type"));
				bean.setExplains(SaleExplainManager.getInstance().getSaleItems(bean.getId(), conn));
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
	
	public List<SaleNew> getSaleItems(){
		List<SaleNew> list = new ArrayList<SaleNew>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql = "select * from youhui_datamining.tyh_sale_new where `status`=1 order by `id`;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SaleNew bean = new SaleNew();
				bean.setId(rs.getString("id"));
				bean.setCommission(rs.getDouble("commission"));
				bean.setCommissionRate(rs.getDouble("commission_rate"));
				bean.setDesc(rs.getString("desc"));
				bean.setId(rs.getString("id"));
				bean.setImg(rs.getString("img"));
				bean.setItemId(rs.getString("item_id"));
				bean.setPositioin(rs.getInt("position"));
				bean.setPrice(rs.getDouble("price"));
				bean.setPromPrice(rs.getDouble("prom_price"));
				bean.setSaleDesc(rs.getString("sale_desc"));
				bean.setTitle(rs.getString("title"));
				bean.setType(rs.getInt("type"));
				bean.setExplains(SaleExplainManager.getInstance().getSaleItems(bean.getId(), conn));
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
	
	public static void main(String[] args) {
		System.out.println(SaleNewManager.getInstance().getSaleItems(1, 1));
	}
}
