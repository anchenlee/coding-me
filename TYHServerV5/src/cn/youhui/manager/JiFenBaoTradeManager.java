package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.youhui.bean.JiFenBaoTrade;
import cn.youhui.common.JiFenBaoErrorCode;
import cn.youhui.dao.MySQLDBIns;

public class JiFenBaoTradeManager {
	public static final int Status_UnInit = 9;        //订单中没有支付宝帐号，不能发送
	public static final int Status_Review = 7;        //订单异常，待审核
	public static final int Status_Init = 0;          //准备发送
	public static final int Status_Waiting = -1;       //等待检查订单，修改状态
	public static final int Status_Success = 1;
	public static final int Status_Fail = 2;
	public static final int Status_HasReSend = 3;
	public static final int Status_Disposeing = 10;    //标记正在处理，以免重复提交
	
	public static final int Status_InBlackList = 12;    //在黑名单中
	
	public static final int max_jfbnum_per_day = 1000;   //一天每个支付宝能获取的集分宝数
	
	private static final int pageSize = 20;
	
	private static JiFenBaoTradeManager instance = null;
	
	private JiFenBaoTradeManager(){}
	
	public static JiFenBaoTradeManager getInstance(){
		if(instance == null) instance = new JiFenBaoTradeManager();
		return instance;
	}
	
	
	public List<JiFenBaoTrade> getTradeList(String uid, int page){
		List<JiFenBaoTrade> list = new ArrayList<JiFenBaoTrade>();
		Connection conn = null;
		try {
			if(page < 1){
				page = 1;
			}
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select 2 as `type`,`jfb_num`*100 as `je`, `zfb`, `status`, `error`, `apply_time`,`trade_id` as id from `youhui_cn_fanli`.`tyh_jifenbao_tradelist` where `uid`=? union select 1 as `type`,`txje`*100 as `je`,`zfb` ,`txstate` as `status`, `why` as `error`, `applytime` as `apply_time`, `id` from `youhui_cn_fanli`.`duoduo_tixian` where `ddusername`=? order by `apply_time` desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, uid);
			s.setInt(3, (page-1)*pageSize);
			s.setInt(4, pageSize);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				JiFenBaoTrade trade = new JiFenBaoTrade();
				trade.setType(rs.getInt("type"));
				trade.setJiFenBaoNum(rs.getInt("je"));
				trade.setZfb(rs.getString("zfb"));
				int status = rs.getInt("status");
//				if(status != 1 && status !=2){
//					status = 0;
//				}
				trade.setStatus(status);
				trade.setError(rs.getString("error"));
				trade.setTimestamp(rs.getLong("apply_time"));
				list.add(trade);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public int getPageNum(String uid){
		int totalPage = 0;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(*) as acount from `youhui_cn_fanli`.`tyh_jifenbao_tradelist` where `uid`=? union select count(*) as acount from `youhui_cn_fanli`.`duoduo_tixian` where `ddusername`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, uid);
			ResultSet rs = s.executeQuery();
			int count = 0;
			while(rs.next()){
				count += rs.getInt("acount");
			}
			int ys = count%pageSize;
			totalPage = (count-ys)/pageSize;
			if(ys > 0)
				totalPage ++;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return totalPage;
	}
	
