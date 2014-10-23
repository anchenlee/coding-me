package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.M_Mix_PagetypeBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.dao.MySQLDBIns;

public class M_Mix_PagetypeCacher {

	
	private static final Logger log = Logger.getLogger(M_Mix_PagetypeCacher.class);
	private static M_Mix_PagetypeCacher instance = null;
	private static String cacheKey = X.CachePrefix.M_MIX_ITEM_CACHE;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static String reg = "^(\\d+,)*\\d+$";
	private M_Mix_PagetypeCacher() {
		
	}
	public static M_Mix_PagetypeCacher getInstance() {
		return instance == null ? (instance = new M_Mix_PagetypeCacher()) : instance;
	}
	
	public ArrayList<M_Mix_PagetypeBean> getProductByKeyword(String tagId,int pageSize,String page) {
		int total = getTagItemCount(tagId);
		int totalPage = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
		int page1 = Integer.parseInt(page);
		totalPage = totalPage > 0 ? totalPage : 1;
		ArrayList<M_Mix_PagetypeBean> list = new ArrayList<M_Mix_PagetypeBean>();
		if (page1 > totalPage) {
			page1 = totalPage;
		}
		list = getTagItems(tagId, (page1 - 1) * pageSize, pageSize);
		return list;
	}
	
	
	public ArrayList<M_Mix_PagetypeBean> getTagItems(final String tagId, final int start, final int pageCount) {
		RedisRunner<ArrayList<M_Mix_PagetypeBean>> r = new RedisRunner<ArrayList<M_Mix_PagetypeBean>>() {

			@Override
			public ArrayList<M_Mix_PagetypeBean> run(Jedis jedis)
					throws JedisConnectionException {
				ArrayList<M_Mix_PagetypeBean> mixList = new ArrayList<M_Mix_PagetypeBean>();
				Set<String> ids = jedis.zrevrange(X.CachePrefix.STYLE_TAG_ITEMS_4_TAG + tagId, start, start + pageCount - 1);
				for (String id : ids) {
					M_Mix_PagetypeBean mixBean = new M_Mix_PagetypeBean();
					Gson gson = new Gson();
					String json = jedis.hget(cacheKey, id);
					String itemIds = "";
					if (json != null) {
						mixBean = gson.fromJson(json, M_Mix_PagetypeBean.class);
						itemIds = mixBean.getItem_ids();
					}
					ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
					
					String[] array = itemIds.split(",");
					for(int i=0;i<array.length;i++) {			
						TeJiaGoodsBean bean = TagItemCacher.getInstance().getProduct(array[i]);
						if (bean != null)
						{
							list.add(bean);
						}
					}
					mixBean.setTjGoodsBeanList(list);
					mixList.add(mixBean);
				}
				return mixList;
			}
		};
		RedisExecutor<ArrayList<M_Mix_PagetypeBean>> e = new RedisExecutor<ArrayList<M_Mix_PagetypeBean>>();
		return e.exe(r);
	}
	
	
//	public M_Mix_PagetypeBean getProduct(String itemId) {
//		M_Mix_PagetypeBean bean = null;
//		Jedis jedis = JedisDBIns.getInstance().getJedis();
//		try {
//			Gson gson = new Gson();
//			String json = jedis.hget(cacheKey, itemId);
//			if (json != null) {
//				bean = gson.fromJson(json, M_Mix_PagetypeBean.class);
//			}
//			if (bean == null) {
//				bean = getProductFromDB(itemId);
//				if (bean != null)
//					addProduct(bean);
//				else {
//					removeProduct(itemId);
//				}
//			}
//			JedisDBIns.getInstance().release(jedis);
//		} catch (JedisConnectionException e) {
//			// TODO: handle exception
//			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
//		} catch (Exception e) {
//			// TODO: handle exception
//			JedisDBIns.getInstance().release(jedis);
//		}
//		return bean;
//	}
	
//	public M_Mix_PagetypeBean getProductFromDB(String id) {
//		Connection conn = MySQLDBIns.getInstance().getConnection();
//		M_Mix_PagetypeBean bean = null;
//		try {
//			Statement s = conn.createStatement();
//			String sql = "select * from youhui_datamining.m_product_page_style where status=1 and pids ='" + id + "'";
//			ResultSet rs = s.executeQuery(sql);
//			if (rs.next()) {
//					ArrayList<TeJiaGoodsBean> tjlist = getPPSBean(rs.getString("item_ids"));
//					bean = new M_Mix_PagetypeBean();
//					bean.setId(rs.getString("id"));
////					bean.setKeyword(rs.getString("keyword"));
//					bean.setTypeid(rs.getString("typeid"));
//					bean.setItem_ids(rs.getString("item_ids"));
//					bean.setTag_id(rs.getString("tag_id"));
//					bean.setRank(rs.getString("rank"));
//					bean.setUpdatetime(rs.getString("updatetime"));
//					bean.setStatus(rs.getString("status"));
//					bean.setTjGoodsBeanList(tjlist);
//				}
//		} catch (SQLException e) {
//		} finally {
//			MySQLDBIns.getInstance().release(conn);
//		}
//		return bean;
//	}
	
