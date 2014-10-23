package cn.suishou.some.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public interface RedisRunner<T> {
	T run(Jedis jedis) throws JedisConnectionException ;
}
