package com.netting.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisExecutor<T> 
{
	public RedisExecutor(){}
	
	public T exe(RedisRunner<T> jedisRunner)
	{
		T resultSet = null ;
		Jedis jconn = null ;
		try
		{
			jconn = RedisConnectionPool.getConnection();
			resultSet = jedisRunner.run(jconn) ;
			RedisConnectionPool.releaseConn(jconn);
		}
		catch(JedisConnectionException e)
		{
			RedisConnectionPool.releaseBrokenConn(jconn) ;
			e.printStackTrace() ;
		}
		catch(Exception e)
		{
			RedisConnectionPool.releaseBrokenConn(jconn) ;
			e.printStackTrace() ;
		}
		
		return resultSet ;
	}
}
