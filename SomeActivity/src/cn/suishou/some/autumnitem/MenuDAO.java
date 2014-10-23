/**
 * 
 */
package cn.suishou.some.autumnitem;

import java.sql.Connection;
import java.sql.PreparedStatement;

import cn.suishou.some.db.SQL;

/**
 * 栏目数据表操作
 * @author lujiabin
 * @since 2014-9-2
 */
public class MenuDAO {
	private static MenuDAO instance;
	
	public static MenuDAO getInstance() {
		return instance == null ? new MenuDAO() :instance;
	}
	
	/**
	 * 更新栏目点击数量
	 * @param menuid
	 */
	public void updateClickNum(String menuid) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = SQL.getInstance().getConnection();
			String sql = "UPDATE `youhui_luckac`.`autumn_item_menu` SET `click_num`=`click_num` + 1 WHERE `menu_id`= ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, menuid);
			ps.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}finally{
		    SQL.getInstance().release(ps, conn);
		}
	}
}
