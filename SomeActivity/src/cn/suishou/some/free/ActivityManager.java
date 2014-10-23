package cn.suishou.some.free;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.suishou.some.db.SQL;
/**
 * 
 * @author hufan
 * @since 2014-8-15
 */
public class ActivityManager {

	/**
	 * 通过活动Id得到活动标题图片
	 * @param a_id
	 * @return
	 */
	public static String getActivityImgByActivityId(String a_id){
		String url="";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=SQL.getInstance().getConnection();
			String sql="select `activity_img_url` from `youhui_luckac`.`free_activity_message` where `activity_id`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, a_id);
			rs=ps.executeQuery();
			while(rs.next()){
				url=rs.getString("activity_img_url");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, con);
		}		
		return url;
	}
	

	/**
	 * 通过商品Id得到商品图片
	 * @param item_id
	 * @return
	 */
	public static String getItemImgByActivityId(String a_id){
		String url="";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=SQL.getInstance().getConnection();
			String sql="select `item_img_url` from `youhui_luckac`.`free_activity_message` where `activity_id`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, a_id);
			rs=ps.executeQuery();
			while(rs.next()){
				url=rs.getString("item_img_url");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, con);
		}		
		return url;
	}
	
	/**
	 * 通过商品Id得到商品的描述信息
	 * @param item_id
	 * @return
	 */
	public static String getItemdescribeByActivityId(String a_id){
		String url="";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=SQL.getInstance().getConnection();
			String sql="select `item_describe` from `youhui_luckac`.`free_activity_message` where `activity_id`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, a_id);
			rs=ps.executeQuery();
			while(rs.next()){
				url=rs.getString("item_describe");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, con);
		}		
		return url;
	}
	
	/**
	 * 通过活动Id得到商品Id
	 * @param a_id
	 * @return
	 */
	public static String getItemidByActivityId(String a_id){
		String itemId="";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=SQL.getInstance().getConnection();
			String sql="select `item_id` from `youhui_luckac`.`free_activity_message` where `activity_id` =?";
			ps=con.prepareStatement(sql);
			ps.setString(1, a_id);
			rs=ps.executeQuery();
			while(rs.next()){
				itemId=rs.getString("item_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, con);
		}		
		return itemId;
	}
	
}
