package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.youhui.bean.VisitRecordUid;
import cn.youhui.dao.MySQLDBIns;


public class VisitRecordUidDAO {
	
	private static VisitRecordUidDAO instance = null;
	
//	private final DateFormat mdf = new SimpleDateFormat("yyyyMM");
	
	private VisitRecordUidDAO(){}
	
	public static VisitRecordUidDAO getInstance(){
		if(instance == null) instance = new VisitRecordUidDAO();
		return instance;
	}
	
	public void add(VisitRecordUid v){	
		
		Connection conn = null;
		PreparedStatement sm = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection2();
			
//			String table = "uid_visit_" + mdf.format(new Date(now));
//			if(isMonthFirst() && !isExistTable(table, conn)){        //先检测是否为月初第一天， 减少查询数据库次数
//				createTable(table, conn);
//			}
			
			String sql = "insert into `youhui_visit`.`uid_visit_201403`(`uid`,`platform`,`version`,`imei`,`imsi`,`interface_name`,`date`,`times`,`first_time`)values(?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE `times`=`times`+1;";
			sm = conn.prepareStatement(sql);
			sm.setString(1, v.getUid());
			sm.setString(2, v.getPlatform());
			sm.setString(3, v.getVersion());
			sm.setString(4, v.getImei());
			sm.setString(5, v.getImsi());
			sm.setString(6, v.getInterfaceName());
			sm.setString(7, v.getDate());
			sm.setInt(8, v.getTimes());
			sm.setLong(9, System.currentTimeMillis());
			sm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
	}
	
public void addAds(VisitRecordUid v){	
		
		Connection conn = null;
		PreparedStatement sm = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection2();
			
			String sql = "insert into `youhui_visit`.`uid_visit_201403_ads`(`uid`,`platform`,`version`,`imei`,`imsi`,`interface_name`,`date`,`times`,`first_time`)values(?,?,?,?,?,?,?,?,?);";
			sm = conn.prepareStatement(sql);
			sm.setString(1, v.getUid());
			sm.setString(2, v.getPlatform());
			sm.setString(3, v.getVersion());
			sm.setString(4, v.getImei());
			sm.setString(5, v.getImsi());
			sm.setString(6, v.getInterfaceName());
			sm.setString(7, v.getDate());
			sm.setInt(8, v.getTimes());
			sm.setLong(9, System.currentTimeMillis());
			sm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
	}
	
}
