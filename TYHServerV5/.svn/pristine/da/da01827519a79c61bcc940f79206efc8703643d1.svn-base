/**
 * 
 */
package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.youhui.bean.TradeOrder;

/**
 * 操作订单数据
 * @author lujiabin
 * @since 2014-9-19
 */
public class TradeOrderInfoDAO {
	private static TradeOrderInfoDAO instance;
	
	public static TradeOrderInfoDAO getInstance(){
		return instance == null ? new TradeOrderInfoDAO() : instance;
	}
	
	/**
	 * 保存订单记录
	 * @param bean
	 * @return
	 */
	public boolean saveTradeOrder(TradeOrder bean){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `tradefind`.`tb_tradeorder` (`order_id`, `item_id`, `price`, `img_url`, `title`, `num`, `status`, `shop_url`, `create_timestamp`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getOrderId());
			ps.setString(2, bean.getItemId());
			ps.setString(3, bean.getPrice());
			ps.setString(4, bean.getImgUrl());
			ps.setString(5, bean.getTitle());
			ps.setString(6, bean.getNum());
			ps.setString(7, bean.getStatus());
			ps.setString(8, bean.getShopUrl());
			ps.setLong(9, System.currentTimeMillis());
			if(ps.executeUpdate()>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		
		return flag;
	}
	
	/**
	 * 更新订单状态
	 * @param orderId
	 * @param status
	 */
	public void update(String orderId,String status){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `tradefind`.`tb_tradeorder` SET `status`=?, `update_timestamp`=? WHERE `order_id`=?;";
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setLong(2, System.currentTimeMillis());
			ps.setString(3, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
	}
	
	/**
	 * 查看订单记录是否存在
	 * @param orderId
	 * @return
	 */
	public boolean isOrderExist(String orderId){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "SELECT `item_id` FROM `tradefind`.`tb_tradeorder` WHERE `order_id`=?;";
		boolean flag = false;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
}
