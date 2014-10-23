package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.bean.ExchItem;
import cn.youhui.dao.MySQLDBIns;

public class ExchItemManager{

	protected static Logger log = Logger.getLogger(ExchItemManager.class);
	private static ExchItemManager instance = null;
	
	private ExchItemManager(){
	}
	
	public static ExchItemManager getInstance(){
		if(instance == null) instance = new ExchItemManager();
		return instance;
	}
	
	public boolean buy(String itemId, int count){
		boolean flag = false;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "update youhui_exchange.exch_item set exch_count = exch_count + ? where item_id = ?  and count >= exch_count + ?";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setInt(1, count);
			s.setString(2, itemId);
			s.setInt(3, count);
			int i = s.executeUpdate();
			if(i > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQLDBIns.getInstance().release(conn);
		return flag;
	}
	
	/**
	 * 
	 * @param itemId
	 * @return 剩下的数量
	 */
	public int getRemain(String itemId){
		int ret = 0;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select (count-exch_count) as remain from youhui_exchange.exch_item  where item_id = ?";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, itemId);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				ret = rs.getInt("remain");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQLDBIns.getInstance().release(conn);
		return ret;
	}
	
	public List<ExchItem> getAll(){
		List<ExchItem> list = new ArrayList<ExchItem>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select * from youhui_exchange.exch_item where status = 0 order by creat_time desc;";
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				ExchItem bean = new ExchItem();
                bean.setCount(rs.getInt("count"));
                bean.setCreatTime(rs.getLong("creat_time"));
                bean.setUpdateTime(rs.getLong("update_time"));
                bean.setDesc(rs.getString("desc"));
                bean.setExchCount(rs.getInt("exch_count"));
                bean.setIcon(rs.getString("icon"));
                bean.setIsVirtual(rs.getInt("is_virtual"));
                bean.setItemId(rs.getString("item_id"));
                bean.setPrice(rs.getFloat("price"));
                bean.setPromPrice(rs.getFloat("prom_price"));
                bean.setListTime(rs.getLong("list_time"));
                bean.setDelistTime(rs.getLong("delist_time"));
                bean.setPromStartTime(rs.getLong("prom_start_time"));
                bean.setPromEndTime(rs.getLong("prom_end_time"));
                bean.setStatus(rs.getInt("status"));
                bean.setVirtualType(rs.getString("virtual_type"));
				bean.setTitle(rs.getString("title"));
				bean.setVersion(rs.getInt("version"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQLDBIns.getInstance().release(conn);
		return list;
	}
	
	
	public int getTotal(){
		int size = 0;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select count(*) as counts from youhui_exchange.exch_item where status = 0;";
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if(rs.next()) {
				size = rs.getInt("counts");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQLDBIns.getInstance().release(conn);
		return size;
	}
	
	
}
