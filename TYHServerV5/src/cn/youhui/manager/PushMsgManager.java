package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.Action;
import cn.youhui.bean.PushMessage;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.utils.ActionChangeUtil;

public class PushMsgManager {
	private static final String defalutuid = "00000000";
	private static final int pageSize = 10;
	private static PushMsgManager instance = null;
	
	public static PushMsgManager getInstance(){
		if(instance == null)
			instance = new PushMsgManager();
		return instance;
	}
	private PushMsgManager(){}
	
	public List<PushMessage> getPushMsgList(String uid, int pageInt, String platform, String version_code)
	{
		List<PushMessage> ret = new ArrayList<PushMessage>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		int pageNum = getPageNum(uid, platform);
		if(pageNum == 0) 
		{
			return null;
		}
		List<String> list = new ArrayList<String>();
		if(pageInt < 1) 
		{
			pageInt = 1;
		}
		else if(pageInt > pageNum)
		{
			pageInt = pageNum; 
		}
		try 
		{
			String sql = "";
			String sql1 = "select pid from youhui_pushmessage.tyh_uid_pmsg where uid=? or uid=? and state='1'";
//			PreparedStatement s = conn.prepareStatement(sql);
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			s1.setString(2, defalutuid);
			ResultSet rs1 = s1.executeQuery();
			while(rs1.next()) 
			{
				list.add(rs1.getString("pid"));
			}
			String t = "";
			if(list != null && list.size() > 0)
			{
				for(int i=0;i<list.size();i++) 
				{					
					t += " and b.pid !='"+list.get(i)+"' ";
				}
			}
			
			String platformstr = "";
			if(null != platform && !"".equals(platform))
			{
//				platformstr = " or a.platform like '"+ platform +"%'";
				if("iphone".equalsIgnoreCase(platform)){
					platform = "iphone_etouch";
				}
				platformstr = " or a.platform = '"+ platform +"'";
			}
			sql = "select a.* ,b.start_time as time,b.content_value from youhui_pushmessage.tyh_uid_pmsg b ,youhui_pushmessage.tyh_pushmessage a where (a.platform = 'all' "+ platformstr +") " +
					"and a.pid=b.pid and (b.uid=? or b.uid=?) and b.state='0'   " +t+"  group by b.pid order by b.start_time desc limit ?,?;";
//			sql = "select * from youhui_pushmessage.tyh_pushmessage inner join youhui_pushmessage.tyh_uid_pmsg on tyh_uid_pmsg.pid = tyh_pushmessage.pid and ((tyh_uid_pmsg.uid = ? or uid = ?) and tyh_uid_pmsg.state='0') where tyh_uid_pmsg.state=0  " +t+" group by tyh_uid_pmsg.pid order by tyh_uid_pmsg.start_time desc limit ?,?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, defalutuid);
			s.setInt(3, pageSize*(pageInt - 1));
			s.setInt(4, pageSize);
			
			System.out.println("==================================");
			System.out.println(s);
			ResultSet rs = s.executeQuery();		
			long now = System.currentTimeMillis();
			while(rs.next()){
				PushMessage bean = new PushMessage();
				long startTime = rs.getLong("time");
				if(startTime > now){
					continue;
				}
				bean.setId(rs.getString("id"));
				bean.setCode(rs.getInt("code"));
				bean.setpId(rs.getString("pid"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
				bean.setStarttime(startTime);
				bean.setContent(bean.convertContent(rs.getString("content"), rs.getString("content_value")));
				
				String action_type = rs.getString("action_type");
				String action_value = rs.getString("action_value");
				Action action = 
						ActionChangeUtil.lowVersionItemAction(bean.getId(), uid, platform, "11", action_type, action_value, version_code);
				bean.setAction(action);
				
				ret.add(bean);
			}
			
		} catch (SQLException e) {
			ret= null;
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return ret;
	}
	
	public int getPageNum(String uid){
		Connection conn = MySQLDBIns.getInstance().getConnection();
		List<String> list = new ArrayList<String>();
		int page = 0;
		try {
			String sql = "select count(distinct(pid)) as scount from youhui_pushmessage.tyh_uid_pmsg where (uid = ?  or   uid = ? ) and state=0 ";
			String sql1 = "select pid from youhui_pushmessage.tyh_uid_pmsg where uid=? and state='1'";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs1 = s1.executeQuery();
			while(rs1.next()) {
				list.add(rs1.getString("pid"));
			}
			String t = "";
			if(list!=null&&list.size()>0)  
				for(int i=0;i<list.size();i++) {					
				t += "and tyh_uid_pmsg.pid !='"+list.get(i)+"'";
				}
			sql = sql+t;
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, defalutuid);
			ResultSet rs = s.executeQuery();
			int count = 0;
			if(rs.next()){
				count = rs.getInt("scount");
			}
			int ys = count%pageSize;
			count = count - ys;
			page = count/pageSize;
			if(ys != 0){
				page++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return page;
	}
	
	public int getPageNum(String uid, String platform){
		Connection conn = MySQLDBIns.getInstance().getConnection();
		List<String> list = new ArrayList<String>();
		int page = 0;
		try {
			String sql = "";
			String sql1 = "select pid from youhui_pushmessage.tyh_uid_pmsg where uid=? and state='1'";
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s1.setString(1, uid);
			ResultSet rs1 = s1.executeQuery();
			while(rs1.next()) {
				list.add(rs1.getString("pid"));
			}
			String t = "";
			if(list!=null&&list.size()>0)  
				for(int i=0;i<list.size();i++) {					
				t += "and b.pid !='"+list.get(i)+"'";
				}
			String platformstr = "";
			if(null != platform && !"".equals(platform)){
//				platformstr = "or a.platform like '"+ platform +"%'";
				if("iphone".equalsIgnoreCase(platform)){
					platform = "iphone_etouch";
				}
				platformstr = " or a.platform = '"+ platform +"'";
			}
			sql = "select count(*) as scount from( select a.pid from youhui_pushmessage.tyh_uid_pmsg b ,youhui_pushmessage.tyh_pushmessage a where (a.platform = 'all' "+ platformstr +") and a.pid=b.pid and (b.uid=? or b.uid=?) and b.state='0'   " +t+"  group by b.pid order by b.start_time desc) as c;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, defalutuid);
			ResultSet rs = s.executeQuery();		
			int count = 0;
			if(rs.next()){
				count = rs.getInt("scount");
			}
			int ys = count%pageSize;
			count = count - ys;
			page = count/pageSize;
			if(ys != 0){
				page++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
		return page;
	}
	
	
	public int addPushState(String uid, String platform,List<PushMessage> list){  
		if(list == null || list.size()<1)
			return 0;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_pushmessage.tyh_push_state(`push_pid` ,`push_title` ,`devtoken` ,`state`, `timestamp`,`platform`) values(?,?,?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			long now = System.currentTimeMillis();
			for(PushMessage msg : list){
				s.setString(1, msg.getpId());
				s.setString(2, msg.getTitle());
				s.setString(3, uid);
				s.setInt(4, 1);
				s.setLong(5, now);
				s.setString(6, platform);
				s.addBatch();
			}
			conn.setAutoCommit(true);
			int[] i = s.executeBatch();
			return i.length;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return 0;
	}
	
	public boolean delUserPushMsg(String uid,String id) {
		boolean flag = false;
		Connection conn = null;
		try {
			int i = 0;
			conn = MySQLDBIns.getInstance().getConnection();
			
			String pid = getPidById(id, conn);
			if(pid != null){
			
			String sql = "update  youhui_pushmessage.tyh_uid_pmsg set state=1 where uid=? and pid=?";
			String sql1 = "insert into youhui_pushmessage.tyh_uid_pmsg(uid,pid,content_value,start_time,state) values(?,?,?,?,?)  ";
			PreparedStatement s = conn.prepareStatement(sql);
			PreparedStatement s1 = conn.prepareStatement(sql1);
			s.setString(1, uid);
			s.setString(2, pid);
			i = s.executeUpdate();
			if(i==0) {
			s1.setString(1, uid);
			s1.setString(2, pid);
			s1.setString(3, "");
			s1.setString(4, System.currentTimeMillis()+"");
			s1.setString(5, "1");
			i =  s1.executeUpdate();
			}
			if(i>0) flag = true;
			s.close();
			s1.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public String getPidById(String id, Connection conn) {
		String pid = null;
		try {
			String sql = "select pid from youhui_pushmessage.tyh_pushmessage  where id=?";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, id);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				pid = rs.getString("pid");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pid;
	}
}