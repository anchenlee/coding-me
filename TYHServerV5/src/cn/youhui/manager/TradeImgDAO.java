package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TradeImgDAO {

private static TradeImgDAO instance;
	
	private TradeImgDAO(){}
	
	public static TradeImgDAO getInstance(){
		if(instance == null){
			instance = new TradeImgDAO();
		}
		return instance;
	}
	
	public String getTradeImg(String tradeId, Connection conn){
		PreparedStatement ps = null;
		String img = "";
		try{
			String sql = "select img from `youhui_fanli`.`trade_img` where `trade_id`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tradeId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				img = rs.getString("img");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return img;
	}
}
