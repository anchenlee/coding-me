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

public class LikeItemManager {
private static Logger log = Logger.getLogger(LikeItemManager.class) ;
	
	private static LikeItemManager instance = null;
	public static LikeItemManager getInstance(){
		if(instance == null) instance = new LikeItemManager();
		return instance;
	}
	
	public boolean addLikeItem(String uid ,String item_id){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		ResultSet rs = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql1 = "SELECT * FROM youhui_datamining.user_like_item WHERE uid=? and item_id=?";
			statement1 = conn.prepareStatement(sql1);
			statement1.setString(1, uid);
			statement1.setString(2, item_id);
			rs = statement1.executeQuery();
			if(!rs.next()) {
				String sql = "insert into youhui_datamining.user_like_item (uid,item_id,timestamp,status)values(?,?,?,?)";
				statement = conn.prepareStatement(sql);
				statement.setString(1, uid);
				statement.setString(2, item_id);
				statement.setLong(3, System.currentTimeMillis());
				statement.setInt(4, 1);
				int i = statement.executeUpdate();
				if(i > 0) flag = true;
			}else {
				String sql = "update youhui_datamining.user_like_item set timestamp=? , status=? WHERE uid=? and item_id=?  ";
				statement = conn.prepareStatement(sql);
				statement.setLong(1, System.currentTimeMillis());
				statement.setInt(2, 1);
				statement.setString(3, uid);
				statement.setString(4, item_id);
				int i = statement.executeUpdate();
				if(i > 0) flag = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
			log.error("Sql Execution Error!", e);
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	

	public boolean delLikeItem(String uid, String item_id){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "update  youhui_datamining.user_like_item set status=0 where uid = ? and item_id=?";
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, uid);
			statement.setString(2, item_id);
			int i =  statement.executeUpdate();
			if(i> 0) flag = true;
		}catch(SQLException e){
			e.printStackTrace();
			log.error("Sql Execution Error!", e);
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 用户是否喜欢该商品
	 * @param uid
	 * @param item_id
	 * @return
	 */
	public boolean isLikeItem(String uid, String itemId){
		boolean flag = false;
		if(uid == null || "".equals(uid)){
			return flag;
		}
		Connection conn = null;
		PreparedStatement statement = null;
		String sql = "select uid from  youhui_datamining.user_like_item where uid = ? and item_id=? and `status` = 1;";
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, uid);
			statement.setString(2, itemId);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
			log.error("Sql Execution Error!", e);
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public List<UidToItemId> getLikeUid2Item(){
		List<UidToItemId> list = new ArrayList<UidToItemId>();
		String sql = "select * from youhui_datamining.user_like_item where status = 1";
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
			e.printStackTrace();
			log.error(e, e);
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public List<UidToItemId> getLikeUid2Item(String uid){
		List<UidToItemId> list = new ArrayList<UidToItemId>();
		String sql = "select * from youhui_datamining.user_like_item where status = 1 and uid = ?;";
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
			e.printStackTrace();
			log.error(e, e);
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
}
