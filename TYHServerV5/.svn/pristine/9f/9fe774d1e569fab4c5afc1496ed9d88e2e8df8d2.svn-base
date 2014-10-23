package cn.youhui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.GoodsTrade;

/**
 * 
 * @author 
 */
public class Admin_Goods_DAO
{
	
	/**
	 * 根据用户UID取交易成功订单信息
	 * @param UID
	 * @return
	 */
	public static  List<GoodsTrade> getGoodsTradeByUID(String UID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<GoodsTrade> goodsList = new ArrayList<GoodsTrade>();
		try 
		{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select 1 from dual;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, UID);
			rs = ps.executeQuery();
			while (rs.next()) {
				GoodsTrade gt = new GoodsTrade();
				goodsList.add(gt);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			MySQLDBIns.getInstance().release( conn);
		}
		return goodsList;
	}
	
	/**
	 * 根据用户itemId订单信息
	 * @param UID
	 * @return
	 */
	public static  GoodsTrade getGoodsTradeById(String id)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		GoodsTrade good = new GoodsTrade();
		try 
		{
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select 1 from dual;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			MySQLDBIns.getInstance().release( conn);
		}
		return good;
	}
	
}
