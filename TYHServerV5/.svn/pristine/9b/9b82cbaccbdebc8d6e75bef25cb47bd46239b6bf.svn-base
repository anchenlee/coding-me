package cn.youhui.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.dao.MySQLDBIns;


public class TeJiaGoodsManager {
	private static Logger log = Logger.getLogger(TeJiaGoodsManager.class) ;
	
	private static TeJiaGoodsManager instance = null;
	public static TeJiaGoodsManager getInstance(){
		if(instance == null) instance = new TeJiaGoodsManager();
		return instance;
	}
	
	public boolean addItem(TeJiaGoodsBean bean){       
		boolean result = false;
		if(bean == null)
			return false;
		Connection conn = MySQLDBIns.getInstance().getConnection();    
		try {
			Statement s = conn.createStatement();
			String sql = "INSERT INTO `youhui_datamining`.`m_discount_products`"
					+ "(`item_id`,`title`,`keyword`,"
					+ "`show_index`,`price_high`,`price_low`"
					+ ",`discount`,`pic_url`,`status`"
					+ ",`click_url`,`update_time`,`commission`,`commission_rate`)VALUES('"
					+ bean.getItem_id()
					+ "','"
					+ bean.getTitle()
					+ "','"
					+ bean.getKeyword()
					+ "','"
					+ bean.getShow_index()
					+ "','"
					+ bean.getPrice_high()
					+ "','"
					+ bean.getPrice_low()
					+ "','"
					+ bean.getDiscount()
					+ "','"
					+ bean.getPic_url()
					+ "','"
					+ bean.getStatus()
					+ "','"
					+ bean.getClickURL()
					+ "','"
					+ System.currentTimeMillis()
					+ "','"
					+ bean.getCommission()
					+ "','"
					+ bean.getCommission_rate()
					+ "');";                 
			int i = s.executeUpdate(sql);           
			if (i > 0){
				result = true;             
				}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return result;
	}
	
	public boolean findItem(String itemId){       
		boolean result = false;
		Connection conn = MySQLDBIns.getInstance().getConnection();    
		try {
			Statement s = conn.createStatement();
			String sql = "select * from `youhui_datamining`.`m_discount_products` where `item_id`= "+ itemId;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				result = true;
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return result;
	}
	
}
