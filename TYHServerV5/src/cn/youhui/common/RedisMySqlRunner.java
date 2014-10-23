package cn.youhui.common;

import java.sql.Connection;
import java.sql.SQLException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public interface RedisMySqlRunner<T> {
	T run(Connection conn , Jedis jedis) throws JedisConnectionException , SQLException ;
}
