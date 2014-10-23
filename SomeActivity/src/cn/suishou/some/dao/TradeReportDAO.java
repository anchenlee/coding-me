package cn.suishou.some.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 订单
 * @author lijun
 * @since 2014-6-13
 */
public class TradeReportDAO {

	private static TradeReportDAO instance = null;
	
	private TradeReportDAO(){}

	public static TradeReportDAO getInstance(){
		if(instance == null){
			instance = new TradeReportDAO();
		}
		return instance;
	}
	
	/**
	 * 是否有购买记录
	 * @param uid
	 * @return
	 */
	public boolean hasTrade(String uid, Connection conn){
		boolean flag = false;
		try{
			String sql = "SELECT id FROM youhui_fanli.tyh_report_trade where uid = ? limit 0,1;";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, uid);
			ResultSet rs = state.executeQuery();
			if(rs.next()){
				flag = true;
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

}
