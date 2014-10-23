package cn.youhui.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.TagBean;
import cn.youhui.bean.TagToItemBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.platform.db.SQL;


public class AdminTagItemDAO {

	public static boolean addItem(TeJiaGoodsBean bean){  
		boolean flag = false;
		
		String sql = "select * from `youhui_datamining`.`m_discount_products` where `item_id`= "+ bean.getItem_id();
		
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;
		try{ 
			conn = SQL.getInstance().getConnection();
			s = conn.prepareStatement(sql);
			rs = s.executeQuery(sql);
			if(rs.next()){
				sql = "update `youhui_datamining`.`m_discount_products` set `catid`='" + bean.getCatID() + "',`jfb_rate`=" + bean.getRate() + ",`status` = 0 ,`title`= '"+bean.getTitle()+"',"
					+ "`show_index`="+ bean.getShow_index() +",`price_high`= '"+bean.getPrice_high()+"',`price_low`='"+bean.getPrice_low()
					+ "',`discount`='"+ bean.getDiscount() +"',`pic_url`='"+bean.getPic_url()+ "',`click_url`='"+bean.getClickURL() 
					+ "',`commission`='"+bean.getCommission()+"',`commission_rate`='"+bean.getCommission_rate()+"',`update_time`='"
					+ System.currentTimeMillis() +"', `keyword`= '"+bean.getKeyword()+"', `width_m`= '"+bean.getWidth_m()+"', `height_m`= '"+bean.getHeight_m()
					+"', `width_b`= '"+bean.getWidth_b()+"', `height_b`= '"+bean.getHeight_b() +"', `width_310`= '"+bean.getWidth_310()+"', `height_310`= '"
					+bean.getHeight_310()+"', `width_170`= '"+bean.getWidth_170()+"', `height_170`= '"+bean.getHeight_170()+"',`reco_reason`='"+bean.getRecoReason()+"',`pic_cut_url`='"+bean.getPicCutUrl()+"',`baoyou`='"+bean.getBaoyou()+"' where `item_id`='"+bean.getItem_id()+"';";
				int i = s.executeUpdate(sql);
				if(i > 0){
					flag = true;
				}
			}else{
				sql = "INSERT INTO `youhui_datamining`.`m_discount_products`"
					+ "(`item_id`,`title`,`keyword`,"
					+ "`show_index`,`price_high`,`price_low`"
					+ ",`discount`,`pic_url`,`status`"
					+ ",`click_url`,`update_time`,`commission`,`commission_rate`,`width_m`,`height_m`,`width_b`,`height_b`,`width_310`,`height_310`,`width_170`,`height_170`,`jfb_rate`,`catid`,`reco_reason`,`pic_cut_url`,`baoyou`)VALUES('"
					+ bean.getItem_id()
					+ "','"
					+ bean.getTitle()
					+ "','"
					+ bean.getKeyword()
					+ "','"
					+ bean.getShow_index()
					+ "','"
					+ bean.getPrice_high()
					+ "','"
					+ bean.getPrice_low()
					+ "','"
					+ bean.getDiscount()
					+ "','"
					+ bean.getPic_url()
					+ "','"
					+ 0
					+ "','"
					+ bean.getClickURL()
					+ "','"
					+ System.currentTimeMillis()
					+ "','"
					+ bean.getCommission()
					+ "','"
					+ bean.getCommission_rate()
					+ "','"
					+ bean.getWidth_m()
					+ "','"
					+ bean.getHeight_m()
						+ "','"
					+ bean.getWidth_b()
					+ "','"
					+ bean.getHeight_b()
						+ "','"
					+ bean.getWidth_310()
					+ "','"
					+ bean.getHeight_310()
						+ "','"
					+ bean.getWidth_170()
					+ "','"
					+ bean.getHeight_170()
					+ "','"
					+ bean.getRate()
					+ "','"
					+ bean.getCatID()
					+ "','"+bean.getRecoReason()+"','"+bean.getPicCutUrl()+"','"+bean.getBaoyou()+"');";
				int i = s.executeUpdate(sql);
				if(i > 0){
					flag = true;
				}
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			SQL.getInstance().release(s, conn);
		}
		return flag;
	}
	
	
	public static int addItem2Tag(String itemId, String tagId,Connection conn){
		int rank = 0;
		if(tagId == null || "".equals(tagId) || itemId == null||"".equals(itemId)){
			return rank;
		}
		String flag = isLocked(tagId,conn);	//查询该标签下锁定的商品个数
		
		String sql = "";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			
			rank = getMinRank(tagId, conn);
			
			List<TagToItemBean> list = null;
			if(Integer.parseInt(flag) > 0 && Integer.parseInt(flag) <= 20){
				sql = "select * from `youhui_datamining`.`m_tagtoitem` where `tag_id`= "+ tagId +" ";//查出前?
				sql = sql +" AND `rel_status` = 1 order by rank asc limit " + flag + ";";
				
				list = new ArrayList<TagToItemBean>();
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while (rs.next()){
					TagToItemBean bean = new TagToItemBean();
					
					bean.setId(rs.getString("id"));
					bean.setItemid(rs.getString("item_id"));
					bean.setRank(rs.getInt("rank"));
					bean.setTagid(tagId);
					
					list.add(bean);
				}
			}
			
			String sql1 = "select `id` from `youhui_datamining`.`m_tagtoitem` where `tag_id`= "+ tagId +" and `item_id`= "+ itemId +" ;";
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			if (rs.next()){
				String id = rs.getString("id");
				sql = "update `youhui_datamining`.`m_tagtoitem` set `rel_status` = 1,`rank`= "+ rank +" where `id`= "+ id +";";
			}else{
			    sql = "INSERT INTO `youhui_datamining`.`m_tagtoitem`(`tag_id`,`item_id`,`rank`) values ("+tagId+","+ itemId +","+rank +");";
			}
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			if(list != null && list.size() > 0) {	//修改前?条数据rank
				int rank1 = rank - 1;
				for(int i = list.size() - 1; i >= 0; i--){
					sql = "update `youhui_datamining`.`m_tagtoitem` set `rank`= "+ rank1 +" where `id`= "+ list.get(i).getId() +" ;";
					rank1--;
					ps = conn.prepareStatement(sql);
					ps.executeUpdate();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return rank;
	}
	
	/*
	 * 查询该标签下锁定的商品个数
	 */
	public static String isLocked(String tagId,Connection conn){
		String lockstatue = "0";
		if(tagId == null||"".equals(tagId)){
			return "0";
		}
		
		String sql = "SELECT `lockstatue` FROM youhui_datamining.m_discount_keywords where id=?;";
		
		PreparedStatement prestmt = null;
		ResultSet rs = null;
		try{
			prestmt = conn.prepareStatement(sql);
			
			prestmt.setString(1, tagId);
			
			rs = prestmt.executeQuery();
			if (rs.next()){
				lockstatue = rs.getString("lockstatue");
			}
		}catch (Exception e){
			e.printStackTrace();
			return "0";
		}
		return lockstatue;
	}
	
	/**
	 * 获取标签下最小的rank
	 */
	public static int getMinRank(String tagId, Connection conn){
		int rank = 0;
		
		String sql = "select min(rank) as mrank from `youhui_datamining`.`m_tagtoitem` where `tag_id` = "+ tagId +" AND `rel_status` = 1;";
		
		Statement st = null;
		ResultSet rs = null;
		try{
			st = conn.createStatement();	

			rs = st.executeQuery(sql);
			if(rs.next()){
				rank = rs.getInt("mrank");
				rank--;
			}
		}catch (Exception e){
			e.printStackTrace();
			return rank;
		}
		
		return rank;
	}
	
	/**
	 * 根据商品id获取商品信息
	 * @param itemId
	 * @return
	 */
	public static TeJiaGoodsBean getDiscountProduct(String itemId) 
	{
		TeJiaGoodsBean bean = null;
		
		Connection conn = null;
		PreparedStatement perstamt = null;
		ResultSet rs = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` WHERE `item_id` = ? AND `status` = 0;";
			perstamt = conn.prepareStatement(sql);

			perstamt.setString(1, itemId);
			
			rs = perstamt.executeQuery();
			if (rs.next())
			{
				bean = new TeJiaGoodsBean();
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setStatus(rs.getInt("status"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setCommission(rs.getString("commission"));
				bean.setCommission_rate(rs.getString("commission_rate"));
				bean.setClickURL(rs.getString("click_url"));
				bean.setIsUpdateed(rs.getInt("isupdate"));
				bean.setRate(rs.getDouble("jfb_rate"));
				bean.setCatID(rs.getString("catid"));
			}

		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		} 
		finally 
		{
			SQL.getInstance().release(perstamt, conn);
		}
		return bean;
	}
	
	public static void addItemJfbRateHistory(String item_id, String new_jfb_rate)
	{
		if (item_id == null || item_id.equalsIgnoreCase("null") || item_id.equals(""))
		{
			return;
		}
		double rate = isExit(item_id);
		if(rate == Double.parseDouble(new_jfb_rate)){
			return;
		}
		String sql1 = "SELECT MAX(`id`) AS lastid FROM `youhui_datamining`.`item_jfb_rate` WHERE `itemid` = ? AND `end_time` = '0';";
		String sql2 = "UPDATE `youhui_datamining`.`item_jfb_rate` SET `end_time` = ? WHERE `id` = ?;";
		String sql3 = "UPDATE `youhui_datamining`.`m_discount_products` a SET a.`jfb_rate` = ? WHERE a.`item_id` = ?;";
		String sql4 = "INSERT into `youhui_datamining`.`item_jfb_rate`(`itemid`,`jfb_rate`,`start_time`,`end_time`) VALUES (?,?,?,?);";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{ 
			String nowTimeMillis = System.currentTimeMillis() + "";
			
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, item_id);
			rs = ps.executeQuery();
			if (rs.next())
			{
				String lastID = rs.getString("lastid");
				if (lastID != null && !"".equals(lastID) && !"null".equalsIgnoreCase(lastID))
				{
					ps = conn.prepareStatement(sql2);
					ps.setString(1, nowTimeMillis);
					ps.setString(2, lastID);
					ps.executeUpdate();
				}
			}
			
			ps = conn.prepareStatement(sql3);
			ps.setString(1, new_jfb_rate);
			ps.setString(2, item_id);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sql4);
			ps.setString(1, item_id);
			ps.setString(2, new_jfb_rate);
			ps.setString(3, nowTimeMillis);
			ps.setString(4, "0");
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			SQL.getInstance().release(ps, conn);
		}
		return;
	}
	
	public static List<TagToItemBean> getRankItemList(String tagid,Connection conn) 
	{
		List<TagToItemBean> list = new ArrayList<TagToItemBean>();
		
		String flag = AdminTagItemDAO.isLocked(tagid,conn) ;
		String sql = "select * from `youhui_datamining`.`m_tagtoitem` where `tag_id`= "+ tagid +" AND `rel_status` = 1";//查出前?
		
		Statement s = null;
		ResultSet rs = null;
		try 
		{
			s = conn.createStatement();
			
			if(Integer.parseInt(flag) <= 20 && Integer.parseInt(flag) > 0)
			{
				sql = sql + " order by rank asc limit " + flag + ";";
				
				rs = s.executeQuery(sql);
				
				while(rs.next())
				{
					TagToItemBean bean = new TagToItemBean();
					
					bean.setItemid(rs.getString("item_id"));
					bean.setRank(rs.getInt("rank"));
					bean.setTagid(tagid);
					
					list.add(bean);
				}
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getKeywordsForPaixu(String tagId) 
	{
		List<String> list = new ArrayList<String>();
		
		ResultSet rs = null;
		Connection conn = null;
		Statement s = null;
		try
		{
			conn = SQL.getInstance().getConnection();
			s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '" + tagId + "'  and `client_show`=1;";
			rs = s.executeQuery(sql);
			while (rs.next()) 
			{
				list.add(rs.getString("id"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			SQL.getInstance().release(s, conn);
		}
		return list;
	}
	
	/**
	 * 删除标签与商品的归属关系
	 * @return
	 */
	public static boolean delItem2Tag(String item_id, String tag_id)
	{
		boolean flag = false;
		
		String sql1 = "UPDATE `youhui_datamining`.`m_tagtoitem` SET `rel_status` = 2 WHERE `item_id` = '" + item_id + "' AND `tag_id` = '" + tag_id + "'";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql1);
			int res = ps.executeUpdate();
			if (res > 0)
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			flag = false;
			e.printStackTrace();
		} 
		finally 
		{
			SQL.getInstance().release(ps, conn);
		}
		
		return flag;
	}
	
	public static boolean isExit(String itemId,String tagId){
		boolean flag = false;
		String query_sql = "SELECT DISTINCT * FROM `youhui_datamining`.`m_tagtoitem`  WHERE `tag_id` ='"+tagId+"'  AND item_id = '" + itemId + "';";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query_sql);
			if (rs.next())
			{
				flag = true;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			SQL.getInstance().release(st, conn);
		}
		return flag;
	}
	
	public static ArrayList<String> getTagListByItemID(String item_id)
	{
		ArrayList<String> tagid_list = new ArrayList<String>();
		
		String query_sql = "SELECT DISTINCT a.tag_id FROM `youhui_datamining`.`m_tagtoitem` a WHERE a.`rel_status` = 1 AND a.item_id = '" + item_id + "';";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query_sql);
			while (rs.next())
			{
				String tag_id = rs.getString("tag_id");
				if (tag_id != null && !tag_id.equals(""))
				{
					tagid_list.add(tag_id);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			SQL.getInstance().release(st, conn);
		}
		
		return tagid_list;
	}
	
	public static List<TagBean> getTagItemListByItemID(String item_id)
	{
		List<TagBean> tagid_list = new ArrayList<TagBean>();
		
		String query_sql = "SELECT b.id,b.keyword FROM `youhui_datamining`.`m_tagtoitem` a  ,`youhui_datamining`.`m_discount_keywords` b WHERE a.`rel_status` = 1 AND a.item_id = '" + item_id + "' and b.id= a.tag_id;";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query_sql);
			while (rs.next())
			{
				TagBean bean = new TagBean();
				bean.setTag_id(rs.getString("id"));
				bean.setTag_name(rs.getString("keyword"));
				tagid_list.add(bean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			SQL.getInstance().release(st, conn);
		}
		
		return tagid_list;
	}
	
	
	public static List<TagBean> getTagItemListByItemID(String item_id,Connection conn)
	{
		List<TagBean> tagid_list = new ArrayList<TagBean>();
		
		String query_sql = "SELECT b.id,b.keyword FROM `youhui_datamining`.`m_tagtoitem` a  ,`youhui_datamining`.`m_discount_keywords` b WHERE a.`rel_status` = 1 AND a.item_id = '" + item_id + "' and b.id= a.tag_id;";
		
		Statement st = null;
		ResultSet rs = null;
		try 
		{
			st = conn.createStatement();
			rs = st.executeQuery(query_sql);
			while (rs.next())
			{
				TagBean bean = new TagBean();
				bean.setTag_id(rs.getString("id"));
				bean.setTag_name(rs.getString("keyword"));
				tagid_list.add(bean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return tagid_list;
	}
	
	public static boolean delItem(String item_id)
	{
		boolean flag = false;
		
		String sql1 = "UPDATE `youhui_datamining`.`m_tagtoitem` SET `rel_status` = 2 WHERE `item_id` = '" + item_id + "';";
		
		String sql2 = "UPDATE `youhui_datamining`.`m_discount_products` SET `status` = 1 WHERE `item_id` = '" + item_id + "';";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			conn = SQL.getInstance().getConnection();
			
			ps = conn.prepareStatement(sql1);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sql2);
			ps.executeUpdate();
			
			flag = true;
		}
		catch (Exception e) 
		{
			flag = false;
			e.printStackTrace();
		} 
		finally 
		{
			SQL.getInstance().release(ps, conn);
		}
		
		return flag;
	}
	
	public static double isExit(String itemid){
		double rate = -1;;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT jfb_rate FROM youhui_datamining.item_jfb_rate where itemid=? order by id desc limit 1;";
		
		try {
			conn = SQL.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemid);
			
			rs = ps.executeQuery();
			if(rs.next()){
				rate = rs.getDouble("jfb_rate");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			SQL.getInstance().release(ps, conn);
		}
		return rate;
	}
	
}
