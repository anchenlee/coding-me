package cn.youhui.shareapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.dao.MySQLDBIns;
import cn.youhui.manager.UserManager;


/**
 * 分享软件活动
 * @author lijun
 * @since 2014-1-10
 */
public class ShareRecordManager {
	
	public static final int STATUS_OLD = 1;   //老用户
	public static final int STATUS_REPEAT = 2;   //imei重复用户
	public static final int STATUS_LEVEL_LOW = 3;    //级别太低  不超过一心
	public static final int STATUS_INACTIVE = 4;   //未激活
	public static final int STATUS_ACTIVE = 5;   //激活,未购物
	public static final int STATUS_HASGET = 6;   //已获得集分宝
	public static final int STATUS_FL = 7;    //已购物
	
	private static final String CHANNEL = "tuiguang";
	
	private static final long lINTERVAL_TIME = 24*60*60*1000;         //从注册到激活  最多间隔时间
	
	private static final int pageSize = 20;
	
	private static ShareRecordManager instance = null;
	
	private ShareRecordManager(){}
	
	public static ShareRecordManager getInstance(){
		if(instance == null) instance = new ShareRecordManager();
		return instance;
	}
	
//	/**
//	 * 推广页面注册
//	 */
//	public int register(String taobaoNick, String fromUid, String platform){
//		int ret = 0;
//		Connection conn = null;
//		try{
//			if(taobaoNick != null){
//				taobaoNick = FanJian.ftoj(taobaoNick.replaceAll(" ", "").toLowerCase());
//			}else{
//				return ret;
//			}
//			conn = SQL.getInstance().getConnection();
//			ShareRecord sr = new ShareRecord();
//			sr.setFromUid(fromUid);
//			sr.setFromTaobaoNick(UserManager.getInstance().getNickbyUid(fromUid, conn));
//			sr.setPlatform(platform);
//			sr.setCreateTime(System.currentTimeMillis());
//			sr.setTaobaoNick(taobaoNick);
//			UserBean user = new UserBean();
//			user.setTaobao_nick(taobaoNick);
//			if(user != null && user.getTaobao_nick() != null && !"".equals(user.getTaobao_nick())){
//				String uid = UserManager.getInstance().getUidbyNick(user.getTaobao_nick(), conn);
//				if(uid == null || "".equals(uid)){
//					org.allove.taoyouhui.manager.UserManager.tgSave(user, CHANNEL, platform);
//					if(user.getUid() != null && !"".equals(user.getUid())){
//						sr.setUid(user.getUid());
//						sr.setStatus(STATUS_INACTIVE);
//						add(sr, conn);
//						ret = 2;
//					}
//				}else{
//					sr.setUid(uid);
//					sr.setStatus(STATUS_OLD);
//					add(sr, conn);
//					ret = 1;          //老用户
//					return ret;
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			SQL.getInstance().release(conn);
//		}
//		return ret;
//	}
	
