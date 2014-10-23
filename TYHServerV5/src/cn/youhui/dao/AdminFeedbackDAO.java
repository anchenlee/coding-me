package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.youhui.bean.IpadFeedbackBean;

/**
 * 
 * @author 
 */
public class AdminFeedbackDAO
{	
	public static final int pageSize = 10;
	
	/**
	 * 增加Ipad反馈
	 * @param ifb
	 * @return
	 */
	public static  Boolean addIpadFeedbackBean(IpadFeedbackBean ifb)
	{
		Boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into youhui_v3.ipad_feedback(uid,msg,type,platform,version,create_time,update_time) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ifb.getUid());
			ps.setString(2, ifb.getMsg());
			ps.setInt(3, ifb.getType());
			ps.setString(4, ifb.getPlatform());
			ps.setString(5, ifb.getVersion());
			ps.setLong(6, System.currentTimeMillis());
			ps.setLong(7, System.currentTimeMillis());
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			MySQLDBIns.getInstance().release( conn);
		}
		return flag;
	}
	
	public static  List<IpadFeedbackBean> getIpadFeedbacksByUid(String uid, int page)
	{
		Connection conn=null;
		IpadFeedbackBean ifb= null;
		List<IpadFeedbackBean> list = new ArrayList<IpadFeedbackBean>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="select * from youhui_v3.ipad_feedback where  uid = ? order by create_time desc limit  ?,?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, uid);
			st.setInt(2, (page - 1)*pageSize);
			st.setInt(3, pageSize);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				ifb=new IpadFeedbackBean();
				ifb.setId(rs.getString("id"));
				ifb.setUid(rs.getString("uid"));
				ifb.setMsg(rs.getString("msg"));
				ifb.setType(rs.getInt("type"));
				ifb.setPlatform(rs.getString("platform"));
				ifb.setVersion(rs.getString("version"));
				ifb.setCreateTime(rs.getLong("create_time"));
				ifb.setCreateDate(sdf.format(new Date(rs.getLong("create_time"))));
				ifb.setUpdateTime(rs.getLong("update_time"));
				ifb.setUpdateDate(sdf.format(rs.getLong("update_time")));
				list.add(ifb);
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public static  int getIpadFeedbacksCount(String uid)
	{
		int cnt = 0;
		Connection conn=null;
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="select count(1) as cnt from youhui_v3.ipad_feedback where  uid = ?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, uid);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				cnt = rs.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return cnt;
	}
	
}
