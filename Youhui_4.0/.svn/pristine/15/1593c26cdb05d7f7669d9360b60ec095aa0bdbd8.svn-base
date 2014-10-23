package com.netting.redis.executor;

import java.util.Set;

import com.netting.redis.RedisExecutor;
import com.netting.redis.RedisRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @category 对Redis的key进行操作
 * @author YAOJIANBO
 * @since 2013-07-02
 */
public class JedisKeyManager 
{
	public static String set(final String key, final String value) 
	{		
		RedisRunner<String> runner = new RedisRunner<String>()
		{
			public String run(Jedis jedis) throws JedisConnectionException 
			{
				return jedis.set(key, value);
			}
		};
		RedisExecutor<String> re = new RedisExecutor<String>();		
		return re.exe(runner) ;
	}
	
	public static String setex(final String key, final String value, final int seconds) 
	{		
		RedisRunner<String> runner = new RedisRunner<String>()
		{
			public String run(Jedis jedis) throws JedisConnectionException 
			{
				return jedis.setex(key, seconds, value);
			}
		};
		RedisExecutor<String> re = new RedisExecutor<String>();		
		return re.exe(runner) ;
	}
	
	public static String get(final String key) 
	{		
		RedisRunner<String> runner = new RedisRunner<String>()
		{
			public String run(Jedis jedis) throws JedisConnectionException 
			{
				return jedis.get(key);
			}
		};
		RedisExecutor<String> re = new RedisExecutor<String>();		
		return re.exe(runner) ;
	}
	
	public static long expire(final String key, final int seconds) 
	{		
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) throws JedisConnectionException 
			{
				return jedis.expire(key, seconds);
			}
		};
		RedisExecutor<Long> re = new RedisExecutor<Long>();		
		return re.exe(runner) ;
	}
	
	public static long del(final String key) 
	{		
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) throws JedisConnectionException 
			{
				if(key == null || "".equals(key))
				{
					return 0l;
				}
				Set<String> set = jedis.keys(key + "*");
				if(set != null && set.size() > 0)
				{
					long count = 0;
					
					for(String s : set)
					{
						count = count + jedis.del(s);
					}
					return count;
				}
				else 
				{
					return 0l;
				}
			}
		};
		
		RedisExecutor<Long> re = new RedisExecutor<Long>();		
		return re.exe(runner) ;
	}
	
}
