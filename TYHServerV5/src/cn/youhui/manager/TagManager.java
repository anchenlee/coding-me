package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.youhui.common.SepaConfig;
import cn.youhui.dao.MySQLDBIns;


public class TagManager {
	private static TagManager instance = null;
	
	public static TagManager getInstance(){
		if(instance == null) instance = new TagManager();
		return instance;
	}

	public Map<String, String> getTag2TagMap() {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` INNER JOIN (SELECT `id` AS pid FROM `youhui_datamining`.`m_discount_keywords`)as T2 on T2.pid = `parent_id` where `client_show`=1 ORDER BY `parent_id` ASC,`rank` ASC;";
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			while(rs.next()) {
				String parentId = rs.getString("parent_id");
				String childIds = map.get(parentId);
				if(childIds == null){
				    map.put(parentId, rs.getString("id"));
				}else{
					map.put(parentId, childIds + SepaConfig.TAG_SEPA + rs.getString("id")); 
				}
			}
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return map;
	}
	
	public Map<String, String> getTagid2nameMap() {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords`;";
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			while(rs.next()){
				map.put(rs.getString("keyword"), rs.getString("id"));
				map.put(rs.getString("id"), rs.getString("keyword"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return map;
	}
	
}
