package cn.youhui.ramdata;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;

public class FavLockCacher {
	static FavLockCacher cacher = new FavLockCacher();
	static String pre_key = X.CachePrefix.FAV_LOCK;
	public static FavLockCacher getInstance(){
		return cacher;
	}
	
	/**
	 * 检测收藏列表是否在刷新
	 * */
	public boolean isRefrush(final String key) {
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				boolean flag = false;
				if(jedis.hget(pre_key,key) != null){
					flag = true;
				}else{
					flag = false;
				}
				return flag;
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
	}
	/**
	 * 检测收藏列表是否在刷新
	 * */
	public boolean runRefrush(final String key) {
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				boolean flag = false;
				jedis.hset(pre_key,key,System.currentTimeMillis()+"");
				flag = true;
				return flag;
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
		
	}
	/**
	 * 删除刷新状态
	 * */
	public boolean destoryRefrush(final String key) {
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){

			@Override
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				// TODO Auto-generated method stub
				boolean flag = false;
				long i = jedis.hdel(pre_key,key);
				flag = i>0?true:false;
				return flag;
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;
		
		return re.exe(runner) ;
		
	}
	
}
