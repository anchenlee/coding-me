package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.bean.UidToItemId;
import cn.youhui.dao.MySQLDBIns;


public class ViewItemManager {
	private static Logger log = Logger.getLogger(ViewItemManager.class) ;
	
	private static ViewItemManager instance = null;
	public static ViewItemManager getInstance(){
		if(instance == null) instance = new ViewItemManager();
		return instance;
	}
	
	public boolean addViewItem(String uid ,String item_id){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		ResultSet rs = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "SELECT * FROM youhui_datamining.user_view_item WHERE uid=? and item_id=?";
			statement1 = conn.prepareStatement(sql1);
			statement1.setString(1, uid);
			statement1.setString(2, item_id);
			rs = statement1.executeQuery();
			if(!rs.next()) {
				String sql = "insert into youhui_datamining.user_view_item (uid,item_id,timestamp,count,status)values(?,?,?,?,?)";
				statement = conn.prepareStatement(sql);
				statement.setString(1, uid);
				statement.setString(2, item_id);
				statement.setLong(3, System.currentTimeMillis());
				statement.setInt(4, 1);
				statement.setInt(5, 1);
				int i = statement.executeUpdate();
				if(i > 0) flag = true;
			}
			else {
				String sql = "update youhui_datamining.user_view_item set timestamp=? , count=count+1,status=? WHERE uid=? and item_id=?  ";
				statement = conn.prepareStatement(sql);
				statement.setLong(1, System.currentTimeMillis());
				statement.setInt(2, 1);
				statement.setString(3, uid);
				statement.setString(4, item_id);
				int i = statement.executeUpdate();
				if(i > 0) flag = true;
			}
		}catch(SQLException e){
			log.error("Sql Execution Error!", e);
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	

	public boolean delViewItem(String uid, String item_id){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "update  youhui_datamining.user_view_item set status=0 where uid = ? and item_id=?";
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, uid);
			statement.setString(2, item_id);
			int i =  statement.executeUpdate();
			if(i> 0) flag = true;
		}catch(SQLException e){
			log.error("Sql Execution Error!", e);
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public List<UidToItemId> getViewUid2Item(){
		List<UidToItemId> list = new ArrayList<UidToItemId>();
		String sql = "select * from youhui_datamining.user_view_item where status = 1";
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			rs = conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				UidToItemId iti = new UidToItemId();
				iti.setUid(rs.getString("uid"));
				iti.setItemId(rs.getString("item_id"));
				iti.setTimestamp(rs.getLong("timestamp"));
				list.add(iti);
			}
		} catch(SQLException e){
			log.error(e, e);
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public List<UidToItemId> getViewUid2Item(String uid){
		List<UidToItemId> list = new ArrayList<UidToItemId>();
		String sql = "select * from youhui_datamining.user_view_item where status = 1 and uid = ?;";
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, uid);
			rs = statement.executeQuery();
			while(rs.next()) {
				UidToItemId iti = new UidToItemId();
				iti.setUid(rs.getString("uid"));
				iti.setItemId(rs.getString("item_id"));
				iti.setTimestamp(rs.getLong("timestamp"));
				list.add(iti);
			}
		} catch(SQLException e){
			log.error(e, e);
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
}
