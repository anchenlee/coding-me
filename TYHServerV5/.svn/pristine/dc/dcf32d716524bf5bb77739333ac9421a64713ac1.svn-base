package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.youhui.dao.MySQLDBIns;

/**
 * 关键词搜索记录
 * @author lijun
 * @since 2013-09-13
 */
public class KeywordCountManager {
	
	private static final int TYPE_ITEM = 1;
	private static final int TYPE_SHOP = 2;
	private static KeywordCountManager instance = null;
	
	private KeywordCountManager(){}
	
	public static KeywordCountManager getInstance(){
		if(instance == null){
			instance = new KeywordCountManager();
		}
		return instance;
	}
	
	
	public void add(String keyword, String type, String uid){
		Connection conn = null;
		int typeint = TYPE_ITEM;
		if("shop".equalsIgnoreCase(type)){
			typeint = TYPE_SHOP;
		}
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into `youhui_datamining`.`tyh_keyword_count`(`keyword`,`uid`,`type`,`timestamp`,`time`)values(?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, keyword);
			ps.setString(2, uid);
			ps.setInt(3, typeint);
			long now = System.currentTimeMillis();
			ps.setLong(4, now);
			ps.setString(5, df.format(new Date(now)));
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
	}
}
