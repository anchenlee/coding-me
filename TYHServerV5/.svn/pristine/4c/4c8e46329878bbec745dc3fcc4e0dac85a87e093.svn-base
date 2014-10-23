package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.SuperDiscountForWebBean;
import cn.youhui.utils.SuperTools;



public class SuperDiscountForWebDAO {

	private static SuperDiscountForWebDAO instance=null;
	public static SuperDiscountForWebDAO getInstance(){
		if(instance==null){
			instance=new SuperDiscountForWebDAO();
		}
		return instance;
	}
	
	public List<String> gettagIds(String keywordName){
		Connection conn=null;
		List<String> list=new ArrayList<String>();
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="SELECT id FROM youhui_datamining.m_discount_keywords where keyword like ? ;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,keywordName);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				list.add(rs.getString("id"));
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public List<String> getItemIds(int tagId){
		Connection conn=null;
		List<String> list=new ArrayList<String>();
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="select item_id from youhui_datamining.m_tagtoitem where tag_id=? and rel_status=1";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1,tagId);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				list.add(rs.getString("item_id"));
			}
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public SuperDiscountForWebBean fck(String itemId,long discountTimestamp){
		Connection conn=null;
		SuperDiscountForWebBean sb=null;
		try{
			conn=MySQLDBIns.getInstance().getConnection();
			String sql="SELECT * FROM youhui_datamining.m_discount_products where item_id=?;";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1,itemId);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				sb=new SuperDiscountForWebBean();
				String img=rs.getString("pic_url");
				sb.setImgBig(img+"_b"+SuperTools.getImgType(img));
				sb.setImgSmall(img+"_s"+SuperTools.getImgType(img));
				sb.setIsSecondKill(0);
				sb.setItemId(itemId);
				sb.setTitle(rs.getString("title"));
				sb.setPriceHigh(rs.getDouble("price_high"));
				sb.setPriceLow(rs.getDouble("price_low"));
			}
			
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return sb;
	}
	
}
