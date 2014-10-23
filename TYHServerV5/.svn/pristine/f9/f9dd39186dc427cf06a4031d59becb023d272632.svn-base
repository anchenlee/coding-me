package cn.youhui.util.oufeicz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import cn.youhui.dao.MySQLDBIns;

public class OufeiCZDAO {
	
	private static final int STATUS_INIT = 0;      //初始
	private static final int STATUS_SUCCESS = 1;   //成功
	private static final int STATUS_FAIL = 2;      //失败
	private static final int STATUS_UNDO = 3;      //撤销
	
	DateFormat df = new SimpleDateFormat("yyyyMMdd");
	DateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static OufeiCZDAO instance = null;
	
	public static OufeiCZDAO getInstance(){
		if(instance == null){
			instance = new OufeiCZDAO();
		}
		return instance;
	}
	
	public boolean add(OufeiCZ of){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			String orderId = getRandOrderId(of.getPhoneNum());
			of.setOrdreId(orderId);
			Date date = new Date();
			String time =  df2.format(date);
			of.setTimeStr(time);
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into `youhui_cn_fanli`.`oufei_order` (`order_id`,`time_str`,`phone_num`,`num`,`status`,`update_time`,`ac_id`,`ac_unique_id`) " +
					"values (?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderId);
			ps.setString(2, time);
			ps.setString(3, of.getPhoneNum());
			ps.setInt(4, of.getNum());
			ps.setInt(5, STATUS_INIT);
			ps.setLong(6, date.getTime());
			ps.setString(7, of.getAcId());
			ps.setString(8, of.getAcUniqueId());
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return flag;
	}
	
	/**
	 * 更新订单状态
	 * @param orderId
	 * @param status
	 */
	public void updateStatus(OufeiCZ of){
		Connection conn = null;
		PreparedStatement ps = null;
		int status = STATUS_FAIL;
		if (of.getCpStatus() == 1) {
			status = STATUS_SUCCESS;
		}else if(of.getCpStatus() == 9){
			status = STATUS_UNDO;
		}
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update `youhui_cn_fanli`.`oufei_order` set `status`=?,`ordercash`=?,`cp_order_id`=?,`cp_status`=? where order_id = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, of.getOrderCash());
			ps.setString(3, of.getCpOrderId());
			ps.setInt(4, of.getCpStatus());
			ps.setString(5, of.getOrdreId());
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, conn);
		}
	}
	
	/**
	 * 获取随机订单号
	 * @return
	 */
	private String getRandOrderId(String phoneNum){
		return df.format(new Date()) + phoneNum.substring(phoneNum.length() - 4, phoneNum.length()) + getRandNum(8);
	}
	
	private String getRandNum(int size){
		StringBuffer sb = new StringBuffer();
		String num[] = {"0","1","2","3","4","5","6","7","8","9"};
		int numSize = num.length;
		Random rm = new Random();
		for(;size > 0; size--){
			sb.append(num[rm.nextInt(numSize)]);
		}
		return sb.toString();
	}
	
	@SuppressWarnings("null")
	public List<OufeiCZ> getAllFalseOrderIds(){
		List<OufeiCZ> list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select phone_num,num,order_id,time_str from `youhui_cn_fanli`.`oufei_order`  where status = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_FAIL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				OufeiCZ oc = new OufeiCZ();
				oc.setPhoneNum(rs.getString("phone_num"));
				oc.setNum(rs.getInt("num"));
				oc.setOrdreId(rs.getString("order_id"));
				oc.setTimeStr(rs.getString("time_str"));
				list.add(oc);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, conn);
		}
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(OufeiCZDAO.getInstance().getRandOrderId("15850673417"));
	}
}
