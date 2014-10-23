package cn.youhui.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.youhui.bean.ExchAddress;
import cn.youhui.dao.MySQLDBIns;


public class ExchAddreManager {
	
	protected static Logger log = Logger.getLogger(ExchAddreManager.class);
	private static ExchAddreManager instance = null;
	
	private ExchAddreManager(){
	}
	
	public static ExchAddreManager getInstance(){
		if(instance == null) instance = new ExchAddreManager();
		return instance;
	}
	
	public String add(ExchAddress bean){
			String addId = null;
			Connection conn = null;
			try{
				conn = MySQLDBIns.getInstance().getConnection();
				String sql = "insert into  youhui_exchange.exch_address(`uid`, `rec_name`, `rec_tel`,`postcode`, `province`," +
						" `city`,`district`, `address`) values(?,?,?,?,?,?,?,?);";
				PreparedStatement s = conn.prepareStatement(sql);
				s.setString(1, bean.getUid());
				s.setString(2, bean.getRecName());
				s.setString(3, bean.getRecTel());
				s.setString(4, bean.getPostCode());
				s.setString(5, bean.getProvince());
				s.setString(6, bean.getCity());
				s.setString(7, bean.getDistrict());
				s.setString(8, bean.getAddress());
				int i = s.executeUpdate();
				if(i > 0){
				ResultSet rs = conn.createStatement().executeQuery("SELECT LAST_INSERT_ID();");
				if(rs.next()){
					addId = rs.getString(1);
				}
				}
			}catch(Exception e){
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}finally{
				if(conn!=null){
					MySQLDBIns.getInstance().release(conn);
				}
			}
			return addId;
	}
	
	public boolean update(ExchAddress bean){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "update youhui_exchange.exch_address set `uid`=?, `rec_name`=?, `rec_tel`=?,`postcode`=?, `province`=?," +
					" `city`=?,`district`=?, `address`=?where address_id =?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, bean.getUid());
			s.setString(2, bean.getRecName());
			s.setString(3, bean.getRecTel());
			s.setString(4, bean.getPostCode());
			s.setString(5, bean.getProvince());
			s.setString(6, bean.getCity());
			s.setString(7, bean.getDistrict());
			s.setString(8, bean.getAddress());
			s.setString(9, bean.getAddressId());
			int i = s.executeUpdate();
			if(i>0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}finally{
			if(conn!=null){
				MySQLDBIns.getInstance().release(conn);
			}
		}
		return flag;
     }
	
	public boolean delete(String addressId){
		boolean flag = false;
		Connection conn = null;
		try{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "delete from  youhui_exchange.exch_address where address_id =?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, addressId);
			int i = s.executeUpdate();
			if(i>0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}finally{
			if(conn!=null){
				MySQLDBIns.getInstance().release(conn);
			}
		}
		return flag;
	}
	
	public List<ExchAddress> getbyUid(String uid){
		List<ExchAddress> list = new ArrayList<ExchAddress>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			String sql = "select * from youhui_exchange.exch_address where uid="+ uid +" order by creat_time desc;";
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				ExchAddress bean = new ExchAddress();
				bean.setAddressId(rs.getString("address_id"));
				bean.setRecName(rs.getString("rec_name"));
				bean.setRecTel(rs.getString("rec_tel"));
				bean.setProvince(rs.getString("province"));
				bean.setCity(rs.getString("city"));
				bean.setDistrict(rs.getString("district"));
				bean.setAddress(rs.getString("address"));
				bean.setPostCode(rs.getString("postcode"));
				bean.setUid(uid);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQLDBIns.getInstance().release(conn);
		return list;
	}
	
}
