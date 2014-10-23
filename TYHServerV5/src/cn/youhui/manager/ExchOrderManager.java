package cn.youhui.manager;

/**
 * @author leejun
 * @since 2012-11-26
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.bean.ExchOrder;
import cn.youhui.dao.MySQLDBIns;

public class ExchOrderManager {
	
	protected static Logger log = Logger.getLogger(ExchOrderManager.class);
	private static ExchOrderManager instance = null;
	private static SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final int PageSize = 3;
	private ExchOrderManager(){
	}
	
	public static ExchOrderManager getInstance(){
		if(instance == null) instance = new ExchOrderManager();
		return instance;
	}
	

	public String add(ExchOrder bean){
		String orderId = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "INSERT INTO `youhui_exchange`.`exch_order`(`address_id`,`count`,`creat_time`,`creat_time_date`,`item_id`," +
					"`price`,`prom_price`,`remark`,`status`,`uid`,`virtual_number`,`virtual_type`,`addinfo`,item_version) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, bean.getAddressId());
			s.setInt(2, bean.getCount());
			Date now = new Date();
			s.setLong(3, now.getTime());
			s.setString(4, sdft.format(now));
			s.setString(5, bean.getItemId());
			s.setFloat(6, bean.getPrice());
			s.setFloat(7, bean.getPromPrice());
			s.setString(8, bean.getRemark());
			s.setInt(9, 0);
			s.setString(10, bean.getUid());
			s.setString(11, bean.getVirtualNumber());
			s.setString(12, bean.getVirtualType());
			s.setString(13, bean.getAddinfo());
			s.setInt(14, bean.getItemVersion());
			int i = s.executeUpdate();
			if(i > 0){
			ResultSet rs = conn.createStatement().executeQuery("SELECT LAST_INSERT_ID();");
			if(rs.next()){
				orderId = rs.getString(1);
			}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}finally{
			if(conn!=null){
				MySQLDBIns.getInstance().release(conn);
			}
		}
		return orderId;
	}
	
	public boolean del(String orderId){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "DELETE FROM `youhui_exchange`.`exch_order` WHERE order_id =?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, orderId);
			int i = s.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}finally{
			if(conn!=null){
				MySQLDBIns.getInstance().release(conn);
			}
		}
		return flag;
	}
	
	public int getTotalPage(String uid){
		int size = 1; 
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select count(*) as counts from `youhui_exchange`.`exch_order` where uid =?";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("counts");
				size = count/PageSize;
				if(size*PageSize < count) size++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQLDBIns.getInstance().release(conn);
		return size;
	}
	
	public List<ExchOrder> getbyUid(String uid , int page){
		List<ExchOrder> list = new ArrayList<ExchOrder>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "SELECT * FROM (select * from youhui_exchange.exch_order where uid =? order by creat_time desc)as exch_order left join youhui_exchange.exch_item_version on exch_order.item_id=exch_item_version.item_id and exch_order.item_version = exch_item_version.version left JOIN youhui_exchange.exch_address ON exch_order.address_id=exch_address.address_id limit "+(page-1)*PageSize+","+page*PageSize;
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				ExchOrder bean = new ExchOrder();
				bean.setAddressId(rs.getString("address_id"));
				bean.setCount(rs.getInt("count"));
				bean.setCreatTime(rs.getLong("creat_time"));
				bean.setItemId(rs.getString("item_id"));
				bean.setOrderId(rs.getString("order_id"));
				bean.setPrice(rs.getFloat("price"));  
				bean.setPromPrice(rs.getFloat("prom_price"));
				bean.setRemark(rs.getString("remark"));
				bean.setStatus(rs.getInt("status"));
				bean.setUid(rs.getString("uid"));
				bean.setVirtualNumber(rs.getString("virtual_number"));
				bean.setVirtualType(rs.getString("virtual_type"));
				bean.setAddinfo(rs.getString("addinfo"));
				bean.getItem().setItemId(rs.getString("item_id"));
				bean.getItem().setTitle(rs.getString("title")); 
				bean.getItem().setIcon(rs.getString("icon"));
				bean.getItem().setDesc(rs.getString("desc"));
				bean.getItem().setPromPrice(rs.getFloat("prom_price"));
				bean.getItem().setPrice(rs.getFloat("price"));
				bean.getItem().setIsVirtual(rs.getInt("is_virtual"));
				bean.getItem().setVirtualType(rs.getString("virtual_type"));
				if(rs.getInt("is_virtual") == 0){
					bean.getAddre().setAddressId(rs.getString("address_id"));
					bean.getAddre().setRecName(rs.getString("rec_name"));
					bean.getAddre().setRecTel(rs.getString("rec_tel"));
					bean.getAddre().setPostCode(rs.getString("postcode"));
					bean.getAddre().setProvince(rs.getString("province"));
					bean.getAddre().setCity(rs.getString("city"));
					bean.getAddre().setDistrict(rs.getString("district"));
					bean.getAddre().setAddress(rs.getString("address"));
				}
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQLDBIns.getInstance().release(conn);
		return list;
	}
	
	
}
