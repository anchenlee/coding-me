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

public class UserEstimateManager {

	/**
	 * 添加用户对商品的评价
	 * @param uid
	 * @param itemId
	 * @return
	 */
	public static boolean addUserEstimamteByUidItemId(UserEstimate uem){
		boolean flag=false;
		PreparedStatement ps=null;
		Connection con=null;
		
		try {
			con=SQL.getInstance().getConnection();
			String sql="insert into `youhui_luckac`.`free_user_estimate` (`uid`,`taobao_nick`,`activity_id`,`u_estimate`,`estimate_time`,`star_num`) values(?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, uem.getUid());
			ps.setString(2, uem.getTaobaoNick());
			ps.setString(3, uem.getActivityId());
			ps.setString(4, uem.getEstimate());
			ps.setLong(5, uem.getTime());
			ps.setInt(6, uem.getStarNum());
			
			if(ps.executeUpdate()>0){
				flag=true;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(ps, con);
		}		
		
		return flag;
	}
}
