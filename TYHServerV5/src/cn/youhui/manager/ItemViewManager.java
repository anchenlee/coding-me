package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.youhui.bean.ItemView;
import cn.youhui.dao.MySQLDBIns;

/**
 * 商品浏览记录
 * @author lijun
 * @since 2013-12-20
 */
public class ItemViewManager {
	
	private static ItemViewManager instance = null;
	
	public static ItemViewManager getInstance(){
		if(instance == null) instance = new ItemViewManager();
		return instance;
	}
	
	public void add(ItemView iv){
		Connection conn = null;
		PreparedStatement statement = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_view.item_view (uid,item_id,see_rate,from_channel,from_value,timestamp)values(?,?,?,?,?,?)";
			statement = conn.prepareStatement(sql);
			statement.setString(1, iv.getUid());
			statement.setString(2, iv.getItemId());
			statement.setDouble(3, iv.getSeeRate());
			statement.setString(4, iv.getFromChannel());
			statement.setString(5, iv.getFromValue());
			statement.setLong(6, System.currentTimeMillis());
			statement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
	}
	
}
