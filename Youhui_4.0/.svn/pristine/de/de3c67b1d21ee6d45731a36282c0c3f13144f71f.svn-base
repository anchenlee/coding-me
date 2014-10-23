package com.netting.redis.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.netting.redis.RedisExecutor;
import com.netting.redis.RedisRunner;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @category 对Redis的hash数据进行操作
 * @author YAOJIANBO
 * @since 2013-07-02
 *
 */
public class JedisHashManager 
{
	/**
	 * 为指定的Key设定Field/Value对</p>
	 * 如果Key不存在，该命令将创建新Key以参数中的Field/Value对</p>
	 * 如果参数中的Field在该Key中已经存在，则用新值覆盖其原有值</p>
	 * @param key
	 * @param field
	 * @param value
	 * @return 1表示新的Field被设置了新值</p>
	 * 			0表示Field已经存在，用新值覆盖原有值
	 */
	public static long set(final String key, final String field, final String value) 
	{
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) 
			{
				long i = jedis.hset(key, field, value);	
				return i;
			}
		};
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 返回指定Key中指定Field的关联值
	 * @param key
	 * @param field
	 * @return
	 */
	public static String get(final String key, final String field) 
	{
		if(null == key || null == field) 
		{
			return null;
		}
		
		RedisRunner<String> runner = new RedisRunner<String>()
		{
			public String run(Jedis jedis) throws JedisConnectionException
			{
				String value = jedis.hget(key, field);
				return value;
			}
		};
		
		RedisExecutor<String> re = new RedisExecutor<String>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 该键包含的所有Field/Value
	 * @param key
	 * @return 
	 */
	public static Map<String, String> getAll(final String key)
	{
		if(null == key) 
		{
			return null;
		}
		
		RedisRunner<Map<String, String>> runner = new RedisRunner<Map<String, String>>()
		{
			public Map<String, String> run(Jedis jedis) throws JedisConnectionException 
			{
				Map<String, String> ret = jedis.hgetAll(key);
				return ret;
			}
		};
			
		RedisExecutor<Map<String, String>> re = new RedisExecutor<Map<String, String>>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 逐对依次设置参数Map中的Field/Value对</p>
	 * 如果其中某个Field已经存在，则用新值覆盖原有值</p>
	 * 如果Key不存在，则创建新Key，同时设定参数中的Field/Value</p>
	 * @param key
	 * @param hash
	 * @return
	 */
	public static String setAll(final String key, final Map<String, String> hash) 
	{
		RedisRunner<String> runner = new RedisRunner<String>()
		{
			public String run(Jedis jedis) throws JedisConnectionException 
			{
				 String value = jedis.hmset(key, hash);
				 return value;
			}
		} ;
			
		RedisExecutor<String> re = new RedisExecutor<String>();		
		return re.exe(runner) ;
	}

	/**
	 * 返回指定Key的所有Fields
	 * @param key
	 * @return
	 */
	public static List<String> getAllField(final String key)
	{
		RedisRunner<List<String>> runner = new RedisRunner<List<String>>()
		{
			public List<String> run(Jedis jedis) throws JedisConnectionException 
			{
				List<String> ret = new ArrayList<String>();
				ret.addAll(jedis.hkeys(key));
				return ret;
			}
		};
		
		RedisExecutor<List<String>> re = new RedisExecutor<List<String>>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 判断指定Key中的指定Field是否存在
	 * @param key
	 * @param field
	 * @return
	 */
	public static boolean isExist(final String key, final String field) 
	{
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>()
		{
			public Boolean run(Jedis jedis) throws JedisConnectionException 
			{
				return  jedis.hexists(key, field);			
			}
		};
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 从指定Key的Hashes Value中删除参数中指定的多个字段，如果不存在的字段将被忽略</p>
	 * 如果Key不存在，则将其视为空Hashes，并返回0</p>
	 * @param key
	 * @param field
	 * @return 实际删除的Field数量
	 */
	public static long delete(final String key, final String field)
	{
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) throws JedisConnectionException 
			{
				long i = jedis.hdel(key, field);
				return i;
			}
		} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 获取该Key所包含的Field的数量
	 * @param key
	 * @return Key包含的Field数量，如果Key不存在，返回0。
	 */
	public static long size(final String key)
	{
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) throws JedisConnectionException 
			{
				long i = jedis.hlen(key);
				return i;
			}
		};
		
		RedisExecutor<Long> re = new RedisExecutor<Long>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 清空
	 */
	public static long clean(final String key) 
	{
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) throws JedisConnectionException 
			{
				Long value = jedis.del(key);
				return value;
			}
		};
			
		RedisExecutor<Long> re = new RedisExecutor<Long>();		
		return re.exe(runner) ;
	}
}