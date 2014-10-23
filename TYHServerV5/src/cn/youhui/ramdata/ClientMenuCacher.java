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

public class ClientMenuCacher {
	private static final Logger log = Logger.getLogger(ClientMenuCacher.class);
	private static ClientMenuCacher instance = null;
	private static String ClientMenuCacheKey = X.CachePrefix.CLIENTMENU_XMLDATA;
	private static String ClientMenuVersion = X.CachePrefix.CLIENTMENU_VERSION;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + ClientMenuCacheKey;
	
	private ClientMenuCacher(){	
	}
	
	public static ClientMenuCacher getInstance(){
		if(instance == null) instance = new ClientMenuCacher();
		return instance;
	}
	
	public String getMenuxml() { 
		RedisRunner<String> r = new RedisRunner<String>() {
			@Override
			public String run(Jedis jedis) throws JedisConnectionException {
				 return jedis.get(ClientMenuCacheKey);
			}
		};
		RedisExecutor<String> e = new RedisExecutor<String>();
		return e.exe(r);
	}	
	
	public String getVersion(){
		RedisRunner<String> r = new RedisRunner<String>() {
			@Override
			public String run(Jedis jedis) throws JedisConnectionException {
				 return jedis.get(ClientMenuVersion);
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
						log.info("clientmenu init.....");
						Statement s = conn.createStatement();
						String sql = "select * from youhui_datamining.tyh_config where ckey = 'menuxml';";
						ResultSet rs = s.executeQuery(sql);
						if(rs.next()) {
							jedis.set(ClientMenuCacheKey, rs.getString("value"));
						}
					} catch (Exception e) {
						e.printStackTrace();
						log.info("clientmenu init exception:" + e.getMessage());
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
}
