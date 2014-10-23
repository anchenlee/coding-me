package cn.youhui.common;

import java.sql.Connection;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import cn.youhui.dao.JedisDBIns;
import cn.youhui.dao.MySQLDBIns;

public class RedisMySqlExecutor<T> {
	public RedisMySqlExecutor(){}
	
	public T exe(RedisMySqlRunner<T> redisMysqlQuery){
		T rst = null ;
		Jedis j = null ;
		Connection  conn = null ;
		try{
			conn = MySQLDBIns.getInstance().getRealConnection() ;
			j = JedisDBIns.getInstance().getJedis() ;
			
			rst = redisMysqlQuery.run(conn, j) ;
			
			JedisDBIns.getInstance().release(j) ;
			
		} catch(JedisConnectionException e){
			
			JedisDBIns.getInstance().releaseBrokenJedis(j) ;
			
			e.printStackTrace() ;
		} catch(Exception e){
			
			JedisDBIns.getInstance().release(j) ;
			e.printStackTrace() ;
			
		} finally {
			
			MySQLDBIns.getInstance().release(conn) ;
			
		}
		
		return rst ;
	}
}
