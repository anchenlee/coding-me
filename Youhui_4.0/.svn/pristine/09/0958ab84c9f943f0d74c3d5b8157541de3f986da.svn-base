package com.netting.redis.executor;

import java.util.ArrayList;
import java.util.List;

import com.netting.redis.RedisExecutor;
import com.netting.redis.RedisRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @category 对Redis的list数据进行操作
 * @author YAOJIANBO
 * @since 2013-07-02
 *
 */
public class JedisListManager 
{	
	/**
	 * 向链表尾部添加元素
	 * @param key
	 * @param value
	 * @return 插入后链表中元素的数量
	 */
	public static long addToRump(final String key, final String value) 
	{
		if (key == null || value == null
				|| key.equals("") || value.equals(""))
		{
			return 0l;
		}
		
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) 
			{
				long i = jedis.rpush(key, value);
				return i;
			}
		} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 向链表头部添加元素
	 * @param key
	 * @param value
	 * @return 插入后链表中元素的数量
	 */
	public static long addToLead(final String key, final String value) 
	{
		if (key == null || value == null
				|| key.equals("") || value.equals(""))
		{
			return 0l;
		}
		
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) 
			{
				long i = jedis.lpush(key, value);
				return i;
			}
		} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 弹出并返回指定Key关联的链表中的第一个元素，即头部元素
	 * @param key
	 * @return 链表中的第一个元素，即头部元素
	 */
	public static String popFromLead(final String key) 
	{
		if (key == null || key.equals(""))
		{
			return null;
		}
		
		RedisRunner<String> runner = new RedisRunner<String>()
		{
			public String run(Jedis jedis) 
			{
				String value = jedis.lpop(key);
				return value;
			}
		} ;
		
		RedisExecutor<String> re = new RedisExecutor<String>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 弹出并返回指定Key关联的链表中的最后一个元素，即尾部元素
	 * @param key
	 * @return 链表中的最后一个元素，即尾部元素
	 */
	public static String popFromRump(final String key) 
	{
		if (key == null || key.equals(""))
		{
			return null;
		}
		
		RedisRunner<String> runner = new RedisRunner<String>()
		{
			public String run(Jedis jedis) 
			{
				String value = jedis.rpop(key);
				return value;
			}
		} ;
		
		RedisExecutor<String> re = new RedisExecutor<String>() ;		
		return re.exe(runner) ;
	}
	
	public static long size(final String key) 
	{
		if (key == null || key.equals(""))
		{
			return 0l;
		}
		
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) 
			{
				long i = jedis.llen(key);
				return i;
			}
		} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}

	public static List<String> getRange(final String key, final long start, final long end)
	{
		if (key == null || key.equals("") || start > end)
		{
			return null;
		}
		
		RedisRunner<List<String>> runner = new RedisRunner<List<String>>()
		{
			public List<String> run(Jedis jedis) throws JedisConnectionException 
			{
				 List<String> value = new ArrayList<String>();
				 
				 value = jedis.lrange(key, start, end);
				 return value;
			}
		};
			
		RedisExecutor<List<String>> re = new RedisExecutor<List<String>>();		
		return re.exe(runner) ;
	}
	
	public static List<String> getAll(final String key) 
	{
		if (key == null || key.equals(""))
		{
			return null;
		}
		
		RedisRunner<List<String>> runner = new RedisRunner<List<String>>()
		{
			public List<String> run(Jedis jedis) throws JedisConnectionException 
			{
				 List<String> value = jedis.lrange(key, 0, -1);
				 return value;
			}
		} ;
			
		RedisExecutor<List<String>> re = new RedisExecutor<List<String>>();		
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
		} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>();		
		return re.exe(runner) ;
	}

}