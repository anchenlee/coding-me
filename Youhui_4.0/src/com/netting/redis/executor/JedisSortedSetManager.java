package com.netting.redis.executor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.netting.redis.RedisExecutor;
import com.netting.redis.RedisRunner;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * @category 对Redis的sortedset数据操作
 * @author YAOJIANBO
 * @since 2013-07-02
 */
public class JedisSortedSetManager
{
	public static long add(final String key, final double score, final String member)
	{		
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) 
			{
				long i = jedis.zadd(key, score, member);
				return i;
			}
		} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public static long size(final String key) 
	{		
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) 
			{
				long i = jedis.zcount(key, -Double.MAX_VALUE, Double.MAX_VALUE);
				return i;
			}
		} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public static long size1(final String key) 
	{		
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) 
			{
				long i = jedis.zcount(key, Double.parseDouble("-1000000"), Double.MAX_VALUE);
				return i;
			}
		} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public static long delete(final String key, final String member)
	{		
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) 
			{
				long i = jedis.zrem(key, member);
				return i;
			}
		} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public static long deleteByScore(final String key, final String minScore, final String maxScore)
	{		
		RedisRunner<Long> runner = new RedisRunner<Long>()
		{
			public Long run(Jedis jedis) 
			{
				long i = jedis.zremrangeByScore(key, minScore, maxScore);
				return i;
			}
		} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public static boolean isExist(final String key, final String member) 
	{		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>()
		{
			public Boolean run(Jedis jedis)
			{
			    Double d = jedis.zscore(key, member);
			    if(d != null) 
			    {
			    	return true;
			    }
				return false;
			}
		} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 返回所有成员值
	 */
	public static Set<String> getAll(final String key) 
	{
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>()
		{
			public Set<String> run(Jedis jedis) 
			{
				Set<String> i = jedis.zrange(key, 0, -1);
				return i;
			}
		} ;
		
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 返回所有成员值从大到小
	 */
	public static Set<String> getAllBydesc(final String key) 
	{
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>()
		{
			public Set<String> run(Jedis jedis) 
			{
				Set<String> i = jedis.zrevrange(key, 0, -1);
				return i;
			}
		} ;
		
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据Score从大到小排序</p>
	 * 该命令返回索引顺序在参数start和stop指定范围内的成员；</p>
	 * 这里start和stop参数都是0-based；</p>
	 * 即0表示第一个成员，-1表示最后一个成员；</p>
	 * @param key
	 * @param start 起始索引值
	 * @param end	结束索引值
	 * @return
	 */
	public static Set<String> getRevRange(final String key, final int start, final int end) 
	{		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>()
		{
			public Set<String> run(Jedis jedis) 
			{
				Set<String> i = jedis.zrevrange(key, start, end);
				return i;
			}
		} ;
		
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据Score从小到大排序</p>
	 * 该命令返回索引顺序在参数start和stop指定范围内的成员；</p>
	 * 这里start和stop参数都是0-based；</p>
	 * 即0表示第一个成员，-1表示最后一个成员；</p>
	 * @param key
	 * @param start 起始索引值
	 * @param end	结束索引值
	 * @return
	 */
	public static Set<String> zrange(final String key, final int start, final int end) 
	{		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>()
		{
			public Set<String> run(Jedis jedis) 
			{
				Set<String> i = jedis.zrange(key, start, end);
				return i;
			}
		} ;
		
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据Score从小到大排序, 返回成员及成员的Score</p>
	 * 该命令返回索引顺序在参数start和stop指定范围内的成员；</p>
	 * 这里start和stop参数都是0-based；</p>
	 * 即0表示第一个成员，-1表示最后一个成员；</p>
	 * @param key
	 * @param start 起始索引值
	 * @param end	结束索引值
	 * @return
	 */
	public static Map<String, Double> getRangeWithScores(final String key, final int start, final int end) 
	{		
		RedisRunner<Map<String, Double>> runner = new RedisRunner<Map<String, Double>>()
		{
			public Map<String, Double> run(Jedis jedis)
			{
				Set<Tuple> set = jedis.zrangeWithScores(key, start, end);
				
				Map<String, Double> map = new LinkedHashMap<String, Double>();
				if(set != null && set.size() > 0)
				{
					for(Tuple t : set)
					{
						map.put(t.getElement(), t.getScore());
					}
				}
				return map;
			}
		} ;
		
		RedisExecutor<Map<String, Double>> re = new RedisExecutor<Map<String, Double>>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据Score从大到小排序, 返回成员及成员的Score</p>
	 * 该命令返回索引顺序在参数start和stop指定范围内的成员；</p>
	 * 这里start和stop参数都是0-based；</p>
	 * 即0表示第一个成员，-1表示最后一个成员；</p>
	 * @param key
	 * @param start 起始索引值
	 * @param end	结束索引值
	 * @return
	 */
	public static Map<String, Double> getRevRangeWithScores(final String key, final int start, final int end) 
	{		
		RedisRunner<Map<String, Double>> runner = new RedisRunner<Map<String, Double>>()
		{
			public Map<String, Double> run(Jedis jedis)
			{
				Set<Tuple> set = jedis.zrevrangeWithScores(key, start, end);
				
				Map<String, Double> map = new LinkedHashMap<String, Double>();
				if(set != null && set.size() > 0)
				{
					for(Tuple t : set)
					{
						map.put(t.getElement(), t.getScore());
					}
				}
				return map;
			}
		} ;
		
		RedisExecutor<Map<String, Double>> re = new RedisExecutor<Map<String, Double>>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据Score从小到大排序, 返回成员及成员的Score</p>
	 * 返回Score在min和max之间的所有成员，即满足表达式min <= score <= max的成员</p>
	 * 其中返回的成员是按照其分数从低到高的顺序返回</p>
	 * 如果成员具有相同的分数，则按成员的字典顺序返回</p>
	 * @param key
	 * @param min 起始Score值
	 * @param max	结束Score值
	 * @return
	 */
	public static Map<String, Double> getRangeByScoresWithScores(final String key, final double min, final double max)
	{		
		RedisRunner<Map<String, Double>> runner = new RedisRunner<Map<String, Double>>()
		{
			public Map<String, Double> run(Jedis jedis)
			{
				Set<Tuple> set = jedis.zrangeByScoreWithScores(key, min, max);
				
				Map<String, Double> map = new LinkedHashMap<String, Double>();
				if(set != null && set.size() > 0)
				{
					for(Tuple t : set)
					{
						map.put(t.getElement(), t.getScore());
					}
				}
				
				return map;
			}
		} ;
		
		RedisExecutor<Map<String, Double>> re = new RedisExecutor<Map<String, Double>>();		
		return re.exe(runner) ;
	}

	/**
	 * 根据Score从大到小排序, 返回成员及成员的Score</p>
	 * 返回Score在max和min之间的所有成员，即满足表达式max => score >= min的成员</p>
	 * 其中返回的成员是按照其分数从高到低的分数排序返回</p>
	 * 如果成员具有相同的分数，则按成员的字典顺序返回</p>
	 * @param key
	 * @param max 最大Score值
	 * @param min	最小Score值
	 * @return
	 */
	public static Map<String, Double> getRevRangeByScoresWithScores(final String key, final double max, final double min)
	{		
		RedisRunner<Map<String, Double>> runner = new RedisRunner<Map<String, Double>>()
		{
			public Map<String, Double> run(Jedis jedis)
			{
				Set<Tuple> set = jedis.zrevrangeByScoreWithScores(key, max, min);
				
				Map<String, Double> map = new LinkedHashMap<String, Double>();
				if(set != null && set.size() > 0)
				{
					for(Tuple t : set)
					{
						map.put(t.getElement(), t.getScore());
					}
				}
				
				return map;
			}
		} ;
		
		RedisExecutor<Map<String, Double>> re = new RedisExecutor<Map<String, Double>>();		
		return re.exe(runner) ;
	}
}