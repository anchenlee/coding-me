package cn.youhui.platform.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisExecutor<T> {
	public RedisExecutor(){}
	
	public T exe(RedisRunner<T> jedisRunner){
		T rst = null ;
		Jedis j = null ;
		try{
			j = JedisDBIns.getInstance().getJedis() ;
			rst = jedisRunner.run(j) ;
			JedisDBIns.getInstance().release(j) ;
		}catch(JedisConnectionException e){
			JedisDBIns.getInstance().releaseBrokenJedis(j) ;
			e.printStackTrace() ;
		}catch(Exception e){
			JedisDBIns.getInstance().release(j);
			e.printStackTrace() ;
		}
		
		return rst ;
	}
}
