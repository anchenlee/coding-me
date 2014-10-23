package com.netting.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.bean.TongjiBean;
import com.netting.bean.TongjiRequest;
import com.netting.db.DataSourceFactory;
import com.netting.util.CodeUtil;


public class Admin_Tongji_DAO {

	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog(Admin_Tongji_DAO.class);
	private static String tttj = "590";
	private static String jhs = "589";
	
	/**
	 * 获取下载渠道
	 * @return
	 */
	public static List<String> getChannel() {
		List<String> list = new ArrayList<String>();
		PreparedStatement s = null;
		Connection conn = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM youhui_v3.user_channel group by channel";
		
		try {
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			
			rs = s.executeQuery();
			
			while(rs.next()) 
			{
				if(rs.getString("channel")!=null&&!"".equals(rs.getString("channel")))
				list.add(rs.getString("channel"));
			}
		} catch (Exception e) {
			logger.error("Admin_Tongji_DAO.getChannel error:", e);
		} finally {
			DataSourceFactory.closeAll(rs, s, conn);
		}
		return list;
	}
	
	public static List<TongjiBean> getTongjiData(TongjiRequest tjReq,String leibie) 
	{
		List<TongjiBean> list = new ArrayList<TongjiBean>();
		PreparedStatement s = null;
		Connection conn = null;
		ResultSet rs = null;
		
		String sql = getSQL(tjReq,leibie);
		if(sql==null||"".equals(sql)) 
		{
			return null;
		}
		try {
			conn = DataSourceFactory.getConnection();
			s = getPreparedStatement(conn, sql, tjReq, leibie);
//			System.out.println(s);
			rs = s.executeQuery();
			
			while(rs.next()) 
			{
				TongjiBean bean = new TongjiBean();
				bean.setTitle(rs.getString("time"));
				bean.setValue(rs.getString("count"));
				list.add(bean);
			}
		} catch (Exception e) {
			logger.error("Admin_Tongji_DAO.getTongjiData error:", e);
		} finally {
			DataSourceFactory.closeAll(rs, s, conn);
		}
		
		return list;
	}
	
