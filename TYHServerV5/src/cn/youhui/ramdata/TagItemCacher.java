package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.dao.MySQLDBIns;

import com.google.gson.Gson;

public class TagItemCacher {
	private static final Logger log = Logger.getLogger(TagItemCacher.class);
	private static String cacheKey = X.CachePrefix.ITEM_CACHE;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static String isconvertKey = X.CachePrefix.IS_ITEMCLICK_CONVERT;
	private static TagItemCacher instance;

	private TagItemCacher() {
	}

	public static TagItemCacher getInstance() {
		return instance == null ? (instance = new TagItemCacher()) : instance;
	}

	public void init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {

			@Override
			public Boolean run(Connection conn, Jedis jedis)
					throws JedisConnectionException, SQLException {
				jedis.del(cacheLock);
				String cacheStatus = jedis.get(cacheLock);
				if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
					try {
						jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
						log.info("tagitemcacher init............");
						Statement s = conn.createStatement();
						String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` where `status` = 0;";
						ResultSet rs = s.executeQuery(sql);
						while (rs.next()) {
							TeJiaGoodsBean bean = new TeJiaGoodsBean();
							bean.setItem_id(rs.getString("item_id"));
							bean.setTitle(rs.getString("title"));
							bean.setKeyword(rs.getString("keyword"));
							bean.setPic_url(rs.getString("pic_url"));
							bean.setPrice_high(rs.getString("price_high"));
							bean.setPrice_low(rs.getString("price_low"));
							bean.setDiscount(rs.getString("discount"));
							bean.setClickURL(rs.getString("click_url"));
							bean.setShow_index(rs.getInt("show_index"));
							bean.setCommission(rs.getString("commission"));
							bean.setCommission_rate(rs.getString("commission_rate"));
							bean.setHeight_b(rs.getInt("height_b"));
							bean.setHeight_m(rs.getInt("height_m"));
							bean.setHeight_310(rs.getInt("height_310"));
							bean.setHeight_170(rs.getInt("height_170"));
							
							bean.setWidth_m(rs.getInt("width_m"));
							bean.setWidth_b(rs.getInt("width_b"));
							bean.setWidth_310(rs.getInt("width_310"));
							bean.setWidth_170(rs.getInt("width_170"));

							bean.setRate(rs.getDouble("jfb_rate"));
							bean.setCatID(rs.getString("catid"));
							bean.setUpdate_time(rs.getLong("update_time"));
							
							String json = new Gson().toJson(bean);
							jedis.hset(cacheKey, bean.getItem_id(), json);
						}
					} catch (SQLException e){
						e.printStackTrace();
						log.error("tagitemcacher init exception " + e.getMessage());
					}finally{
						jedis.del(cacheLock);
					}
				}
				return true;
			}
		};
		new RedisMySqlExecutor<Boolean>().exe(r);
	}

	public TeJiaGoodsBean getProduct(String itemId) {
		TeJiaGoodsBean bean = null;
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Gson gson = new Gson();
			String json = jedis.hget(cacheKey, itemId);
			
			
			//update_time 格式统一
			if(json!=null&&!"".equals(json)){
				String updateTime=json.split("\"update_time\":")[1].split(",")[0].replaceAll("\"", "");
				if(updateTime.contains("-")){
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time=sdf.parse(updateTime).getTime()+"";
					json=json.replaceAll(updateTime, time);
				}
			}
			
			
			
			if (json != null) {
				bean = gson.fromJson(json, TeJiaGoodsBean.class);
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
			e.printStackTrace();
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			e.printStackTrace();
			JedisDBIns.getInstance().release(jedis);
		}
		return bean;
	}
	
	public double getJfbRate(String itemId){
		double rate = 0.0;
		TeJiaGoodsBean item = getProduct(itemId);
		if(item != null){
			rate = item.getRate();
		}
		return rate;
	}

	public TeJiaGoodsBean getProductFromDB(String itemId) {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		TeJiaGoodsBean bean = null;
		try {
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` WHERE `item_id` = '"
					+ itemId + "' ;";
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				bean = new TeJiaGoodsBean();
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setClickURL(rs.getString("click_url"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setCommission(rs.getString("commission"));
				bean.setRate(rs.getDouble("jfb_rate"));
				bean.setCatID(rs.getString("catid"));
				bean.setCommission_rate(rs.getString("commission_rate"));
				bean.setUpdate_time(rs.getLong("update_time"));
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return bean;
	}

	public void addProduct(TeJiaGoodsBean bean) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Gson gson = new Gson();
			String json = gson.toJson(bean);
			jedis.hset(cacheKey, bean.getItem_id(), json);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			// TODO: handle exception
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			// TODO: handle exception
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void addProducts(List<TeJiaGoodsBean> list){
		if(list != null){
			Jedis jedis = JedisDBIns.getInstance().getJedis();
			try {
				Gson gson = new Gson();
				for(TeJiaGoodsBean bean : list){
					String jn = jedis.hget(cacheKey, bean.getItem_id());
					if (jn != null) {
						TeJiaGoodsBean olditem = gson.fromJson(jn, TeJiaGoodsBean.class);
						bean.setRate(olditem.getRate());
					}
					String json = gson.toJson(bean);
					jedis.hset(cacheKey, bean.getItem_id(), json);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				JedisDBIns.getInstance().release(jedis);
			}
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

	public String getIsconverted(){
		RedisRunner<String> r = new RedisRunner<String>() {
			@Override
			public String run(Jedis jedis) throws JedisConnectionException{
			     String isconvert = jedis.get(isconvertKey);
			     if(!"0".equals(isconvert) && !"1".equals(isconvert))
			    	 isconvert = "1";
			     return isconvert;
			}
		};
		RedisExecutor<String> e = new RedisExecutor<String>();
		return e.exe(r);
	}
	
	public static void mian(String[] args) {
		// TODO Auto-generated method stub
		TagItemCacher.getInstance().reload();
	}
}
