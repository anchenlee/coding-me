package cn.youhui.ramdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;

public class TagStatsCacher {
	private static String VIEW_COUNT = "view_count";
	private static String CLICK_COUNT = "click_count";
	private static String DELETE_COUNT = "delete_count";
	private static String OWNED_COUNT = "owned_count";
	private static String cacheKey = X.CachePrefix.TAG_STATS;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static TagStatsCacher instance;

	private static Logger log = Logger.getLogger(TagStatsCacher.class);

	private TagStatsCacher() {
	}

	public static TagStatsCacher getInstance() {
		return instance == null ? (instance = new TagStatsCacher()) : instance;
	}

	public void init() {
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {

			@Override
			public Boolean run(Connection conn, Jedis jedis)
					throws JedisConnectionException, SQLException {
				// TODO Auto-generated method stub
				String cacheStatus = jedis.get(cacheLock);
				if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
					try {
						jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
						log.info("tagstatscacher init............");
						Statement s = conn.createStatement();
						String sql = "SELECT * FROM `youhui_datamining`.`tyh_tag_stats`;";
						ResultSet rs = s.executeQuery(sql);
						jedis.del(cacheKey);
						while (rs.next()) {
							jedis.hset(cacheKey, rs.getString("tag_id")
									+ VIEW_COUNT, rs.getString("view_count"));
							jedis.hset(cacheKey, rs.getString("tag_id")
									+ VIEW_COUNT, rs.getString("view_count"));
							jedis.hset(cacheKey, rs.getString("tag_id")
									+ VIEW_COUNT, rs.getString("view_count"));
						}
					} catch (SQLException e) {
						e.printStackTrace();
						log.error("tagstatscacher init exception " + e.getMessage());
					}finally{
						jedis.del(cacheLock);
					}
				}
				return true;
			}
		};
		new RedisMySqlExecutor<Boolean>().exe(r);
	}

	public void reload(){
	    init();
	}

	public void addStats(final String tagId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				jedis.hset(cacheKey, tagId + VIEW_COUNT, "0");
				jedis.hset(cacheKey, tagId + CLICK_COUNT, "0");
				jedis.hset(cacheKey, tagId + DELETE_COUNT, "0");
				jedis.hset(cacheKey, tagId + OWNED_COUNT, "0");
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public void incrOwnedCount(final String tagId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				jedis.hincrBy(cacheKey, tagId + OWNED_COUNT, 1);
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public void decrOwnedCount(final String tagId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				jedis.hincrBy(cacheKey, tagId + OWNED_COUNT, -1);
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public void incrViewCount(final String tagId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				jedis.hincrBy(cacheKey, tagId + VIEW_COUNT, 1);
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public void incrClickCount(final String tagId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				jedis.hincrBy(cacheKey, tagId + CLICK_COUNT, 1);
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}

	public void incrDeleteCount(final String tagId) {
		RedisRunner<Boolean> r = new RedisRunner<Boolean>() {

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				jedis.hincrBy(cacheKey, tagId + DELETE_COUNT, 1);
				return true;
			}
		};
		new RedisExecutor<Boolean>().exe(r);
	}
}