	/**
	 * 停用
	 * @param uid
	 * @param zfb
	 */
	public void grantJFB(String uid, String zfb){
		if(uid == null || "".equals(uid) || zfb == null || "".equals(zfb))
			return;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "update youhui_cn_fanli.tyh_jifenbao_tradelist set `zfb`=?,`status`=? ,`timestamp`=? where `uid` = ? and `status` = ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, zfb);
			s.setInt(2, Status_Init);
			s.setLong(3, System.currentTimeMillis());
			s.setString(4, uid);
			s.setInt(5, Status_UnInit);
			s.executeUpdate();
			
			//重新发送因为支付宝帐号错误而失败的订单，生成一条新的订单，改变tradeid,修改原来订单状态为已重发(3)
			sql = "select * from youhui_cn_fanli.tyh_jifenbao_tradelist where `uid` = ? and `status` = ? and `error`=?;";
			s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, Status_Fail);
			s.setString(3, JiFenBaoErrorCode.USER_NOT_EXIST);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				JiFenBaoTrade jfbb = new JiFenBaoTrade();
				jfbb.setFrom(rs.getString("from"));
				jfbb.setFromId(rs.getString("from_id"));
				jfbb.setJiFenBaoNum(rs.getInt("jfb_num"));
				jfbb.setStatus(Status_Init);
				String newTradeId = getNewTradeId(rs.getString("trade_id"));
				jfbb.setTradeId(newTradeId);
				jfbb.setUid(uid);
				jfbb.setZfb(zfb);
				updateStatus(rs.getString("trade_id"), Status_HasReSend, conn);
//				add(jfbb, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
	}

	/**
	 * 根据旧tradeid获取新的
	 * @param oldTradeId
	 * @return
	 */
	private String getNewTradeId(String oldTradeId){
		char lastchar = oldTradeId.charAt(oldTradeId.length()-1);
		if((lastchar <= 57 && lastchar>=48)|| lastchar == 122){
			return oldTradeId + "a";
		}else{
			char nextChar = (char) (lastchar + 1);
			return oldTradeId.substring(0, oldTradeId.length()-1) + nextChar;
		}
	}
	
	/**
	 * 根据新tradeid获取生成它的tradeid
	 * @param oldTradeId
	 * @return
	 */
	private String getOldTradeId(String newTradeId){
		char lastchar = newTradeId.charAt(newTradeId.length()-1);
		if(lastchar <= 57 && lastchar>=48){
			return newTradeId;
		}else if(lastchar == 97){
			return newTradeId.substring(0, newTradeId.length()-1);
		}else{
			char nextChar = (char) (lastchar - 1);
			return newTradeId.substring(0, newTradeId.length()-1) + nextChar;
		}
	}
	
	private boolean updateStatus(String tradeId, int status, Connection conn){
		boolean flag = false;
		PreparedStatement ps;
		try {
			String sql = "update youhui_cn_fanli.tyh_jifenbao_tradelist set `status`=? where `trade_id`=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, tradeId);
			int i = ps.executeUpdate();
			if(i > 0)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public Map<String, Integer> getStatusold(List<String> fromIds, String from, Connection conn){
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			String sql = "select `status` from youhui_cn_fanli.tyh_jifenbao_tradelist  where `from` = ? and `from_id`=? and status <> ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			if(fromIds != null)
				for(String fromId : fromIds){
					s.setString(1, from);
					s.setString(2, fromId);
					s.setInt(3, Status_HasReSend);
					ResultSet rs = s.executeQuery();
					if(rs.next()){
						int status = rs.getInt("status");
						if(status == Status_Init || status == Status_UnInit || status == Status_Disposeing){
							status = Status_Init;
						}
						map.put(fromId, status);
					}
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Map<String, Integer> getStatus(List<String> tradeIds, Connection conn){
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			String sql = "select `status` from youhui_cn_fanli.tyh_jifenbao_tradelist  where `trade_id`=? and status <> ?;";
			PreparedStatement s = conn.prepareStatement(sql);
			if(tradeIds != null)
				for(String tradeId : tradeIds){
					s.setString(1, tradeId);
					s.setInt(2, Status_HasReSend);
					ResultSet rs = s.executeQuery();
					if(rs.next()){
						int status = rs.getInt("status");
						if(status == Status_Init || status == Status_UnInit || status == Status_Disposeing){
							status = Status_Init;
						}
						map.put(tradeId, status);
					}
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 提现集分宝个数 0成功，1审核中
	 * @param conn
	 * @param uid
	 * @return
	 */
	public int[] getTXJfbNum(Connection conn, String uid){
		int jfbNum[] = {0,0};
		PreparedStatement ps;
		try {
			String sql = "select sum(jfb_num)as sum_jfb from youhui_cn_fanli.tyh_jifenbao_tradelist where `uid` = ? and (`status` = ? or `status` = ? or `status` = ? or `status`=? or `status`=?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, Status_Review);
			ps.setInt(3, Status_Disposeing);
			ps.setInt(4, Status_Init);
			ps.setInt(5, Status_Waiting);
			ps.setInt(6, Status_InBlackList);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				jfbNum[1] = rs.getInt("sum_jfb");
			}
			sql = "select sum(jfb_num)as sum_jfb from youhui_cn_fanli.tyh_jifenbao_tradelist where `uid` = ? and `status` = ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, Status_Success);
			rs = ps.executeQuery();
			if(rs.next()){
				jfbNum[0] = rs.getInt("sum_jfb");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jfbNum;
	}
	
	public static void main(String[] args) {
		System.out.println(JiFenBaoTradeManager.getInstance().getTradeList("32870501", 1).get(2).getTimestamp());
	}
}
