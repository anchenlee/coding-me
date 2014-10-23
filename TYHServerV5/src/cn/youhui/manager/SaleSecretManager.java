package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.log4j.Logger;

import cn.youhui.dao.MySQLDBIns;

/**
 * 特惠密语
 * @author lijun
 * @since 2013-09-28
 */
public class SaleSecretManager {
	
	private static final Logger log = Logger.getLogger(SaleSecretManager.class);
	private static SaleSecretManager instance;
	
	private SaleSecretManager() {
	}
	
	public static SaleSecretManager getInstance() {
		if (instance == null)
			instance = new SaleSecretManager();
		return instance;
	}
	
	public String getSecret(String saleId, String uid){
		String secret = null;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_datamining.tyh_sale_new_secret where `sale_id`=? and `uid`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, saleId);
			ps.setString(2, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				secret = rs.getString("secret");
			}else{
				String secrettmp = getRandSecret();
				int i = 0;
				int j = 0;
				while(i <1 && j<10){
					j++;
					sql = "insert into youhui_datamining.tyh_sale_new_secret (`sale_id`,`uid`,`secret`,`status`,`creat_time`,`use_time`)values(?,?,?,?,?,?);";
					ps = conn.prepareStatement(sql);
					ps.setString(1, saleId);
					ps.setString(2, uid);
					ps.setString(3, secrettmp);
					ps.setInt(4, 1);
					ps.setLong(5, System.currentTimeMillis());
					ps.setLong(6, 0l);
					i = ps.executeUpdate();
				}
				if(i > 0){
					secret = secrettmp;
				}
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return secret;
	}
	
    public static String key = "0123456789";

    /**
     * 产生18位的随机码,开头为001
     * @return
     */
	private String getRandSecret(){
		StringBuffer sb = new StringBuffer("001");
		Random rm = new Random();
		int i = 15;
		while(i > 0){
			i--;
			int index = (int)(rm.nextFloat() * key.length());
			index = Math.min(index, key.length()-1);
			index = Math.max(0, index);
			sb.append(key.charAt(index));
		}
		return sb.toString();
	}
}
