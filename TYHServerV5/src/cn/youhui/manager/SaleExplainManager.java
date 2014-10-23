package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.bean.SaleExplain;
import cn.youhui.bean.TaobaoShop;
import cn.youhui.dao.MySQLDBIns;

public class SaleExplainManager {
	
	private static final Logger log = Logger.getLogger(SaleExplainManager.class);
	private static SaleExplainManager instance;
	
	private SaleExplainManager() {
	}
	
	public static SaleExplainManager getInstance() {
		if (instance == null)
			instance = new SaleExplainManager();
		return instance;
	}
	
	public List<SaleExplain> getSaleItems(String saleId){
		List<SaleExplain> list = new ArrayList<SaleExplain>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql = "select * from youhui_datamining.tyh_sale_new_explain where `sale_id`=? and `status`=1;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SaleExplain bean = new SaleExplain();
				bean.setId(rs.getString("id"));
				bean.setDesc(rs.getString("desc"));
				bean.setImg(rs.getString("img"));
				bean.setItems(rs.getString("imgs"));
				bean.setShops(rs.getString("shops"));
				bean.setType(rs.getInt("type"));
				bean.setUrl(rs.getString("url"));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public List<SaleExplain> getSaleItems(String saleId, Connection conn){
		List<SaleExplain> list = new ArrayList<SaleExplain>();
		try{
			String sql = "select * from youhui_datamining.tyh_sale_new_explain where `sale_id`=? and `status`=1;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, saleId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SaleExplain bean = new SaleExplain();
				bean.setId(rs.getString("id"));
				bean.setDesc(rs.getString("desc"));
				bean.setImg(rs.getString("img"));
				bean.setItems(rs.getString("items"));
				bean.setShops(rs.getString("shops"));
				bean.setType(rs.getInt("type"));
				bean.setUrl(rs.getString("url"));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
		}
		return list;
	}
	
	public String getItemsStr(String explainId){
		String items = null;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql = "select * from youhui_datamining.tyh_sale_new_explain where `id`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, explainId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				items = rs.getString("items");
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return items;
	}
	
	public List<TaobaoShop> getShopsByExplainId(String explainId){
		List<TaobaoShop> shops = new ArrayList<TaobaoShop>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql = "select * from youhui_datamining.tyh_sale_new_explain where `id`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, explainId);
			ResultSet rs = ps.executeQuery();

			String shopstr = null;
			if (rs.next()) {
				shopstr = rs.getString("shops");
			}
			
			if(shopstr != null && !"".equals(shopstr)){
				shops = ShopManager.getInstance().getShopsByShopIds(shopstr);
			}else{
				shops = null;
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return shops;
	}
}
