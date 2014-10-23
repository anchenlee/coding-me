package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.bean.TaobaoShop;
import cn.youhui.dao.MySQLDBIns;

public class ShopManager {

	private static final Logger log = Logger.getLogger(ShopManager.class);
	private static ShopManager instance;
	
	private ShopManager() {
	}
	
	public static ShopManager getInstance() {
		if (instance == null)
			instance = new ShopManager();
		return instance;
	}
	
	public List<TaobaoShop> getShopsByShopIds(String shopIds){
		List<TaobaoShop> shops = new ArrayList<TaobaoShop>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			if(shopIds.lastIndexOf(",")  == shopIds.length() -1){       //去掉最后的","
				shopIds = shopIds.substring(0, shopIds.length()-1);
			}
			String sql = "select * from youhui_datamining.tyh_tb_shop where `user_id` in ("+ shopIds +");";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				TaobaoShop shop = new TaobaoShop();
				shop.setAuction_count(rs.getLong("auction_count"));
				shop.setClickUrl(rs.getString("click_url"));
				shop.setCommissionRate(rs.getDouble("commission_rate"));
				shop.setSellerCredit(rs.getString("seller_credit"));
				shop.setSellerNick(rs.getString("seller_nick"));
				shop.setShopTitle(rs.getString("shop_title"));
				shop.setShopType(rs.getString("shop_type"));
				shop.setTotalAuction(rs.getString("total_auction"));
				shop.setUserId(rs.getString("user_id"));
				shops.add(shop);
			}
			
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return shops;
	}
	
	public static void main(String[] args) {
		System.out.println(ShopManager.getInstance().getShopsByShopIds("379833581").size());
	}
}
