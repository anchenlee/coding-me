package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import cn.youhui.bean.VisitRecord;
import cn.youhui.dao.MySQLDBIns;

public class VisitRecordManager {

	private static Logger log = Logger.getLogger(VisitRecordManager.class) ;
	
	private final DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	private final DateFormat mdf = new SimpleDateFormat("YYYY_MM");
	private static VisitRecordManager instance = null;
	private VisitRecordManager(){}
	
	public static VisitRecordManager getInstance(){
		if(instance == null) instance = new VisitRecordManager();
		return instance;
	}
	
	
	public void add(VisitRecord vr){	
		
		Connection conn = null;
		PreparedStatement sm = null;
		try{
			Long now = System.currentTimeMillis();
			conn = MySQLDBIns.getInstance().getConnection();
			String table = "visit_record_" + mdf.format(new Date(now));
			if(!isExistTable(table, conn)){
				createTable(table, conn);
			}
			String sql = "insert into `youhui_visit`.`"+ table +"`(`interface_name`,`uid`,`platform`,`channel`,`version`,`imei`,`imsi`,`ip`,`params`,`param1`,`param2`,`param3`,`time`,`timestamp`)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			sm = conn.prepareStatement(sql);
			sm.setString(1, vr.getInterfaceName());
			sm.setString(2, vr.getUid());
			sm.setString(3, vr.getPlatform());
			sm.setString(4, vr.getChannel());
			sm.setString(5, vr.getVersion());
			sm.setString(6, vr.getImei());
			sm.setString(7, vr.getImsi());
			sm.setString(8, vr.getIp());
			sm.setString(9, vr.getParams());
			sm.setString(10, vr.getParam1());
			sm.setString(11, vr.getParam2());
			sm.setString(12, vr.getParam3());
			sm.setString(13, df.format(new Date(now)));
			sm.setLong(14, now);
			sm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			log.error("add visitRecord exception!", e);
		}finally{
		    MySQLDBIns.getInstance().release(conn);
		}
	}
	
	private boolean isExistTable(String tableName, Connection conn){
		boolean flag = false;
		PreparedStatement sm = null;
		try{
			String sql = "select `id` from `youhui_visit`.`"+ tableName +"` limit 1,1;";
		    sm = conn.prepareStatement(sql);
			flag = sm.execute();
		}catch (SQLException e) {
			flag = false;
		}
		return flag;
	}
	
	private void createTable(String tableName, Connection conn){
		PreparedStatement sm = null;
		try{
			String sql = "CREATE TABLE  `youhui_visit`.`"+ tableName +"` (`id` int(10) unsigned NOT NULL auto_increment,`interface_name` varchar(30) NOT NULL,`uid` varchar(20),`platform` varchar(20),`version` varchar(20),`channel` varchar(30),`imei` varchar(30),`imsi` varchar(30),`ip` varchar(15),`params` text,`param1` varchar(50),`param2` varchar(50),`param3` varchar(50),`time` varchar(25) NOT NULL,`timestamp` char(20) NOT NULL, PRIMARY KEY  (`id`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
			sm = conn.prepareStatement(sql);
			sm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			log.error("create visit_record table exception!", e);
		}
	}
	
	public static void main(String[] args) {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		System.out.println(VisitRecordManager.getInstance().isExistTable("visit_record_2013_07", conn));
		MySQLDBIns.getInstance().release(conn);
	}
}
