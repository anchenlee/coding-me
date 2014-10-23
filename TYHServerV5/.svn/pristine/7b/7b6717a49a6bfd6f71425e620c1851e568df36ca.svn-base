package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.dao.MySQLDBIns;

public class GetPointUid {

	private static GetPointUid instance = null;
	    
	    
	public static GetPointUid getInstance(){
		if(instance == null) instance = new GetPointUid();
		return instance;
	}
	
	/**
	 * 得到某个时间段注册的uid列表
	 * @return
	 */
	public List<String> getTradeUid(long start,long end){
		List<String> uidList=new ArrayList<String>();
		PreparedStatement ps=null;
		Connection conn = null;
		ResultSet rs=null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql ="select distinct(uid) from youhui_v3.user where creat_time>=? and creat_time<=? ;";
			ps=conn.prepareStatement(sql);
			ps.setString(1, start+"");
			ps.setString(2, end+"");
			rs=ps.executeQuery();
			while(rs.next()){
				uidList.add(rs.getString("uid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return uidList;
	}
	
	public int getDingdanNum(List<String> uidList){
		int num=0;
		PreparedStatement ps=null;
		Connection conn = null;
		ResultSet rs=null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql ="select trade_id from youhui_fanli.tyh_report_trade where uid=? limit 1 ;";
			ps=conn.prepareStatement(sql);
			for(int i=0;i<uidList.size();i++){
				ps.setString(1, uidList.get(i));
				rs=ps.executeQuery();
				if(rs.next())
					num++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return num;
	}
	
	
	public int getSignNum(List<String> uidList){
		int num=0;
		PreparedStatement ps=null;
		Connection conn = null;
		ResultSet rs=null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql ="select id from youhui_cn_fanli.tyh_activity_join  where uid=? and running_days>=5 and activity_id='1' limit 1 ;";
			ps=conn.prepareStatement(sql);
			for(int i=0;i<uidList.size();i++){
				ps.setString(1, uidList.get(i));
				rs=ps.executeQuery();
				if(rs.next())
					num++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return num;
	}
	
	
	
	
	

}
