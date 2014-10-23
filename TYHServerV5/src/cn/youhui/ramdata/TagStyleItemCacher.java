package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.ProductPageStyleBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.X;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.dao.MySQLDBIns;

import com.google.gson.Gson;

public class TagStyleItemCacher {
	private static final Logger log = Logger.getLogger(TagStyleItemCacher.class);
	private static String cacheKey = X.CachePrefix.STYLE_ITEM_CACHE;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static TagStyleItemCacher instance;
	private String reg = "^(\\d+,)*\\d+$";

	private TagStyleItemCacher() {
	}

	public static TagStyleItemCacher getInstance() {
		return instance == null ? (instance = new TagStyleItemCacher()) : instance;
	}

	public void init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {

			@Override
			public Boolean run(Connection conn, Jedis jedis)
					throws JedisConnectionException, SQLException {
				String cacheStatus = jedis.get(cacheLock);
				if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
					try {
						jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
						log.info("tagstyleitemcacher init............");
						Statement s = conn.createStatement();
//						String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products`;";
						String sql = "select * from youhui_datamining.m_product_page_style where status=1 ORDER BY position DESC";
						ResultSet rs = s.executeQuery(sql);
						jedis.del(cacheKey);
						while (rs.next()) {
							if(!rs.getString("pids").matches(reg)) continue; 
							ArrayList<TeJiaGoodsBean> tjlist = getPPSBean(rs.getString("pids"));

							ProductPageStyleBean bean = new ProductPageStyleBean();
							bean.setId(rs.getInt("id"));
							bean.setKeyword(rs.getString("keyword"));
							bean.setStyle(rs.getString("style"));
							bean.setPids(rs.getString("pids"));

							bean.setPosition(rs.getInt("position"));
							bean.setAddtime(rs.getString("addtime"));
							bean.setUpdatetime(rs.getString("updatetime"));
							bean.setStatus(rs.getString("status"));
							bean.setTjGoodsBeanList(tjlist);

							String json = new Gson().toJson(bean);
							jedis.hset(cacheKey, bean.getPids(), json);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						log.error("tagstyleitemcacher init exception " + e.getMessage());
					}finally{
						jedis.del(cacheLock);
					}
				}
				return true;
			}
		};
		new RedisMySqlExecutor<Boolean>().exe(r);
	}

	public ProductPageStyleBean getProduct(String itemId) {
		ProductPageStyleBean bean = null;
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Gson gson = new Gson();
			String json = jedis.hget(cacheKey, itemId);
			if (json != null) {
				bean = gson.fromJson(json, ProductPageStyleBean.class);
			}
			if (bean == null) {
				bean = getProductFromDB(itemId);
				if (bean != null)
					addProduct(bean);
				else {
					removeProduct(itemId);
				}
			}
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			// TODO: handle exception
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			// TODO: handle exception
			JedisDBIns.getInstance().release(jedis);
		}
		return bean;
	}

	public ProductPageStyleBean getProductFromDB(String id) {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		ProductPageStyleBean bean = null;
		try {
			Statement s = conn.createStatement();
			String sql = "select * from youhui_datamining.m_product_page_style where status=1 and pids ='" + id + "'";
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				if(rs.getString("pids").matches(reg)) {
				ArrayList<TeJiaGoodsBean> tjlist = getPPSBean(rs.getString("pids"));
				bean = new ProductPageStyleBean();
				bean.setId(rs.getInt("id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setStyle(rs.getString("style"));
				bean.setPids(rs.getString("pids"));
				bean.setPosition(rs.getInt("position"));
				bean.setAddtime(rs.getString("addtime"));
				bean.setUpdatetime(rs.getString("updatetime"));
				bean.setStatus(rs.getString("status"));
				bean.setTjGoodsBeanList(tjlist);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return bean;
	}
	
	
	public ArrayList<TeJiaGoodsBean> getPPSBean(String id) {
		Connection conn = null;
		ResultSet rs = null;
		ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "select * from youhui_datamining.m_discount_products where id in(" + id + ") order by field(id," + id + ")";
			rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
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

	public void addProduct(ProductPageStyleBean bean) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Gson gson = new Gson();
			String json = gson.toJson(bean);
			jedis.hset(cacheKey, bean.getPids(), json);
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

	public void reload() {
		init();
	}

	public static void main(String[] args) {
		TagStyleItemCacher cacher = new TagStyleItemCacher();
		cacher.reload();
	}
}
