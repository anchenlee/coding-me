/**
 * 
 */
package cn.suishou.some.autumnitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.suishou.some.db.SQL;

/**
 * 用户点击记录数据表操作
 * @author lujiabin
 * @since 2014-9-2
 */
public class RecordDAO {
	private static RecordDAO instance;
	
	public static RecordDAO getInstance() {
		return instance == null ? new RecordDAO() : instance;
	}
	
	/**
	 * 保存用户点击栏目记录
	 * @param uid
	 * @param menuid
	 */
	public void saveClickMenuInfo(String uid ,String menuid) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "INSERT INTO `youhui_luckac`.`autumn_item_menu_record` (`uid`, `menu_id`, `update_time`) VALUES (?, ?, ?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, menuid);
			ps.setLong(3, System.currentTimeMillis());
			ps.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(ps, conn);
		}
	}
	
	/**
	 * 保存用户点赞记录
	 * @param uid
	 * @param itemid
	 */
	public void saveClickLikeInfo(String uid ,String itemid) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "INSERT INTO `youhui_luckac`.`autumn_item_product_record` (`uid`, `item_id`, `update_time`) VALUES (?, ?, ?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, itemid);
			ps.setLong(3, System.currentTimeMillis());
			ps.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(ps, conn);
		}
	}
	
	/**
	 * 根据用户点赞记录表获取一件商品的点赞状态
	 * @param uid
	 * @param itemid
	 * @return 0：未点赞 1：已点赞
	 */
	public int getLikeStatus(String uid ,String itemid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		int status = 0;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "SELECT * FROM youhui_luckac.autumn_item_product_record where `uid`=? and `item_id`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, itemid);
			rs = ps.executeQuery();
			if(rs.next()) {
				status = 1;
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(ps, conn);
		}
		return status;
	}
	
	/**
	 * 获取从第一个点赞时间到现在的小时数（四舍五入）
	 * @param itemid
	 * @return
	 */
	public int getHour(String itemid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long now = System.currentTimeMillis();//当前时间
		long time = 0;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "SELECT * FROM `youhui_luckac`.`autumn_item_product_record` where `item_id`=? order by id;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemid);
			rs = ps.executeQuery();
			if(rs.next()) {
				time = rs.getInt("update_time");
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(ps, conn);
		}
		int hour = (int) (now - time)/3600000;
		//四舍五入
		if((now-time)%3600000 > 1800000) {
			hour++;
		}
		return hour<=0?1:hour;
	}
	
	/*
	 * 
	 * 获取到目前为止该商品每小时的点赞人次
	 */
	public int getUserNum(String itemid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int  tmp = 0;
		int userNumPerHour=1;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "SELECT count(*)as c FROM `youhui_luckac`.`autumn_item_product_record` where `item_id`=? ;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemid);
			rs = ps.executeQuery();
			if(rs.next()) {
				tmp = rs.getInt("c");
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(ps, conn);
		}
		int hour=getHour(itemid);
		if(tmp%hour==0){
			userNumPerHour=tmp/hour;
		}else{
			userNumPerHour=tmp/hour+1;
		}
		return userNumPerHour;
	}
}