	/**
	 * 安卓用户激活后
	 * @param code
	 * @param fromUid
	 * @param platform
	 * @return
	 */
	public boolean save(String uid, String taobaoNick, String fromUid, String platform){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			ShareRecord sr = new ShareRecord();
			String fromTaobaoNick = UserManager.getInstance().getUserNickWithConn(fromUid, conn);
			sr.setFromUid(fromUid);
			sr.setFromTaobaoNick(fromTaobaoNick);
			sr.setPlatform(platform);
			sr.setUid(uid);
			sr.setTaobaoNick(taobaoNick);
			sr.setStatus(STATUS_ACTIVE);
			long now = System.currentTimeMillis();
			sr.setCreateTime(now);
			sr.setActiveTime(now);
			flag = add(sr, conn);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	/**
	 * 老用户登陆
	 * @param code
	 * @param fromUid
	 * @param platform
	 * @return
	 */
	public boolean saveOld(String uid, String taobaoNick, String fromUid, String platform){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			ShareRecord sr = new ShareRecord();
			String fromTaobaoNick = UserManager.getInstance().getUserNickWithConn(fromUid, conn);
			sr.setFromUid(fromUid);
			sr.setFromTaobaoNick(fromTaobaoNick);
			sr.setPlatform(platform);
			sr.setUid(uid);
			sr.setTaobaoNick(taobaoNick);
			sr.setStatus(STATUS_OLD);
			long now = System.currentTimeMillis();
			sr.setCreateTime(now);
			sr.setActiveTime(now);
			flag = add(sr, conn);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	private boolean add(ShareRecord sr, Connection conn){
		boolean flag = false;
		try{
			String sql = "insert into `youhui_shareapp`.`share_record` (`uid`,`taobao_nick`,`from_uid`,`fl_jfb_num`," +
					"`tg_jfb_num`,`status`,`platform`,`create_time`,`active_time`,`from_taobao_nick`)values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sr.getUid());
			ps.setString(2, sr.getTaobaoNick());
			ps.setString(3, sr.getFromUid());
			ps.setInt(4, sr.getFlJFBNum());
			ps.setInt(5, sr.getTgJFBNum());
			ps.setInt(6, sr.getStatus());
			ps.setString(7, sr.getPlatform());
			ps.setLong(8, sr.getCreateTime());
			ps.setLong(9, sr.getActiveTime());
			ps.setString(10, sr.getFromTaobaoNick());
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 是否在有效激活时间以内
	 * @return
	 */
	public boolean isInValidTime(String uid, Connection conn){
		boolean flag = false;
		try{
			String sql = "select create_time from `youhui_shareapp`.`share_record` where `uid`=? and `status`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, STATUS_INACTIVE);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				long createTime = rs.getLong("create_time");
				if(createTime + lINTERVAL_TIME > System.currentTimeMillis()){
					flag = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 设置用户为imei重复
	 * @param uid
	 * @param conn
	 * @return
	 */
	public boolean repeatUid(String uid, Connection conn){
		boolean flag = false;
		try{
			String sql = "update `youhui_shareapp`.`share_record` set `status`=? where `uid`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_REPEAT);
			ps.setString(2, uid);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 设置用户为 级别太低
	 * @param uid
	 * @param conn
	 * @return
	 */
	public boolean levelLowUid(String uid, Connection conn){
		boolean flag = false;
		try{
			String sql = "update `youhui_shareapp`.`share_record` set `status`=? where `uid`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_LEVEL_LOW);
			ps.setString(2, uid);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 设置用户已激活
	 * @return
	 */
	public boolean activeUid(String uid, Connection conn){
		boolean flag = false;
		try{
			String sql = "update `youhui_shareapp`.`share_record` set `status`=?, `active_time`=? where `uid`=? and `status`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_ACTIVE);
			ps.setLong(2, System.currentTimeMillis());
			ps.setString(3, uid);
			ps.setInt(4, STATUS_INACTIVE);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 设置状态为已领取，并加上相应集分宝
	 * @return
	 */
	public boolean setHasGet(String uid, int jfbNum){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update `youhui_shareapp`.`share_record` set `status`=?, `tg_jfb_num`=? where `uid`=? and `status`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_HASGET);
			ps.setLong(2, jfbNum);
			ps.setString(3, uid);
			ps.setInt(4, STATUS_ACTIVE);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
//	
//	/**
//	 * 设置状态为已购物返利奖励，并加上相应集分宝
//	 * @return
//	 */
//	public boolean setFL(String uid, int jfbNum){
//		boolean flag = false;
//		Connection conn = null;
//		try{
//			conn = SQL.getInstance().getConnection();
//			String sql = "update `youhui_shareapp`.`share_record` set `status`=?, `tg_jfb_num`=`tg_jfb_num`+? where `uid`=? and `status`>=?;";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, STATUS_FL);
//			ps.setLong(2, jfbNum);
//			ps.setString(3, uid);
//			ps.setInt(4, STATUS_HASGET);
//			int i = ps.executeUpdate();
//			if(i > 0){
//				flag = true;
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			SQL.getInstance().release(conn);
//		}
//		return flag;
//	}
//	

	/**
	 * 获取推广人uid
	 * @return
	 */
	public String getFromUid(String uid, Connection conn){
		String fromUid = null;
		try{
			String sql = "select from_uid from `youhui_shareapp`.`share_record` where `uid`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				fromUid = rs.getString("from_uid");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return fromUid;
	}
	
//	
//	/**
//	 * 获取分享情况
//	 * @return
//	 */
//	public ShareRecord getShareDetail(String fromUid){
//		ShareRecord sr = new ShareRecord();
//		Connection conn = null;
//		try{
//			conn = SQL.getInstance().getConnection();
//			String sql = "select sum(tg_jfb_num) as sumjfb from `youhui_shareapp`.`share_record` where `from_uid`=?;";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, fromUid);
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()){
//				sr.setTgJFBNum(rs.getInt("sumjfb"));
//			}
//			
//			sql = "select count(id) as tgnum from `youhui_shareapp`.`share_record` where `from_uid`=? and `status` > ?;";
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, fromUid);
//			ps.setInt(2, STATUS_OLD);
//			rs = ps.executeQuery();
//			if(rs.next()){
//				sr.setTotalAcount(rs.getInt("tgnum"));
//			}
//			
//			sql = "select count(id) as tgnum from `youhui_shareapp`.`share_record` where `from_uid`=? and `status` = ?;";
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, fromUid);
//			ps.setInt(2, STATUS_INACTIVE);
//			rs = ps.executeQuery();
//			if(rs.next()){
//				sr.setInavtiveAcount(rs.getInt("tgnum"));
//			}
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			SQL.getInstance().release(conn);
//		}
//		return sr;
//	}
//	
//	/**
//	 * 获取分享的子用户
//	 * @return
//	 */
//	public List<ShareRecord> getShareUids(String fromUid, int page){
//		List<ShareRecord> srs = new ArrayList<ShareRecord>();
//		Connection conn = null;
//		try{
//			conn = SQL.getInstance().getConnection();
//			String sql = "select * from `youhui_shareapp`.`share_record` where `from_uid`=? order by `create_time` desc limit ?,?;";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, fromUid);
//			ps.setInt(2, (page - 1)*pageSize);
//			ps.setInt(3, pageSize);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()){
//				ShareRecord sr = new ShareRecord();
//				sr.setUid(rs.getString("uid"));
//				sr.setTaobaoNick(rs.getString("taobao_nick"));
//				sr.setStatus(rs.getInt("status"));
//				sr.setFlJFBNum(rs.getInt("fl_jfb_num"));
//				sr.setTgJFBNum(rs.getInt("tg_jfb_num"));
//				sr.setCreateTime(rs.getLong("create_time"));
//				sr.setActiveTime(rs.getLong("active_time"));
//				srs.add(sr);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			SQL.getInstance().release(conn);
//		}
//		return srs;
//	}
//	
//	/**
//	 * 获取分享的子用户列表页数
//	 * @return
//	 */
//	public int getShareUidsPage(String fromUid){
//		int num = 0;
//		Connection conn = null;
//		try{
//			conn = SQL.getInstance().getConnection();
//			String sql = "select count(id) as ct from `youhui_shareapp`.`share_record` where `from_uid`=?;";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, fromUid);
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()){
//				int count = rs.getInt("ct");
//				num = count/pageSize;
//				if(count%pageSize > 0){
//					num ++;
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			SQL.getInstance().release(conn);
//		}
//		return num;
//	}
//	
//	/**
//	 * 获取分享的子用户列表页数
//	 * @return
//	 */
//	public int getTotalPageNum(){
//		int num = 0;
//		Connection conn = null;
//		try{
//			conn = SQL.getInstance().getConnection();
//			String sql = "select count(id) as ct from `youhui_shareapp`.`share_record` where status>=?;";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1,STATUS_HASGET);
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()){
//				int count = rs.getInt("ct");
//				num = count/pageSize;
//				if(count%pageSize > 0){
//					num ++;
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			SQL.getInstance().release(conn);
//		}
//		return num;
//	}
//	/**
//	 * 获取前三名推广
//	 * @return
//	 */
//	public List<ShareRecord> getTop3(){
//		List<ShareRecord> srs = new ArrayList<ShareRecord>();
//		Connection conn = null;
//		try{
//			conn = SQL.getInstance().getConnection();
//			String sql = "select from_taobao_nick, from_uid ,sum(tg_jfb_num) as sumjfb from `youhui_shareapp`.`share_record` where `tg_jfb_num` > 0 group by from_uid order by sumjfb desc limit 3;";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()){
//				ShareRecord sr = new ShareRecord();
//				sr.setFromUid(rs.getString("from_uid"));
//				String tn = rs.getString("from_taobao_nick");
//				sr.setFromTaobaoNick(tn.substring(0, 1) +"**" +tn.substring(tn.length()-1, tn.length()));
//				sr.setTgJFBNum(rs.getInt("sumjfb"));
//				srs.add(sr);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			SQL.getInstance().release(conn);
//		}
//		return srs;
//	}
//	
//	/**
//	 * 根据状态获取子用户
//	 * @return
//	 */
//	private List<ShareRecord> getShareUidsByStatus(int status){
//		List<ShareRecord> srs = new ArrayList<ShareRecord>();
//		Connection conn = null;
//		try{
//			conn = SQL.getInstance().getConnection();
//			String sql = "select * from `youhui_shareapp`.`share_record` where `status`=?;";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, status);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()){
//				ShareRecord sr = new ShareRecord();
//				sr.setUid(rs.getString("uid"));
//				sr.setTaobaoNick(rs.getString("taobao_nick"));
//				sr.setFromUid(rs.getString("from_uid"));
//				sr.setFromTaobaoNick(rs.getString("from_taobao_nick"));
//				sr.setStatus(rs.getInt("status"));
//				sr.setFlJFBNum(rs.getInt("fl_jfb_num"));
//				sr.setTgJFBNum(rs.getInt("tg_jfb_num"));
//				sr.setCreateTime(rs.getLong("create_time"));
//				sr.setActiveTime(rs.getLong("active_time"));
//				srs.add(sr);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			SQL.getInstance().release(conn);
//		}
//		return srs;
//	}
//	
//	/**
//	 * 获取成功分享的子用户
//	 * @return
//	 */
//	public List<ShareRecord> getSucUids(){
//		List<ShareRecord> srs = new ArrayList<ShareRecord>();
//		Connection conn = null;
//		try{
//			conn = SQL.getInstance().getConnection();
//			String sql = "select * from `youhui_shareapp`.`share_record` where `status`>=? ;";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, STATUS_HASGET);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()){
//				ShareRecord sr = new ShareRecord();
//				sr.setUid(rs.getString("uid"));
//				sr.setTaobaoNick(rs.getString("taobao_nick"));
//				sr.setFromUid(rs.getString("from_uid"));
//				sr.setFromTaobaoNick(rs.getString("from_taobao_nick"));
//				sr.setStatus(rs.getInt("status"));
//				sr.setFlJFBNum(rs.getInt("fl_jfb_num"));
//				sr.setTgJFBNum(rs.getInt("tg_jfb_num"));
//				sr.setCreateTime(rs.getLong("create_time"));
//				sr.setActiveTime(rs.getLong("active_time"));
//				srs.add(sr);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			SQL.getInstance().release(conn);
//		}
//		return srs;
//	}
//	
//	public static void main(String[] args) {
//		Connection conn = SQL.getInstance().getConnection();
//		System.out.println(ShareRecordManager.getInstance().isInValidTime("31720931", conn));
//		SQL.getInstance().release(conn);
//	}
}
