package cn.youhui.api.huafei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.youhui.dao.MySQLDBIns;

public class HuafeiCZDAO {
	
	private static SimpleDateFormat ft1 = new SimpleDateFormat("yyyyMMdd");

	private static final int STATUS_INIT = 0;
	private static final int STATUS_SUCCESS = 1;
	private static final int STATUS_FAIL = 2;
	
	private static HuafeiCZDAO instance = null;
	
	public static HuafeiCZDAO getInstance(){
		if(instance == null){
			instance = new HuafeiCZDAO();
		}
		return instance;
	}
	
	/**
	 * 添加
	 * @param hf
	 * @return
	 */
	public boolean add(HuafeiCZ hf){
		int i=0;
		while(i<10){
			if(addHuafeiCZ(hf)){
				return true;
			}else{
				i++;
			}
		}
		return false;
	}
	
	
	/**
	 * 通过trade_id 得到HuafeiCZ
	 * @param trade_id
	 * @return
	 */
	public HuafeiCZ getHuafeiCZbyTradeId(String trade_id){
		HuafeiCZ hf=null;
		Connection conn=null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select trade_id,uid,phone_num,cz_num,`create_time` from `youhui_cn_fanli`.`huafei_cz` where `trade_id`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, trade_id);
			ResultSet rs=s.executeQuery();
			while(rs.next()){
				hf=new HuafeiCZ();
				hf.setTradeId(rs.getString("trade_id"));
				hf.setUid(rs.getString("uid"));
				hf.setCzNum(rs.getInt("cz_num"));
				hf.setPhoneNum(rs.getString("phone_num"));
				hf.setCreateTime(rs.getLong("create_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return hf;
	}
	
	/**
	 * 通过uid得到所有不重复的phone_num
	 * @param uid
	 * @return
	 */
	public List<String> getPhoneNumByUid(String uid){
		List<String> phoneNumList=new ArrayList<String>();
	//	System.out.println("进入phone");
		Connection conn=null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select phone_num,create_time from `youhui_cn_fanli`.`huafei_cz` where `uid`=? order by `id` desc;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			ResultSet rs=s.executeQuery();
			while(rs.next()){
				if(!hasExist(phoneNumList, rs.getString("phone_num"))){
					phoneNumList.add(rs.getString("phone_num"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return phoneNumList;
	}
	
	public boolean hasExist(List<String> list,String str){
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals(str)){
				return true;
			}
		}
		return false;
	}
	
	private boolean addHuafeiCZ(HuafeiCZ hf){
		boolean flag = false;	
		Connection conn = null;
		try {
			hf.setTradeId(getTradeId(hf.getUid()));
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into `youhui_cn_fanli`.`huafei_cz` (trade_id,uid,phone_num,cz_num,create_time,status) values(?,?,?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, hf.getTradeId());
			s.setString(2, hf.getUid());
			s.setString(3, hf.getPhoneNum());
			s.setInt(4, hf.getCzNum());
			s.setLong(5, System.currentTimeMillis());
			s.setInt(6, STATUS_INIT);
			int n = s.executeUpdate();
			if(n > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}

	public boolean setSuccess(String tradeId){
		return setStatus(tradeId, STATUS_SUCCESS, "");
	}
	
	public boolean setFail(String tradeId, String reason){
		return setStatus(tradeId, STATUS_FAIL, reason);
	}
	
	private boolean setStatus(String tradeId, int toStatus, String reason){
		boolean flag = false;	
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update `youhui_cn_fanli`.`huafei_cz` set `status` = ?,`done_time`=?,`fail_reason`=? where `trade_id`=? and `status`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setInt(1, toStatus);
			s.setLong(2, System.currentTimeMillis());
			s.setString(3, reason);
			s.setString(4, tradeId);
			s.setInt(5, STATUS_INIT);
			int n = s.executeUpdate();
			if(n > 0){
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	private static String getTradeId(String uid){
		String today = ft1.format(new Date());
		int i = 8 - uid.length();
		String uuid = uid;
		if(i >= 0){
			for(;i>0;i--){
				uuid = "0"+uuid;
			}
		}else{
			uuid = uuid.substring(-i, uuid.length());
		}
		String cuid = "";
		for(int j = 0; j < uuid.length()/2; j++){
			int s = Integer.parseInt(String.valueOf(uuid.charAt(j)));
			int e = Integer.parseInt(String.valueOf(uuid.charAt(uuid.length()-j-1)));
			cuid += (s + e)%10;
		}
		String rank = getRankNum(8);
		return today + rank + cuid;
	}
	
	private static String getRankNum(int size){
		StringBuffer sb = new StringBuffer();
		Random rm = new Random();
		for(int i = 0;i<size;i++){
			sb.append(rm.nextInt(10));
		}
		return sb.toString();
	}
	
	/**
	 * 获取充值成功记录
	 * @param uid
	 * @return
	 */
	public List<HuafeiCZ> getRecordUid(String uid){
		List<HuafeiCZ> list=new ArrayList<HuafeiCZ>();
		Connection conn=null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select `trade_id`,`uid`,`phone_num`,`cz_num`,`create_time` from `youhui_cn_fanli`.`huafei_cz` where `uid`=? and `status`=? order by `id` desc;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setInt(2, STATUS_SUCCESS);
			ResultSet rs=s.executeQuery();
			while(rs.next()){
				HuafeiCZ cz = new HuafeiCZ();
				cz.setTradeId(rs.getString("trade_id"));
				cz.setCzNum(rs.getInt("cz_num"));
				cz.setCreateTime(rs.getLong("create_time"));
				cz.setPhoneNum(rs.getString("phone_num"));
				list.add(cz);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(HuafeiCZDAO.getInstance().getRecordUid("108612506").get(0));
	}
}
