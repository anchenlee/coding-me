package cn.youhui.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.youhui.bean.City;
import cn.youhui.dao.MySQLDBIns;

public class CityManager {

	private static CityManager instance = null;
	
	private CityManager(){}
	
	public static CityManager getInstance(){
		if(instance == null) instance = new CityManager();
		return instance;
	}
	
	public City getCityByBdCityId(String id){
		City city = new City();
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "SELECT `id`,`name` FROM city.city where `baidu_city_id`=?;";
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				city.setName(rs.getString("name"));
				city.setId(rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return city;
	}
}
