package cn.youhui.ramdata;

/**
 * @author leejun
 * @since 2012-12-2
 */
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

	public class AppConfigCacher {
		private static final Logger log = Logger.getLogger(AppConfigCacher.class);
		private static AppConfigCacher instance = null;
		private static String AppConfigCacheKey = X.CachePrefix.APPCONFIG_DATA;
		private static String AnnouncementCacheKey = X.CachePrefix.ANNOUNCEMENT;
		private static String SpecialUrlCacheKey = X.CachePrefix.SPECIALURL;
		private static String LoadingAdCacheKey = X.CachePrefix.LOADINGAD;
		private static String AppConfig4IPhoneCacheKey = X.CachePrefix.APPCONFIG4IPHONE_DATA;
		private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + AppConfigCacheKey;
		private AppConfigCacher(){	
		}
		
		public static AppConfigCacher getInstance(){
			if(instance == null) instance = new AppConfigCacher();
			return instance;
		}
		
		public String getAnnouncementData() { 
			RedisRunner<String> r = new RedisRunner<String>() {
				@Override
				public String run(Jedis jedis) throws JedisConnectionException {
					 return jedis.get(AnnouncementCacheKey);
				}
			};
			RedisExecutor<String> e = new RedisExecutor<String>();
			return e.exe(r);
		}
		
		public String getSpecialUrlData() { 
			RedisRunner<String> r = new RedisRunner<String>() {
				@Override
				public String run(Jedis jedis) throws JedisConnectionException {
					 return jedis.get(SpecialUrlCacheKey);
				}
			};
			RedisExecutor<String> e = new RedisExecutor<String>();
			return e.exe(r);
		}
		
		public String getLoadingAdData() {
			RedisRunner<String> r = new RedisRunner<String>() {
				@Override
				public String run(Jedis jedis) throws JedisConnectionException {
					 return jedis.get(LoadingAdCacheKey);
				}
			};
			RedisExecutor<String> e = new RedisExecutor<String>();
			return e.exe(r);
		}
		
		public String getData() { 
			RedisRunner<String> r = new RedisRunner<String>() {
				@Override
				public String run(Jedis jedis) throws JedisConnectionException {
					 return jedis.get(AppConfigCacheKey);
				}
			};
			RedisExecutor<String> e = new RedisExecutor<String>();
			return e.exe(r);
		}
		
		public String getIPhoneConfigData() { 
			RedisRunner<String> r = new RedisRunner<String>() {
				@Override
				public String run(Jedis jedis) throws JedisConnectionException {
					 return jedis.get(AppConfig4IPhoneCacheKey);
				}
			};
			RedisExecutor<String> e = new RedisExecutor<String>();
			return e.exe(r);
		}	
		
		public String setData(final String data) { 
			RedisRunner<String> r = new RedisRunner<String>() {
				@Override
				public String run(Jedis jedis) throws JedisConnectionException {
					 return jedis.set(AppConfigCacheKey, data);
				}
			};
			RedisExecutor<String> e = new RedisExecutor<String>();
			return e.exe(r);
		}	
		
		public void init() {
			RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() {
				@Override
				public Boolean run(Connection conn, Jedis jedis) throws JedisConnectionException, SQLException {
					String cacheStatus = jedis.get(cacheLock);
					if(!X.CachePrefix.LOCK_MARK.equals(cacheStatus)){
						try {
							jedis.set(cacheLock, X.CachePrefix.LOCK_MARK);
							log.info("appconfig init.....");
							Statement s = conn.createStatement();
							String sql = "select * from youhui_datamining.tyh_config where ckey = 'appconfig';";
							ResultSet rs = s.executeQuery(sql);
							if(rs.next()) 
							{
								jedis.set(AppConfigCacheKey, rs.getString("value"));
							}
							
							sql = "select * from youhui_datamining.tyh_config where ckey = 'appconfig4iphone';";
							rs = s.executeQuery(sql);
							if(rs.next()) 
							{
								jedis.set(AppConfig4IPhoneCacheKey, rs.getString("value"));
							}
						} catch (Exception e) {
							e.printStackTrace();
							log.info("appconfig init exception:" + e.getMessage());
						}finally{
							jedis.del(cacheLock);
						}
					}
					return true;
				}
			};
			RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
			e.exe(r);
		}

		public void reload() {
			init();
		}
		
		public static void main(String[] a){
			System.out.println(AppConfigCacher.getInstance().getAnnouncementData());
		}
		
}
