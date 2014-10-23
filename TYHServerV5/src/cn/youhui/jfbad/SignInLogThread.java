package cn.youhui.jfbad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.youhui.dao.MySQLDBIns;

public class SignInLogThread  extends Thread{
	
	private static DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	private String uid = "";
	
	private int type = 0;
	
	private int flag ;
	
	private String d = "";
	
	
	public SignInLogThread(String uid, int type, int flag, String d) {
		super();
		this.uid = uid;
		this.type = type;
		this.flag = flag;
		this.d = d;
	}

	@Override
	public void run() {
		add();
	}
	
	private void add(){
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into `tmp`.`signin_log` (`uid`,`date`,`flag`,`type`,`update_time`,`d`)values(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, uid);
			ps.setString(2, df.format(new Date()));
			ps.setInt(3, flag);
			ps.setInt(4, type);
			ps.setLong(5, System.currentTimeMillis());
			ps.setString(6, d);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
	}

}
