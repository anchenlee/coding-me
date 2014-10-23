package cn.suishou.some.manager;

import java.util.ArrayList;
import java.util.List;

import cn.suishou.some.config.RedisExecutor;
import cn.suishou.some.config.RedisRunner;




import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @category 对redis的list数据进行操作
 * @author leejun
 * @since 2012-10-21
 *
 */
public class JedisListManager {	
	
	private String key = new String();
	
	public JedisListManager(String key){
		this.key = key;
	}
	
	/**
	 * @return 添加成功返回true,更新返回false
	 */
	public long add( final String value) {
		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.rpush(key, value); 
				return i;
			}} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 添加一个List
	 * @param list
	 * @return
	 */
	public long addList(final List<String> list){
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis){
				long resultNum = 0l;
				for (String value : list){
					long i = jedis.rpush(key, value); 
					resultNum = resultNum + i;
				}
				return resultNum;
			}
		};
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
   public long size() 
   {
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.llen(key);
				return i;
			}} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}

	public List<String> getRange(final long start, final long end){
		RedisRunner<List<String>> runner = new RedisRunner<List<String>>(){
			public List<String> run(Jedis jedis) throws JedisConnectionException {
				 long length = jedis.llen(key);
				 List<String> value = new ArrayList<String>();
				 if(end > length){
				 	value = jedis.lrange(key, start, length);
				 }else{
				 	value = jedis.lrange(key, start, end);
				 }
				 return value;
		}} ;
			
		RedisExecutor<List<String>> re = new RedisExecutor<List<String>>();		
		return re.exe(runner) ;
	}
	
	public List<String> getAll() {
		
		RedisRunner<List<String>> runner = new RedisRunner<List<String>>(){
			public List<String> run(Jedis jedis) throws JedisConnectionException {
				 long end = jedis.llen(key);
				 List<String> value = jedis.lrange(key, 0, end);
				 return value;
			}} ;
			
		RedisExecutor<List<String>> re = new RedisExecutor<List<String>>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 清空
	 */
	public long clean() {
		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) throws JedisConnectionException {
				Long value = jedis.del(key);
				return value;
			}} ;
			
			RedisExecutor<Long> re = new RedisExecutor<Long>();		
			return re.exe(runner) ;
	}
	


}