/**
 * 
 */
package cn.suishou.some.autumnitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.suishou.some.db.SQL;

/**
 * 商品数据表操作
 * @author lujiabin
 * @since 2014-9-2
 */
public class ItemDAO {
	private static ItemDAO instance;
	
	public static ItemDAO getInstance() {
		return instance == null ? new ItemDAO() : instance;
	}
	
	/**
	 * 获取对应栏目下商品列表
	 * @param uid
	 * @param menuid
	 * @return 商品列表
	 */
	public List<ItemBean> GetItemList(String uid,String menuid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ItemBean> list = new ArrayList<ItemBean>();
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "SELECT * FROM `youhui_luckac`.`autumn_item_products` where `menu_id`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1,menuid);
			rs = ps.executeQuery();
			while(rs.next()) {
				ItemBean item = new ItemBean();
				item.setItemId(rs.getString("item_id"));
				item.setTitle(rs.getString("title"));
				item.setClickNum(rs.getInt("click_num"));
				item.setClickUrl(rs.getString("click_url"));
				item.setPicUrl(rs.getString("pic_url"));
				item.setPriceNow(rs.getString("price_now"));
				item.setPriceOriginal(rs.getString("price_original"));
				//根据用户点击商品记录设置点赞按钮状态
				item.setStatus(RecordDAO.getInstance().getLikeStatus(uid, item.getItemId()));
				list.add(item);
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(ps, conn);
		}
		
		return list;
	}
	
	/**
	 * 更新商品点赞数量
	 * @param itemid
	 */
	public void updateClickNum(String itemid,int addNum) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "UPDATE `youhui_luckac`.`autumn_item_products` SET `click_num`=`click_num` + ? WHERE `item_id` = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,addNum);
			ps.setString(2, itemid);
			ps.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(ps, conn);
		}
	}
	
	/**
	 * 获取一件商品的赞数
	 * @param itemid
	 * @return 赞数
	 */
	public int getLikeNum(String itemid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "SELECT `click_num` FROM `youhui_luckac`.`autumn_item_products` where `item_id`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemid);
			rs = ps.executeQuery();
			if(rs.next()) {
				num = rs.getInt("click_num");
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(ps, conn);
		}
		
		return num;
	}
	public int getLikeNumToday(String itemid) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int num = 0;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "SELECT count(id)as c FROM youhui_luckac.autumn_item_product_record where update_time>? and `item_id`=?;";
			ps = conn.prepareStatement(sql);
			ps.setLong(1,sdf.parse(sdf.format(new Date().getTime())).getTime());
			ps.setString(2, itemid);
			rs = ps.executeQuery();
			if(rs.next()) {
				num = rs.getInt("c");
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(ps, conn);
		}
		
		return num;
	}
	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(sdf.parse(sdf.format(new Date().getTime())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
