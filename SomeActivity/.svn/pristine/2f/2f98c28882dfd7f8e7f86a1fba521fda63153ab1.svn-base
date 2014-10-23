package cn.suishou.some.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.suishou.some.db.SQL;

/**
 * 集分宝帐户明细
 * @author lijun
 *
 */
public class JiFenBaoMXManager {

private static JiFenBaoMXManager instance = null;

    private static final int TYPE_FL = 1;    //返利
    private static final int TYPE_AC = 2;    //参加活动
    private static final int TYPE_TX = 3;    //提现集分宝
    private static final int TYPE_PURCHASE = 4;  //集分宝兑换购买
    
    private static final int STATUS_SUCCESS = 2;    //成功
    
	
	private JiFenBaoMXManager(){}
	
	public static JiFenBaoMXManager getInstance(){
		if(instance == null) instance = new JiFenBaoMXManager();
		return instance;
	}
	
	/**
	 * 集分宝余额
	 * @param uid
	 * @return
	 */
	public int getYue(String uid){
		int jfbNum = 0;
		if(uid == null || "".equals(uid))
			return jfbNum;
		Connection conn = null;
		try {
			conn = SQL.getInstance().getConnection();
			String uidp = uid.substring(0, 1);
			if(uidp.compareTo("9") > 0){
				return jfbNum;
			}
			String sql = "select sum(jfb_num) as sumjfb from youhui_fanli.tyh_trade_mx_"+ uidp +" where uid = ? and `status`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, STATUS_SUCCESS);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				jfbNum = rs.getInt("sumjfb");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(null, conn);
		}
		return jfbNum;
	}
	
}
