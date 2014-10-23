package cn.youhui.acapi.newuser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.youhui.dao.MySQLDBIns;

/**
 * 记录中奖话费充值情况
 * @author lijun
 * @since 2014-8-1
 */
public class HuafeiManager {

	private static HuafeiManager instance = null;
	
	public static HuafeiManager getInstance(){
		if(instance == null){
			instance = new HuafeiManager();
		}
		return instance;
	}
	
	public boolean addRecord(String imei, String imsi, String uid, String phoneNum, String attr){
		boolean flag = false;
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into `youhui_luckac`.`luck_huafei` (`imei`,`imsi`,`uid`,`phone_num`,`timestamp`,`status`,`attribution`)" +
					" values (?,?,?,?,?,0,?)";
			ps = conn.prepareStatement(sql);
			long now = System.currentTimeMillis();
			ps.setString(1, imei);
			ps.setString(2, imsi);
			ps.setString(3, uid);
			ps.setString(4, phoneNum);
			ps.setLong(5, now);
			ps.setString(6, attr);
			if(ps.executeUpdate() > 0){ 
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return flag;
	}
	
	public boolean searchRecordCntByPhone(String phoneNum,int exceptionCount){
		boolean flag = false;
		PreparedStatement ps = null;
		Connection conn = null;
		int cnt = 0;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select count(1) as cnt from `youhui_luckac`.`luck_huafei`  where phone_num = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, phoneNum);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > exceptionCount) {
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return flag;
	}
	
	public boolean updateCZtoException(int id){
		boolean flag = false;
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update  `youhui_luckac`.`luck_huafei`  set status = 1 where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return flag;
	}
	
	/**
	 * 取得所有异常的待充值记录
	 * @return
	 */
	public String getAllExceptionDataIds(){
		String ids = "";
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select id from `youhui_luckac`.`luck_huafei` where status =  1";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ids += rs.getString("id") + ",";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return ids;
	}
	
	/**
	 * 取得刚插入记录的主键id
	 * @return
	 */
	public int getIdByInstance(){
		int id = 0;
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select max(id) as m from `youhui_luckac`.`luck_huafei`";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("m");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return id;
	}
	
	/**
	 * 时间段内中奖次数上限
	 * @return
	 */
	public boolean insertNumToTimePeriod(long startTime ,long endTime ,String num,int type){
		boolean flag = false;
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into  `youhui_luckac`.`mcd_nums`(start_time,end_time,num,update_time,type) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, startTime);
			ps.setLong(2, endTime);
			ps.setString(3, num);
			ps.setLong(4, System.currentTimeMillis());
			ps.setInt(5, type);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return flag;
	}
	
	/**
	 * 时间段内中奖次数上限
	 * @return
	 */
	public boolean updateNumToTimePeriod(int id,long startTime ,long endTime ,String num,int type){
		boolean flag = false;
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update  `youhui_luckac`.`mcd_nums` set start_time = ? ,end_time = ?,num = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, startTime);
			ps.setLong(2, endTime);
			ps.setString(3, num);
			ps.setInt(4, id);
			ps.setInt(5, type);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return flag;
	}
	
	
	
	/**
	 *检查请该imei和imsi是否被已被使用过
	 * @return
	 */
	public boolean checkIfVal(String imei,String imsi){
		boolean flag = true;
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select id from youhui_luckac.luck_huafei where imei = ? and imsi = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, imei);
			ps.setString(2, imsi);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return flag;
	}
	
	/**
	 *检查手机号是否被充值过
	 * @return
	 */
	public boolean checkIfValPhoneNum(String phoneNum){
		boolean flag = true;
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select id from youhui_luckac.luck_huafei where phone_num = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, phoneNum);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return flag;
	}
	
	/**
	 * 取得所有归属地为空的记录
	 */
	public List<Map<String ,String>> getAllNullAttrRecords()
	{
		List<Map<String ,String>> list = new ArrayList<Map<String ,String>>();
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select id,phone_num from youhui_luckac.luck_huafei where attribution is null or attribution = '' ;";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Map<String ,String> map = new HashMap<String ,String>();
				map.put("id", rs.getString("id"));
				map.put("phoneNum", rs.getString("phone_num"));
				list.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return list;
	}
	
	/**
	 * 更新手机号码归属地
	 */
	public boolean updateAttrRecords(String id,String attr)
	{
		boolean flag = false;
		PreparedStatement ps = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update  youhui_luckac.luck_huafei set attribution = ? where id = ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, attr);
			ps.setString(2, id);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(ps, null);
		}
		return flag;
	}
	
}