	public static String getSQL(TongjiRequest tjReq,String leibie)
	{
		String sql = "";
		//商品交易数据统计
		if(tjReq!=null&&tjReq.getType().equals("itemTrade"))
		{
			if(leibie.equals("1"))	//商品交易
			{
				if(tjReq.getFrequency().equals("1")) 
				{
					sql = "select left(pay_time,7) as time, count(*) as count from youhui_cn_fanli.duoduo_tradelist where num_iid=? and pay_time between ? and ? group by time order by time desc";
				}
				else if(tjReq.getFrequency().equals("2")) 
				{
					sql = "select left(pay_time,10) as time, count(*) as count from youhui_cn_fanli.duoduo_tradelist where num_iid=? and pay_time between ? and ? group by time order by time desc";
				}
				else 
				{
					sql = "select left(pay_time,13) as time, count(*) as count from youhui_cn_fanli.duoduo_tradelist where num_iid=? and pay_time between ? and ? group by time order by time desc";						
				}
			}
			else if(leibie.equals("2"))	//商品订单
			{
				if(tjReq.getFrequency().equals("1")) 
				{
					sql ="select left(createtime,7) as time , count(*) as count from tradefind.tradeorder where itemid=? and createtime between ? and ?  group by time order by time desc";
				}
				else 
				{
					sql= "select  createtime as time, count(*) as count   from tradefind.tradeorder where itemid=? and createtime between ? and ?  group by time order by time desc" ;
				}
			}
			else if(leibie.equals("3"))//商品订单成功
			{
				if(tjReq.getFrequency().equals("1")) 
				{
					sql ="select left(createtime,7) as time , count(*) as count from tradefind.tradeorder where itemid=? and createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款') group by time order by time desc";
				}
				else 
				{
					sql= "select  createtime as time, count(*) as count   from tradefind.tradeorder where itemid=? and createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款')  group by time order by time desc" ;
				}
				
			}
		}
		//标签下商品数据统计
		else if(tjReq!=null&&tjReq.getType().equals("targetItemTrade"))
		{
			if(leibie.equals("1")) 	//标签下商品交易
			{
				if(tjReq.getKeyword().equals(jhs))	//聚划算
				{
					if(tjReq.getFrequency().equals("1")) 
					{
						sql = "select left(pay_time,7) as time ,count(*) as count from youhui_cn_fanli.duoduo_tradelist where shop_title like ? and pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					}
					else if(tjReq.getFrequency().equals("2")) 
					{
						sql = "select  left(pay_time,10) as time , count(*) as count from youhui_cn_fanli.duoduo_tradelist where shop_title like ? and pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					}
					else
					{ 
						sql = "select left(pay_time,13) as time ,count(*) as count  from youhui_cn_fanli.duoduo_tradelist where shop_title like ? and pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";	
					}
					
				}
				else if(tjReq.getKeyword().equals(tttj)) 	//天天特价
				{
					if(tjReq.getFrequency().equals("1")) 
					{
						sql = "select  left(pay_time,7) as time ,count(*) as count from youhui_cn_fanli.duoduo_tradelist where item_title like ? and pay_time!='0000-00-00 00:00:00' and pay_time between ? and ?  group by time order by time desc";
					}
					else if(tjReq.getFrequency().equals("2"))
					{
						sql = "select  left(pay_time,10) as time , count(*) as count from youhui_cn_fanli.duoduo_tradelist where item_title like ? and pay_time!='0000-00-00 00:00:00' and pay_time between ? and ?  group by time  order by time desc";
					}
					else 
					{
						sql = "select left(pay_time,13) as time ,count(*) as count  from youhui_cn_fanli.duoduo_tradelist where item_title like ? and pay_time!='0000-00-00 00:00:00' and pay_time between ? and ?  group by time  order by time desc";
					}
					
				}
				else 	//其他标签
				{
					String ctagids = "";
					if(tjReq.getKeyword() != null && !tjReq.getKeyword().equals("613") && !tjReq.getKeyword().equals("601")){//优质精选和天猫特卖不通过子标签查询
						ctagids = Admin_Tag_Item_DAO.getCtagids(tjReq.getKeyword());//判断标签下是否有子标签，通过子标签来查询
					}
					String tiaojian = "";
					if(ctagids != null && !"".equals(ctagids)){
							String[] ctag = ctagids.split(",");
							for(String s : ctag){
								tiaojian =tiaojian + " tag_id = '"+s+"'"+" or";
							}
							if(tiaojian.length() >2){				
								tiaojian = tiaojian.substring(0, tiaojian.length()-2);
							}
					}
					if(tjReq.getFrequency().equals("1")) 
					{
						if(ctagids != null && !"".equals(ctagids)){
							sql = "select left(pay_time,7) as time ,count(*) as count from youhui_cn_fanli.duoduo_tradelist join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where "+tiaojian+" ) as t) on duoduo_tradelist.num_iid = t.item_id  and pay_time between ? and ?  group by time order by time desc";
						}
						else{							
							sql = "select left(pay_time,7) as time ,count(*) as count from youhui_cn_fanli.duoduo_tradelist join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where tag_id =?) as t) on duoduo_tradelist.num_iid = t.item_id  and pay_time between ? and ?  group by time order by time desc";
						}
					}
					else if(tjReq.getFrequency().equals("2")) 
					{
						if(ctagids != null && !"".equals(ctagids)){
							sql = "select left(pay_time,10) as time,count(*) as count from youhui_cn_fanli.duoduo_tradelist join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where "+tiaojian+" ) as t) on duoduo_tradelist.num_iid = t.item_id  and pay_time between ? and ?  group by time order by time desc";
						}
						else{									
							sql = "select left(pay_time,10) as time,count(*) as count from youhui_cn_fanli.duoduo_tradelist join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where tag_id =?) as t) on duoduo_tradelist.num_iid = t.item_id  and pay_time between ? and ?  group by time order by time desc";
						}
					}
					else 
					{
						if(ctagids != null && !"".equals(ctagids)){
							sql = "select left(pay_time,13) as time ,count(*) as count from youhui_cn_fanli.duoduo_tradelist join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where "+tiaojian+" ) as t) on duoduo_tradelist.num_iid = t.item_id  and pay_time between ? and ?  group by time order by time desc";
						}
						else{								
							sql = "select left(pay_time,13) as time ,count(*) as count from youhui_cn_fanli.duoduo_tradelist join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where tag_id =?) as t) on duoduo_tradelist.num_iid = t.item_id  and pay_time between ? and ?  group by time order by time desc";
						}
					}
					
				}
			}
			else if(leibie.equals("2"))	//标签下商品订单
			{
				if(tjReq.getKeyword().equals(jhs))	//聚划算
				{
					if(tjReq.getFrequency().equals("1")) 
					{						
							sql = "select left(createtime,7) as time ,count(*) as count from tradefind.tradeorder where shopname like ? and createtime between ? and ?  group by time  order by time desc";
					}
					else  
					{
						sql= "select createtime as time,count(*) as count  from tradefind.tradeorder where shopname like ? and createtime between ? and ? group by time  order by time desc" ;
					}
					
				}
				else if(tjReq.getKeyword().equals(tttj)) 	//天天特价
				{
					if(tjReq.getFrequency().equals("1")) 
					{
						sql = "select left(createtime,7) as time, count(*) as count  from tradefind.tradeorder where itemtitle like ? and createtime between ? and ?  group by time  order by time desc";
					}
					else 
					{
						sql = "select createtime as time, count(*) as count  from tradefind.tradeorder where itemtitle like ? and createtime between ? and ?  group by time  order by time desc" ;
					}
					
				}
				else 	//其他标签
				{
					String ctagids = "";
					if(tjReq.getKeyword() != null && !tjReq.getKeyword().equals("613") && !tjReq.getKeyword().equals("601")){//优质精选和天猫特卖不通过子标签查询
						ctagids = Admin_Tag_Item_DAO.getCtagids(tjReq.getKeyword());//判断标签下是否有子标签，通过子标签来查询
					}
					String tiaojian = "";
					if(ctagids != null && !"".equals(ctagids)){
							String[] ctag = ctagids.split(",");
							for(String s : ctag){
								tiaojian =tiaojian + " tag_id = '"+s+"'"+" or";
							}
							if(tiaojian.length() >2){				
								tiaojian = tiaojian.substring(0, tiaojian.length()-2);
							}
					}
					if(tjReq.getFrequency().equals("1")) 
					{
						if(ctagids != null && !"".equals(ctagids)){
							sql ="select left(createtime,7) as time ,count(tradenum) as count FROM tradefind.tradeorder join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where "+tiaojian+" ) as t) on tradeorder.itemid=t.item_id  and createtime between ? and ?  group by time order by time desc";
						}
						else {							
							sql ="select left(createtime,7) as time ,count(tradenum) as count FROM tradefind.tradeorder join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where tag_id =?) as t) on tradeorder.itemid=t.item_id  and createtime between ? and ?  group by time order by time desc";
						}
					}
					else 
					{
						if(ctagids != null && !"".equals(ctagids)){
							sql = "select createtime as time,count(tradenum) as count   FROM tradefind.tradeorder join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where "+tiaojian+" ) as t) on tradeorder.itemid=t.item_id  and createtime between ? and ?  group by time order by time desc" ;
						}
						else {							
							sql = "select createtime as time,count(tradenum) as count   FROM tradefind.tradeorder join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where tag_id =?) as t) on tradeorder.itemid=t.item_id  and createtime between ? and ?  group by time order by time desc" ;
						}
					}
					
				}
			}
			else if(leibie.equals("3")) 	//标签下商品订单成功
			{
				if(tjReq.getKeyword().equals(jhs))	//聚划算
				{
					if(tjReq.getFrequency().equals("1"))
					{
						sql = "select left(createtime,7) as time ,count(*) as count from tradefind.tradeorder where shopname like ? and createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款' or orderstatus like '订单结算')  group by time  order by time desc";
					}
					else 
					{
						sql= "select createtime as time,count(*) as count  from tradefind.tradeorder where shopname like ? and createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款' or orderstatus like '订单结算')  group by time  order by time desc" ;
					}
					
				}
				else if(tjReq.getKeyword().equals(tttj)) 	//天天特价
				{
					if(tjReq.getFrequency().equals("1")) 
					{
						sql = "select left(createtime,7) as time, count(*) as count  from tradefind.tradeorder where itemtitle like ? and createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款' or orderstatus like '订单结算')  group by time  order by time desc";
					}
					else 
					{
						sql = "select createtime as time, count(*) as count  from tradefind.tradeorder where itemtitle like ? and createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款' or orderstatus like '订单结算')  group by time  order by time desc" ;
					}
					
				}
				else 	//其他标签
				{
					String ctagids = "";
					if(tjReq.getKeyword() != null && !tjReq.getKeyword().equals("613") && !tjReq.getKeyword().equals("601")){//优质精选和天猫特卖不通过子标签查询
						ctagids = Admin_Tag_Item_DAO.getCtagids(tjReq.getKeyword());//判断标签下是否有子标签，通过子标签来查询
					}
					String tiaojian = "";
					if(ctagids != null && !"".equals(ctagids)){
							String[] ctag = ctagids.split(",");
							for(String s : ctag){
								tiaojian =tiaojian + " tag_id = '"+s+"'"+" or";
							}
							if(tiaojian.length() >2){				
								tiaojian = tiaojian.substring(0, tiaojian.length()-2);
							}
					}
					if(tjReq.getFrequency().equals("1")) 
					{
						if(ctagids != null && !"".equals(ctagids)){
							sql ="select left(createtime,7) as time ,count(tradenum) as count FROM tradefind.tradeorder join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where  "+tiaojian+" ) as t) on tradeorder.itemid=t.item_id   and createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款' or orderstatus like '订单结算')  group by time order by time desc";
						}
						else{							
							sql ="select left(createtime,7) as time ,count(tradenum) as count FROM tradefind.tradeorder join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where tag_id =?) as t) on tradeorder.itemid=t.item_id   and createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款' or orderstatus like '订单结算')  group by time order by time desc";
						}
					}
					else 
					{
						if(ctagids != null && !"".equals(ctagids)){
							sql = "SELECT createtime as time,count(tradenum) as count   FROM tradefind.tradeorder join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where  "+tiaojian+" ) as t) on tradeorder.itemid=t.item_id and createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款' or orderstatus like '订单结算') group by time order by time desc ;" ;	
						}
						else{							
							sql = "SELECT createtime as time,count(tradenum) as count   FROM tradefind.tradeorder join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where tag_id =?) as t) on tradeorder.itemid=t.item_id and createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款' or orderstatus like '订单结算') group by time order by time desc ;" ;
						}
					}
					
				}
			}
		}
		else if(tjReq!=null&&tjReq.getType().equals("ItemOrderTotalTongji"))
		{
			if(leibie.equals("1"))	//商品交易
			{
				if(tjReq.getFrequency().equals("1")) sql = "select left(pay_time,7) as time, count(*) as count from youhui_cn_fanli.duoduo_tradelist where  pay_time between ? and ? group by time order by time desc";
				else if(tjReq.getFrequency().equals("2")) sql = "select left(pay_time,10) as time, count(*) as count from youhui_cn_fanli.duoduo_tradelist where   pay_time between ? and ? group by time order by time desc";
				else sql = "select left(pay_time,13) as time, count(*) as count from youhui_cn_fanli.duoduo_tradelist where  pay_time between ? and ? group by time order by time desc";
				
			}
			else if(leibie.equals("2"))	//商品订单
			{
				if(tjReq.getFrequency().equals("1")) sql ="select left(createtime,7) as time , count(*) as count from tradefind.tradeorder where  createtime between ? and ?  group by time order by time desc";
				else sql= "select  createtime as time, count(*) as count   from tradefind.tradeorder where  createtime between ? and ?  group by time order by time desc" ;
				
			}
			else if(leibie.equals("3"))	//商品订单成功
			{
				if(tjReq.getFrequency().equals("1")) sql ="select left(createtime,7) as time , count(*) as count from tradefind.tradeorder where  createtime between ? and ?  and (orderstatus like '订单成功' or orderstatus like '订单付款' or orderstatus like '订单结算')  group by time order by time desc";
				else sql= "select  createtime as time, count(*) as count   from tradefind.tradeorder where   createtime between ? and ? and (orderstatus like '订单成功' or orderstatus like '订单付款' or orderstatus like '订单结算')    group by time order by time desc" ;
				
			}
		}
		else if(tjReq!=null&&tjReq.getType().equals("itemJine"))
		{
			if(leibie.equals("1"))	//商品交易
			{
				if(tjReq.getFrequency().equals("1")) {
					if(tjReq.getKeyword().equals(tttj)) sql = "select  left(pay_time,7) as time ,sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist where item_title like ? and pay_time!='0000-00-00 00:00:00' and pay_time between ? and ?  group by time order by time desc";
					else if(tjReq.getKeyword().equals(jhs)) sql = "select left(pay_time,7) as time ,sum(pay_price)  as count from youhui_cn_fanli.duoduo_tradelist where shop_title like ? and pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					else if(tjReq.getKeyword().length()>6) sql = "select left(pay_time,7) as time ,sum(pay_price)  as count from youhui_cn_fanli.duoduo_tradelist where num_iid = ? and pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					else if(tjReq.getKeyword().equals("all")) sql = "select left(pay_time,7) as time ,sum(pay_price)  as count from youhui_cn_fanli.duoduo_tradelist where pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					else sql = "select left(pay_time,7) as time ,sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where tag_id =?) as t) on duoduo_tradelist.num_iid = t.item_id  and pay_time between ? and ?  group by time order by time desc";
				}
				else if(tjReq.getFrequency().equals("2")) {
					if(tjReq.getKeyword().equals(tttj)) sql = "select  left(pay_time,10) as time ,sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist where item_title like ? and pay_time!='0000-00-00 00:00:00' and pay_time between ? and ?  group by time order by time desc";
					else if(tjReq.getKeyword().equals(jhs)) sql = "select  left(pay_time,10) as time , sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist where shop_title like ? and pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					else if(tjReq.getKeyword().length()>6) sql = "select  left(pay_time,10) as time , sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist where num_iid = ? and pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					else if(tjReq.getKeyword().equals("all")) sql = "select  left(pay_time,10) as time ,sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist where  pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					else sql = "select left(pay_time,10) as time,sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where tag_id =?) as t) on duoduo_tradelist.num_iid = t.item_id  and pay_time between ? and ?  group by time order by time desc";
				}
				else {
					if(tjReq.getKeyword().equals(tttj)) sql = "select  left(pay_time,13) as time ,sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist where item_title like ? and pay_time!='0000-00-00 00:00:00' and pay_time between ? and ?  group by time order by time desc";
					else if(tjReq.getKeyword().equals(jhs)) sql = "select  left(pay_time,13) as time , sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist where shop_title like ? and pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					else if(tjReq.getKeyword().length()>6) sql = "select  left(pay_time,13) as time , sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist where num_iid = ? and pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					else if(tjReq.getKeyword().equals("all")) sql = "select  left(pay_time,13) as time , sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist where  pay_time!='0000-00-00 00:00:00'  and pay_time between ? and ?  group by time order by time desc";
					else sql = "select left(pay_time,13) as time ,sum(pay_price) as count from youhui_cn_fanli.duoduo_tradelist join  ((SELECT item_id FROM youhui_datamining.m_tagtoitem where tag_id =?) as t) on duoduo_tradelist.num_iid = t.item_id  and pay_time between ? and ?  group by time order by time desc";
				}
			}

		}
		else if(tjReq!=null&&tjReq.getType().equals("userRegister"))
		{
			if(leibie.equals("1"))	//
			{
				String channel = "";
				String table = " ";
				if(tjReq.getChannel()!=null&&!"".equals(tjReq.getChannel())) {
					table = ",youhui_v3.user_channel b ";
					channel = " and a.uid=b.uid  and b.channel="+"'"+tjReq.getChannel()+"'";
				}
				if(tjReq.getFrequency().equals("1")) {
					if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword())) {
						sql = "SELECT DATE_FORMAT(from_unixtime(creat_time/1000) ,'%Y-%m') as time ,count(*) as count  FROM youhui_v3.user a "+table+"  where platform=? and from_unixtime(creat_time/1000) between ? and ?  "+channel+ "  group by  time";
					}
					else 	sql = "SELECT DATE_FORMAT(from_unixtime(creat_time/1000) ,'%Y-%m') as time ,count(*) as count  FROM youhui_v3.user a "+table+"  where  from_unixtime(creat_time/1000) between ? and ?  "+channel+ "   group by  time";
					
				}
				else if(tjReq.getFrequency().equals("2"))  {
					if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword()))
					sql = "SELECT DATE_FORMAT(from_unixtime(creat_time/1000) ,'%Y-%m-%d') as time ,count(*) as count  FROM youhui_v3.user a "+table+"  where platform=? and from_unixtime(creat_time/1000) between ? and ?  "+channel+ "  group by  time order by time desc";
					else 	sql = "SELECT DATE_FORMAT(from_unixtime(creat_time/1000) ,'%Y-%m-%d') as time ,count(*) as count  FROM youhui_v3.user a "+table+"  where  from_unixtime(creat_time/1000) between ? and ? "+channel+ "   group by  time  order by time desc";					
				}
				else  {
					if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword()))
					sql = "SELECT DATE_FORMAT(from_unixtime(creat_time/1000) ,'%Y-%m-%d %H') as time,count(*) as count  FROM youhui_v3.user a "+table+"  where platform=? and from_unixtime(creat_time/1000) between ? and ?  "+channel+ "   group by  time";		
					else 		sql = "SELECT DATE_FORMAT(from_unixtime(creat_time/1000) ,'%Y-%m-%d %H') as time,count(*) as count  FROM youhui_v3.user a "+table+" where  from_unixtime(creat_time/1000) between ? and ?  "+channel+ "   group by  time";		
					
				}
			}
		}
		else if(tjReq!=null&&tjReq.getType().equals("targetItemClick"))
		{
			if(leibie.equals("1"))	//商品交易
			{
				
			}
			else if(leibie.equals("2"))	//商品订单
			{
				
			}
			else if(leibie.equals("3"))	//商品订单成功
			{
				
			}
		}
		else if(tjReq!=null&&tjReq.getType().equals("newUserTrade"))
		{
			if(leibie.equals("1"))	//商品交易
			{
				
			}
			else if(leibie.equals("2"))	//商品订单
			{
				
			}
			else if(leibie.equals("3"))	//商品订单成功
			{
				
			}
		}
		else if(tjReq!=null&&tjReq.getType().equals("userFanxian"))
		{
			if(leibie.equals("1"))	//商品交易
			{
				if(tjReq.getFrequency().equals("1")) {
					if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword())) {
						sql = "SELECT left(pay_time,7) as time, sum(fxje) as count  FROM youhui_cn_fanli.duoduo_tradelist where outer_code=? and pay_time between ? and ?   group by  time order by time desc";
					}
					else 	sql = "SELECT left(pay_time,7) as time, sum(fxje) as count  FROM youhui_cn_fanli.duoduo_tradelist where  pay_time between ? and ?   group by  time order by time desc";
					
				}
				else if(tjReq.getFrequency().equals("2"))  {
					if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword()))
					sql = "SELECT left(pay_time,10) as time, sum(fxje) as count  FROM youhui_cn_fanli.duoduo_tradelist where outer_code=? and pay_time between ? and ?   group by  time order by time desc";
					else 	sql = "SELECT left(pay_time,10) as time, sum(fxje) as count  FROM youhui_cn_fanli.duoduo_tradelist where  pay_time between ? and ?   group by  time order by time desc";					
				}
				else  {
					if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword()))
					sql = "SELECT left(pay_time,13) as time, sum(fxje) as count  FROM youhui_cn_fanli.duoduo_tradelist where outer_code=? and pay_time  between ? and ?   group by  time order by time desc";		
					else 		sql = "SELECT left(pay_time,13) as time, sum(fxje) as count  FROM youhui_cn_fanli.duoduo_tradelist where  pay_time between ? and ?   group by  time order by time desc";		
					
				}
			}
			else if(leibie.equals("2"))	//商品订单
			{
				if(tjReq.getFrequency().equals("1")) {
					if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword())) {
						sql = "SELECT left(pay_time,7) as time, sum(commission) as count  FROM youhui_cn_fanli.duoduo_tradelist where outer_code=? and pay_time between ? and ?   group by  time order by time desc";
					}
					else 	sql = "SELECT left(pay_time,7) as time, sum(commission) as count  FROM youhui_cn_fanli.duoduo_tradelist where  pay_time between ? and ?   group by  time order by time desc";
					
				}
				else if(tjReq.getFrequency().equals("2"))  {
					if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword()))
					sql = "SELECT left(pay_time,10) as time, sum(commission) as count  FROM youhui_cn_fanli.duoduo_tradelist where outer_code=? and pay_time between ? and ?   group by  time order by time desc";
					else 	sql = "SELECT left(pay_time,10) as time, sum(commission) as count  FROM youhui_cn_fanli.duoduo_tradelist where  pay_time between ? and ?   group by  time order by time desc";					
				}
				else  {
					if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword()))
					sql = "SELECT left(pay_time,13) as time, sum(commission) as count  FROM youhui_cn_fanli.duoduo_tradelist where outer_code=? and pay_time  between ? and ?   group by  time order by time desc";		
					else 		sql = "SELECT left(pay_time,13) as time, sum(commission) as count  FROM youhui_cn_fanli.duoduo_tradelist where  pay_time between ? and ?   group by  time order by time desc";		
					
				}
			}
		}
		else if(tjReq!=null&&tjReq.getType().equals("jifenbao"))
		{
			if(leibie.equals("1")) 
			{
				if(tjReq.getFrequency().equals("1")) sql = "SELECT sum(jfb_num) as count ,left( from_unixtime(left(timestamp,10)) ,7) as time FROM youhui_cn_fanli.tyh_activity_join where (activity_id='001'  or activity_id='1') and ?< timestamp and timestamp<? group by time order by time desc;";
				else if(tjReq.getFrequency().equals("2")) sql = "SELECT sum(jfb_num) as count ,left( from_unixtime(left(timestamp,10)) ,10) as time FROM youhui_cn_fanli.tyh_activity_join where (activity_id='001'  or activity_id='1') and ?< timestamp and timestamp<?  group by date order by date desc;";
				else if(tjReq.getFrequency().equals("3")) sql = "SELECT sum(jfb_num) as count ,left( from_unixtime(left(timestamp,10)) ,13) as time FROM youhui_cn_fanli.tyh_activity_join where (activity_id='001'  or activity_id='1') and ?< timestamp and timestamp<? group by time order by time desc;";
				
			}
			else if(leibie.equals("2")) 
			{
				if(tjReq.getFrequency().equals("1")) sql = "SELECT sum(jfb_num)/count(uid) as count ,left( from_unixtime(left(timestamp,10)) ,7) as time FROM youhui_cn_fanli.tyh_activity_join where (activity_id='001'  or activity_id='1')  and ?< timestamp and timestamp<? group by time order by time desc;";
				else if(tjReq.getFrequency().equals("2")) sql = "SELECT sum(jfb_num)/count(uid) as count ,left( from_unixtime(left(timestamp,10)) ,10) as time FROM youhui_cn_fanli.tyh_activity_join where  (activity_id='001'  or activity_id='1')  and ?< timestamp and timestamp<?  group by date order by date desc;";
				else if(tjReq.getFrequency().equals("3")) sql = "SELECT sum(jfb_num)/count(uid) as count ,left( from_unixtime(left(timestamp,10)) ,13) as time FROM youhui_cn_fanli.tyh_activity_join where  (activity_id='001'  or activity_id='1')  and ?< timestamp and timestamp<? group by time order by time desc;";
				
			}
			else if(leibie.equals("3")) 
			{
				if(tjReq.getFrequency().equals("1")) sql = "SELECT count(uid) as count ,left( from_unixtime(left(timestamp,10)) ,7) as time FROM youhui_cn_fanli.tyh_activity_join where  (activity_id='001'  or activity_id='1')  and ?< timestamp and timestamp<? group by time order by time desc ;";
				else if(tjReq.getFrequency().equals("2")) sql = "SELECT count(uid) as count ,left( from_unixtime(left(timestamp,10)) ,10) as time FROM youhui_cn_fanli.tyh_activity_join where  (activity_id='001'  or activity_id='1')   and ?< timestamp and timestamp<?  group by date order by date desc;";
				else if(tjReq.getFrequency().equals("3")) sql = "SELECT count(uid) as count ,left( from_unixtime(left(timestamp,10)) ,13) as time FROM youhui_cn_fanli.tyh_activity_join where  (activity_id='001'  or activity_id='1')  and ?< timestamp and timestamp<? group by time order by time desc;";
				
			}
		}
		return sql;
	}
	
	public static PreparedStatement getPreparedStatement(Connection conn,String sql,TongjiRequest tjReq,String leibie) throws SQLException {
		PreparedStatement perstmt = null;
		perstmt = conn.prepareStatement(sql);

		if(tjReq!=null&&tjReq.getType().equals("itemTrade"))
		{
			if(leibie.equals("1")) 
			{
				perstmt.setString(1, tjReq.getKeyword());
				perstmt.setString(2, getbeforeString(tjReq.getStartDate()));
				perstmt.setString(3, getnextString(tjReq.getEndDate()));
			}
			else if(leibie.equals("2")||leibie.equals("3")) 
			{
				perstmt.setString(1, tjReq.getKeyword());
				perstmt.setString(2, getperString(tjReq.getStartDate()));
				perstmt.setString(3, getperString(tjReq.getEndDate()));
			}
		}
		//标签下商品数据统计
		else if(tjReq!=null&&tjReq.getType().equals("targetItemTrade"))
		{
			if(tjReq.getKeyword().equals(tttj)) 
			{
				perstmt.setString(1, "%天天特价%");
			}
			else if(tjReq.getKeyword().equals(jhs)) 
			{
				perstmt.setString(1, "%聚划算%");
			}
			else 
			{
				String ctagids = "";
				if(tjReq.getKeyword() != null && !tjReq.getKeyword().equals("613") && !tjReq.getKeyword().equals("601")){//优质精选和天猫特卖不通过子标签查询
					ctagids = Admin_Tag_Item_DAO.getCtagids(tjReq.getKeyword());//判断标签下是否有子标签，通过子标签来查询
				}
				if(ctagids != null && !"".equals(ctagids)){
					if(leibie.equals("1")) 
					{
						
						perstmt.setString(1, getbeforeString(tjReq.getStartDate()));
						perstmt.setString(2, getnextString(tjReq.getEndDate()));
					}
					else if(leibie.equals("2")||leibie.equals("3")) 
					{
						perstmt.setString(1, getperString(tjReq.getStartDate()));
						perstmt.setString(2, getperString(tjReq.getEndDate()));
					}
					return perstmt;
				}			
				perstmt.setString(1, tjReq.getKeyword());
			}
			if(leibie.equals("1")) 
			{
				
				perstmt.setString(2, getbeforeString(tjReq.getStartDate()));
				perstmt.setString(3, getnextString(tjReq.getEndDate()));
			}
			else if(leibie.equals("2")||leibie.equals("3")) 
			{
				perstmt.setString(2, getperString(tjReq.getStartDate()));
				perstmt.setString(3, getperString(tjReq.getEndDate()));
			}
		}
		else if(tjReq!=null&&tjReq.getType().equals("ItemOrderTotalTongji"))
		{
			if(leibie.equals("1")) 
			{
				perstmt.setString(1, getbeforeString(tjReq.getStartDate()));
				perstmt.setString(2, getnextString(tjReq.getEndDate()));
			}
			else if(leibie.equals("2")||leibie.equals("3")) 
			{
				perstmt.setString(1, getperString(tjReq.getStartDate()));
				perstmt.setString(2, getperString(tjReq.getEndDate()));
			}
		}
		else if(tjReq!=null&&tjReq.getType().equals("itemJine"))
		{
			if(tjReq.getKeyword().equals("all")) {
				perstmt.setString(1, getperString(tjReq.getStartDate()));
				perstmt.setString(2, getnextString(tjReq.getEndDate()));
			}
			else {			
				if(tjReq.getKeyword().equals(tttj)) 
				{
					perstmt.setString(1, "%天天特价%");
				}
				else if(tjReq.getKeyword().equals(jhs)) 
				{
					perstmt.setString(1, "%聚划算%");
				}
				else 
				{
					perstmt.setString(1, tjReq.getKeyword());
				}
				perstmt.setString(2, getperString(tjReq.getStartDate()));
				perstmt.setString(3, getnextString(tjReq.getEndDate()));
			}
		}
		else if(tjReq!=null&&tjReq.getType().equals("userRegister"))
		{
			if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword())) {
				perstmt.setString(1, tjReq.getKeyword());
				perstmt.setString(2, tjReq.getStartDate());
				perstmt.setString(3, tjReq.getEndDate());
				}
				else  {
					perstmt.setString(1, tjReq.getStartDate());
					perstmt.setString(2, tjReq.getEndDate());
				}
		}
		else if(tjReq!=null&&tjReq.getType().equals("targetItemClick"))
		{
		
		}
		else if(tjReq!=null&&tjReq.getType().equals("newUserTrade"))
		{
		
		}
		else if(tjReq!=null&&tjReq.getType().equals("userFanxian"))
		{
			if(tjReq.getKeyword()!=null&&!"".equals(tjReq.getKeyword())) {
				perstmt.setString(1, tjReq.getKeyword());
				perstmt.setString(2, tjReq.getStartDate());
				perstmt.setString(3, tjReq.getEndDate());
				}
				else  {
					perstmt.setString(1, tjReq.getStartDate());
					perstmt.setString(2, tjReq.getEndDate());
				}
		}
		else if(tjReq!=null&&tjReq.getType().equals("jifenbao"))
		{
			perstmt.setLong(1,CodeUtil.getUnixTimestemp(tjReq.getStartDate()+" 00:00:00"));
			perstmt.setLong(2, CodeUtil.getUnixTimestemp(tjReq.getEndDate()+" 00:00:00"));
		}
		return perstmt;
	}
	
	public static String getperString(String date) {
		String des = "";
		if(date!=null&&!date.equals("")&&date.length()>=10) {
			des = date.substring(0, 10);
		}
		return des;
	}
	
	public static String getbeforeString(String date) {
		String des = "";
		if(date!=null&&!date.equals("")) {
			des = date.substring(0, 10)+" 00:00:00";
		}
		return des;
	}
	
	public static String getnextString(String date) {
		String des = "";
		if(date!=null&&!date.equals("")) {
			des = date.substring(0, 10)+" 23:59:59";
		}
		return des;
	}
}
