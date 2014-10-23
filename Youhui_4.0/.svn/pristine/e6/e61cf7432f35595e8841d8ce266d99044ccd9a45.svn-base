package com.netting.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netting.conf.Config;
import com.netting.conf.SysConf;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * REDIS連接池
 * @author YAOJIANBO
 * @version  1.0
 * @since 2012-12-26
 */
public class RedisConnectionPool
{
	/**
	 * 初始化日志
	 */
	private static Log logger = LogFactory.getLog( RedisConnectionPool.class );
	
	private static JedisPool pool = null;
	
	private static Jedis jedis = null;
	
	/**
	 * 初始化连接池
	 */
	public static void init()
	{
		JedisPoolConfig config = new JedisPoolConfig();
		
		config.setMaxActive(512);
		config.setMaxIdle(512);
		config.setMaxWait(1000);
		config.setTestOnBorrow(true);
		config.setTestWhileIdle(true);
		config.setTestOnReturn(true);
		config.minEvictableIdleTimeMillis = 60000;
		config.timeBetweenEvictionRunsMillis = 30000;
		config.numTestsPerEvictionRun = -1;
		
		pool = new JedisPool(config, Config.GLB_REDIS_HOST, Config.GLB_REDIS_PORT);
	}
	
	public static Jedis getConnection()
	{
		if (pool == null)
		{
			init();
		}
		jedis = pool.getResource();
		
		if (null != jedis && jedis.isConnected())
		{
			return jedis;
		}
		else
		{
			logger.info( "Redis服务器未连接......" );
			init();
			return getConnection();
		}
	}
	
	/**
	 * 连接使用完毕 释放
	 * @param jconn
	 */
	public static void releaseConn(Jedis jconn)
	{
		if (pool != null && jconn != null)
		{
			pool.returnResource( jconn );
		}
	}
	
	/**
	 * 释放出现问题的redis连接
	 * @param jconn
	 */
	public static void releaseBrokenConn(Jedis jconn) 
	{
		if (pool != null && jconn != null)
		{
			pool.returnBrokenResource(jconn);
		}
	}
	
	/**
	 * 释放连接池
	 */
	public static void destoryPool()
	{
		pool.destroy();
	}
}
