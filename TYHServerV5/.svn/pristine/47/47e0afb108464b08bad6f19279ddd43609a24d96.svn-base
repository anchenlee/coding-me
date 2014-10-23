package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.youhui.bean.VisitRecordInterface;
import cn.youhui.dao.MySQLDBIns;

public class VisitRecordInterfaceDAO {
	
private static VisitRecordInterfaceDAO instance = null;
	
	private VisitRecordInterfaceDAO(){}
	
	public static VisitRecordInterfaceDAO getInstance(){
		if(instance == null) instance = new VisitRecordInterfaceDAO();
		return instance;
	}
	
	public void add(VisitRecordInterface v){	
		
		Connection conn = null;
		PreparedStatement sm = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection2();
			
			String sql = "insert into `youhui_visit`.`interface_visit`(`interface_name`,`time`,`times`)values(?,?,?) ON DUPLICATE KEY UPDATE `times`=`times`+1;";
			sm = conn.prepareStatement(sql);
			sm.setString(1, v.getInterfaceName());
			sm.setString(2, v.getTime());
			sm.setInt(3, v.getTimes());
			sm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
	}
}
