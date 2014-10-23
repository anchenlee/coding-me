
package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;

public class Tag4ItemCacher {
	private static final Logger log = Logger.getLogger(Tag4ItemCacher.class);
	
	private static String cacheKey = X.CachePrefix.TAG_ITEMS_4_TAG;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static Tag4ItemCacher instance;
	private Tag4ItemCacher() {
		
	}
	
	public static Tag4ItemCacher getInstance() {
		return instance == null ? (instance = new Tag4ItemCacher()) : instance;
	}
	
	public boolean init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
			
			@Override
			public Boolean run(Connection conn, Jedis jedis)
					throws JedisConnectionException, SQLException {
				String cacheStatus = jedis.get(cacheLock);
				if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
					try {
						jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
						log.info("tag4itemcacher init............");
						Statement s = conn.createStatement();
						String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` where `status`= 0 ORDER BY `update_time` DESC;";
						ResultSet rs = s.executeQuery(sql);
						jedis.del(cacheKey + "*");
						while (rs.next()){
							jedis.zadd(X.CachePrefix.TAG_ITEMS_4_TAG + rs.getString("keyword"), rs.getDouble("update_time"), rs.getString("item_id"));
						}
					} catch (Exception e) {
						log.info("tag4itemcacher init exception : " + e.getMessage());
					}finally{
						jedis.del(cacheLock);
					}
				}
				return true;
			}
		};
		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
		return e.exe(r);
	}
	
	public boolean reload() {
		return init();
	}
	
	public boolean addTagItem(final String keyword, final String itemId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {
			
			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				jedis.zadd(X.CachePrefix.TAG_ITEMS_4_TAG + keyword,
						System.currentTimeMillis(), itemId);
				return true;
			}
		};
		RedisExecutor<Boolean> e = new RedisExecutor<Boolean>();
		return e.exe(r);
	}
	
	public ArrayList<TeJiaGoodsBean> getTagItems(final String keyword, final int start, final int pageCount) {
		RedisRunner<ArrayList<TeJiaGoodsBean>> r = new RedisRunner<ArrayList<TeJiaGoodsBean>>() {
			
			@Override
			public ArrayList<TeJiaGoodsBean> run(Jedis jedis)
					throws JedisConnectionException {
				// TODO Auto-generated method stub
				ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
				Set<String> ids = jedis.zrevrange(X.CachePrefix.TAG_ITEMS_4_TAG + keyword, start, start + pageCount - 1);
				TagItemCacher cacher = TagItemCacher.getInstance();
				for (String itemId : ids) {
					TeJiaGoodsBean bean = cacher.getProduct(itemId);
					if (bean != null)
						list.add(bean);
				}
				return list;
			}
		};
		RedisExecutor<ArrayList<TeJiaGoodsBean>> e = new RedisExecutor<ArrayList<TeJiaGoodsBean>>();
		return e.exe(r);
	}
	
	public int getTagItemCount(final String keyword) {
		// TODO Auto-generated method stub
		RedisRunner<Integer> r = new RedisRunner<Integer>() {
			
			@Override
			public Integer run(Jedis jedis) throws JedisConnectionException {
				long total = jedis.zcount(X.CachePrefix.TAG_ITEMS_4_TAG
						+ keyword, Double.MIN_VALUE, Double.MAX_VALUE);
				return (int) total;
			}
		};
		RedisExecutor<Integer> e = new RedisExecutor<Integer>();
		return e.exe(r);
	}
}