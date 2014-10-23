package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.netting.bean.AD_Bean;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;

public class UpdatePriceTest {

	public static List<String> getADBean()
	{
		List<String> list = new ArrayList<String>();
		String sql = "SELECT item_id FROM youhui_datamining.m_discount_products where price_low > price_high limit 2000;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				list.add(rs.getString("item_id"));
			}
		}
		catch (Exception e) 
		{
			
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		
		return list;
	}
	
	
	public static boolean updatePrice(String itemid){
		
		boolean flag = false;
		String sql = "update youhui_datamining.m_discount_products set price_low =? where item_id=?;";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = DataSourceFactory.getConnection();
			
			ps = conn.prepareStatement(sql);
			try {
				String promoPrice = TaobaoAPI_DAO.getPromoPrice(itemid); 
				if(promoPrice != null && !"".equals(promoPrice)) 
				{
					ps.setString(1, promoPrice);
					ps.setString(2, itemid);
					
					ps = conn.prepareStatement("update youhui_datamining.m_discount_products set price_low =price_high where item_id=?;");
					ps.setString(1, itemid);
					ps.executeUpdate();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			DataSourceFactory.closeAll(rs, ps, conn);
		}
		return flag;
	}
	
	
	public static void excute(){
		List<String> list = getADBean();
		for(String itemid : list){
			System.out.println(itemid);
			updatePrice(itemid);
		}
	}
	
	public static void main(String[] args) {
		excute();
	}
	
}
