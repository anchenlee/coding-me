package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

import cn.youhui.bean.UserDevtoken;
import cn.youhui.dao.MySQLDBIns;

/**
 * 操作用户设备信息
 * @author lijun
 * @since 2014-9-16
 */
public class UserDevtokenDAO {

	public static void main(String[] args) throws ParseException {
	//	UserDevtoken ud=new UserDevtoken("333", "q", "w", "e", "r", "t", "y", "258", "u");
//		if(addUserDevtoken(ud)){
//			System.out.println("成功");
//		}
		UserDevtokenDAO uu=new UserDevtokenDAO();
		System.out.println(uu.getDevtokenByOpenudid("333"));
	}
	
	private static UserDevtokenDAO udd= null;
	
	public static UserDevtokenDAO getInstance() {
		if (udd == null){
			udd = new UserDevtokenDAO();
		}
		return udd;
	}

	private UserDevtokenDAO() {}
	
	public  boolean addUserDevtoken(UserDevtoken userDevtoken){
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps=null;
		try{
			if(userDevtoken.getUid()!=null&&!userDevtoken.getUid().equals("")){
				if(hasUid(userDevtoken.getOpenudid())){
					flag=updateOpenudidByUid(userDevtoken.getUid(), userDevtoken.getOpenudid());
				}else{
					updateUidByOpenudid(userDevtoken.getUid(), userDevtoken.getOpenudid());
				}
			}else{
				conn = MySQLDBIns.getInstance().getConnection();
				String sql="insert into `youhui_v3`.`user_devtoken` (`openudid`,`imsi`,`imei`,`idfa`,`mac_md5`,`os_version`,`version`,`uid`,`devtoken`,`insert_time`) values(?,?,?,?,?,?,?,?,?,?);";
				ps=conn.prepareStatement(sql);
				ps.setString(1, userDevtoken.getOpenudid());
				ps.setString(2, userDevtoken.getImsi());
				ps.setString(3, userDevtoken.getImei());
				ps.setString(4, userDevtoken.getIdfa());
				ps.setString(5, userDevtoken.getMacMD5());
				ps.setString(6, userDevtoken.getOsVersion());
				ps.setString(7, userDevtoken.getVersion());
				ps.setString(8, userDevtoken.getUid());
				ps.setString(9, userDevtoken.getDevtoken());
				ps.setLong(10, System.currentTimeMillis());
				if(ps.executeUpdate()>0){
					flag=true;
				}
			}

		}catch(Exception e){
			//不写日志
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	
	public  boolean addUserDevtoken(String uid,String openudid){
		boolean flag=false;
		if(uid!=null&&!uid.equals("")){
			if(hasUid(openudid)){
				flag=updateOpenudidByUid(uid, openudid);
			}
			else {
				flag=updateUidByOpenudid(uid, openudid);
			}
		}
		return flag;
	}
	
	/**
	 * 通过openudid判断uid是否存在
	 * @param openudid
	 * @return
	 */
	public  boolean hasUid(String uid){
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql="select `uid` from `youhui_v3`.`user_devtoken` where `uid`=?;";
			ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getString("uid")!=null&&!rs.getString("uid").equals("")){
					flag=true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 通过uid更新openudid
	 * @param uid
	 * @return
	 */
	public  boolean updateOpenudidByUid(String uid,String openudid){
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps=null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql="update `youhui_v3`.`user_devtoken` set `openudid`=? , `update_time`=? where uid=?;";
			ps=conn.prepareStatement(sql);
			ps.setString(1, openudid);
			ps.setLong(2, System.currentTimeMillis());
			ps.setString(3, uid);
			if(ps.executeUpdate()>0){
				flag=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	
	public  boolean updateUidByOpenudid(String uid,String openudid){
		boolean flag=false;
		Connection conn = null;
		PreparedStatement ps=null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql="update `youhui_v3`.`user_devtoken` set `uid`=? , `update_time`=? where openudid=?;";
			ps=conn.prepareStatement(sql);
			ps.setString(1,uid);
			ps.setLong(2, System.currentTimeMillis());
			ps.setString(3,openudid );
			if(ps.executeUpdate()>0){
				flag=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	
	public String getDevtokenByOpenudid(String openudid){
		String devtoken=null;
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql="select `devtoken` from `youhui_v3`.`user_devtoken` where `openudid`=?;";
			ps=conn.prepareStatement(sql);
			ps.setString(1, openudid);
			rs=ps.executeQuery();
			if(rs.next()){
				devtoken=rs.getString("devtoken");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return devtoken;
	}
}