	public static ArrayList<TeJiaGoodsBean> getPPSBean(String id) {
		Connection conn = null;
		ResultSet rs = null;
		ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		String[] array = id.split(",");
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_datamining.m_discount_products where item_id =? and `status`=0 ";
			PreparedStatement perstamt = conn.prepareStatement(sql);
			for(int i=0;i<array.length;i++) {
				perstamt.setString(1, array[i]);
			rs = perstamt.executeQuery();
			if (rs.next()) {
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
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
				list.add(bean);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public void addProduct(M_Mix_PagetypeBean bean) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Gson gson = new Gson();
			String json = gson.toJson(bean);
			jedis.hset(cacheKey, bean.getId(), json);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			// TODO: handle exception
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			// TODO: handle exception
			JedisDBIns.getInstance().release(jedis);
		}
	}

	public void removeProduct(String itemId) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			jedis.hdel(cacheKey, itemId);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			// TODO: handle exception
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			// TODO: handle exception
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public int getTagItemCount(final String tagId) {
		// TODO Auto-generated method stub
		RedisRunner<Integer> r = new RedisRunner<Integer>() {

			@Override
			public Integer run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				long total = jedis.zcount(X.CachePrefix.STYLE_TAG_ITEMS_4_TAG
						+ tagId, Double.parseDouble("-1000000"), Double.MAX_VALUE);
				return (int) total;
			}
		};
		RedisExecutor<Integer> e = new RedisExecutor<Integer>();
		return e.exe(r);
	}
	
	public int getTotalNum(String tagId,int pageSize) {
		int total = getTagItemCount(tagId);
		int ys = (int) (total%pageSize);
		int ret = (int) ((total - ys)/pageSize);
		if(ys > 0)
			ret++;
		return ret;
	}
	
	public boolean reloadCache(String tagid) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
//		initTagItem();
		boolean result = false;
		try {
			Set<String> sets = jedis.keys(X.CachePrefix.STYLE_TAG_ITEMS_4_TAG + tagid);
			for (String key : sets)//删set
				jedis.del(key);
			jedis.del(X.CachePrefix.M_MIX_ITEM_CACHE);//删hs
			ArrayList<M_Mix_PagetypeBean> list = getListByTagid(tagid);

			for(M_Mix_PagetypeBean bean:list) {
//				jedis.hdel(PREFIX, bean.getPids());
//				jedis.zrem(TAG_ITEMS + keyword, bean.getPids());
				Gson gson = new Gson();
				String json = gson.toJson(bean);
				jedis.hset(X.CachePrefix.M_MIX_ITEM_CACHE, bean.getId(), json);
				jedis.zadd(X.CachePrefix.STYLE_TAG_ITEMS_4_TAG + bean.getTag_id(),Double.parseDouble( bean.getRank()), bean.getId());
			}
			result = true;
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			result = false;
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			result = false;
			JedisDBIns.getInstance().release(jedis);
		}
		return result;
	}
	
	public static ArrayList<M_Mix_PagetypeBean> getListByTagid(String tagid) {
		ArrayList<M_Mix_PagetypeBean> list = new ArrayList<M_Mix_PagetypeBean>();
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_datamining.m_mix_pagetype where status=1";
			if (tagid != null && !tagid.equals("")) {
				sql += " and tag_id = '" + tagid + "'";
			}
			sql += " order by rank desc";

			rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				
				if(!rs.getString("item_ids").matches(reg)) continue; 
//				ArrayList<TeJiaGoodsBean> tjlist = getPPSBean(rs.getString("item_ids"));
				M_Mix_PagetypeBean bean = new M_Mix_PagetypeBean();
				bean.setId(rs.getString("id"));
				bean.setTag_id(rs.getString("tag_id"));
//				bean.setKeyword(keyword);
				bean.setTypeid(rs.getString("typeid"));
				bean.setItem_ids(rs.getString("item_ids"));
				bean.setRank(rs.getString("rank"));
				bean.setUpdatetime(rs.getString("updatetime"));
				bean.setStatus(rs.getString("status"));
//				bean.setTjGoodsBeanList(tjlist);
				list.add(bean);
			}
			if(rs!=null)
			{				
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public void init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
			public Boolean run(Connection conn, Jedis jedis) throws JedisConnectionException, SQLException 
			{
				String cacheStatus = jedis.get(cacheLock);
				if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus))
				{
					try
					{
						jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
						log.info("tagStyleMix init.....");
						Statement s = conn.createStatement();
						String sql = "SELECT distinct(tag_id) FROM youhui_datamining.m_mix_pagetype";
						ResultSet rs = s.executeQuery(sql);
						jedis.del(cacheKey);
//						reloadCache("569");
						while (rs.next()) 
						{
							String tagid = rs.getString("tag_id");
//							reloadCache(tagid);
							
							Set<String> sets = jedis.keys(X.CachePrefix.STYLE_TAG_ITEMS_4_TAG + tagid);
							for (String key : sets)//删set
							{
								jedis.del(key);
							}
//							jedis.del(X.CachePrefix.M_MIX_ITEM_CACHE);//删hs
							ArrayList<M_Mix_PagetypeBean> list = getListByTagid(tagid);

							for(M_Mix_PagetypeBean bean:list) 
							{
								Gson gson = new Gson();
								String json = gson.toJson(bean);
								jedis.hset(X.CachePrefix.M_MIX_ITEM_CACHE, bean.getId(), json);
								jedis.zadd(X.CachePrefix.STYLE_TAG_ITEMS_4_TAG + bean.getTag_id(),Double.parseDouble( bean.getRank()), bean.getId());
							}						
						}
						if(rs!=null) 
						{
							rs.close();
						}
					} catch (Exception e) {
						log.info("tagStyleMix init exception : " + e.getMessage());
					}finally{
						jedis.del(cacheLock);
						MySQLDBIns.getInstance().release(conn);
						JedisDBIns.getInstance().release(jedis);
					}
				}
				return true;
			}
		};
		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
		e.exe(r);
	}
	
	public void reload()
	{
		init();
	}
	
	public static void main(String[] args) {
		M_Mix_PagetypeCacher.getInstance().reload();
	}
}
